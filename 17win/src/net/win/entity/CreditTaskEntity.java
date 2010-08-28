package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 信誉任务
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_CREDITTASK")
public class CreditTaskEntity extends BaseEntity {
	// 发布人
	@Column(name = "RELEASE_PERSON_", nullable = false)
	private UserEntity releasePerson;
	// 发布点
	@Column(name = "RELEASE_DOT_", nullable = false)
	private Float releaseDot;
	// 价格
	private Float money;
	// 接手时间
	private Date startDate;
	// 接收人
	private UserEntity receivePerson;
	// 状态
	private String status;
	// 商品地址
	private String itemUrl;
	// 是否修改价格
	private Boolean updatePrice;
	// 动态评分
	private String grade;
	// 收货时间类型[0:自定义,1:立即，2:24小时,3:48小时,4：72小时]
	private String receiveType;
	// 间隔几个小时
	private Integer intervalTime;
	// 定时任务时间(不能小于开始时间)
	private Date timeingTime;
	public Float getReleaseDot() {
		return releaseDot;
	}
	public void setReleaseDot(Float releaseDot) {
		this.releaseDot = releaseDot;
	}
	public UserEntity getReleasePerson() {
		return releasePerson;
	}
	public void setReleasePerson(UserEntity releasePerson) {
		this.releasePerson = releasePerson;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public UserEntity getReceivePerson() {
		return receivePerson;
	}
	public void setReceivePerson(UserEntity receivePerson) {
		this.receivePerson = receivePerson;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	public Boolean getUpdatePrice() {
		return updatePrice;
	}
	public void setUpdatePrice(Boolean updatePrice) {
		this.updatePrice = updatePrice;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getReceiveType() {
		return receiveType;
	}
	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}
	public Integer getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(Integer intervalTime) {
		this.intervalTime = intervalTime;
	}
	public Date getTimeingTime() {
		return timeingTime;
	}
	public void setTimeingTime(Date timeingTime) {
		this.timeingTime = timeingTime;
	}

}
