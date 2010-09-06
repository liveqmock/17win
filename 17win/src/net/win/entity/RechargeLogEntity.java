package net.win.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 充值记录
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_RECHARGELOG")
public class RechargeLogEntity {
	// 类型(1 购买发布点 2 充值 3会员)
	private String type;
	// 描述
	private String desc;
	// 金额
	private Double money;
	// 结果
	private String result;
	// 操作时间
	private Date operationDate;
	// 操作人员
	private UserEntity operator;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	public UserEntity getOperator() {
		return operator;
	}
	public void setOperator(UserEntity operator) {
		this.operator = operator;
	}

}
