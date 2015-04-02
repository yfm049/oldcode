package com.zjzs.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.PortableInterceptor.SUCCESSFUL;

import com.opensymphony.xwork2.ActionSupport;
import com.zjzs.db.SqlUtil;
import com.zjzs.utils.Page;

public class OutAction extends ActionSupport {

	private Page page = new Page();
	private List<Object> param = new ArrayList<Object>();
	private Map<String, Object> map;
	private List<Map<String, Object>> lmo;
	private String json;
	private int id;
	private String username;
	private String danhao;
	private String phonenum;

	public String outlist() {
		page.setIsasc(false);
		String sql = "select * from out_list where 1=1";
		if(username!=null&&!"".equals(username)){
			sql+=" and username like ?";
			param.add("%"+username+"%");
		}
		if(danhao!=null&&!"".equals(danhao)){
			sql+=" and danhao like ?";
			param.add("%"+danhao+"%");
		}
		if(phonenum!=null&&!"".equals(phonenum)){
			sql+=" and phonenum like ?";
			param.add("%"+phonenum+"%");
		}
		page.setTsize(SqlUtil.searchCount(sql, param.toArray()));
		lmo = SqlUtil.search(sql, param.toArray(), page);
		return "list";
	}

	public String save() {
		map = new HashMap<String, Object>();
		map.put("url", "manager/out!outlist.action");
		try {
			System.out.println(json);
			JSONObject jo = new JSONObject(json);
			String username = jo.getString("username");
			String phonenum = jo.getString("phonenum");
			String date = jo.getString("date");
			String price = jo.getString("total");
			String dxprice = jo.getString("dxtotal");
			long danhao = System.currentTimeMillis();
			JSONArray ja = jo.getJSONArray("data");
			String sql = "insert into out_list(username,danhao,phonenum,date,price,dxprice) values (?,?,?,?,?,?)";
			param.add(username);
			param.add(danhao);
			param.add(phonenum);
			param.add(date);
			param.add(price);
			param.add(dxprice);
			int id = SqlUtil.save(sql, param.toArray());
			sql = "insert into out_data(oid,name,pinpai,guige,shuliang,danjia,danwei,mishu,money) values(?,?,?,?,?,?,?,?,?)";
			for (int i = 0; i < ja.length(); i++) {
				param.clear();
				JSONObject item = ja.getJSONObject(i);
				System.out.println(item.toString());
				String name = item.getString("username");
				String pinpai = item.getString("pinpai");
				String guige = item.getString("guige");
				String shuliang = item.getString("shuliang");
				String danjia = item.getString("danjia");

				String danwei = item.getString("danwei");
				String mishu = item.getString("mishu");
				String prices = item.getString("price");
				param.add(id);
				param.add(name);
				param.add(pinpai);
				param.add(guige);
				param.add(shuliang);
				param.add(danjia);
				param.add(danwei);
				param.add(mishu);
				param.add(prices);
				SqlUtil.save(sql, param.toArray());
			}
			map.put("msg", "保存成功");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", "保存失败");

		}
		return SUCCESS;
	}

	public String detail() {
		param.add(id);
		String sql = "select * from out_list where id=?";
		map = SqlUtil.searchMap(sql, param.toArray());
		sql = "select * from out_data where oid=?";
		lmo = SqlUtil.search(sql, param.toArray());
		return "detail";
	}

	public String print() {
		param.add(id);
		String sql = "select * from out_list where id=?";
		map = SqlUtil.searchMap(sql, param.toArray());
		sql = "select * from out_data where oid=?";
		lmo = SqlUtil.search(sql, param.toArray());
		return "print";
	}

	public String update() {
		param.add(id);
		String sql = "select * from out_list where id=?";
		map = SqlUtil.searchMap(sql, param.toArray());
		sql = "select * from out_data where oid=?";
		lmo = SqlUtil.search(sql, param.toArray());
		return "update";
	}

	public String saveupdate() {
		map = new HashMap<String, Object>();
		map.put("url", "manager/out!outlist.action");
		try {
			System.out.println(json);
			JSONObject jo = new JSONObject(json);
			String id = jo.getString("id");
			String username = jo.getString("username");
			String phonenum = jo.getString("phonenum");
			String date = jo.getString("date");
			String price = jo.getString("total");
			String dxprice = jo.getString("dxtotal");
			JSONArray ja = jo.getJSONArray("data");
			String sql = "update out_list set username=?,phonenum=?,date=?,price=?,dxprice=? where id=?";
			param.add(username);
			param.add(phonenum);
			param.add(date);
			param.add(price);
			param.add(dxprice);
			param.add(id);
			SqlUtil.save(sql, param.toArray());
			sql = "delete from out_data where oid=?";
			param.clear();
			param.add(id);
			SqlUtil.save(sql, param.toArray());
			sql = "insert into out_data(oid,name,pinpai,guige,shuliang,danjia,danwei,mishu,money) values(?,?,?,?,?,?,?,?,?)";
			for (int i = 0; i < ja.length(); i++) {
				param.clear();
				JSONObject item = ja.getJSONObject(i);
				System.out.println(item.toString());
				String name = item.getString("username");
				String pinpai = item.getString("pinpai");
				String guige = item.getString("guige");
				String shuliang = item.getString("shuliang");
				String danjia = item.getString("danjia");
				String danwei = item.getString("danwei");
				String mishu = item.getString("mishu");
				String prices = item.getString("price");
				param.add(id);
				param.add(name);
				param.add(pinpai);
				param.add(guige);
				param.add(shuliang);
				param.add(danjia);
				param.add(danwei);
				param.add(mishu);
				param.add(prices);
				SqlUtil.save(sql, param.toArray());
			}
			map.put("msg", "修改成功");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", "修改失败");
		}
		return SUCCESS;
	}

	public String delete() {
		map = new HashMap<String, Object>();
		try {
			param.add(id);
			String sql = "delete from out_data where oid=?";
			SqlUtil.save(sql, param.toArray());
			sql = "delete from out_list where id=?";
			SqlUtil.save(sql, param.toArray());
			map.put("msg", "删除成功");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", "删除失败");
		}
		return SUCCESS;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Map<String, Object>> getLmo() {
		return lmo;
	}

	public void setLmo(List<Map<String, Object>> lmo) {
		this.lmo = lmo;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDanhao() {
		return danhao;
	}

	public void setDanhao(String danhao) {
		this.danhao = danhao;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
}
