package com.ssafy.backend.feed.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ssafy.backend.feed.model.FeedModel;
import com.ssafy.backend.feed.model.LikeFeedModel;

public interface FeedService {
	// 피드 작성
	int writeFeed(FeedModel model) throws Exception;
	
	// 피드 삭제
	int deleteFeed(@Param("feedID") int feedID, @Param("userID") String userID) throws Exception;
	
	// 좋아요 설정
	int incLikeCnt(int feedID) throws Exception;
	
	// 좋아요 해제
	int decLikeCnt(int feedID) throws Exception;
	
	// 해당 유저의 피드 불러오기
	List<FeedModel> getFeedList(String userID) throws Exception;
	
	// 피드 좋아요 리스트 추가
	int insertLikeFeed(@Param("feedID") int feedID, @Param("userID") String userID) throws Exception;
		
	// 피드 좋아요 리스트 삭제
	int deleteLikeFeed(@Param("feedID") int feedID, @Param("userID") String userID) throws Exception;
	
	// 내 타임라인 보기
	List<FeedModel> getMyTimeline(String userID) throws Exception;
	
	// 내가 좋아요 한 피드 보기
	List<FeedModel> likeFeedList(String userID) throws Exception;
	
	// 메인 화면 피드 보기(모든 피드)
	List<FeedModel> mainFeedList() throws Exception;
	
	//상세 피드글 보기
	FeedModel getFeed(int feedID) throws Exception;
	
	//좋아요 인원찾기
	LikeFeedModel selectLike(@Param("feedID") int feedID, @Param("userID") String userID) throws Exception;
}
