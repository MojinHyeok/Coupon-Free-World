package com.ssafy.backend.socket.model;

public class SocketModel {
	String userName;
	String content;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public SocketModel(String userName, String content) {
		super();
		this.userName = userName;
		this.content = content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
