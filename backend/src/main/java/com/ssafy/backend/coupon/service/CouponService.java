package com.ssafy.backend.coupon.service;

import org.springframework.data.repository.query.Param;

import com.ssafy.backend.coupon.model.GameIDModel;

public interface CouponService {
	void getAFKArenaCoupon() throws Exception;
	
	int updateAFKArenaUID(GameIDModel model) throws Exception;
	
	void registAFKArenaCoupon(@Param("userID") String userID, @Param("verifyCode") String verifyCode) throws Exception;
}
