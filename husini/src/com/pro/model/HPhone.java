package com.pro.model;

public class HPhone {

	public static String[] types=new String[]{"拨通后挂断","接听后挂断"};
	private String phonenum;
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	private int type=0;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	private int time=0;
}
