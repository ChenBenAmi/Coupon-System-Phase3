package com.project.CouponSystem.services;

import org.springframework.http.ResponseEntity;

import com.project.CouponSystem.beans.ClientType;

public interface CouponClient {
	
	ResponseEntity<?> login(String name, String password, ClientType clientType);
	
	ResponseEntity<?> logout(String token);
}