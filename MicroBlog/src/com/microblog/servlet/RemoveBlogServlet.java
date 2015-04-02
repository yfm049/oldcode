package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.WeiboDao;
import com.microblog.po.Weibo;

public class RemoveBlogServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		int u_id = Integer.parseInt(request.getParameter("u_id"));

		int id =  Integer.parseInt(request.getParameter("w_content"));
		
		Weibo weibo = new Weibo();

		weibo.setId(id);
		
		WeiboDao weiboDao = new WeiboDao();
		
		boolean flag = weiboDao.deleteWeibo(weibo);
		
		int res = flag?1:2;
		//如何接受要在index 页面里写
		response.sendRedirect("../servlet/MyBlogServlet?msg="+res);
		
	}	
		
}