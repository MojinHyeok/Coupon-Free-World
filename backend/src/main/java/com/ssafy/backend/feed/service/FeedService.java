package com.ssafy.backend.feed.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ssafy.backend.feed.model.FeedModel;

public interface FeedService {
	// 피드 작성
	int writeFeed(FeedModel model) throws Exception;
	
	// 피드 삭제
	int deleteFeed(@Param("feedID") int feedID, @Param("userID") String userID) throws Exception;
	
	// 좋아요 설정
	int incLikeCnt(@Param("feedID") int feedID) throws Exception;
	
	// 좋아요 해제
	int decLikeCnt(@Param("feedID") int feedID) throws Exception;
	
	// 해당 유저의 피드 불러오기
	List<FeedModel> getFeedList(@Param("feedID") int feedID) throws Exception;
}
