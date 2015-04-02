package com.pro.yfm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MessageService extends Service {
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(timer!=null){
			timer.cancel();
		}
	}
	NotificationManager nm;
	String url="manager/user!SendAuth.action";
	public static List<TongZhi> lg = new ArrayList<TongZhi>();
	private Timer timer;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		nm =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		timer=new Timer();
		timer.scheduleAtFixedRate(new Dingshi(), 3000, 1000*2);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public void GetMsg(){
		try {
			String msg=HttpDao.GetJsonData(url+"?user.imei="+GetImei(), null);
			Log.i("--", msg);
			if(msg!=null&&!"".equals(msg)){
				TongZhi tz=new TongZhi();
				tz.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				JSONObject jo=new JSONObject(msg);
				if(jo.has("msg")){
					msg=jo.getString("msg");
					tz.setCon(msg);
					lg.add(tz);
					show();
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void show(){
		if(lg.size()>0){
			Notification nc=new Notification(R.drawable.icon_unread_msg, "收到"+lg.size()+"新消息", System.currentTimeMillis());
			nc.defaults=Notification.DEFAULT_ALL;
			Intent intent=new Intent(this,TongzhiActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			PendingIntent pendind=PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			nc.flags=Notification.FLAG_AUTO_CANCEL|Notification.FLAG_NO_CLEAR;
			nc.setLatestEventInfo(this, "新消息", lg.get(0).getCon(), pendind);
			nm.notify(0, nc);
		}
		
		
	}
	public String GetImei(){
    	TelephonyManager telephonyManager=(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
    	String imei=telephonyManager.getDeviceId();
    	return imei;
    }
	class Dingshi extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
			GetMsg();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
}
