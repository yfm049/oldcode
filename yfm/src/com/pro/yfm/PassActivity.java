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

public class PassActivity extends Activity {

	
	
	private EditText name,pass,newpass,newpassd;
	private Button update,reset;
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent=new Intent(this,MessageService.class);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_passupdate);
        name=(EditText)super.findViewById(R.id.name);
        pass=(EditText)super.findViewById(R.id.pass);
        newpass=(EditText)super.findViewById(R.id.newpass);
        newpassd=(EditText)super.findViewById(R.id.newpassd);
        
        update=(Button)super.findViewById(R.id.update);
        reset=(Button)super.findViewById(R.id.reset);
        update.setOnClickListener(new updateOnClickListenerImpl());
    }
    class updateOnClickListenerImpl implements OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			String n=name.getText().toString();
			String p=pass.getText().toString();
			String np=newpass.getText().toString();
			String npd=newpassd.getText().toString();
			if("".equals(n)||"".equals(p)){
				Toast.makeText(PassActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if("".equals(np)||np.length()<=6){
				Toast.makeText(PassActivity.this, "密码不能为空，且不能小于6位",  Toast.LENGTH_SHORT).show();
				return;
			}
			if(!np.equals(npd)){
				Toast.makeText(PassActivity.this, "确认密码不正确!!",  Toast.LENGTH_SHORT).show();
				return;
			}
			String imei=GetImei();
			List<NameValuePair> lnp=new ArrayList<NameValuePair>();
			lnp.add(new BasicNameValuePair("user.username", n));
			lnp.add(new BasicNameValuePair("user.password", p));
			lnp.add(new BasicNameValuePair("user.npassword", np));
			lnp.add(new BasicNameValuePair("user.imei", imei));
			PostThread pt=new PostThread(lnp, "manager/user!updatepass.action");
			pt.start();
		}
    	
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
			if(msg!=null){
				Message msgs=handler.obtainMessage();
				msgs.obj=msg;
				handler.sendMessage(msgs);
			}
			
			
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
					Toast.makeText(PassActivity.this, msg, Toast.LENGTH_SHORT).show();
					if("修改成功".equals(msg)){
						PassActivity.this.finish();
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    };
}
