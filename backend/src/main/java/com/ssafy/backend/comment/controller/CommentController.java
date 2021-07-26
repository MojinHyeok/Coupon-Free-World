package com.ssafy.backend.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.comment.service.CommentService;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService service;
	
	// 게시판에 코멘트 등록 
	
	// 피드에 코멘트 등록
	
	// 게시판 클릭 시 코멘트 불러오기
	
	// 피드 클릭 시 코멘트 불러오기
}
