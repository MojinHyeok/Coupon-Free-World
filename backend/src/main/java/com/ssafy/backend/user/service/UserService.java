package com.ssafy.backend.user.service;

import java.util.List;

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
		//회원 리스트 검색
		List<String> searchUserList(String userid);
		//Email로 검색 중복 검사하기위해
		UserModel  getUserInfoByEmail(String email);

}
