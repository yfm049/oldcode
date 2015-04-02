package chu.server.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import chu.server.bean.PlaneInfo;

public class GetUserTicketInfo extends HttpServlet{
     private List<PlaneInfo> list;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String getUserName = new String(req.getParameter("username").getBytes("UTF-8"),"UTF-8");
		System.out.println("用户名为:" + getUserName);
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		DB db = new DB();
		PrintWriter out = resp.getWriter();
		list = db.getUserTicketInfo(getUserName.toString());
		JSONArray array = new JSONArray();
		for (PlaneInfo planeInfo : list) {
			JSONObject obj = new JSONObject();
			try{
				obj.put("_from", planeInfo.get_from());
				obj.put("_to", planeInfo.get_to());
				obj.put("time", planeInfo.getTime());
				obj.put("price", planeInfo.getPrice());
				obj.put("airport", planeInfo.getAirport());
				obj.put("level", planeInfo.getLevel());
				obj.put("code", planeInfo.getCode());
			}catch(Exception e){
				e.printStackTrace();
			}
			array.add(obj);
		}
	    
	     out.println(array.toString());
	     out.flush();
	     out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
