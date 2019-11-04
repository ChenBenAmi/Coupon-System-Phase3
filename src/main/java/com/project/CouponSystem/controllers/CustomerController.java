package com.project.CouponSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.CouponSystem.beans.CouponType;
import com.project.CouponSystem.services.CustomerService;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@PutMapping("/purchaseCoupon")
	public ResponseEntity<Object> purchaseCoupon(@PathVariable String token, @RequestParam long couponId) {
		return customerService.purchaseCoupon(token, couponId);
		
	}

	@GetMapping("/hitory")
	public ResponseEntity<Object> getAllHistory(@PathVariable String token) {
		return customerService.getAllHistory(token);
	}

	@GetMapping("/hitory/{couponType}")
	public ResponseEntity<Object> getHistoryByType(@PathVariable String token, @PathVariable CouponType couponType) {
		return customerService.getHistoryByType(token, couponType);
	}

	@GetMapping("/hitory/{price}")
	public ResponseEntity<Object> getHistoryByPrice(@PathVariable String token, @PathVariable double price) {
		return customerService.getHistoryByPrice(token, price);
	}
	
	@GetMapping("/viewIncome")
	public ResponseEntity<?> viewIncome(@RequestParam String token) {
		return customerService.viewIncome(token);
	}

}
