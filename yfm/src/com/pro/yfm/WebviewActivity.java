package com.pro.yfm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebviewActivity extends Activity {

	

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(webview.canGoBack()){
			webview.goBack();
		}else{
			Exit();
		}
	}

	private WebView webview;
	private WebSettings ws;
	private ProgressBar progress;
	public static int pg = 1, djs;
	private TextView daojishi;
	private Button exit;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.webview_main);
		exit=(Button)super.findViewById(R.id.exit);
		exit.setOnClickListener(new ExitOnClickListener());
		webview = (WebView) super.findViewById(R.id.myweb);
		webview.setWebChromeClient(new WebChromeClientImpl(handler));
		webview.setWebViewClient(wc);
		progress = (ProgressBar) super.findViewById(R.id.progress);
		daojishi = (TextView) super.findViewById(R.id.djs);
		ws = webview.getSettings();
		ws.setJavaScriptCanOpenWindowsAutomatically(true);
		ws.setJavaScriptEnabled(true);
		ws.setSupportZoom(true);
		ws.setBuiltInZoomControls(true);//设置支持缩放
		ws.setPluginsEnabled(true);
		ws.setDefaultZoom(WebSettings.ZoomDensity.FAR);
		String time=this.getIntent().getStringExtra("time");
		webview.loadUrl("http://183.247.171.38/");
		CountDownTimerImpl cdt = new CountDownTimerImpl(Integer.parseInt(time) * 60 * 1000, 1000);
		cdt.start();
	}
	class ExitOnClickListener implements OnClickListener{

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Exit();
		}
		
	}
	WebViewClient wc = new WebViewClient() {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}

	};
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == pg) {
				progress.setProgress(msg.arg1);
			}
			if (msg.what == djs) {
				daojishi.setText(msg.obj.toString());
			}
		}

	};

	class CountDownTimerImpl extends CountDownTimer {

		public CountDownTimerImpl(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			ShowDialog();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			Long m = millisUntilFinished / 1000;
			String p = m / 60 + "分" + m % 60 + "秒";
			Message msg = handler.obtainMessage();
			msg.what = djs;
			msg.obj = p;
			handler.sendMessage(msg);
		}

	}

	private void ShowDialog(){
		Builder builder=new Builder(this);
		builder.setTitle("程序退出");
		builder.setMessage("时间到,程序即将退出");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				WebviewActivity.this.finish();
			}
		});
		builder.setCancelable(false);
		builder.create().show();
	}
	private void Exit(){
		Builder builder=new Builder(this);
		builder.setTitle("程序退出");
		builder.setMessage("你确定要退出程序吗?");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				WebviewActivity.this.finish();
			}
		});
		builder.setNegativeButton("取消", null);
		builder.create().show();
	}
}
