package com.xinxi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.xinxi.db.SqlUtil;

public class GoodsItem extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GoodsItem() {
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
		// http://10.120.148.55:8080/xinxi/servlet/GoodsItem?goodstype=°å²Ä

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String goodstype = request.getParameter("goodstype");
		String sqlWhere = "";
		if (goodstype != null && !goodstype.equals("")) {
			sqlWhere = " and cgoodstype='" + goodstype + "'";
		}

		SqlUtil util = new SqlUtil();
		String sql = "select cgoodsname from goodsitem where 1=1" + sqlWhere
				+ " order by iindex";
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
