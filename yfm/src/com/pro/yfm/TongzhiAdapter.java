package com.pro.yfm;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TongzhiAdapter extends BaseAdapter {

	private List<TongZhi> lg = new ArrayList<TongZhi>();
	private Context context;
	public TongzhiAdapter(Context context){
		this.context=context;
		lg.addAll(MessageService.lg);
		MessageService.lg.clear();
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return lg.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return  lg.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int pos, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.tongzhi_item, null);
		}
		TextView date=(TextView)view.findViewById(R.id.date);
		TextView con=(TextView)view.findViewById(R.id.con);
		TongZhi tz=lg.get(pos);
		date.setText(tz.getDate());
		con.setText(tz.getCon());
		return view;
	}

}
