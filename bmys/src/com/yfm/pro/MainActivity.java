package com.yfm.pro;

import java.util.HashMap;
import java.util.Map;

import android.app.ActivityGroup;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends ActivityGroup {



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.tousu) {
			if (MainActivity.cid <= 0) {
				Intent intent = new Intent(this, LoginActivity.class);
				this.startActivity(intent);

			} else {
				Intent intent = new Intent(MainActivity.this,
						TouSuActivity.class);
				this.startActivity(intent);
			}
		}
		if (id == R.id.tuichu) {
			exit();

		}
		return super.onOptionsItemSelected(item);
	}
	public void exit(){
		Builder builder = new Builder(MainActivity.this);
		builder.setTitle("退出程序");
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.qkmm, null);
		final CheckBox cb = (CheckBox) view.findViewById(R.id.zhxx);
		builder.setView(view);
		builder.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (cb.isChecked()) {
							SharedPreferences sps = MainActivity.this
									.getSharedPreferences("user",
											MODE_PRIVATE);

							Editor et = sps.edit();
							et.putString("name", "");
							et.putString("pass", "");
							et.putInt("cid", -1);

							et.commit();
						}
						MainActivity.this.finish();
					}
				});
		builder.setNegativeButton("取消", null);
		builder.create().show();
	}
	private PopupWindow popup;
	private static LinearLayout content, shouye, liebiao, yuyue, phone, more,
				about,jszc, tsjy,wxby,login,ditu,exit;
	private static Map<String, View> mv = new HashMap<String, View>();
	private static ActivityGroup group;
	public static int cid = -1;
	public static String name;
	public static String pass;
	public static String imei;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Intent service = new Intent(this, MessageService.class);
		this.startService(service);
		SharedPreferences sps = MainActivity.this.getSharedPreferences("user",
				MODE_PRIVATE);
		cid = sps.getInt("cid", -1);
		name=sps.getString("name", "");
		pass=sps.getString("pass", "");
		imei=GetImei();
		group = this;
		content = (LinearLayout) super.findViewById(R.id.content);
		shouye = (LinearLayout) super.findViewById(R.id.shouye);
		shouye.setOnClickListener(new OnClickListenerImpl(0));

		liebiao = (LinearLayout) super.findViewById(R.id.liebiao);
		liebiao.setOnClickListener(new OnClickListenerImpl(1));
		yuyue = (LinearLayout) super.findViewById(R.id.yuyue);
		yuyue.setOnClickListener(new OnClickListenerImpl(2));

		phone = (LinearLayout) super.findViewById(R.id.phone);
		phone.setOnClickListener(new OnClickListenerImpl(3));

		more = (LinearLayout) super.findViewById(R.id.more);
		more.setOnClickListener(new OnClickListenerImpl(4));

		

		Intent intent = new Intent(MainActivity.this, ShouyeActivity.class);
		intent.setAction("shouye");
		MainActivity.showView(intent);
	}

	public static void showView(Intent intent) {
		String action = intent.getAction();
//		View view = mv.get(action);
//		if (view == null) {
//			
//		}
		View view = group.getLocalActivityManager()
				.startActivity(action, intent).getDecorView();
		content.removeAllViews();
		content.addView(view, new LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));
	}

	public void showpop() {
		if (popup == null) {
			View view = LayoutInflater.from(this).inflate(R.layout.more_item,
					null);
			about = (LinearLayout) view.findViewById(R.id.about);
			about.setOnClickListener(new OnClickListenerImpl(5));
			
			jszc = (LinearLayout) view.findViewById(R.id.jszc);
			jszc.setOnClickListener(new OnClickListenerImpl(6));
			//
			
			
			tsjy = (LinearLayout) view.findViewById(R.id.tsjy);
			tsjy.setOnClickListener(new OnClickListenerImpl(7));
			//
			wxby = (LinearLayout) view.findViewById(R.id.wxby);
			wxby.setOnClickListener(new OnClickListenerImpl(8));
			
			login = (LinearLayout) view.findViewById(R.id.login);
			login.setOnClickListener(new OnClickListenerImpl(9));
			//
			ditu = (LinearLayout) view.findViewById(R.id.ditu);
			ditu.setOnClickListener(new OnClickListenerImpl(10));
			exit = (LinearLayout) view.findViewById(R.id.exit);
			exit.setOnClickListener(new OnClickListenerImpl(11));
			
			popup = new PopupWindow(view, LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT, true);
			ColorDrawable cd = new ColorDrawable(-0000);
			popup.setBackgroundDrawable(cd);
			popup.update();
			popup.setTouchable(true); // 设置popupwindow可点击
			popup.setOutsideTouchable(true); // 设置popupwindow外部可点击
		}
		Log.i("--", popup.isShowing() + "");
		if (popup.isShowing()) {
			popup.dismiss();
			return;
		}
		popup.showAtLocation(content, Gravity.BOTTOM, 0, 105);
	}
	
	class OnClickListenerImpl implements OnClickListener {
		private int action;

		public OnClickListenerImpl(int action) {
			this.action = action;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent;
			if (action !=4&&action!=10&&action!=7&&action!=9) {
				shouye.setBackgroundDrawable(null);
				liebiao.setBackgroundDrawable(null);
				yuyue.setBackgroundDrawable(null);
				phone.setBackgroundDrawable(null);
				more.setBackgroundDrawable(null);
			}
			if(action > 4&&action!=10&&action!=7&&action!=9){
				more.setBackgroundResource(R.drawable.choosecur);
			}
			switch (action) {
			case 0:
				intent = new Intent(MainActivity.this, ShouyeActivity.class);
				intent.setAction("shouye");
				MainActivity.showView(intent);
				shouye.setBackgroundResource(R.drawable.choosecur);
				break;
			case 1:
				intent = new Intent(MainActivity.this, LiebiaoActivity.class);
				intent.setAction("liebiao");
				MainActivity.showView(intent);
				liebiao.setBackgroundResource(R.drawable.choosecur);
				break;
			case 2:
				intent = new Intent(MainActivity.this, YuyueActivity.class);
				intent.setAction("yuyue");
				MainActivity.showView(intent);
				yuyue.setBackgroundResource(R.drawable.choosecur);
				break;

			case 3:
				intent = new Intent(MainActivity.this, PhoneActivity.class);
				intent.setAction("phone");
				MainActivity.showView(intent);
				phone.setBackgroundResource(R.drawable.choosecur);
				break;
			case 4:
				showpop();
				break;
			case 5:
				intent = new Intent(MainActivity.this, GuanyuActivity.class);
				intent.setAction("guanyu");
				MainActivity.showView(intent);
				if (popup!=null&&popup.isShowing()) {
					popup.dismiss();
					return;
				}
				break;
			case 6:
				intent = new Intent(MainActivity.this, ZhichiActivity.class);
				intent.setAction("zhichi");
				MainActivity.showView(intent);
				if (popup!=null&&popup.isShowing()) {
					popup.dismiss();
					return;
				}
				break;
			case 7:
				if (MainActivity.cid <= 0) {
					intent = new Intent(MainActivity.this, LoginActivity.class);
					intent.setAction("login");
					MainActivity.this.startActivity(intent);
				} else {
					intent = new Intent(MainActivity.this,
							TouSuActivity.class);
					intent.setAction("tousu");
					MainActivity.this.startActivity(intent);
				}
				if (popup!=null&&popup.isShowing()) {
					popup.dismiss();
					return;
				}
				break;
			case 8:
				intent = new Intent(MainActivity.this, WxbyActivity.class);
				intent.setAction("wxby");
				MainActivity.showView(intent);
				if (popup!=null&&popup.isShowing()) {
					popup.dismiss();
					return;
				}
				break;
			case 9:
				intent = new Intent(MainActivity.this, LoginActivity.class);
				intent.setAction("login");
				MainActivity.this.startActivity(intent);
				if (popup!=null&&popup.isShowing()) {
					popup.dismiss();
					return;
				}
				break;
			case 10:
				intent = new Intent(MainActivity.this, DiTuActivity.class);
				intent.setAction("ditu");
				MainActivity.this.startActivity(intent);
				if (popup!=null&&popup.isShowing()) {
					popup.dismiss();
					return;
				}
				break;
			case 11:
				exit();
				if (popup!=null&&popup.isShowing()) {
					popup.dismiss();
					return;
				}
				break;
			
			default:
				break;
			}
			
			
		}

	}
	public String GetImei(){
    	TelephonyManager telephonyManager=(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
    	String imei=telephonyManager.getDeviceId();
    	return imei;
    }
}
