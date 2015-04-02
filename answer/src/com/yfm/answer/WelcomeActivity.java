package com.yfm.answer;




import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeActivity extends Activity {

	private Button phone,yctb,jhsb,exit;
	private DevicePolicyManager policyManager; 
	private ComponentName componentName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE); 
	    componentName = new ComponentName(this.getApplicationContext(), LockReceiver.class); 
	       
		phone=(Button)super.findViewById(R.id.phone);
		yctb=(Button)super.findViewById(R.id.yctb);
		jhsb=(Button)super.findViewById(R.id.jhsb);
		exit=(Button)super.findViewById(R.id.exit);
		phone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				WelcomeActivity.this.startActivity(intent);
			}
		});
		yctb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PackageManager pm=WelcomeActivity.this.getPackageManager();
				pm.setComponentEnabledSetting(WelcomeActivity.this.getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
				WelcomeActivity.this.finish();
			}
		});
		jhsb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(policyManager.isAdminActive(componentName)){
					Toast.makeText(WelcomeActivity.this, "设备已经激活", Toast.LENGTH_SHORT).show();
				}else{
					activeManager();
				}
			}
		});
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WelcomeActivity.this.finish();
			}
		});
	}
	private void activeManager() { 
        //使用隐式意图调用系统方法来激活指定的设备管理器 
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN); 
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName); 
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "来电接听"); 
        startActivity(intent); 
    } 

}
