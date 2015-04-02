package com.yfm.ydbg;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;

import com.yfm.webservice.SoapUtils;
import com.yfm.webservice.SqlUtils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	private EditText account, pw;
	private CheckBox jzmm, zddl;
	private Button login;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		account = (EditText) super.findViewById(R.id.account);
		pw = (EditText) super.findViewById(R.id.pw);
		jzmm = (CheckBox) super.findViewById(R.id.jzmm);
		zddl = (CheckBox) super.findViewById(R.id.zddl);
		login = (Button) super.findViewById(R.id.login);
		login.setOnClickListener(new loginOnClickListener());
		if (isConnect(this) == false) {
			new AlertDialog.Builder(this)
					.setTitle("网络错误")
					.setMessage("网络连接失败，请确认网络连接")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									MainActivity.this.finish();
								}
							}).show();
		}

	}

	public static boolean isConnect(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("error", e.toString());
		}
		return false;
	}

	class loginOnClickListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			login();
		}

	}

	public void login() {
		String ac = account.getText().toString();
		String pass = pw.getText().toString();
		if (!"".equals(ac) && !"".equals(pass)) {
			ProgressDialog pd = new ProgressDialog(this);
			pd.setTitle("正在登录中...");
			pd.setMessage("请稍后");
			pd.show();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("account", ac);
			map.put("pw", pass);
			SoapUtils.Send(this, "GetUserMsg", map, handler, pd);
		} else {
			Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
		}

	}

	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int what = msg.what;
			if (what == 1) {
				Log.i("login", msg.obj.toString());
				if ("true".equals(msg.obj.toString())) {
					Toast.makeText(MainActivity.this, "登陆成功",
							Toast.LENGTH_SHORT).show();
					String ac = account.getText().toString();
					String pass = pw.getText().toString();
					FunActivity.account = ac;
					FunActivity.pass = pass;
					SqlUtils su = new SqlUtils(MainActivity.this);
					su.insertuser(ac, pass);
					Intent intent = new Intent(MainActivity.this,
							FunActivity.class);
					MainActivity.this.startActivity(intent);
					MainActivity.this.finish();
				} else {
					Toast.makeText(MainActivity.this, "登陆失败，请检查用户名和密码",
							Toast.LENGTH_SHORT).show();
				}
			} else if (what == 2) {
				Toast.makeText(MainActivity.this, msg.obj.toString(),
						Toast.LENGTH_SHORT).show();
			}

		}

	};

}
