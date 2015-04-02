package com.microblog.dao;

import java.sql.ResultSet;

import com.microblog.dbutil.DBConn;

public class AddUserCheckDao {
	//验证用户名是否已经使用
	public boolean check(final String userid){
		//动态创建一个SQL语句
		String strSQL = "select count(*) from user where u_account = ?";
		//创建数据库链接
		DBConn dbconn = new DBConn();
		//调用dbconn的execu方法完成查询
		ResultSet rs1 = dbconn.execQuery(strSQL, new Object[]{userid});
		//获取rs1的值用于判断数据库验证是否成功
		try {
			rs1.next();
			return rs1.getInt(1)>0? true:false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally{
			dbconn.closeConn();
		}
	}
//	public static void main(String[] args){
//	AddUserCheckDao adminDao = new AddUserCheckDao();
//	System.out.print(adminDao.check("dfd"));
//}

}
