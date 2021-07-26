package com.ssafy.backend.social.service;

import java.util.List;

import com.ssafy.backend.social.model.SocialModel;

public interface SocialService {
	//팔로워찾기
	List<String> findFollower(String userid);
	//팔로잉찾기
	List<String> findFollowing(String userid);
	//팔로우요청
	int requestFollow(SocialModel model);
	//팔로우수락
	int allowFollow(SocialModel model);
	//팔로우요청한 사람찾기
	List<String> findrequestFollow(String userid);
	//중복검사하기
	SocialModel checkDuplicate(SocialModel model);
	//팦로우취소
	int cancleFollow(SocialModel model);
}
