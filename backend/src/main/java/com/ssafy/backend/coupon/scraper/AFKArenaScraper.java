package com.ssafy.backend.coupon.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.backend.coupon.mapper.CouponMapper;

public class AFKArenaScraper {
	final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36";

	@Autowired
	CouponMapper mapper;
	
	public static void getCoupon() throws Exception {
		final String URL = "https://www.afkarena.net/redemption-codes";
		
		Document doc = Jsoup.connect(URL).get();
		
		Elements couponList = doc.select("input");
		
		for(Element c : couponList) {
			String coupon = c.val();
			System.out.println(coupon);
		}
	}
	
	public static void registCoupon() throws Exception {
		final String URL = "https://cdkey.lilith.com/afk-global";
		
		Document doc = Jsoup.connect(URL).get();
	}
	
	
}
