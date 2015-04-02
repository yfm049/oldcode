package com.yfm.webservice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlUtils extends SQLiteOpenHelper {

	private static String name="ydbg";
	private static int version=1;
	public SqlUtils(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="create table user(name varchar(50),pass varchar(50))";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql="drop table user";
		db.execSQL(sql);
		onCreate(db);
	}
	public void insertuser(String name,String pass){
		SQLiteDatabase sq=this.getWritableDatabase();
		String sql="delete from user where name='"+name+"'";
		sq.execSQL(sql);
		sql="insert into user (name,pass) values ('"+name+"','"+pass+"')";
		sq.execSQL(sql);
		sq.close();
	}


}
