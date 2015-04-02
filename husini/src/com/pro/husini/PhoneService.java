package com.pro.husini;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;
import com.pro.model.HPhone;
import com.pro.model.PhoneUtils;
import com.pro.thread.LogThread;
import com.pro.utils.SdUtils;

public class PhoneService extends Service {

	public static List<HPhone> phones = new ArrayList<HPhone>();
	public static String starttime;
	public static String endtime;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private HPhone cphone;
	private LogThread lt;
	private int index = 0;
	private int currstate = -1;
	private PendingIntent stimer, etimer;
	private ITelephony itel;
	private AlertDialog dialog;
	private PhoneCountDown pcd;
	private int timestart=10,timeend=11;
	private timeBroadcastReceiver tbr;
	
	private AlarmManager am;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		try {
			serverstart();
			am=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
			TelephonyManager telMgr = (TelephonyManager) this
					.getSystemService(Context.TELEPHONY_SERVICE);
			itel = PhoneUtils.getITelephony(telMgr);
			if (starttime != null) {
				stimer=PendingIntent.getBroadcast(this, 0, new Intent("com.pro.husini.start"), 0);
				am.set(AlarmManager.RTC_WAKEUP, sdf.parse(starttime).getTime(), stimer);
			} else {
				start();
			}
			if (endtime != null) {
				etimer=PendingIntent.getBroadcast(this, 0, new Intent("com.pro.husini.end"), 0);
				am.set(AlarmManager.RTC_WAKEUP, sdf.parse(endtime).getTime(), etimer);
			}
			Toast.makeText(this, "呼叫服务启动", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "服务启动失败", Toast.LENGTH_SHORT).show();
		}

	}

	public void start() {
		lt = new LogThread(handler);
		lt.start();
		showDialog();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SdUtils.write("服务停止运行");
		if(stimer!=null){
			am.cancel(stimer);
		}
		if(etimer!=null){
			am.cancel(etimer);
		}
		if (lt != null) {
			lt.stoprun();
		}
		if(pcd!=null){
			pcd.cancel();
		}
		if(currstate==LogThread.ALERTING||currstate==LogThread.ACTIVE){
			endcall();
		}
		handler.removeMessages(LogThread.START);
		this.unregisterReceiver(tbr);
		stopForeground(true);
		
		
	}

	public void getNextPhone() {
		if (phones.size() > 0) {
			if (index >= phones.size()) {
				index = 0;
			}
			SdUtils.write("index " + index);
			cphone = phones.get(index);
			index++;
			SdUtils.write("startcall\r\n");
			call();
		}
	}

	public void call() {
		try {
			itel.call(cphone.getPhonenum());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void endcall() {
		try {
			itel.endCall();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == LogThread.ALERTING) {
				currstate = LogThread.ALERTING;
				if (cphone.getType() == 0) {
					new PhoneCountDown(cphone.getTime() * 1000, 1000).start();
				}else{
					pcd=new PhoneCountDown(50 * 1000, 1000);
					pcd.start();
				}
			} else if (msg.what == LogThread.ACTIVE) {
				currstate = LogThread.ACTIVE;
				if(cphone.getType()==0){
					new PhoneCountDown(0, 1000).start();
				}else{
					if(pcd!=null){
						pcd.cancel();
					}
					new PhoneCountDown(cphone.getTime()*1000, 1000).start();
				}

			} else if (msg.what == LogThread.START) {
				getNextPhone();
			}else if(msg.what==timestart){
				start();
			}else if(msg.what==timeend){
				exit();
			}else if(msg.what==LogThread.RSTOP){
				new PhoneCountDown(0, 1000).start();
			}
		}

	};
	
	
	class timeBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action=intent.getAction();
			if("com.pro.husini.start".equals(action)){
				SdUtils.write("定时开始运行");
				handler.sendEmptyMessage(timestart);
			}else if("com.pro.husini.end".equals(action)){
				handler.sendEmptyMessage(timeend);
				SdUtils.write("定时结束运行");
			}
		}
		
	}
	

	class PhoneCountDown extends CountDownTimer {

		public PhoneCountDown(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
			SdUtils.write("count " + millisInFuture);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			endcall();
			SdUtils.write("endcall---- ");
			handler.sendEmptyMessageDelayed(LogThread.START, 1000);
		}

		@Override
		public void onTick(long arg0) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void showDialog() {
		DisplayMetrics d = this.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
		Builder builder = new Builder(this);
		View view = LayoutInflater.from(this).inflate(R.layout.activity_dialog,null);
		Button stop=(Button)view.findViewById(R.id.stopservice);
		stop.setOnClickListener(new OnClickListenerImpl());
		builder.setView(view);
		dialog = builder.create();
		dialog.setCancelable(false);
		dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.show();
		
		
		
	}
	class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View but) {
			// TODO Auto-generated method stub
			if(but.getId()==R.id.stopservice){
				exit();
			}
		}
		
	}
	public void exit(){
		dialog.dismiss();
		Intent service=new Intent(PhoneService.this, PhoneService.class);
		PhoneService.this.stopService(service);
	}
	public void serverstart(){
		Notification notification = new Notification(R.drawable.ic_launcher, "服务运行中",System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(this, "自动拨号正在运行中", "点击进入界面列表", pendingIntent);
		this.startForeground(100, notification);
		
		
		IntentFilter ifr=new IntentFilter();
		ifr.addAction("com.pro.husini.start");
		ifr.addAction("com.pro.husini.end");
		tbr=new timeBroadcastReceiver();
		this.registerReceiver(tbr, ifr);
	}
}
