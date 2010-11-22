package net.win.vo;

import java.util.Date;

import javax.persistence.Entity;

import net.win.BaseVO;

/**
 * 资产记录
 * 
 * @author xgj
 * 
 */
@Entity
public class CapitalLogVO extends BaseVO {
	// 类别， 1 金额 2 发布点
	private String type;
	// 记录时间
	private Date logTime;
	// 描述
	private String desc;

	//

	private Double value;
	// 查询
	private Date startDate;
	private Date endDate;

	private Double startValue;
	private Double endValue;

	public Double getStartValue() {
		return startValue;
	}

	public void setStartValue(Double startValue) {
		this.startValue = startValue;
	}

	public Double getEndValue() {
		return endValue;
	}

	public void setEndValue(Double endValue) {
		this.endValue = endValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
