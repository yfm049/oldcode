package com.yfm.webservice;

import java.util.Map;
import java.util.Set;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class WsThread extends Thread {


	private static String namespace="http://tempuri.org/";
	private static String endpoint="http://60.166.34.122/WebService/WebService.asmx";
	private String methodname;
	private Map<String,Object> map;
	private Handler handler;
	private Dialog dialog;
	public WsThread(String methodname,Map<String,Object> map,Handler handler,Dialog dialog){
		this.methodname=methodname;
		this.map=map;
		this.handler=handler;
		this.dialog=dialog;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			SoapObject so=new SoapObject(namespace,methodname);
			if(map!=null){
				Set<String> ss=map.keySet();
				for(String key:ss){
					so.addProperty(key, map.get(key));
					Log.i("canshu", key+"--"+map.get(key));
				}
			}
			SoapSerializationEnvelope sse=new SoapSerializationEnvelope(SoapEnvelope.VER11);
			sse.dotNet=true;
			sse.setOutputSoapObject(so);
			
			HttpTransportSE transport=new HttpTransportSE(endpoint);
			String action=namespace+methodname;
			Log.i("action", action);
			transport.call(action, sse);
			Object res=sse.getResponse();
			if(handler!=null){
				Message msg=handler.obtainMessage();
				msg.what=1;
				msg.obj=res;
				handler.sendMessage(msg);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(handler!=null){
				Message msg=handler.obtainMessage();
				msg.what=2;
				msg.obj="数据获取异常，请检查网络设置";
				handler.sendMessage(msg);
			}
			e.printStackTrace();
		}finally{
			if(dialog!=null){
				dialog.cancel();
			}
		}
	}
	
}
