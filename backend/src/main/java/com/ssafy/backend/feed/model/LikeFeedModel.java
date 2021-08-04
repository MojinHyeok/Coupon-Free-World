package com.ssafy.backend.feed.model;

public class LikeFeedModel {
	int lfID;
	String userID;
	int feedID;
	public int getLfID() {
		return lfID;
	}
	public void setLfID(int lfID) {
		this.lfID = lfID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getFeedID() {
		return feedID;
	}
	public void setFeedID(int feedID) {
		this.feedID = feedID;
	}

}
