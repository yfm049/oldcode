package com.qq.visitor;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.qq.adapter.ListAdapter;
import com.qq.net.HttpUtils;

public class VisitorActivity extends Activity {

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		stop();
		finish();
	}

	private EditText qqs;
	private ProgressDialog pd;
	private String sid;
	private ListView listview;
	
	private List<String> listData;
	
	private ListAdapter adapter;
	private VisitorAsynTask task;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_visitor);
		qqs = (EditText) super.findViewById(R.id.qqs);
		sid=this.getIntent().getStringExtra("sid");
		listview=(ListView)super.findViewById(R.id.visitor);
		
		listData=new ArrayList<String>();
		adapter=new ListAdapter(this, listData);
		listview.setAdapter(adapter);
	}

	public void start(View v) {
		Button but=(Button)this.findViewById(R.id.start);
		if("开始获取".equals(but.getText())){
			caiji();
		}else{
			stop();
		}
		
	}
	public void caiji(){
		String qq = qqs.getText().toString();
		if (!"".equals(qq)) {
			task=new VisitorAsynTask();
			task.execute(qq);
			Button but=(Button)this.findViewById(R.id.start);
			but.setText("停止获取");
		} else {
			Toast.makeText(this, "请输入qq号码", Toast.LENGTH_SHORT).show();
		}
	}
	public void stop(){
		Button but=(Button)this.findViewById(R.id.start);
		but.setText("开始获取");
		handler.removeMessages(100);
		task.cancel(true);
	}
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==100){
				caiji();
			}
		}
		
	};
	

	class VisitorAsynTask extends AsyncTask<String, Integer, String> {

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			adapter.notifyDataSetChanged();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(VisitorActivity.this);
			pd.setMessage("正在获取中，请稍后...");
			pd.show();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			handler.sendEmptyMessageDelayed(100, 60000);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String[] qvals = params[0].split(";");
			listData.clear();
			for (int i=0;i<qvals.length;i++) {
				int totalpage=1;
				for(int j=1;j<=totalpage;j++){
					String url = "http://m.qzone.com/mqz_get_visitor?res_mode=0&res_uin="
							+ qvals[i]
							+ "&offset=0&count=10&page="+j+"&format=json&t=1327850441996&sid="
							+ sid;
					String m = HttpUtils.DoGet(url);
					totalpage=HttpUtils.ParseJson(m,i, listData);
				}
				publishProgress(i);
			}
			
			List<String> qqs=HttpUtils.read();
			qqs.removeAll(listData);
			qqs.addAll(listData);
			HttpUtils.exportSd(qqs);
			return "suc";
		}

	}

	public void showFileChooser(View v) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("text/plain");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		try {
			startActivityForResult(Intent.createChooser(intent, "请选择一个要上传的文件"),
					100);
		} catch (android.content.ActivityNotFoundException ex) {
			// Potentially direct the user to the Market with a Dialog
			Toast.makeText(this, "请安装文件管理器", Toast.LENGTH_SHORT)
					.show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
