package com.microblog.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.RelaDao;
import com.microblog.dao.WeiboDao;
import com.microblog.po.User;

public class AttentionSevlet extends HttpServlet {
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取客户端数据
		 String u_id = request.getParameter("u_id");
		//处理数据，调用DAO
		 RelaDao relaDao = new RelaDao();
		 List<User> fanslist = relaDao.getConcersbyResult(Integer.parseInt(u_id));

		//共享数据
			if(fanslist!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("fanslist", fanslist);
				
				
//				int u_id1 = Integer.parseInt("u_id");
//				WeiboDao weiboDao = new WeiboDao();
//				
//				try {
//					session.setAttribute("ConcersNum", relaDao.showConcersNum(u_id1));
//					session.setAttribute("FansNum", relaDao.showFansNum(u_id1));
//					session.setAttribute("WeiboNum", weiboDao.showWeiboNum(u_id1));
//					
					
					
					request.getRequestDispatcher("/attention.jsp?msg="+u_id).forward(request, response);
					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

				
		 
	}

	}}
