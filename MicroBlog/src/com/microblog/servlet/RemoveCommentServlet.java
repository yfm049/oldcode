package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.CommentDao;
import com.microblog.po.Comment;

public class RemoveCommentServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		request.setCharacterEncoding("utf-8");
		int id =  Integer.parseInt(request.getParameter("id"));
		
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		
		Comment comment = new Comment();
		
		comment.setId(id);
		
		CommentDao commentDao = new CommentDao();
		
		boolean flag = commentDao.deleteComment(comment);
		
		int res = flag?1:2;
		
		response.sendRedirect("../servlet/ShowMyCommentServlet?c_id="+c_id);
		
		
	}

}
