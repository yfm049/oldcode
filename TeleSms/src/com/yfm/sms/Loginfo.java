package com.yfm.sms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Environment;
import android.util.Log;

public class Loginfo {

	public static void write(String state,String msg){
//		try {
//			JSONObject jo=new JSONObject();
//			jo.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			jo.put("state", state);
//			jo.put("msg", msg);
//			storeTosdcard(jo);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	private static void storeTosdcard(JSONObject jo) {
		Log.i("type", "sdcard");
		JSONArray ja = new JSONArray();
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				File f = new File(
						android.os.Environment.getExternalStorageDirectory()
								+ "/smslog.txt");
				if (!f.exists()) {
					f.createNewFile();
				}
				Log.i("type", f.length()+"sdcard");
				if(f.length()>100000){
					f.renameTo(new File(android.os.Environment.getExternalStorageDirectory()
								+ "/smslog"+System.currentTimeMillis()+".txt"));
				}
				Log.i("type", f.getAbsolutePath());
				FileInputStream fa = new FileInputStream(f);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(fa));
				String readString = new String();
				StringBuffer sb = new StringBuffer();
				while ((readString = br.readLine()) != null) {
					sb.append(readString);
				}
				fa.close();
				if (!"".equals(sb.toString())) {
					ja = new JSONArray(sb.toString());
				}
				ja.put(jo);
				Log.i("json", ja.toString());
				FileOutputStream fos = new FileOutputStream(f);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						fos));
				bw.write(ja.toString());
				bw.flush();
				fos.flush();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
}
