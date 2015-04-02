package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.CollectDao;
import com.microblog.po.Collection;

public class RemoveCollectionServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
//		int u_id = Integer.parseInt(request.getParameter("u_id"));
		String s_id = request.getParameter("w_id");
		int id =  Integer.parseInt(request.getParameter("w_content"));
		
		Collection collection = new Collection();
		collection.setS_id(s_id);
		collection.setId(id);
		
		CollectDao collectDao = new CollectDao();
		
		boolean flag = collectDao.deleteCollection(collection);
		
		int res = flag?1:2;
		//如何接受要在index 页面里写o
		response.sendRedirect("../servlet/AllCollectionServlet?msg1="+res);
	}

}
