package com.ssafy.backend.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.social.mapper.SocialMapper;
import com.ssafy.backend.social.model.SocialModel;

@Service
public class SocialServiceImpl implements SocialService{

	@Autowired
	SocialMapper mapper;
	
	@Override
	public List<String> findFollower(String userid) {
		return mapper.findFollower(userid);
	}

	@Override
	public List<String> findFollowing(String userid) {
		return mapper.findFollowing(userid);
	}

}
