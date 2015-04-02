package com.yfm.adapter;

import java.util.List;

import com.yfm.pojo.Funinfo;
import com.yfm.ydbg.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FunAdapter extends BaseAdapter {

	private Context context;
	private List<Funinfo> li;
	public FunAdapter(Context context,List<Funinfo> li){
		this.context=context;
		this.li=li;
		
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return li.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return li.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.activity_fun_item, null);
		}
		Funinfo info=li.get(arg0);
		ImageView image=(ImageView)view.findViewById(R.id.image);
		TextView name=(TextView)view.findViewById(R.id.name);
		image.setImageResource(info.getImage());
		name.setText(info.getName());
		return view;
	}

}
