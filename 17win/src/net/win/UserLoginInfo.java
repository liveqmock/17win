package net.win;

import java.util.Date;

public class UserLoginInfo {
	/**
	 * 基本信息
	 */
	private Long id;
	// 用户名
	private String username;
	// QQ
	private String qq;
	// 发布点
	private Float releaseDot;
	// 手机
	private String telephone;
	// 可以兑换发布点的积分
	private Integer convertScore;
	// 升级用的积分，不能兑换发布点
	private Integer upgradeScore;
	// 最后一次登陆时间
	private Date lastLoginTime;
	// 当前级别(一心，还是一钻)
	private String level;
	// 操作码激活状态
	private Boolean operationCodeStatus = false;

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

	public Float getReleaseDot() {
		return releaseDot;
	}

	public void setReleaseDot(Float releaseDot) {
		this.releaseDot = releaseDot;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getConvertScore() {
		return convertScore;
	}

	public void setConvertScore(Integer convertScore) {
		this.convertScore = convertScore;
	}

	public Integer getUpgradeScore() {
		return upgradeScore;
	}

	public void setUpgradeScore(Integer upgradeScore) {
		this.upgradeScore = upgradeScore;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Boolean getOperationCodeStatus() {
		return operationCodeStatus;
	}

	public void setOperationCodeStatus(Boolean operationCodeStatus) {
		this.operationCodeStatus = operationCodeStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
