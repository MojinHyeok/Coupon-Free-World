package com.ssafy.backend.comment.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.comment.model.CommentModel;

@Repository
public interface CommentMapper {
	// 게시글에 코멘트 등록
	int boardComment(CommentModel model) throws Exception;
	
	// 피드에 코멘트 등록
	int feedComment(CommentModel model) throws Exception;
	
	// 게시글 클릭 시 코멘트 가져오기
	List<CommentModel> getBoardComment(int boardID) throws Exception;
	
	// 피드 클릭 시 코멘트 가져오기
	List<CommentModel> getFeedComment(int feedID) throws Exception;
	
	// 코멘트 추천
	int recommendUp(int commentID) throws Exception;
	
	// 코멘트 비추천
	int recommendDown(int commentID) throws Exception;
}
