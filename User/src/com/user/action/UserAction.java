package com.user.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.user.db.SqlUtil;
import com.user.pojo.User;
import com.user.utils.Page;

public class UserAction extends ActionSupport {

	private User user;
	private List<Object> param=new ArrayList<Object>();
	private Map<String, Object> map=new HashMap<String, Object>();
	private List<Map<String, Object>> lmo=new ArrayList<Map<String,Object>>();
	private Page page=new Page();
	public String list(){
		String sql="select *,case when state=1 then '授权' else '未授权' end as zt from user";
		page.setTsize(SqlUtil.searchCount(sql, param.toArray()));
		lmo=SqlUtil.search(sql, param.toArray(), page);
		return "list";
	}
	public String update(){
		String sql="select * from user where id=?";
		param.add(user.getId());
		map=SqlUtil.searchMap(sql, param.toArray());
		return "update";
	}
	public String save(){
		String sql="update user set username=?,password=?,realname=?,phonenum=?,email=?,imei=? where id=?";
		param.add(user.getUsername());
		param.add(user.getPassword());
		param.add(user.getRealname());
		param.add(user.getPhonenum());
		param.add(user.getEmail());
		param.add(user.getImei());
		param.add(user.getId());
		int i=SqlUtil.update(sql, param.toArray());
		if(i>0){
			map.put("msg", "修改成功");
		}else{
			map.put("msg", "修改失败");
		}
		return "save";
	}
	public String delete(){
		String sql="delete from user where id=?";
		param.add(user.getId());
		int i=SqlUtil.update(sql, param.toArray());
		if(i>0){
			map.put("msg", "删除成功");
		}else{
			map.put("msg", "删除失败");
		}
		return SUCCESS;
	}
	public String shouquan(){
		String sql="update  user set state=? where id=?";
		param.add(user.getState());
		param.add(user.getId());
		int i=SqlUtil.update(sql, param.toArray());
		if(i>0){
			map.put("msg", "操作成功");
		}else{
			map.put("msg", "操作失败");
		}
		return SUCCESS;
	}
	public String login(){
		String sql="select * from user where username=? and password=? and imei=?";
		param.add(user.getUsername());
		param.add(user.getPassword());
		param.add(user.getImei());
		map=SqlUtil.searchMap(sql, param.toArray());
		if(map.isEmpty()){
			map.put("msg", "登录失败");
		}else{
			map.clear();
			map.put("msg", "登录成功");
		}
		return SUCCESS;
	}
	public String yanzheng(){
		String sql="select * from user where imei=?";
		param.add(user.getImei());
		Map<String, Object> mo=SqlUtil.searchMap(sql, param.toArray());
		if(mo.isEmpty()){
			map.put("msg", "请先注册");
		}else if("0".equals(mo.get("state"))){
			map.put("msg", "请等待授权");
		}else{
			map.put("msg", mo.get("username"));
		}
		return SUCCESS;
	}
	public String adduser(){
		String sql="select * from user where imei=?";
		param.clear();
		param.add(user.getImei());
		int m=SqlUtil.searchCount(sql, param.toArray());
		sql="select * from user where username=?";
		param.clear();
		param.add(user.getUsername());
		int c=SqlUtil.searchCount(sql, param.toArray());
		if(m>0){
			map.put("msg", "此手机已经注册,请登录");
		}else if(c>0){
			map.put("mag", "用户名已存在");
		}else{
			sql="insert into user(username,password,realname,phonenum,email,imei) values (?,?,?,?,?,?)";
			param.clear();
			param.add(user.getUsername());
			param.add(user.getPassword());
			param.add(user.getRealname());
			param.add(user.getPhonenum());
			param.add(user.getEmail());
			param.add(user.getImei());
			c=SqlUtil.save(sql, param.toArray());
			if(c>0){
				map.put("msg", "注册成功");
			}else{
				map.put("msg", "注册失败");
			}
		}
		return SUCCESS;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<Map<String, Object>> getLmo() {
		return lmo;
	}
	public void setLmo(List<Map<String, Object>> lmo) {
		this.lmo = lmo;
	}
}
