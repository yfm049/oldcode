package com.yfm.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import com.yfm.gtzs.MainActivity;
import com.yfm.gtzs.R;
import com.yfm.pojo.HttpDao;
import com.yfm.pojo.Info;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class InfoAdapter extends BaseAdapter {

	private List<Info> li=new ArrayList<Info>();
	public List<Info> getLi() {
		return li;
	}
	private Context context;
	private Dialog dialog;
	public static int currpage=1;
	private String url="http://"+MainActivity.ipport+"/xinxi/servlet/Xinxi";
	public  static boolean isrun=false;
	public InfoAdapter(Context context){
		this.context=context;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return li.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return li.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int pos, View view, ViewGroup arg2) {
		// TODO Auto-generated method st
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.list_item, null);
		}
		TextView cdname=(TextView) view.findViewById(R.id.cdname);
		TextView cplace=(TextView) view.findViewById(R.id.cplace);
		TextView ccusfullname=(TextView) view.findViewById(R.id.ccusfullname);
		TextView cgoodstype=(TextView) view.findViewById(R.id.cgoodstype);
		TextView cgoodsid=(TextView) view.findViewById(R.id.cgoodsid);
		TextView csize=(TextView) view.findViewById(R.id.csize);
		TextView ctextture=(TextView) view.findViewById(R.id.ctextture);
		TextView cprice=(TextView) view.findViewById(R.id.cprice);
		TextView dweight=(TextView) view.findViewById(R.id.dweight);
		TextView dupdatedate=(TextView) view.findViewById(R.id.dupdatedate);
		Info info=li.get(pos);
		cdname.setText(info.getCdname());
		cplace.setText(info.getCplace());
		ccusfullname.setText(info.getCcusfullname());
		cgoodstype.setText(info.getCgoodstype());
		cgoodsid.setText(info.getCgoodsid());
		csize.setText(info.getCsize());
		ctextture.setText(info.getCtextture());
		cprice.setText(info.getCprice());
		dweight.setText(info.getDweight());
		dupdatedate.setText(info.getDupdatedate());
		return view;
	}
	public void getData(List<NameValuePair> lnv){
		Log.i("--", isrun+"");
		if(!isrun){
			if(dialog!=null){
				dialog.cancel();
			}
			dialog=new ProgressDialog(context);
			dialog.setTitle("正在加载...");
			Log.i("--", "正在加载...");
			dialog.show();
			Getdata ga=new Getdata(url+"?pagesize=25&page="+currpage,lnv,dialog);
			ga.start();
		}
	}
	public Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==0){
				InfoAdapter.this.notifyDataSetChanged();
				currpage++;
				isrun=false;
			}else{
				Toast.makeText(context, "加载了0条数据", Toast.LENGTH_SHORT).show();
			}
			
			
		}
		
	};
	class Getdata extends Thread{
		private Dialog dialog;
		private String url;
		private List<NameValuePair> lnv;
		public Getdata(String url,List<NameValuePair> lnv,Dialog dialog){
			this.url=url;
			this.dialog=dialog;
			this.lnv=lnv;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i("isrun", isrun+"");
			try {
					isrun=true;
					List<Info> lgs=HttpDao.getInfos(url, lnv,dialog);
					li.addAll(lgs);
					Log.i("tiso", lgs.size()+"---");
					if(lgs.size()>0){
						handler.sendEmptyMessage(0);
					}else{
						handler.sendEmptyMessage(1);
					}
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
