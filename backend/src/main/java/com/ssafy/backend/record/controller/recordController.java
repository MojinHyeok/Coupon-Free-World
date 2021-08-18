package com.ssafy.backend.record.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.backend.record.model.RecordModel;
import com.ssafy.backend.record.service.RecordService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/record")
public class recordController {
	
	@Autowired
	RecordService service;
	
	@ApiOperation(value = "검색정보 저장", notes = "검색정보를 저장합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "검색정보 저장 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/insert/{sourceID}/{targetID}")
	public ResponseEntity<?> insertRecord(@PathVariable("sourceID") String sourceID, 
			@PathVariable("targetID") String targetID) throws Exception {
		RecordModel model=new RecordModel();
		model.setSourceID(sourceID);
		model.setTargetID(targetID);
		int res=service.registRecord(model);
		if(res >= 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "검색정보 불러오기", notes = "검색정보를 불러옵니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "검색정보 불러오기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/fetchRecord/{sourceID}")
	public ResponseEntity<?> fetchRecord(@PathVariable("sourceID") String sourceID
			) throws Exception {
		
		List<RecordModel> result=service.fetchRecord(sourceID);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "검색정보 삭제", notes = "검색정보를 삭제합니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "검색정보 삭제하기 성공"),
		@ApiResponse(code = 404, message = "페이지를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "내부 서버 오류")
	})
	@GetMapping("/deleteRecord/{sourceID}/{targetID}")
	public ResponseEntity<?> DelteRecord(@PathVariable("sourceID") String sourceID, 
			@PathVariable("targetID") String targetID) throws Exception {
		RecordModel model=new RecordModel();
		model.setSourceID(sourceID);
		model.setTargetID(targetID);
		int res=service.deleteRecord(model);
		System.out.println(res);
		if(res >= 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

	
	
	
}
