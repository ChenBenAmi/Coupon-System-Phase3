package com.project.CouponSystem.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.CouponSystem.beans.Coupon;
import com.project.CouponSystem.beans.CouponType;
import com.project.CouponSystem.repo.CompanyRepo;
import com.project.CouponSystem.repo.CouponRepo;

@Service
@Transactional
public class CompanyService {
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CouponRepo couponRepo;

	public RequestEntity<Object> createCoupon(long compId, @RequestBody Coupon coupon) {
		return null;
	}

	public RequestEntity<Object> deleteCoupon(long compId, long couponId) {
		return null;
	}

	public RequestEntity<Object> updateCoupon(long compId, @RequestBody Coupon coupon) {
		return null;
	}

	public RequestEntity<Object> getCompany(long compId) {
		return null;
	}

	public RequestEntity<Object> getAllCoupon(long compId) {
		return null;
	}

	public RequestEntity<Object> getCouponByType(long compId, CouponType couponType) {
		return null;
	}

	public RequestEntity<Object> getCouponByPrice(long compId, double price) {
		return null;
	}

	public RequestEntity<Object> getCouponByDate(long compId, LocalDateTime localdatetime) {
		return null;
	}
}
