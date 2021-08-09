package com.ssafy.backend.coupon.service;

import org.springframework.stereotype.Service;

import com.ssafy.backend.coupon.scraper.AFKArenaScraper;

@Service
public class CouponServiceImpl implements CouponService {

	@Override
	public void getAFKArenaCoupon() throws Exception {
		AFKArenaScraper.getCoupon();
	}
}
