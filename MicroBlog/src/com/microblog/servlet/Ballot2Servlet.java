package com.microblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.BallotDao;
import com.microblog.po.Ballot;

public class Ballot2Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String movie = request.getParameter("movie");

		Ballot ballot = new Ballot();

		ballot.setChoose(movie);

		BallotDao ballotDao = new BallotDao();

		boolean flag = ballotDao.select2(ballot);

		int res = flag ? 1 : 2;

		response.sendRedirect("../ballot.jsp?msg=" + res);

	}

}
