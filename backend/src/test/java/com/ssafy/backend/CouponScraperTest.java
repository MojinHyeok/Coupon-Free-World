package com.ssafy.backend;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.backend.coupon.scraper.AFKArenaScraper;
import com.ssafy.backend.coupon.service.CouponService;
import com.ssafy.backend.coupon.service.CouponServiceImpl;

@SpringBootTest
public class CouponScraperTest {
	
	@Autowired
	CouponService service = new CouponServiceImpl();
	
	@Test
	public void scraperTest() throws Exception {
		System.out.println("test~!");
		service.getAFKArenaCoupon();
	}
}
