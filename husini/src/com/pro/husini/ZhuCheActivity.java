package com.pro.husini;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pro.utils.SimpleCrypto;

public class ZhuCheActivity extends Activity {


	private EditText apkxlh,apkzcm;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sp=this.getSharedPreferences("config", MODE_PRIVATE);
		if(sp.getBoolean("ok", false)){
			Intent intent=new Intent(this,MainActivity.class);
			this.startActivity(intent);
			this.finish();
		}
		setContentView(R.layout.activity_zhuce);
		apkxlh=(EditText)super.findViewById(R.id.apkxlh);
		apkzcm=(EditText)super.findViewById(R.id.apkzcm);
		apkxlh.setText(getImei());
		
	}

	public String getImei(){
		TelephonyManager telephonemanage = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonemanage.getDeviceId();
	}
	public void yanzheng(View but){
		String apkm=apkzcm.getText().toString();
		String skm=SimpleCrypto.GetMD5Code(getImei());
		if(skm.length()>=32){
			skm=skm.substring(27, 32);
			if(skm.equalsIgnoreCase(apkm)){
				Editor dt=sp.edit();;
				dt.putBoolean("ok", true);
				dt.commit();
				Intent intent=new Intent(this,MainActivity.class);
				this.startActivity(intent);
				this.finish();
			}else{
				Toast.makeText(this, "×¢²áÂë´íÎó", Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(this, "×¢²áÂë´íÎó", Toast.LENGTH_SHORT).show();
		}
	}
}
