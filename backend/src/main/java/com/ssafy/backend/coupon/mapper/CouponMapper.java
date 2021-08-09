package com.ssafy.backend.coupon.mapper;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.coupon.model.CouponModel;

@Repository
public interface CouponMapper {
	int insertAFKCoupon(CouponModel model) throws Exception;
}
