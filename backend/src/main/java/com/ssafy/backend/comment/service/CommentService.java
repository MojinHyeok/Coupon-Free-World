package com.ssafy.backend.comment.service;

import com.ssafy.backend.comment.model.CommentModel;

public interface CommentService {
	// 게시판에 코멘트 달기
	public CommentModel boardComment(CommentModel model) throws Exception;
	
	// 상대방 피드에 코멘트 달기
	public CommentModel feedComment(CommentModel model) throws Exception;
	
}
