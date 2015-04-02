package com.iptv.season;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;

import com.iptv.model.StateLayout;
import com.iptv.model.Tvinfo;
import com.iptv.season_new.R;

public class LiveTVActivity extends Activity {

	private SurfaceView playview;
	private StateLayout Stateview;
	private List<Tvinfo> tvlist=new ArrayList<Tvinfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livetv);
		playview=(SurfaceView)this.findViewById(R.id.playview);
		Stateview=new StateLayout(this);
		
	}
}
