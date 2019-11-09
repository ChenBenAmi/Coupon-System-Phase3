package com.project.CouponSystem.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.project.CouponSystem.beans.ClientType;
import com.project.CouponSystem.beans.Coupon;
import com.project.CouponSystem.beans.CouponType;
import com.project.CouponSystem.beans.Customer;
import com.project.CouponSystem.beans.Income;
import com.project.CouponSystem.beans.IncomeType;
import com.project.CouponSystem.repo.CouponRepo;
import com.project.CouponSystem.repo.CustomerRepo;

@Service
@Transactional
public class CustomerService implements CouponClient {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CouponRepo couponRepo;
	@Autowired
	private IncomeService incomeService;

	private Map<String, Long> tokens = new Hashtable<>();

	@Override
	public ResponseEntity<?> login(String name, String password, ClientType clientType) {
		Customer customer = customerRepo.findCustomerByCustomerName(name);
		if (customer != null) {
			if (customer.getPassword().equalsIgnoreCase(password)) {
				String token = UUID.randomUUID().toString();
				tokens.put(token, customer.getId());
				return ResponseEntity.ok(token);
			}
		}
		return ResponseEntity.badRequest().body("User name or password incorrect");
	}

	@Override
	public ResponseEntity<?> logout(String token) {
		if (tokens.containsKey(token)) {
			tokens.remove(token);
			return ResponseEntity.ok("Logged out successfully");
		}
		return ResponseEntity.badRequest().body("Token Doesnt exist");
	}

	public ResponseEntity<Object> purchaseCoupon(String token, long couponId) {
		if (tokens.containsKey(token)) {
			Customer customer = customerRepo.findCustomerById(tokens.get(token));
			if (customer != null) {
				Coupon coupon = couponRepo.findCouponById(couponId);
				if (coupon != null) {
					if (coupon.getAmount() > 0) {
						coupon.setAmount(coupon.getAmount() - 1);
						long id = couponRepo.save(coupon).getId();
						if (customer.getCouponsCollection() != null) {
							customer.getCouponsCollection().put(id, coupon);
							Income income = new Income();
							income.setAmount(coupon.getPrice());
							income.setDate(LocalDateTime.now());
							income.setName(customer.getCustomerName());
							income.setDescription(IncomeType.CUSTOMER_PURCHASE);
							Income storedIncome = incomeService.storeIncome(income);
							if (customer.getIncomeCollection() != null) {
								customer.getIncomeCollection().put(storedIncome.getId(), storedIncome);
								return ResponseEntity.ok(customerRepo.save(customer));
							} else {
								customer.setIncomeCollection(new Hashtable<>());
								customer.getIncomeCollection().put(storedIncome.getId(), storedIncome);
								return ResponseEntity.ok(customerRepo.save(customer));
							}
						}
					}
				}
				return ResponseEntity.badRequest().body("Coupon cant be null");
			}
			return ResponseEntity.badRequest().body("Cant find Customer");
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please login");
	}

	public ResponseEntity<Object> getAllHistory(String token) {
		if (tokens.containsKey(token)) {
			Customer customer = customerRepo.findCustomerById(tokens.get(token));
			if (customer != null) {
				if (customer.getCouponsCollection() != null && customer.getCouponsCollection().size() > 0) {
					ResponseEntity.ok(customer.getCouponsCollection());
				}
			}
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please login");
	}

	public ResponseEntity<Object> getHistoryByType(String token, CouponType couponType) {
		List<Coupon> couponsList = new ArrayList<>();
		if (tokens.containsKey(token)) {
			Customer customer = customerRepo.findCustomerById(tokens.get(token));
			if (customer != null) {
				if (customer.getCouponsCollection() != null && customer.getCouponsCollection().size() > 0) {
					for (Map.Entry<Long, Coupon> entry : customer.getCouponsCollection().entrySet()) {
						if (entry.getValue().getCouponType().equals(couponType)) {
							couponsList.add(entry.getValue());
						}
					}
					return ResponseEntity.ok(couponsList);
				}
				return ResponseEntity.badRequest().body("No coupons available");
			}
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please login");
	}

	public ResponseEntity<Object> getHistoryByPrice(String token, double price) {
		List<Coupon> couponsList = new ArrayList<>();
		if (tokens.containsKey(token)) {
			Customer customer = customerRepo.findCustomerById(tokens.get(token));
			if (customer != null) {
				if (customer.getCouponsCollection() != null && customer.getCouponsCollection().size() > 0) {
					for (Map.Entry<Long, Coupon> entry : customer.getCouponsCollection().entrySet()) {
						if (entry.getValue().getPrice() == price) {
							couponsList.add(entry.getValue());
						}
					}
					return ResponseEntity.ok(couponsList);
				}
				return ResponseEntity.badRequest().body("No coupons available");
			}
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please login");
	}

	public ResponseEntity<?> viewIncome(@RequestParam String token) {
		if (tokens.containsKey(token)) {
			Optional<Customer> customer = customerRepo.findById(tokens.get(token));
			if (customer.isPresent()) {

				return incomeService.viewIncomeByCustomer(customer.get().getId());

			}
			return ResponseEntity.badRequest().body("Cant find Customer");
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
	}

}
