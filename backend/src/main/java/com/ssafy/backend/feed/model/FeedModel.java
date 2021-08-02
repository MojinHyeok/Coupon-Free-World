package com.ssafy.backend.feed.model;

public class FeedModel {
	int feedID;
	String userID;
	String photoPath1;
	String photoPath2;
	String content;
	String date;
	int likeCnt;
	
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
	public String getPhotoPath1() {
		return photoPath1;
	}
	public void setPhotoPath1(String photoPath1) {
		this.photoPath1 = photoPath1;
	}
	public String getPhotoPath2() {
		return photoPath2;
	}
	public void setPhotoPath2(String photoPath2) {
		this.photoPath2 = photoPath2;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	
}
