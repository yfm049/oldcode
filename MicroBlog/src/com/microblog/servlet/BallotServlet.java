package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.BallotDao;
import com.microblog.po.Ballot;

public class BallotServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tennis = request.getParameter("tennis");
		
		Ballot ballot = new Ballot();
		
		ballot.setChoose(tennis);
		
		BallotDao ballotDao = new BallotDao();
		
		boolean flag = ballotDao.select(ballot);
		
		int res = flag?1:2;
		
		response.sendRedirect("../ballot.jsp?msg="+res);
		
	}

}
