package com.project.CouponSystem.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.project.CouponSystem.beans.CouponType;
import com.project.CouponSystem.repo.CouponRepo;
import com.project.CouponSystem.repo.CustomerRepo;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CouponRepo couponRepo;
	
	public ResponseEntity<Object> purchaseCoupon(long customerId,long couponId) {
		
		return null;
	}
	
	public ResponseEntity<Object> getAllHistory(long customerId) {
		return null;
	}

	public ResponseEntity<Object> getHistoryByType(long customerId,CouponType couponType) {
		return null;
	}

	public ResponseEntity<Object> getHistoryByType(long customerId,double price) {
		return null;
	}


}
