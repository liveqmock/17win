package net.win.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 用户
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "Tb_User")
public class UserEntity extends BaseEntity {
	/**
	 * 基本信息
	 */
	// 用户名
	@Column(name = "USERNAME_", length = 12, nullable = false)
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
	// 电子邮箱
	@Column(name = "EMAIL_", length = 24, unique = true, nullable = false)
	private String email;
	// 手机
	@Column(name = "TELPHONE_", unique = true, columnDefinition = "CHAR(11)", nullable = false)
	private String telephone;
	// 升级用的积分，不能兑换发布点
	@Column(name = "UPGRADE_SCORE_", nullable = false)
	private Integer upgradeScore = 0;
	// 记录一千，主要是给推广用
	@Column(name = "RECORD_1000_SCORE_COUNT_")
	private Integer record1000ScoreCount = 0;
	// 注册时间
	@Column(name = "REGISTERTIME_", nullable = false)
	private Date registerTime = new Date();

	// 上一次状态
	@Column(name = "LAST_STATUS_", columnDefinition = "CHAR(1)", nullable = false)
	private String lastStatus = "0";
	// 状态(0,未激活，1正常,2冻结,3找密码,)
	@Column(name = "STATUS_", columnDefinition = "CHAR(1)", nullable = false)
	private String status = "0";

	// 用户类型(0,普通用户，1管理员)
	@Column(name = "TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String type = "0";
	// 状态描述
	@Column(name = "STATUS_DESC_", length = 255)
	private String statusDesc = "未激活";
	// 钱
	@Column(name = "MONEY_")
	private Double money = 0D;
	// 推广积分
	@Column(name = "SPREAD_SCORE_")
	private Integer spreadScore = 0;
	// 推广人数
	@Column(name = "SPREAD_COUNT_")
	private Integer spreadCount = 0;
	/**
	 * 可选选项
	 */

	// 旺旺
	@Column(name = "WW_", length = 24)
	private String ww="";
	// 发布任务数
	@Column(name = "RELEASE_TASK_COUNT_")
	private Integer releaseTaskCount = 0;
	// 接手任务数
	@Column(name = "RECEIVE_TASK_COUNT_")
	private Integer receiveTaskCount = 0;

	// 最后一次登陆时间
	@Column(name = "LASTLOGINTIME_")
	private Date lastLoginTime;
	// 买家
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "USER_ID_")
	private List<BuyerEntity> buyers;

	// 卖家
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "USER_ID_")
	private List<SellerEntity> sellers;

	// 发布的任务
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "RELEASE_PERSON_")
	@Cascade(CascadeType.ALL)
	private List<CreditTaskEntity> releaseCreditTasks;

	// 接手的任务
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECEIVE_PERSON_")
	@Cascade(CascadeType.ALL)
	private List<CreditTaskEntity> receiveCreditTasks;

	// 资产记录
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	@Cascade(CascadeType.ALL)
	private List<CapitalLogEntity> capitalLogs;

	// 提现的记录
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	@Cascade(CascadeType.ALL)
	private List<WithdrawalsEntity> withdrawalses;

	// 介绍人
	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "REFEREE_ID_")
	private UserEntity referee;

	// // 我的介绍人
	@OneToMany(targetEntity = UserEntity.class, mappedBy = "referee", fetch = FetchType.LAZY)
	private List<UserEntity> myReferees;

	// // 我的任务模板
	@OneToMany(targetEntity = CreditTaskRepositoryEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	@Cascade(CascadeType.ALL)
	private List<CreditTaskRepositoryEntity> creditTaskRepositorys;

	// // 我的发的站内信
	@OneToMany(targetEntity = SmsEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "From_User_ID_")
	@Cascade(CascadeType.ALL)
	private List<SmsEntity> fromSms;

	// // 我的收的站内信
	@OneToMany(targetEntity = SmsEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "To_User_ID_")
	@Cascade(CascadeType.ALL)
	private List<SmsEntity> toSms;

	// // 我发送的物流信息
	@OneToMany(targetEntity = LogisticsEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "User_ID_")
	@Cascade(CascadeType.ALL)
	private List<LogisticsEntity> releaseLogistics;

	// // 我使用的物流信息
	@ManyToMany(mappedBy = "receieveUsers")
	@Cascade(CascadeType.ALL)
	private List<LogisticsEntity> receieveLogistics;

	// 任务联系人
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	@Cascade(CascadeType.ALL)
	private List<TaskLinkManEntity> taskLinkmans;

	public List<TaskLinkManEntity> getTaskLinkmans() {
		return taskLinkmans;
	}

	public void setTaskLinkmans(List<TaskLinkManEntity> taskLinkmans) {
		this.taskLinkmans = taskLinkmans;
	}

	/**
	 * 关联关系
	 * 
	 * @return
	 */
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

	public String getWw() {
		return ww;
	}

	public void setWw(String ww) {
		this.ww = ww;
	}

	public Integer getUpgradeScore() {
		return upgradeScore;
	}

	public void setUpgradeScore(Integer upgradeScore) {
		this.upgradeScore = upgradeScore;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatusAndLastStatus(String status) {
		this.lastStatus = this.status;
		this.status = status;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public List<CreditTaskEntity> getReleaseCreditTasks() {
		return releaseCreditTasks;
	}

	public void setReleaseCreditTasks(List<CreditTaskEntity> releaseCreditTasks) {
		this.releaseCreditTasks = releaseCreditTasks;
	}

	public List<CreditTaskEntity> getReceiveCreditTasks() {
		return receiveCreditTasks;
	}

	public void setReceiveCreditTasks(List<CreditTaskEntity> receiveCreditTasks) {
		this.receiveCreditTasks = receiveCreditTasks;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public List<BuyerEntity> getBuyers() {
		return buyers;
	}

	public void setBuyers(List<BuyerEntity> buyers) {
		this.buyers = buyers;
	}

	public List<SellerEntity> getSellers() {
		return sellers;
	}

	public void setSellers(List<SellerEntity> sellers) {
		this.sellers = sellers;
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

	public List<WithdrawalsEntity> getWithdrawalses() {
		return withdrawalses;
	}

	public void setWithdrawalses(List<WithdrawalsEntity> withdrawalses) {
		this.withdrawalses = withdrawalses;
	}

	public List<CreditTaskRepositoryEntity> getCreditTaskRepositorys() {
		return creditTaskRepositorys;
	}

	public void setCreditTaskRepositorys(
			List<CreditTaskRepositoryEntity> creditTaskRepositorys) {
		this.creditTaskRepositorys = creditTaskRepositorys;
	}

	public UserEntity getReferee() {
		return referee;
	}

	public void setReferee(UserEntity referee) {
		this.referee = referee;
	}

	public List<UserEntity> getMyReferees() {
		return myReferees;
	}

	public void setMyReferees(List<UserEntity> myReferees) {
		this.myReferees = myReferees;
	}

	public List<SmsEntity> getFromSms() {
		return fromSms;
	}

	public void setFromSms(List<SmsEntity> fromSms) {
		this.fromSms = fromSms;
	}

	public List<SmsEntity> getToSms() {
		return toSms;
	}

	public void setToSms(List<SmsEntity> toSms) {
		this.toSms = toSms;
	}

	public List<LogisticsEntity> getReleaseLogistics() {
		return releaseLogistics;
	}

	public void setReleaseLogistics(List<LogisticsEntity> releaseLogistics) {
		this.releaseLogistics = releaseLogistics;
	}

	public List<LogisticsEntity> getReceieveLogistics() {
		return receieveLogistics;
	}

	public void setReceieveLogistics(List<LogisticsEntity> receieveLogistics) {
		this.receieveLogistics = receieveLogistics;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Integer getRecord1000ScoreCount() {
		return record1000ScoreCount;
	}

	public void setRecord1000ScoreCount(Integer record1000ScoreCount) {
		this.record1000ScoreCount = record1000ScoreCount;
	}

	public List<CapitalLogEntity> getCapitalLogs() {
		return capitalLogs;
	}

	public void setCapitalLogs(List<CapitalLogEntity> capitalLogs) {
		this.capitalLogs = capitalLogs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
