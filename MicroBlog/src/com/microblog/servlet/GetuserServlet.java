package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.EdictDao;
import com.microblog.po.User;


public class GetuserServlet extends HttpServlet {

	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String u_id = request.getParameter("u_id");
		//根据id查询用户
		
		User user = new EdictDao().getUserById(Integer.parseInt(u_id));
     
		//将用户实例保存到request作用域下。
		request.setAttribute("user", user );
		//跳转到显示界面
		request.getRequestDispatcher("/userinfo.jsp").forward(request, response);
		
		
		
	}

}
