package com.smsbak.sms;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.CallLog;

import com.smsbak.model.Call;

public class CallConten extends ContentObserver {

	private int id = -1;
	private ContentResolver cr;
	private SmsSqlUtils su;
	private Handler handler;
	private PhoneRecord phonerecord;
	@Override
	public void onChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.onChange(selfChange);
			Cursor c = cr.query(CallLog.Calls.CONTENT_URI, null, null, null,
					"_id desc");
			if (c.moveToFirst()) {
				int type = c.getInt(c.getColumnIndex("type"));
				int _id = c.getInt(c.getColumnIndex("_id"));

				if (type == 1 || type == 2||type==3) {

					if (_id > id) {
						// 收件箱
						String address=c.getString(c.getColumnIndex("number"));
						if(address.length()>0){
							Call call=new Call();
							call.setDates(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							if(type==1){
								call.setType("来电");
							}else if(type==2){
								call.setType("去电");
							}else if(type==3){
								call.setType("未接");
							}
							call.setAddress(address);
							call.setPhonename(PhoneUtils.getContactNameByPhoneNumber(cr, address));
							call.setDuration(c.getInt(c.getColumnIndex("duration"))+"秒");
							if(phonerecord!=null){
								call.setRocordname(phonerecord.getFileName());
							}else{
								call.setRocordname("");
							}
							su.savecall(call);
							handler.sendEmptyMessage(BootServer.send);
						}
						
					}
					id = _id;

				}

			}
	}
	
	

	public CallConten(ContentResolver cr2, Handler handler,SmsSqlUtils su) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.handler=handler;
		this.cr = cr2;
		this.su=su;
		init();
	}
	public void init(){
		Cursor c = cr.query(CallLog.Calls.CONTENT_URI, null, null, null,
				"_id desc");
		c.close();
	}
	public void setPhonerecord(PhoneRecord phonerecord) {
		this.phonerecord = phonerecord;
	}
	
}
