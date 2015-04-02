package com.smsbak.sms;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

public class SmsApp extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		IntentFilter intentfilter=new IntentFilter(Intent.ACTION_TIME_TICK);
		BroadcastReceiverImpl receiver=new BroadcastReceiverImpl();
		this.registerReceiver(receiver, intentfilter);
	}

}
