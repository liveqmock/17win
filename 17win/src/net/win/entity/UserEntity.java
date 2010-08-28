package net.win.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/**
 * 用户
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_USER")
public class UserEntity extends BaseEntity {
	/**
	 * 基本信息
	 */
	// 用户名
	@Column(name = "USERNAME_", length = 20, unique = true, nullable = false)
	private String username;
	// 登陆密码
	@Column(name = "LOGIN_PASSWORD_", columnDefinition = "CHAR(24)", nullable = false)
	private String loginPassword;
	// 操作码
	@Column(name = "OPERATION_CODE_", columnDefinition = "CHAR(24)", nullable = false)
	private String opertationCode;
	// QQ
	@Column(name = "QQ_", length = 11, nullable = false)
	private String qq;
	// 发布点
	@Column(name = "RELEASE_DOT_", nullable = false)
	private Float releaseDot;
	// 电子邮箱
	@Column(name = "EMAIL_", length = 24, nullable = false)
	private String email;
	// 手机
	@Column(name = "TELPHONE_", unique = true, columnDefinition = "CHAR(11)", nullable = false)
	private String telephone;
	// 性别
	@Column(name = "SEX_", columnDefinition = "CHAR(1)", nullable = false)
	private String sex;
	// 可以兑换发布点的积分
	@Column(name = "CONVERT_SCORE_", nullable = false)
	private Integer convertScore;
	// 升级用的积分，不能兑换发布点
	@Column(name = "UPGRADE_SCORE_", nullable = false)
	private Integer upgradeScore;
	// 注册时间
	@Column(name = "REGISTERTIME_", nullable = false)
	private Date registerTime;
	// 最后一次登陆时间
	@Column(name = "LASTLOGINTIME_")
	private Date lastLoginTime;
	// 当前级别(一心，还是一钻)
	@Column(name = "LEVEL_", nullable = false)
	private String level;
	// 是否激活
	@Column(name = "ACTIVATE_", nullable = false)
	private Boolean activate;
	/**
	 * 可选选项
	 */
	// 昵称
	@Column(name = "NICKNAME_", length = 20)
	private String nickname;
	// 店铺地址
	@Column(name = "SHOP_", length = 50)
	private String shop;
	// 旺旺
	@Column(name = "WW_", length = 24)
	private String ww;
	// 密码问题
	@Column(name = "PW_QUESTION_", length = 20)
	private String pwQuestion;
	// 密码问题
	@Column(name = "PW_ANSWER_", length = 20)
	private String pwAnswer;
	// // 省
	// // 市
	// // 县
	// // 地址（收货地址）
	// private String address;
	/**
	 * 关联关系
	 * 
	 * @return
	 */
	// 角色
	private RoleEntity role = new RoleEntity();
	// 介绍人
	private UserEntity referee;
	// 推广
	private Set<UserEntity> promoteUsers = new HashSet<UserEntity>(0);
	// 信誉任务模板
	private Set<CreditTaskTemplateEntity> creditTaskTemplates = new HashSet<CreditTaskTemplateEntity>(
			0);
	// 流量任务模板
	private Set<FlowTaskTemplateEntity> flowTaskTemplates = new HashSet<FlowTaskTemplateEntity>(
			0);
	// 收藏任务模板
	private Set<CollectTaskTemplateEntity> collectTaskTemplates = new HashSet<CollectTaskTemplateEntity>(
			0);
	// 真实地址模板
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_USER_RECEIVEREAL_TP", joinColumns = {@JoinColumn(name = "USER_ID_", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "RECEIVEREALINFO_ID_", nullable = false)})
	private Set<ReceiveRealInfoEntity> receiveRealInfos = new HashSet<ReceiveRealInfoEntity>(
			0);
	// 虚拟地址模板
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_USER_RECEIVEVIRTUAL_TP", joinColumns = {@JoinColumn(name = "USER_ID_", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "RECEIVEVIRTUALINFO_ID_", nullable = false)})
	private Set<ReceiveVirtualInfoEntity> receiveVirtualInfos = new HashSet<ReceiveVirtualInfoEntity>(
			0);

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getOpertationCode() {
		return opertationCode;
	}
	public void setOpertationCode(String opertationCode) {
		this.opertationCode = opertationCode;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getWw() {
		return ww;
	}
	public void setWw(String ww) {
		this.ww = ww;
	}
	public String getPwQuestion() {
		return pwQuestion;
	}
	public void setPwQuestion(String pwQuestion) {
		this.pwQuestion = pwQuestion;
	}
	public String getPwAnswer() {
		return pwAnswer;
	}
	public void setPwAnswer(String pwAnswer) {
		this.pwAnswer = pwAnswer;
	}
	public Float getReleaseDot() {
		return releaseDot;
	}
	public void setReleaseDot(Float releaseDot) {
		this.releaseDot = releaseDot;
	}
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	public Set<UserEntity> getPromoteUsers() {
		return promoteUsers;
	}
	public void setPromoteUsers(Set<UserEntity> promoteUsers) {
		this.promoteUsers = promoteUsers;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Boolean getActivate() {
		return activate;
	}
	public void setActivate(Boolean activate) {
		this.activate = activate;
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
	public Set<ReceiveRealInfoEntity> getReceiveRealInfos() {
		return receiveRealInfos;
	}
	public void setReceiveRealInfos(Set<ReceiveRealInfoEntity> receiveRealInfos) {
		this.receiveRealInfos = receiveRealInfos;
	}
	public Set<CreditTaskTemplateEntity> getCreditTaskTemplates() {
		return creditTaskTemplates;
	}
	public void setCreditTaskTemplates(
			Set<CreditTaskTemplateEntity> creditTaskTemplates) {
		this.creditTaskTemplates = creditTaskTemplates;
	}
	public Set<FlowTaskTemplateEntity> getFlowTaskTemplates() {
		return flowTaskTemplates;
	}
	public void setFlowTaskTemplates(
			Set<FlowTaskTemplateEntity> flowTaskTemplates) {
		this.flowTaskTemplates = flowTaskTemplates;
	}
	public Set<CollectTaskTemplateEntity> getCollectTaskTemplates() {
		return collectTaskTemplates;
	}
	public void setCollectTaskTemplates(
			Set<CollectTaskTemplateEntity> collectTaskTemplates) {
		this.collectTaskTemplates = collectTaskTemplates;
	}
	public UserEntity getReferee() {
		return referee;
	}
	public void setReferee(UserEntity referee) {
		this.referee = referee;
	}
	public Set<ReceiveVirtualInfoEntity> getReceiveVirtualInfos() {
		return receiveVirtualInfos;
	}
	public void setReceiveVirtualInfos(
			Set<ReceiveVirtualInfoEntity> receiveVirtualInfos) {
		this.receiveVirtualInfos = receiveVirtualInfos;
	}
}
