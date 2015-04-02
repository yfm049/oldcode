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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yfm.net.HttpDao;
import com.yfm.net.OnClickListenerImpl;
import com.yfm.pojo.Good;
import com.yfm.pojo.Phone;
import com.yfm.pro.R;

public class PhoneAdapter extends LbAdapter {

	private List<Phone> lg=new ArrayList<Phone>();
	private Context context;
	private Dialog dialog;
	private int currpage=1;
	private String url="/MobApp/TelephoneList";
	public PhoneAdapter(Context context){
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
			view=LayoutInflater.from(context).inflate(R.layout.phone_item, null);
		}
		
		TextView title=(TextView)view.findViewById(R.id.title);
		TextView phonenum=(TextView)view.findViewById(R.id.phonenum);
		Phone gd=lg.get(arg0);
		title.setText(gd.getTitle());
		phonenum.setText(gd.getPhonenum());
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
				PhoneAdapter.this.notifyDataSetChanged();
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
					List<Phone> lgs=HttpDao.getPhone(url, dialog);
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
