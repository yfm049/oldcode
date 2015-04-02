package chu.server.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyPswServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = new String(req.getParameter("username").getBytes("UTF-8"),"UTF-8");
		String password = new String(req.getParameter("password").getBytes("UTF-8"),"UTF-8");
		System.out.println("密码:"+password);
		//判断是否修改成功
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		DB db=  new DB();
		try {
			if(db.modifyPsw(name, password)==true){
				System.out.println("修改成功");
				String data = "OK";
				out.print(data);
			}else{
				System.out.println("修改失败");
				String data = "Failed";
				out.print(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
}
