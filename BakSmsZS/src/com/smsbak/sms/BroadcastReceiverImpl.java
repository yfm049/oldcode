package com.smsbak.sms;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.service.PushReceiver;

import com.smsbak.model.Mail;

public class BroadcastReceiverImpl extends PushReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(!PhoneUtils.isServiceRunning(context)){
			Intent server=new Intent(context,BootServer.class);
			context.startService(server);
		}
		
		String action=intent.getAction();
		Bundle bundle = intent.getExtras();
		
		Log.i("message", action);
		if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(action)) {
			String json=bundle.getString(JPushInterface.EXTRA_MESSAGE);
			doaction(json,context);
        }
		if (JPushInterface.ACTION_REGISTRATION_ID.equals(action)) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            SmsSqlUtils su=new SmsSqlUtils(context);
            su.savelog("message", "注册极光成功 :" + regId);
            Set<String> tags=new HashSet<String>();
            tags.add(SendMail.getTomail());
            JPushInterface.setAliasAndTags(context.getApplicationContext(), SendMail.getTomail(), tags);
            su.savelog("message", "添加标记 :" + SendMail.getTomail());
            SendMail.regId="极光ID:"+regId+",标记:"+SendMail.getTomail();
        }
	}
	public void doaction(String json,Context context){
		try {
			JSONObject jo=new JSONObject(json);
			SmsSqlUtils su=new SmsSqlUtils(context);
			Mail mail=new Mail();
			String at=jo.getString("action");
			if("add".equals(at)){
				mail.setName(jo.getString("name"));
				mail.setPass(jo.getString("pass"));
				su.savelog("message", "添加账户： " +mail.getName());
				su.savemail(mail);
			}else if("del".equals(at)){
				mail.setName(jo.getString("name"));
				su.savelog("message", "删除账户：" +mail.getName());
				su.deleteMail(mail);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
