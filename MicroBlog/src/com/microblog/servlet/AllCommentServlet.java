package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.CommentDao;
import com.microblog.dao.WeiboDao;
import com.microblog.po.Comment;
import com.microblog.po.User;
import com.microblog.po.Weibo;

public class AllCommentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		try {
		
	    
		CommentDao commentDao = new CommentDao();
		
		HttpSession session = request.getSession();
		
		Weibo weibo=(Weibo)session.getAttribute("weibo");
		
		
		
		int id = weibo.getId();
		
		
		 ArrayList<Comment> alllist = commentDao.getCommentbyId(id);
		 
				if(alllist!=null) {
					
					session.setAttribute("alllist", alllist);
					
					request.getRequestDispatcher("/showcontent.jsp").forward(request, response);
		}
				
				
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		
		
		
	}

}
