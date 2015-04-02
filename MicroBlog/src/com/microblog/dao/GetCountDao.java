package com.microblog.dao;

import java.sql.ResultSet;

import com.microblog.dbutil.DBConn;

public class GetCountDao {
	
		//获得使用的人数
		public int check(){
			//动态创建一个SQL语句
			String strSQL = "select count(*) from user";
			//创建数据库链接
			DBConn dbconn = new DBConn();
			//调用dbconn的execu方法完成查询
			ResultSet rs1 = dbconn.execQuery(strSQL, new Object[]{});
			try {
				rs1.next();
				int rs2= rs1.getInt(1);
				return rs2;
				
			} catch (Exception e) {
				// TODO: handle exception
				return 0;
			}finally{
				dbconn.closeConn();}
			
				
			
			
	}
		
	public static void main(String[] args){
			
			System.out.print("rs");
		}
}
