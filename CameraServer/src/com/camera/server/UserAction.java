package com.camera.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.camera.db.SqlUtil;

public class UserAction {

	private int id;
	private String code;
	private String type;
	private String mode;
	private String jqmcode;
	private File imgfile;
	private String imgfileContentType;
	private String imgfileFileName;
	private List<Object> param=new ArrayList<Object>();
	private Map<String, Object> map=new HashMap<String, Object>();
	private List<Map<String, Object>> lmo=new ArrayList<Map<String, Object>>();
	public String GetUserInfo(){
		String sql="select * from users where code=?";
		param.add(code);
		map=SqlUtil.searchMap(sql, param.toArray());
		return "success";
	}
	public String upload(){
		try {
			String path = ServletActionContext.getServletContext().getRealPath(
			"img");
			String filename=System.currentTimeMillis()+"_"+imgfileFileName;
			FileUtils.copyFile(imgfile, new File(path,filename));
			map.put("msg", "上传成功");
			String sql="insert into record(code,imgname,types,mode,jqmcode) values (?,?,?,?,?)";
			param.add(code);
			param.add(filename);
			param.add(type);
			param.add(mode);
			param.add(jqmcode);
			SqlUtil.save(sql, param.toArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", "上传失败");
		}
		return "success";
	}
	public String hasRecord(){
		String sql="select * from record where day(ctime)=day(getdate()) and types=? and code=? and mode='feidafan'";
		param.add(type);
		param.add(code);
		int i=SqlUtil.searchCount(sql, param.toArray());
		System.out.println(i);
		if(i>0){
			map.put("msg", "已刷卡,不要重复刷卡");
		}else{
			map.put("msg", "true");
		}
		return "success";
	}
	public String returnRecord(){
		String sql="select top 6 * from (select a.name,b.* from users a left join record b on a.code=b.code) b where mode='feidafan' and zt='0'";
		lmo=SqlUtil.search(sql, param.toArray());
		return "list";
	}
	public String biaoshi(){
		String sql="update record set zt=1,jqmcode=? where id=?";
		param.add(jqmcode);
		param.add(id);
		int i=SqlUtil.save(sql, param.toArray());
		param.clear();
		sql="select types,count(types) c from (select * from record where day(ctime)=day(getdate()) and zt=1 and jqmcode=?)a group by types";
		param.add(jqmcode);
		map=SqlUtil.searchMap(sql, param.toArray());
		if(i>0){
			map.put("msg", "打饭成功");
		}
		return "success";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public File getImgfile() {
		return imgfile;
	}
	public void setImgfile(File imgfile) {
		this.imgfile = imgfile;
	}
	public String getImgfileContentType() {
		return imgfileContentType;
	}
	public void setImgfileContentType(String imgfileContentType) {
		this.imgfileContentType = imgfileContentType;
	}
	public String getImgfileFileName() {
		return imgfileFileName;
	}
	public void setImgfileFileName(String imgfileFileName) {
		this.imgfileFileName = imgfileFileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getJqmcode() {
		return jqmcode;
	}
	public void setJqmcode(String jqmcode) {
		this.jqmcode = jqmcode;
	}
	public List<Map<String, Object>> getLmo() {
		return lmo;
	}
	public void setLmo(List<Map<String, Object>> lmo) {
		this.lmo = lmo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
