package com.microblog.servlet;

import java.awt.SystemColor;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.EdictDao;
import com.microblog.po.User;

public class UpdateServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User user =new   User();
		//获取修改的ID号
		user=(User)session.getAttribute("user");
		//int up_id = 
			//Integer.parseInt(request.getParameter("u_id"));
		
		//和录入数据是一样的，获取表单中大量提交数据
	
		
	
		String u_nickname=request.getParameter("u_nickname");
		String u_sex=request.getParameter("u_sex");
		String u_city=request.getParameter("u_city");
		String u_date=request.getParameter("u_date");
		String u_qq=request.getParameter("u_qq");
		String u_msn=request.getParameter("u_msn");
		String u_motto=request.getParameter("u_motto");
		String u_realname=request.getParameter("u_realname");		
	
		
	 
	    user.setU_nickname(u_nickname);
	    user.setU_sex(u_sex);
	    user.setU_city(u_city);
	    user.setU_date(u_date);
	    user.setU_qq(u_qq);
	    user.setU_msn(u_msn);
	    user.setU_motto(u_motto);
	    user.setU_realname(u_realname);
	    //user.setU_id(up_id);
	   //根据处理结果响应客户端	      
	      
		//交给Dao 去处理。   updateStudent(Student student , int up_id);
		EdictDao edictdao=new EdictDao();
	    edictdao.updateUser(user,user.getU_id());	    
	    
		session.setAttribute("user", user);
	   // int flag=1;
		//页面跳转
		//request.getRequestDispatcher("/userinfo.jsp?msg=1").forward(request, response);
		// String path = flag?"../mypassword.jsp?msg=1":"../error1.jsp";
		 // System.out.println(flag);
		  response.sendRedirect("../userinfo.jsp?msg=1");
	}

	}

