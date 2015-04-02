package com.yfm.control;

import android.content.Context;
import android.os.Handler;

public class ReciverMsg extends Thread {

	
	private Context context;
	private Handler handler;
	public ReciverMsg(Context context,Handler handler){
		this.context=context;
		this.handler=handler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
}
