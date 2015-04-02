package com.yfm.weather;

import java.util.HashMap;
import java.util.Map;

import android.app.ActivityGroup;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;

import com.yfm.utils.SqliteUtils;

public class MainActivity extends ActivityGroup {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id=item.getItemId();
		if(id==R.id.menu_settings){
			SqliteUtils su=new SqliteUtils(this);
			SQLiteDatabase db=su.getWritableDatabase();
			db.execSQL("drop table citylist");
			su.onCreate(db);
		}
		return super.onOptionsItemSelected(item);
	}

	private LinearLayout content;
	private LinearLayout weather;
	private LinearLayout gongjiao;
	private LinearLayout about;
	private LinearLayout tuichu;
	private Map<String,View> mapview=new HashMap<String, View>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        content=(LinearLayout)super.findViewById(R.id.content);
        
        about=(LinearLayout)super.findViewById(R.id.about);
        about.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,about.class);
		        intent.setAction("about");
		        showactivity(intent);
			}
		});
        tuichu=(LinearLayout)super.findViewById(R.id.tuichu);
        
        tuichu.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder builer=new Builder(MainActivity.this);
				builer.setTitle("确定退出吗");
				builer.setMessage("点击确定退出");
				builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						MainActivity.this.finish();
					}
				});
				builer.setNegativeButton("取消", null);
				builer.create().show();
			}
		});
        
        weather=(LinearLayout)super.findViewById(R.id.weather);
        weather.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,ViewPagerActivity.class);
		        intent.setAction("ViewPagerActivity");
		        showactivity(intent);
			}
		});
        gongjiao=(LinearLayout)super.findViewById(R.id.gongjiao);
        gongjiao.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(MainActivity.this,Gongjiao.class);
				intent.setAction("Gongjiao");
				MainActivity.this.showactivity(intent);
			}
		});
        Intent intent=new Intent(this,ViewPagerActivity.class);
        intent.setAction("ViewPagerActivity");
        showactivity(intent);
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

    public void showactivity(Intent intent){
    	String key=intent.getAction();
    	View view=mapview.get(key);
    	if(view==null){
    		Window window=MainActivity.this.getLocalActivityManager().startActivity(intent.getAction(), intent);
    		view=window.getDecorView();
    		mapview.put(key, view);
    	}
    	MainActivity.this.content.removeAllViews();
    	MainActivity.this.content.addView(view);
    	System.out.println(key);
    	weather.setBackgroundResource(R.color.heise);
    	gongjiao.setBackgroundResource(R.color.heise);
    	about.setBackgroundResource(R.color.heise);
    	tuichu.setBackgroundResource(R.color.heise);
    	if("ViewPagerActivity".equals(key)){
    		weather.setBackgroundResource(R.color.myColor);
    	}
    	if("Gongjiao".equals(key)){
    		gongjiao.setBackgroundResource(R.color.myColor);
    	}
    	if("about".equals(key)){
    		about.setBackgroundResource(R.color.myColor);
    	}
    	
    }

    
}
