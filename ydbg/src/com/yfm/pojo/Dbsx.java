package com.yfm.pojo;

import java.io.Serializable;

public class Dbsx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String EventID;
	private String EventTitle;
	private String EventContent;
	private String EventDateTime;
	private String EventImgUrl;
	public String getEventID() {
		return EventID;
	}
	public void setEventID(String eventID) {
		EventID = eventID;
	}
	public String getEventImgUrl() {
		return EventImgUrl;
	}
	public void setEventImgUrl(String eventImgUrl) {
		EventImgUrl = eventImgUrl;
	}
	public String getEventTitle() {
		return EventTitle;
	}
	public void setEventTitle(String eventTitle) {
		EventTitle = eventTitle;
	}
	public String getEventContent() {
		return EventContent;
	}
	public void setEventContent(String eventContent) {
		EventContent = eventContent;
	}
	public String getEventDateTime() {
		return EventDateTime;
	}
	public void setEventDateTime(String eventDateTime) {
		EventDateTime = eventDateTime;
	}
}
