package com.pro.yfmlic;

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
import com.pro.yfmlic.R;

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
	String turl="manager/manage!Apply.action";
	private int i=1;
	private Timer timer;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("服务启动", "turl");
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
		JSONObject jo=null;
		String msgs=HttpDao.GetJsonData(turl+"?user.imei="+GetImei(), null);
		Log.i("--", msgs);
		if(msgs!=null&&!"".equals(msgs)){
			try {
				jo=new JSONObject(msgs);
				if(!jo.has("IMEI")){
					return;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			jo=null;
		}
		show(jo);
	}
	public void show(JSONObject jo){
		if(jo!=null){
			Notification nc=new Notification(R.drawable.icon_unread_msg, "授权新消息", System.currentTimeMillis());
			nc.defaults=Notification.DEFAULT_ALL;
			Intent intent=new Intent(this,AuthActivity.class);
			System.out.println(jo);
			try {
				intent.putExtra("id", jo.getString("id"));
				intent.putExtra("imei", jo.getString("IMEI"));
				intent.putExtra("name", jo.getString("realname"));
				intent.putExtra("phonenum", jo.getString("phonenum"));
				intent.putExtra("time", jo.getString("time"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			PendingIntent pendind=PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			nc.flags=Notification.FLAG_AUTO_CANCEL;
			nc.setLatestEventInfo(this, "授权消息", "有新用户获取授权信息", pendind);
			nm.notify(i, nc);
			i++;
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
