package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
/**
 * 信誉任务
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_CREDITTASK")
public class CreditTaskEntity extends BaseEntity {
	// 类型 ( 1:淘宝，2：拍拍，3有啊)
	@Column(name = "TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;
	// 发布人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "RELEASE_PERSON_")
	private UserEntity releasePerson;
	// 接收人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "RECEIVE_PERSON_")
	private UserEntity receivePerson;
	// 发布点
	@Column(name = "RELEASE_DOT_", nullable = false)
	private Float releaseDot;
	// 价格
	@Column(name = "MONEY_", nullable = false)
	private Double money;
	// 接手时间
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;
	// 状态 ([1:等待我付款,2:等待卖家发货,3:等待卖家发货](买家),[4:等待接手,5:等待接手,6:等待我发货,7:等待买家确认8:等待我核查好评](卖家))
	@Column(name = "STATUS", columnDefinition = "CHAR(1)", nullable = false)
	private String status;
	// 商品地址
	@Column(name = "ITEM_URL_", length = 50, nullable = false)
	private String itemUrl;
	// 是否修改价格
	@Column(name = "UPDATE_PRICE_", nullable = false)
	private Boolean updatePrice;
	// 动态评分(x:默认好评，x:全部5分 ...)
	@Column(name = "GRADE_", columnDefinition = "CHAR(1)", nullable = false)
	private String grade;
	// 间隔几个小时(x*24[勾选]或则X[自定义])
	@Column(name = "INTERVAL_HOUR_", nullable = false)
	private Integer intervalHour;
	// 运货单号
	@Column(name = "WAYBILL_", length = 30)
	private String waybill;
	// 定时任务时间(不能小于开始时间)
	@Column(name = "TIMEING_TIME_")
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

	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
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
	public Date getTimeingTime() {
		return timeingTime;
	}
	public void setTimeingTime(Date timeingTime) {
		this.timeingTime = timeingTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWaybill() {
		return waybill;
	}
	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}
	public Integer getIntervalHour() {
		return intervalHour;
	}
	public void setIntervalHour(Integer intervalHour) {
		this.intervalHour = intervalHour;
	}

}
