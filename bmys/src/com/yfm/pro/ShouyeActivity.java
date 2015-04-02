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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

import com.yfm.adapter.LbAdapter;
import com.yfm.adapter.ShouYeAdapter;
import com.yfm.net.HttpDao;
import com.yfm.pojo.Good;
import com.yfm.pro.LiebiaoActivity.OnItemClickListenerImpl;
import com.yfm.pro.LiebiaoActivity.OnScrollListenerImpl;

public class ShouyeActivity extends ActivityGroup {

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ShouYeAdapter sy=new ShouYeAdapter(this);
		datalist.setAdapter(sy);
		sy.getData();
		datalist.setOnItemClickListener(new OnItemClickListenerImpl());
		datalist.setOnScrollListener(new OnScrollListenerImpl());
	}
	private ListView datalist;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shouye);
		datalist=(ListView)super.findViewById(R.id.datalist);
		
	}
	class OnItemClickListenerImpl implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Good gd=(Good)arg0.getItemAtPosition(arg2);
			Intent intent=new Intent(ShouyeActivity.this,DetailActivity.class);
			intent.putExtra("good", gd);
			ShouyeActivity.this.startActivity(intent);
		}
    	
    }
	class OnScrollListenerImpl implements OnScrollListener {

		@Override
		public void onScroll(AbsListView arg0, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
			if (totalItemCount > visibleItemCount) {
				Log.i("spa", firstVisibleItem + "--" + visibleItemCount + "--"
						+ totalItemCount);
				if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
					LbAdapter la = (LbAdapter) datalist.getAdapter();
					la.getData();
				}
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView arg0, int arg1) {
			// TODO Auto-generated method stub
			Log.i("----", arg1 + "");
		}

	}
	
}
