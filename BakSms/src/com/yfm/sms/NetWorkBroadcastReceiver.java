package com.yfm.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetWorkBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Loginfo.write("BroadcastReceiver", "BroadcastReceiver´¥·¢");
		SendMail sm=new SendMail(arg0);
		Loginfo.write("BroadcastReceiver", "BroadcastReceiverÍøÂç×´Ì¬"+sm.getNetWorkState());
		if(sm.getNetWorkState()&&!SendMail.isfasong){
			SendMailThread smt=new SendMailThread(sm);
    		smt.start();
		}
	}

}
