package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.AddUserCheckDao;
import com.microblog.dao.UserDao;
import com.microblog.po.User;

public class AddUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 接受客户端数据
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rpassword = request.getParameter("rpassword");
		String nickname = request.getParameter("nickname");
		String sex = request.getParameter("sex");
		String city = request.getParameter("city");
		String ques = request.getParameter("ques");

		// 处理验证码
		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand");

		String input = (String) request.getParameter("yzm");

		if (rand.equals(input)) {
			int res0 = 1;

			// 处理数据,封装数据传到dao
			User user = new User();
			user.setU_account(email);
			user.setU_pwd(password);
			user.setU_pwd1(rpassword);
			user.setU_nickname(nickname);
			user.setU_sex(sex);
			user.setU_addr(city);
			user.setU_ques(ques);

			// 调用验证Dao
			AddUserCheckDao addCheckDao = new AddUserCheckDao();
			boolean flag1 = addCheckDao.check(email);
			int res1 = flag1 ? 1 : 2;

			int res2 = 2;

			if (flag1 == false) {
				// 调用dao

				UserDao userDao = new UserDao();
				boolean flag = userDao.addUser(user);
				res2 = flag ? 1 : 2;
			}

			response.sendRedirect("../register.jsp?msg=" + res2 + res1 + res0);
		} else {
			int res0 = 2;
//
//			User user = new User();
//			user.setU_account(email);
//			user.setU_pwd(password);
//			user.setU_pwd1(rpassword);
//			user.setU_nickname(nickname);
//			user.setU_sex(sex);
//			user.setU_addr(city);
//			user.setU_ques(ques);
//
//			// 调用验证Dao
//			AddUserCheckDao addCheckDao = new AddUserCheckDao();
//			boolean flag1 = addCheckDao.check(email);
//			int res1 = flag1 ? 1 : 2;
//
//			int res2 = 2;
//
//			if (flag1 == false) {
//				// 调用dao
//
//				UserDao userDao = new UserDao();
//				boolean flag = userDao.addUser(user);
//				res2 = flag ? 1 : 2;
			
			int res1 = 2;
			int res2 = 2;
			response.sendRedirect("../register.jsp?msg=" + res2 + res1 + res0);
			}
			// 页面跳转
			

		}

	}



//public static void main(String[] args){
//		System.out.print("res+res1");}
//
//}
