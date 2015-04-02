package com.yfm.entie;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetActivity extends Activity {


	private EditText ip;
	private EditText port;
	
	private EditText jdpass;
	private EditText afpass;
	private EditText apppass;
	private Button queding;
	private Button quxiao;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        ip=(EditText)super.findViewById(R.id.ip);
        port=(EditText)super.findViewById(R.id.port);
        jdpass=(EditText)super.findViewById(R.id.jdpass);
        afpass=(EditText)super.findViewById(R.id.afpass);
        apppass=(EditText)super.findViewById(R.id.apppass);
        init();//初始化数据
        queding=(Button)super.findViewById(R.id.queding);
        quxiao=(Button)super.findViewById(R.id.quxiao);
       
        queding.setOnClickListener(new OnClickListenerimpl("save"));
        quxiao.setOnClickListener(new OnClickListenerimpl("cencle"));
       
     }
    public boolean isIp(){
    	Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
    	Matcher matcher = pattern.matcher(ip.getText()); 
    	return matcher.matches();
    }
    public void save(){
    	SharedPreferences sp=this.getSharedPreferences("set", MODE_PRIVATE);
    	String p=port.getText().toString();
    	String i=ip.getText().toString();
    	String jd=jdpass.getText().toString();
    	String af=afpass.getText().toString();
    	String app=apppass.getText().toString();
    	if(isIp()&&p.length()>0&&jd.length()==6&&af.length()==6){
    		Editor e=sp.edit();
    		e.putString("ip", i);
    		e.putInt("port", Integer.parseInt(p));
    		e.putString("jdpass", jd);
    		e.putString("afpass", af);
    		e.putString("apppass", app);
    		e.commit();
    		Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
    		SetActivity.this.finish();
    	}else{
    		Toast.makeText(this, "数据填写错误，请重新填写", Toast.LENGTH_LONG).show();
    	}
    }
    public void init(){
    	SharedPreferences sp=this.getSharedPreferences("set", MODE_PRIVATE);
    	String aip=sp.getString("ip", "");
    	int aport=sp.getInt("port", 20625);
    	String jd=sp.getString("jdpass", "");
    	String af=sp.getString("afpass", "");
    	String app=sp.getString("apppass", "");
    	ip.setText(aip);
    	port.setText(String.valueOf(aport));
    	jdpass.setText(jd);
    	afpass.setText(af);
    	apppass.setText(app);
    }
    class OnClickListenerimpl implements OnClickListener{

    	private String action;
    	public OnClickListenerimpl(String action){
    		this.action=action;
    	}
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if("save".equals(action)){
				SetActivity.this.save();
			}
			if("cencle".equals(action)){
				SetActivity.this.init();
			}
			
		}
    	
    }
    
}
