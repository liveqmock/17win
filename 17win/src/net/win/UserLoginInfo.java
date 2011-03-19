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
	// 手机
	private String telephone;
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

	private Integer upgradeScore;
	// 状态
	private String status;
	// 钱
	private Double money;

	// vip结束日期
	private Date vipEndDate;
	// VIP 成长值
	private Integer vipGrowValue;

	// vip类型
	private String vipType;

	// VIP状态 true 表示没失效，false表示失效
	private Boolean vipEnable;

	// 是否发送手机验证
	private Boolean sendMsgTOValiate = false;

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public Date getVipEndDate() {
		return vipEndDate;
	}

	public void setVipEndDate(Date vipEndDate) {
		this.vipEndDate = vipEndDate;
	}

	public Integer getVipGrowValue() {
		return vipGrowValue;
	}

	public void setVipGrowValue(Integer vipGrowValue) {
		this.vipGrowValue = vipGrowValue;
	}

	public Integer getUpgradeScore() {
		return upgradeScore;
	}

	public void setUpgradeScore(Integer upgradeScore) {
		this.upgradeScore = upgradeScore;
	}

	public Boolean getSendMsgTOValiate() {
		return sendMsgTOValiate;
	}

	public void setSendMsgTOValiate(Boolean sendMsgTOValiate) {
		this.sendMsgTOValiate = sendMsgTOValiate;
	}
}
