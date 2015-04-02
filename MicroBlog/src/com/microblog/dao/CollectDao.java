package com.microblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.microblog.dbutil.DBConn;
import com.microblog.po.Collection;
import com.microblog.po.Weibo;

public class CollectDao {

	
	public boolean collectWeibo1(Weibo weibo) { // 收藏带图片的

		String sql = "insert into collection (s_id,s_content,s_date,s_image) values(?,?,now(),?)";

		int s_id = weibo.getW_id();// 与u_id外键相连，把前台的u_id赋给w_id
		String s_content = weibo.getW_content();
		String s_image = weibo.getW_image();

		DBConn dbConn = new DBConn();

		int rs = dbConn.execOther(sql, new Object[] { s_id, s_content, s_image });

		dbConn.closeConn();

		return rs > 0 ? true : false;

	}
	public boolean collectWeibo2(Weibo weibo) { // 收藏不带图片的

		String sql = "insert into collection (s_id,s_content,s_date,s_image) values(?,?,now(),null)";

		int s_id = weibo.getW_id();// 与u_id外键相连，把前台的u_id赋给w_id
		String s_content = weibo.getW_content();

		

		DBConn dbConn = new DBConn();

		int rs = dbConn.execOther(sql, new Object[] { s_id, s_content });

		dbConn.closeConn();

		return rs > 0 ? true : false;

	}
	
	
	
	public ArrayList<Collection> getWeibobyU_id(int u_id) throws SQLException {// 获取某位用户自己的收藏

		String sql = "select * from collection where s_id=?";

		DBConn dbConn = new DBConn();
		ResultSet rs = dbConn.execQuery(sql, new Object[] { u_id });

		if (!rs.next()) {
			rs.close();
			dbConn.closeConn();
			return null;
		} else {
			ArrayList<Collection> Result = new ArrayList<Collection>();

			do {
				Collection collection = new Collection();
				
				collection.setId(rs.getInt(1));
				collection.setS_id(rs.getString(2));
				collection.setS_content(rs.getString(3));
				collection.setS_date(rs.getString(4));
				
				collection.setS_image(rs.getString(5));
				

				Result.add(collection);
			} while (rs.next());
			rs.close();
			dbConn.closeConn();
			return Result;
		}
	}
	
	public boolean deleteCollection(Collection collection) { // 删除收藏

		String sql = "delete from collection where s_id=? and id=?";

		String s_id = collection.getS_id();
		int id = collection.getId();

		DBConn dbConn = new DBConn();

		int rs = dbConn.execOther(sql, new Object[] { s_id, id });

		dbConn.closeConn();
		return rs > 0 ? true : false;

	}
	
}
