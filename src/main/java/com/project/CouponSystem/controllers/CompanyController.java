package com.project.CouponSystem.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.CouponSystem.beans.Coupon;
import com.project.CouponSystem.beans.CouponType;
import com.project.CouponSystem.services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@PostMapping("/createCoupon")
	public RequestEntity<Object> createCoupon(@RequestParam long compId, @RequestBody Coupon coupon) {
		return null;
	}

	@DeleteMapping("/deleteCoupon")
	public RequestEntity<Object> deleteCoupon(@RequestParam long compId, @RequestParam long couponId) {
		return null;
	}

	@PutMapping("/updateCoupon")
	public RequestEntity<Object> updateCoupon(@RequestParam long compId, @RequestBody Coupon coupon) {
		return null;
	}

	@GetMapping("/getCompany")
	public RequestEntity<Object> getCompany(@RequestParam long compId) {
		return null;
	}

	@GetMapping("/getAllCoupon")
	public RequestEntity<Object> getAllCoupon(@RequestParam long compId) {
		return null;
	}

	@GetMapping("/getCoupon/{couponType}")
	public RequestEntity<Object> getCouponByType(@RequestParam long compId,@PathVariable CouponType couponType) {
		return null;
	}

	@GetMapping("/getCoupon/{price}")
	public RequestEntity<Object> getCouponByPrice(@RequestParam long compId,@PathVariable double price) {
		return null;
	}

	@GetMapping("/getCoupon/{localdatetime}")
	public RequestEntity<Object> getCouponByDate(@RequestParam long compId,@PathVariable LocalDateTime localdatetime) {
		return null;
	}
}
