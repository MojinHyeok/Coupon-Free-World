package com.ssafy.backend.comment.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.comment.model.CommentModel;

@Repository
public interface CommentMapper {
	CommentModel boardComment(CommentModel model) throws Exception;
	CommentModel feedComment(CommentModel model) throws Exception;
	List<CommentModel> getBoardComment(int boardID) throws Exception;
	List<CommentModel> getFeedComment(int feedID) throws Exception;
}
