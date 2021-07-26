package com.ssafy.backend.comment.mapper;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.comment.model.CommentModel;

@Repository
public interface CommentMapper {
	CommentModel boardComment(CommentModel model) throws Exception;
	CommentModel feedComment(CommentModel model) throws Exception;
}
