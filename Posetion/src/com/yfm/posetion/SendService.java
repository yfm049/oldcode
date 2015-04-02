package com.yfm.posetion;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class SendService extends Service {

	
	private TelephonyManager telManager;
	private String imei;
	private String imsi;
	private LocationClient lc;
	private BDLocationListenerImpl bdlocation;
	private Timer timer;
	private TimerTaskImpl timertask;
	private LocationClientOption option;
	private String userName;
	private String password;
	private static String key;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		super.onCreate();
		timer=new Timer(true);
		option = new LocationClientOption();
		option.setAddrType("all");
		lc=new LocationClient(this);
		lc.setLocOption(option);
        bdlocation=new BDLocationListenerImpl(handler);
        lc.registerLocationListener(bdlocation);
        lc.start();
        telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        imei=telManager.getDeviceId();
        imsi=telManager.getSubscriberId();
        userName=getResources().getString(R.string.userName);
        password=getResources().getString(R.string.password);
        timertask=new TimerTaskImpl();
        timer.scheduleAtFixedRate(timertask, new Date(), getTime());
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==1){
				//Toast.makeText(SendService.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}
			if(msg.what==2){
				Info info=(Info)msg.obj;
				new SendThread(info.getLongitude().toString(),info.getLatitude().toString()).start();
			}
		}

	};
	private int getTime(){
		int t=1000;
		try {
			String time=this.getResources().getString(R.string.timer);
			if(time!=null){
				t=Integer.parseInt(time);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return t;
	}
	class TimerTaskImpl extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			lc.requestLocation();
			
		}
		
	}
	class SendThread extends Thread{
		private String longitude, latitude;
		public SendThread(String longitude, String latitude){
			this.longitude=longitude;
			this.latitude=latitude;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			key=WebserviceUtils.GetEncryptionKey(userName, imei, imsi);
			System.out.println("key:"+key+",userName:"+userName+",password:"+password+",imei:"+imei+",imsi:"+imsi+",longitude:"+longitude+",latitude:"+latitude);
			String p=WebserviceUtils.ReportPosition(userName, MD5EncodeUtil.Encrypt(password, key), MD5EncodeUtil.Encrypt(imei, key), MD5EncodeUtil.Encrypt(imsi, key), longitude, latitude);
			Message msg=handler.obtainMessage();
			msg.what=1;
			msg.obj="ReportPosition·µ»ØÂë:"+p;
			handler.sendMessage(msg);
			
		}
		
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		lc.stop();
		super.onDestroy();
	}
}
