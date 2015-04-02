package com.yfm.sms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetEmailActivity extends Activity {
    /** Called when the activity is first created. */
	private EditText email;
	private Button save;
	private SharedPreferences sp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        Intent intent=new Intent(this,BootServer.class);
		this.startService(intent);
        email=(EditText)super.findViewById(R.id.email);
        save=(Button)super.findViewById(R.id.save);
        save.setOnClickListener(new saveOnClickListener());
        sp= this.getSharedPreferences("set", MODE_PRIVATE);
        email.setText(sp.getString("email", ""));
    }
    class saveOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String value=email.getText().toString();
			if(checkEmail(value)){
				Editor edit=sp.edit();
				edit.putString("email", value);
				edit.commit();
				Toast.makeText(SetEmailActivity.this, "设置成功.", Toast.LENGTH_LONG).show();
				Intent location = new Intent("com.yfm.sms.test");
				SetEmailActivity.this.sendBroadcast(location);
				SetEmailActivity.this.finish();
			}else{
				Toast.makeText(SetEmailActivity.this, "邮箱格式不正确", Toast.LENGTH_LONG).show();
			}
		}
    	
    }
    public boolean checkEmail(String mail){  
        String regex = "\\w+([-+.]\\w+)*@163.com";  
        Pattern   p   =   Pattern.compile(regex);  
        Matcher   m   =   p.matcher(mail);  
        return m.find();  
    }  
}