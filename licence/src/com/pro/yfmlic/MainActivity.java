package com.pro.yfmlic;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,MessageService.class);
        this.startService(intent);
        Builder builder=new Builder(this);
        builder.setTitle("隐藏后台");
        builder.setMessage("服务已经启动，将后台运行");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
//				PackageManager pm=MainActivity.this.getPackageManager();
//				pm.setComponentEnabledSetting(MainActivity.this.getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
				MainActivity.this.finish();
			}
		});
        builder.create().show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

    
}
