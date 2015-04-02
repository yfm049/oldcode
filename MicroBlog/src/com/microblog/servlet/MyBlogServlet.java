package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.RelaDao;
import com.microblog.dao.WeiboDao;
import com.microblog.po.User;
import com.microblog.po.Weibo;

public class MyBlogServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		try {
			
			//int u_id = Integer.parseInt(request.getParameter("u_id"));
			HttpSession session = request.getSession();
			User user=(User) session.getAttribute("user");
			int u_id=user.getU_id();
			
			
			WeiboDao weiboDao = new WeiboDao();
			ArrayList<Weibo> lstPro = new ArrayList<Weibo>();

			lstPro = weiboDao.getWeibobyU_id(u_id);
			session.setAttribute("lstPro", lstPro);
			

			RelaDao relaDao = new RelaDao();
			session.setAttribute("ConcersNum", relaDao.showConcersNum(u_id));

			session.setAttribute("FansNum", relaDao.showFansNum(u_id));
			session.setAttribute("WeiboNum", weiboDao.showWeiboNum(u_id));
			

			request.getRequestDispatcher("/profile.jsp").forward(request,response);

		} catch (Exception e) {

			e.printStackTrace();
		}


		
		
	}

}
