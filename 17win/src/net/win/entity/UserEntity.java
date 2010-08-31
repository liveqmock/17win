package net.win.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "TB_USER")
public class UserEntity extends BaseEntity {
	/**
	 * 基本信息
	 */
	// 用户名
	@Column(name = "USERNAME_", length = 12, unique = true, nullable = false)
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
	private Double releaseDot;
	// 电子邮箱
	@Column(name = "EMAIL_", length = 24,unique=true, nullable = false)
	private String email;
	// 手机
	@Column(name = "TELPHONE_", unique = true, columnDefinition = "CHAR(11)", nullable = false)
	private String telephone;
	// 可以兑换发布点的积分
	@Column(name = "CONVERT_SCORE_", nullable = false)
	private Integer convertScore;
	// 升级用的积分，不能兑换发布点
	@Column(name = "UPGRADE_SCORE_", nullable = false)
	private Integer upgradeScore;
	// 注册时间
	@Column(name = "REGISTERTIME_", nullable = false)
	private Date registerTime;
	// 当前级别(一心，还是一钻)
	@Column(name = "LEVEL_", length = 2, nullable = false)
	private String level;
	// 状态(0，没激活，1激活，2被冻结,3xxxxx)
	@Column(name = "STATUS_", columnDefinition = "CHAR(1)", nullable = false)
	private String status;
	// 钱
	@Column(name = "MONEY_", nullable = false)
	private Double money;

	/**
	 * 可选选项
	 */
	// 旺旺
	@Column(name = "WW_", length = 24)
	private String ww;
	// 最后一次登陆时间
	@Column(name = "LASTLOGINTIME_")
	private Date lastLoginTime;
	// 淘宝信息
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TAOBAOUSER_ID_")
	@Cascade(CascadeType.ALL)
	private TaobaoUserEntity taobaoUser;

	// 拍拍信息
	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "PAIPAIUSER_ID_")
	private PaipaiUserEntity paipaiUser;

	// 有啊信息
	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "YOUAUSER_ID_")
	private YouaUserEntity youaUser;

	// 发布的任务
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "RELEASE_PERSON_")
	@Cascade(CascadeType.ALL)
	private List<CreditTaskEntity> releaseCreditTasks;
	// 接受的任务
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECEIVE_PERSON_")
	@Cascade(CascadeType.ALL)
	private List<CreditTaskEntity> receiveCreditTasks;
	// 省
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RPOVINCE_ID_")
	private ProvinceEntity province;
	// 市
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID_")
	private CityEntity city;
	// 县
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID_")
	private AreaEntity area;

	// 介绍人
	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "REFEREE_ID_")
	private UserEntity referee;

	// // 我的介绍人
	@OneToMany(targetEntity = UserEntity.class, mappedBy = "referee")
	private List<UserEntity> myReferees;

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

	/**
	 * 关联关系
	 * 
	 * @return
	 */
	// // 角色
	// private RoleEntity role = new RoleEntity();
	// // 推广
	// private Set<UserEntity> promoteUsers = new HashSet<UserEntity>(0);
	// // 信誉任务模板
	// private Set<CreditTaskTemplateEntity> creditTaskTemplates = new
	// HashSet<CreditTaskTemplateEntity>(
	// 0);
	// // 流量任务模板
	// private Set<FlowTaskTemplateEntity> flowTaskTemplates = new
	// HashSet<FlowTaskTemplateEntity>(
	// 0);
	// // 收藏任务模板
	// private Set<CollectTaskTemplateEntity> collectTaskTemplates = new
	// HashSet<CollectTaskTemplateEntity>(
	// 0);
	// // 真实地址模板
	// @ManyToMany(fetch = FetchType.LAZY)
	// @JoinTable(name = "TB_USER_RECEIVEREAL_TP", joinColumns =
	// {@JoinColumn(name = "USER_ID_", nullable = false)}, inverseJoinColumns =
	// {@JoinColumn(name = "RECEIVEREALINFO_ID_", nullable = false)})
	// private Set<ReceiveRealInfoEntity> receiveRealInfos = new
	// HashSet<ReceiveRealInfoEntity>(
	// 0);
	// // 虚拟地址模板
	// @ManyToMany(fetch = FetchType.LAZY)
	// @JoinTable(name = "TB_USER_RECEIVEVIRTUAL_TP", joinColumns =
	// {@JoinColumn(name = "USER_ID_", nullable = false)}, inverseJoinColumns =
	// {@JoinColumn(name = "RECEIVEVIRTUALINFO_ID_", nullable = false)})
	// private Set<ReceiveVirtualInfoEntity> receiveVirtualInfos = new
	// HashSet<ReceiveVirtualInfoEntity>(
	// 0);
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

	public Double getReleaseDot() {
		return releaseDot;
	}

	public void setReleaseDot(Double releaseDot) {
		this.releaseDot = releaseDot;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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

	public ProvinceEntity getProvince() {
		return province;
	}

	public void setProvince(ProvinceEntity province) {
		this.province = province;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public AreaEntity getArea() {
		return area;
	}

	public void setArea(AreaEntity area) {
		this.area = area;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TaobaoUserEntity getTaobaoUser() {
		return taobaoUser;
	}

	public void setTaobaoUser(TaobaoUserEntity taobaoUser) {
		this.taobaoUser = taobaoUser;
	}

	public PaipaiUserEntity getPaipaiUser() {
		return paipaiUser;
	}

	public void setPaipaiUser(PaipaiUserEntity paipaiUser) {
		this.paipaiUser = paipaiUser;
	}

	public YouaUserEntity getYouaUser() {
		return youaUser;
	}

	public void setYouaUser(YouaUserEntity youaUser) {
		this.youaUser = youaUser;
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

}
