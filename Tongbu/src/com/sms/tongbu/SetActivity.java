package com.sms.tongbu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SetActivity extends Activity {

	private RadioGroup mygroup;
	private EditText num;
	private Button ok,quxiao;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.set);
		sp=this.getSharedPreferences("phone", MODE_PRIVATE);
		mygroup=(RadioGroup)super.findViewById(R.id.mygroup);
		num=(EditText)super.findViewById(R.id.num);
		num.setText(sp.getString("num", ""));
		ok=(Button)super.findViewById(R.id.ok);
		quxiao=(Button)super.findViewById(R.id.quxiao);
		ok.setOnClickListener(new OkOnClickListenerImp());
		quxiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SetActivity.this.finish();
			}
		});
	}
	class OkOnClickListenerImp implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int checkid=mygroup.getCheckedRadioButtonId();
			String p="cube";
			String numSe=num.getText().toString();
			if(R.id.cube==checkid){
				p="cube";
			}else{
				p="mosaic";
			}
			Editor e=sp.edit();
			e.putString("num", numSe);
			e.putString("name", p);
			e.commit();
			Toast.makeText(SetActivity.this, "±£´æ³É¹¦", Toast.LENGTH_SHORT).show();
			SetActivity.this.finish();
		}
		
	}

}
