package com.microblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.microblog.dbutil.DBConn;
import com.microblog.po.Ballot;

public class BallotDao {

	public boolean select(Ballot ballot) {

		String choose = ballot.getChoose();

		String sql = "insert into ballot (choose,que) values (?,1)";

		DBConn dbConn = new DBConn();

		int rs = dbConn.execOther(sql, new Object[] { choose });

		dbConn.closeConn();

		return rs > 0 ? true : false;

	}
	
	public boolean select2(Ballot ballot) {

		String choose = ballot.getChoose();

		String sql = "insert into ballot (choose,que) values (?,2)";

		DBConn dbConn = new DBConn();

		int rs = dbConn.execOther(sql, new Object[] { choose });

		dbConn.closeConn();

		return rs > 0 ? true : false;

	}

	public double showSelect1() {

		String sql = "select count(*) from ballot where choose='费德勒'";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
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

	public double showSelect2() {

		String sql = "select count(*) from ballot where choose='纳达尔'";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
			if (rs.next()) {
				int rs2 = rs.getInt(1);

				dbConn.closeConn();

				return rs2;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public double showSelect3() {

		String sql = "select count(*) from ballot where choose='德约科维奇'";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
			if (rs.next()) {
				int rs3 = rs.getInt(1);

				dbConn.closeConn();

				return rs3;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public double showSelect4() {

		String sql = "select count(*) from ballot where choose='罗迪克'";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
			if (rs.next()) {
				int rs4 = rs.getInt(1);

				dbConn.closeConn();

				return rs4;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public double showSelect5() {

		String sql = "select count(*) from ballot where choose='穆雷'";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
			if (rs.next()) {
				int rs5 = rs.getInt(1);

				dbConn.closeConn();

				return rs5;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
	
	public double showSelectSum() {

		String sql = "select count(*) from ballot where que=2";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
			if (rs.next()) {
				int rsSum = rs.getInt(1);

				dbConn.closeConn();

				return rsSum;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
	
	public double showSelect6() {

		String sql = "select count(*) from ballot where choose='加勒比海盗'";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
			if (rs.next()) {
				int rs6 = rs.getInt(1);

				dbConn.closeConn();

				return rs6;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	public double showSelect7() {

		String sql = "select count(*) from ballot where choose='功夫熊猫2'";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
			if (rs.next()) {
				int rs7 = rs.getInt(1);

				dbConn.closeConn();

				return rs7;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
	public double showSelect8() {

		String sql = "select count(*) from ballot where choose='哈利波特7'";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
			if (rs.next()) {
				int rs8 = rs.getInt(1);

				dbConn.closeConn();

				return rs8;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
	public double showSelect9() {

		String sql = "select count(*) from ballot where choose='变形金刚3'";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
			if (rs.next()) {
				int rs9 = rs.getInt(1);

				dbConn.closeConn();

				return rs9;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	public double showSelect10() {

		String sql = "select count(*) from ballot where choose='不爱看电影……爱看裸婚时代'";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {});

		try {
			if (rs.next()) {
				int rs10 = rs.getInt(1);

				dbConn.closeConn();

				return rs10;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
}
