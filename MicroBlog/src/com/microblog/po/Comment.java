package com.microblog.po;

public class Comment {
	
	private int id;
	private int c_id; //与微博号外键相连
	private int c_uid; //与用户号外键相连
    private String c_content;
    private String c_date;
    private String c_unick;
    private String c_upic;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getC_uid() {
		return c_uid;
	}
	public void setC_uid(int c_uid) {
		this.c_uid = c_uid;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	public String getC_unick() {
		return c_unick;
	}
	public void setC_unick(String c_unick) {
		this.c_unick = c_unick;
	}
	public String getC_upic() {
		return c_upic;
	}
	public void setC_upic(String c_upic) {
		this.c_upic = c_upic;
	}
    
	
	
    
}
