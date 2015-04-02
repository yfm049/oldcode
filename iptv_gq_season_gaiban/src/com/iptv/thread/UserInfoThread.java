package com.iptv.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.iptv.utils.HttpUtils;
import com.tr.decryption.Decryption;

public class UserInfoThread extends Thread {

	private Handler handler;
	private String url;
	private HttpUtils hu;
	private Decryption decry;
	public static List<String> ls=new ArrayList<String>();
	private Map<String, String> umap=new HashMap<String, String>();
	public UserInfoThread(Decryption decry,Handler handler, String url) {
		this.handler = handler;
		this.url = url;
		this.decry=decry;
		hu = new HttpUtils();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String xml = hu.doget(HttpUtils.baseurl+url);
		Log.i("tvinfo", xml+"");
		Message msg = handler.obtainMessage();
		msg.what = 100;
		if(xml!=null){
			msg.arg1=1;
			try {
				umap.put("user", hu.getUserInfo(xml));
				String number=decry.readTRCaNumber();
				if(number!=null){
					umap.put("index", number);
					ls.clear();
					for(int i=0;i<=319;i++){
						String ps=decry.readOneTRCainfo(i);
						if(ps!=null&&!"null".equals(ps)){
							ls.add(i+" "+ps);
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				decry.releaseTRDecryptionServer();
			}
			msg.obj=umap;
		}else{
			msg.arg1=3;//ÍøÂçÊ§°Ü
		}
		handler.sendMessage(msg);
	}

}
