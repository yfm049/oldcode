package com.iptv.utils;

import android.util.Log;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ListView;

import com.iptv.adapter.AbsAdapter;

public class LayoutListener implements OnGlobalLayoutListener {

	private ListView listview;
	private AbsAdapter adapter;
	private int height=-1;
	public LayoutListener(ListView listview,AbsAdapter adapter){
		this.listview=listview;
		this.adapter=adapter;
	}
	@Override
	public void onGlobalLayout() {
		// TODO Auto-generated method stub
		if(listview.getHeight()>0&&height!=listview.getHeight()){
			Log.i("tvinfo", "listview api "+listview.getHeight());
			adapter.setItemheight(listview.getHeight());
			height=listview.getHeight();
		}
		
	}

}
