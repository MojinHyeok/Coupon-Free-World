package com.ssafy.backend.social.service;

import java.util.List;

import com.ssafy.backend.social.model.SocialModel;

public interface SocialService {
	//팔로워찾기
	List<String> findFollower(String userid);
	//팔로잉찾기
	List<String> findFollowing(String userid);

}
