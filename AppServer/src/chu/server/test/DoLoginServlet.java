package chu.server.test;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DoLoginServlet extends HttpServlet{
     private DB db;
    
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
		System.out.println("ÐÕÃû£º" + username);
		System.out.println("ÃÜÂë£º" + password);
		db = new DB();
		//ÅÐ¶ÏµÇÂ½
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(db.chechUser(username, password)==true){
			System.out.println("µÇÂ½³É¹¦!");
			String successData = "OK";
			String getTicketInfo = "123";
			String failedData = "null";
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			try{
				obj.put("successData", successData);
				obj.put("getTicketInfo", getTicketInfo);
				obj.put("failedData", failedData);
			}catch(Exception e){
				e.printStackTrace();
			}
			array.add(obj);
			System.out.println(array.toString());
            out.print(array.toString());
           
		}else{
			System.out.println("µÇÂ¼Ê§°Ü!");
			String failedData = "Error";
			String successData = "null";
			String getTicketInfo = "null";
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			try{
				obj.put("failedData", failedData);
				obj.put("successData", successData);
				obj.put("getTicketInfo", getTicketInfo);
			}catch(Exception e){
				e.printStackTrace();
			}
			array.add(obj);
			System.out.println(array.toString());
            out.print(array.toString());;
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);	}
	
}
