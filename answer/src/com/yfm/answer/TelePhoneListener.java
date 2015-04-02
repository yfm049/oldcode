package com.yfm.answer;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class TelePhoneListener extends PhoneStateListener {

	private Context context;

	public TelePhoneListener(Context context) {
		this.context = context;
	}

	@Override
	public void onCallStateChanged(int state, String incomingNumber) {
		// TODO Auto-generated method stub
		try {
			super.onCallStateChanged(state, incomingNumber);
			Log.i("state", "电话状态" + state + ",电话号码" + incomingNumber);
			if (PhoneUtils.getBooleanConfig(context, "auto", true)) {
				switch (state) {
				case TelephonyManager.CALL_STATE_RINGING:
					Log.i("phonenumber", "响铃" + incomingNumber);
					PhoneUtils.answerRingingCall(context, incomingNumber);
					handler.sendEmptyMessageDelayed(1, 1000);
					break;
				case TelephonyManager.CALL_STATE_IDLE:
					Log.i("phonenumber", "挂断" + incomingNumber);
					handler.sendEmptyMessageDelayed(2, 1000);
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					Log.i("phonenumber", "通话中" + incomingNumber);
					handler.sendEmptyMessageDelayed(1, 1000);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==1){
				PhoneUtils.lock(context);
			}
			if(msg.what==2){
				PhoneUtils.lock(context);
				PhoneUtils.huifu(context);
			}
		}
		
	};

}
