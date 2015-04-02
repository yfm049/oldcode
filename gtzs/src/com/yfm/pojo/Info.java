package com.yfm.pojo;

import java.io.Serializable;

public class Info implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 品种
	private String cgoodsid;
	// 大类
	private String cgoodstype;
	// 规格
	private String csize;
	// 材质
	private String ctextture;

	public String getCgoodsid() {
		return cgoodsid;
	}

	public void setCgoodsid(String cgoodsid) {
		this.cgoodsid = cgoodsid;
	}

	public String getCgoodstype() {
		return cgoodstype;
	}

	public void setCgoodstype(String cgoodstype) {
		this.cgoodstype = cgoodstype;
	}

	public String getCsize() {
		return csize;
	}

	public void setCsize(String csize) {
		this.csize = csize;
	}

	public String getCtextture() {
		return ctextture;
	}

	public void setCtextture(String ctextture) {
		this.ctextture = ctextture;
	}

	public String getCplace() {
		return cplace;
	}

	public void setCplace(String cplace) {
		this.cplace = cplace;
	}

	public String getCprice() {
		return cprice;
	}

	public void setCprice(String cprice) {
		this.cprice = cprice;
	}

	public String getDweight() {
		return dweight;
	}

	public void setDweight(String dweight) {
		this.dweight = dweight;
	}

	public String getCdname() {
		return cdname;
	}

	public void setCdname(String cdname) {
		this.cdname = cdname;
	}

	public String getDupdatedate() {
		return dupdatedate;
	}

	public void setDupdatedate(String dupdatedate) {
		this.dupdatedate = dupdatedate;
	}

	public String getCcusfullname() {
		return ccusfullname;
	}

	public void setCcusfullname(String ccusfullname) {
		this.ccusfullname = ccusfullname;
	}

	public String getCcusperson() {
		return ccusperson;
	}

	public void setCcusperson(String ccusperson) {
		this.ccusperson = ccusperson;
	}

	public String getCcusphone() {
		return ccusphone;
	}

	public void setCcusphone(String ccusphone) {
		this.ccusphone = ccusphone;
	}

	public String getCcusmobile() {
		return ccusmobile;
	}

	public void setCcusmobile(String ccusmobile) {
		this.ccusmobile = ccusmobile;
	}

	public String getCcusfax() {
		return ccusfax;
	}

	public void setCcusfax(String ccusfax) {
		this.ccusfax = ccusfax;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCcusaddress() {
		return ccusaddress;
	}

	public void setCcusaddress(String ccusaddress) {
		this.ccusaddress = ccusaddress;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 产地
	private String cplace;
	// 价格
	private String cprice;
	// 吨位
	private String dweight;
	// 仓库
	private String cdname;
	// 发布时间
	private String dupdatedate;
	// 公司名称
	private String ccusfullname;
	// 联系人
	private String ccusperson;
	// 联系电话
	private String ccusphone;
	// 手机号码
	private String ccusmobile;
	// 会员传真
	private String ccusfax;
	// qq号码
	private String qq;
	// email
	private String email;
	// 详细地址
	private String ccusaddress;
	// 备注
	private String beizhu;
}
