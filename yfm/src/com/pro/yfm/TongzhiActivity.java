package com.pro.yfm;

import android.app.ActivityGroup;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class TongzhiActivity extends ActivityGroup {

	public TongzhiAdapter ta;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		TongzhiAdapter ta=new TongzhiAdapter(this);
		datalist.setAdapter(ta);
		MessageService.lg.clear();
	}
	private ListView datalist;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tongzhi);
		datalist=(ListView)super.findViewById(R.id.datalist);
		
	}
	
	
}
