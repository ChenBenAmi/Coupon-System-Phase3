package com.project.CouponSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.CouponSystem.beans.Coupon;
import com.project.CouponSystem.beans.Customer;

public interface CouponRepo extends JpaRepository<Long, Coupon> {

	public Coupon findCouponById(long id);

	public Coupon findCouponByTitle(String title);

}
