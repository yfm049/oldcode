package com.yfm.gtzs;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yfm.pojo.Info;

public class DetailActivity extends Activity {

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Intent intent=this.getIntent();
		Serializable sa=intent.getSerializableExtra("info");
		if(sa!=null){
			Info info=(Info)sa;
			ccusfullname.setText(info.getCcusfullname());
			ccusperson.setText(info.getCcusperson());
			ccusphone.setText(info.getCcusphone());
			ccusmobile.setText(info.getCcusmobile());
			ccusfax.setText(info.getCcusfax());
			qq.setText(info.getQq());
			email.setText(info.getEmail());
			ccusaddress.setText(info.getCcusaddress());
			beizhu.setText(info.getBeizhu());
		}
	}
		// 公司名称
		private TextView ccusfullname;
		// 联系人
		private TextView ccusperson;
		// 联系电话
		private TextView ccusphone;
		// 手机号码
		private TextView ccusmobile;
		// 会员传真
		private TextView ccusfax;
		// qq号码
		private TextView qq;
		// email
		private TextView email;
		// 详细地址
		private TextView ccusaddress;
		// 备注
		private TextView beizhu;
		private ImageView phone,mphone;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detail_main);
        ccusfullname=(TextView)super.findViewById(R.id.ccusfullname);
        ccusperson=(TextView)super.findViewById(R.id.ccusperson);
        ccusphone=(TextView)super.findViewById(R.id.ccusphone);
        ccusmobile=(TextView)super.findViewById(R.id.ccusmobile);
        ccusfax=(TextView)super.findViewById(R.id.ccusfax);
        qq=(TextView)super.findViewById(R.id.qq);
        email=(TextView)super.findViewById(R.id.email);
        ccusaddress=(TextView)super.findViewById(R.id.ccusaddress);
        beizhu=(TextView)super.findViewById(R.id.beizhu);
        ccusphone.setOnClickListener(new OnClickListenerImpl(1));
        ccusmobile.setOnClickListener(new OnClickListenerImpl(2));
        phone=(ImageView)super.findViewById(R.id.phone);
        phone.setOnClickListener(new OnClickListenerImpl(1));
        mphone=(ImageView)super.findViewById(R.id.mphone);
        mphone.setOnClickListener(new OnClickListenerImpl(2));
    }
    class OnClickListenerImpl implements OnClickListener{
    	private int i;
    	public OnClickListenerImpl(int i){
    		this.i=i;
    	}
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String phone=null;
			if(i==1){
				phone=ccusphone.getText().toString();
			}
			if(i==2){
				phone=ccusmobile.getText().toString();
			}
			if(phone!=null&&!"".equals(phone)){
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
	             //通知activtity处理传入的call服务
				 DetailActivity.this.startActivity(intent);
			}
			
		}
    	
    }
   
    
}
