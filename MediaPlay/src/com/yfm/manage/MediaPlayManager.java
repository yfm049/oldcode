package com.yfm.manage;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetFileDescriptor;

public class MediaPlayManager {

	private List<Player> lp=new ArrayList<Player>();
	public List<Player> getLp() {
		return lp;
	}
	private Context context;
	public MediaPlayManager(Context context){
		this.context=context;
	}
	public boolean addPlayer(String type,String name){
		Player player=new Player(context,type, name);
		lp.add(player);
		return true;
	}
	public void PlayAll(){
		for(Player player:lp){
			player.play();
		}
	}
	public void PlayItem(String name){
		for(Player player:lp){
			if(player.getName().equals(name)){
				player.play();
			}
		}
	}
	public void RePlayAll(){
		for(Player player:lp){
			player.Replay();
		}
	}
	public void RePlayItem(String name){
		for(Player player:lp){
			if(player.getName().equals(name)){
				player.Replay();
			}
			
		}
	}
	public void StopAll(){
		for(Player player:lp){
			player.stop();
		}
	}
	public void StopItem(String name){
		for(Player player:lp){
			if(player.getName().equals(name)){
				player.stop();
			}
			
		}
	}
	public void ClearAll(){
		for(Player player:lp){
			player.release();
		}
		lp.clear();
	}
	public void ClearItem(String name){
		for(Player player:lp){
			if(player.getName().equals(name)){
				player.release();
				lp.remove(player);
			}
			
		}
	}
	
}
