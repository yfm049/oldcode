package com.iptv.season;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iptv.season_new.R;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	public void livetvbutClick(View v){
		Intent livetv=new Intent(this,LiveTVActivity.class);
		this.startActivity(livetv);
	}
	public void playbackbutClick(View v){
		
	}
	public void webbutClick(View v){
		
	}
	public void apkbutClick(View v){
		
	}
	public void setupbutClick(View v){
		
	}
	public void infobutClick(View v){
		
	}
}
