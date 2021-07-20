package com.ssafy.backend.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.user.model.UserModel;
import com.ssafy.backend.user.service.UserService;

@RestController
@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	
	
	//회원가입 URI (RquestBody에 회원정보 전송)
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody UserModel model){
		String msg="";
		
		HttpStatus status;
		try {
			int result=service.userRegister(model);
			if(result>=1) {
				msg="success";
			}else {
				msg="faile";
			}
			status=HttpStatus.ACCEPTED;
		}catch(Exception e) {
		
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(msg,status);
	}
	
}
