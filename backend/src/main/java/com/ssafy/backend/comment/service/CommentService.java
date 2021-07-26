package com.ssafy.backend.comment.service;

import java.util.List;

import com.ssafy.backend.comment.model.CommentModel;

public interface CommentService {
	// 게시판에 코멘트 달기
	public CommentModel boardComment(CommentModel model) throws Exception;
	
	// 상대방 피드에 코멘트 달기
	public CommentModel feedComment(CommentModel model) throws Exception;
	
	// 게시글 누르면 코멘트 불러오기
	public List<CommentModel> getBoardComment(int boardID) throws Exception;
	
	// 피드 누르면 코멘트 불러오기
	public List<CommentModel> getFeedComment(int feedID) throws Exception;
	
}
