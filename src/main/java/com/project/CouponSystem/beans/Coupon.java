package com.project.CouponSystem.beans;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Coupon {

	
	private long id;
	private String title;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int amount;
	private CouponType couponType;
	private String message;
	private double price;
	private String image;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	@Column(unique = true)
	public String getTitle() {
		return title;
	}
	@Column
	public LocalDateTime getStartDate() {
		return startDate;
	}
	@Column
	public LocalDateTime getEndDate() {
		return endDate;
	}
	@Column
	public int getAmount() {
		return amount;
	}
	@Enumerated(EnumType.STRING)
	public CouponType getCouponType() {
		return couponType;
	}
	@Column
	public String getMessage() {
		return message;
	}
	@Column
	public double getPrice() {
		return price;
	}
	@Column
	public String getImage() {
		return image;
	}
	
	
}
