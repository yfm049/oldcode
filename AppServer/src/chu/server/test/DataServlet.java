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

public class DataServlet extends HttpServlet {

	private List<PlaneInfo> mList;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String getFromCity = new String(req.getParameter("getFromCity").getBytes("UTF-8"),"UTF-8");
		String getToCity = new String(req.getParameter("getToCity").getBytes("UTF-8"),"UTF-8");
		//String getLevel = new String(req.getParameter("getLevel").getBytes("UTF-8"),"UTF-8");
		String getDate = new String(req.getParameter("getDate").getBytes("UTF-8"),"UTF-8");
		
		System.out.println("出发城市：" + getFromCity);
		System.out.println("到达城市：" + getToCity);
		System.out.println("时间：" + getDate);
		// 输出
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		DB db = new DB();
		PrintWriter out = resp.getWriter();
		// 得到List数据
		mList = db.getPlaneInfo(getFromCity,getToCity,getDate);

		JSONArray array = new JSONArray();
		for (PlaneInfo planeInfo : mList) {
			// 单个Json对象
			JSONObject obj = new JSONObject();
			try {
				obj.put("_from", planeInfo.get_from());
				obj.put("_to", planeInfo.get_to());
				obj.put("time", planeInfo.getTime());
				obj.put("price", planeInfo.getPrice());
				obj.put("airport", planeInfo.getAirport());
				obj.put("level", planeInfo.getLevel());
			} catch (Exception e) {
				e.printStackTrace();
			}
			array.add(obj);
		}

		out.print(array.toString());
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
