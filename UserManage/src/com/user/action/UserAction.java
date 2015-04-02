package com.user.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		String sql="update user set time=?,password=?,realname=?,phonenum=?,email=?,imei=? where id=?";
		param.add(user.getTime());
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
	//登录
	public String login(){
		String sql = "select * from user where username=? and imei=? and password=?";
		List<Map<String, Object>> lmo = SqlUtil.search(sql, new Object[] {user.getUsername(), user.getImei() ,user.getPassword()});
		if (lmo.size() > 0) {
			Object time=lmo.get(0).get("time");
			sql = "select * from auth where name=? and imei=? and code=? and zt=4";
			lmo = SqlUtil.search(sql, new Object[] { user.getUsername(), user.getImei(), user.getCode() });
			if (lmo.size() > 0) {
				sql="delete from auth where name=? and imei=?";
				int i=SqlUtil.save(sql, new Object[]{user.getUsername(), user.getImei()});
				map.put("msg", "验证通过");
				map.put("time", time);
				sql="insert into log(username,logintime) values (?,now())";
				SqlUtil.save(sql, new Object[]{user.getUsername()});
			} else {
				map.put("msg", "验证失败");
			}
		} else {
			sql="select * from user where imei=? and state=1";
			lmo=SqlUtil.search(sql, new Object[]{user.getImei()});
			if(lmo.size()<=0){
				map.put("msg", "非授权手机，不能获取");
			}else{
				map.put("msg", "用户名或密码错误");
			}
		}
		return SUCCESS;
	}
	//获取授权码
	public String AddAuth(){
		String sql="select * from user where username=? and imei=? and password =? and state=1";
		List<Map<String, Object>> lmo=SqlUtil.search(sql, new Object[]{user.getUsername(), user.getImei() ,user.getPassword()});
		if(lmo.size()>0){
			sql="delete from auth where name=? and imei=?";
			int i=SqlUtil.save(sql, new Object[]{user.getUsername(), user.getImei() });
			sql="insert into auth(name,imei,time) values(?,?,now())";
			i=SqlUtil.save(sql, new Object[]{user.getUsername(), user.getImei() });
			if(i>0){
				map.put("msg", "请等待授权！");
			}else{
				map.put("msg", "获取失败，请重新获取");
			}
		}else{
			sql="select * from user where imei=? and state=1";
			lmo=SqlUtil.search(sql, new Object[]{user.getImei()});
			if(lmo.size()<=0){
				map.put("msg", "非授权手机，不能获取");
			}else{
				map.put("msg", "用户名或密码错误");
			}
		}
		return SUCCESS;
	}
	//推送授权码
	public String SendAuth(){
		String sql="select * from auth where imei=?";
		Map<String, Object> mo=SqlUtil.searchMap(sql, new Object[]{user.getImei()});
		Object zt=mo.get("zt");
		if("2".equals(zt)){
			Object code=mo.get("code");
			if(code!=null&&!"".equals(code)){
				//推送为3
				sql="update auth set zt=4 where imei=?";
				SqlUtil.save(sql, new Object[]{user.getImei()});
				map.put("msg", "授权码为"+code+",请注意查收");
			}else{
				map.put("msg", "");
			}
		}else if("3".equals(zt)){
			sql="update auth set zt=4 where imei=?";
			SqlUtil.save(sql, new Object[]{user.getImei()});
			map.put("msg", "管理员不同意你的申请。");
		}
		return SUCCESS;
	}
	public String updatepass(){
		String sql="select * from user where username=?";
		param.add(user.getUsername());
		Map<String, Object> mo=SqlUtil.searchMap(sql, param.toArray());
		if(mo.isEmpty()){
			map.put("msg", "用户名不存在");
		}else{
			Object imei=mo.get("imei");
			if(!imei.equals(user.getImei())){
				map.put("msg", "非授权手机，不能修改");
			}else{
				Object pass=mo.get("password");
				if(user.getPassword().equals(pass)){
					sql="update user set password=? where username=? and imei=?";
					param.clear();
					param.add(user.getPassword());
					param.add(user.getUsername());
					param.add(user.getImei());
					int i=SqlUtil.update(sql, param.toArray());
					if(i>0){
						map.put("msg", "修改成功");
					}else{
						map.put("msg", "修改失败");
					}
				}else{
					map.put("msg", "原密码密码错误");
				}
			}
		}
		return SUCCESS;
	}
	public String adduser(){
		String sql="select * from user where username=?";
		param.add(user.getUsername());
		int c=SqlUtil.searchCount(sql, param.toArray());
		if(c>0){
			map.put("mag", "用户名已存在");
		}else{
			sql="insert into user(username,password,realname,phonenum,email,imei,time) values (?,?,?,?,?,?,?)";
			param.clear();
			param.add(user.getUsername());
			param.add(user.getPassword());
			param.add(user.getRealname());
			param.add(user.getPhonenum());
			param.add(user.getEmail());
			param.add(user.getImei());
			param.add(user.getTime());
			c=SqlUtil.save(sql, param.toArray());
			if(c>0){
				map.put("msg", "添加成功");
			}else{
				map.put("msg", "添加失败");
			}
		}
		return SUCCESS;
	}
	public String loginlog(){
		String sql="select b.*,a.logintime from log a left join user b on a.username=b.username";
		page.setTsize(SqlUtil.searchCount(sql, null));
		lmo=SqlUtil.search(sql, null, page);
		return "log";
		
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
