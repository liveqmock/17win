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
	// 发货信息
	private String releaseInfo;
	// 收货信息
	private String receieveInfo;
	// 快递公司
	private String expressCompany;
	// 使用数
	private Integer useCount = 0;
	// 总收益
	private Double releaseDotCount = 0D;
	// 备注
	private String remark;

	private Date arrivalDate;

	// 记录日期
	private Date logDate = new Date();

	private Boolean deleteFlag = true;

	private Date startDate;
	private Date endDate;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReleaseInfo() {
		return releaseInfo;
	}

	public void setReleaseInfo(String releaseInfo) {
		this.releaseInfo = releaseInfo;
	}

	public Double getReleaseDotCount() {
		return releaseDotCount;
	}

	public void setReleaseDotCount(Double releaseDotCount) {
		this.releaseDotCount = releaseDotCount;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public LogisticsEntity getLogistics() {
		return logistics;
	}

	public void setLogistics(LogisticsEntity logistics) {
		this.logistics = logistics;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
