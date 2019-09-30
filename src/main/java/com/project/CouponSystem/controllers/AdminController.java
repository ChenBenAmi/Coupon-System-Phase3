package com.project.CouponSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.CouponSystem.beans.Company;
import com.project.CouponSystem.beans.Customer;
import com.project.CouponSystem.services.AdminService;

@RestController("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("getAllCompanies")
	public ResponseEntity<Object> getAllCompanies(@RequestParam long adminId) {
		return null;
	}
	
	@GetMapping("getCompany")
	public ResponseEntity<Object> getCompany(@RequestParam long adminId,@RequestParam long companyId) {
		return null;
	}
	
	@PostMapping("addCompany")
	public ResponseEntity<Object> addCompany(@RequestParam long adminId,@RequestBody Company company) {
		return null;
	}
	
	@DeleteMapping("deleteCompany")
	public ResponseEntity<Object> deleteCompany(@RequestParam long adminId,@RequestParam long companyId) {
		return null;
	}
	
	@PutMapping("updateCompany")
	public ResponseEntity<Object> updateCompany(@RequestParam long adminId,@RequestBody Company company) {
		return null;
	}
	
	@GetMapping("getAllCustomers")
	public ResponseEntity<Object> getAllCustomers(@RequestParam long adminId) {
		return null;
	}
	
	@GetMapping("getCustomer")
	public ResponseEntity<Object> getCustomer(@RequestParam long adminId,@RequestParam long customerId) {
		return null;
	}
	
	@PostMapping("addCustomer")
	public ResponseEntity<Object> addCustomer(@RequestParam long adminId,@RequestBody Customer customer) {
		return null;
	}
	
	@DeleteMapping("deleteCustomer")
	public ResponseEntity<Object> deleteCustomer(@RequestParam long adminId,@RequestParam long customerId) {
		return null;
	}
	
	@PutMapping("updateCustomer")
	public ResponseEntity<Object> updateCustomer(@RequestParam long adminId,@RequestBody Customer customer) {
		return null;
	}
	
	
	

}
