package com.sms.tongbu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class WelcomeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,WindowManager.LayoutParams. FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome);
        Thread th=new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
				WelcomeActivity.this.startActivity(intent);
				WelcomeActivity.this.finish();
			}
        	
        };
        th.start();
    }


    
}
