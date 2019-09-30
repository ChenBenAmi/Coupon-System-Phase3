package com.project.CouponSystem.services;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.project.CouponSystem.beans.ClientType;
import com.project.CouponSystem.beans.Coupon;
import com.project.CouponSystem.beans.CouponType;
import com.project.CouponSystem.beans.Customer;
import com.project.CouponSystem.repo.CouponRepo;
import com.project.CouponSystem.repo.CustomerRepo;

@Service
@Transactional
public class CustomerService implements CouponClient {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CouponRepo couponRepo;

	private Map<String, Long> tokens = new Hashtable<>();

	@Override
	public ResponseEntity<?> login(String name, String password, ClientType clientType) {
		Customer customer = customerRepo.findCustomerByCustomerName(name);
		if (customer != null) {
			if (customer.getPassword().equalsIgnoreCase(password)) {
				String token = UUID.randomUUID().toString();
				tokens.put(token, customer.getId());
				return ResponseEntity.ok("Logged in as " + clientType.toString() + " your token is: " + token);
			}
		}
		return ResponseEntity.badRequest().body("User name or password incorrect");
	}

	@Override
	public ResponseEntity<?> logout(String token) {
		tokens.remove(token);
		return ResponseEntity.ok("Logged out successfully");
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
							return ResponseEntity.ok(customerRepo.save(customer));
						}
					}
				}
			}
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please login");
	}

	public ResponseEntity<Object> getAllHistory(String token) {
		if (tokens.containsKey(token)) {
			Customer customer = customerRepo.findCustomerById(tokens.get(token));
			if (customer != null) {
				if(customer.getCouponsCollection()!=null && customer.getCouponsCollection().size()>0) {
					ResponseEntity.ok(customer.getCouponsCollection());
				}
			}
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please login");
	}

	public ResponseEntity<Object> getHistoryByType(String token, CouponType couponType) {
		List<Coupon> couponsList=new ArrayList<>();
		if (tokens.containsKey(token)) {
			Customer customer = customerRepo.findCustomerById(tokens.get(token));
			if (customer != null) {
				if(customer.getCouponsCollection()!=null && customer.getCouponsCollection().size()>0) {
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
		List<Coupon> couponsList=new ArrayList<>();
		if (tokens.containsKey(token)) {
			Customer customer = customerRepo.findCustomerById(tokens.get(token));
			if (customer != null) {
				if(customer.getCouponsCollection()!=null && customer.getCouponsCollection().size()>0) {
					for (Map.Entry<Long, Coupon> entry : customer.getCouponsCollection().entrySet()) {
						if (entry.getValue().getPrice()==price) {
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

}
