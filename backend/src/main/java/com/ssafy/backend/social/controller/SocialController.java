package com.ssafy.backend.social.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.social.service.SocialService;
import com.ssafy.backend.user.model.UserModel;

@RestController
@CrossOrigin(origins= {"*"},maxAge=6000)
@RequestMapping("/social")
public class SocialController {
	
	@Autowired
	private SocialService service;
	
	//팔로우명단 가져오기
	@PostMapping("/follower")
	public ResponseEntity<?> findFollower(@RequestBody UserModel model){
		List<String> list=service.findFollower(model.getUserID());
		if(list.isEmpty())return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	//팔로잉명단 가져오기
	@PostMapping("/following")
	public ResponseEntity<?> findFollowing(@RequestBody UserModel model){
		List<String> list=service.findFollowing(model.getUserID());
		if(list.isEmpty())return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
}
