package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.WeiboDao;
import com.microblog.po.Weibo;

public class ZhuanfaServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//从前台获得数据
		int id = Integer.parseInt(request.getParameter("id"));
		int w_id = Integer.parseInt(request.getParameter("zhuanw_id"));
		String w_content = request.getParameter("zhuanw_content");
		String w_unick = request.getParameter("zhuanw_unick");
		String w_upic = request.getParameter("zhuanw_upic");
		String w_image = request.getParameter("zhuanw_image");

		
		//处理数据,封装数据传到dao
			Weibo weibo = new Weibo();
			weibo.setId(id);
			weibo.setW_id(w_id);
			weibo.setW_content(w_content);
			weibo.setW_unick(w_unick);
			weibo.setW_upic(w_upic);
		
			
			WeiboDao weiboDao = new WeiboDao();
			if (w_image.equals("null")){	
			//调用dao
				
				boolean flag = weiboDao.sendWeibo4(weibo);
			}
			
		
		
		else{

			weibo.setW_image(w_image);
		
			boolean flag = weiboDao.sendWeibo3(weibo);
			
			}
			int showZhuanfaNum = weiboDao.showZhuanfaNum(weibo);

			HttpSession session = request.getSession();
			session.setAttribute("showZhuanfaNum", showZhuanfaNum);

			request.getRequestDispatcher("/servlet/AllServlet").forward(
					request, response);
		
}

	}


