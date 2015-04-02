package com.yfm.pro;

import android.app.ActivityGroup;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.yfm.adapter.WxbyAdapter;
import com.yfm.net.HttpDao;
import com.yfm.pojo.Good;

public class WxbyActivity extends ActivityGroup {

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		WxbyAdapter wa=new WxbyAdapter(this);
		datelist.setAdapter(wa);
		wa.getData();
		datelist.setOnItemClickListener(new OnItemClickListenerImpl());
	}
	private GridView datelist;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wxby);
		datelist=(GridView)super.findViewById(R.id.datelist);
		
	}
	class OnItemClickListenerImpl implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Good gd=(Good)arg0.getItemAtPosition(arg2);
			Intent intent=new Intent(WxbyActivity.this,WxbyXxActivity.class);
			intent.putExtra("good", gd);
			MainActivity.showView(intent);
		}
    	
    }
	
}
