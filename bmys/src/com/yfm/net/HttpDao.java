package com.yfm.net;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Dialog;
import android.util.Log;

import com.yfm.pojo.Good;
import com.yfm.pojo.Phone;
import com.yfm.pojo.TongZhi;
import com.yfm.pojo.YuYue;
import com.yfm.pojo.YuYueGrid;

public class HttpDao {
	private static String httpurl="http://118.244.62.98";
	private static final int REQUEST_TIMEOUT = 10 * 1000;// 设置请求超时10秒钟
	private static final int SO_TIMEOUT = 10 * 1000; // 设置等待数据超时时间10秒钟
	private static DefaultHttpClient hc;
	private static BasicHttpParams httpParams;
	static {
		httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
		
	}

	public static String GetJsonData(String url, Dialog dialog) {

		Log.i("url", url);
		try {
			hc = new DefaultHttpClient(httpParams);
			HttpGet hg = new HttpGet(httpurl+url);
			HttpResponse hr = hc.execute(hg);
			Log.i("code", hr.getStatusLine().getStatusCode() + "");
			if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String con = EntityUtils.toString(hr.getEntity(), "UTF-8");
				Log.i("json", con);
				return con;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();
				dialog = null;
			}
		}

		return null;
	}

	public static String PostData(String url, List<NameValuePair> np,
			Dialog dialog) {
		Log.i("url", url);
		try {
			hc = new DefaultHttpClient(httpParams);
			HttpPost hg = new HttpPost(httpurl+url);
			if (np != null && np.size() > 0) {
				hg.setEntity(new UrlEncodedFormEntity(np, "UTF-8"));
			}

			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String con = EntityUtils.toString(hr.getEntity(), "UTF-8");
				Log.i("json", con);
				return con;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();
				dialog = null;
			}
		}

		return null;
	}

	public static List<Good> getGood(String url, Dialog dialog) {
		List<Good> lg = new ArrayList<Good>();
		try {
			String json = GetJsonData(url, dialog);
			if (json != null && !"".equals(json)) {

				JSONArray ja = new JSONArray(json);
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = ja.getJSONObject(i);
					Good good = new Good();
					if (!jo.isNull("ID")) {
						good.setId(jo.getString("ID"));
					}
					if (!jo.isNull("Title")) {
						good.setTitle(jo.getString("Title"));
					}
					if (!jo.isNull("Price")) {
						good.setPrice(jo.getString("Price"));
					}
					if (!jo.isNull("Introduction")) {
						good.setIntro(jo.getString("Introduction"));
					}
					if (!jo.isNull("Count")) {
						good.setPcount(jo.getString("Count"));
					}
					if (!jo.isNull("starttime")) {
						good.setSdate(jo.getString("starttime"));
					}
					if (!jo.isNull("endtime")) {
						good.setEdate(jo.getString("endtime"));
					}
					if (!jo.isNull("ImageUrl")) {
						good.setImageUrl(jo.getString("ImageUrl"));
					}
					if (!jo.isNull("itemtype")) {
						good.setItemtype(jo.getString("itemtype"));
					}
					lg.add(good);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();

			}
		}
		return lg;
	}

	public static String getIntro(String url, Dialog dialog) {
		try {
			String json = GetJsonData(url, dialog);
			if (json != null && !"".equals(json)) {

				JSONObject ja = new JSONObject(json);
				if (!ja.isNull("MobAppContent")) {
					return ja.getString("MobAppContent");
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();

			}
		}
		return null;
	}

	public static List<YuYue> getYuyueList(String url, Dialog dialog) {
		List<YuYue> lg = new ArrayList<YuYue>();
		try {
			String json = GetJsonData(url, dialog);
			if (json != null && !"".equals(json)) {
				JSONArray jot = new JSONArray(json);
				
				for(int i=0;i<jot.length();i++) {
					JSONObject jo = jot.getJSONObject(i);
					if (jo != null) {
						String sz = jo.getString("con");
						YuYue yu = new YuYue();
						yu.setData(jo.getString("time"));
						String[] sp = sz.split(",");
						List<YuYueGrid> lgd = new ArrayList<YuYueGrid>();
						yu.setLyg(lgd);
						Calendar time = Calendar.getInstance();
						Date d = new Date();
						d.setHours(8);
						d.setMinutes(30);
						d.setSeconds(0);
						time.setTime(d);
						for (String s : sp) {
							YuYueGrid yg = new YuYueGrid();
							yg.setValue(s);
							yg.setName(new SimpleDateFormat("HH:mm")
									.format(time.getTime()));
							time.add(Calendar.MINUTE, 30);
							lgd.add(yg);
						}
						lg.add(yu);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();
			}
		}
		return lg;
	}

	public static List<Phone> getPhone(String url, Dialog dialog) {
		List<Phone> lg = new ArrayList<Phone>();
		try {
			String json = GetJsonData(url, dialog);
			if (json != null && !"".equals(json)) {
				JSONArray ja = new JSONArray(json);
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = ja.getJSONObject(i);
					Phone good = new Phone();
					if (!jo.isNull("Title")) {
						good.setTitle(jo.getString("Title"));
					}
					if (!jo.isNull("Tel")) {
						good.setPhonenum(jo.getString("Tel"));
					}
					lg.add(good);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();
			}
		}
		return lg;
	}

	public static List<TongZhi> getMessage(String url, Dialog dialog) {
		List<TongZhi> lg = new ArrayList<TongZhi>();
		try {
			String json = GetJsonDatas(url, dialog);
			if (json != null && !"".equals(json)) {
				JSONArray ja = new JSONArray(json);
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = ja.getJSONObject(i);
					if (!jo.isNull("content")) {
						TongZhi tz = new TongZhi();
						tz.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date()));
						tz.setCon(jo.getString("content"));
						lg.add(tz);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();
			}
		}
		return lg;
	}

	public static String GetJsonDatas(String url, Dialog dialog) {
		Log.i("url", url);
		try {
			hc = new DefaultHttpClient(httpParams);
			HttpGet hg = new HttpGet(httpurl+url);
			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String con = EntityUtils.toString(hr.getEntity(), "UTF-8");
				Log.i("json", con);
				return con;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();
				dialog = null;
			}
		}

		return null;
	}
}
