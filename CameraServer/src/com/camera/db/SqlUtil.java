package com.camera.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.camera.utils.Page;





public class SqlUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static List<Map<String, Object>> search(String sql, Object[] lo) {
		System.out.println(sql);
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
				Map<String, Object> mp = new LinkedHashMap<String, Object>();
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
	//select * from ( select TOP 18 * FROM ( SELECT TOP 18*3   * from ()   ORDER BY id ASC ) as aSysTable   ORDER BY id DESC ) as bSysTable   ORDER BY id ASC 

	//select * from (select *, ROW_NUMBER() over(order by px desc) rw from appinfo) p where rw between 21 and 40
	public static List<Map<String, Object>> search(String sql, Object[] lo,Page page) {

		int start=(page.getCpage()-1)*page.getPsize()+1;
		int end=page.getCpage()*page.getPsize();
		String px="desc";
		if(page.isIsasc()){
			px="asc";
		}
		String pagesql="select * from (select *, ROW_NUMBER() over(order by "+page.getPaixu()+" "+px+") rownum from ("+sql+") m) p where rownum between "+start+" and "+end+"";
		
		Connection con = DbCon.getcon();
		List<Map<String, Object>> lm = new ArrayList<Map<String, Object>>();
		try {
			PreparedStatement ps = con.prepareStatement(pagesql);
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
						System.out.println(rm.getColumnName(c)+"--"+o);
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
			System.out.println("执行" + sql + "抛出异常");
			e.printStackTrace();
		}finally{
			DbCon.closecon(con);
		}
		return count;
	}
	public static int delete(String sql, Object[] lo){
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
//select * from ( select TOP 18 * FROM ( SELECT TOP 18*3   * from ()   ORDER BY id ASC ) as aSysTable   ORDER BY id DESC ) as bSysTable   ORDER BY id ASC 
	
	
}
