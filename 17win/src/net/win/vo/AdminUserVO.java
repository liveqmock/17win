package net.win.vo;

import java.util.Date;

import net.win.BaseVO;

public class AdminUserVO extends BaseVO {

	/**
	 * 接手数据
	 */

	// 用户名
	private String username;
	// 余额
	private Double startMoney;
	private Double endMoney;

	private Double money;

	private Date regeditDate;

	private String qq;
	private String ww;

	private Integer spreadScore;

	private String statusDesc;

	private Date lastLoginTime;
	private Integer releaseTaskCount;

	private Integer receiveTaskCount;
	// 注册日期
	private Date regeditStartDate;
	private Date regeditEndDate;
	// 电子邮箱
	private String email;
	// 电话
	private String telphone;
	// 推广积分
	private Integer startSpreadScore;
	private Integer endSpreadScore;
	// 发布任务数
	private Integer startReleaseTaskCount;
	private Integer endReleaseTaskCount;
	// 接手任务数
	private Integer startReceieveTaskCount;
	private Integer endReceieveTaskCount;
	private Date startOverDate;
	private Date endOverDate;
	// 最后一次登录时间
	private Date startLastLoginTime;
	private Date endLastLoginTime;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getStartMoney() {
		return startMoney;
	}

	public void setStartMoney(Double startMoney) {
		this.startMoney = startMoney;
	}

	public Double getEndMoney() {
		return endMoney;
	}

	public void setEndMoney(Double endMoney) {
		this.endMoney = endMoney;
	}

	public Date getRegeditStartDate() {
		return regeditStartDate;
	}

	public void setRegeditStartDate(Date regeditStartDate) {
		this.regeditStartDate = regeditStartDate;
	}

	public Date getRegeditEndDate() {
		return regeditEndDate;
	}

	public void setRegeditEndDate(Date regeditEndDate) {
		this.regeditEndDate = regeditEndDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Integer getStartSpreadScore() {
		return startSpreadScore;
	}

	public void setStartSpreadScore(Integer startSpreadScore) {
		this.startSpreadScore = startSpreadScore;
	}

	public Integer getEndSpreadScore() {
		return endSpreadScore;
	}

	public void setEndSpreadScore(Integer endSpreadScore) {
		this.endSpreadScore = endSpreadScore;
	}

	public Integer getStartReleaseTaskCount() {
		return startReleaseTaskCount;
	}

	public void setStartReleaseTaskCount(Integer startReleaseTaskCount) {
		this.startReleaseTaskCount = startReleaseTaskCount;
	}

	public Integer getEndReleaseTaskCount() {
		return endReleaseTaskCount;
	}

	public void setEndReleaseTaskCount(Integer endReleaseTaskCount) {
		this.endReleaseTaskCount = endReleaseTaskCount;
	}

	public Integer getStartReceieveTaskCount() {
		return startReceieveTaskCount;
	}

	public void setStartReceieveTaskCount(Integer startReceieveTaskCount) {
		this.startReceieveTaskCount = startReceieveTaskCount;
	}

	public Integer getEndReceieveTaskCount() {
		return endReceieveTaskCount;
	}

	public void setEndReceieveTaskCount(Integer endReceieveTaskCount) {
		this.endReceieveTaskCount = endReceieveTaskCount;
	}

	public Date getStartOverDate() {
		return startOverDate;
	}

	public void setStartOverDate(Date startOverDate) {
		this.startOverDate = startOverDate;
	}

	public Date getEndOverDate() {
		return endOverDate;
	}

	public void setEndOverDate(Date endOverDate) {
		this.endOverDate = endOverDate;
	}

	public Date getStartLastLoginTime() {
		return startLastLoginTime;
	}

	public void setStartLastLoginTime(Date startLastLoginTime) {
		this.startLastLoginTime = startLastLoginTime;
	}

	public Date getEndLastLoginTime() {
		return endLastLoginTime;
	}

	public void setEndLastLoginTime(Date endLastLoginTime) {
		this.endLastLoginTime = endLastLoginTime;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getRegeditDate() {
		return regeditDate;
	}

	public void setRegeditDate(Date regeditDate) {
		this.regeditDate = regeditDate;
	}

	public Integer getSpreadScore() {
		return spreadScore;
	}

	public void setSpreadScore(Integer spreadScore) {
		this.spreadScore = spreadScore;
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

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWw() {
		return ww;
	}

	public void setWw(String ww) {
		this.ww = ww;
	}

}
