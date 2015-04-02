package com.yfm.adapter;

import java.util.List;

import com.yfm.pojo.Type;
import com.yfm.ydbg.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TypeAdapter extends BaseAdapter {

	private Context context;
	private List<Type> lt;
	public TypeAdapter(Context context,List<Type> lt){
		this.context=context;
		this.lt=lt;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return lt.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lt.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.type_item, null);
		}
		TextView type=(TextView)view.findViewById(R.id.type);
		Type t=lt.get(position);
		type.setText(t.getEventTypeName());
		return view;
	}

}
