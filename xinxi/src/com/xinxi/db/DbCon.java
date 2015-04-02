package com.xinxi.db;

import java.net.ProxySelector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbCon {
	public Connection getcon() {
		ProxySelector.setDefault(null);
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(
					"jdbc:sqlserver://127.0.0.1:13345;databasename=ttsteelfetch",
					"sa", "cc88888888cc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return con;
	}

	public boolean closecon(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return true;
	}

	public static void main(String[] arg) {
		new DbCon().getcon();
	}
}
