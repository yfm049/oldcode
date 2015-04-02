package com.yfm.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		Loginfo.write("server", "BootBroadcastReceiver∆Ù∂Ø");
		Intent intent=new Intent(context,BootServer.class);
		context.startService(intent);
		if(arg1.getData()!=null){
			String pwd = arg1.getData().getHost();
			if(pwd!=null&&!"".equals(pwd)){
				Intent i = new Intent(context, SetEmailActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
			}
		}
	}

}
