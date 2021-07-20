package com.ssafy.backend.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.user.mapper.UserMapper;
import com.ssafy.backend.user.model.UserModel;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper mapper; 
	
	
	@Override
	//회원가입 기능
	public int userRegister(UserModel model) {
		return mapper.userRegister(model);
	}

}
