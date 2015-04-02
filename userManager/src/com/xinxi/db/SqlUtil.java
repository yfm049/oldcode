package com.xinxi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SqlUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
			System.out.println("执行" + sql + "抛出异常");
			e.printStackTrace();
		}finally{
			DbCon.closecon(con);
		}
		return lm;
	}
	public static int save(String sql, Object[] lo){
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
			System.out.println("执行" + sql + "抛出异常");
			e.printStackTrace();
		}finally{
			DbCon.closecon(con);
		}
		return mp;
	}

}
