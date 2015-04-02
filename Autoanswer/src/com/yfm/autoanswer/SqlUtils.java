package com.yfm.autoanswer;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlUtils extends SQLiteOpenHelper {

	private String createsql="create table myphone(phone varchar(30));";
	private String dropsql="drop talbe myphone";
	private static String name="AutoAnswer";
	private static int version=1;
	public SqlUtils(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(createsql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL(dropsql);
		onCreate(arg0);
	}
	public List<String> querylist(){
		
		List<String> ls=new ArrayList<String>();
		try {
			SQLiteDatabase sd=this.getReadableDatabase();
			String sql="select * from myphone";
			Cursor cursor=sd.rawQuery(sql, null);
			while(cursor.moveToNext()){
				String p=cursor.getString(cursor.getColumnIndex("phone"));
				ls.add(p);
			}
			cursor.close();
			sd.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}
	public void delete(String p){
		try {
			String sql="delete from myphone where phone=";
			SQLiteDatabase sd=this.getWritableDatabase();
			if(p!=null){
				sql=sql+"'"+p+"'";
				sd.execSQL(sql);
			}
			sd.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void add(CharSequence to){
		try {
			if(queryPhone(to)){
				return;
			}
			String sql="insert into myphone (phone) values(?)";
			SQLiteDatabase sd=this.getWritableDatabase();
			if(to!=null){
				sd.execSQL(sql, new Object[]{to});
			}
			sd.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(String from,CharSequence to){
		try {
			if(queryPhone(to)){
				return;
			}
			String sql="update myphone set phone=? where phone=?";
			SQLiteDatabase sd=this.getWritableDatabase();
			if(from!=null&&to!=null){
				sd.execSQL(sql, new Object[]{to,from});
			}
			sd.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean queryPhone(CharSequence to){
		boolean phone=false;
		try {
			String sql="select * from myphone where phone='"+to+"'";
			SQLiteDatabase sd=this.getReadableDatabase();
			Cursor cursor=sd.rawQuery(sql,null);
			if(cursor.moveToNext()){
				phone=true;
			}
			cursor.close();
			sd.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phone;
		
	}
}
