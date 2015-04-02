package com.microblog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.microblog.dbutil.DBConn;
import com.microblog.po.User;

public class EdictDao {
	
	public List<User> getUser() {
		
		List<User> list = new ArrayList<User>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/microblog","root","root");
			
			ResultSet rs = conn.createStatement().executeQuery("select * from user");
			
			while(rs.next()) {
				User use = new User();
			
			use.setU_nickname(rs.getString(4));
			use.setU_sex(rs.getString(5));
			use.setU_city(rs.getString(6));
            use.setU_date(rs.getString(7));
			use.setU_qq(rs.getString(8));
			use.setU_msn(rs.getString(9));
			use.setU_motto(rs.getString(10));

				list.add(use);
			}
			
			rs.close();
			
			return list;
		} catch (Exception e) {
			
		}
		
		return null;
	}

	
	public User getUserById(final int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/etc?autoReconnect=true&useUnicode=true&characterEncoding=utf-8","root","root");
			
			ResultSet rs = conn.createStatement().executeQuery("select * from microblog where id = "+id);
			
			
			User use = new User();
			if(rs.next()) {
				
				use.setU_id(rs.getInt(1));
				use.setU_account(rs.getString(2));
				use.setU_pwd(rs.getString(3));
				use.setU_nickname(rs.getString(4));
				use.setU_sex(rs.getString(5));
				use.setU_city(rs.getString(6));
	            use.setU_date(rs.getString(7));
				use.setU_qq(rs.getString(8));
				use.setU_msn(rs.getString(9));
				use.setU_motto(rs.getString(10));
				use.setU_realname(rs.getString(11));
			}
			
			rs.close();
			conn.close();
			
			return use;
		} catch (Exception e) {
			
		}
		
		return null;
	}


	public  void updateUser(User use , int id ) {
		//拆除对象属性 目的组织SQL语句
		
		String u_nickname=use.getU_nickname();
		String u_sex=use.getU_sex();
		String u_city=use.getU_city();
		String u_date=use.getU_date();
		String u_qq=use.getU_qq();
		String u_msn=use.getU_msn();
		String u_motto=use.getU_motto();
		String u_realname=use.getU_realname();
		String updateSql = "update user set u_nickname=?,u_sex=?,u_city=?,u_date=? ,u_qq=?,u_msn=?,u_motto=? ,u_realname=? where u_id = ?"; 
		DBConn  dbconn=new DBConn();
		dbconn.execOther(updateSql, new Object[]{u_nickname,u_sex,u_city,u_date,u_qq,u_msn,u_motto ,u_realname,id });
	 
		dbconn.closeConn();
		
	}
	
	public void updatepwd(String newpwd1 ,int id ){
		
		
	  String updateSql="update user set u_pwd=? where u_id = ?"; 
	  DBConn  dbconn=new DBConn();
	  int a=dbconn.execOther(updateSql, new Object[]{newpwd1,id });
	  
//		if(r>0)
//			System.out.println("true");else
//				System.out.println("false");
//		dbconn.closeConn();
		
	}
	
	public  boolean cheak(int id ,String oldpwd){
		
		
		//动态创建一个SQL语句
		String strSql="select count(*) from user where u_id=? and u_pwd=?";
		//创建数据库连接
		DBConn dbconn= new DBConn();
		//调用dbconn
		ResultSet rs=dbconn.execQuery(strSql, new Object[]{id,oldpwd});
	       
		
		//获取rs中的值，用于判断数据验证是否成功
      try {
     	 rs.next();
     	 boolean flag=rs.getInt(1)>0?true:false;
     	 return flag;
		} catch (Exception e) {
			return false;
	}finally{
		dbconn.closeConn();
			
		}
		
		
	}
	
	
}




