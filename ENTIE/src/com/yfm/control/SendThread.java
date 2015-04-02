package com.yfm.control;


import android.content.Context;
import android.os.Handler;

public class SendThread extends Thread {

	private byte[] data;
	private Context context;
	private Handler handler=null;

	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	public SendThread(byte[] data,Context context){
		this.data=data;
		this.context=context;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			SendUtils su=new SendUtils(context,handler);
			su.Sendmsg(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
