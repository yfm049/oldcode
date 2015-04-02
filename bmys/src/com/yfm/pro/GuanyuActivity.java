package com.yfm.pro;

import android.app.ActivityGroup;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.yfm.net.HttpDao;

public class GuanyuActivity extends ActivityGroup {

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
	private String url="/MobApp/about";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guanyu);
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
				Toast.makeText(GuanyuActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
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
