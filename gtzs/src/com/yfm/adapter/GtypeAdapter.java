package com.yfm.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yfm.adapter.GSidAdapter.cgoodsnameThread;
import com.yfm.gtzs.MainActivity;
import com.yfm.gtzs.R;
import com.yfm.pojo.HttpDao;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GtypeAdapter extends BaseAdapter {

	public String url="http://"+MainActivity.ipport+"/xinxi/servlet/GoodsType";
	private Dialog dialog ;
	private List<String> ls=new ArrayList<String>();
	public List<String> getLs() {
		return ls;
	}
	private Context context;
	public GtypeAdapter(Context context){
		this.context=context;
		initdate();
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return ls.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return ls.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.spinner_item, null);
			
		}
		TextView xianshi=(TextView)view.findViewById(R.id.xianshi);
		xianshi.setText(ls.get(arg0));
		return view;
	}
	public void initdate(){
		ls.clear();
		dialog=new ProgressDialog(context);
		dialog.setTitle("正在获取分类列表...");
		dialog.show();
		new cgoodsnameThread().start();
	}
	class cgoodsnameThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			String json=HttpDao.GetJsonData(url, null, dialog);
			if(json!=null){
				try {
					JSONArray ja=new JSONArray(json);
					for(int i=0;i<ja.length();i++){
						JSONObject jo=ja.getJSONObject(i);
						ls.add(jo.getString("cgoodstype"));
					}
					handler.sendEmptyMessage(0);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			GtypeAdapter.this.notifyDataSetChanged();
		}
		
	};
}
