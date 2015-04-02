package com.smsbak.sms;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneStateListenerImpl extends PhoneStateListener {

	private PhoneRecord phonerecord;
	private CallConten callcontent;
	private Context context;
	public PhoneStateListenerImpl(Context context,CallConten callcontent){
		this.callcontent=callcontent;
		this.context=context;
	}
	@Override
	public void onCallStateChanged(int state, String incomingNumber) {
		// TODO Auto-generated method stub
		super.onCallStateChanged(state, incomingNumber);
		switch (state) {
		case TelephonyManager.CALL_STATE_OFFHOOK:
			phonerecord=new PhoneRecord(context);
			phonerecord.startRecord(incomingNumber);
			callcontent.setPhonerecord(phonerecord);
			break;
		case TelephonyManager.CALL_STATE_IDLE:
			if(phonerecord!=null){
				phonerecord.StopRecord();
			}
			break;
		default:
			break;
		}
	}
}
