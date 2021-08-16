package com.ssafy.backend.coupon.model;

public class GameIDModel {
	String userID;
	String afkarenaUID;
	String cookierunUID;
	
	public String getCookierunUID() {
		return cookierunUID;
	}
	public void setCookierunUID(String cookierunUID) {
		this.cookierunUID = cookierunUID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAfkarenaUID() {
		return afkarenaUID;
	}
	public void setAfkarenaUID(String afkarenaUID) {
		this.afkarenaUID = afkarenaUID;
	}
}
