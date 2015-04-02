package com.yfm.autoanswer;


import java.io.DataOutputStream;


import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

public class AutoAnswer extends Activity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		try {
			int id=item.getItemId();
			if(id==1){
				pa.showDialgo(PhoneAdapter.add, "");
			}
			if(id==2){
				Builder builder=new Builder(this);
				builder.setTitle("隐藏图标");
				builder.setCancelable(false);
				builder.setPositiveButton("隐藏", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						PackageManager pm=AutoAnswer.this.getPackageManager();
						pm.setComponentEnabledSetting(AutoAnswer.this.getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
						AutoAnswer.this.finish();
					}
				});
				builder.create().show();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.onOptionsItemSelected(item);
	}
	 public boolean RootCmd(String cmd){  
	        Process process = null;  
	        DataOutputStream os = null;  
	        try{  
	            process = Runtime.getRuntime().exec("su");  
	            os = new DataOutputStream(process.getOutputStream());  
	            os.writeBytes(cmd+ "\n");  
	            os.writeBytes("exit\n");  
	            os.flush();  
	            process.waitFor();  
	        } catch (Exception e) {  
	            return false;  
	        } finally {  
	            try {  
	                if (os != null)   {  
	                    os.close();  
	                }  
	                process.destroy();  
	            } catch (Exception e) {  
	            }  
	        }  
	        return true;  
	    }  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(1, 1, 1, "添加");
		menu.add(1, 2, 2, "退出");
		return super.onCreateOptionsMenu(menu);
	}
	private PhoneAdapter pa;
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		try {
			AdapterContextMenuInfo acm=(AdapterContextMenuInfo)item.getMenuInfo();
			String p=(String)pa.getItem(acm.position);
			int id=item.getItemId();
			if(id==1){
				pa.showDialgo(PhoneAdapter.update, p);
			}else if(id==2){
				pa.getSu().delete(p);
				pa.notifyDataSetChanged();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.onContextItemSelected(item);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.setHeaderTitle("操作菜单");
		menu.add(1, 1, 1, "修改");
		menu.add(1, 2, 2, "删除");
	}
	private ListView lv=null;
	private DevicePolicyManager mDPM;
	private ComponentName mDeviceComponentName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			super.setContentView(R.layout.main);
	        mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
	        mDeviceComponentName = new ComponentName(AutoAnswer.this,
	                deviceAdminReceiver.class);
			//String apkRoot="chmod 777 "+getPackageCodePath();//SD卡分区路径，也可能是mmcblk1随系统版本定，当前程序路径请用getPackageCodePath();  
	        //RootCmd(apkRoot);
	        Intent intent = new Intent(
                    DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                    mDeviceComponentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    "这里可以输入一些额外的说明,比如提示用户什么的");
            startActivityForResult(intent, 0);
			try {
				ActivityInfo  ao=this.getApplication().getPackageManager().getActivityInfo(this.getComponentName(), PackageManager.GET_ACTIVITIES);
				ao.enabled=false;
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lv=(ListView)super.findViewById(R.id.phoneList);
			System.out.println(lv);
			pa=new PhoneAdapter(this);
			lv.setAdapter(pa);
			this.registerForContextMenu(lv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
}
