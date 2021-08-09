package com.ssafy.backend.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.coupon.service.CouponService;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/coupon")
public class CouponController {
	@Autowired
	private CouponService service;
	
	@GetMapping("/AFKArena")
	public ResponseEntity<?> getAFKArenaCouponList() throws Exception{
		service.getAFKArenaCoupon();		
		
		return new ResponseEntity<Void>(HttpStatus.OK); 
	}
}
