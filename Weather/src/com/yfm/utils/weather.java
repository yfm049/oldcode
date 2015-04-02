package com.yfm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class weather {

	private String day;
	private Bitmap img;

	public String getDay() {
		return day.split(" ")[1];
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Bitmap getImg() {
		return img;
	}

	public void setImg(String imgurl) {
		try {
			URL url = new URL(imgurl);
			InputStream is = url.openStream();
			this.img = BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getHigh() {
		return high;
	}

	public void settemp(String temp) {
		String t[] = temp.split("бл");
		Log.i("temp", temp);
		if(t.length>1){
			this.high = t[0];
			this.low = t[1];
		}else{
			this.high=t[0];
			this.low="";
		}
		
	}

	public String getLow() {
		return low;
	}

	public String getFeng() {
		return feng;
	}

	public void setFeng(String feng) {
		this.feng = feng;
	}

	private String high;
	private String low;
	private String feng;
	private String tianqi;

	public String getTianqi() {
		return tianqi;
	}

	public void setTianqi(String tianqi) {
		this.tianqi = tianqi;
	}
}
