package com.pro.yfmlic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.pro.yfmlic.R;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(arg0,MessageService.class);
		arg0.startService(intent);
	}

}
