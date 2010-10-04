package net.win;

import java.util.Date;

import javax.persistence.Column;

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
	// 最后一次登陆时间
	private Date lastLoginTime;
	// 注册时间
	private Date registerTime;
	// 当前级别(一心，还是一钻)
	private Integer level;
	// 级别图片(一心，还是一钻)
	private String levelImg;
	// 操作码激活状态 true 当前会话激活， false当前会话没激活
	private Boolean operationCodeStatus = false;
	// 状态
	private String status;
	// 钱
	private Double money;
	// vip类型
	private String vipType;

	// VIP状态 true 表示没失效，false表示失效
	private Boolean vipEnable;

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

	public String getLevelImg() {
		return levelImg;
	}

	public void setLevelImg(String levelImg) {
		this.levelImg = levelImg;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getVipType() {
		return vipType;
	}

	public void setVipType(String vipType) {
		this.vipType = vipType;
	}

	public Boolean getVipEnable() {
		return vipEnable;
	}

	public void setVipEnable(Boolean vipEnable) {
		this.vipEnable = vipEnable;
	}
}
