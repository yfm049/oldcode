package com.microblog.dao;

import java.sql.ResultSet;

import com.microblog.dbutil.DBConn;
import com.microblog.po.User;

public class AdminDao {
	//µÇÂ½ÑéÖ¤
	public User check(final String userid,final String password){
		
		User user = null;
		String strSQL = "select * from user where u_account = ? and u_pwd = ?";
		
		DBConn dbconn = new DBConn();
		
		ResultSet rs = dbconn.execQuery(strSQL, new Object[]{userid,password});
		
		try {
			while(rs.next()) {
				user = new User();
				user.setU_id(rs.getInt("u_id"));
				user.setU_account(rs.getString("u_account"));
				user.setU_pwd(rs.getString("u_pwd"));
				user.setU_nickname(rs.getString("u_nickname"));
				user.setU_sex(rs.getString("u_sex"));
				user.setU_city(rs.getString("u_city"));
				user.setU_pic(rs.getString("u_pic"));
				user.setU_date(rs.getString("u_date"));
				user.setU_qq(rs.getString("u_qq"));
				user.setU_msn(rs.getString("u_msn"));
				user.setU_motto(rs.getString("u_motto"));
				user.setU_realname(rs.getString("u_realname"));

			}
			rs.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbconn.closeConn();
		}
		
		return user;
	}



//		public static void main(String[] args){
//			AdminDao adminDao = new AdminDao();
//			System.out.print(adminDao.check("admin","admin"));
//}
}
