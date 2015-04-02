package com.yfm.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.yfm.net.HttpDao;
import com.yfm.pojo.TongZhi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;

public class MessageService extends Service {

	NotificationManager nm;
	String url="/MobApp/Notice";
	public static List<TongZhi> lg = new ArrayList<TongZhi>();
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		nm =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Timer timer=new Timer();
		timer.scheduleAtFixedRate(new Dingshi(), 5000, 1000*60*60);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public void GetMsg(){
		if(MainActivity.cid==-1){
			lg.addAll(HttpDao.getMessage(url+"?cid="+GetImei(), null));
		}else{
			lg.addAll(HttpDao.getMessage(url+"?cid="+MainActivity.cid, null));
		}
		
		show();
	}
	public void show(){
		if(lg.size()>0){
			Notification nc=new Notification(R.drawable.icon_unread_msg, "收到"+lg.size()+"新消息", System.currentTimeMillis());
			Intent intent=new Intent(this,TongzhiActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			PendingIntent pendind=PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			nc.flags=Notification.FLAG_AUTO_CANCEL;
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
