package com.microblog.po;

public class Weibo {
	private int id;
	private int w_id;
	private String w_content;
	private String w_image;
	public String getW_image() {
		return w_image;
	}
	public void setW_image(String wImage) {
		w_image = wImage;
	}
	private String w_date;
	private String w_unick;
	private String w_upic;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getW_id() {
		return w_id;
	}
	public void setW_id(int w_id) {
		this.w_id = w_id;
	}
	public String getW_content() {
		return w_content;
	}
	public void setW_content(String w_content) {
		this.w_content = w_content;
	}

	
	public String getW_date() {
		return w_date;
	}
	public void setW_date(String w_date) {
		this.w_date = w_date;
	}
	public String getW_unick() {
		return w_unick;
	}
	public void setW_unick(String w_unick) {
		this.w_unick = w_unick;
	}
	public String getW_upic() {
		return w_upic;
	}
	public void setW_upic(String w_upic) {
		this.w_upic = w_upic;
	}

}
