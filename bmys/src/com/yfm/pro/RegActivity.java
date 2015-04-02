package com.yfm.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.ActivityGroup;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yfm.net.HttpDao;

public class RegActivity extends ActivityGroup {

	private EditText name, pass, passd, email, phonenum, phonecode;
	private Button quxiao, regbut, code;
	private String url = "/MobApp/GetVCode";
	private String rurl = "/MobApp/registe";
	public ProgressDialog pd;
	TimeCount time;
	String text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reg);
		name = (EditText) super.findViewById(R.id.name);
		pass = (EditText) super.findViewById(R.id.pass);
		passd = (EditText) super.findViewById(R.id.passd);
		email = (EditText) super.findViewById(R.id.email);
		phonenum = (EditText) super.findViewById(R.id.phonenum);
		phonecode = (EditText) super.findViewById(R.id.phonecode);

		quxiao = (Button) super.findViewById(R.id.quxiao);
		quxiao.setOnClickListener(new OnClickListenerImpl(3));
		code = (Button) super.findViewById(R.id.code);
		code.setOnClickListener(new OnClickListenerImpl(1));
		regbut = (Button) super.findViewById(R.id.regbut);
		regbut.setOnClickListener(new OnClickListenerImpl(2));
		text=code.getText().toString();
	}

	public static String codes = null;

	class OnClickListenerImpl implements OnClickListener {
		private int i;

		public OnClickListenerImpl(int i) {
			this.i = i;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (i == 1 || i == 2) {
				Message msg = handler.obtainMessage();
				String nm = name.getText().toString();
				String ps = pass.getText().toString();
				String pd = passd.getText().toString();
				String emails = email.getText().toString();
				String phonenums = phonenum.getText().toString();
				String phonecodes = phonecode.getText().toString();
				if ("".equals(nm)) {
					msg.obj = "用户名不能为空";
					handler.sendMessage(msg);
					return;
				}
				if (nm.length() < 6) {
					msg.obj = "用户名至少6位";
					handler.sendMessage(msg);
					return;
				}
				if ("".equals(ps)) {
					msg.obj = "密码不能为空";
					handler.sendMessage(msg);
					return;
				}
				if (ps.length() < 6) {
					msg.obj = "密码至少6位";
					handler.sendMessage(msg);
					return;
				}
				if (!pd.equals(ps)) {
					msg.obj = "确认密码不正确";
					handler.sendMessage(msg);
					return;
				}
				if(emails==null||"".equals(emails)){
					msg.obj = "真实姓名不能为空";
					handler.sendMessage(msg);
					return;
				}
				Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
				Matcher m = p.matcher(phonenums);  
				if (!m.matches()) {
					msg.obj = "手机号格式不正确";
					handler.sendMessage(msg);
					return;
				}
				if(i==1){
					time = new TimeCount(60000, 1000);
					time.start();
					
				}
				if(i==2&&"".equals(phonecodes)){
					msg.obj = "验证码不能为空";
					handler.sendMessage(msg);
					return;
				}
			}
			if (i == 3) {
				RegActivity.this.finish();
			}
			pd = new ProgressDialog(RegActivity.this);
			pd.setTitle("正在处理");
			pd.setMessage("正在努力请求中,请稍后");
			pd.show();
			regthread rg = new regthread(i, pd);
			rg.start();
		}

	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (pd != null) {
				pd.cancel();
			}
			if(msg.what==2){
				code.setEnabled(false);
				code.setText(msg.obj.toString());
				return;
			}
			if(msg.what==3){
				code.setText(msg.obj.toString());
				code.setEnabled(true);
				time.cancel();
				return;
			}
			if(msg.what==100){
				Builder builder=new Builder(RegActivity.this);
				builder.setTitle("提示");
				builder.setMessage(msg.obj.toString());
				builder.setPositiveButton("确定", null);
				builder.create().show();
			}else{
				Toast.makeText(RegActivity.this, msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
			}
			
			
		}

	};

	class regthread extends Thread {
		private int i;
		private Dialog dialog;

		public regthread(int i, Dialog dialog) {
			this.i = i;
			this.dialog = dialog;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			String nm = name.getText().toString();
			String ps = pass.getText().toString();
			String pd = passd.getText().toString();
			String emails = email.getText().toString();
			String phonenums = phonenum.getText().toString();
			String phonecodes = phonecode.getText().toString();
			
			if (i == 1) {
				
				List<NameValuePair> np = new ArrayList<NameValuePair>();
				np.add(new BasicNameValuePair("name", nm));
				np.add(new BasicNameValuePair("tel", phonenums));
				np.add(new BasicNameValuePair("smid", MainActivity.imei));
				String p = HttpDao.PostData(url, np, dialog);
				if (p != null) {
					int i = Integer.parseInt(p);
					if (i == 0) {
						msg.obj = "用户名已存在";
						handler.sendMessage(msg);
						codes = null;
						Message msgq = new Message();
						msgq.what=3;
						msgq.obj=text;
						handler.sendMessage(msgq);
					} else if(i==-1){
						Log.i("p", p + "");
						msg.obj = "软件无法获得有效标示，请退出软件重新注册";
						msg.what=100;
						handler.sendMessage(msg);
					}else {
						Log.i("p", p + "");
						msg.obj = "验证码已经发送到您注册填写的手机上，请注意查收！";
						msg.what=100;
						handler.sendMessage(msg);
						codes = p;
					}
				} else {
					msg.obj = "对不起您刚才的操作失败，请电话联系我们或电脑登录我们的网上商城完成操作！";
					msg.what=100;
					handler.sendMessage(msg);
					codes = null;
					Message msgq = new Message();
					msgq.what=3;
					msgq.obj=text;
					handler.sendMessage(msgq);
				}

			} else if (i == 2) {
				
				if (phonecodes.equals(codes)) {
					List<NameValuePair> np = new ArrayList<NameValuePair>();
					np.add(new BasicNameValuePair("name", nm));
					np.add(new BasicNameValuePair("pwd", ps));
					np.add(new BasicNameValuePair("tel", phonenums));
					np.add(new BasicNameValuePair("tname", emails));
					np.add(new BasicNameValuePair("smid", MainActivity.imei));
					np.add(new BasicNameValuePair("vcode", phonecodes));
					String p = HttpDao.PostData(rurl, np, dialog);
					if (p != null) {
						int i = Integer.parseInt(p);
						if (i == 0) {
							msg.obj = "注册失败";
							handler.sendMessage(msg);
							codes = null;
						} else if(i==-3){ 
							msg.obj = "验证码错误或失效，请重新获得验证码";
							msg.what=100;
							handler.sendMessage(msg);
						}else {
							msg.obj = "注册成功";
							handler.sendMessage(msg);
							RegActivity.this.finish();
						}
					}
				} else {
					dialog.cancel();
					msg.obj = "验证码错误";
					handler.sendMessage(msg);
				}
			} else if (i == 3) {
				dialog.cancel();
				RegActivity.this.finish();
			}
		}

	}

	public void save(String name, String pass, String cid, int i) {
		SharedPreferences sps = this.getSharedPreferences("user", MODE_PRIVATE);

		Editor et = sps.edit();
		if (i == 0) {
			et.putString("name", name);
			et.putString("pass", pass);
			et.putString("cid", cid);
		} else {
			et.putString("name", "");
			et.putString("pass", "");
			et.putString("cid", "-1");
		}
		et.commit();

	}

	public void init() {
		SharedPreferences sps = this.getSharedPreferences("user", MODE_PRIVATE);
		name.setText(sps.getString("name", ""));
		pass.setText(sps.getString("pass", ""));
	}
	class TimeCount extends CountDownTimer{

		
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
			
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			
			Message msg=handler.obtainMessage();
			msg.what=3;
			msg.obj=text;
			handler.sendMessage(msg);
			
		}

		@Override
		public void onTick(long arg0) {
			// TODO Auto-generated method stub
			Message msg=handler.obtainMessage();
			msg.what=2;
			msg.obj=arg0/1000+"秒";
			handler.sendMessage(msg);
			
		}
		
	}
}
