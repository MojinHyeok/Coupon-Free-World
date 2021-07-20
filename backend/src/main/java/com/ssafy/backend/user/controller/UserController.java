package com.ssafy.backend.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
				msg="fail";
			}
			status=HttpStatus.ACCEPTED;
		}catch(Exception e) {
			msg="error";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(msg,status);
	}
	
	//회원정보
	@PostMapping("/info")
	public ResponseEntity<Map<String, Object>> userInfo(@RequestBody UserModel model){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		
		String userId=model.getUserID();
		try {
			UserModel user=service.getUserInfo(userId);
			resultMap.put("userInfo", user);
			resultMap.put("meesage", "success");
			status=HttpStatus.ACCEPTED;			
		}catch(Exception e){
			resultMap.put("message", e.getMessage());
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap,status);
	}
	
	
	//회원정보 수정
	@PostMapping("/modify")
	public ResponseEntity<String> userModify(@RequestBody UserModel model){
		String msg="";
		HttpStatus status;
		try {
			int result=service.userModify(model);
			if(result>=1)msg="success";
			else msg="fail";
			status=HttpStatus.ACCEPTED;
		}catch(Exception e){
			msg="error";
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(msg,status);
	}
	//회원정보삭제하기
	@PostMapping("/delete")
	public ResponseEntity<String> userDelte(@RequestBody UserModel model){
		String msg="";
		HttpStatus status;
		String userID=model.getUserID();
		try {
			int result=service.userDelete(userID);
			if(result>=1)msg="success";
			else msg="fail";
			status=HttpStatus.ACCEPTED;
		}catch(Exception e){
			msg="error";
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(msg,status);
	}
}
