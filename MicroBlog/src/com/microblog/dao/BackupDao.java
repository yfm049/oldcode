package com.microblog.dao;

import java.sql.ResultSet;

import com.microblog.dbutil.DBConn;
import com.microblog.po.User;






public class BackupDao {
	
public User check(final String userid,final String ques){
		
		User user = null;

		String strSQL = "select * from user where u_account = ? and u_ques = ?";
		
		DBConn dbconn = new DBConn();
		
		ResultSet rs = dbconn.execQuery(strSQL, new Object[]{userid,ques});
		
		try {
			while(rs.next()) {
				user = new User();
			
				user.setU_account(rs.getString("u_account"));
				user.setU_pwd(rs.getString("u_pwd"));
		
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
}
