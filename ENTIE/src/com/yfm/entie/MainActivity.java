package com.yfm.entie;

import java.util.HashMap;
import java.util.Map;

import android.app.ActivityGroup;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;

public class MainActivity extends ActivityGroup {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();

		if (id == R.id.set) {
			Intent intent = new Intent(this, SetActivity.class);
			this.startActivity(intent);
		}
		if (id == R.id.exit) {
			MainActivity.this.finish();
		}
		return super.onOptionsItemSelected(item);
	}

	private Map<String, View> mview = new HashMap<String, View>();
	private LinearLayout content;

	private LinearLayout qjmode;
	private LinearLayout dgmode;
	private LinearLayout dqmode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		Intent intent = new Intent(this, QingJingModeActivity.class);
		intent.setAction("QingJingModeActivity");
		Intent dgmodeintent = new Intent(MainActivity.this,
				DengGuangModeActivity.class);
		dgmodeintent.setAction("DengGuangModeActivity");
		Intent dqmodeintent = new Intent(MainActivity.this,
				DianQiModeActivity.class);
		dqmodeintent.setAction("DianQiModeActivity");

		content = (LinearLayout) super.findViewById(R.id.content);
		qjmode = (LinearLayout) super.findViewById(R.id.qjmode);
		qjmode.setOnClickListener(new OnClickListenerImpl(intent));
		dgmode = (LinearLayout) super.findViewById(R.id.dgmode);
		dgmode.setOnClickListener(new OnClickListenerImpl(dgmodeintent));
		dqmode = (LinearLayout) super.findViewById(R.id.dqmode);
		dqmode.setOnClickListener(new OnClickListenerImpl(dqmodeintent));
		showActivity(intent);
		SharedPreferences sp = this.getSharedPreferences("set", MODE_PRIVATE);
		boolean p = sp.getBoolean("one", true);
		if (p) {
			Builder builder = new Builder(this);
			builder.setTitle("设置");
			builder.setMessage("是否要进行设置?");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(MainActivity.this,
									SetActivity.class);
							MainActivity.this.startActivity(intent);
						}
					});
			builder.setNegativeButton("取消", null);
			builder.create().show();
			Editor e = sp.edit();
			e.putBoolean("one", false);
			e.commit();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showActivity(Intent intent) {
		String key = intent.getAction();
		View view = null;
		Window window = this.getLocalActivityManager().startActivity(key,
				intent);
		view = window.getDecorView();
		mview.put(key, view);
		content.removeAllViews();
		content.addView(view, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));

		qjmode.setBackgroundDrawable(null);
		dgmode.setBackgroundDrawable(null);
		dqmode.setBackgroundDrawable(null);
		if ("QingJingModeActivity".equals(key)) {
			qjmode.setBackgroundResource(R.drawable.bottom_on);
		}
		if ("DengGuangModeActivity".equals(key)) {
			dgmode.setBackgroundResource(R.drawable.bottom_on);
		}
		if ("DianQiModeActivity".equals(key)) {
			dqmode.setBackgroundResource(R.drawable.bottom_on);
		}

	}

	class OnClickListenerImpl implements OnClickListener {

		private Intent intent;

		public OnClickListenerImpl(Intent intent) {
			this.intent = intent;
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub
			showActivity(intent);
		}

	}

}
