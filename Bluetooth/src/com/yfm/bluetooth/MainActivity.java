package com.yfm.bluetooth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yfm.adapter.BlueToothAdapter;
import com.yfm.adapter.ButtonGridAdapter;
import com.yfm.adapter.lightGridAdapter;
import com.yfm.utils.Channel;
import com.yfm.utils.OrderUtils;

public class MainActivity extends Activity {

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		this.unregisterReceiver(br);
		super.onDestroy();
	}
	private EditText bootpass;
	private GridView lights, buttongrid;
	private Button openbl, bluetoothstate, boot, openstatus, close, dianya;
	public OrderUtils orderutils;
	public List<BluetoothDevice> lbd = new ArrayList<BluetoothDevice>();
	public BluetoothReciver br = new BluetoothReciver();
	public BlueToothAdapter ba = new BlueToothAdapter(lbd, this);
	public Dialog dialog;
	public String currOrder = null;
	private List<Channel> lc = new ArrayList<Channel>();
	private BaseAdapter buttonadapter, lightadapter;
	private SharedPreferences sp = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		// 注册广播
		IntentFilter inf = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		IntentFilter filter = new IntentFilter(
				BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

		this.registerReceiver(br, inf);
		this.registerReceiver(br, filter);
		orderutils = new OrderUtils(this, handler);
		orderutils.openBluetooth();
		openbl = (Button) super.findViewById(R.id.openbl);
		bluetoothstate = (Button) super.findViewById(R.id.bluetoothstate);
		boot = (Button) super.findViewById(R.id.boot);
		openstatus = (Button) super.findViewById(R.id.openstatus);
		close = (Button) super.findViewById(R.id.close);
		dianya = (Button) super.findViewById(R.id.dianya);
		bootpass = (EditText) super.findViewById(R.id.bootpass);
		boot.setOnClickListener(new BootcloseClice(1));
		close.setOnClickListener(new BootcloseClice(2));
		
		
		openbl.setOnClickListener(new OpenBlOnClick());

		lights = (GridView) super.findViewById(R.id.lights);
		buttongrid = (GridView) super.findViewById(R.id.buttongrid);
		buttongrid.setVisibility(View.GONE);
		sp = this.getSharedPreferences("tongdao", MODE_PRIVATE);
		try {
			String td = sp.getString("td", "");
			if (td != null && !"".equals(td)) {
				JSONArray ja=new JSONArray(td);
				for(int i=0;i<ja.length();i++){
					JSONObject jo=ja.getJSONObject(i);
					Channel cl = new Channel();
					cl.setId(jo.getInt("id"));
					cl.setName(jo.getString("name"));
					lc.add(cl);
				}
			} else {
				for (int i = 0; i < 8; i++) {
					Channel cl = new Channel();
					cl.setId(i + 1);
					cl.setName((i + 1)+"通道");
					lc.add(cl);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buttonadapter = new ButtonGridAdapter(lc, this, orderutils);
		lightadapter = new lightGridAdapter(lc, this);
		buttongrid.setAdapter(buttonadapter);
		
		lights.setAdapter(lightadapter);
		lights.setOnTouchListener(new GridviewOnTouchListener());
	}

	public void save() {
		try {
			JSONArray ja=new JSONArray();
			for(int i=0;i<lc.size();i++){
				JSONObject jo=new JSONObject();
				Channel cl=lc.get(i);
				jo.put("id", cl.getId());
				jo.put("name", cl.getName());
				ja.put(jo);
			}
			if(ja.length()>0){
				Editor e=sp.edit();
				e.putString("td", ja.toString());
				e.commit();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 开机关机
	class BootcloseClice implements OnClickListener {
		int i;

		public BootcloseClice(int i) {
			this.i = i;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (i == 1) {
				// 开机
				String p = bootpass.getText().toString();
				char[] chars = p.toCharArray();
				if (chars.length == 4 && Integer.parseInt(p) < 8889) {
					StringBuffer sb = new StringBuffer();

					for (int i = 0; i < chars.length; i++) {
						sb.append("FC");
						sb.append("A" + (i + 1));
						sb.append("0" + chars[i]);
						sb.append("FD");
					}
					orderutils.SendOrder(sb.toString());

				} else {
					Toast.makeText(MainActivity.this, "密码长度必须为4位,并且为1-8数字",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				// 关机
				orderutils.SendOrder("FCA600FD");
			}

		}

	}

	// 打开搜多蓝牙对话框
	class OpenBlOnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Boolean flag = orderutils.openBluetooth();
			if (flag) {
				Builder builder = new Builder(MainActivity.this);
				builder.setTitle("请选择一个设备连接");
				View view = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.searchbluetooth, null);
				ListView bluelist = (ListView) view
						.findViewById(R.id.bluetoothlist);
				orderutils.setListBl(lbd);
				bluelist.setAdapter(ba);
				Button search = (Button) view.findViewById(R.id.search);
				search.setOnClickListener(new searchbluetooth());
				builder.setView(view);
				Dialog p = builder.create();
				p.show();
				bluelist.setOnItemClickListener(new bluelistItemClick(p));
			}

		}

	}

	class bluelistItemClick implements OnItemClickListener {

		private Dialog p;

		public bluelistItemClick(Dialog p) {
			this.p = p;
		}

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int pos,
				long id) {
			// TODO Auto-generated method stub

			p.cancel();
			BluetoothDevice device = lbd.get(pos);
			dialog = ProgressDialog.show(MainActivity.this, "连接蓝牙", "正在连接"
					+ device.getName());
			orderutils.conncetDevice(device);

		}

	}

	// 查找蓝牙设备
	class searchbluetooth implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			lbd.clear();
			ba.notifyDataSetChanged();
			dialog = ProgressDialog.show(MainActivity.this, "查找设备", "正在查找设备");
			orderutils.founddevice();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class BluetoothReciver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			Log.i("发现设备", action);
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				lbd.add(device);
				ba.notifyDataSetChanged();
			}
			if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				if (dialog != null) {
					dialog.cancel();

				}
			}

		}

	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			int i = msg.what;
			// 蓝牙设备连接状态1 arg1等于1时连接成功，其他为失败
			// Toast.makeText(MainActivity.this, "回复："+msg.obj,
			// Toast.LENGTH_SHORT).show();
			// Log.i("status", msg.obj + "");
			if (i == 1) {
				if (msg.arg1 == 1) {
					Toast.makeText(MainActivity.this, msg.obj.toString(),
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(MainActivity.this, msg.obj.toString(),
							Toast.LENGTH_LONG).show();
				}
				bluetoothstate.setText(msg.obj.toString());
				if (dialog != null) {
					dialog.cancel();
				}
			}
			if (i == 2) {
				if (msg.arg1 == 1) {
					String order = msg.obj.toString();
					// 开机密码正确
					if ("FCA501FD".equals(order)) {
						Toast.makeText(MainActivity.this, "密码正确",
								Toast.LENGTH_LONG).show();
					}
					if ("FCA500FD".equals(order)) {
						Toast.makeText(MainActivity.this, "密码错误",
								Toast.LENGTH_LONG).show();
					}
					if ("FCA601FD".equals(order)) {
						openstatus.setText("开机完毕");
						buttongrid.setVisibility(View.VISIBLE);
					}
					if ("FCA600FD".equals(order)) {
						openstatus.setText("关机完毕");
						buttongrid.setVisibility(View.GONE);
					}
					if (order.startsWith("FCE3")) {
						String p = order.substring(4, 6);
						int v = Integer.parseInt(p, 16);
						dianya.setText("当前电压" + v + "v");
					}
					if (order.startsWith("FCA9")) {
						String p = order.substring(4, 6);
						int v = Integer.parseInt(p, 16);
						openstatus.setText(openstatus.getText() + ",延时" + v
								+ "秒");
					}
					for (int t = 1; t < 9; t++) {
						String o = "FCD" + t + "01FD";
						String c = "FCD" + t + "00FD";

						Channel cl = lc.get(t - 1);
						if (o.equals(order)) {
							openstatus.setText("通道" + t + "开");
							cl.setState(true);
							lightadapter.notifyDataSetChanged();
							buttonadapter.notifyDataSetChanged();
						}
						if (c.equals(order)) {
							openstatus.setText("通道" + t + "关");
							cl.setState(false);
							lightadapter.notifyDataSetChanged();
							buttonadapter.notifyDataSetChanged();
						}
					}
				}
			}

		}

	};

	class GridviewOnTouchListener implements OnTouchListener {

		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			int action = event.getAction();
			if (action == MotionEvent.ACTION_MOVE) {
				return true;
			}
			return false;
		}

	}

}
