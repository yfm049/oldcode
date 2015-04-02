package com.yfm.webservice;

import java.util.Map;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;

public class SoapUtils {

	
	public static final synchronized void Send(Context context,String methodname,Map<String,Object> map,Handler handler,Dialog dialog){
		WsThread ws=new WsThread(methodname, map, handler, dialog);
		ws.start();
	}
}
