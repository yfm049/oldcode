package com.pro.stu;

import java.util.List;
import java.util.Locale;

import org.apache.http.Header;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.pro.utils.Info;
import com.pro.utils.SQLiteUtils;
import com.pro.utils.WebserviceUtils;

public class MainActivity extends Activity {


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if("设置".equals(item.getTitle())){
			Intent intent = new Intent();
			intent.setAction("com.android.settings.TTS_SETTINGS");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
		if("数据".equals(item.getTitle())){
			update();
		}
		if("退出".equals(item.getTitle())){
			MainActivity.this.finish();
		}
		
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("设置");
		menu.add("数据");
		menu.add("退出");
		return super.onCreateOptionsMenu(menu);
	}
	private EditText code;
	private TextView sm;
	private ImageButton jl;
	private ImageButton update;
	private TextToSpeech ts;
	private ProgressDialog pd;
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==1){
				pd.cancel();
				Toast.makeText(MainActivity.this, "更新成功..", Toast.LENGTH_LONG).show();
			}
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		code=(EditText)findViewById(R.id.code);
		sm=(TextView)findViewById(R.id.sm);
		jl=(ImageButton)findViewById(R.id.jl);
		update=(ImageButton)findViewById(R.id.update);
		code.setOnEditorActionListener(new OnEditorActionListenerImpl());
		ts=new TextToSpeech(this, new OnInitListenerImpl());
		jl.setOnClickListener(new OnClickListenerImpl());
		update.setOnClickListener(new updateOnClickListenerImpl());
		
	}
	class updateOnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			update();
		}
		
	}
	private void update(){
		pd=new ProgressDialog(this);
		pd.setMessage("正在更新数据,请稍后...");
		pd.setCancelable(false);
		pd.show();
		new GetThread().start();
	}
	class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,JiluActivity.class);
			MainActivity.this.startActivity(intent);
		}
		
	}
	class OnInitListenerImpl implements OnInitListener{

		@Override
		public void onInit(int status) {
			// TODO Auto-generated method stub
			ts.setLanguage(Locale.CHINESE);
		}
		
	}
	class OnEditorActionListenerImpl implements OnEditorActionListener{

		@Override
		public boolean onEditorAction(TextView v, int keyCode, KeyEvent event){
			// TODO Auto-generated method stub
			System.out.println(keyCode+"--"+event.getAction());
			if(keyCode==EditorInfo.IME_ACTION_UNSPECIFIED&&event.getAction()==0){
				GetInfo();
				return true;
			}
			return false;
		}
		
	}
	public void GetInfo(){
		SQLiteUtils su=new SQLiteUtils(this);
		List<Info> linfo=su.GetInfo(code.getText().toString());
		if(linfo!=null&&linfo.size()>0){
			Info info=linfo.get(0);
			su.savejl(info);
			sm.setText("卡号:"+info.getPareCard()+"\r\n会员姓名:"+info.getChildName()+"\r\n类型:"+info.getClassName()+"\r\n手机号:"+info.getParePhone()+"\r\n出生日期:"+info.getChildBirth()+"\r\n监管人:"+info.getPareName());
			ts.speak(info.getClassName()+info.getChildName().toString(), 0, null);
			code.setText("");
		}else{
			ts.speak("无效卡", 0, null);
		}
	}
	class GetThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			List<Info> linfo=WebserviceUtils.GetWebXml();
			SQLiteUtils su=new SQLiteUtils(MainActivity.this);
			su.SaveDate(linfo);
			handler.sendEmptyMessage(1);
		}
		
	}
	

}
