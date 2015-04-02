package com.app.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
//连接数据库
public class DbCon {
	public static BoneCP connectionPool = null;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/app?useUnicode=true&characterEncoding=UTF-8"); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
			config.setUsername("root"); 
			config.setPassword("123456");
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			connectionPool = new BoneCP(config); // setup the connection pool
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取连接
	public static Connection getcon() {
		Connection connection=null;
		try {
			connection = connectionPool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	//关闭连接
	public static boolean closecon(Connection con) {
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

}
