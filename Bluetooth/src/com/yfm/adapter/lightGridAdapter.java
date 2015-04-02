package com.yfm.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.yfm.bluetooth.R;
import com.yfm.utils.Channel;

public class lightGridAdapter extends BaseAdapter {

	private List<Channel> lc;
	private Context context;
	public lightGridAdapter(List<Channel> lc,Context context){
		this.lc=lc;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lc.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return lc.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Channel cl=lc.get(pos);
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.state_item, null);
		}
		System.out.println("-----"+cl.isState());
		ImageView rb=(ImageView)view.findViewById(R.id.lightstate);
		if(cl.isState()){
			rb.setImageResource(R.drawable.red);
		}else{
			rb.setImageResource(R.drawable.blue);
		}
		TextView lightid=(TextView)view.findViewById(R.id.lightid);
		lightid.setText(cl.getId()+"");
		return view;
	}

}
