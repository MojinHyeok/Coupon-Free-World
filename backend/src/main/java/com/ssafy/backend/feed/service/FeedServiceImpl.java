package com.ssafy.backend.feed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.feed.mapper.FeedMapper;
import com.ssafy.backend.feed.model.FeedModel;

@Service
public class FeedServiceImpl implements FeedService {
	@Autowired
	FeedMapper mapper;
	
	@Override
	public int writeFeed(FeedModel model) throws Exception {
		return mapper.writeFeed(model);
	}

	@Override
	public int deleteFeed(int feedID) throws Exception {
		return mapper.deleteFeed(feedID);
	}

	@Override
	public int incLikeCnt(int feedID) throws Exception {
		return mapper.incLikeCnt(feedID);
	}

	@Override
	public int decLikeCnt(int feedID) throws Exception {
		return mapper.decLikeCnt(feedID);
	}

}
