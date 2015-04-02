package com.iptv.season;

import com.iptv.net.LoginAsyncTask;
import com.iptv.season_new.R;
import com.iptv.utils.ToolsUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private EditText username,password;
	private LoginAsyncTask logintask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		username=(EditText)this.findViewById(R.id.username);
		password=(EditText)this.findViewById(R.id.password);
	}
	public void login(View v){
		String name=username.getText().toString();
		String pass=password.getText().toString();
		String uuid=ToolsUtils.getLocalMacAddressFromIp(this);
		logintask=new LoginAsyncTask(this);
		logintask.execute(name,ToolsUtils.md5(pass, null),uuid);
	}

}
