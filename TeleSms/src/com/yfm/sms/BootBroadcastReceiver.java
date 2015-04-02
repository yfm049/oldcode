package com.yfm.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Loginfo.write("server", "BootBroadcastReceiver∆Ù∂Ø");
		Intent intent=new Intent(arg0,BootServer.class);
		arg0.startService(intent);
	}

}
