package com.yfm.pojo;

import java.io.Serializable;

public class Good implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String fenlei;
	private String url;
	private String ImageUrl;
	private String xxurl;
	private String itemtype;
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	public String getXxurl() {
		return xxurl;
	}
	public void setXxurl(String xxurl) {
		this.xxurl = xxurl;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFenlei() {
		return fenlei;
	}
	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	private String title;
	private String price;
	private String intro;
	private String sdate;
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	private String edate;
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	private String pcount;
	
	public String getPcount() {
		return pcount;
	}
	public void setPcount(String pcount) {
		this.pcount = pcount;
	}
}
