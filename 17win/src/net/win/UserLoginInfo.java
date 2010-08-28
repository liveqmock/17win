package net.win;

import java.util.Date;

public class UserLoginInfo {
	//ID
	private Long id;
	// 用户名
	private String username;
	// 域名
	private String domainName;
	// 最后一次登陆时间
	private Date lastLoginTime;
	// 手机
	private String telephone;
	// 电子邮箱
	private String email;
	// 性别 1,男,2女
	private String sex;
	// //公司电话
	private String companyTEL;
	// 公司电话，分机
	private String companyTELExt;
	// 管理员
	private Boolean admin = false;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCompanyTEL() {
		return companyTEL;
	}
	public void setCompanyTEL(String companyTEL) {
		this.companyTEL = companyTEL;
	}
	public String getCompanyTELExt() {
		return companyTELExt;
	}
	public void setCompanyTELExt(String companyTELExt) {
		this.companyTELExt = companyTELExt;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
