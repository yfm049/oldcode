package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.CollectDao;
import com.microblog.po.Weibo;

public class CollectionServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		
		//超链接与转发一样
		int w_id = Integer.parseInt(request.getParameter("zhuanw_id"));
		String w_content = request.getParameter("zhuanw_content");
		
		String w_image = request.getParameter("zhuanw_image");
		
		Weibo weibo = new Weibo();
		weibo.setW_id(w_id);
		weibo.setW_content(w_content);
		
		
		CollectDao collectDao = new CollectDao();
		
		
		if (w_image.equals("null")) {
			// 调用dao
			boolean flag = collectDao.collectWeibo2(weibo);
			 int res = flag?1:2;
			response.sendRedirect("../servlet/AllCollectionServlet?msg="+res);

		} else {
			weibo.setW_image(w_image);
			boolean flag = collectDao.collectWeibo1(weibo);
			
			int res = flag?1:2;

			// 页面跳转
			response.sendRedirect("../servlet/AllCollectionServlet?msg="+res);
		

		}
		
		
		
		
	}

}
