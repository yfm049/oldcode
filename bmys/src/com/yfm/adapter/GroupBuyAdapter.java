package com.yfm.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yfm.net.HttpDao;
import com.yfm.net.OnClickListenerImpl;
import com.yfm.pojo.Good;
import com.yfm.pro.R;

public class GroupBuyAdapter extends LbAdapter {

	private Context context;
	private Dialog dialog;
	public static  String url="/MobApp/GroupPurchaseList";
	public static  String aurl="/MobApp/AddGroupPurchase";
	public static  String xxurl="/mobapp/GetGroupPurchase/";
	public GroupBuyAdapter(Context context){
		this.context=context;
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
		view=LayoutInflater.from(context).inflate(R.layout.groupbuy_item, null);
		TextView id=(TextView)view.findViewById(R.id.id);
		TextView title=(TextView)view.findViewById(R.id.title);
		TextView date=(TextView)view.findViewById(R.id.date);
		Good gd=lg.get(arg0);
		
		gd.setFenlei("详情");
		gd.setUrl(aurl);
		gd.setXxurl(xxurl+gd.getId());
		
		id.setText(gd.getId());
		title.setText(gd.getTitle());
		date.setText(gd.getSdate()+"-"+gd.getEdate());
		TextView price=(TextView)view.findViewById(R.id.price);
		price.setText(gd.getPrice());
		TextView pcount=(TextView)view.findViewById(R.id.pcount);
		pcount.setText(gd.getPcount());
		ImageView imageView=(ImageView)view.findViewById(R.id.img);
		Drawable cachedImage = asyncImageLoader.loadDrawable(gd.getImageUrl(),imageView);
		if (cachedImage == null) {
			imageView.setImageResource(R.drawable.def);
		}else{
			imageView.setImageDrawable(cachedImage);
		}
		return view;
	}
	
	public void getData(){
		Log.i("--", isrun+"");
		if(!isrun){
			if(dialog!=null){
				dialog.cancel();
			}
			dialog=new ProgressDialog(context);
			dialog.setTitle("正在加载...");
			Log.i("--", "正在加载...");
			dialog.show();
			Getdata ga=new Getdata(url+"?page="+currpage,dialog);
			ga.start();
		}
	}
	public Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==0){
				GroupBuyAdapter.this.notifyDataSetChanged();
				currpage++;
				isrun=false;
			}else{
				Toast.makeText(context, "加载了0条数据", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	};
	class Getdata extends Thread{
		private Dialog dialog;
		private String url;
		public Getdata(String url,Dialog dialog){
			this.url=url;
			this.dialog=dialog;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i("isrun", isrun+"");
			try {
					isrun=true;
					List<Good> lgs=HttpDao.getGood(url, dialog);
					lg.addAll(lgs);
					Log.i("tiso", lgs.size()+"---");
					if(lgs.size()>0){
						handler.sendEmptyMessage(0);
					}else{
						handler.sendEmptyMessage(1);
					}
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
