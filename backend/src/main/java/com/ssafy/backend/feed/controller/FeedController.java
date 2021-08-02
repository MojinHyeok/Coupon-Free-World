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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/feed")
public class FeedController {
	@Autowired
	FeedService service;
	
	@ApiOperation(value = "피드 작성", notes = "피드를 작성합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "피드 작성 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
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

	@ApiOperation(value = "피드 삭제", notes = "피드를 삭제합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "피드 삭제 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@DeleteMapping("/delete/{userID}/{feedID}")
	public ResponseEntity<?> deleteFeed(@PathVariable("feedID") int feedID, 
										@PathVariable("userID") String userID) throws Exception {
		
		int res = service.deleteFeed(feedID, userID);
		
		if(res >= 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "피드 좋아요", notes = "피드에 좋아요를 눌러 좋아요 수를 증가시킵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "좋아요 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/incLikeCnt")
	public ResponseEntity<?> incLikeCnt(int feedID) throws Exception {
		service.incLikeCnt(feedID);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "피드 싫어요", notes = "피드에 싫어요를 눌러 좋아요 수를 감소시킵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "싫어요 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/decLikeCnt")
	public ResponseEntity<?> decLikeCnt(int feedID) throws Exception {
		service.decLikeCnt(feedID);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
