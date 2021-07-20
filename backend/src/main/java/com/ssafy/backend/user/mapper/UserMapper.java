package com.ssafy.backend.user.mapper;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.user.model.UserModel;

@Repository
public interface UserMapper {
	//회원가입
	int userRegister(UserModel model);

}
