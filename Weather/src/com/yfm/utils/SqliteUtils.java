package com.yfm.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteUtils extends SQLiteOpenHelper {

	String weather = "http://qq.ip138.com";
	private static String name = "citylist";
	private static int version = 1;
	private String sql = "create table citylist(code varchar(20),name varchar(30))";
	private Context context;
	HttpClient hc = new DefaultHttpClient();

	public SqliteUtils(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		final ProgressDialog pd = ProgressDialog.show(context, "更新城市列表",
				"请稍后...");
		pd.show();
		db.execSQL(sql);
		Thread th = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i("保存数据", "----");
				try {
					saveDate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					pd.cancel();
				}
			}

		};
		th.start();
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table citylist");
		onCreate(db);
	}

	public void saveDate() throws Exception {
		String url = "http://qq.ip138.com/weather/";
		HttpGet hg = new HttpGet(url);
		HttpResponse hr = hc.execute(hg);
		HttpEntity he = hr.getEntity();
		String provi = EntityUtils.toString(he, "gb2312");
		Document doc = Jsoup.parse(provi);
		Element pro = doc.getElementById("ProvList");
		Elements eles = pro.getElementsByTag("a");
		for (Element e : eles) {
			String name = e.html().trim();
			String href = e.attr("href");
			savecity(href, name);
		}

	}

	public void savecity(String p, String names) throws Exception {
		SqliteUtils su = new SqliteUtils(context);
		SQLiteDatabase sd = su.getWritableDatabase();

		sql = "insert into citylist(name,code) values(?,?)";

		HttpGet hg = new HttpGet(weather + p);
		HttpResponse hr = hc.execute(hg);
		HttpEntity he = hr.getEntity();
		String provi = EntityUtils.toString(he, "gb2312");
		Document doc = Jsoup.parse(provi);
		Element pro = doc.getElementById("CityList");
		if ("上海".equals(names) || "北京".equals(names) || "天津".equals(names)) {
			sd.execSQL(sql, new Object[] { names, p });
		}
		Elements eles = pro.getElementsByTag("a");
		for (Element e : eles) {
			String href = e.attr("href");
			String name = e.html().trim();
			Log.i("保存数据", e.html());
			sd.execSQL(sql, new Object[] { name, href });
		}
		sd.close();

	}

}
