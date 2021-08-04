package com.ssafy.backend.feed.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.backend.amazonS3.S3Uploader;
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
	
	@Autowired
	S3Uploader s3UPloader;
	
	@ApiOperation(value = "피드 작성", notes = "피드를 작성합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "피드 작성 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/write")
	public ResponseEntity<?> writeFeed(
//			@RequestBody FeedModel model,
			@RequestParam("userID")String userID,
			@RequestParam("content")String content,
			@RequestParam("files")List<MultipartFile> multipartFiles
			) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date time = new Date();
		FeedModel model=new FeedModel();
		System.out.println(userID);
		model.setUserID(userID);
		model.setContent(content);
//		사진 작업
		int size=multipartFiles.size();
		switch (size) {
		case 1:
			model.setPhotoPath1(s3UPloader.upload(multipartFiles.get(0), "feed"));			
			break;
		case 2:
			model.setPhotoPath1(s3UPloader.upload(multipartFiles.get(0), "feed"));
			model.setPhotoPath2(s3UPloader.upload(multipartFiles.get(1), "feed"));
			break;
		case 3:
			model.setPhotoPath1(s3UPloader.upload(multipartFiles.get(0), "feed"));
			model.setPhotoPath2(s3UPloader.upload(multipartFiles.get(1), "feed"));
			model.setPhotoPath3(s3UPloader.upload(multipartFiles.get(2), "feed"));
			break;
		case 4:
			model.setPhotoPath1(s3UPloader.upload(multipartFiles.get(0), "feed"));
			model.setPhotoPath2(s3UPloader.upload(multipartFiles.get(1), "feed"));
			model.setPhotoPath3(s3UPloader.upload(multipartFiles.get(2), "feed"));
			model.setPhotoPath4(s3UPloader.upload(multipartFiles.get(3), "feed"));
			break;
		}
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
	
	@ApiOperation(value = "나의 피드 불러오기", notes = "해당 유저의 피드를 불러옵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "피드 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/getFeedList")
	public ResponseEntity<?> getFeedList(@RequestBody String userID) throws Exception {
		List<FeedModel> list = service.getFeedList(userID);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "피드 좋아요", notes = "피드에 좋아요를 눌러 좋아요 수를 증가시킵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "좋아요 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/incLikeCnt")
	public ResponseEntity<?> incLikeCnt(@RequestParam("feedID") int feedID, 
										@RequestParam("userID") String userID) throws Exception {
		
		int res = service.insertLikeFeed(feedID, userID);
		
		if(res >= 1) {
			service.incLikeCnt(feedID);
			return new ResponseEntity<Void>(HttpStatus.OK);	
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "피드 좋아요 취소", notes = "피드에 좋아요를 취소합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "피드 좋아요 취소 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/decLikeCnt")
	public ResponseEntity<?> decLikeCnt(@RequestParam("feedID") int feedID, 
										@RequestParam("userID") String userID) throws Exception {
		service.decLikeCnt(feedID);
		service.deleteLikeFeed(feedID, userID);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 내 타임라인의 피드(내 피드 + 내가 팔로우하는 사람의 피드) 불러오기
	@ApiOperation(value = "내 타임라인 보기", notes = "나의 타임라인을 봅니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "내 타임라인 보기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@PostMapping("/getMyTimeline")
	public ResponseEntity<?> getMyTimeline(@RequestBody String userID) throws Exception {
		List<FeedModel> list = service.getMyTimeline(userID);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
