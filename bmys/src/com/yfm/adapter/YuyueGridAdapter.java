package com.yfm.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.yfm.net.HttpDao;
import com.yfm.pojo.YuYue;
import com.yfm.pojo.YuYueGrid;
import com.yfm.pro.R;

public class YuyueGridAdapter extends BaseAdapter {

	private List<YuYueGrid> lg=new ArrayList<YuYueGrid>();
	private Context context;
	public YuyueGridAdapter(Context context,List<YuYueGrid> lg){
		this.context=context;
		this.lg=lg;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lg.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return lg.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.yuyuegrid_item, null);
		}
		TextView name=(TextView)view.findViewById(R.id.name);
		TextView value=(TextView)view.findViewById(R.id.value);
		YuYueGrid gd=lg.get(arg0);
		name.setText(gd.getName());
		value.setText(gd.getValue());
		return view;
	}
	
	
}
