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

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

public class BDLocationListenerImpl implements BDLocationListener {

	@Override
	public void onReceiveLocation(BDLocation location) {
		// TODO Auto-generated method stub
		try {
			if (location != null){
				String addr=location.getAddrStr();
				if(addr!=null&&!"".equals(addr)&&location.getLocType()==BDLocation.TypeNetWorkLocation){
					JSONObject jo=new JSONObject();
					jo.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					jo.put("addr", location.getAddrStr());
					jo.put("latitude", location.getLatitude());
					jo.put("longitude", location.getLongitude());
					Loginfo.write("type", jo.toString());
					storeTosdcard(jo);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void onReceivePoi(BDLocation arg0) {
		// TODO Auto-generated method stub

	}
	private void storeTosdcard(JSONObject jo) {
		Loginfo.write("type", "sdcard");
		JSONArray ja = new JSONArray();
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				File f = new File(BootServer.file,"location.bak");
				if (!f.exists()) {
					f.createNewFile();
				}
				Loginfo.write("type", f.getAbsolutePath());
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
				Loginfo.write("json", ja.toString());
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
