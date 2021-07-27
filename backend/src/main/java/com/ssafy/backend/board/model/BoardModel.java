package com.ssafy.backend.board.model;

public class BoardModel {
	int noticeOption;
	String title;
	String content;
	String writer;
	String regDate;
	String filePath1;
	String filePath2;
	String filePath3;
	String filePath4;
	int recommend;
	int viewCount;
	public int getNoticeOption() {
		return noticeOption;
	}
	public void setNoticeOption(int noticeOption) {
		this.noticeOption = noticeOption;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getcontent() {
		return content;
	}
	public void setcontent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getFilePath1() {
		return filePath1;
	}
	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
	}
	public String getFilePath2() {
		return filePath2;
	}
	public void setFilePath2(String filePath2) {
		this.filePath2 = filePath2;
	}
	public String getFilePath3() {
		return filePath3;
	}
	public void setFilePath3(String filePath3) {
		this.filePath3 = filePath3;
	}
	public String getFilePath4() {
		return filePath4;
	}
	public void setFilePath4(String filePath4) {
		this.filePath4 = filePath4;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	@Override
	public String toString() {
		return "BoardModel [noticeOption=" + noticeOption + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", regDate=" + regDate + ", filePath1=" + filePath1
				+ ", filePath2=" + filePath2 + ", filePath3=" + filePath3 + ", filePath4=" + filePath4 + "]";
	}
	
	
}
