package com.yfm.ydbg;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.yfm.adapter.DbsxAdapter;
import com.yfm.webservice.SoapUtils;

public class MimagxActivity extends Activity {

	private EditText ymm,xmm,qrmm;
	private ImageView gengxin,quxiao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mimagx);
        ymm=(EditText)super.findViewById(R.id.ymm);
        xmm=(EditText)super.findViewById(R.id.xmm);
        qrmm=(EditText)super.findViewById(R.id.qrmm);
        gengxin=(ImageView)super.findViewById(R.id.gengxin);
        quxiao=(ImageView)super.findViewById(R.id.quxiao);
        gengxin.setOnClickListener(new gengxinOnClickListener());
        quxiao.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MimagxActivity.this.finish();
			}
		});
    }

    class gengxinOnClickListener implements OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			String yuanmima=ymm.getText().toString();
			String xinmima=xmm.getText().toString();
			String querenmima=qrmm.getText().toString();
			if(!yuanmima.equals(FunActivity.pass)){
				Toast.makeText(MimagxActivity.this, "原密码不正确", Toast.LENGTH_SHORT).show();
				return;
			}
			if(xinmima.length()<6&&!xinmima.equals(querenmima)){
				Toast.makeText(MimagxActivity.this, "新密码格式不正确", Toast.LENGTH_SHORT).show();
				return;
			}
			if(!xinmima.equals(querenmima)){
				Toast.makeText(MimagxActivity.this, "确认密码不正确", Toast.LENGTH_SHORT).show();
				return;
			}
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("newpassword", xinmima);
			map.put("account", FunActivity.account);
			ProgressDialog pd=new ProgressDialog(MimagxActivity.this);
			pd.setTitle("密码更新");
			pd.setMessage("正在更新...");
			pd.show();
			SoapUtils.Send(MimagxActivity.this, "UpdatePassword", map, handler, pd);
		}
    	
    }
    private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==1){
				if("true".equals(msg.obj.toString())){
					Toast.makeText(MimagxActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
					MimagxActivity.this.finish();
				}else{
					Toast.makeText(MimagxActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
				}
			}else if(msg.what==2){
				Toast.makeText(MimagxActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}
		}
    	
    };
    
}
