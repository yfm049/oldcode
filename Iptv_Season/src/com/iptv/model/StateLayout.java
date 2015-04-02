package com.iptv.model;

import com.iptv.season_new.R;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StateLayout {

	
	public StateLayout(Activity tv){
		statelayout=(LinearLayout)tv.findViewById(R.id.statelayout);
		tvstate=(TextView)tv.findViewById(R.id.tvstate);
		tvname=(TextView)tv.findViewById(R.id.tvname);
		speed=(TextView)tv.findViewById(R.id.speed);
		tvcode=(TextView)tv.findViewById(R.id.tvcode);
	}
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		}
		
	};
	public void hide(){
		statelayout.setVisibility(View.INVISIBLE);
	}
	public void show(){
		statelayout.setVisibility(View.VISIBLE);
	}
	public void showspeed(int kb){
		speed.setText("("+kb+")");
	}
	
	private LinearLayout statelayout;
	public LinearLayout getStatelayout() {
		return statelayout;
	}
	public void setStatelayout(LinearLayout statelayout) {
		this.statelayout = statelayout;
	}
	public TextView getTvstate() {
		return tvstate;
	}
	public void setTvstate(TextView tvstate) {
		this.tvstate = tvstate;
	}
	public TextView getTvname() {
		return tvname;
	}
	public void setTvname(TextView tvname) {
		this.tvname = tvname;
	}
	public TextView getSpeed() {
		return speed;
	}
	public void setSpeed(TextView speed) {
		this.speed = speed;
	}
	public TextView getTvcode() {
		return tvcode;
	}
	public void setTvcode(TextView tvcode) {
		this.tvcode = tvcode;
	}
	private TextView tvstate;
	private TextView tvname;
	private TextView speed;
	private TextView tvcode;
	
}
