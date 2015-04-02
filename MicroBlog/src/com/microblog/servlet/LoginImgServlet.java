package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.LoginDaoImpl;
import com.microblog.po.User;

public class LoginImgServlet extends HttpServlet {


	public LoginImgServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		//System.out.println("www");
		List<User> list=new ArrayList<User>();
		LoginDaoImpl login=new LoginDaoImpl();
		list=login.getUsers();
        request.setAttribute("fri_list",list);		
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
		public void init() throws ServletException {
			// Put your code here}

}
}
