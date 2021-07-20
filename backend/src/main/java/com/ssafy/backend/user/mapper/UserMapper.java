package com.ssafy.backend.user.mapper;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.user.model.UserModel;

@Repository
public interface UserMapper {
	//회원가입
	int userRegister(UserModel model);
	//회원정보수정
	int userModify(UserModel model);
	//회원정보삭제
	int userDelete(String userid);
	//회원정보조회
	UserModel getUserInfo(String userid);

}
