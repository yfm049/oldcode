package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		//String send = request.getParameter("send");
		String receive = request.getParameter("receive");
		//String item = request.getParameter("item");
		String u_pwd = request.getParameter("u_pwd");
		//String password = request.getParameter("password");
		
		response.setContentType("text/html;charset=utf-8");
	
		Email mail = new SimpleEmail();
		
		mail.setCharset("utf-8");
		
		mail.setHostName("smtp.qq.com");
		
		mail.setAuthentication("370810365@qq.com", "02100515wsljx");  
		
		try {
			mail.setFrom("370810365@qq.com");
			
			mail.addTo(receive);
			
			mail.setSubject("Find password");
			
			mail.setMsg(u_pwd);
			mail.send(); 
			
			int res=1;
			
			response.sendRedirect("../backupok.jsp?msg="+res);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
