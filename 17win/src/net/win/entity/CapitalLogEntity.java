package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 资产记录
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_CapitalLog")
public class CapitalLogEntity extends BaseEntity {
	// 类别， 1 金额 2 发布点  3积分
	@Column(name = "Type_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;
	// 所属人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "USER_ID_")
	private UserEntity user;
	// 记录时间
	@Column(name = "LogTime_", nullable = false)
	private Date logTime;

	// 金额 或则发布点的值
	@Column(name = "Value_", nullable = false)
	private Double value;
	
	// 剩余资产
	@Column(name = "Remain_Value_", nullable = false)
	private Double remainValue;
	// 描述
	@Column(name = "Desc_", length = 200, nullable = false)
	private String desc;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getRemainValue() {
		return remainValue;
	}

	public void setRemainValue(Double remainValue) {
		this.remainValue = remainValue;
	}

}
