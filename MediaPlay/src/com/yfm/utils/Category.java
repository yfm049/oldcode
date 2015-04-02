package com.yfm.utils;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	private List<String> names=new ArrayList<String>();
}
