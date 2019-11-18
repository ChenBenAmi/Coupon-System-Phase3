package com.project.CouponSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/getAllCoupons")
	public ResponseEntity<Object> getAllCoupons(@RequestParam String token) {
		return customerService.getAllCoupons(token);
	}

	@GetMapping("/purchaseCoupon")
	public ResponseEntity<Object> purchaseCoupon(@RequestParam String token, @RequestParam long couponId) {
		return customerService.purchaseCoupon(token, couponId);
	}

	@GetMapping("/getAllHistory")
	public ResponseEntity<Object> getAllHistory(@RequestParam String token) {
		return customerService.getAllHistory(token);
	}

	@GetMapping("/getHistoryByType")
	public ResponseEntity<Object> getHistoryByType(@RequestParam String token, @RequestParam CouponType couponType) {
		return customerService.getHistoryByType(token, couponType);
	}

	@GetMapping("/getHistoryByPrice")
	public ResponseEntity<Object> getHistoryByPrice(@RequestParam String token, @RequestParam double price) {
		return customerService.getHistoryByPrice(token, price);
	}
	
	@GetMapping("/viewIncome")
	public ResponseEntity<?> viewIncome(@RequestParam String token) {
		return customerService.viewIncome(token);
	}

}
