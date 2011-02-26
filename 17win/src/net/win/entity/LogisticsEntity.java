package net.win.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 物流信息
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "Tb_Logicstics")
public class LogisticsEntity extends BaseEntity {
	// 运货单号
	@Column(name = "WAYBILL_", length = 25, nullable = false, unique = true)
	private String waybill;
	// 1 正常 2不能使用(处于纠纷中)
	@Column(name = "STATUS_", columnDefinition = "char(1)",nullable=false)
	private String status = "1";
	// 发货时间
	@Temporal(TemporalType.DATE)
	@Column(name = "SEND_DATE_", nullable = false)
	private Date sendDate;
	// 到达时间
	@Column(name = "ARRIVAL_DATE_")
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;
	// 发货信息
	@Column(name = "RELEASE_INFO_", nullable = false)
	private String releaseInfo;
	// 收货信息
	@Column(name = "RECEIEVE_INFO_", nullable = false)
	private String receieveInfo;
	// 快递公司
	@Column(name = "Express_Company_", length = 20, nullable = false)
	private String expressCompany;
	// 使用数
	@Column(name = "USE_COUNT_", nullable = false)
	private Integer useCount = 0;
	// 总收益
	@Column(name = "ReleaseDot_Count_", nullable = false)
	private Double releaseDotCount = 0D;
	// 备注
	@Column(name = "REMARK_")
	private String remark;

	// 记录日期
	@Column(name = "LOG_DATE_", nullable = false)
	private Date logDate = new Date();

	// 发送者
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "User_ID_")
	private UserEntity releaseUser;

	// 接手者
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Tb_Logistics_Bid_User", joinColumns = { @JoinColumn(name = "USER_ID_", referencedColumnName = "ID_") }, inverseJoinColumns = { @JoinColumn(name = "Logistics_ID_", referencedColumnName = "ID_") })
	private List<UserEntity> receieveUsers;

	public String getWaybill() {
		return waybill;
	}

	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getReceieveInfo() {
		return receieveInfo;
	}

	public void setReceieveInfo(String receieveInfo) {
		this.receieveInfo = receieveInfo;
	}

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public Double getReleaseDotCount() {
		return releaseDotCount;
	}

	public void setReleaseDotCount(Double releaseDotCount) {
		this.releaseDotCount = releaseDotCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public UserEntity getReleaseUser() {
		return releaseUser;
	}

	public void setReleaseUser(UserEntity releaseUser) {
		this.releaseUser = releaseUser;
	}

	public List<UserEntity> getReceieveUsers() {
		return receieveUsers;
	}

	public void setReceieveUsers(List<UserEntity> receieveUsers) {
		this.receieveUsers = receieveUsers;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getReleaseInfo() {
		return releaseInfo;
	}

	public void setReleaseInfo(String releaseInfo) {
		this.releaseInfo = releaseInfo;
	}

	public void addReceieveUser(UserEntity receieveUsers) {
		getReceieveUsers().add(receieveUsers);
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
