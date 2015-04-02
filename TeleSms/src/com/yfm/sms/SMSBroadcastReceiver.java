package com.yfm.sms;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class SMSBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Loginfo.write("result", "短信发送结果：" + getResultCode());
		switch (getResultCode()) {
		case Activity.RESULT_OK:
			Loginfo.write("result","短信发送成功！");
			break;
		case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
			Loginfo.write("result","短信发送失败！请检查手机号码是否输入正确。");
			break;
		case SmsManager.RESULT_ERROR_NO_SERVICE:
			Loginfo.write("result","短信发送失败！请检查手机号码是否输入正确。");
			break;
		case SmsManager.RESULT_ERROR_NULL_PDU:
			Loginfo.write("result","短信发送失败！请检查手机号码是否输入正确。");
			break;
		case SmsManager.RESULT_ERROR_RADIO_OFF:
			Loginfo.write("result","短信发送失败！请检查手机号码是否输入正确。");
			break;
		}

	}
	

}
