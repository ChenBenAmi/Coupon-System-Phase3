package com.project.CouponSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.CouponSystem.beans.Company;
import com.project.CouponSystem.beans.Coupon;

public interface CompanyRepo extends JpaRepository<Long, Company> {
	
	public Company findCompanyById(long id);
	public Company findCompanyBycompName(String compName);
	

}
