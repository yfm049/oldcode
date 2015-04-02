package chu.server.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserTicketServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = new String(req.getParameter("name").getBytes("UTF-8"),"UTF-8");
		String from = new String(req.getParameter("from").getBytes("UTF-8"),"UTF-8");
		String to = new String(req.getParameter("to").getBytes("UTF-8"),"UTF-8");
		String time = new String(req.getParameter("time").getBytes("UTF-8"),"UTF-8");
		String price = new String(req.getParameter("price").getBytes("UTF-8"),"UTF-8");
		String airport = new String(req.getParameter("airport").getBytes("UTF-8"),"UTF-8");
		String level = new String(req.getParameter("level").getBytes("UTF-8"),"UTF-8");
		
		System.out.println("姓名:" + name);
		System.out.println("机场:" + airport);
		DB db = new DB();
        resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		boolean isCheckedTicket = db.checkUserTicket(name,from,to,time,price,airport,level);
		
		if(isCheckedTicket==true){
			System.out.println("已经存在");
			String existData = "Exist";
			out.print(existData);
		}
		else if(isCheckedTicket==false){
			boolean insertUserTicketInfo = db.insertUserTicketInfo(name, from, to, time, price, airport, level);
			if(insertUserTicketInfo==true){
			System.out.println("插入成功");
			String successData = "OK";
			out.println(successData);
			}else{
			System.out.println("插入失败");
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
