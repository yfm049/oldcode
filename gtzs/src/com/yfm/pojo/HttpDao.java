package com.yfm.pojo;

import java.util.ArrayList;
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
import android.os.Handler;
import android.util.Log;


public class HttpDao {

	private static final int REQUEST_TIMEOUT = 10*1000;//设置请求超时10秒钟  
	private static final int SO_TIMEOUT = 10*1000;  //设置等待数据超时时间10秒钟  
	private static DefaultHttpClient hc ;	
	private static BasicHttpParams httpParams;
	public static Handler handler=null;
	static{
		httpParams = new BasicHttpParams();  
	    HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);  
	    HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT); 
	    hc = new DefaultHttpClient(httpParams);
	}

	public static String GetJsonData(String url, List<NameValuePair> np,Dialog dialog) {
		
		Log.i("url", url);
		try {
			
			HttpPost hg = new HttpPost(url);
			if (np != null && np.size() > 0) {
				hg.setEntity(new UrlEncodedFormEntity(np, "UTF-8"));
			}

			HttpResponse hr = hc.execute(hg);
			Log.i("code", hr.getStatusLine().getStatusCode()+"");
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
			HttpPost hg = new HttpPost(url);
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

	public static List<Info> getInfos(String url, List<NameValuePair> lnv,Dialog dialog) {
		List<Info> lg = new ArrayList<Info>();
		try {
			String json = GetJsonData(url,lnv, dialog);
			if (json != null && !"".equals(json)) {

				JSONArray ja = new JSONArray(json);
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = ja.getJSONObject(i);
					Info info = new Info();
					if (!jo.isNull("cgoodsid")) {
						info.setCgoodsid(jo.getString("cgoodsid"));
					}
					if (!jo.isNull("cgoodstype")) {
						info.setCgoodstype(jo.getString("cgoodstype"));
					}
					if (!jo.isNull("csize")) {
						info.setCsize(jo.getString("csize"));
					}
					if (!jo.isNull("ctexture")) {
						info.setCtextture(jo.getString("ctexture"));
					}
					if (!jo.isNull("cplace")) {
						info.setCplace(jo.getString("cplace"));
					}
					if (!jo.isNull("dprice")) {
						info.setCprice(jo.getString("dprice"));
					}
					if (!jo.isNull("dweight")) {
						info.setDweight(jo.getString("dweight"));
					}
					if (!jo.isNull("cdname")) {
						info.setCdname(jo.getString("cdname"));
					}
					if (!jo.isNull("dupdatedate")) {
						info.setDupdatedate(jo.getString("dupdatedate"));
					}
					if (!jo.isNull("ccusfullname")) {
						info.setCcusfullname(jo.getString("ccusfullname"));
					}
					if (!jo.isNull("ccusperson")) {
						info.setCcusperson(jo.getString("ccusperson"));
					}
					if (!jo.isNull("ccusphone")) {
						info.setCcusphone(jo.getString("ccusphone"));
					}
					if (!jo.isNull("ccusmobile")) {
						info.setCcusmobile(jo.getString("ccusmobile"));
					}
					if (!jo.isNull("ccusfax")) {
						info.setCcusfax(jo.getString("ccusfax"));
					}
					if (!jo.isNull("qq")) {
						info.setQq(jo.getString("qq"));
					}
					if (!jo.isNull("email")) {
						info.setEmail(jo.getString("email"));
					}
					if (!jo.isNull("ccusaddress")) {
						info.setCcusaddress(jo.getString("ccusaddress"));
					}
					if (!jo.isNull("beizhu")) {
						info.setBeizhu(jo.getString("beizhu"));
					}
					
					lg.add(info);
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

//	public static List<YuYue> getYuyueList(String url, Dialog dialog) {
//		List<YuYue> lg = new ArrayList<YuYue>();
//		try {
//			String json = GetJsonData(url, dialog);
//			if (json != null && !"".equals(json)) {
//				JSONObject jot = new JSONObject(json);
//				Iterator ito = jot.keys();
//				while (ito.hasNext()) {
//					Object o = ito.next();
//					if (o != null) {
//						String sz = jot.getString(o.toString());
//						YuYue yu = new YuYue();
//						yu.setData(o.toString());
//						String[] sp = sz.split(",");
//						List<YuYueGrid> lgd = new ArrayList<YuYueGrid>();
//						yu.setLyg(lgd);
//						Calendar time = Calendar.getInstance();
//						Date d = new Date();
//						d.setHours(8);
//						d.setMinutes(30);
//						d.setSeconds(0);
//						time.setTime(d);
//						for (String s : sp) {
//							YuYueGrid yg = new YuYueGrid();
//							yg.setValue(s);
//							yg.setName(new SimpleDateFormat("HH:mm")
//									.format(time.getTime()));
//							time.add(Calendar.MINUTE, 30);
//							lgd.add(yg);
//						}
//						lg.add(yu);
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (dialog != null) {
//				dialog.cancel();
//			}
//		}
//		return lg;
//	}
//
//	public static List<Phone> getPhone(String url, Dialog dialog) {
//		List<Phone> lg = new ArrayList<Phone>();
//		try {
//			String json = GetJsonData(url, dialog);
//			if (json != null && !"".equals(json)) {
//				JSONArray ja = new JSONArray(json);
//				for (int i = 0; i < ja.length(); i++) {
//					JSONObject jo = ja.getJSONObject(i);
//					Phone good = new Phone();
//					if (!jo.isNull("Title")) {
//						good.setTitle(jo.getString("Title"));
//					}
//					if (!jo.isNull("Tel")) {
//						good.setPhonenum(jo.getString("Tel"));
//					}
//					lg.add(good);
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (dialog != null) {
//				dialog.cancel();
//			}
//		}
//		return lg;
//	}
//
//	public static List<TongZhi> getMessage(String url, Dialog dialog) {
//		List<TongZhi> lg = new ArrayList<TongZhi>();
//		try {
//			String json = GetJsonDatas(url, dialog);
//			if (json != null && !"".equals(json)) {
//				JSONArray ja = new JSONArray(json);
//				for (int i = 0; i < ja.length(); i++) {
//					JSONObject jo = ja.getJSONObject(i);
//					if (!jo.isNull("content")) {
//						TongZhi tz = new TongZhi();
//						tz.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//								.format(new Date()));
//						tz.setCon(jo.getString("content"));
//						lg.add(tz);
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (dialog != null) {
//				dialog.cancel();
//			}
//		}
//		return lg;
//	}
//
//	public static String GetJsonDatas(String url, Dialog dialog) {
//		Log.i("url", url);
//		try {
//			DefaultHttpClient dhc = new DefaultHttpClient();
//			HttpGet hg = new HttpGet(url);
//			HttpResponse hr = dhc.execute(hg);
//			if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				String con = EntityUtils.toString(hr.getEntity(), "UTF-8");
//				Log.i("json", con);
//				return con;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (dialog != null) {
//				dialog.cancel();
//				dialog = null;
//			}
//		}
//
//		return null;
//	}
}
