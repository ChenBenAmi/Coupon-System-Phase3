package com.project.CouponSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.CouponSystem.beans.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

	public Customer findCustomerById(long id);
	
	public Customer findCustomerByCustomerName(String customerName);
}
