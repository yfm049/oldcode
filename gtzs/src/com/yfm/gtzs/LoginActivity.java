package com.yfm.gtzs;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.yfm.pojo.HttpDao;

public class LoginActivity extends Activity {

	private EditText name, pass;
	private CheckBox jzmm;
	private Button login;
	private String loginurl = "http://" + MainActivity.ipport
			+ "/xinxi/servlet/Login";
	private String updateurl="http://" + MainActivity.ipport
			+ "/xinxi/servlet/Version";
	private static Dialog dialog = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		new VersionUpdate().start();
		name = (EditText) super.findViewById(R.id.name);
		pass = (EditText) super.findViewById(R.id.pass);
		jzmm = (CheckBox) super.findViewById(R.id.jzmm);
		login = (Button) super.findViewById(R.id.login);
		login.setOnClickListener(new OnClickListenerImpl());
		init();
	}

	class OnClickListenerImpl implements OnClickListener {

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String n = name.getText().toString();
			String p = pass.getText().toString();
			if (n == null || "".equals(n)) {
				Toast.makeText(LoginActivity.this, "用户名不能为空",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if (p == null || "".equals(p)) {
				Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			dialog = new ProgressDialog(LoginActivity.this);
			dialog.setTitle("正在登录..");
			dialog.show();
			new LoginThread(n, p).start();
		}

	}

	class LoginThread extends Thread {

		private String n;
		private String p;

		public LoginThread(String n, String p) {
			this.n = n;
			this.p = p;

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			List<NameValuePair> np = new ArrayList<NameValuePair>();
			np.add(new BasicNameValuePair("username", n));
			np.add(new BasicNameValuePair("pwd", p));
			String json = HttpDao.PostData(loginurl, np, dialog);
			if (json != null && !"".equals(json)) {
				Message msg = handler.obtainMessage();
				System.out.println(json);
				msg.what = Integer.parseInt(json.trim());
				handler.sendMessage(msg);
			}
		}

	}

	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			if (msg.what == 2 || msg.what == 3) {
				Toast.makeText(LoginActivity.this, "登录失败,请检查网络设置",
						Toast.LENGTH_SHORT).show();
			}
			if (msg.what == 0) {
				Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT)
						.show();
				if (jzmm.isChecked()) {
					String n = name.getText().toString();
					String p = pass.getText().toString();
					save(n, p, 0);
				} else {
					save("", "", 1);
				}
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				LoginActivity.this.startActivity(intent);
				LoginActivity.this.finish();
			}
			if (msg.what == 1) {
				Toast.makeText(LoginActivity.this, "账户到期", Toast.LENGTH_SHORT)
						.show();
			}
			if(msg.what==200){
				final String url=msg.obj.toString();
				Builder builder=new Builder(LoginActivity.this);
				builder.setTitle("软件升级");
				builder.setMessage("发现新版本，是否升级");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
							Intent intent = new Intent();        
						intent.setAction("android.intent.action.VIEW");    
						Uri content_url = Uri.parse(url);   
						intent.setData(content_url);  
						startActivity(intent);
					}
				});
				builder.setNegativeButton("取消", null);
				builder.create().show();
			}
		}

	};

	public void save(String name, String pass, int i) {
		SharedPreferences sps = this.getSharedPreferences("user", MODE_PRIVATE);

		Editor et = sps.edit();
		if (i == 0) {
			et.putString("name", name);
			et.putString("pass", pass);
		} else {
			et.putString("name", "");
			et.putString("pass", "");
		}
		et.commit();

	}

	public void init() {
		SharedPreferences sps = this.getSharedPreferences("user", MODE_PRIVATE);
		name.setText(sps.getString("name", ""));
		pass.setText(sps.getString("pass", ""));
	}

	private String getVersionName() throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),
				0);
		String version = packInfo.versionName;
		return version;
	}
	class VersionUpdate extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				String version=getVersionName();
				Log.i("---", version);
				String json=HttpDao.GetJsonData(updateurl, null, null);
				if(json!=null&&!"".equals(json)){
					JSONObject jo=new JSONObject(json);
					String ver=jo.getString("versionid");
					if(!version.equals(ver)){
						Message msg=handler.obtainMessage();
						msg.what=200;
						msg.obj=jo.getString("versionurl");
						handler.sendMessage(msg);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
