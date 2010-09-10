package net.win.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 任务记录
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "Tb_TaskLog")
public class TaskLogEntity {
	// 类型(1 淘宝 2 拍拍 3有啊 4流量 5收藏)
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
