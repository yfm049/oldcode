package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.BackuppwdDao;

import com.microblog.po.User;

public class BackuppwdServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String  userid=request.getParameter("userid");
		String  newpwd = request.getParameter("newpwd3");
		BackuppwdDao backuppwddao=new BackuppwdDao();
		backuppwddao.updatepwd(newpwd,userid);
		response.sendRedirect("../login.jsp?msg=1");
	 
	    }
		  
		
		}
		