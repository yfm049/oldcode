package com.iptv.application;

import com.iptv.season_new.R;

import android.app.Application;

public class IptvApplication extends Application {

	private static IptvApplication application; 
	private static String baseurl;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		application=this;
		baseurl=this.getResources().getString(R.string.baseurl);
	}

	public static IptvApplication getApplication() {
		return application;
	}
	
	public static String getBaseurl() {
		return baseurl;
	}
}
