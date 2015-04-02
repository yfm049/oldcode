package com.qq.adapter;

import java.util.List;
import java.util.Map;

import com.qq.visitor.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private Context ctx;
	private List<String> listData;
	public ListAdapter(Context ctx, List<String> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }




	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}


	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listData.get(arg0);
	}


	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=LayoutInflater.from(ctx).inflate(R.layout.activity_child, null);
		}
		TextView child=(TextView)convertView.findViewById(R.id.child);
		TextView xh=(TextView)convertView.findViewById(R.id.xh);
		final String childText = listData.get(arg0);
		child.setText(childText);
		xh.setText(arg0+1+":");
		return convertView;
	}

	
	
}
