package com.microblog.dao;

import com.microblog.dbutil.DBConn;

public class BackuppwdDao {
	public void updatepwd(String newpwd3 ,String account ){
	
	
	  String updateSql="update user set u_pwd=? where u_account = ?"; 
	  DBConn  dbconn=new DBConn();
	  dbconn.execOther(updateSql, new Object[]{newpwd3,account });
	  
	  dbconn.closeConn();
}
}