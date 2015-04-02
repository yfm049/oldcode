package com.yfm.pro;

import android.app.ActivityGroup;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yfm.adapter.LbAdapter;
import com.yfm.net.HttpDao;
import com.yfm.net.OnClickListenerImpl;
import com.yfm.pojo.Good;

public class DetailActivity extends ActivityGroup {

	private TextView fenlei,title,sdate,edate,pcount,intr,price;
	private LinearLayout sdata,edata,count,lprice;
	private Button btn,back;
	public Good good;
	private Dialog dialog;
	private ImageView img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detail_item);
        img=(ImageView)super.findViewById(R.id.img);
        fenlei=(TextView)super.findViewById(R.id.fenlei);
        title=(TextView)super.findViewById(R.id.title);
        sdate=(TextView)super.findViewById(R.id.sdate);
        edate=(TextView)super.findViewById(R.id.edate);
        
        pcount=(TextView)super.findViewById(R.id.pcount);
        intr=(TextView)super.findViewById(R.id.intr);
        price=(TextView)super.findViewById(R.id.price);
        sdata=(LinearLayout)super.findViewById(R.id.sdata);
        edata=(LinearLayout)super.findViewById(R.id.edata);
        
        count=(LinearLayout)super.findViewById(R.id.count);
        lprice=(LinearLayout)super.findViewById(R.id.lprice);
        btn=(Button)super.findViewById(R.id.btn);
        back=(Button)super.findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DetailActivity.this.finish();
			}
		});
        Intent intent=this.getIntent();
        good=(Good)intent.getSerializableExtra("good");
        btn.setOnClickListener(new OnClickListenerImpl(this, 1, good));
        //fenlei.setText(good.getFenlei());
        title.setText(good.getTitle());
        intr.setText(good.getIntro());
        Drawable cachedImage = LbAdapter.asyncImageLoader.loadDrawable(good.getImageUrl(),img);
		if (cachedImage == null) {
			img.setImageResource(R.drawable.def);
		}else{
			img.setImageDrawable(cachedImage);
		}
        if(good.getIntro()==null||"".equals(good.getIntro())){
        	dialog=new ProgressDialog(DetailActivity.this);
			dialog.setTitle("正在加载...");
			Log.i("--", "正在加载...");
			dialog.show();
			Getdata ga=new Getdata(good.getXxurl(),dialog);
			ga.start();
        }
        if(good.getSdate()!=null&&!"".equals(good.getSdate())){
        	sdate.setText(good.getSdate());
        	sdata.setVisibility(View.VISIBLE);
        }
        if(good.getEdate()!=null&&!"".equals(good.getEdate())){
        	edate.setText(good.getEdate());
        	edata.setVisibility(View.VISIBLE);
        }
        if(good.getPcount()!=null&&!"".equals(good.getPcount())){
        	pcount.setText(good.getPcount());
        	count.setVisibility(View.VISIBLE);
        }
        if(good.getPrice()!=null&&!"".equals(good.getPrice())){
        	price.setText(good.getPrice());
        	lprice.setVisibility(View.VISIBLE);
        }
    }
    public Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==0){
				good.setIntro(msg.obj.toString());
				intr.setText(msg.obj.toString()+"");
			}else{
				Toast.makeText(DetailActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
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
			try{
					String  lgs=HttpDao.getIntro(url, dialog);
					if(lgs!=null){
						Message msg=handler.obtainMessage();
						msg.obj=lgs;
						handler.sendMessage(msg);
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
