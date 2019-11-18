package com.project.CouponSystem.services;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.CouponSystem.beans.ClientType;
import com.project.CouponSystem.beans.Company;
import com.project.CouponSystem.beans.Coupon;
import com.project.CouponSystem.beans.CouponType;
import com.project.CouponSystem.beans.Income;
import com.project.CouponSystem.beans.IncomeType;
import com.project.CouponSystem.repo.CompanyRepo;
import com.project.CouponSystem.repo.CouponRepo;
import com.project.CouponSystem.utils.DateUtils;

@Service
@Transactional
public class CompanyService implements CouponClient {
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CouponRepo couponRepo;

	@Autowired
	private IncomeService incomeService;

	private Map<String, Long> tokens = new Hashtable<>();

	@Override
	public ResponseEntity<?> login(String name, String password, ClientType clientType) {
		Company company = companyRepo.findCompanyBycompanyName(name);
		if (company != null) {
			if (company.getPassword().equalsIgnoreCase(password)) {
				String token = UUID.randomUUID().toString();
				tokens.put(token, company.getId());
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

	public ResponseEntity<Object> createCoupon(String token, @RequestBody Coupon coupon) {
		if (tokens.containsKey(token)) {
			Company company = companyRepo.findCompanyById(tokens.get(token));
			if (company != null) {
				if (coupon != null) {
					coupon.setStartDate(DateUtils.formatDate());
					long id = couponRepo.save(coupon).getId();
					company.getCouponsCollection().put(id, coupon);
					Income income = new Income();
					income.setAmount(100);
					income.setDate(DateUtils.formatDate());
					income.setName(company.getCompanyName());
					income.setDescription(IncomeType.COMPANY_NEW_COUPON);
					Income storedIncome = incomeService.storeIncome(income);
					if (company.getIncomeCollection() != null) {
						company.getIncomeCollection().put(storedIncome.getId(), storedIncome);
						companyRepo.save(company);
						return ResponseEntity.ok(couponRepo.findById(id));
					} else {
						company.setIncomeCollection(new Hashtable<>());
						company.getIncomeCollection().put(storedIncome.getId(), storedIncome);
						companyRepo.save(company);
						return ResponseEntity.ok(couponRepo.findById(id));
					}
				}
				return ResponseEntity.badRequest().body("Coupon cant be null");
			}
			return ResponseEntity.badRequest().body("Cant find Company");
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
	}

	public ResponseEntity<Object> deleteCoupon(String token, long couponId) {
		if (tokens.containsKey(token)) {
			Company company = companyRepo.findCompanyById(tokens.get(token));
			Optional<Coupon> coupon = couponRepo.findById(couponId);
			if (company != null) {
				if (coupon.isPresent()) {
					if (company.getCouponsCollection() != null && company.getCouponsCollection().size() > 0) {
						company.getCouponsCollection().remove(company.getId(), couponRepo.findCouponById(couponId));
						couponRepo.deleteById(couponId);
						return ResponseEntity.ok("Coupon Delete sucess");
					}
					return ResponseEntity.badRequest().body("Company couponCollection is empty");
				}
				return ResponseEntity.badRequest().body("Cant find Coupon");
			}
			return ResponseEntity.badRequest().body("Cant find Company");
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
	}

	public ResponseEntity<Object> updateCoupon(String token, @RequestBody Coupon coupon) {
		if (tokens.containsKey(token)) {
			Company company = companyRepo.findCompanyById(tokens.get(token));
			if (company != null) {
				Optional<Coupon> dbCoupon = couponRepo.findById(coupon.getId());
				if (dbCoupon.isPresent()) {
					couponRepo.save(coupon);
					company.getCouponsCollection().put(coupon.getId(), coupon);
					Income income = new Income();
					income.setAmount(10);
					income.setDate(DateUtils.formatDate());
					income.setName(company.getCompanyName());
					income.setDescription(IncomeType.COMPANY_UPDATE_COUPON);

					Income storedIncome = incomeService.storeIncome(income);
					if (company.getIncomeCollection() != null) {
						company.getIncomeCollection().put(storedIncome.getId(), storedIncome);
						return ResponseEntity.ok(companyRepo.save(company));
					} else {
						company.setIncomeCollection(new Hashtable<>());
						company.getIncomeCollection().put(storedIncome.getId(), storedIncome);
						return ResponseEntity.ok(companyRepo.save(company));
					}
				}
				return ResponseEntity.badRequest().body("Cant find Coupon");
			}
			return ResponseEntity.badRequest().body("Cant find Company");
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
	}

	public ResponseEntity<Object> getCompany(String token) {
		if (tokens.containsKey(token)) {
			Company company = companyRepo.findCompanyById(tokens.get(token));
			if (company != null) {
				return ResponseEntity.ok(company);
			} else {
				return ResponseEntity.badRequest().body("Cant find Company");
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
		}
	}

	public ResponseEntity<Object> getAllCoupon(String token) {
		if (tokens.containsKey(token)) {
			Company company = companyRepo.findCompanyById(tokens.get(token));
			if (company != null) {
				return ResponseEntity.ok(company.getCouponsCollection());
			} else {
				return ResponseEntity.badRequest().body("Cant find Company");
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
		}
	}

	// TODO fix method
	public ResponseEntity<Object> getCouponByType(String token, CouponType couponType) {
		if (tokens.containsKey(token)) {
			Company company = companyRepo.findCompanyById(tokens.get(token));
			if (company != null) {
				return ResponseEntity.ok(couponRepo.findCouponBycouponType(couponType));
			} else {
				return ResponseEntity.badRequest().body("Cant find Company");
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
		}
	}

	// TODO fix method
	public ResponseEntity<Object> getCouponByPrice(String token, double price) {
		Company company = companyRepo.findCompanyById(tokens.get(token));
		if (tokens.containsKey(token)) {
			if (company != null) {
				return ResponseEntity.ok(couponRepo.findCouponByprice(price));
			} else {
				return ResponseEntity.badRequest().body("Cant find Company");
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
		}
	}

	// TODO fix method
	public ResponseEntity<Object> getCouponByDate(String token, Date date) {
		if (tokens.containsKey(token)) {
			Company company = companyRepo.findCompanyById(tokens.get(token));
			if (company != null) {
				return ResponseEntity.ok(couponRepo.findCouponByendDate(date));
			} else {
				return ResponseEntity.badRequest().body("Cant find Company");
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
		}
	}

	public ResponseEntity<?> viewIncome(@RequestParam String token) {
		if (tokens.containsKey(token)) {
			Optional<Company> company = companyRepo.findById(tokens.get(token));
			if (company.isPresent()) {
				return incomeService.viewIncomeByCompany(company.get().getId());
			}
			return ResponseEntity.badRequest().body("Cant find Company");
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
	}

}
