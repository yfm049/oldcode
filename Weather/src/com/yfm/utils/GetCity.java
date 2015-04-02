package com.yfm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GetCity {

	private Context context;
	String weather="http://qq.ip138.com";
	private SqliteUtils su;
	HttpClient hc=new DefaultHttpClient();
	public GetCity(Context context){
		this.context=context;
		su=new SqliteUtils(context);
	}
	public String getCitycode(String cityname){
		String code=null;
		String sql="select * from citylist where name=?";
		SQLiteDatabase sdu=su.getReadableDatabase();
		Cursor cursor=sdu.rawQuery(sql, new String[]{cityname});
		if(cursor.moveToNext()){
			code=cursor.getString(cursor.getColumnIndex("code"));
		}
		cursor.close();
		sdu.close();
		return code;
	}
	public List<weather> getWeather(String code){
		List<weather> lw=new ArrayList<weather>();
		try {
			String uri=weather+code;
			System.out.println(uri);
			HttpClient hc=new DefaultHttpClient();
			HttpGet hg=new HttpGet(uri);
			HttpResponse hr=hc.execute(hg);
			HttpEntity he=hr.getEntity();
			String html=EntityUtils.toString(he,"gb2312");
			Document doc=Jsoup.parse(html);
			Elements eles=doc.getElementsByAttributeValue("bordercolorlight", "#008000");
			Element body=eles.get(0);
			Elements tr=body.getElementsByTag("tr");
			for(int i=1;i<8;i++){
				String day=tr.get(0).child(i).text();
				String img=tr.get(1).child(i).child(0).attr("src");
				String tianqi=tr.get(1).child(i).child(0).attr("alt");
				String temp=tr.get(2).child(i).text();
				String feng=tr.get(3).child(i).text();
				weather wea=new weather();
				wea.setDay(day);
				wea.setImg(weather+img);
				wea.setFeng(feng);
				wea.settemp(temp);
				wea.setTianqi(tianqi);
				lw.add(wea);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lw;
	}
	
}
