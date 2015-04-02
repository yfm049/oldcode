package com.yfm.entie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.yfm.adapter.DQadapter;

public class DianQiModeActivity extends Activity {

	private DQadapter adapter;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adapter.startTongbu();
	}

	private ExpandableListView listview;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianqi);
        listview=(ExpandableListView)super.findViewById(R.id.dqlist);
        adapter=new DQadapter(this);
        listview.setAdapter(adapter);
        listview.expandGroup(0);
        listview.expandGroup(1);
        listview.expandGroup(2);
        
    }


}
