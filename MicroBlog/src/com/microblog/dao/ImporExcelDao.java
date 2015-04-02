package com.microblog.dao;

import java.util.Iterator;
import java.util.List;

import com.microblog.dbutil.DBConn;
import com.microblog.po.User;

public class ImporExcelDao {

	public boolean importExcel(List temp) {

		String u_account = "";
		String u_pwd = "";
		String u_nickname = "";
		String u_sex = "";

		String strSQL = "insert into user values(null,?,?,?,?,null,null,null,null,null,null,null,null,null,null)";

		int rs = 0;
		Iterator iter = temp.iterator();
		while (iter.hasNext()) {
			User user = (User) iter.next();

			u_account = user.getU_account();
			u_pwd = user.getU_pwd();
			u_nickname = user.getU_nickname();
			u_sex = user.getU_sex();
			DBConn dbConn = new DBConn();
			rs = dbConn.execOther(strSQL, new Object[] { u_account, u_pwd, u_nickname, u_sex });

			dbConn.closeConn();
		}

		return rs > 0 ? true : false;
	}

}
