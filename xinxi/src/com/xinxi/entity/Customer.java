package com.xinxi.entity;

public class Customer {
	private String username;
	private String pwd;
	private String endtime;
	private byte[] pwdbyte;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public byte[] getPwdbyte() {
		return pwdbyte;
	}

	public void setPwdbyte(byte[] pwdbyte) {
		this.pwdbyte = pwdbyte;
	}

}
