package com.ssafy.backend.coupon.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.coupon.mapper.CouponMapper;
import com.ssafy.backend.coupon.model.GameIDModel;

@Service
public class CouponServiceImpl implements CouponService {
	
	final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36";
	
	@Autowired
	private CouponMapper mapper;
	
	@Override
	public void getAFKArenaCoupon() throws Exception {
		final String URL = "https://www.afkarena.net/redemption-codes";
		
		Document doc = Jsoup.connect(URL).get();
		
		Elements couponList = doc.select("input");
		
		for(Element c : couponList) {
			String coupon = c.val();
			System.out.println(coupon);
			if(coupon.equals("")) {
				break;
			}
		}
	}

	@Override
	public int updateAFKArenaUID(GameIDModel model) throws Exception {
		return mapper.updateAFKArenaUID(model);
	}

	@Override
	public void registAFKArenaCoupon(String userID, String verifyCode) throws Exception {
		final String URL = "https://cdkey.lilith.com/api/verify-afk-code";
		
		String AFKArenaUID = mapper.selectAFKArenaUID(userID);
		
		System.out.println(AFKArenaUID);
		
//		Document doc = Jsoup.connect(URL).get();
	}

	
}
