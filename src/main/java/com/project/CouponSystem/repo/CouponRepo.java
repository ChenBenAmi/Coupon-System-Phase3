package com.project.CouponSystem.repo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.CouponSystem.beans.Coupon;
import com.project.CouponSystem.beans.CouponType;

public interface CouponRepo extends JpaRepository<Coupon,Long> {

	public Coupon findCouponById(long id);
	public Coupon findCouponBytitle(String title);
	public Coupon findCouponBycouponType(CouponType couponType);
	public Coupon findCouponByprice(double price);
	public Coupon findCouponByendDate(LocalDateTime endDate);
}
