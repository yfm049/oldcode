package com.pro.yfmlic;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.pro.yfmlic.R;

public class AuthActivity extends Activity {

	private TextView msg;
	private Button tongyi,butongyi;
	private String id;
	private String imei,name,time,phonenum;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.auth);
        Intent intent=this.getIntent();
        id=intent.getStringExtra("id");
        imei=intent.getStringExtra("imei");
        name=intent.getStringExtra("name");
        time=intent.getStringExtra("time");
        phonenum=intent.getStringExtra("phonenum");
        msg=(TextView)super.findViewById(R.id.msg);
        msg.setText(time+","+name+"手机号码为"+phonenum+"申请进入ERP系统,你是否同意?");
        tongyi=(Button)super.findViewById(R.id.tongyi);
        butongyi=(Button)super.findViewById(R.id.butongyi);
        tongyi.setOnClickListener(new tongyiOnClickListenerImpl());
        butongyi.setOnClickListener(new butongyiOnClickListenerImpl());
    }
    class tongyiOnClickListenerImpl implements OnClickListener{

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			List<NameValuePair> lnp=new ArrayList<NameValuePair>();
			lnp.add(new BasicNameValuePair("user.id", id));
			lnp.add(new BasicNameValuePair("user.imei", imei));
			lnp.add(new BasicNameValuePair("user.msg", "true"));
			PostThread pt=new PostThread(lnp, "manager/manage!ToApply.action");
			pt.start();
		}
    	
    }
    class butongyiOnClickListenerImpl implements OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			List<NameValuePair> lnp=new ArrayList<NameValuePair>();
			lnp.add(new BasicNameValuePair("user.id", id));
			lnp.add(new BasicNameValuePair("user.imei", imei));
			lnp.add(new BasicNameValuePair("user.msg", "false"));
			PostThread pt=new PostThread(lnp, "manager/manage!ToApply.action");
			pt.start();
		}
    	
    }


    class PostThread extends Thread{
    	@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			String msg=HttpDao.PostData(url, lnp, null);
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
    Handler handler=new Handler(){

		@Override
		public void handleMessage(Message ms) {
			// TODO Auto-generated method stub
			super.handleMessage(ms);
			try {
				String msg=ms.obj.toString();
				if(msg!=null){
					JSONObject jo=new JSONObject(msg);
					if(!jo.has("msg")){
						return;
					}
					msg=jo.getString("msg");
					Toast.makeText(AuthActivity.this, msg, Toast.LENGTH_SHORT).show();
					AuthActivity.this.finish();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    };
}
