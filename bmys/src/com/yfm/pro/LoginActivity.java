package com.yfm.pro;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.ActivityGroup;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.yfm.net.HttpDao;

public class LoginActivity extends ActivityGroup {


	private EditText name,pass;
	private Button login,reg;
	private CheckBox jzmm;
	private String url="/MobApp/Logon";
	public ProgressDialog pd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        name=(EditText)super.findViewById(R.id.name);
        pass=(EditText)super.findViewById(R.id.pass);
        login=(Button)super.findViewById(R.id.login);
        reg=(Button)super.findViewById(R.id.reg);
        jzmm=(CheckBox)super.findViewById(R.id.jzmm);
        login.setOnClickListener(new OnClickListenerImpl());
        reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,RegActivity.class);
				LoginActivity.this.startActivity(intent);
			}
		});
        init();
    }
    class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Message msg=handler.obtainMessage();
    		String nm=name.getText().toString();
			String ps=pass.getText().toString();
			if("".equals(nm)||"".equals(ps)){
				msg.obj="用户名或密码不能为空!";
				handler.sendMessage(msg);
				return;
			}
			pd=new ProgressDialog(LoginActivity.this);
			pd.setTitle("请求中");
			pd.setMessage("正在努力为你登陆中，请稍后...");
			pd.show();
			logthread ld=new logthread(pd);
			ld.start();
		}
    	
    }
    class logthread extends Thread{
    	@Override
		public void run() {
			// TODO Auto-generated method stub
    		Message msg=handler.obtainMessage();
    		String nm=name.getText().toString();
			String ps=pass.getText().toString();
			if(!"".equals(nm)&&!"".equals(ps)){
				List<NameValuePair> np=new ArrayList<NameValuePair>();
				np.add(new BasicNameValuePair("name", nm));
				np.add(new BasicNameValuePair("pwd", ps));
				String p=HttpDao.PostData(url, np, dialog);
				if(p!=null){
					int i=Integer.parseInt(p);
					if(i==-1){
						msg.obj="未通过短信验证";
						handler.sendMessage(msg);
					}
					if(i==0){
						msg.obj="账号或密码错误";
						handler.sendMessage(msg);
					}
					
					if(i>0){
						msg.obj="登陆成功";
						handler.sendMessage(msg);
						MainActivity.cid=i;
						MainActivity.name=nm;
						MainActivity.pass=ps;
						if(jzmm.isChecked()){
							LoginActivity.this.save(nm,ps,p,0);
						}else{
							LoginActivity.this.save(nm,ps,p,1);
						}
						LoginActivity.this.finish();
					}
				}
				
			}else{
				msg.obj="用户名或密码不能为空!";
				handler.sendMessage(msg);
			}
		}
    	private Dialog dialog;
    	public logthread(Dialog dialog){
    		this.dialog=dialog;
    	}
    }
    private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(pd!=null){
				pd.cancel();
			}
			Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
			
		}
    	
    };
    public void save(String name,String pass,String cid,int i){
    	SharedPreferences sps=this.getSharedPreferences("user", MODE_PRIVATE);
    	
    	Editor et=sps.edit();
    	if(i==0){
    		et.putString("name", name);
        	et.putString("pass", pass);
        	et.putInt("cid", Integer.parseInt(cid));
    	}else{
    		et.putString("name", "");
        	et.putString("pass", "");
        	et.putInt("cid", -1);
    	}
    	et.commit();
    	
    	
    }
    public void init(){
    	SharedPreferences sps=this.getSharedPreferences("user", MODE_PRIVATE);
    	name.setText(sps.getString("name", ""));
    	pass.setText(sps.getString("pass", ""));
    }

}
