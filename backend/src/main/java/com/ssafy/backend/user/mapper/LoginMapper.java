package com.ssafy.backend.user.mapper;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.user.model.UserModel;

@Repository
public interface LoginMapper {
    // 로그인
    UserModel login(UserModel model) throws Exception;
    // 사용자 정보
    UserModel userInfo(String id) throws Exception;
}
