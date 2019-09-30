package com.project.CouponSystem.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.CouponSystem.beans.Company;
import com.project.CouponSystem.beans.Customer;
import com.project.CouponSystem.repo.CompanyRepo;
import com.project.CouponSystem.repo.CouponRepo;
import com.project.CouponSystem.repo.CustomerRepo;
@Service
@Transactional
public class AdminService {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CouponRepo couponRepo;
	@Autowired
	private CompanyRepo companyRepo;
	
	public ResponseEntity<Object> getAllCompanies(@RequestParam long adminId) {
		return null;
	}
	
	public ResponseEntity<Object> getCompany(@RequestParam long adminId,@RequestParam long companyId) {
		return null;
	}
	
	public ResponseEntity<Object> addCompany(@RequestParam long adminId,@RequestBody Company company) {
		return null;
	}
	
	public ResponseEntity<Object> deleteCompany(@RequestParam long adminId,@RequestParam long companyId) {
		return null;
	}
	
	public ResponseEntity<Object> updateCompany(@RequestParam long adminId,@RequestBody Company company) {
		return null;
	}
	
	public ResponseEntity<Object> getAllCustomers(@RequestParam long adminId) {
		return null;
	}
	
	public ResponseEntity<Object> getCustomer(@RequestParam long adminId,@RequestParam long customerId) {
		return null;
	}
	
	public ResponseEntity<Object> addCustomer(@RequestParam long adminId,@RequestBody Customer customer) {
		return null;
	}
	
	public ResponseEntity<Object> deleteCustomer(@RequestParam long adminId,@RequestParam long customerId) {
		return null;
	}
	
	public ResponseEntity<Object> updateCustomer(@RequestParam long adminId,@RequestBody Customer customer) {
		return null;
	}
	
}
