package com.project.CouponSystem.beans;

import java.util.Hashtable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	private long id;
	private String customerName;
	private String password;
	private Map<Long,Coupon> couponsCollection = new Hashtable<>();
	
	

}