package com.ssafy.backend.user.service;

import com.ssafy.backend.user.model.UserModel;

public interface UserService {
		//회원가입
		int userRegister(UserModel model);
		//회원정보수정
		int userModify(UserModel model);
		//회원정보삭제
		int userDelete(String userid);
		//회원정보조회
		UserModel getUserInfo(String userid);
	

}
