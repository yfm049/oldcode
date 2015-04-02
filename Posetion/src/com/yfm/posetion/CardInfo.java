package com.yfm.posetion;

public class CardInfo {

	

	private int ContentTypeID;
	public int getContentTypeID() {
		return ContentTypeID;
	}
	public void setContentTypeID(int contentTypeID) {
		ContentTypeID = contentTypeID;
	}
	public int getContentIndex() {
		return ContentIndex;
	}
	public void setContentIndex(int contentIndex) {
		ContentIndex = contentIndex;
	}
	public String getReadPassword() {
		return ReadPassword;
	}
	public void setReadPassword(String readPassword) {
		ReadPassword = readPassword;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	private int ContentIndex;
	private int ContentAddr;
	public int getContentAddr() {
		return ContentAddr;
	}
	public void setContentAddr(int contentAddr) {
		ContentAddr = contentAddr;
	}
	private String ReadPassword;
	private String Content;
}
