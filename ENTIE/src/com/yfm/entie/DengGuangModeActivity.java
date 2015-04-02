package com.yfm.entie;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.yfm.adapter.DGadapter;

public class DengGuangModeActivity extends Activity {

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adapter.startTongbu();
	}
	private DGadapter adapter;
	private ListView listview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dengguang);
        listview=(ListView)super.findViewById(R.id.dglist);
        adapter=new DGadapter(this);
        listview.setAdapter(adapter);
        
    }



    
}
