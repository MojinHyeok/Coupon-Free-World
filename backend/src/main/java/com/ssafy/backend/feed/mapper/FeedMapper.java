package com.ssafy.backend.feed.mapper;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.backend.feed.model.FeedModel;

@Repository
public interface FeedMapper {
	// 피드 작성
	int writeFeed(FeedModel model) throws Exception;
	
	// 피드 삭제
	int deleteFeed(int feedID) throws Exception;
	
	// 좋아요 설정
	int incLikeCnt(@Param("feedID") int feedID, @Param("likeCount") int likeCount) throws Exception;
	
	// 좋아요 해제
	int decLikeCnt(@Param("feedID") int feedID, @Param("likeCount") int likeCount) throws Exception;
}
