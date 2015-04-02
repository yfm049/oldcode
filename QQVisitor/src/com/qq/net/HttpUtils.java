package com.qq.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Environment;

public class HttpUtils {

	private static HttpClient httpClient;
	static{
		HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params,
                HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, true);
 
        // 设置timeout
        HttpConnectionParams.setConnectionTimeout(params, 15000);
        HttpConnectionParams.setSoTimeout(params, 15000);
        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        schReg.register(new Scheme("https",
                SSLSocketFactory.getSocketFactory(), 443));
 
        // 线程安全的
        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
                params, schReg);
 
        httpClient = new DefaultHttpClient(conMgr, params);
	}
	public static String DoGet(String url){
		String con=null;
		try {
			HttpGet hg=new HttpGet(url);
			HttpResponse hr=httpClient.execute(hg);
			if(hr.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				con=EntityUtils.toString(hr.getEntity());
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static int ParseJson(String m,int index,List<String> childs){
		int totalpage=0;
		try {
			if(m!=null&&!"".equals(m)){
				JSONObject jo=new JSONObject(m);
				int code=jo.getInt("code");
				if(code==0){
					JSONObject data=jo.getJSONObject("data");
					JSONArray list=data.getJSONArray("list");
					totalpage=data.getInt("totalpage");
					for(int i=0;i<list.length();i++){
						JSONObject item=list.getJSONObject(i);
						childs.add(item.getString("uin"));
					}
				}
			}else{
				--index;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalpage;
	}
	public static List<String> read(){
		List<String> qq=new ArrayList<String>();
		try {
			if (android.os.Environment.getExternalStorageState().equals(  
				    android.os.Environment.MEDIA_MOUNTED)){
				File path = Environment.getExternalStorageDirectory();
				File file=new File(path, "qq.txt");
				FileInputStream fis=new FileInputStream(file);
				InputStreamReader isr=new InputStreamReader(fis);
				BufferedReader br=new BufferedReader(isr);
				String p=null;
				
				while((p=br.readLine())!=null){
					qq.add(p);
				}
				br.close();
				isr.close();
				fis.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qq;
	}
	public static void exportSd(List<String> qqs){
		try {
			if (android.os.Environment.getExternalStorageState().equals(  
				    android.os.Environment.MEDIA_MOUNTED)){
				File path = Environment.getExternalStorageDirectory();
				File file=new File(path, "qq.txt");
				FileOutputStream fos=new FileOutputStream(file);
				OutputStreamWriter osw=new OutputStreamWriter(fos);
				BufferedWriter bw=new BufferedWriter(osw);
				for(String qq:qqs){
					bw.write(qq+"\r\n");
				}
				bw.close();
				osw.close();
				fos.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
