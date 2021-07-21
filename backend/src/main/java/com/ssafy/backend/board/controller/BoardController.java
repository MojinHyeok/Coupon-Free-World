package com.ssafy.backend.board.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@PostMapping("/write")
	public ResponseEntity<?> writeBoard(@RequestBody BoardModel model) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date time = new Date();
        
        model.setRegDate(format.format(time));
        
        int res = service.writeBoard(model);
        
        if(res >= 1) {
        	return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
	}
	
	
}
