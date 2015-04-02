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

import com.xinxi.entity.Customer;

public class SqlUtil {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<Map<String, Object>> search(String sql, Object[] lo) {
		DbCon db = new DbCon();
		Connection con = db.getcon();
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
			db.closecon(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Ö´ÐÐ" + sql + "Å×³öÒì³£");
			e.printStackTrace();
		}
		return lm;
	}

	public Customer queryYH(String username) {
		DbCon db = null;
		Connection con = null;
		Statement sta = null;
		ResultSet rs = null;

		String sql = "select  ccusname, cpassword, dcusenddate  from customer where ccusname ='"
				+ username + "'";
		Customer customer = null;
		try {
			db = new DbCon();
			con = db.getcon();
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				customer = new Customer();
				customer.setUsername(rs.getString(1));
				customer.setPwdbyte(rs.getBytes(2));

				Object obj = rs.getObject(3);
				customer.setEndtime(sdf.format(obj));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				sta.close();
				db.closecon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return customer;
	}

	public byte[] queryPwd(String sql) {
		DbCon db = new DbCon();
		Connection con = db.getcon();
		byte[] content = null;
		try {
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				content = rs.getBytes(1);
				System.out.println("-------------|");
				for (int i = 0; i < content.length; i++) {
					System.out.print(content[i]);
				}
				System.out.println("|------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;
	}
}
