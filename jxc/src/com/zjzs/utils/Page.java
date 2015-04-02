package com.zjzs.utils;

public class Page {

	private int cpage=1;
	private int tsize=0;
	private int psize=20;
	private boolean isasc=true;
	public int getCpage() {
		return cpage;
	}
	public void setCpage(int cpage) {
		this.cpage = cpage;
	}
	public int getTsize() {
		return tsize;
	}
	public void setTsize(int tsize) {
		this.tsize = tsize;
	}
	public int getPsize() {
		return psize;
	}
	public boolean isIsasc() {
		return isasc;
	}
	public void setIsasc(boolean isasc) {
		this.isasc = isasc;
	}
}
