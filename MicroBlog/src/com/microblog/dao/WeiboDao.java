package com.microblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.dbutil.DBConn;
import com.microblog.po.Weibo;

public class WeiboDao {

		public boolean sendWeibo(Weibo weibo) { // 增加微博

			String sql = "insert into weibo (w_id,w_content,w_date,w_unick,w_upic,w_image, w_times) values(?,?,now(),?,?,?,0)";

			int w_id = weibo.getW_id();// 与u_id外键相连，把前台的u_id赋给w_id
			String w_content = weibo.getW_content();
			String w_unick = weibo.getW_unick();
			String w_pic = weibo.getW_upic();
			String w_image = weibo.getW_image();

			DBConn dbConn = new DBConn();

			int rs = dbConn.execOther(sql, new Object[] { w_id, w_content, w_unick,
					w_pic, w_image });

			dbConn.closeConn();

			return rs > 0 ? true : false;

		}
		public void addPic(Weibo weibo) {

			String filetodb = weibo.getW_image();

			String strSQL = "insert into weibo (w_image) values (?)";

			DBConn dbConn = new DBConn();

			dbConn.execOther(strSQL, new Object[] { filetodb });

			dbConn.closeConn();

		}

	public ArrayList<Weibo> getWeibobyU_id(int u_id) throws SQLException {// 获取某位用户自己的微博

		String sql = "select * from weibo where w_id=? order by w_date desc";

		DBConn dbConn = new DBConn();
		ResultSet rs = dbConn.execQuery(sql, new Object[] { u_id });

		if (!rs.next()) {
			rs.close();
			dbConn.closeConn();
			return null;
		} else {
			ArrayList<Weibo> Result = new ArrayList<Weibo>();

			do {
				Weibo weibo = new Weibo();
				weibo.setId(rs.getInt(1));
				weibo.setW_id(rs.getInt(2));
				weibo.setW_content(rs.getString(3));
				weibo.setW_date(rs.getString(4));
				weibo.setW_unick(rs.getString(5));
				weibo.setW_upic(rs.getString(6));

				Result.add(weibo);
			} while (rs.next());
			rs.close();
			dbConn.closeConn();
			return Result;
		}
	}

	public ArrayList<Weibo> getConcerstWeibobyU_id(int u_id)// 用户获取关注的人的微博
			throws SQLException {

		DBConn dbConn = new DBConn();

		ArrayList<Weibo> Result2 = new ArrayList<Weibo>();

		String sql2 = "select * from weibo,relation where w_id = g_id and r_id = ? order by w_date desc;";

		ResultSet rs = dbConn.execQuery(sql2, new Object[] { u_id });
		while (rs.next()) {

			Weibo weibo = new Weibo();// 封装weibo对象

			weibo.setId(rs.getInt(1));
			weibo.setW_id(rs.getInt(2));
			weibo.setW_content(rs.getString(3));

			weibo.setW_date(rs.getString(5).toString());
			weibo.setW_unick(rs.getString(6));
			weibo.setW_upic(rs.getString(7));

			Result2.add(weibo);// 封装好的weibo放入一个集合
		}

		rs.close();
		dbConn.closeConn();
		return Result2;

	}

	public ArrayList<Weibo> getFriendstWeibobyU_id(int u_id)// 用户获取好友的微博
			throws SQLException {

		DBConn dbConn = new DBConn();

		ArrayList<Weibo> Result2 = new ArrayList<Weibo>();

		String sql2 = "select * from weibo,relation where w_id = g_id and state = 1 and r_id = ? order by w_date desc;";

		ResultSet rs = dbConn.execQuery(sql2, new Object[] { u_id });
		while (rs.next()) {

			Weibo weibo = new Weibo();// 封装weibo对象

			weibo.setId(rs.getInt(1));
			weibo.setW_id(rs.getInt(2));
			weibo.setW_content(rs.getString(3));

			weibo.setW_date(rs.getString(5).toString());
			weibo.setW_unick(rs.getString(6));
			weibo.setW_upic(rs.getString(7));

			Result2.add(weibo);// 封装好的weibo放入一个集合
		}

		rs.close();
		dbConn.closeConn();
		return Result2;

	}

	public boolean deleteWeibo(Weibo weibo) { // 删除微博

		String sql = "delete from comment where c_id=?";

		int id = weibo.getId();

		DBConn dbConn = new DBConn();

		dbConn.execOther(sql, new Object[] { id });

		dbConn.closeConn();
		
		String sql1 =  "delete from weibo where id=?";
		DBConn dbConn1 = new DBConn();

		int rs=dbConn1.execOther(sql1, new Object[] { id });
		dbConn1.closeConn();
		return rs > 0 ? true : false;

	}

   
	public List<Weibo> getAllWeibobyU_id(int u_id, int u__id, int u___id)throws SQLException {// 获取某位用户自己的,关注的人的微博

		String sql = "select * from weibo where w_id= any (select g_id from relation where (r_id=? and state=0) or (r_id=? and state=1)) or w_id=? order by w_date desc";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] { u_id, u_id, u_id });

		ArrayList<Weibo> Result2 = new ArrayList<Weibo>();
		while (rs.next()) {

			Weibo weibo = new Weibo();// 封装weibo对象

			weibo.setId(rs.getInt(1));
			weibo.setW_id(rs.getInt(2));
			weibo.setW_content(rs.getString(3));
			weibo.setW_date(rs.getString(4));
			weibo.setW_unick(rs.getString(5));
			weibo.setW_upic(rs.getString(6));
			weibo.setW_image(rs.getString(7));
			Result2.add(weibo);
			

		}
		rs.close();
		dbConn.closeConn();
		return Result2;


	}
	
	public int showWeiboNum(int u_id) throws SQLException {// 显示微博数量

		String sql = "select * from weibo where w_id = ?";// g_id关注了多少r_id

		DBConn conn = new DBConn();
		ResultSet rs = conn.execQuery(sql, new Object[] { u_id });

		int weiboNum = 0;
		while (rs.next()) {
			weiboNum++;
		}
		rs.close();
		conn.closeConn();
		return weiboNum;

	}
	public boolean addWeibo(Weibo weibo) { // 增加微博

		String sql = "insert into weibo (w_id,w_content,w_date,w_unick,w_upic) values(?,?,?,?,?)";

		int w_id = weibo.getW_id();// 与u_id外键相连，把前台的u_id赋给w_id
		String w_content = weibo.getW_content();
		String w_date = weibo.getW_date();
		String w_unick = weibo.getW_unick();
		String w_pic = weibo.getW_upic();

		DBConn dbConn = new DBConn();

		int rs = dbConn.execOther(sql,new Object[] { w_id, w_content,w_date,w_unick,w_pic });

		dbConn.closeConn();
		
		return rs > 0 ? true : false;

	}
	
	public List<Weibo> getSearchByContent(String content)// 根据内容获取微博
		throws SQLException {
	
	List<Weibo> searchcontentlist = new ArrayList<Weibo>();
	
	String sql2 = "select * from weibo where w_content like '%"+content+"%' order by w_date desc"; 
	
	DBConn dbConn = new DBConn();
	
	
	ResultSet rs = dbConn.execQuery(sql2, new Object[] {});
	
	try {
		while (rs.next()) {
			
			Weibo weibo = new Weibo();// 封装weibo对象
		
			weibo.setId(rs.getInt(1));
			weibo.setW_id(rs.getInt(2));
			weibo.setW_content(rs.getString(3));
		
			weibo.setW_date(rs.getString(4));
			weibo.setW_unick(rs.getString(5));
			weibo.setW_upic(rs.getString(6));
			
			searchcontentlist.add(weibo);// 封装好的weibo放入一个集合
		
	}
		return searchcontentlist;
	}catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	finally{
		
		dbConn.closeConn();

	}
	
	
	
	}

	public List<Weibo> getSearchByUser(String content)// 根据用户获取微博

	throws SQLException {

		List<Weibo> searchcontentlist = new ArrayList<Weibo>();
		
		String sql2 = "select * from weibo where w_id = any(select u_id from user where u_nickname like'%"+content+"%') order by w_date desc";
		
		DBConn dbConn = new DBConn();
		
		
		ResultSet rs = dbConn.execQuery(sql2, new Object[] {});
		
		try {
			while (rs.next()) {
				
				Weibo weibo = new Weibo();// 封装weibo对象
			
				weibo.setId(rs.getInt(1));
				weibo.setW_id(rs.getInt(2));
				weibo.setW_content(rs.getString(3));
			
				weibo.setW_date(rs.getString(4));
				weibo.setW_unick(rs.getString(5));
				weibo.setW_upic(rs.getString(6));
				
				searchcontentlist.add(weibo);// 封装好的weibo放入一个集合
			
		}
			return searchcontentlist;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			
			dbConn.closeConn();
		
		}



}

	public Weibo getWeibobyId(int id)// 用户获取关注的人的微博
				throws SQLException {
			
			DBConn dbConn = new DBConn();
			
			Weibo weibo = new Weibo();
			
			String sql2 = "select * from weibo where id = ? order by w_date desc";
			
			ResultSet rs = dbConn.execQuery(sql2, new Object[] { id });
			while (rs.next()) {
			
				// 封装weibo对象
			
				weibo.setId(rs.getInt(1));
				weibo.setW_id(rs.getInt(2));
				weibo.setW_content(rs.getString(3));
			
				weibo.setW_date(rs.getString(4));
				weibo.setW_unick(rs.getString(5));
				weibo.setW_upic(rs.getString(6));
			
				
			}
			
			rs.close();
			dbConn.closeConn();
			return weibo;

}
public boolean sendWeibo4(Weibo weibo) { // 转发微博 无图片的
		
		int id = weibo.getId();
		int w_id = weibo.getW_id();// 与u_id外键相连，把前台的u_id赋给w_id,要发微博的人
			String w_content = weibo.getW_content();
			String w_unick = weibo.getW_unick();
			String w_pic = weibo.getW_upic();
		
			
			DBConn dbConn = new DBConn();
			
			int rs=0;
			int rs2=0;
			try {
			String strSql = "select w_times from weibo where id = ?";

					ResultSet rs1 = dbConn.execQuery(strSql, new Object[] { id });

					while (rs1.next()) {
						int count = rs1.getInt(1);
						count++;
						String sql = "insert into weibo (w_id,w_content,w_date,w_unick,w_upic,w_image,w_times) values(?,?,now(),?,?,null,?)";
						 
						rs = dbConn.execOther(sql, new Object[] { w_id, w_content, w_unick, w_pic, count });
						
						
						String sql2 = "update weibo set w_times = ? where id = ?";
						
						rs2 = dbConn.execOther(sql2, new Object[]{ count, id });
					}
					
				
				return rs > 0&& rs2 > 0 ? true : false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return false;
			}
		 
	}
	
public boolean sendWeibo3(Weibo weibo) { // 转发微博 有图片的
	
	int id = weibo.getId();
	int w_id = weibo.getW_id();// 与u_id外键相连，把前台的u_id赋给w_id,要发微博的人
		String w_content = weibo.getW_content();
		String w_unick = weibo.getW_unick();
		String w_pic = weibo.getW_upic();
		String w_image = weibo.getW_image();
		
		DBConn dbConn = new DBConn();
		
		int rs=0;
		int rs2=0;
		try {
		String strSql = "select w_times from weibo where id = ?";

				ResultSet rs1 = dbConn.execQuery(strSql, new Object[] { id });

				while (rs1.next()) {
					int count = rs1.getInt(1);
					count++;
					String sql = "insert into weibo (w_id,w_content,w_date,w_unick,w_upic,w_image,w_times) values(?,?,now(),?,?,?,?)";
					 
					rs = dbConn.execOther(sql, new Object[] { w_id, w_content, w_unick, w_pic, w_image, count });
					
					
					String sql2 = "update weibo set w_times = ? where id = ?";
					
					rs2 = dbConn.execOther(sql2, new Object[]{ count, id });
				}
				
			
			return rs > 0&& rs2 > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	 
}


public int showZhuanfaNum(Weibo weibo) { // 统计转发次数

	try {

		int id= weibo.getId();
		
		DBConn dbConn = new DBConn();
		String sql = "select w_times from weibo where id = ?";

			ResultSet rs = dbConn.execQuery(sql, new Object[] { id });

			if (rs.next()) {

				int rs1 = rs.getInt(1);
				dbConn.closeConn();
				return rs1;
			}
		
	} catch (SQLException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;

}

public boolean sendWeibo2(Weibo weibo) { // 增加微博

	String sql = "insert into weibo (w_id,w_content,w_date,w_unick,w_upic,w_image,w_times) values(?,?,now(),?,?,null,0)";

	int w_id = weibo.getW_id();// 与u_id外键相连，把前台的u_id赋给w_id
	String w_content = weibo.getW_content();
	String w_unick = weibo.getW_unick();
	String w_pic = weibo.getW_upic();


	DBConn dbConn = new DBConn();

	int rs = dbConn.execOther(sql, new Object[] { w_id, w_content, w_unick, w_pic});

	dbConn.closeConn();

	return rs > 0 ? true : false;

}

}
