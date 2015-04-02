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

public class CopyOfXinxi extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CopyOfXinxi() {
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
			sqlWhere1 += " and t1.cgoodstype = '" + dalei + "'";
		}
		if (pinzhong != null && pinzhong.length() > 0) {
			sqlWhere += " and t1.cgoodsid = '" + pinzhong + "'";
			sqlWhere1 += " and t1.cgoodsid = '" + pinzhong + "'";
		}
		if (guige != null && guige.length() > 0) {
			sqlWhere += " and t1.csize = '" + guige + "'";
			sqlWhere1 += " and t1.csize = '" + guige + "'";
		}
		int start = (page - 1) * pagesize + 1;
		int end = start + pagesize - 1;

		SqlUtil util = new SqlUtil();
		String sql = "select * from ("
				+ "select *,row_number() over(order by dupdatedate desc) as rownum from ("
				+ "SELECT  t1.id,t1.cgoodstype,t1.cgoodsid,t1.csize,t1.ctexture ,t1.cplace,t1.dprice,t1.dweight,t1.cdcname,t1.dupdatedate"
				+ ",t2.ccusfullname,t2.ccusperson,t2.ccusphone,t2.ccusmobile,t2.ccusfax ,t2.ccusemail,t2.ccusaddress,t2.cmemo "
				+ "FROM  goodsrecord t1 left join customer t2 on  t1.ccuscode= t2.ccuscode where 1=1 "
				+ sqlWhere + " ) p) u where rownum between " + start + " and "
				+ end;
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
