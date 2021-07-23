package com.ssafy.backend.board.mapper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.backend.board.model.BoardModel;

@Repository
public interface BoardMapper {
	// 글쓰기
    int writeBoard(BoardModel model) throws SQLException;
    // 글 삭제
    int deleteBoard(int boardNum) throws SQLException;
    // 글 수정
    int updateBoard(BoardModel model) throws SQLException;
    // 전체 게시글 수
    int totalCount() throws SQLException;
    // 게시글 내용 보기
    BoardModel selectBoard(int boardNum) throws SQLException;
    
    List<BoardModel> boardList(@Param("limit") int limit, @Param("offset") int offset) throws SQLException;
    
    // 게시글 검색
    List<BoardModel> searchBoard(@Param("searchOption") String searchOption,
            @Param("value") String value,
            @Param("limit") int limit,
            @Param("offset") int offset) throws SQLException;
}
