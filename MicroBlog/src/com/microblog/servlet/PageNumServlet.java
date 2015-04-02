package com.microblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.WeiboDao;
import com.microblog.po.User;
import com.microblog.po.Weibo;

public class PageNumServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		int pg_nu=Integer.parseInt(request.getParameter("pg_nu"));
		HttpSession session = request.getSession();
		User user =(User) session.getAttribute("user");
		int u_id = user.getU_id();
		WeiboDao weiboDao = new WeiboDao();
		List<Weibo> lstPro = new ArrayList<Weibo>();
		try {
			lstPro = weiboDao.getAllWeibobyU_id(u_id, u_id,pg_nu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("lstPro", lstPro);
		//session.setAttribute("lstPro", lstPro);
		int su=lstPro.size();
		System.out.println(su);
		if(su!=0){
			request.getRequestDispatcher("/home.jsp").forward(request,
					response);	
		}
		//response.sendRedirect("../home.jsp");
		
	}

}
