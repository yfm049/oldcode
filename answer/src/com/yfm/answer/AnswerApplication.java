package com.yfm.answer;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

public class AnswerApplication extends Application {

	private BootBroadcastReceiver receiver;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		receiver=new BootBroadcastReceiver();
		IntentFilter filter=new IntentFilter();
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(2147483647);
		this.registerReceiver(receiver, filter);
		Intent service=new Intent(this,AnswerService.class);
		this.startService(service);
	}

}
