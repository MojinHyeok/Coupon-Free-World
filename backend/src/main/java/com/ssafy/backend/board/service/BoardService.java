package com.ssafy.backend.board.service;

import java.util.List;
import org.springframework.data.repository.query.Param;
import com.ssafy.backend.board.model.BoardModel;

public interface BoardService {
	// 글쓰기
    int writeBoard(BoardModel BoardModel) throws Exception;
    // 글목록 가져오기
    List<BoardModel> showList() throws Exception;
    // 글삭제
    int deleteBoard(int boardID) throws Exception;
    // 
    BoardModel selectBoard(int boardID) throws Exception;
    // 게시글 검색
    List<BoardModel> searchBoard(String searchOption, String value, int limit, int offset) throws Exception;
    // 게시글 수정
    int updateBoard(BoardModel BoardModel) throws Exception;
    // 전체 게시글 수
    public int getTotalCount() throws Exception;
    // 
    List<BoardModel> selectBoardLimitOffset(@Param("limit") int limit, @Param("offset") int offset) throws Exception;

}
