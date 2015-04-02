package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinxi.db.SqlUtil;

public class AddAuth extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddAuth() {
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

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String imei=request.getParameter("imei");
		String sql="select * from auth where imei=?";
		Map<String, Object> mo=SqlUtil.searchMap(sql, new Object[]{imei});
		Object zt=mo.get("zt");
		if("2".equals(zt)){
			Object code=mo.get("code");
			if(code!=null&&!"".equals(code)){
				//推送为3
				sql="update auth set zt=4 where imei=?";
				SqlUtil.save(sql, new Object[]{imei});
				out.print("授权码为"+code+",请注意查收");
			}else{
				out.print("");
			}
		}else if("3".equals(zt)){
			sql="update auth set zt=4 where imei=?";
			SqlUtil.save(sql, new Object[]{imei});
			out.print("管理员不同意你的申请。");
		}
		out.flush();
		out.close();
		
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
		PrintWriter out = response.getWriter();
		String name=request.getParameter("name");
		String imei=request.getParameter("imei");
		String pass=request.getParameter("pass");
		System.out.println(imei);
		String sql="select * from user where username=? and imei=? and password =?";
		List<Map<String, Object>> lmo=SqlUtil.search(sql, new Object[]{name,imei,pass});
		if(lmo.size()>0){
			sql="delete from auth where name=? and imei=?";
			int i=SqlUtil.save(sql, new Object[]{name,imei});
			sql="insert into auth(name,imei) values(?,?)";
			i=SqlUtil.save(sql, new Object[]{name,imei});
			if(i>0){
				out.print("请等待授权！");
			}else{
				out.print("获取失败，请重新获取");
			}
		}else{
			sql="select * from user where imei=?";
			lmo=SqlUtil.search(sql, new Object[]{imei});
			if(lmo.size()<=0){
				out.print("非授权手机，不能获取");
			}else{
				out.print("用户名或密码错误");
			}
		}
		out.flush();
		out.close();
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
