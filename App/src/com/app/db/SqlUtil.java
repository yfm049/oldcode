package com.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



//数据查询工具类
public class SqlUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//根据sql获取数据
	public static List<Map<String, Object>> search(String sql, Object[] lo) {
		Connection con = DbCon.getcon();
		List<Map<String, Object>> lm = new ArrayList<Map<String, Object>>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			if (lo != null) {

				for (int i = 1; i <= lo.length; i++) {
					ps.setObject(i, lo[i - 1]);
				}
			}
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rm = rs.getMetaData();
			int cont = rm.getColumnCount();
			while (rs.next()) {
				Map<String, Object> mp = new HashMap<String, Object>();
				for (int c = 1; c <= cont; c++) {
					Object o = rs.getObject(c);
					if (o instanceof Date) {
						String date = o == null ? "" : sdf.format(o);
						mp.put(rm.getColumnName(c), date);
						continue;
					}
					mp.put(rm.getColumnName(c), o == null ? "" : o);
				}
				lm.add(mp);
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(sql);
			e.printStackTrace();
		}finally{
			DbCon.closecon(con);
		}
		return lm;
	}
	
	//根据sql保存数据
	public static int save(String sql, Object[] lo){
		int row=0;
		Connection con = DbCon.getcon();
		try {
			
			PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			if (lo != null) {

				for (int i = 1; i <= lo.length; i++) {
					ps.setObject(i, lo[i - 1]);
				}
			}
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs!=null&&rs.next()){
				row=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbCon.closecon(con);
		}
		return row;
	}
	//根据sql更新数据
	public static int update(String sql, Object[] lo){
		int row=0;
		Connection con = DbCon.getcon();
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			if (lo != null) {

				for (int i = 1; i <= lo.length; i++) {
					ps.setObject(i, lo[i - 1]);
				}
			}
			row=ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbCon.closecon(con);
		}
		return row;
	}
	//根据sql查询数据
	public static Map<String, Object> searchMap(String sql, Object[] lo) {
		Connection con = DbCon.getcon();
		Map<String, Object> mp = new HashMap<String, Object>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			if (lo != null) {

				for (int i = 1; i <= lo.length; i++) {
					ps.setObject(i, lo[i - 1]);
				}
			}
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rm = rs.getMetaData();
			int cont = rm.getColumnCount();
			while (rs.next()) {
				
				for (int c = 1; c <= cont; c++) {
					Object o = rs.getObject(c);
					if (o instanceof Date) {
						String date = o == null ? "" : sdf.format(o);
						mp.put(rm.getColumnName(c), date);
						continue;
					}
					mp.put(rm.getColumnName(c), o == null ? "" : o);
				}
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(sql);
			e.printStackTrace();
		}finally{
			DbCon.closecon(con);
		}
		return mp;
	}
	//根据sql查询总数
	public static int searchCount(String sql, Object[] lo) {
		int count=0;
		String sqlcount="select count(*) ct from ("+sql+") p";
		System.out.println(sqlcount);
		Connection con = DbCon.getcon();
		try {
			PreparedStatement ps = con.prepareStatement(sqlcount);
			if (lo != null) {

				for (int i = 1; i <= lo.length; i++) {
					ps.setObject(i, lo[i - 1]);
				}
			}
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count=rs.getInt("ct");
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(sql);
			e.printStackTrace();
		}finally{
			DbCon.closecon(con);
		}
		return count;
	}
//select * from ( select TOP 18 * FROM ( SELECT TOP 18*3   * from ()   ORDER BY id ASC ) as aSysTable   ORDER BY id DESC ) as bSysTable   ORDER BY id ASC 
	
	
}
