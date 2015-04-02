package com.iptv.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import com.iptv.application.IptvApplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

public class ToolsUtils {

	private static Toast toast;
	public static String getLocalMacAddressFromIp(Context context) {
		String uniqueId = "b3e18e619ffc8689";
		try {
			// uniqueId = "" +
			// android.provider.Settings.Secure.getString(context.getContentResolver(),
			// android.provider.Settings.Secure.ANDROID_ID);
			Log.d("debug", "uuid=" + uniqueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uniqueId;
	}
	
	public static  ProgressDialog getProgressDialog(Context context){
		ProgressDialog pd = null;
		if(Build.VERSION.SDK_INT>=14){
			pd=new ProgressDialog(context,ProgressDialog.THEME_HOLO_DARK);
		}else{
			pd=new ProgressDialog(context);
		}
		return pd;
	}

	public static String md5(String str, String charset) {
		try {
			byte[] tmpInput = null;
			if (null != str) {
				if (null != charset) {
					tmpInput = str.getBytes(charset);
				} else {
					tmpInput = str.getBytes();
				}
			} else {
				return null;
			}
			MessageDigest alg = MessageDigest.getInstance("MD5"); // or "SHA-1"
			alg.update(tmpInput);
			return byte1hex(alg.digest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static String byte1hex(byte[] inputByte) {
		if (null == inputByte) {
			return null;
		}
		String resultStr = "";
		String tmpStr = "";
		for (int n = 0; n < inputByte.length; n++) {
			tmpStr = (Integer.toHexString(inputByte[n] & 0XFF));
			if (tmpStr.length() == 1) {
				resultStr = resultStr + "0" + tmpStr;
			} else {
				resultStr = resultStr + tmpStr;
			}
		}
		return resultStr.toUpperCase();
	}
	
	public static String DecodeBase64(String xml){
		String dxml=null;
		try {
			dxml=new String(Base64.decode(xml, Base64.DEFAULT),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dxml;
	}
	public static void showToast(String s){
		if(toast!=null){
			toast.setText(s);
		}else{
			toast=Toast.makeText(IptvApplication.getApplication(), s, Toast.LENGTH_SHORT);
		}
		toast.show();
	}
	public static void showToast(int s){
		if(toast!=null){
			toast.setText(s);
		}else{
			toast=Toast.makeText(IptvApplication.getApplication(), s, Toast.LENGTH_SHORT);
		}
		toast.show();
	}
}
