package com.yfm.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yfm.pojo.Good;
import com.yfm.pro.DetailActivity;
import com.yfm.pro.LoginActivity;
import com.yfm.pro.MainActivity;
import com.yfm.pro.RegActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class OnClickListenerImpl implements OnClickListener {

	private Context context;
	private int i;
	private Good gd;

	public OnClickListenerImpl(Context context, int i, Good gd) {
		this.context = context;
		this.i = i;
		this.gd = gd;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (i == 1) {
			if (MainActivity.cid <= 0) {
				Intent intent=new Intent(context,LoginActivity.class);
				context.startActivity(intent);
			} else {
				List<NameValuePair> lnp = new ArrayList<NameValuePair>();
				lnp.add(new BasicNameValuePair("id", gd.getId()));
				lnp.add(new BasicNameValuePair("cid", String
						.valueOf(MainActivity.cid)));
				lnp.add(new BasicNameValuePair("username", MainActivity.name));
				lnp.add(new BasicNameValuePair("userpwd", MainActivity.pass));
				System.out.println("id:"+gd.getId()+"--cid:"+MainActivity.cid);
				ProgressDialog dialog=new ProgressDialog(context);
				dialog.setTitle("正在请求中...");
				Log.i("--", "正在加载...");
				dialog.show();
				Getdata gda=new Getdata(gd.getUrl(), lnp, dialog);
				gda.start();
			}
		}else if(i == 2){
			Intent intent=new Intent(context,DetailActivity.class);
			intent.putExtra("good", gd);
			context.startActivity(intent);
		}
	}
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			String p=msg.obj.toString();
			if("1".equals(p)){
				Builder builder=new Builder(context);
				builder.setTitle("提示");
				builder.setMessage("恭喜您成功完成预定，请注意查收短信！");
				builder.setPositiveButton("确定", null);
				builder.create().show();
			}else if("-1".equals(p)){
				Builder builder=new Builder(context);
				builder.setTitle("提示");
				builder.setMessage("账号或密码错误，请退出软件重新登陆");
				builder.setPositiveButton("确定", null);
				builder.create().show();
			}else{
				Builder builder=new Builder(context);
				builder.setTitle("提示");
				builder.setMessage("对不起您刚才的操作失败，请电话联系我们或电脑登录我们的网上商城完成操作！");
				builder.setPositiveButton("确定", null);
				builder.create().show();
			}
		}
		
	};

	class Getdata extends Thread{
		private Dialog dialog;
		private String url;
		private List<NameValuePair> lnp;
		public Getdata(String url,List<NameValuePair> lnp,Dialog dialog){
			this.url=url;
			this.dialog=dialog;
			this.lnp=lnp;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
						String p=HttpDao.PostData(url, lnp, dialog);
						Message msg=handler.obtainMessage();
						msg.obj=p;
						handler.sendMessage(msg);
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
