package com.yfm.pro;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.ActivityGroup;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yfm.adapter.PhoneAdapter;
import com.yfm.net.HttpDao;
import com.yfm.pojo.Phone;

public class TouSuActivity extends ActivityGroup {

	private TextView tstext;
	private Button ts,qx;
	private String url="/MobApp/AddConsultation";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tousu);
		tstext=(TextView)super.findViewById(R.id.tstext);
		ts=(Button)super.findViewById(R.id.ts);
		qx=(Button)super.findViewById(R.id.qx);
		ts.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if("".equals(tstext.getText().toString())){
					Toast.makeText(TouSuActivity.this, "投诉内容不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				List<NameValuePair> lnp = new ArrayList<NameValuePair>();
				lnp.add(new BasicNameValuePair("username", MainActivity.name));
				lnp.add(new BasicNameValuePair("userpwd", MainActivity.pass));
				lnp.add(new BasicNameValuePair("CID", String
						.valueOf(MainActivity.cid)));
				lnp.add(new BasicNameValuePair("Content", tstext.getText().toString()));
				pd = new ProgressDialog(TouSuActivity.this);
				pd.setTitle("正在处理");
				pd.setMessage("正在努力请求中,请稍后");
				pd.show();
				PostThread pt=new PostThread(lnp, url);
				pt.start();
				
				
			}
		});
		qx.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TouSuActivity.this.finish();
			}
		});
		
	}
	class PostThread extends Thread{
    	@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			String msg=HttpDao.PostData(url, lnp, pd);
			Message msgs=handler.obtainMessage();
			msgs.obj=msg;
			handler.sendMessage(msgs);
			
		}
		private List<NameValuePair> lnp;
		private String url;
    	public PostThread(List<NameValuePair> lnp,String url){
    		this.lnp=lnp;
    		this.url=url;
    	}
    }
	public ProgressDialog pd;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if("1".equals(msg.obj)){
				Builder builder=new Builder(TouSuActivity.this);
				builder.setTitle("提示");
				builder.setMessage("感谢您对我们的工作提出建议和意见，我们将马上处理您的问题！如果我们的工作给您造成不便敬请谅解！");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						TouSuActivity.this.finish();
					}
				});
				builder.create().show();
				
			}else{
				Builder builder=new Builder(TouSuActivity.this);
				builder.setTitle("提示");
				builder.setMessage("对不起您刚才的操作失败，请电话联系我们或电脑登录我们的网上商城完成操作！");
				builder.setPositiveButton("确定", null);
				builder.create().show();
			}
			
			
			
			
		}

	};
	
}
