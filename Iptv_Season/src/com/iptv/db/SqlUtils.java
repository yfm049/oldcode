package com.iptv.db;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iptv.application.IptvApplication;
import com.iptv.model.Category;
import com.iptv.model.Tvinfo;

public class SqlUtils extends SQLiteOpenHelper {

	private static String name = "iptv.db";
	private static int version = 1;

	public SqlUtils() {
		super(IptvApplication.getApplication(), name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String categorysql = "create table category(id varchar(10),name varchar(200))";
		db.execSQL(categorysql);
		String tvinfosql = "create table tvinfo(id varchar(10),cid varchar(10),name varchar(200),httpurl varchar(200),hotlink varchar(10),isflag varchar(2),logo varchar(20),epg varchar(100))";
		db.execSQL(tvinfosql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		String dcategorysql = "drop table category";
		db.execSQL(dcategorysql);
		String dtvinfosql = "drop table tvinfo";
		db.execSQL(dtvinfosql);
	}

	public void initTvData(List<Category> lc) {
		if (lc != null) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.beginTransaction();
			db.execSQL("delete from category");
			db.execSQL("delete from tvinfo");
			String categorysql = "insert into category(id,name) values(?,?)";
			String tvinfosql = "insert into tvinfo(id,cid,name,httpurl,hotlink,isflag,logo,epg) values(?,?,?,?,?,?,?,?)";
			for (Category cate : lc) {
				db.execSQL(categorysql,
						new Object[] { cate.getId(), cate.getName() });
				List<Tvinfo> ltv = cate.getTvlist();
				for (Tvinfo tv : ltv) {
					db.execSQL(
							tvinfosql,
							new Object[] { tv.getId(), cate.getId(),
									tv.getName(), tv.getHttpurl(),
									tv.getHotlink(), tv.getIsflag(),
									tv.getLogo(), tv.getEpg() });
				}
			}
			db.setTransactionSuccessful();
			db.endTransaction();
			db.close();
		}

	}

	public List<Category> GetAllCategory() {
		List<Category> lc = new ArrayList<Category>();
		Category all = new Category();
		all.setId("-1");
		all.setName("È«²¿");
		lc.add(all);
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "select * from category order by id";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			Category item=new Category();
			item.setId(cursor.getString(cursor.getColumnIndex("id")));
			item.setName(cursor.getString(cursor.getColumnIndex("name")));
			lc.add(item);
		}
		cursor.close();
		db.close();
		Category fav = new Category();
		fav.setId("-2");
		fav.setName("ÊÕ²Ø");
		lc.add(all);
		return lc;
	}
	
	public void GetAllTvinfo(Category category){
		category.getTvlist().clear();
		String sql="select * from tvinfo";
		if(!"-1".equals(category.getId())){
			sql+=" where cid="+category.getId();
		}else if("-2".equals(category.getId())){
			sql+=" where isflag=1";
		}
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			Tvinfo tvinfo = new Tvinfo();
			tvinfo.setId(cursor.getString(cursor.getColumnIndex("id")));
			tvinfo.setName(cursor.getString(cursor.getColumnIndex("name")));
			tvinfo.setHttpurl(cursor.getString(cursor.getColumnIndex("httpurl")));
			tvinfo.setHotlink(cursor.getString(cursor.getColumnIndex("hotlink")));
			tvinfo.setIsflag(cursor.getString(cursor.getColumnIndex("isflag")));
			tvinfo.setLogo(cursor.getString(cursor.getColumnIndex("logo")));
			tvinfo.setEpg(cursor.getString(cursor.getColumnIndex("epg")));
			category.getTvlist().add(tvinfo);
		}
		cursor.close();
		db.close();
	}
	
	public void FavoriteTvinfo(Tvinfo tvinfo){
		String sql="update tvinfo set isflag=?";
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL(sql, new Object[]{tvinfo.getIsflag()});
		db.close();
	}

}
