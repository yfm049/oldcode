package chu.server.test;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserTicketServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = new String(req.getParameter("username").getBytes("UTF-8"),"UTF-8");
		String from = new String(req.getParameter("from").getBytes("UTF-8"),"UTF-8");
		String to = new String(req.getParameter("to").getBytes("UTF-8"),"UTF-8");
		
		System.out.println("from="+from);
		System.out.println("to="+to);
		DB db = new DB();
        resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		if(db.deleteUserTicket(username, from, to)==true){
			String data = "OK";
			out.print(data);
		}else{
			String data = "Failed";
			out.print(data);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
}
