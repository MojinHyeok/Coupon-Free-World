package com.ssafy.backend.coupon.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ssafy.backend.coupon.model.GameIDModel;
import com.ssafy.backend.coupon.service.CouponServiceImpl.cookiecoupon;

public interface CouponService {
	List<String> getAFKArenaCoupon() throws Exception;
	
	List<cookiecoupon> getCookkieCoupon() throws Exception;
	
	int updateAFKArenaUID(GameIDModel model) throws Exception;
	
	void registAFKArenaCoupon(@Param("userID") String userID, @Param("verifyCode") String verifyCode) throws Exception;
	
	void registCookierunCoupon(@Param("userID") String userID) throws Exception;	
	
	int updateCookierunUID(GameIDModel model) throws Exception;
	
	String selectCookierunUID(String userID) throws Exception;
	
	
	
	GameIDModel getGameUID(String userID) throws Exception;
}
