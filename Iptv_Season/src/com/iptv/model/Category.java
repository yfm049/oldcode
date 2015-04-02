package com.iptv.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

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
	public List<Tvinfo> getTvlist() {
		return tvlist;
	}
	public void setTvlist(List<Tvinfo> tvlist) {
		this.tvlist = tvlist;
	}
	private String name;
	private List<Tvinfo> tvlist=new ArrayList<Tvinfo>();
}
