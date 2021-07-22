package com.ssafy.backend.social.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.social.model.SocialModel;

@Repository
public interface SocialMapper {

	List<String> findFollower(String userid);
	List<String> findFollowing(String userid);
}
