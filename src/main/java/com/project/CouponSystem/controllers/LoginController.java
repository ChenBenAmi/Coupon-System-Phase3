package com.project.CouponSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.CouponSystem.beans.ClientType;
import com.project.CouponSystem.services.AdminService;
import com.project.CouponSystem.services.CompanyService;
import com.project.CouponSystem.services.CustomerService;
@CrossOrigin(origins ="http://localhost:4200")
@RestController
public class LoginController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CompanyService companyService;
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String userName, @RequestParam String password,
			@RequestParam ClientType clientType) {
		System.out.println("hey");
		switch (clientType) {
		case ADMIN:
			return adminService.login(userName, password, clientType);

		case COMPANY:
			return companyService.login(userName, password, clientType);

		case CUSTOMER:
			return customerService.login(userName, password, clientType);
		}
		return ResponseEntity.badRequest().body("Something went wrong");
	}
	
	@PostMapping("logout")
	public ResponseEntity<?> logout(@RequestParam String token,@RequestParam ClientType clientType) {
		switch (clientType) {
		case ADMIN:
			return adminService.logout(token);

		case COMPANY:
			return companyService.logout(token);

		case CUSTOMER:
			return customerService.logout(token);
		}
		return ResponseEntity.badRequest().body("Something went wrong");
	}

}
