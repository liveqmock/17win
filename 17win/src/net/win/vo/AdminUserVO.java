package net.win.vo;

import java.util.Date;

import net.win.BaseVO;

public class AdminUserVO extends BaseVO {

	/**
	 * 接手数据
	 */

	// 用户名
	private String username;
	// 发布点
	private Double startReleaseDot;
	private Double endReleaseDot;
	// 余额
	private Double startMoney;
	private Double endMoney;
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
	// 是否VIP
	private String vipEnable;
	// VIP成长值
	private Integer startVipGrowValue;
	private Integer endVipGrowValue;
	// VIP结束时间
	private Date startOverDate;
	private Date endOverDate;
	// 最后一次登录时间
	private Date startLiatLogin;
	private Date endLiatLogin;

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

	public Double getStartReleaseDot() {
		return startReleaseDot;
	}

	public void setStartReleaseDot(Double startReleaseDot) {
		this.startReleaseDot = startReleaseDot;
	}

	public Double getEndReleaseDot() {
		return endReleaseDot;
	}

	public void setEndReleaseDot(Double endReleaseDot) {
		this.endReleaseDot = endReleaseDot;
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

	public String getVipEnable() {
		return vipEnable;
	}

	public void setVipEnable(String vipEnable) {
		this.vipEnable = vipEnable;
	}

	public Integer getStartVipGrowValue() {
		return startVipGrowValue;
	}

	public void setStartVipGrowValue(Integer startVipGrowValue) {
		this.startVipGrowValue = startVipGrowValue;
	}

	public Integer getEndVipGrowValue() {
		return endVipGrowValue;
	}

	public void setEndVipGrowValue(Integer endVipGrowValue) {
		this.endVipGrowValue = endVipGrowValue;
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

	public Date getStartLiatLogin() {
		return startLiatLogin;
	}

	public void setStartLiatLogin(Date startLiatLogin) {
		this.startLiatLogin = startLiatLogin;
	}

	public Date getEndLiatLogin() {
		return endLiatLogin;
	}

	public void setEndLiatLogin(Date endLiatLogin) {
		this.endLiatLogin = endLiatLogin;
	}

}
