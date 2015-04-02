package com.microblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.microblog.dbutil.DBConn;
import com.microblog.po.User;

public class UserDao {
	public boolean addUser(final User user){
		//拆除对象的属性，目的组织SQL语句
		String email = user.getU_account();
		String password = user.getU_pwd();

		String nickname = user.getU_nickname();
		String sex = user.getU_sex();
		String city = user.getU_addr();
		String ques = user.getU_ques();
		
		String u_pic = "/"+"MicroBlog"+"/"+"face"+"/"+"def"+"."+"jpg";
		
		//动态组织一个SQL语句
		String strSQL = "insert into user values(null,?,?,?,?,?,null,null,null,null,null,?,?,null,null)";
		
		//连接数据库
		DBConn dbconn = new DBConn();
		int affectedRows = dbconn.execOther(strSQL, new Object[]{email,password,nickname,sex,city,u_pic,ques});
		
		
		//关闭数据库
		dbconn.closeConn();
		
		return affectedRows>0?true:false;
		}

	public void addPic(User user) {

		String filetodb = user.getU_pic();

		String strSQL = "insert into user (u_pic) values (?)";

		DBConn dbConn = new DBConn();

		dbConn.execOther(strSQL, new Object[] { filetodb });

		dbConn.closeConn();

	}
	
	
	
	public void updatePic(User user) {

		String u_pic = user.getU_pic();
		int u_id = user.getU_id();
		

		String strSQL = "update user set u_pic="+"?"+ "where u_id="+"?";

		DBConn dbConn = new DBConn();

		dbConn.execOther(strSQL, new Object[] { u_pic, u_id });

		dbConn.closeConn();

	}
	 public User checkEmail(String email)
		{  User user=new User();
			String sql="select * from user where u_account=?";
			DBConn dbconn=new DBConn();
			ResultSet rs=dbconn.execQuery(sql,new Object []{email});
			try {
			if(rs.next()){
			   user.setU_account(rs.getString(1));
			   user.setU_pwd(rs.getString(2));
			   user.setU_nickname(rs.getString(3));
			   user.setU_sex(rs.getString(4));
			   user.setU_city(rs.getString(5));
			   user.setU_date(rs.getString(6));
			   user.setU_qq(rs.getString(7));
			   user.setU_msn(rs.getString(8));
			   user.setU_motto(rs.getString(9));
			   user.setU_edu(rs.getString(10));
			   user.setU_pic(rs.getString(11));
			   user.setU_ques(rs.getString(12));
			   user.setU_label(rs.getString(13));
			   user.setU_realname(rs.getString(14));

		       return user;
				}else{
					return null;
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				  return null;
			}finally{
				dbconn.closeConn();
			}	
		
		}
	 public User checkPassword(String password)
		{  User user=new User();
			String sql="select * from user where u_pwd=?";
			DBConn dbconn=new DBConn();
			ResultSet rs=dbconn.execQuery(sql,new Object []{password});
			try {
			if(rs.next()){
			   user.setU_account(rs.getString(1));
			   user.setU_pwd(rs.getString(2));
			   user.setU_nickname(rs.getString(3));
			   user.setU_sex(rs.getString(4));
			   user.setU_city(rs.getString(5));
			   user.setU_date(rs.getString(6));
			   user.setU_qq(rs.getString(7));
			   user.setU_msn(rs.getString(8));
			   user.setU_motto(rs.getString(9));
			   user.setU_edu(rs.getString(10));
			   user.setU_pic(rs.getString(11));
			   user.setU_ques(rs.getString(12));
			   user.setU_label(rs.getString(13));
			   user.setU_realname(rs.getString(14));

		       return user;
				}else{
					return null;
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				  return null;
			}finally{
				dbconn.closeConn();
			}	
		
		}
	 
//	 public int checkPassword2(User user)
//		{  
//		 
//		 int u_id = user.getU_id();
//		 String password = user.getU_pwd();
//			String sql="select count(*) from user where u_pwd=? and u_id=?";
//			DBConn dbconn=new DBConn();
//			ResultSet rs=dbconn.execQuery(sql,new Object []{password, u_id});
//			
//			int count = 0;
//			try {
//				while(rs.next()){
//					
//					 count = rs.getInt(1);
//					
//				}
//
//				dbconn.closeConn();
//				return count;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return 0;
//		  
//		}
	 public User showUser(int u_id)//根据u_id获取用户信息 (user.jsp页已用)
		{  User user=new User();
			String sql="select * from user where u_id=?";
			DBConn dbconn=new DBConn();
			ResultSet rs=dbconn.execQuery(sql,new Object []{u_id});
			try {
				
			if(rs.next()){
			user.setU_id(rs.getInt(1));
			   user.setU_account(rs.getString(2));
			   user.setU_pwd(rs.getString(3));
			   user.setU_nickname(rs.getString(4));
			   user.setU_sex(rs.getString(5));
			   user.setU_city(rs.getString(6));
			   user.setU_date(rs.getString(7));
			   user.setU_qq(rs.getString(8));
			   user.setU_msn(rs.getString(9));
			   user.setU_motto(rs.getString(10));
			   user.setU_edu(rs.getString(11));
			   user.setU_pic(rs.getString(12));
			   user.setU_ques(rs.getString(13));
			   user.setU_label(rs.getString(14));
			   user.setU_realname(rs.getString(15));

		       return user;
				}else{
					return null;
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				  return null;
			}finally{
				dbconn.closeConn();
			}	
		
		}
	
	
	}