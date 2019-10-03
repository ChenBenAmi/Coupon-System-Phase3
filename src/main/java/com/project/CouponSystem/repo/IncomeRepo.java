package com.project.CouponSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.CouponSystem.beans.Income;

public interface IncomeRepo extends JpaRepository<Income, Long> {
	

}
