package com.iptv.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iptv.pojo.Notice;
import com.iptv.season.R;

public class NoticeAdapter extends AbsAdapter {

	private Context context;
	private List<Notice> ln;
	public NoticeAdapter(Context context,List<Notice> ln){
		this.context=context;
		this.ln=ln;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ln.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ln.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_notice_lisg_item, null);
		}
		
		return convertView;
	}


}
