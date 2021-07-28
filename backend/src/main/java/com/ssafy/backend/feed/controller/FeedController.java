package com.ssafy.backend.feed.controller;

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

import com.ssafy.backend.feed.model.FeedModel;
import com.ssafy.backend.feed.service.FeedService;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/feed")
public class FeedController {
	@Autowired
	FeedService service;
	
	@PostMapping("/write")
	public ResponseEntity<?> writeFeed(@RequestBody FeedModel model) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date time = new Date();
		
		model.setDate(format.format(time));
		model.setLikeCnt(0);
		
		int res = service.writeFeed(model);
		
		if(res >= 1) {
			return new ResponseEntity<>(model, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{feedID}")
	public ResponseEntity<?> deleteFeed(@PathVariable("feedID") int feedID) throws Exception {
		int res = service.deleteFeed(feedID);
		
		if(res >= 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/incLikeCnt")
	public ResponseEntity<?> incLikeCnt(int feedID) throws Exception {
		service.incLikeCnt(feedID);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/decLikeCnt")
	public ResponseEntity<?> decLikeCnt(int feedID) throws Exception {
		service.decLikeCnt(feedID);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
