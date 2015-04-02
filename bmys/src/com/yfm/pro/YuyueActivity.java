package com.yfm.pro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.ActivityGroup;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.yfm.adapter.YuYueAdapter;
import com.yfm.net.HttpDao;

public class YuyueActivity extends ActivityGroup {

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ya = new YuYueAdapter(this);
		datalist.setAdapter(ya);
		ya.getData();
		yuyuebut.setOnClickListener(new OnClickListenerImpl());
	}

	private ListView datalist;
	private YuYueAdapter ya;
	private Button yuyuebut;
	private Dialog dialog;
	private List<String> shijianlist = new ArrayList<String>();
	private List<String> riqilist = new ArrayList<String>();
	private Spinner riqi, shijian;
	String url = "/MobApp/AddReservation";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yuyue);
		yuyuebut = (Button) super.findViewById(R.id.yuyuebut);
		datalist = (ListView) super.findViewById(R.id.datalist);
		
		Calendar time = Calendar.getInstance();
		Date d = new Date();
		d.setHours(8);
		d.setMinutes(30);
		d.setSeconds(0);
		time.setTime(d);
		for (int i = 0; i < 19; i++) {
			shijianlist
					.add(new SimpleDateFormat("HH:mm").format(time.getTime()));
			time.add(Calendar.MINUTE, 30);
		}
		time.setTime(new Date());
		for (int i = 0; i < 15; i++) {
			riqilist.add(new SimpleDateFormat("yyyy-MM-dd").format(time
					.getTime()));
			time.add(Calendar.DAY_OF_MONTH, 1);
		}
	}

	class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			if (MainActivity.cid <= 0) {
				Intent intent = new Intent(YuyueActivity.this, LoginActivity.class);
				YuyueActivity.this.startActivity(intent);
			} else {
				Builder builder = new Builder(YuyueActivity.this);
				builder.setTitle("预约");
				View v = LayoutInflater.from(YuyueActivity.this).inflate(
						R.layout.yuyuedialog, null);
				riqi = (Spinner) v.findViewById(R.id.riqi);
				shijian = (Spinner) v.findViewById(R.id.shijian);
				ArrayAdapter<String> aa = new ArrayAdapter<String>(
						YuyueActivity.this, R.layout.yuyuedialog_item,
						R.id.xianshi, riqilist);
				riqi.setAdapter(aa);
				ArrayAdapter<String> sj = new ArrayAdapter<String>(
						YuyueActivity.this, R.layout.yuyuedialog_item,
						R.id.xianshi, shijianlist);
				shijian.setAdapter(sj);
				builder.setView(v);
				builder.setPositiveButton("确定", new DialogOnClickListener());
				builder.setNegativeButton("取消", null);
				dialog = builder.create();
				dialog.show();
			}
		}

	}

	class DialogOnClickListener implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			int rpos = riqi.getSelectedItemPosition();
			int spos = shijian.getSelectedItemPosition();
			List<NameValuePair> lnp = new ArrayList<NameValuePair>();
			lnp.add(new BasicNameValuePair("username", MainActivity.name));
			lnp.add(new BasicNameValuePair("userpwd", MainActivity.pass));
			lnp.add(new BasicNameValuePair("cid", String
					.valueOf(MainActivity.cid)));
			lnp.add(new BasicNameValuePair("rdatetime", riqilist.get(rpos) + " "
					+ shijianlist.get(spos)+":00"));
			System.out.println("cid:"+MainActivity.cid+"--rdatetime:"+riqilist.get(rpos) + " "
					+ shijianlist.get(spos)+":00");
			dialog.cancel();
			dialog=new ProgressDialog(YuyueActivity.this);
			dialog.setTitle("正在请求中...");
			Log.i("--", "正在加载...");
			dialog.show();
			Getdata gda=new Getdata(url, lnp, dialog);
			gda.start();
		}

	}
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			String u=msg.obj.toString();
			if ("1".equals(u)) {
				Builder builder=new Builder(YuyueActivity.this);
				builder.setTitle("提示");
				builder.setMessage("恭喜您成功完成预约，请注意查收短信！");
				builder.setPositiveButton("确定", null);
				builder.create().show();
			} else {
				Builder builder=new Builder(YuyueActivity.this);
				builder.setTitle("提示");
				builder.setMessage("对不起您的预约失败，请电话联系我们或电脑登录我们的网上商城完成预约！");
				builder.setPositiveButton("确定", null);
				builder.create().show();
			}
		}
		
	};

	class Getdata extends Thread{
		private Dialog dialog;
		private String url;
		private List<NameValuePair> lnp;
		public Getdata(String url,List<NameValuePair> lnp,Dialog dialog){
			this.url=url;
			this.dialog=dialog;
			this.lnp=lnp;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
						String p=HttpDao.PostData(url, lnp, dialog);
						Message msg=handler.obtainMessage();
						msg.obj=p;
						handler.sendMessage(msg);
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
