package com.ssafy.backend.board.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	// limit = 출력할 행의 수
	// offset = 몇번째 row부터 출력할 것인지(0부터 시작)
	@GetMapping("/list/{limit}/{offset}")
	public ResponseEntity<?> boardList(@PathVariable("limit") int limit, @PathVariable("offset") int offset) throws Exception {
		List<BoardModel> list = service.selectBoardLimitOffset(limit, offset);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 글 삭제
	// 게시글 번호(테이블의 PK)로 접근함, 값 보낼 때 boardID를 보내야 함
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
	@PutMapping("/update")
	public ResponseEntity<?> updateBoard(@RequestBody BoardModel model) throws Exception {
		int res = service.updateBoard(model);
		
		if(res >= 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 게시글 검색
	// searchOption = 검색옵션(내용, 제목, 작성자)
	// searchOption 값 보낼때 내용 = content, 작성자 = writer, 제목 = title로 보내기
	// value = 포함될 값
	// limit = 출력할 행의 수
	// offset = 몇번째 row부터 출력할 것인지(0부터 시작)
	@GetMapping("/search/{searchOption}/{value}/{limit}/{offset}")
	public ResponseEntity<?> searchBoard(@PathVariable("searchOption") String searchOption,
										 @PathVariable("value") String value,
										 @PathVariable("limit") int limit,
										 @PathVariable("offset") int offset) throws Exception {
		
		List<BoardModel> list = service.searchBoard(searchOption, value, limit, offset);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 게시글 내용 보기(클릭 시 조회수 올라가게)
	@GetMapping("/select/{boardID}")
	public ResponseEntity<?> selectBoard(@PathVariable("boardID") int boardID) throws Exception {
		BoardModel model = service.selectBoard(boardID);
		
        if (model == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        // 여기서 조회수 올라가게 해야함
        return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	// 전체 게시글 수 가져오기
	@GetMapping("/totalCnt")
	public ResponseEntity<?> totalCount() throws Exception {
		int cnt = service.totalCount();
		
		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}
}
