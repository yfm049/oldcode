package com.yfm.ydbg;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yfm.pojo.Dbsx;
import com.yfm.webservice.SoapUtils;

public class DbsxHuiFuActivity extends Activity {

	private TextView EventTitle,EventContent;
	private ImageView EventImgUrl;
	private EditText huifu;
	private ImageView submit;
	private Dbsx dbsx=null;
	private String url=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dbsx_huifu);
        EventTitle=(TextView)super.findViewById(R.id.EventTitle);
        EventContent=(TextView)super.findViewById(R.id.EventContent);
        EventImgUrl=(ImageView)super.findViewById(R.id.EventImgUrl);
        huifu=(EditText)super.findViewById(R.id.huifu);
        submit=(ImageView)super.findViewById(R.id.submit);
        submit.setOnClickListener(new submitOnClickListener());
        Serializable si= this.getIntent().getSerializableExtra("dbsx");
        if(si!=null){
        	dbsx=(Dbsx)si;
        	EventTitle.setText(dbsx.getEventTitle());
        	EventContent.setText(dbsx.getEventContent());
        	url=dbsx.getEventImgUrl();
        	ImageThread it=new ImageThread();
        	it.start();
        }
        
    }
    class submitOnClickListener implements OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(dbsx!=null){
				String hf=huifu.getText().toString();
				String EventID=dbsx.getEventID();
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("EventID", EventID);
				map.put("feedbackContent", hf);
				ProgressDialog pd=new ProgressDialog(DbsxHuiFuActivity.this);
				pd.setTitle("回复中");
				pd.setMessage("请稍后...");
				pd.show();
				SoapUtils.Send(DbsxHuiFuActivity.this, "FeedBackEvent", map, handler, pd);
			}
		}
    }
    private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int what=msg.what;
			if(what==1){
				String ms=msg.obj.toString();
				if("true".equals(ms)){
					Toast.makeText(DbsxHuiFuActivity.this, "信息回复成功", Toast.LENGTH_SHORT).show();
					DbsxHuiFuActivity.this.finish();
				}else{
					Toast.makeText(DbsxHuiFuActivity.this, "信息回复失败", Toast.LENGTH_SHORT).show();
				}
			}
			if(what==100){
				Object d=msg.obj;
				if(d!=null){
					EventImgUrl.setImageDrawable((Drawable)msg.obj);
				}
				
			}
		}
    	
    };
    class ImageThread extends Thread{
    	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Drawable drawable= loadImageFromUrl();
			 Message message = handler.obtainMessage(100, drawable);
             handler.sendMessage(message);
		}
    	
    };
    public Drawable loadImageFromUrl() {
		URL m;
		InputStream i = null;
		try {
			m = new URL(url);
			i = (InputStream) m.getContent();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Drawable d = Drawable.createFromStream(i, "src");
		return d;
	}
    
}
