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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	@Column(name = "WAYBILL_", nullable = false, unique = true)
	private String waybill;
	// 发货时间
	@Column(name = "SEND_DATE_")
	private Date sendDate;
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
	@Column(name = "MONEY_", nullable = false)
	private Double money = 0D;
	// 备注
	@Column(name = "REMARK_")
	private String remark;

	// 发送者
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "User_ID_")
	private UserEntity releaseUser;

	// 接受者者
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinTable(name = "Tb_Logistics_Bid_User", joinColumns = { @JoinColumn(name = "USER_ID_", referencedColumnName = "ID_") }, inverseJoinColumns = { @JoinColumn(name = "Logistics_ID", referencedColumnName = "ID_") })
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
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

}
