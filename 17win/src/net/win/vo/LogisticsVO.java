package net.win.vo;

import java.util.Date;

import net.win.BaseVO;
import net.win.entity.LogisticsEntity;

public class LogisticsVO extends BaseVO {

	private LogisticsEntity logistics = new LogisticsEntity();
	// 运货单号
	private String waybill;
	// 发货时间
	private Date sendDate;
	// 收货信息
	private String receieveInfo;
	// 快递公司
	private String expressCompany;
	// 使用数
	private Integer useCount = 0;
	// 总收益
	private Double money = 0D;
	// 备注
	private String remark;

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

	public LogisticsEntity getLogistics() {
		return logistics;
	}

	public void setLogistics(LogisticsEntity logistics) {
		this.logistics = logistics;
	}

}
