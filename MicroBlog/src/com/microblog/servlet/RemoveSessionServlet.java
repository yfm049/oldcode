package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveSessionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		

		
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		//Ïú»Ù·½·¨
		session.invalidate();
		//É¾Cookie		
		Cookie cookie = new Cookie("autologinpwd", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		//É¾Cookie

		response.sendRedirect("../login.jsp");
	}

}
