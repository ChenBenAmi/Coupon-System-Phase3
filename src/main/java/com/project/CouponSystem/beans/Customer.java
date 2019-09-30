package com.project.CouponSystem.beans;

import java.util.Hashtable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Customer {
	
	private long id;
	private String customerName;
	private String password;
	private Map<Long,Coupon> couponsCollection = new Hashtable<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	@Column(unique = true,nullable = false)
	public String getCustomerName() {
		return customerName;
	}
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}
	@ManyToMany
	public Map<Long, Coupon> getCouponsCollection() {
		return couponsCollection;
	}
	
	
	
	
	

}