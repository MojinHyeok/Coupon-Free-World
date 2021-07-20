package com.ssafy.backend.board.mapper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ssafy.backend.board.model.BoardModel;

public interface BoardMapper {
	// 글쓰기
    int writeBoard(BoardModel model) throws SQLException;
    // 글 목록 불러오기
    List<BoardModel> showList() throws SQLException;
    // 글 삭제
    int deleteBoard(int boardNum) throws SQLException;
    // 글 수정
    int updateBoard(BoardModel model) throws SQLException;
    // 전체 게시글 수
    int getTotalCount() throws SQLException;
    // 게시글 하나 검색
    BoardModel selectBoard(int boardNum) throws SQLException;
    
    List<BoardModel> selectBoardLimitOffset(@Param("limit") int limit, @Param("offset") int offset) throws SQLException;
    // 게시글 검색
    List<BoardModel> searchBoard(@Param("searchOption") String SearchOption,
            @Param("value") String value,
            @Param("limit") int limit,
            @Param("offset") int offset) throws SQLException;
}
