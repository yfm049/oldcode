package com.qq.visitor;

import com.qq.net.HttpUtils;
import com.qq.net.MD5;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText mqq,mpass;
	private ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mqq=(EditText)super.findViewById(R.id.qq);
		mpass=(EditText)super.findViewById(R.id.pass);
	}
	public void login(View v){
		System.out.println("-----------------"+v.getId());
		String qq=mqq.getText().toString();
		String pass=mpass.getText().toString();
		if(!"".equals(qq)&&!"".equals(pass)){
			String url="http://pt.3g.qq.com/login?act=json&format=2&bid_code=qzoneLogin&r=0.29844494513235987&qq="+qq+"&pmd5="+MD5.getMD5Str(pass)+"&go_url=http%3A%2F%2Fm.qzone.com%2Finfocenter%3Fg_f%3D%26sid%3D";
			System.out.println(url);
			new loginAsynTask().execute(url);
		}else{
			Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
		}
		
	}

	class loginAsynTask extends AsyncTask<String,Integer,String>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(MainActivity.this);
			pd.setMessage("正在登录中，请稍后...");
			pd.show();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			String mmsg="";
			if(result!=null){
				System.out.println(result);
				if(result.contains("成功")){
					mmsg="登录成功";
					String key=result.split("\"")[5];
					Intent intent=new Intent(MainActivity.this,VisitorActivity.class);
					intent.putExtra("sid", key);
					MainActivity.this.startActivity(intent);
					MainActivity.this.finish();
				}else{
					mmsg="登录失败,用户名或密码错误";
				}
			}else{
				mmsg="系统忙，请稍后登录";
			}
			Toast.makeText(MainActivity.this, mmsg, Toast.LENGTH_SHORT).show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return HttpUtils.DoGet(params[0]);
		}

		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
