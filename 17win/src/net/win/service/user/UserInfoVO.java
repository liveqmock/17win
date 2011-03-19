package net.win.service.user;

import java.util.Date;

import net.win.BaseVO;

public class UserInfoVO extends BaseVO {

	public UserInfoVO(String username, String qq, Integer releaseTaskCount,
			Integer receiveTaskCount, Date registerTime) {
		this.username = username;
		this.qq = qq;
		this.releaseTaskCount = releaseTaskCount;
		this.receiveTaskCount = receiveTaskCount;
		this.registerTime = registerTime;
	}

	public UserInfoVO(String username, Integer spreadCount, Integer spreadScore) {
		this.username = username;
		this.spreadCount = spreadCount;
		this.spreadScore = spreadScore;
	}

	// 用户名
	private String username;
	// QQ
	private String qq;
	// 电子邮箱
	private String email;
	// 手机
	private String telephone;
	// 升级用的积分， 
	private Integer upgradeScore = 0;
	// 注册时间
	private Date registerTime = new Date();
	// 发布任务数
	private Integer releaseTaskCount = 0;
	// 接手任务数
	private Integer receiveTaskCount = 0;
	// 推广积分
	private Integer spreadScore = 0;
	// 推广人数
	private Integer spreadCount = 0;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

 

	 

	public Integer getUpgradeScore() {
		return upgradeScore;
	}

	public void setUpgradeScore(Integer upgradeScore) {
		this.upgradeScore = upgradeScore;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getReleaseTaskCount() {
		return releaseTaskCount;
	}

	public void setReleaseTaskCount(Integer releaseTaskCount) {
		this.releaseTaskCount = releaseTaskCount;
	}

	public Integer getReceiveTaskCount() {
		return receiveTaskCount;
	}

	public void setReceiveTaskCount(Integer receiveTaskCount) {
		this.receiveTaskCount = receiveTaskCount;
	}

	public Integer getSpreadScore() {
		return spreadScore;
	}

	public void setSpreadScore(Integer spreadScore) {
		this.spreadScore = spreadScore;
	}

	public Integer getSpreadCount() {
		return spreadCount;
	}

	public void setSpreadCount(Integer spreadCount) {
		this.spreadCount = spreadCount;
	}
}
