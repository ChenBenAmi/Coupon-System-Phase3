package com.project.CouponSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.CouponSystem.beans.Company;
import com.project.CouponSystem.beans.Customer;
import com.project.CouponSystem.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/getAllCompanies")
	public ResponseEntity<Object> getAllCompanies(@PathVariable String token) {
		return adminService.getAllCompanies(token);
	}
	
	@GetMapping("/getCompany")
	public ResponseEntity<Object> getCompany(@PathVariable String token,@RequestParam long companyId) {
		return adminService.getCompany(token, companyId);
	}
	
	@PostMapping("/addCompany")
	public ResponseEntity<Object> addCompany(@PathVariable String token,@RequestBody Company company) {
		return adminService.addCompany(token, company);
	}
	
	@DeleteMapping("/deleteCompany")
	public ResponseEntity<Object> deleteCompany(@PathVariable String token,@RequestParam long companyId) {
		return adminService.deleteCompany(token, companyId);
	}
	
	@PutMapping("/updateCompany")
	public ResponseEntity<Object> updateCompany(@PathVariable String token,@RequestBody Company company) {
		return adminService.updateCompany(token, company);
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<Object> getAllCustomers(@PathVariable String token) {
		return adminService.getAllCustomers(token);
	}
	
	@GetMapping("/getCustomer")
	public ResponseEntity<Object> getCustomer(@PathVariable String token,@RequestParam long customerId) {
		return adminService.getCustomer(token, customerId);
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Object> addCustomer(@PathVariable String token,@RequestBody Customer customer) {
		return adminService.addCustomer(token, customer);
	}
	
	@DeleteMapping("/deleteCustomer")
	public ResponseEntity<Object> deleteCustomer(@PathVariable String token,@RequestParam long customerId) {
		return adminService.deleteCustomer(token, customerId);
	}
	
	@PutMapping("/updateCustomer")
	public ResponseEntity<Object> updateCustomer(@PathVariable String token,@RequestBody Customer customer) {
		return adminService.updateCustomer(token, customer);
	}
	
	
	

}
