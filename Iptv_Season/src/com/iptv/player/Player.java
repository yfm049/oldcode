package com.iptv.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Player {

	private Context context;
	private SurfaceHolder holder;
	private MediaPlayer mediaplayer;
	private Mediaplayerlistener listener;

	public Player(Context context, SurfaceView playview) {
		this.context = context;
		holder = playview.getHolder();
		holder.setKeepScreenOn(true);
		mediaplayer = new MediaPlayer();
		listener = new Mediaplayerlistener();
		mediaplayer.setOnPreparedListener(listener);
		mediaplayer.setOnInfoListener(listener);
		mediaplayer.setOnErrorListener(listener);
		mediaplayer.setOnVideoSizeChangedListener(listener);
		mediaplayer.setOnCompletionListener(listener);
		mediaplayer.setOnSeekCompleteListener(listener);
		holder.addCallback(listener);
	}

	public void start(String url){
		mediaplayer.reset();
		try {
			mediaplayer.setDisplay(holder);
			mediaplayer.setDataSource(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		mediaplayer.prepareAsync();
	}
	public void play(){
		mediaplayer.start();
	}
	public void pause(){
		mediaplayer.pause();
	}
	public void stop(){
		if (mediaplayer != null) {
			mediaplayer.stop();
			mediaplayer.reset();
		}
	}
	public void release(){
		if(mediaplayer!=null){
			mediaplayer.release();
		}
	}
	
	class Mediaplayerlistener implements OnPreparedListener, OnInfoListener,
			OnErrorListener, OnVideoSizeChangedListener, OnCompletionListener,
			OnSeekCompleteListener,SurfaceHolder.Callback {

		@Override
		public void onPrepared(MediaPlayer mp) {
			// TODO Auto-generated method stub
			play();
		}

		@Override
		public boolean onInfo(MediaPlayer mp, int code, int extra) {
			// TODO Auto-generated method stub
			if (code == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
				pause();
			} else if (code == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
				play();
			}
			return true;
		}

		@Override
		public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onVideoSizeChanged(MediaPlayer arg0, int width, int height) {
			// TODO Auto-generated method stub
			if(width>0&&height>0){
				holder.setFixedSize(width, height);
			}
		}

		@Override
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			stop();
		}

		@Override
		public void onSeekComplete(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			play();
		}
		
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			mediaplayer.setDisplay(holder);
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			release();
		}

	}
}
