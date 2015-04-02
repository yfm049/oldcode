package com.yfm.adapter;

import java.util.List;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yfm.net.HttpDao;
import com.yfm.pojo.Good;
import com.yfm.pro.MainActivity;
import com.yfm.pro.R;

public class ShouYeAdapter extends LbAdapter {

	private Context context;
	private Dialog dialog;
	private int currpage=1;
	private String url="/mobapp/Index";

	
	public ShouYeAdapter(Context context){
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lg.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return lg.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Good gd=lg.get(arg0);
		view=LayoutInflater.from(context).inflate(R.layout.shouye_item, null);
		TextView id=(TextView)view.findViewById(R.id.id);
		TextView title=(TextView)view.findViewById(R.id.title);
		TextView itemtype=(TextView)view.findViewById(R.id.itemtype);
		
		if("1".equals(gd.getItemtype())){
			gd.setFenlei("详情");
			gd.setUrl(ShangPinAdapter.aurl);
			gd.setXxurl(ShangPinAdapter.xxurl+gd.getId());
			itemtype.setText("类型:商品");
			
		}else if("2".equals(gd.getItemtype())){
			
			gd.setFenlei("详情");
			gd.setUrl(ActiveAdapter.aurl);
			gd.setXxurl(ActiveAdapter.xxurl+gd.getId());
			itemtype.setText("类型:活动");
			
		}else if("3".equals(gd.getItemtype())){
			
			gd.setFenlei("详情");
			gd.setUrl(GroupBuyAdapter.aurl);
			gd.setXxurl(GroupBuyAdapter.xxurl+gd.getId());
			itemtype.setText("类型:团购");
		}else if("4".equals(gd.getItemtype())){
		
			gd.setFenlei("详情");
			gd.setUrl(PartyAdapter.aurl);
			gd.setXxurl(PartyAdapter.xxurl+gd.getId());
			itemtype.setText("类型:俱乐部");
			
		}else if("5".equals(gd.getItemtype())){
			
			gd.setFenlei("详情");
			gd.setUrl(HuiShouAdapter.aurl);
			gd.setXxurl(HuiShouAdapter.xxurl+gd.getId());
			itemtype.setText("类型:回收");
		}
		id.setText(gd.getId());
		title.setText(gd.getTitle());
		Log.i("gd", gd.toString()+"--"+arg0);
		
		TextView pcount=(TextView)view.findViewById(R.id.pcount);
		pcount.setText(gd.getPcount());
		ImageView imageView=(ImageView)view.findViewById(R.id.img);
		Drawable cachedImage = asyncImageLoader.loadDrawable(gd.getImageUrl(),imageView);
		if (cachedImage == null) {
			imageView.setImageResource(R.drawable.def);
		}else{
			imageView.setImageDrawable(cachedImage);
		}
		
		return view;
	}
	
	public void getData(){
		Log.i("--", isrun+"");
		if(!isrun){
			if(dialog!=null){
				dialog.cancel();
			}
			dialog=new ProgressDialog(context);
			dialog.setTitle("正在加载...");
			Log.i("--", "正在加载...");
			dialog.show();
			Getdata ga=new Getdata(url+"?cid="+MainActivity.cid+"&page="+currpage,dialog);
			ga.start();
		}
	}
	public Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==0){
				ShouYeAdapter.this.notifyDataSetChanged();
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
		public Getdata(String url,Dialog dialog){
			this.url=url;
			this.dialog=dialog;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i("isrun", isrun+"");
			try {
					isrun=true;
					List<Good> lgs=HttpDao.getGood(url, dialog);
					lg.addAll(lgs);
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
