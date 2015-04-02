package com.xinxi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xinxi.db.SqlUtil;
import com.xinxi.util.ExtHelper;

public class Xinxi extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Xinxi() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://127.0.0.1:8080/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String dalei = request.getParameter("dalei");
		String pinzhong = request.getParameter("pinzhong");
		String guige = request.getParameter("guige");

		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pagesize = Integer.parseInt(request.getParameter("pagesize"));
		String sqlWhere = "";
		String sqlWhere1 = "";
		String sqlFenye = "";
		if (dalei != null && dalei.length() > 0) {
			sqlWhere += " and t1.cgoodstype = '" + dalei + "'";
			sqlWhere1 += " and cgoodstype = '" + dalei + "'";
		}
		if (pinzhong != null && pinzhong.length() > 0) {
			sqlWhere += " and t1.cgoodsid = '" + pinzhong + "'";
			sqlWhere1 += " and cgoodsid = '" + pinzhong + "'";
		}
		if (guige != null && guige.length() > 0) {
			sqlWhere += " and t1.csize like '%" + guige + "%'";
			sqlWhere1 += " and csize  like '%" + guige + "%'";
		}
		int qian = (page - 1) * pagesize;

		SqlUtil util = new SqlUtil();
		String sql = "select Top "
				+ pagesize
				+ "* from "
				+ " (SELECT  t1.id,t1.cgoodstype,t1.cgoodsid,t1.csize,t1.ctexture ,t1.cplace,t1.dprice,t1.dweight,t1.cdcname,t1.dupdatedate"
				+ " ,t2.ccusfullname,t2.ccusperson,t2.ccusphone,t2.ccusmobile,t2.ccusfax ,t2.ccusemail,t2.ccusaddress,t2.cmemo "
				+ " FROM  goodsrecord t1 left join customer t2 on  t1.ccuscode= t2.ccuscode where 1=1 "
				+ sqlWhere + ")w1 where 1=1 " + " and id not in(select Top "
				+ qian + " id from goodsrecord t1 where 1=1 " + sqlWhere
				+ " order by id desc) order by id desc";
		System.out.println(sql);
		List list = util.search(sql, null);
		JSONArray array = JSONArray.fromObject(list);
		System.out.println(array.toString());
		out.println(array.toString());
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
