package com.yfm.autoanswer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class HeipingActicity extends Activity {

	public static Activity heiping;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		try {
			super.onCreate(savedInstanceState);

			WindowManager.LayoutParams params = getWindow().getAttributes();
			        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
			        params.screenBrightness = 0;
			        getWindow().setAttributes(params);
			super.requestWindowFeature(Window.FEATURE_NO_TITLE);
			this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
			super.setContentView(R.layout.screen);
			heiping=this;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
