package com.user.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.opensymphony.xwork2.ActionSupport;
import com.user.db.SqlUtil;
import com.user.pojo.User;
import com.user.utils.Page;

public class ManageAction extends ActionSupport {

	private User user;
	private List<Object> param=new ArrayList<Object>();
	private Map<String, Object> map=new HashMap<String, Object>();
	private List<Map<String, Object>> lmo=new ArrayList<Map<String,Object>>();
	private static Random random = new Random();
	private Page page=new Page();
	public String list(){
		String sql="select *,case when state=1 then '使用中' else '' end as zt from manager";
		page.setTsize(SqlUtil.searchCount(sql, param.toArray()));
		lmo=SqlUtil.search(sql, param.toArray(), page);
		return "list";
	}
	public String update(){
		String sql="select * from manager where id=?";
		param.add(user.getId());
		map=SqlUtil.searchMap(sql, param.toArray());
		return "update";
	}
	public String save(){
		String sql="update manager set username=?,password=?,realname=?,phonenum=?,email=?,imei=? where id=?";
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
		String sql="delete from manager where id=?";
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
		String sql="update manager set state='0'";
		SqlUtil.update(sql, null);
		sql="update  manager set state=? where id=?";
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
		String sql="select * from manager where username=? and password=?";
		param.add(user.getUsername());
		param.add(user.getPassword());
		map=SqlUtil.searchMap(sql, param.toArray());
		return SUCCESS;
	}
	public String adduser(){
		String sql="select * from manager where username=?";
		param.add(user.getUsername());
		int c=SqlUtil.searchCount(sql, param.toArray());
		if(c>0){
			map.put("mag", "用户名已存在");
		}else{
			sql="insert into manager(username,password,realname,phonenum,email,imei) values (?,?,?,?,?,?)";
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
	public String Apply(){
		String sql="select * from manager where imei=? and state=1";
		param.add(user.getImei());
		int c=SqlUtil.searchCount(sql, param.toArray());
		if(c>0){
			sql="select a.*,b.username,b.realname,b.phonenum from auth a right join user b on a.imei=b.imei where a.zt=1";
			map=SqlUtil.searchMap(sql, new Object[]{});
			System.out.println(map);
			if(map.get("id")!=null&&map.get("IMEI")!=null){
				sql="update auth set zt=0 where id=?";
				SqlUtil.save(sql, new Object[]{map.get("id")});
			}else{
				map.put("msg", "");
			}
		}
		
		return SUCCESS;
	}
	public String ToApply(){
		if("true".equals(user.getMsg())){
			//同意2
			String sql="update auth set code=?,zt=2 where id=?";
			SqlUtil.save(sql, new Object[]{random.nextInt(9999)+"",user.getId()});
		}else{
			//不同意3
			String sql="update auth set zt=? where id=?";
			SqlUtil.save(sql, new Object[]{"3",user.getId()});
		}
		map.put("msg","操作成功");
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
