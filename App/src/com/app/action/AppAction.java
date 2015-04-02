package com.app.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.db.SqlUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AppAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Object> param=new ArrayList<Object>();
	private Map<String, Object> map=new HashMap<String, Object>();
	private List<Map<String, Object>> lmo=new ArrayList<Map<String,Object>>();
	private int fid=-1;
	private int id;
	private String name;
	//分类列表获取
	public String fenleilist(){
		String sql="select * from fenlei order by id asc";
		lmo=SqlUtil.search(sql, null);
		return "listjson";
	}
	//更新下载数量
	public String updatecount(){
		String sql="update applist set downcount=downcount+1 where id=?";
		SqlUtil.update(sql, new Object[]{id});
		map.put("msg", "成功");
		return "mapjson";
	}
	//应用列表获取
	public String listapp(){
		System.out.println(name+"");
		String sql="select * from applist where 1=1";
		if(fid>0){
			sql+=" and fid=?";
			param.add(fid);
		}
		if(name!=null&&!"".equals(name)){
			sql+=" and name like ?";
			param.add("%"+name+"%");
		}
		sql+=" order by downcount desc";
		System.out.println(sql+"-"+param.toString());
		lmo=SqlUtil.search(sql, param.toArray());
		return "listjson";
	}
	//获取程序更新
	public String getupdate(){
		try {
			StringBuffer sb=new StringBuffer();
			if(name!=null&&!"".equals(name)){
				JSONArray ja=new JSONArray(name);
				for(int i=0;i<ja.length();i++){
					JSONObject jo=ja.getJSONObject(i);
					String pn=jo.getString("pkname");
					int vs=jo.getInt("vs");
					if(pn!=null&&!"".equals(pn)){
						sb.append("select * from applist where pkname='"+pn+"' and version>"+vs+" union ");
					}
				}
			}
			String sql=sb.substring(0, sb.lastIndexOf("union"));
			lmo=SqlUtil.search(sql,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "listjson";
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
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
