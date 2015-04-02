package com.sms.tongbu;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class DataAdapter extends BaseAdapter {

	private Context context;
	private int image=1;//1 ºìÐÄ 2ÎåÐÇ
	private int value=0;

	public int getValue() {
		return value;
	}

	private List<Boolean> lb=new ArrayList<Boolean>();
	public int getImage() {
		return image;
		
	}

	public void init(){
		lb.clear();
		for(int i=0;i<20;i++){
			lb.add(false);
		}
	}
	public void setValue(int v){
		value=v;
		int m=v/5;
		init();
		for(int i=0;i<m;i++){
			lb.set(i, true);
		}
	}
	public void setImage(int image) {
		this.image = image;
	}

	public DataAdapter(Context context){
		this.context=context;
		init();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lb.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lb.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.image_item, null);
		}
		ImageView iv=(ImageView)view.findViewById(R.id.image);
		Boolean b=lb.get(position);
		if(image==1){
			if(b){
				iv.setImageResource(R.drawable.lhx);
			}else{
				iv.setImageResource(R.drawable.hhx);
			}
		}else{
			if(b){
				iv.setImageResource(R.drawable.lwx);
			}else{
				iv.setImageResource(R.drawable.hwx);
			}
		}
		return view;
	}

}
