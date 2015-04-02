package com.user.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.user.db.SqlUtil;
import com.user.pojo.User;
import com.user.utils.Page;

public class AdminAction extends ActionSupport {

	private String name;
	private String pass;
	private List<Object> param=new ArrayList<Object>();
	private Map<String, Object> map=new HashMap<String, Object>();

	public String login(){
		String sql="select * from admin where name=? and pass=?";
		param.add(name);
		param.add(pass);
		int i=SqlUtil.searchCount(sql, param.toArray());
		if(i>0){
			map.put("msg", "登录成功");
			ServletActionContext.getRequest().getSession().setAttribute("name", name);
		}else{
			map.put("msg", "登录失败,用户名或密码错误");
		}
		return SUCCESS;
	}
	public String loginout(){
		ServletActionContext.getRequest().getSession().removeAttribute("name");
		return "out";
	}
	public String update(){
		Object n=ServletActionContext.getRequest().getSession().getAttribute("name");
		String name="";
		if(n!=null){
			name=n.toString();
		}
		String sql="update admin set pass=? where name=?";
		param.add(pass);
		param.add(name);
		int row=SqlUtil.update(sql, param.toArray());
		if(row>0){
			map.put("msg", "修改成功");
		}else{
			map.put("msg", "修改失败");
		}
		return SUCCESS;
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
}
