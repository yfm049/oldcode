package chu.server.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.mysql.jdbc.PreparedStatement;

import chu.server.bean.PlaneInfo;

public class DB {
	private List<PlaneInfo> list;
	Connection ct;
	Statement sm;
	ResultSet rs;

	// 构造器建立与数据库的连接
	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/chu?useUnicode=true&characterEncoding=utf-8",
					"root", "123456");
			sm = ct.createStatement();
			System.out.println("1111");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 验证登陆方法
	public boolean chechUser(String username, String password) {
		// String sql = " select * from chu_user where name=' " + username +
		// " ' and password=' " + password + " ' ";
		String sql = "select name,password from chu_user where name='"
				+ username + "' and password='" + password + "'";
		try {
			// sm = ct.createStatement();
			rs = sm.executeQuery(sql);
			while (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}

	// 查询数据库表chu_plane_info好的机票信息
	public List<PlaneInfo> getPlaneInfo(String from, String to, String date) {
		try {
			list = new ArrayList<PlaneInfo>();
			// sm = ct.createStatement();
			String sql = "select * from chu_plane_info where _from='" + from
					+ "' and _to='" + to + "' and time='" + date + "'";
			System.out.println(sql);
			rs = sm.executeQuery(sql);
			while (rs.next()) {
				PlaneInfo planeInfo = new PlaneInfo();
				planeInfo.set_from(rs.getString(1));
				planeInfo.set_to(rs.getString(2));
				planeInfo.setTime(rs.getString(3));
				planeInfo.setPrice(rs.getString(4));
				planeInfo.setAirport(rs.getString(5));
				planeInfo.setLevel(rs.getString(6));
				list.add(planeInfo);
			}
			rs.close();
			System.out.println(list.size()+"--");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 插入用户预订机票信息表
	public boolean insertUserTicketInfo(String name, String from, String to,
			String time, String price, String airport, String level) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssS");
			String sql = "insert into chu_user_ticket values(?,?,?,?,?,?,?,?)";
			PreparedStatement psm = (PreparedStatement) ct
					.prepareStatement(sql);
			psm.setString(1, name);
			psm.setString(2, from);
			psm.setString(3, to);
			psm.setString(4, time);
			psm.setString(5, price);
			psm.setString(6, airport);
			psm.setString(7, level);
			psm.setString(8, sdf.format(new Date()));
			psm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 判断是否重复预定方法
	public boolean checkUserTicket(String name, String from, String to,
			String time, String price, String airport, String level) {
		try {
			String sql = "select * from chu_user_ticket where username='"
					+ name + "'and _from='" + from + "'and _to='" + to
					+ "'and time='" + time + "'and price='" + price
					+ "'and airport='" + airport + "'and level='" + level + "'";
			rs = sm.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	// 验证是否注册成功方法
	public boolean checkUserRegister(String username, String password) {
		try {
			String sql = "insert into chu_user values(?,?)";
			PreparedStatement psm = ct.prepareStatement(sql);
			psm.setString(1, username);
			psm.setString(2, password);
			psm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 验证是否重复注册方法
	public boolean isUserRegister(String username) {
		try {
			String sql = "select * from chu_user where name='" + username + "'";
			rs = sm.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	// 查询用户预订机票信息，返回list
	public List<PlaneInfo> getUserTicketInfo(String username) {
		try {
			list = new ArrayList<PlaneInfo>();
			String sql = "select * from chu_user_ticket where username='"
					+ username + "'";
			rs = sm.executeQuery(sql);
			while (rs.next()) {
				PlaneInfo planeInfo = new PlaneInfo();
				planeInfo.set_from(rs.getString(2));
				planeInfo.set_to(rs.getString(3));
				planeInfo.setTime(rs.getString(4));
				planeInfo.setPrice(rs.getString(5));
				planeInfo.setAirport(rs.getString(6));
				planeInfo.setLevel(rs.getString(7));
				planeInfo.setCode(rs.getString(8));
				list.add(planeInfo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// 修改密码方法
	public boolean modifyPsw(String username, String password)
			throws SQLException {
		String sql1 = "update chu_user set password='" + password
				+ "'where name='" + username + "'";
		sm.execute(sql1);
		String sql2 = "select password from chu_user where password='"
				+ password + "'";
		rs = sm.executeQuery(sql2);
		while (rs.next()) {
			return true;
		}
		return false;

	}

	// 删除用户预订机票方法
	public boolean deleteUserTicket(String username, String from, String to)  {
		try{
		String sql = "delete from chu_user_ticket where username=? and _from=? and _to=?";
        PreparedStatement psm = ct.prepareStatement(sql);
        psm.setString(1, username);
        psm.setString(2, from);
        psm.setString(3, to);
        psm.executeUpdate();
		return true;

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

}
