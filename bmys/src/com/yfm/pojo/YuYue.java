package com.yfm.pojo;

import java.util.List;

public class YuYue {

	public String data;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public List<YuYueGrid> getLyg() {
		return lyg;
	}
	public void setLyg(List<YuYueGrid> lyg) {
		this.lyg = lyg;
	}
	public List<YuYueGrid> lyg;
}
