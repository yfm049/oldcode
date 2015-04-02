package com.iptv.thread;

import java.text.DecimalFormat;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import android.content.Context;
import android.os.Handler;

import com.iptv.pojo.TvInfo;
import com.iptv.utils.HttpUtils;

public class NetSpeedThread extends Thread {

	private HttpUtils hu;
	private TvInfo tvinfo;
	private Handler handler;
	private boolean run = true;
	private double downcount=0;
	private int tms=0;
	private DecimalFormat df=new DecimalFormat("#.00"); 
	
	public NetSpeedThread(Context context, TvInfo info, Handler handler) {
		hu = new HttpUtils(context);
		this.tvinfo = info;
		this.handler = handler;
	}
	
	public void reset(TvInfo info){
		this.tvinfo=info;
		tms=0;
		downcount=0;
	}
	public void stopsp(){
		run=false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String xml=null;
		
			while (run) {
				try {
				String url=tvinfo.getspeedurl();
				xml = hu.doget(url);
				if (xml != null) {
					Document doc=DocumentHelper.parseText(xml);
					Element ele=doc.getRootElement();
					Element channel=ele.element("channel");
					Element download=channel.element("download");
					String flowbyte=download.attributeValue("flowbyte");
					
					String flowkbs=download.attributeValue("flowkbps");
					
					double fb=Double.parseDouble(flowkbs);
					if(handler!=null){
						double sp=fb/8;
						handler.sendMessage(handler.obtainMessage(2098, df.format(sp)+"kb/s"));
					}
				}
				if(tms>30000&&downcount<1024&&handler!=null){
					handler.sendEmptyMessage(2099);
				}
				
				tms=tms+1000;
				Thread.sleep(1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		
	}

}
