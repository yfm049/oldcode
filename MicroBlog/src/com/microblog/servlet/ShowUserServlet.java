package com.microblog.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.UserDao;
import com.microblog.dao.WeiboDao;
import com.microblog.po.User;
import com.microblog.po.Weibo;

public class ShowUserServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse 

response)
			throws ServletException, IOException {
 
		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		int u_id = Integer.parseInt(request.getParameter("u_id"));
		
		WeiboDao weiboDao = new WeiboDao();
		
		
		try {
			ArrayList<Weibo> alllist = weiboDao.getWeibobyU_id(u_id);
			

			HttpSession session = request.getSession();
			
			session.setAttribute("alllist", alllist);
			
			UserDao userDao = new UserDao();
			
			User user1 = (User)userDao.showUser(u_id);
			
			
			session.setAttribute("user1", user1);
			request.getRequestDispatcher("/user.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
