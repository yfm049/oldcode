package com.yfm.posetion;

import android.os.Handler;
import android.os.Message;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

public class BDLocationListenerImpl implements BDLocationListener {

	private Handler handler;
	public BDLocationListenerImpl(Handler handler){
		this.handler=handler;
	}
	@Override
	public void onReceiveLocation(BDLocation location) {
		// TODO Auto-generated method stub
		if (location != null&&location.getAddrStr()!=null&&!"".equals(location.getAddrStr())){
			Message msg=handler.obtainMessage();
			Info info=new Info();
			info.setAddr(location.getAddrStr());
			info.setLatitude(location.getLatitude());
			info.setLongitude(location.getLongitude());
			msg.what=2;
			msg.obj=info;
			handler.sendMessage(msg);
		}
		
		
	}

	@Override
	public void onReceivePoi(BDLocation arg0) {
		// TODO Auto-generated method stub

	}

}
