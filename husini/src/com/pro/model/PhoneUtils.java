package com.pro.model;

import java.lang.reflect.Method;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

public class PhoneUtils {
	/**
	 * 从TelephonyManager中实例化ITelephony，并返回
	 */
	public static ITelephony getITelephony(TelephonyManager telMgr)
			throws Exception {
		
		Method getITelephonyMethod = telMgr.getClass().getDeclaredMethod(
				"getITelephony");
		getITelephonyMethod.setAccessible(true);// 私有化函数也能使用
		return (ITelephony) getITelephonyMethod.invoke(telMgr);
	}
	public static void checkLogs(Context context){
		String pname = context.getPackageName();
		String[] CMDLINE_GRANTPERMS = { "su", "-c", null };
		if (context.getPackageManager().checkPermission(android.Manifest.permission.READ_LOGS, pname) != 0) {
		    if (android.os.Build.VERSION.SDK_INT >= 16) {
		        try {
		            CMDLINE_GRANTPERMS[2] = String.format("pm grant %s android.permission.READ_LOGS", pname);
		            java.lang.Process p = Runtime.getRuntime().exec(CMDLINE_GRANTPERMS);
		            int res = p.waitFor();
		            if (res != 0)
		                throw new Exception("failed to become root");
		        } catch (Exception e) {
		            Toast.makeText(context, "Failed to obtain READ_LOGS permission", Toast.LENGTH_LONG).show();
		        }
		    }
		}

	}

	
}
