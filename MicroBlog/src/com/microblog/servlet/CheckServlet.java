package com.microblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.UserDao;
import com.microblog.po.User;

public class CheckServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

PrintWriter out = response.getWriter();
		
		String email =request.getParameter("email");  //"admin";
		
		System.out.println(email);
		
		UserDao userDao=new UserDao();
		User user=userDao.checkEmail(email);
		
		if(user!=null) {
			out.print("true");
		}else {
			out.print("false");
		}
		out.flush();
		out.close();
	}
}



