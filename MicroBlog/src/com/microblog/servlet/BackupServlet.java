package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.BackupDao;
import com.microblog.po.User;

public class BackupServlet extends HttpServlet {

	

	

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = null;
		String userid=request.getParameter("userid");
		String ques=request.getParameter("ques");
		
		BackupDao backupDao=new BackupDao();
		
		 user= (User)backupDao.check(userid, ques);
		 
		 if (user!=null){
		
		String u_pwd = user.getU_pwd();
		
		HttpSession session = request.getSession();
		session.setAttribute("u_pwd", u_pwd);
		
		
		request.getRequestDispatcher("/backupok.jsp").forward(
				request, response);
		}else{
			
			int res = 1;
			response.sendRedirect("../backup.jsp?msg="+res);
			
			
		}
//		String path=(flag==true)?"../backupok.jsp?msg="+userid:"../backup.jsp?msg=1";
//		response.sendRedirect(path);
		
		//MicroBlog/servlet/GetaccountServlet
		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
