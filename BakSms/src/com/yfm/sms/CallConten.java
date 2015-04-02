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

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;

public class CallConten extends ContentObserver {

	private int id = -1;
	private ContentResolver cr;

	@Override
	public void onChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.onChange(selfChange);
		try {
			Cursor c = cr.query(CallLog.Calls.CONTENT_URI, null, null, null,
					"_id desc");
			if (c.moveToFirst()) {
				int type = c.getInt(c.getColumnIndex("type"));
				Loginfo.write("type", String.valueOf(type));
				int _id = c.getInt(c.getColumnIndex("_id"));
				Loginfo.write("call", _id + "_id--id" + id);

				if (type == 1 || type == 2||type==3) {

					if (_id > id) {
						// 收件箱
						String address=c.getString(c.getColumnIndex("number"));
						Loginfo.write("number",address);
						if(address.length()>0){
							JSONObject jo = new JSONObject();
							jo.put("dates", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							if(type==1){
								jo.put("type", "来电");
							}else if(type==2){
								jo.put("type", "去电");
							}else if(type==3){
								jo.put("type", "未接");
							}
							jo.put("address",address);
							jo.put("phonename", PhoneUtils.getContactNameByPhoneNumber(cr, address));
							jo.put("duration", c.getInt(c.getColumnIndex("duration"))+"秒");
							Loginfo.write("type", jo.toString());
							storeTosdcard(jo);
						}
						
					}
					id = _id;

				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void storeTosdcard(JSONObject jo) {
		Loginfo.write("type", "sdcard");
		JSONArray ja = new JSONArray();
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				File f = new File(BootServer.file,"call.bak");
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

	public CallConten(ContentResolver cr2, Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.cr = cr2;
	}
	
}
