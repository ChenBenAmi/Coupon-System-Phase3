package com.project.CouponSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.CouponSystem.beans.CouponType;
import com.project.CouponSystem.services.CustomerService;

@RestController("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@PutMapping("/purchaseCoupon")
	public ResponseEntity<Object> purchaseCoupon(@RequestParam long customerId, @RequestParam long couponId) {

		return null;
	}

	@GetMapping("/hitory")
	public ResponseEntity<Object> getAllHistory(@RequestParam long customerId) {
		return null;
	}

	@GetMapping("/hitory/{couponType}")
	public ResponseEntity<Object> getHistoryByType(@RequestParam long customerId, @PathVariable CouponType couponType) {
		return null;
	}

	@GetMapping("/hitory/{price}")
	public ResponseEntity<Object> getHistoryByType(@RequestParam long customerId, @PathVariable double price) {
		return null;
	}

}
