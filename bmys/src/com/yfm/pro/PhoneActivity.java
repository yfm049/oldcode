package com.yfm.pro;

import android.app.ActivityGroup;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.yfm.adapter.PhoneAdapter;
import com.yfm.pojo.Phone;

public class PhoneActivity extends ActivityGroup {

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		spa=new PhoneAdapter(this);
		datalist.setAdapter(spa);
		spa.getData();
		datalist.setOnItemClickListener(new OnItemClickListenerImpl());
	}

	private ListView datalist;
	private PhoneAdapter spa;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone);
		datalist = (ListView) super.findViewById(R.id.datalist);
		
	}

	class OnItemClickListenerImpl implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Phone p=(Phone)spa.getItem(arg2);
			 Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + p.getPhonenum()));
              //通知activtity处理传入的call服务
			 PhoneActivity.this.startActivity(intent);
		}
		
	}
}
