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
public class CreditTaskEntity extends BaseEntity {
	// 任务ID 给用户看 格式yyymmddhhmmssis
	@Column(name = "TESTID_", columnDefinition = "CHAR(17)", nullable = false, unique = true)
	private String testID;
	// 发布点
	@Column(name = "RELEASE_DOT_", nullable = false)
	private Double releaseDot;
	// 发布时间
	@Column(name = "RELEASE_DATE")
	private Date releaseDate;
	// 发布人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "RELEASE_PERSON_")
	private UserEntity releasePerson;
	// 接收人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "RECEIVE_PERSON_")
	private UserEntity receivePerson;
	// 好评
	@Column(name = "COMMENT_", length = 255)
	private String comment;
	// 类型 ( 1:淘宝，2：拍拍，3有啊)
	@Column(name = "TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;
	// 价格
	@Column(name = "MONEY_", nullable = false)
	private Double money;
	// 所剩时间(分钟 / 秒（卖家发货）)
	@Column(name = "REMAIN_TIME_")
	private Long remainTime = 20L;
	// 状态
	// (-2（已接手等待审核） -1因为申述被暂停(但是要判断12小时，不能像2000w一样不能判断) ,0 还没开始（定时任务有用）
	// 1:等待接手，2买家接手，卖家等待买家付款，3买家已付款。等待卖家确认发货，4：卖家发货了。等待买家确认好评。5：买家已经确认好评。等待卖家确认好评。6完成
	@Column(name = "STATUS_", length = 2, nullable = false)
	private String status;
	// 任务保护
	@Column(name = "PROTECT_", nullable = false)
	private Boolean protect;
	// 商品地址
	@Column(name = "ITEM_URL_", length = 2000, nullable = false)
	private String itemUrl;
	// 是否修改价格
	@Column(name = "UPDATE_PRICE_", nullable = false)
	private Boolean updatePrice;
	// 动态评分(1马上带字好评)
	@Column(name = "GRADE_", length = 20, nullable = false)
	private String grade;
	// 任务类型(1 虚拟任务 2，实体任务 3，套餐任务)
	@Column(name = "TASK_TYPE", columnDefinition = "CHAR(1)", nullable = false)
	private String taskType;
	// 间隔几个小时(x*24[勾选]或则X[自定义] 0为马上收货)
	@Column(name = "INTERVAL_HOUR_", nullable = false)
	private Integer intervalHour;
	// 运货单号
	@Column(name = "WAYBILL_", length = 30)
	private String waybill;
	// 地址
	@Column(name = "ADDRESS_", length = 100)
	private String address;

	// 指定接任务的人
	@Column(name = "ASSIGN_USER_", length = 12)
	private String assignUser;

	// 附加金额、
	@Column(name = "ADDTATION_MONEY", nullable = false)
	private Double addtionMoney = 0d;
	// 附加发布点
	@Column(name = "ADDTATION_RELEASE_DOT", nullable = false)
	private Double addtionReleaseDot = 0D;

	// 定时任务时间(不能小于开始时间)
	@Column(name = "TIMEING_TIME_")
	private Date timeingTime;

	// 接手时间
	@Column(name = "RECEIVE_DATE_")
	private Date receiveDate;

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

	public Long getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(Long remainTime) {
		this.remainTime = remainTime;
	}

	public Boolean getProtect() {
		return protect;
	}

	public void setProtect(Boolean protect) {
		this.protect = protect;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiveIP() {
		return receiveIP;
	}

	public void setReceiveIP(String receiveIP) {
		this.receiveIP = receiveIP;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Double getAddtionMoney() {
		return addtionMoney;
	}

	public void setAddtionMoney(Double addtionMoney) {
		this.addtionMoney = addtionMoney;
	}

	public Double getAddtionReleaseDot() {
		return addtionReleaseDot;
	}

	public void setAddtionReleaseDot(Double addtionReleaseDot) {
		this.addtionReleaseDot = addtionReleaseDot;
	}

	public String getAssignUser() {
		return assignUser;
	}

	public void setAssignUser(String assignUser) {
		this.assignUser = assignUser;
	}

	public String getTestID() {
		return testID;
	}

	public void setTestID(String testID) {
		this.testID = testID;
	}

	public Double getReleaseDot() {
		return releaseDot;
	}

	public void setReleaseDot(Double releaseDot) {
		this.releaseDot = releaseDot;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public UserEntity getReleasePerson() {
		return releasePerson;
	}

	public void setReleasePerson(UserEntity releasePerson) {
		this.releasePerson = releasePerson;
	}

	public UserEntity getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(UserEntity receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
