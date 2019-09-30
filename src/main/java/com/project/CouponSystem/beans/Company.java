package com.project.CouponSystem.beans;

import java.util.Hashtable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	private String compName;
	private String password;
	private String email;
	private Map<Long, Coupon> couponsCollection = new Hashtable<>();
	
	public long getId() {
		return id;
	}
	@Column(unique = true,nullable=false)
	public String getCompName() {
		return compName;
	}
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}
	@Column(nullable= false) 
	public String getEmail() {
		return email;
	}
	@ManyToMany
	public Map<Long, Coupon> getCouponsCollection() {
		return couponsCollection;
	}
	
	
	
}
