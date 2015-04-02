package com.pro.husini;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.pro.adapter.PhoneAdapter;
import com.pro.model.HPhone;
import com.pro.model.PhoneUtils;

public class MainActivity extends Activity {

	private ListView phonelist;
	private PhoneAdapter adapter;
	private ListenerImpl listener;
	private HPhone phone;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private Calendar ca;
	private int cid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PhoneUtils.checkLogs(this);
		phonelist = (ListView) super.findViewById(R.id.phonelist);
		adapter = new PhoneAdapter(this);
		phonelist.setAdapter(adapter);
		listener = new ListenerImpl();
		phonelist.setOnItemLongClickListener(listener);
		PhoneUtils.checkLogs(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adapter.notifyDataSetChanged();
	}

	public void add(View v) {
		Intent add = new Intent(this, AddActivity.class);
		this.startActivity(add);
	}


	class ListenerImpl implements OnItemLongClickListener,
			DialogInterface.OnClickListener, TimePickerDialog.OnTimeSetListener {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			PhoneService.phones.remove(phone);
			if(PhoneService.phones.size()<=0){
				Intent service=new Intent(MainActivity.this, PhoneService.class);
				MainActivity.this.stopService(service);
			}
			adapter.notifyDataSetChanged();
		}

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			phone = PhoneService.phones.get(arg2);
			shoudelete();
			return false;
		}

		@Override
		public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
			ca.set(Calendar.HOUR_OF_DAY, arg1);
			ca.set(Calendar.MINUTE, arg2);
			if(cid==R.id.start){
				PhoneService.starttime=sdf.format(ca.getTime());
			}else if(cid==R.id.end){
				PhoneService.endtime=sdf.format(ca.getTime());
			}
			
		}
	}

	public void shoudelete() {
		Builder bulder = new Builder(this);
		bulder.setTitle("是否删除");
		bulder.setMessage("是否删除选中项");
		bulder.setNegativeButton("确定", listener);
		bulder.setNeutralButton("取消", null);
		bulder.create().show();
	}

	public void startservice(View v){
		if(PhoneService.phones.size()>0){
			Intent service=new Intent(this, PhoneService.class);
			this.startService(service);
			finish();
		}else{
			Toast.makeText(this, "请添加号码后启动", Toast.LENGTH_SHORT).show();
		}
		
	}
	public void quit(View v){
		Intent service=new Intent(this, PhoneService.class);
		this.stopService(service);
		PhoneService.phones.clear();
		finish();
	}
	public void showstarttime(View v) {
		try {
			cid = v.getId();
			ca = Calendar.getInstance();
			if (PhoneService.starttime != null
					&& !"".equals(PhoneService.starttime)) {
				ca.setTime(sdf.parse(PhoneService.starttime));
			} else {
				ca.setTime(new Date());
			}
			int hour = ca.get(Calendar.HOUR_OF_DAY);
			int minute = ca.get(Calendar.MINUTE);
			TimePickerDialog dialog = new TimePickerDialog(this, listener,
					hour, minute, true);
			dialog.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showendtime(View v) {
		try {
			cid = v.getId();
			ca = Calendar.getInstance();
			if (PhoneService.endtime != null
					&& !"".equals(PhoneService.endtime)) {
				ca.setTime(sdf.parse(PhoneService.endtime));
			} else {
				ca.setTime(new Date());
			}
			int hour = ca.get(Calendar.HOUR_OF_DAY);
			int minute = ca.get(Calendar.MINUTE);
			TimePickerDialog dialog = new TimePickerDialog(this, listener,
					hour, minute, true);
			dialog.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
