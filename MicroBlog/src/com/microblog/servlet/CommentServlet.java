package com.microblog.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.CommentDao;
import com.microblog.dao.WeiboDao;
import com.microblog.po.Comment;
import com.microblog.po.Weibo;

public class CommentServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		try {
		//int id = Integer.parseInt(request.getParameter("id"));
		int c_id =Integer.parseInt(request.getParameter("id"));
		int c_uid =Integer.parseInt(request.getParameter("c_uid"));
		String c_upic = request.getParameter("c_upic");
		String c_unick = request.getParameter("c_unick");
		String c_content = request.getParameter("textarea");
		
		
		Comment comment = new Comment();
		
		//comment.setId(id);
		comment.setC_id(c_id);
		comment.setC_uid(c_uid);
		comment.setC_content(c_content);
		comment.setC_unick(c_unick);
		comment.setC_upic(c_upic);
		
		CommentDao commentDao = new CommentDao();
		boolean flag = commentDao.sendComment(comment);
		int res = flag?1:2;
      
		int id = Integer.parseInt(request.getParameter("id"));//将当前被评论的微博取出放入session
		
		WeiboDao weiboDao = new WeiboDao();
		
		Weibo weibo = new Weibo();
		
	    
			weibo =	weiboDao.getWeibobyId(id);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("weibo", weibo);
			
			
			response.sendRedirect("../servlet/AllCommentServlet?msg="+res);
		
		
		
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	
	}

}
