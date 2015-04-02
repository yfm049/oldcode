package com.yfm.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.yfm.manage.MediaPlayManager;

public class MediaPlayService extends Service{

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	public static MediaPlayManager mediaplayManager;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mediaplayManager=new MediaPlayManager(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
