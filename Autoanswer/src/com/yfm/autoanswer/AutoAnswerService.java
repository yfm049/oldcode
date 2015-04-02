package com.yfm.autoanswer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class AutoAnswerService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		try {
			Bundle bundle=intent.getExtras();
			if(bundle!=null){
				boolean flag=bundle.getBoolean("msg", false);
				if(flag){
					Intent ia=new Intent(this, AutoAnswer.class);
					ia.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					this.startActivity(ia);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.onStartCommand(intent, flags, startId);
	}

	private TelephonyManager tm=null;
	private TelePhoneListener tl=null;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		try {
			Log.i("msg", "·þÎñÆô¶¯");
			this.tm=(TelephonyManager)super.getSystemService(Context.TELEPHONY_SERVICE);
			tl=new TelePhoneListener(this);
			this.tm.listen(tl, PhoneStateListener.LISTEN_CALL_STATE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
