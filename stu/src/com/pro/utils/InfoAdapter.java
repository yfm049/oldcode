package com.pro.utils;

import java.util.ArrayList;
import java.util.List;

import com.pro.stu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InfoAdapter extends BaseAdapter {

	private List<Info> linfo=new ArrayList<Info>();
	public List<Info> getLinfo() {
		return linfo;
	}

	private Context context;
	public InfoAdapter(Context context){
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return linfo.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return linfo.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Info info=linfo.get(arg0);
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.activity_jilu_item, null);
		}
		TextView parecard=(TextView) view.findViewById(R.id.parecard);

		TextView parename=(TextView) view.findViewById(R.id.parename);
		TextView jlriqi=(TextView) view.findViewById(R.id.jlriqi);
		parecard.setText(info.getPareCard());

		jlriqi.setText(info.getJldate());
		parename.setText(info.getPareName());
		return view;
	}

}
