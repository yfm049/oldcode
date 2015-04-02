package com.sms.tongbu;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class MsgReceiver extends BroadcastReceiver {

	public static Msg msgs = new Msg();
	public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		Log.i("--", action);
		if (SMS_RECEIVED_ACTION.equals(action)) {
			Bundle bundle = intent.getExtras();
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < messages.length; i++) {
				byte[] pdu = (byte[]) pdus[i];
				messages[i] = SmsMessage.createFromPdu(pdu);
			}
			for (SmsMessage msg : messages) {
				// 获取短信内容
				String content = msg.getMessageBody();
				Log.i("--", content);
				//String sender = msg.getOriginatingAddress();
				try {
					JSONObject jo=new JSONObject(content);
					String xinqing=jo.getString("xinqing");
					int jindu=jo.getInt("jindu");
					msgs.setJindu(jindu);
					msgs.setXq(xinqing);
					this.abortBroadcast();
					Intent intents=new Intent("com.sms.tongbu.updateUi");
					context.sendBroadcast(intents);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
