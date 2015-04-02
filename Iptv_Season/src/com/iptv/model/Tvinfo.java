package com.iptv.model;

public class Tvinfo {

	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHttpurl() {
		return httpurl;
	}
	public void setHttpurl(String httpurl) {
		this.httpurl = httpurl;
	}
	public String getHotlink() {
		return hotlink;
	}
	public void setHotlink(String hotlink) {
		this.hotlink = hotlink;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getEpg() {
		return epg;
	}
	public void setEpg(String epg) {
		this.epg = epg;
	}
	private String name;
	private String httpurl;
	private String hotlink;
	private String isflag;
	public String getIsflag() {
		return isflag;
	}
	public void setIsflag(String isflag) {
		this.isflag = isflag;
	}
	private String logo;
	private String epg;
}
