package com.ssafy.backend.comment.service;

import java.util.List;

import com.ssafy.backend.comment.model.CommentModel;

public interface CommentService {
	// 게시판에 코멘트 달기
	public int boardComment(CommentModel model) throws Exception;
	
	// 상대방 피드에 코멘트 달기
	public int feedComment(CommentModel model) throws Exception;
	
	// 게시글 누르면 코멘트 불러오기
	public List<CommentModel> getBoardComment(int boardID) throws Exception;
	
	// 피드 누르면 코멘트 불러오기
	public List<CommentModel> getFeedComment(int feedID) throws Exception;
	
	// 코멘트 추천
	int recommendUp(int commentID) throws Exception;
	
	// 코멘트 비추천
	int recommendDown(int commentID) throws Exception;
	
	// 코멘트 삭제
	int deleteComment(int commentID) throws Exception;
}
