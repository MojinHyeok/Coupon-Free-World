package com.ssafy.backend.comment.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.comment.model.CommentModel;
import com.ssafy.backend.comment.service.CommentService;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService service;
	
	SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd HH:mm:ss");
	
	// 게시판에 코멘트 등록 
	@PostMapping("/registBoard")
	public ResponseEntity<?> registBoardComment(@RequestBody CommentModel model) throws Exception {
		int res = service.boardComment(model);
		
		if(res >= 1) {
			return new ResponseEntity<>(model, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		/*
		 * 코멘트 등록 시 DB에 INSERT가 되며 등록한 코멘트를 바로 Front에서 불러올 수 있음
		 */
	}
	
	// 피드에 코멘트 등록
	@PostMapping("/registFeed")
	public ResponseEntity<?> registFeedComment(@RequestBody CommentModel model) throws Exception {
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	// 게시판 클릭 시 코멘트 불러오기
	@GetMapping("/getBoard/{boardID}")
	public ResponseEntity<?> getBoardComment(@PathVariable("boardID") int boardID) throws Exception {
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	// 피드 클릭 시 코멘트 불러오기
	@GetMapping("/getFeed/{feedID}")
	public ResponseEntity<?> getFeedComment(@PathVariable("feedID") int feedID) throws Exception {
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
}
