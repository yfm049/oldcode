package com.yfm.pro;

import java.util.HashMap;
import java.util.Map;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yfm.adapter.ActiveAdapter;
import com.yfm.adapter.GroupBuyAdapter;
import com.yfm.adapter.HuiShouAdapter;
import com.yfm.adapter.LbAdapter;
import com.yfm.adapter.PartyAdapter;
import com.yfm.adapter.ShangPinAdapter;
import com.yfm.pojo.Good;

public class LiebiaoActivity extends ActivityGroup {

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	private ListView datalist;
	private static Map<String, LbAdapter> lba = new HashMap<String, LbAdapter>();
	private LinearLayout shangpin, active, groupbuy, party, huishou;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.liebiao);

		shangpin = (LinearLayout) super.findViewById(R.id.shangpin);
		active = (LinearLayout) super.findViewById(R.id.active);
		groupbuy = (LinearLayout) super.findViewById(R.id.groupbuy);
		party = (LinearLayout) super.findViewById(R.id.party);
		huishou = (LinearLayout) super.findViewById(R.id.huishou);

		shangpin.setOnClickListener(new OnClickListenerImpl(1));
		active.setOnClickListener(new OnClickListenerImpl(2));
		groupbuy.setOnClickListener(new OnClickListenerImpl(3));
		party.setOnClickListener(new OnClickListenerImpl(4));
		huishou.setOnClickListener(new OnClickListenerImpl(5));

		datalist = (ListView) super.findViewById(R.id.datalist);

		datalist.setOnScrollListener(new OnScrollListenerImpl());
		datalist.setOnItemClickListener(new OnItemClickListenerImpl());
		ShangPinAdapter spa = new ShangPinAdapter(this);
		lba.put("shangpin", spa);
		datalist.setAdapter(spa);
		spa.getData();
	}

	class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Good gd = (Good) arg0.getItemAtPosition(arg2);
			Intent intent = new Intent(LiebiaoActivity.this,
					DetailActivity.class);
			intent.putExtra("good", gd);
			LiebiaoActivity.this.startActivity(intent);
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

	class OnClickListenerImpl implements OnClickListener {
		private int id;

		public OnClickListenerImpl(int id) {
			this.id = id;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			LbAdapter la = null;
			shangpin.setBackgroundDrawable(null);
			active.setBackgroundDrawable(null);
			groupbuy.setBackgroundDrawable(null);
			party.setBackgroundDrawable(null);
			huishou.setBackgroundDrawable(null);
			switch (id) {
			case 1:
				la = lba.get("shangpin");
				if (la == null) {
					la = new ShangPinAdapter(LiebiaoActivity.this);
					lba.put("shangpin", la);
				}
				shangpin.setBackgroundResource(R.drawable.btn_0);
				break;
			case 2:
				la = lba.get("active");
				if (la == null) {
					la = new ActiveAdapter(LiebiaoActivity.this);
					lba.put("active", la);
				}
				active.setBackgroundResource(R.drawable.btn_0);
				break;
			case 3:
				la = lba.get("groupbuy");
				if (la == null) {
					la = new GroupBuyAdapter(LiebiaoActivity.this);
					lba.put("groupbuy", la);
				}
				groupbuy.setBackgroundResource(R.drawable.btn_0);
				break;

			case 4:
				la = lba.get("party");
				if (la == null) {
					la = new PartyAdapter(LiebiaoActivity.this);
					lba.put("party", la);
				}
				party.setBackgroundResource(R.drawable.btn_0);
				break;
			case 5:
				la = lba.get("huishou");
				if (la == null) {
					la = new HuiShouAdapter(LiebiaoActivity.this);
					lba.put("huishou", la);
				}
				huishou.setBackgroundResource(R.drawable.btn_0);
				break;
			default:
				break;
			}
			if (la != null) {
				datalist.setAdapter(la);
				la.clearData();

				la.setIsrun(false);
				la.getData();
			}
		}

	}
}
