package com.yfm.ydbg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.yfm.adapter.FunAdapter;
import com.yfm.pojo.Funinfo;
import com.yfm.webservice.SoapUtils;

public class FunActivity extends Activity {

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.stopService(intent);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		this.startService(intent);
	}
	private FunAdapter fa;
	private GridView fungrid;
	private List<Funinfo> li;
	public static String account=null;
	public static String pass;
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fun);
        intent=new Intent(this,DbsxService.class);
        li=new ArrayList<Funinfo>();
        builderfun();
        fa=new FunAdapter(this,li);
        fungrid=(GridView)super.findViewById(R.id.fungrid);
        fungrid.setAdapter(fa);
        fungrid.setOnItemClickListener(new funOnItemClickListener());
        
    }
    class funOnItemClickListener implements OnItemClickListener{

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Funinfo info=li.get(position);
			if(info.getImage()==R.drawable.dbsx){
				Intent intent=new Intent(FunActivity.this,DbsxActivity.class);
				FunActivity.this.startActivity(intent);
			}else if(info.getImage()==R.drawable.xxcj){
				Intent intent=new Intent(FunActivity.this,XxCjActivity.class);
				FunActivity.this.startActivity(intent);
			}
			else if(info.getImage()==R.drawable.mmxg){
				Intent intent=new Intent(FunActivity.this,MimagxActivity.class);
				FunActivity.this.startActivity(intent);
			}else if(info.getImage()==R.drawable.rjgx){
				CheckVersion();
			}
			
		}
    	
    }
    public void CheckVersion(){
    	ProgressDialog pd=new ProgressDialog(this);
    	pd.setTitle("版本检测");
    	pd.setMessage("正在检测新版本...");
    	pd.show();
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("versionNum", getversion());
    	SoapUtils.Send(FunActivity.this, "CheckVersion", null, handler, pd);
    	
    }
    public String getversion(){
    	try {
			PackageInfo pi=this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
    }
    private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==1){
				if("0".equals(msg.obj.toString())){
					Toast.makeText(FunActivity.this, "已经是最新版本", Toast.LENGTH_SHORT).show();
				}else{
					final String url=msg.obj.toString();
					Builder builder=new Builder(FunActivity.this);
					builder.setTitle("版本");
					builder.setMessage("检测到新版本,是否下载?");
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							System.out.println(url);
						}
					});
					builder.setNegativeButton("取消", null);
					builder.create().show();
				}
			}
		}
    	
    };
    private void builderfun(){
    	Funinfo dbsx=new Funinfo();
    	dbsx.setImage(R.drawable.dbsx);
    	dbsx.setName("待办事项");
    	li.add(dbsx);
    	Funinfo xxcj=new Funinfo();
    	xxcj.setImage(R.drawable.xxcj);
    	xxcj.setName("信息采集");
    	li.add(xxcj);
    	Funinfo mmxg=new Funinfo();
    	mmxg.setImage(R.drawable.mmxg);
    	mmxg.setName("密码更改");
    	li.add(mmxg);
    	Funinfo rjgx=new Funinfo();
    	rjgx.setImage(R.drawable.rjgx);
    	rjgx.setName("软件更新");
    	li.add(rjgx);
    	
    }

    
}
