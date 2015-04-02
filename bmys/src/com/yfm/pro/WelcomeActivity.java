package com.yfm.pro;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class WelcomeActivity extends ActivityGroup {

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		Thread th=new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(4000);
					Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
					WelcomeActivity.this.startActivity(intent);
					WelcomeActivity.this.finish();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				super.run();
			}
			
		};
		th.start();
	}

}
