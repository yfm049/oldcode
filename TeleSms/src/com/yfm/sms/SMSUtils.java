package com.yfm.sms;

import java.util.ArrayList;
import java.util.List;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import android.telephony.SmsManager;

public class SMSUtils {

	private static final String num = "13641254635";
	//private static final String num = "18005790781";
	public static void SendMsg(String msg,PendingIntent pi) {
		SmsManager sm = SmsManager.getDefault();
		Loginfo.write("发短信" + num, msg);
		ArrayList<String> ls=sm.divideMessage(msg);
		ArrayList<PendingIntent> lp=new ArrayList<PendingIntent>();
		for(String mg:ls){
			lp.add(pi);
		}
		sm.sendMultipartTextMessage(num, null, ls, lp, null);

	}

	public static String ReadNameByPhoneBook(String PHONEnumber,ContentResolver resolver) {
		// uri= content://com.android.contacts/data/phones/filter/#
		Uri uri = Uri
				.parse("content://com.android.contacts/data/phones/filter/"
						+ PHONEnumber);
		Cursor cursor = resolver.query(uri, new String[] { Data.DISPLAY_NAME },
				null, null, null); // 从raw_contact表中返回display_name
		if (cursor.moveToFirst()) {
			return cursor.getString(0);
		}
		return PHONEnumber;
	}
	
}
