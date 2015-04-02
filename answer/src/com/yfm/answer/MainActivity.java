package com.yfm.answer;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.unregisterReceiver(receiver);
	}
	private PhoneAdapter pa;
	private ListView lv=null;
	private DevicePolicyManager policyManager; 
	private ComponentName componentName;
	private BootBroadcastReceiver receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent=new Intent(this,AnswerService.class);
		startService(intent);
		receiver=new BootBroadcastReceiver();
		IntentFilter filter=new IntentFilter();
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(2147483647);
		this.registerReceiver(receiver, filter);
		
		lv=(ListView)super.findViewById(R.id.phoneList);
		System.out.println(lv);
		pa=new PhoneAdapter(this);
		lv.setAdapter(pa);
		this.registerForContextMenu(lv);
		policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE); 
	    componentName = new ComponentName(this.getApplicationContext(), LockReceiver.class); 
	    if(!policyManager.isAdminActive(componentName)){
	    	activeManager();
	    }
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		try {
			int id=item.getItemId();
			if(id==1){
				pa.showDialgo(PhoneAdapter.add, "");
			}
			if(id==2){
				this.finish();
			}
			if(id==3){
				if(!policyManager.isAdminActive(componentName)){
			    	activeManager();
			    }else{
			    	Toast.makeText(this, "已经激活", Toast.LENGTH_LONG).show();
			    }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(1, 1, 1, "添加号码");
		menu.add(1, 2, 2, "退出程序");
		menu.add(1, 3, 3, "激活设备");
		return super.onCreateOptionsMenu(menu);
	}
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
	private void activeManager() { 
        //使用隐式意图调用系统方法来激活指定的设备管理器 
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN); 
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName); 
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "来电接听"); 
        startActivity(intent); 
    } 

}
