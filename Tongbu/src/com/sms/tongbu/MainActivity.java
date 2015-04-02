package com.sms.tongbu;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this,SetActivity.class);
		this.startActivity(intent);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		init();
		
		IntentFilter filter = new IntentFilter("com.sms.tongbu.updateUi");
		this.registerReceiver(Ui, filter);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Editor e=sp.edit();
		Log.i("--", "保存状态");
		e.putString("xinqing", xinqing.getText().toString());
		e.putString("rxinqing", rxinqing.getText().toString());
		e.putInt("jindu", dataadapter.getValue());
		e.putInt("rjindu", rdataadapter.getValue());
		e.commit();
		this.unregisterReceiver(Ui);
	}

	private ImageView logo, leftlogo, rightlogo, rleftlogo, rrightlogo, jian,
			jia, liaotian, shuaxin;
	private TextView title, name, xinqing, baifenbi, leftname, rightname,
			rleftname, rrightname, rbaifenbi, rxinqing;
	private GridView datagrid, rdatagrid;
	private DataAdapter dataadapter, rdataadapter;
	private SharedPreferences sp =null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		sp = this.getSharedPreferences("phone", MODE_PRIVATE);
		Intent service=new Intent(this,MsgService.class);
		this.startService(service);
		dataadapter = new DataAdapter(this);
		rdataadapter = new DataAdapter(this);
		rdataadapter.setImage(2);
		logo = (ImageView) super.findViewById(R.id.logo);
		leftlogo = (ImageView) super.findViewById(R.id.leftlogo);
		rightlogo = (ImageView) super.findViewById(R.id.rightlogo);
		rleftlogo = (ImageView) super.findViewById(R.id.rleftlogo);
		rrightlogo = (ImageView) super.findViewById(R.id.rrightlogo);
		jian = (ImageView) super.findViewById(R.id.jian);
		jia = (ImageView) super.findViewById(R.id.jia);
		liaotian = (ImageView) super.findViewById(R.id.liaotian);
		shuaxin = (ImageView) super.findViewById(R.id.shuaxin);

		title = (TextView) super.findViewById(R.id.title);
		title.setText("CUBE&MOSAIC");
		name = (TextView) super.findViewById(R.id.name);
		xinqing = (TextView) super.findViewById(R.id.xinqing);
		baifenbi = (TextView) super.findViewById(R.id.baifenbi);
		leftname = (TextView) super.findViewById(R.id.leftname);
		rightname = (TextView) super.findViewById(R.id.rightname);
		rleftname = (TextView) super.findViewById(R.id.rleftname);
		rrightname = (TextView) super.findViewById(R.id.rrightname);
		rbaifenbi = (TextView) super.findViewById(R.id.rbaifenbi);
		rxinqing = (TextView) super.findViewById(R.id.rxinqing);

		datagrid = (GridView) super.findViewById(R.id.datagrid);
		rdatagrid = (GridView) super.findViewById(R.id.rdatagrid);
		datagrid.setAdapter(dataadapter);
		rdatagrid.setAdapter(rdataadapter);

		jia.setOnClickListener(new jiaOnClickListenerImpl());
		jian.setOnClickListener(new jianOnClickListenerImpl());
		liaotian.setOnClickListener(new liaotianOnClickListenerImpl());
		shuaxin.setOnClickListener(new shuaxinOnClickListenerImpl());
		xinqing.setOnClickListener(new xinqingOnClickListenerImpl());
		Log.i("--", "获取状态");
		rdataadapter.setValue(sp.getInt("rjindu", 0));
		rdataadapter.notifyDataSetChanged();
		dataadapter.setValue(sp.getInt("jindu", 0));
		dataadapter.notifyDataSetChanged();
		
		rbaifenbi.setText(sp.getInt("rjindu", 0) + "%");
		baifenbi.setText(sp.getInt("jindu", 0)+"%");
		rxinqing.setText(sp.getString("rxinqing", ""));
		xinqing.setText(sp.getString("xinqing", ""));
	}
	
	
	private void init() {
		String kv = this.getKv("name", "cube");
		if ("mosaic".equals(kv)) {
			logo.setImageResource(R.drawable.cube);
			name.setText("CUBE");
			xinqing.setText("");
			leftname.setText("CUBE");
			rightname.setText("MOSAIC");
			rleftname.setText("MOSAIC");
			rrightname.setText("CUBE");
			leftlogo.setImageResource(R.drawable.cube);
			rightlogo.setImageResource(R.drawable.mosaic);
			rleftlogo.setImageResource(R.drawable.mosaic);
			rrightlogo.setImageResource(R.drawable.cube);
		} else {
			logo.setImageResource(R.drawable.mosaic);
			name.setText("MOSAIC");
			xinqing.setText("");
			leftname.setText("MOSAIC");
			rightname.setText("CUBE");
			rleftname.setText("CUBE");
			rrightname.setText("MOSAIC");
			leftlogo.setImageResource(R.drawable.mosaic);
			rightlogo.setImageResource(R.drawable.cube);
			rleftlogo.setImageResource(R.drawable.cube);
			rrightlogo.setImageResource(R.drawable.mosaic);
		}
	}

	BroadcastReceiver Ui = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			rdataadapter.setValue(MsgReceiver.msgs.getJindu());
			rdataadapter.notifyDataSetChanged();
			rbaifenbi.setText(MsgReceiver.msgs.getJindu() + "%");
			rxinqing.setText(MsgReceiver.msgs.getXq());
		}
	};

	class shuaxinOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 发送短信;
			SendMsg();
			Toast.makeText(MainActivity.this, "刷新短信已发送",
					Toast.LENGTH_SHORT).show();
		}

	}
	class xinqingOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			Builder builder=new Builder(MainActivity.this);
			builder.setTitle("心情");
			final EditText et=new EditText(MainActivity.this);
			builder.setView(et);
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					xinqing.setText(et.getText().toString());
					SendMsg();
					
				}
			});
			builder.setNegativeButton("取消", null);
			builder.create().show();
			
		}

	}
	class liaotianOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 发送短信;
			Intent intent = new Intent(Intent.ACTION_VIEW);

			intent.putExtra("address", getKv("num", ""));

			intent.setType("vnd.android-dir/mms-sms");
			startActivity(intent);
		}

	}
	
	class jiaOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int i = dataadapter.getValue();
			if (i == 100) {
				Toast.makeText(MainActivity.this, "已经是100了，不能再加了",
						Toast.LENGTH_SHORT).show();
				return;
			}
			i = i + 5;
			dataadapter.setValue(i);
			dataadapter.notifyDataSetChanged();
			baifenbi.setText(i + "%");
			// 发送短信;
			SendMsg();
		}

	}

	class jianOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int i = dataadapter.getValue();
			if (i == 0) {
				Toast.makeText(MainActivity.this, "已经是0了，不能再减了",
						Toast.LENGTH_SHORT).show();
				return;
			}
			i = i - 5;
			dataadapter.setValue(i);
			dataadapter.notifyDataSetChanged();
			baifenbi.setText(i + "%");

			// 发送短信;
			SendMsg();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("设置");
		return true;
	}

	public void SendMsg() {
		String num = getKv("num", "");
		if (num != null && !"".equals(num)) {
			try {
				SmsManager sm = SmsManager.getDefault();
				JSONObject mp = new JSONObject();
				mp.put("xinqing", xinqing.getText().toString());
				mp.put("jindu", dataadapter.getValue());
				sm.sendTextMessage(num, null, mp.toString(), null, null);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "同步失败，短信发送失败", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "请先设置手机号", Toast.LENGTH_SHORT).show();
		}

	}

	public String getKv(String key, String def) {
		
		return sp.getString(key, def);
	}

}
