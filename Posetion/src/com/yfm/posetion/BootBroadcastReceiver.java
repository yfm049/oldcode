package com.yfm.posetion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(context,SendService.class);
		context.startService(intent);
		
		
		
	}

}
