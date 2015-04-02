package com.microblog.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.RelaDao;
import com.microblog.dao.WeiboDao;

public class BefansServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取客户端传入的数据
        String u_id = request.getParameter("u_id");
		String g_id = request.getParameter("g_id");
		//处理数据，后台支持
		RelaDao relaDao = new RelaDao();
		try {
			boolean flag = relaDao.Befans(u_id, g_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//页面跳转
		
		HttpSession session = request.getSession();
		
		int u_id1 = Integer.parseInt(u_id);
		
		WeiboDao weiboDao = new WeiboDao();

		try {
			session.setAttribute("ConcersNum", relaDao.showConcersNum(u_id1));
			
			session.setAttribute("FansNum", relaDao.showFansNum(u_id1));
			session.setAttribute("WeiboNum",weiboDao.showWeiboNum(u_id1));
			request.getRequestDispatcher("/servlet/AttentionSevlet?msg="+u_id).forward(request, response);
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

}
