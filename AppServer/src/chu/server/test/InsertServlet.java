package chu.server.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = new String(req.getParameter("username").getBytes("UTF-8"),"UTF-8");
		String password = new String(req.getParameter("password").getBytes("UTF-8"),"UTF-8");
		
		System.out.println("注册名：" + username);
		System.out.println("密码：" + password);
		
		DB db = new DB();
        resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		boolean isUserRegister = db.isUserRegister(username);
		if(isUserRegister==true){
			System.out.println("用户已存在");
			String data = "Exist";
			out.print(data);
		}else if(isUserRegister==false){
			boolean insertUser = db.checkUserRegister(username, password);
			if(insertUser==true){
				System.out.println("注册成功");
				String successData = "OK";
				out.print(successData);
			}else{
				System.out.println("注册失败");
				String failedData = "Error";
				out.print(failedData);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
}
