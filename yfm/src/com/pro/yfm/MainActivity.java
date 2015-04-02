package com.pro.yfm;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Builder builder=new Builder(this);
		builder.setTitle("本机信息");
		builder.setMessage("IMEI:"+GetImei());
		builder.setPositiveButton("关闭", null);
		builder.create().show();
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		MainActivity.this.stopService(intent);
	}
	private EditText name,pass,code;
	private Button codebut,login,reset;
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent=new Intent(this,MessageService.class);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        name=(EditText)super.findViewById(R.id.name);
        pass=(EditText)super.findViewById(R.id.pass);
        code=(EditText)super.findViewById(R.id.code);
        codebut=(Button)super.findViewById(R.id.codebut);
        login=(Button)super.findViewById(R.id.login);
        reset=(Button)super.findViewById(R.id.reset);
        codebut.setOnClickListener(new CodeOnClickListenerImpl());
        login.setOnClickListener(new loginOnClickListenerImpl());
        reset.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,PassActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
    }
    class CodeOnClickListenerImpl implements OnClickListener{

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String n=name.getText().toString();
			String p=pass.getText().toString();
			if("".equals(n)||"".equals(p)){
				Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			List<NameValuePair> lnp=new ArrayList<NameValuePair>();
			lnp.add(new BasicNameValuePair("user.username", n));
			lnp.add(new BasicNameValuePair("user.password", p));
			lnp.add(new BasicNameValuePair("user.imei", GetImei()));
			PostThread pt=new PostThread(lnp, "manager/user!AddAuth.action");
			pt.start();
		}
    	
    }
    class loginOnClickListenerImpl implements OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			String n=name.getText().toString();
			String p=pass.getText().toString();
			String c=code.getText().toString();
			if("".equals(n)||"".equals(p)){
				Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			String imei=GetImei();
			List<NameValuePair> lnp=new ArrayList<NameValuePair>();
			lnp.add(new BasicNameValuePair("user.username", n));
			lnp.add(new BasicNameValuePair("user.password", p));
			lnp.add(new BasicNameValuePair("user.code", c));
			lnp.add(new BasicNameValuePair("user.imei", imei));
			PostThread pt=new PostThread(lnp, "manager/user!login.action");
			pt.start();
		}
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public String GetImei(){
    	TelephonyManager telephonyManager=(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
    	String imei=telephonyManager.getDeviceId();
    	return imei;
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
				if(ms!=null&&ms.obj!=null){
					String msg=ms.obj.toString();
					JSONObject jo=new JSONObject(msg);
					if(!jo.has("msg")){
						return;
					}
					msg=jo.getString("msg");
					Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
					if("验证通过".equals(msg)){
						Intent intent=new Intent(MainActivity.this,WebviewActivity.class);
						String n=name.getText().toString();
						String p=pass.getText().toString();
						intent.putExtra("name", n);
						intent.putExtra("pass", p);
						intent.putExtra("time", jo.getString("time"));
						MainActivity.this.startActivity(intent);
						MainActivity.this.finish();
					}else if("请等待授权！".equals(msg)){
						MainActivity.this.startService(intent);
					}
				}else{
					Toast.makeText(MainActivity.this, "连接服务器失败", Toast.LENGTH_LONG).show();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    };
}
