package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 信誉任务
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "Tb_CreditTask")
public class CreditTaskEntity extends TaskBaseEntity {
	// 类型 ( 1:淘宝，2：拍拍，3有啊)
	@Column(name = "TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;
	// 价格
	@Column(name = "MONEY_", nullable = false)
	private Double money;
	// 所剩时间(分钟)
	@Column(name = "REMAIN_TIME_", nullable = false)
	private Integer remainTime = 20;
	// 状态
	// (-1因为申述被暂停(但是要判断12小时，不能像2000w一样不能判断) ,0 还没开始（定时任务有用）
	// 1:等待接手，2买家接手，卖家等待买家付款，3买家已付款。等待卖家确认发货，4：卖家发货了。等待买家确认好评。5：买家已经确认好评。等待卖家确认好评。6完成
	// ，-2审核
	@Column(name = "STATUS", length = 2, nullable = false)
	private String status;
	// 任务保护
	@Column(name = "PROTECT_", nullable = false)
	private Boolean protect;
	// 商品地址
	@Column(name = "ITEM_URL_", length = 255, nullable = false)
	private String itemUrl;
	// 是否修改价格
	@Column(name = "UPDATE_PRICE_", nullable = false)
	private Boolean updatePrice;
	// 动态评分(1:全部5分，2:全部不打分 3带子好评)
	@Column(name = "GRADE_", columnDefinition = "CHAR(1)", nullable = false)
	private String grade;
	// 好评时间类型(1 马上 2:24小时 3:48小时 ，4：72小时，5 ：自定义)
	@Column(name = "GOOD_TIME_TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String goodTimeType;
	// 间隔几个小时(x*24[勾选]或则X[自定义] 0为马上收货)
	@Column(name = "INTERVAL_HOUR_", nullable = false)
	private Integer intervalHour;
	// 运货单号
	@Column(name = "WAYBILL_", length = 30)
	private String waybill;
	// 地址
	@Column(name = "ADDRESS_", length = 100)
	private String address;
	// 定时任务时间(不能小于开始时间)
	@Column(name = "TIMEING_TIME_")
	private Date timeingTime;

	// 当前操作的时间
	@Column(name = "NOW_OPERTATION_DATE_")
	private Date nowOpertationDate;

	// 接收人IP
	@Column(name = "RECEIVE_IP_", length = 20)
	private String receiveIP;

	// 发布人的卖家号
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SELLER_ID_")
	private SellerEntity seller;

	// 接收人的买家号
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUYER_ID_")
	private BuyerEntity buyer;

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

	public Integer getIntervalHour() {
		return intervalHour;
	}

	public void setIntervalHour(Integer intervalHour) {
		this.intervalHour = intervalHour;
	}

	public String getWaybill() {
		return waybill;
	}

	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}

	public Date getTimeingTime() {
		return timeingTime;
	}

	public void setTimeingTime(Date timeingTime) {
		this.timeingTime = timeingTime;
	}

	public SellerEntity getSeller() {
		return seller;
	}

	public void setSeller(SellerEntity seller) {
		this.seller = seller;
	}

	public BuyerEntity getBuyer() {
		return buyer;
	}

	public void setBuyer(BuyerEntity buyer) {
		this.buyer = buyer;
	}

	public Integer getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(Integer remainTime) {
		this.remainTime = remainTime;
	}

	public Boolean getProtect() {
		return protect;
	}

	public void setProtect(Boolean protect) {
		this.protect = protect;
	}

	public String getGoodTimeType() {
		return goodTimeType;
	}

	public void setGoodTimeType(String goodTimeType) {
		this.goodTimeType = goodTimeType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getNowOpertationDate() {
		return nowOpertationDate;
	}

	public void setNowOpertationDate(Date nowOpertationDate) {
		this.nowOpertationDate = nowOpertationDate;
	}

	public String getReceiveIP() {
		return receiveIP;
	}

	public void setReceiveIP(String receiveIP) {
		this.receiveIP = receiveIP;
	}
}
