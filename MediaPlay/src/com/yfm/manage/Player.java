package com.yfm.manage;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class Player {

	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	private MediaPlayer mediaplayer=null;
	private Context context;
	public Player(Context context,String type,String name){
		this.type=type;
		this.name=name;
		this.context=context;
		try {
			mediaplayer=new MediaPlayer();
			mediaplayer.reset();
			AssetFileDescriptor file=context.getAssets().openFd(name);
			mediaplayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean play(){
		try {
			if(mediaplayer!=null){
				mediaplayer.stop();
				mediaplayer.prepare();
				mediaplayer.start();
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean Replay(){
		try {
			if(mediaplayer!=null){
				mediaplayer.stop();
				mediaplayer.prepare();
				mediaplayer.seekTo(0);
				mediaplayer.start();
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean stop(){
		try {
			if(mediaplayer!=null){
				mediaplayer.stop();
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean release(){
		try {
			if(mediaplayer!=null){
				mediaplayer.release();
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
