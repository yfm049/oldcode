package com.yfm.adapter;

import com.yfm.entie.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Qjadapter extends BaseAdapter {

	private int[] img=new int[]{R.drawable.hk,R.drawable.ys,R.drawable.jc,R.drawable.wx,R.drawable.jr,R.drawable.sm,R.drawable.wcbf,R.drawable.zjcf,R.drawable.yjbf};
	private Context context;
	
	public Qjadapter(Context context,int i){
		this.context=context;
		if(i==1){
			img=new int[]{R.drawable.wcbf,R.drawable.zjcf,R.drawable.yjbf};
		}else{
			img=new int[]{R.drawable.hk,R.drawable.ys,R.drawable.jc,R.drawable.wx,R.drawable.jr,R.drawable.sm};
		}
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return img.length;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return img[arg0];
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.qingjing_item, null);
		}
		ImageView iv=(ImageView)view.findViewById(R.id.qjimg);
		iv.setImageResource(img[arg0]);
		return view;
	}

}
