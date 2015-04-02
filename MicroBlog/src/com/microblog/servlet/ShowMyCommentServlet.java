package com.microblog.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.CommentDao;
import com.microblog.po.Comment;

public class ShowMyCommentServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		
		
		CommentDao commentDao = new CommentDao();
		
		ArrayList<Comment> alllist;
		try {
			alllist = commentDao.getCommentbyId(c_id);
			HttpSession session = request.getSession();
			session.setAttribute("alllist", alllist);
			
			
			
		int num = commentDao.showCommentNum(c_id);
			
			session.setAttribute("num", num);
			
			request.getRequestDispatcher("/comment.jsp").forward(
					request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}

}
