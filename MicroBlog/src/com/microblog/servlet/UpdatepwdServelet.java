package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.EdictDao;

import com.microblog.po.User;

public class UpdatepwdServelet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//int o_id = Integer.parseInt(request.getParameter("u_id"));
		HttpSession session = request.getSession();
		User user =new   User();
		//获取修改的ID号
		user=(User)session.getAttribute("user");
		
		String  newpwd = request.getParameter("newpwd1");
		String  oldpwd = request.getParameter("old_pwd");
		EdictDao edictdao=new EdictDao();
		  boolean flag  =edictdao.cheak(user.getU_id(),oldpwd);
	    if (flag==true){
	    	  edictdao.updatepwd(newpwd, user.getU_id());
	    	  response.sendRedirect("../mypassword.jsp?msg=1");
	    }else{
	    	 response.sendRedirect("../mypassword.jsp?msg=2");
	    }
		  
	}
		
	}


