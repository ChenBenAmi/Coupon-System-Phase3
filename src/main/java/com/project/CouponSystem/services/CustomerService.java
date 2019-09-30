package com.project.CouponSystem.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.CouponSystem.beans.Coupon;
import com.project.CouponSystem.beans.CouponType;
import com.project.CouponSystem.beans.Customer;
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
		Customer customer=customerRepo.findCustomerById(customerId);
		if (customer!=null) {
			Coupon coupon =couponRepo.findCouponById(couponId);
			if (coupon!=null) {
				if (coupon.getAmount()>0) {
				coupon.setAmount(coupon.getAmount()-1);	
				}
			}
		}
		return null;
	}
	
	public ResponseEntity<Object> getAllHistory(long customerId) {
		Customer customer=customerRepo.findCustomerById(customerId);
		if (customer!=null) {
			
		}
		return null;
	}

	public ResponseEntity<Object> getHistoryByType(long customerId,CouponType couponType) {
		Customer customer=customerRepo.findCustomerById(customerId);
		if (customer!=null) {
			
		}
		return null;
	}

	public ResponseEntity<Object> getHistoryByType(long customerId,double price) {
		Customer customer=customerRepo.findCustomerById(customerId);
		if (customer!=null) {
			
		}
		return null;
	}


}
