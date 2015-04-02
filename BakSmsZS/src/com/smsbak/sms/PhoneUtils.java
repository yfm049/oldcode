package com.smsbak.sms;

import java.io.DataOutputStream;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

public class PhoneUtils {

	
	public static String getContactNameByPhoneNumber(ContentResolver cr,
			String address) {
		String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER };
		if (address != null && address.startsWith("+86")) {
			address = address.substring(3);
		}
		// 将自己添加到 msPeers 中
		Cursor cursor = cr.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, // Which
																				// columns
																				// to
																				// return.
				ContactsContract.CommonDataKinds.Phone.NUMBER + " like '%"
						+ address + "%'", // WHERE clause.
				null, // WHERE clause value substitution
				null); // Sort order.

		if (cursor == null) {
			Log.d("phone", "getPeople null");
			return "";
		}
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);

			// 取得联系人名字
			int nameFieldColumnIndex = cursor
					.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
			String name = cursor.getString(nameFieldColumnIndex);
			return name;
		}
		return "";
	}
	public static void SendMailBroadcast(Context context){
		Intent send = new Intent("com.yfm.sms.sendmail");
		context.sendBroadcast(send);
	}
	
	public static boolean isServiceRunning(Context context) {
	    ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if ("com.smsbak.sms.BootServer".equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	public static boolean checkAppExist(Context context,String packageName) {
		if (packageName == null || "".equals(packageName))
			return false;
		try {
			ApplicationInfo info = context.getPackageManager().getApplicationInfo(
					packageName, PackageManager.GET_UNINSTALLED_PACKAGES);

			return true;
		} catch (NameNotFoundException e) {
			
			return false;
		}
	}
	public static boolean RootCommand(String command) {
		Process process = null;
		DataOutputStream os = null;
		try {
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(command + "\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
			return false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
		Log.d("*** DEBUG ***", "Root SUC ");
		return true;
	}
}
