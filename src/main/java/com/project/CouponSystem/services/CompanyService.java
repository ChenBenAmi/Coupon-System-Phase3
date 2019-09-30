package com.project.CouponSystem.services;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
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
		Company company=companyRepo.findCompanyBycompName(name);
		if (company!=null) {
			if (company.getPassword().equalsIgnoreCase(password)) {
				String token=UUID.randomUUID().toString();
				tokens.put(token,company.getId());
				return ResponseEntity.ok("Logged in as "+clientType.toString()+" your token is: "+token);
			}
		}
		return ResponseEntity.badRequest().body("User name or password incorrect");
	}
	
	@Override
	public ResponseEntity<?> logout(String token) {
		return null;
	}
	
	public RequestEntity<Object> createCoupon(long compId, @RequestBody Coupon coupon) {
		Company company = companyRepo.findCompanyById(compId);
		Coupon thisCoupon = couponRepo.findCouponById(coupon.getId());
		if(company != null){
			
		}
		return null;
		
	}

	public RequestEntity<Object> deleteCoupon(long compId, long couponId) {
		Company company = companyRepo.findCompanyById(compId);
		if(company != null){
			
		}
		return null;
	}

	public RequestEntity<Object> updateCoupon(long compId, @RequestBody Coupon coupon) {
		Company company = companyRepo.findCompanyById(compId);
		if(company != null){
			
		}
		return null;
	}

	public RequestEntity<Object> getCompany(long compId) {
		Company company = companyRepo.findCompanyById(compId);
		if(company != null){
			
		}
		return null;
	}

	public RequestEntity<Object> getAllCoupon(long compId) {
		Company company = companyRepo.findCompanyById(compId);
		if(company != null){
			
		}
		return null;
	}

	public RequestEntity<Object> getCouponByType(long compId, CouponType couponType) {
		Company company = companyRepo.findCompanyById(compId);
		if(company != null){
			
		}
		return null;
	}

	public RequestEntity<Object> getCouponByPrice(long compId, double price) {
		Company company = companyRepo.findCompanyById(compId);
		if(company != null){
			
		}
		return null;
	}

	public RequestEntity<Object> getCouponByDate(long compId, LocalDateTime localdatetime) {
		Company company = companyRepo.findCompanyById(compId);
		if(company != null){
			
		}
		return null;
	}
}
