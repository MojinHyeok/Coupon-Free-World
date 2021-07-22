package com.ssafy.backend.board.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.board.model.BoardModel;
import com.ssafy.backend.board.service.BoardService;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// 글쓰기
	@PostMapping("/write")
	public ResponseEntity<?> writeBoard(@RequestBody BoardModel model) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date time = new Date();
        
        model.setRegDate(format.format(time));
        model.setRecommend(0);
        model.setViewCount(1);
        int res = service.writeBoard(model);
        
        if(res >= 1) {
        	return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
	}
	// 글 목록 불러오기
	
	// 글 삭제(게시글 번호(테이블의 PK)로 접근함)
	@DeleteMapping("/delete/{boardID}")
	public ResponseEntity<?> deleteBoard(@PathVariable("boardID") int boardID) throws Exception {
		int res = service.deleteBoard(boardID);
		
		if(res >= 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	// 글 수정
	
	// 게시글 검색(옵션-작성자, 내용, 제목)
	
	// 게시글 내용 보기(클릭 시 조회수 올라가게)
}
