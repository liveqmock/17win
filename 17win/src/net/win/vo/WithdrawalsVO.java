package net.win.vo;

import java.util.Date;

import net.win.BaseVO;

public class WithdrawalsVO extends BaseVO {
	/**
	 * 基本信息
	 */
	// 提现类型(1 店铺地址提现,2 支付宝提现 3 财付通提现)
	private String type;
	// 提现金额
	private Double money;
	// 操作日期
	private Date operationDate;
	// 状态(1：申请中，2：完成)
	private String status;
	// 名字(掌柜名，或者是真实名字)
	private String realName;
	// 真实的标志(商品地址，支付宝账号，财付通账号)
	private String realIdentity;
	// 状态描述
	private String statusDesc = "";
	/**
	 * 选填
	 */
	// 店铺类型(1 淘宝 2拍拍 3有啊)
	private String shopType;

	/**
	 * 其他数据
	 * 
	 * @return
	 */
	// 操作码
	private String operationCode;

	/**
	 * 其他
	 * 
	 * @return
	 */
	private Double startMoney;
	private Double endMoney;
	private Date startDate;
	private Date endDate;

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getRealIdentity() {
		return realIdentity;
	}

	public void setRealIdentity(String realIdentity) {
		this.realIdentity = realIdentity;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

}
