package com.pro.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUtils extends SQLiteOpenHelper {
	private static String name = "stu";
	private static int version = 1;

	public SQLiteUtils(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table stu(bid integer primary key AUTOINCREMENT,PareCard varchar(30),ChildName varchar(30),ClassName varchar(30),ParePhone varchar(30),ChildBirth date,PareName varchar(30))";
		String sqljl = "create table stujl(bid integer primary key AUTOINCREMENT,PareCard varchar(30),jldate datetime)";
		db.execSQL(sql);
		db.execSQL(sqljl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		init(db);
	}

	
	public void SaveDate(List<Info> linfo) {
		String sql = "insert into stu(PareCard,ChildName,ClassName,ParePhone,ChildBirth,PareName) values (?,?,?,?,?,?)";
		SQLiteDatabase sd = this.getWritableDatabase();
		sd.execSQL("delete from stu");
		for (int i = 0; i < linfo.size(); i++) {
			Info info = linfo.get(i);
			sd.execSQL(
					sql,
					new Object[] { info.getPareCard(), info.getChildName(),
							info.getClassName(), info.getParePhone(),
							info.getChildBirth(), info.getPareName() });
			System.out.println(info.getPareCard());
		}
		sd.close();

	}

	public void init(SQLiteDatabase db) {
		db.execSQL("drop table stu");
		db.execSQL("drop table stujl");
		onCreate(db);
	}

	public List<Info> GetInfo(String PareCard) {
		List<Info> linfo = new ArrayList<Info>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from stu where PareCard is not null";
		List<String> ls = new ArrayList<String>();
		
		if (PareCard != null && !"".equals(PareCard)) {
			sql = sql + " and PareCard=?";
			ls.add(PareCard);
		}else{
			return linfo;
		}
		Cursor cursor = db.rawQuery(sql, ls.toArray(new String[ls.size()]));
		while (cursor.moveToNext()) {
			Info info = new Info();
			info.setId(cursor.getInt(cursor.getColumnIndex("bid")));
			info.setPareCard(cursor.getString(cursor.getColumnIndex("PareCard")));
			info.setChildName(cursor.getString(cursor
					.getColumnIndex("ChildName")));
			info.setClassName(cursor.getString(cursor
					.getColumnIndex("ClassName")));
			info.setParePhone(cursor.getString(cursor
					.getColumnIndex("ParePhone")));
			info.setChildBirth(cursor.getString(cursor
					.getColumnIndex("ChildBirth")));
			info.setPareName(cursor.getString(cursor.getColumnIndex("PareName")));
			linfo.add(info);
		}
		return linfo;
	}
	public void savejl(Info info){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d=sdf.format(new Date());
		SQLiteDatabase sd = this.getWritableDatabase();
		String sql="insert into stujl (PareCard,jldate) values (?,?)";
		System.out.println(d+"-----");
		sd.execSQL(sql, new String[]{info.getPareCard(),d});
		sd.close();
	}
	public List<Info> GetInfo(String ChildName,String jldate) {
		List<Info> linfo = new ArrayList<Info>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select stujl.jldate,stu.* from stujl left join stu on stujl.PareCard=stu.PareCard where stu.PareCard is not null";
		List<String> ls = new ArrayList<String>();
		
		if (ChildName != null && !"".equals(ChildName)) {
			sql = sql + " and stu.ChildName = ?";
			ls.add(ChildName);
		}else{
			return linfo;
		}
		if (jldate != null && !"".equals(jldate)) {
			sql = sql + " and strftime('%Y-%m-%d',stujl.jldate)=?";
			ls.add(jldate);
		}
		
		Cursor cursor = db.rawQuery(sql, ls.toArray(new String[ls.size()]));
		while (cursor.moveToNext()) {
			Info info = new Info();
			info.setId(cursor.getInt(cursor.getColumnIndex("bid")));
			info.setPareCard(cursor.getString(cursor.getColumnIndex("PareCard")));
			info.setChildName(cursor.getString(cursor
					.getColumnIndex("ChildName")));
			info.setClassName(cursor.getString(cursor
					.getColumnIndex("ClassName")));
			info.setParePhone(cursor.getString(cursor
					.getColumnIndex("ParePhone")));
			info.setChildBirth(cursor.getString(cursor
					.getColumnIndex("ChildBirth")));
			info.setPareName(cursor.getString(cursor.getColumnIndex("PareName")));
			info.setJldate(cursor.getString(cursor.getColumnIndex("jldate")));
			linfo.add(info);
		}
		return linfo;
	}

}
