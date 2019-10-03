package com.project.CouponSystem.services;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.project.CouponSystem.beans.ClientType;
import com.project.CouponSystem.beans.Company;
import com.project.CouponSystem.beans.Coupon;
import com.project.CouponSystem.beans.CouponType;
import com.project.CouponSystem.repo.CompanyRepo;
import com.project.CouponSystem.repo.CouponRepo;

@Service
@Transactional
public class CompanyService implements CouponClient {
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CouponRepo couponRepo;

	private Map<String, Long> tokens = new Hashtable<>();

	@Override
	public ResponseEntity<?> login(String name, String password, ClientType clientType) {
		Company company = companyRepo.findCompanyBycompName(name);
		if (company != null) {
			if (company.getPassword().equalsIgnoreCase(password)) {
				String token = UUID.randomUUID().toString();
				tokens.put(token, company.getId());
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

	public ResponseEntity<Object> createCoupon(String token, @RequestBody Coupon coupon) {
		if (tokens.containsKey(token)) {
			Company company = companyRepo.findCompanyById(tokens.get(token));
			Coupon thisCoupon = couponRepo.findCouponById(coupon.getId());
			if (company != null) {
				if (thisCoupon != null) {

					company.getCouponsCollection().put(company.getId(), thisCoupon);
					return ResponseEntity.ok("Coupon add Sucess");
				} else {
					return ResponseEntity.badRequest().body("Cant find Coupon");

				}
			} else {
				return ResponseEntity.badRequest().body("Cant find Company");

			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
		}

	}

	public ResponseEntity<Object> deleteCoupon(String token, long couponId) {
		if (tokens.containsKey(token)) {
		Company company = companyRepo.findCompanyById(tokens.get(token));
			if (company != null) {
				if (couponRepo.findCouponById(couponId) != null) {
					company.getCouponsCollection().remove(company.getId(), couponRepo.findCouponById(couponId));
					couponRepo.deleteById(couponId);
					return ResponseEntity.ok("Coupon Delete sucess");
				} else {
					return ResponseEntity.badRequest().body("Cant find Coupon");
				}
			} else {
				return ResponseEntity.badRequest().body("Cant find Company");

			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
		}
	}

	public ResponseEntity<Object> updateCoupon(String token, @RequestBody Coupon coupon) {
		if (tokens.containsKey(token)) {
		Company company = companyRepo.findCompanyById(tokens.get(token));
		
			if (company != null) {
				if (coupon != null) { /// key ///// //// old value///////////////////// /////new value = coupon /////
					company.getCouponsCollection().replace(coupon.getId(), couponRepo.findCouponById(coupon.getId()),
							coupon);
					return ResponseEntity.ok("Coupon Update sucess");
				} else {
					return ResponseEntity.badRequest().body("Cant find Coupon");
				}
			} else {
				return ResponseEntity.badRequest().body("Cant find Company");
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
		}

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
				return ResponseEntity.ok(company.getCouponsCollection().toString());
			} else {
				return ResponseEntity.badRequest().body("Cant find Company");
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
		}
	}
	//TODO fix method 
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
	
	//TODO fix method 
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

	//TODO fix method 
	public ResponseEntity<Object> getCouponByDate(String token, LocalDateTime localdatetime) {
		if (tokens.containsKey(token)) {
		Company company = companyRepo.findCompanyById(tokens.get(token));
			if (company != null) {
				return ResponseEntity.ok(couponRepo.findCouponByendDate(localdatetime));
			} else {
				return ResponseEntity.badRequest().body("Cant find Company");
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please login!");
		}
	}
}
