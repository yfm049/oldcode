package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinxi.db.SqlUtil;

public class Auth extends HttpServlet {

	private static Random random = new Random();
	/**
	 * Constructor of the object.
	 */
	public Auth() {
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
		//获取新申请
		String sql="select a.*,b.username from auth a right join user b on a.imei=b.imei where a.zt=1";
		Map<String, Object> mo=SqlUtil.searchMap(sql, new Object[]{});
		System.out.println(mo.get("id")!=null&&mo.get("IMEI")!=null);
		if(mo.get("id")!=null&&mo.get("IMEI")!=null){
			String jsonobject="{\"id\":\""+mo.get("id")+"\",\"imei\":\""+mo.get("IMEI")+"\",\"name\":\""+mo.get("username")+"\"}";
			out.print(jsonobject);
			//已经推送0
			sql="update auth set zt=0 where id=?";
			SqlUtil.save(sql, new Object[]{mo.get("id")});
		}else{
			out.print("");
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
		String msg=request.getParameter("msg");
		String id=request.getParameter("id");
		PrintWriter out = response.getWriter();
		if("true".equals(msg)){
			//同意2
			String sql="update auth set code=?,zt=2 where id=?";
			SqlUtil.save(sql, new Object[]{random.nextInt(9999)+"",id});
		}else{
			//不同意3
			String sql="update auth set zt=? where id=?";
			SqlUtil.save(sql, new Object[]{"3",id});
		}
		out.print("操作成功");
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
