package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tb_Withdrawals")
public class WithdrawalsEntity extends BaseEntity {
	/**
	 * 基本信息
	 */
	// 提现类型(1 店铺地址提现,2 支付宝提现 3 财付通提现)
	@Column(name = "TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;
	// 提现金额
	@Column(name = "MONEY_", nullable = false)
	private Double money;
	// 操作日期
	@Column(name = "OPERATION_DATE_", nullable = false)
	private Date operationDate;
	// 状态(0：申请中，1：完成,2驳回,可能因为商品价格和提取价格不同)
	@Column(name = "STATUS_", nullable = false)
	private String status = "0";
	// 状态描述
	@Column(name = "STATUS_DESC_", nullable = false)
	private String statusDesc = "";

	// 名字(掌柜名，或者是真实名字)
	@Column(name = "REAL_NAME_", length = 20, nullable = false)
	private String realName;

	// 名字(商品地址，支付宝账号，财付通账号)
	@Column(name = "REAL_IDENTITY_", length = 50, nullable = false)
	private String realIdentity;

	// 提现人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "USER_ID_")
	private UserEntity user;
	/**
	 * 选填
	 */
	// 店铺类型(1 淘宝 2拍拍 3有啊)
	@Column(name = "REALNAME_", columnDefinition = "CHAR(1)")
	private String shopType;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRealIdentity() {
		return realIdentity;
	}

	public void setRealIdentity(String realIdentity) {
		this.realIdentity = realIdentity;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
