package com.project.CouponSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.CouponSystem.beans.Company;

public interface CompanyRepo extends JpaRepository<Long, Company> {
	
	

}
