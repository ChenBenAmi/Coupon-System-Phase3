package com.project.CouponSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.CouponSystem.beans.Coupon;

public interface CouponRepo extends JpaRepository<Long, Coupon> {

}
