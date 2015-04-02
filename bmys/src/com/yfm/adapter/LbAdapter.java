package com.yfm.adapter;

import java.util.ArrayList;
import java.util.List;

import com.yfm.pojo.Good;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class LbAdapter extends BaseAdapter {

	public static AsyncImageLoader asyncImageLoader = new AsyncImageLoader();
	public boolean isrun=false;
	public int currpage=1;
	public List<Good> lg=new ArrayList<Good>();
	public void setIsrun(boolean isrun) {
		this.isrun = isrun;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	public void getData(){};
	public void clearData(){
		lg.clear();
		currpage=1;
		this.notifyDataSetChanged();
	}
}
