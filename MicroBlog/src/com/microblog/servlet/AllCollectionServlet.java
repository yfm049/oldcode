package com.microblog.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.CollectDao;
import com.microblog.po.Collection;
import com.microblog.po.User;
import com.microblog.po.Weibo;

public class AllCollectionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int u_id = user.getU_id();

		CollectDao collectDao = new CollectDao();

		ArrayList<Collection> alllist;

		try {
			alllist = collectDao.getWeibobyU_id(u_id);
			if (alllist != null) {

				session.setAttribute("alllist", alllist);

				request.getRequestDispatcher("/collection.jsp").forward(
						request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
