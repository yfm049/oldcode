package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.xinxi.db.DbCon;
import com.xinxi.db.SqlUtil;

public class add extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public add() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String IMEI = request.getParameter("imei");
		String sql = "insert into user(username,IMEI,password) values(?,?,?);";
		int row=SqlUtil.save(sql, new Object[]{username,IMEI,password});
		if(row>0){
			request.setAttribute("msg", "添加成功");
		}else{
			request.setAttribute("msg", "添加失败");
		}
		
		request.getRequestDispatcher("/servlet/Listdate").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
