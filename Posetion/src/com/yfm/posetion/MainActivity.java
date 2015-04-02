package com.yfm.posetion;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cilico.tools.I2CTools;

public class MainActivity extends Activity {

	private WebView webview;
	private ProgressBar progress;
	private WebSettings ws;
	private Intent service;
	private TelephonyManager telManager;
	private String imei;
	private String imsi;
	private String userName;
	private String password;
	private Button read;
	private TextView identitycode,gos,sfzcode,sname;
	private ProgressDialog pd;
	private Info info;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service=new Intent(this,SendService.class);
        this.startService(service);
        userName=getResources().getString(R.string.userName);
        password=getResources().getString(R.string.password);
        telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        imei=telManager.getDeviceId();
        imsi=telManager.getSubscriberId();
        webview=(WebView)super.findViewById(R.id.webView);
        read=(Button)super.findViewById(R.id.read);
        identitycode=(TextView)super.findViewById(R.id.identitycode);
        gos = (TextView) super.findViewById(R.id.gsi);
		sfzcode = (TextView) super.findViewById(R.id.sfzcode);
		sname = (TextView) super.findViewById(R.id.sname);
		
        progress = (ProgressBar) super.findViewById(R.id.progress);
        webview.setWebChromeClient(new WebChromeClientImpl(handler));
		webview.setWebViewClient(wc);
		webview.setInitialScale(25);
		ws = webview.getSettings();
		ws.setJavaScriptCanOpenWindowsAutomatically(true);
		ws.setJavaScriptEnabled(true);
		ws.setPluginState(PluginState.ON);
		ws.setDefaultZoom(WebSettings.ZoomDensity.FAR);
		ws.setSupportZoom(true);
		ws.setBuiltInZoomControls(true);//设置支持缩放
		ws.setUseWideViewPort(true);
		webview.loadUrl("http://hao.360.cn/");
		read.setOnClickListener(new readOnClickListener());
    }
    class readOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String code=I2CTools.ReadUID();
			if(code.length()!=0){
				identitycode.setText(code);
				ShowSendDialog();
			}else{
				Toast.makeText(MainActivity.this, "读取失败，请重新读取!", Toast.LENGTH_SHORT).show();
			}
		}
    	
    }
    private void ShowSendDialog(){
    	pd=new ProgressDialog(MainActivity.this);
		pd.setMessage("正在获取信息，请稍候...");
		pd.setCancelable(false);
		pd.show();
		new identityThread().start();
    	
    	
    }
    private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {
				progress.setProgress(msg.arg1);
			}
			if(msg.what==2){
				pd.cancel();
				if (info!= null && info.getLci().size() > 0) {
					String rn = "";
					String gs = "";
					String sc = "";
					for (int i = 0; i < info.getLci().size(); i++) {
						CardInfo ci = info.getLci().get(i);
						int cid = ci.getContentTypeID();
						if (cid == 1) {
							rn += ci.getContent();
						}
						if (cid == 3) {
							gs += ci.getContent();
						}
						if (cid == 2) {
							sc += ci.getContent();
						}

					}
					gos.setText(replaceBlank(gs));
					sfzcode.setText(replaceBlank(sc));
					sname.setText(replaceBlank(rn));
				}
				Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}
		}

	};
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("`");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(webview.canGoBack()){
			webview.goBack();
		}else{
			Exit();
		}
	}
	private WebViewClient wc = new WebViewClient() {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}

	};
	private void Exit(){
		Builder builder=new Builder(this);
		builder.setTitle("程序退出");
		builder.setMessage("你确定要退出程序吗?");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				MainActivity.this.finish();
			}
		});
		builder.setNegativeButton("取消", null);
		builder.create().show();
	}
	
	class identityThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			String	key=WebserviceUtils.GetEncryptionKey(userName, imei, imsi);
			String xml=WebserviceUtils.ReportPosition(userName, MD5EncodeUtil.Encrypt(password, key), MD5EncodeUtil.Encrypt(imei, key), MD5EncodeUtil.Encrypt(imsi, key), MD5EncodeUtil.Encrypt(identitycode.getText().toString(), key));
			info=XMlUtils.ParseXml(xml);
			ReadContent();
			Message msg=handler.obtainMessage();
			msg.what=2;
			if(xml!=null&&!"".equals(xml)){
				msg.obj="成功";
			}else{
				msg.obj="身份卡未认证";
			}
			
			handler.sendMessage(msg);
			
		}
		
	}
	public void ReadContent(){
		try {
			if(info!=null){
				List<CardInfo> lci=info.getLci();
				if(lci!=null&&lci.size()>0){
					for(int i=0;i<lci.size();i++){
						CardInfo ci=lci.get(i);
						byte[] pout=new byte[16];
						byte[] PassWord=I2CTools.stringToBytes(ci.getReadPassword());
						byte add=(byte)ci.getContentAddr();
						I2CTools.ReadBlock(pout, PassWord, (byte) 0x60, add);
						String con=new String(pout, "GBK");
						ci.setContent(con);
					}
					
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
