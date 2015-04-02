package com.yfm.entie;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {


	private EditText wpass;
	
	private Button jinru;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        wpass=(EditText)super.findViewById(R.id.wpass);
        jinru=(Button)super.findViewById(R.id.jinru);
        SharedPreferences sp=this.getSharedPreferences("set", MODE_PRIVATE);
        final String apppass=sp.getString("apppass", "1234");
        jinru.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(apppass.equals(wpass.getText().toString())){
		        	Intent intent=new Intent(LoginActivity.this,MainActivity.class);
		        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        	LoginActivity.this.startActivity(intent);
		        	LoginActivity.this.finish();
		        }else{
		        	Toast.makeText(LoginActivity.this, "√‹¬Î¥ÌŒÛ", Toast.LENGTH_LONG).show();
		        }
				
			}
		});
        
     }
    
}
