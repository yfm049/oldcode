package com.microblog.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.microblog.dbutil.DBConn;
import com.microblog.po.User;



public class LoginDaoImpl {
	public List<User> getUsers(){
		
		List<User> list = new ArrayList<User>();
		//创建数据库链接
		DBConn dbconn = new DBConn();
		try {
		String strSQL = "select u_id, u_pic from user order by rand() limit 0,24";
		//调用dbconn的execu方法完成查询
		ResultSet rs = dbconn.execQuery(strSQL, new Object[]{});
		while(rs.next()) {
			User user = new User();
			user.setU_id(rs.getInt("u_id"));
			user.setU_pic(rs.getString("u_pic"));				
			list.add(user);
		}			
		rs.close();			
		return list;
	} catch (Exception e) {
		return null;
	}finally{
		dbconn.closeConn();
	}		
	
}
}
