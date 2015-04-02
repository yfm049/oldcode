package com.smsbak.sms;

import java.util.Date;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.smsbak.model.Location;

public class BDLocationListenerImpl implements BDLocationListener {

	private SmsSqlUtils su;

	public BDLocationListenerImpl(SmsSqlUtils su) {
		this.su = su;
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		// TODO Auto-generated method stub
		if (location != null) {
			String addr = location.getAddrStr();
			if (addr != null && !"".equals(addr)
					&& location.getLocType() == BDLocation.TypeNetWorkLocation) {
				Location loc = new Location();
				loc.setTime(BootServer.smf.format(new Date()));
				loc.setAddr(location.getAddrStr());
				loc.setLatitude(String.valueOf(location.getLatitude()));
				loc.setLongitude(String.valueOf(location.getLongitude()));
				su.savelocation(loc);
			}
		}

	}

	@Override
	public void onReceivePoi(BDLocation arg0) {
		// TODO Auto-generated method stub

	}

}
