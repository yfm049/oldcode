package com.yfm.adapter;

import java.util.ArrayList;
import java.util.List;

import com.yfm.adapter.ActiveAdapter.Getdata;
import com.yfm.net.HttpDao;
import com.yfm.net.OnClickListenerImpl;
import com.yfm.pojo.Good;
import com.yfm.pojo.YuYue;
import com.yfm.pro.R;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class YuYueAdapter extends BaseAdapter {

	private List<YuYue> lg=new ArrayList<YuYue>();
	public List<YuYue> getLg() {
		return lg;
	}
	private Context context;
	private Dialog dialog;
	private String url="/MobApp/ReservationList";
	private static boolean isrun=false;
	public YuYueAdapter(Context context){
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
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.yuyue_item, null);
		}
		TextView date=(TextView)view.findViewById(R.id.date);
		GridView gv=(GridView)view.findViewById(R.id.shuju);
		YuYue gd=lg.get(arg0);
		date.setText(gd.getData());
		YuyueGridAdapter yga=new YuyueGridAdapter(context,gd.getLyg());
		gv.setAdapter(yga);
		return view;
	}
	
	public void getData(){
		
		if(!isrun){
			if(dialog!=null){
				dialog.cancel();
			}
			dialog=new ProgressDialog(context);
			dialog.setTitle("正在加载...");
			Log.i("--", "正在加载...");
			dialog.show();
			Getdata ga=new Getdata(url,dialog);
			ga.start();
		}
	}
	public Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==0){
				YuYueAdapter.this.notifyDataSetChanged();
				
			}else{
				Toast.makeText(context, "加载了0条数据", Toast.LENGTH_SHORT).show();
			}
			isrun=false;
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
					List<YuYue> lgs=HttpDao.getYuyueList(url, dialog);
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
