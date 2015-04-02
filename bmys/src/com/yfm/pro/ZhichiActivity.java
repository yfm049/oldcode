package com.yfm.pro;

import java.util.List;

import android.app.ActivityGroup;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.yfm.adapter.ActiveAdapter;
import com.yfm.adapter.PhoneAdapter;
import com.yfm.net.HttpDao;
import com.yfm.pojo.Good;
import com.yfm.pojo.Phone;

public class ZhichiActivity extends ActivityGroup {

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Dialog dialog=new ProgressDialog(this);
		dialog.setTitle("正在加载...");
		Log.i("--", "正在加载...");
		dialog.show();
		Getdata ga=new Getdata(url, dialog);
		ga.start();
	}
	private TextView zhichitext;
	private String url="/MobApp/support";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zhichi);
		zhichitext=(TextView)super.findViewById(R.id.zhichitext);
		
	}
	public Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==0){
				zhichitext.setText(msg.obj.toString());
			}else{
				Toast.makeText(ZhichiActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
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
			try {
					String text=HttpDao.GetJsonData(url, dialog);
					if(text!=null){
						Message msg=handler.obtainMessage();
						msg.obj=text;
						msg.what=0;
						handler.sendMessage(msg);
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
