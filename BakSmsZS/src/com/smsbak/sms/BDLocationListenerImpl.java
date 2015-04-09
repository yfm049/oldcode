package com.smsbak.sms;

import java.util.Date;

import android.content.Context;
import android.content.Intent;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.smsbak.model.Location;

public class BDLocationListenerImpl implements BDLocationListener {

	private SmsSqlUtils su;
	private Context context;

	public BDLocationListenerImpl(Context context,SmsSqlUtils su) {
		this.su = su;
		this.context=context;
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		// TODO Auto-generated method stub
		if (location != null) {
			String addr = location.getAddrStr();
			if (addr != null && !"".equals(addr)) {
				Location loc = new Location();
				loc.setTime(BootServer.smf.format(new Date()));
				loc.setAddr(location.getAddrStr());
				loc.setLatitude(String.valueOf(location.getLatitude()));
				loc.setLongitude(String.valueOf(location.getLongitude()));
				su.savelocation(loc);
			}
			if(BootServer.isrequest){
				Intent send = new Intent("com.yfm.sms.sendmail");
				context.sendBroadcast(send);
			}
		}

	}

	
	

}
