package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 信誉任务模板
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "Tb_CreditTaskReposotory")
public class CreditTaskRepositoryEntity extends BaseEntity {

	// 名字
	@Column(name = "NAME_", length = 20, nullable = false, unique = true)
	private String name;
	// 类型 ( 1:淘宝，2：拍拍，3有啊)
	@Column(name = "TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;
	// 价格
	@Column(name = "MONEY_", nullable = false)
	private Double money;
	// 任务保护
	@Column(name = "PROTECT_", nullable = false)
	private Boolean protect;
	// 商品地址
	@Column(name = "ITEM_URL_", length = 50, nullable = false)
	private String itemUrl;
	// 是否修改价格
	@Column(name = "UPDATE_PRICE_", nullable = false)
	private Boolean updatePrice;
	// 上次发布时间
	@Column(name = "lastDispathDate_")
	private Date lastDispathDate;
	// 发布次数
	@Column(name = "dispathCount_")
	private Integer dispathCount = 0;
	// 好评时间类型(1 马上 2:24小时 3:48小时 ，4：72小时，5 ：自定义)
	@Column(name = "GOOD_TIME_TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String goodTimeType;
	// 动态评分(x:默认好评，x:全部5分 ...)
	@Column(name = "GRADE_", columnDefinition = "CHAR(1)", nullable = false)
	private String grade;
	// 间隔几个小时(x*24[勾选]或则X[自定义] 0为马上收货) 收货时间
	@Column(name = "INTERVAL_HOUR_", nullable = false)
	private Integer intervalHour;

	// 发布点
	@Column(name = "RELEASE_DOT_", nullable = false)
	private Double releaseDot;

	// 发布人的卖家号
	@Column(name = "SELLER_ID_", nullable = false)
	private Long sellerID;

	// 是否地址
	@Column(name = "ADDRESS_", nullable = false)
	private Boolean address;

	// 描述
	@Column(name = "DESC_", length = 255)
	private String desc;

	// 所属人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "USER_ID_")
	private UserEntity user;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

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

	public Boolean getProtect() {
		return protect;
	}

	public void setProtect(Boolean protect) {
		this.protect = protect;
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

	public Long getSellerID() {
		return sellerID;
	}

	public void setSellerID(Long sellerID) {
		this.sellerID = sellerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAddress() {
		return address;
	}

	public void setAddress(Boolean address) {
		this.address = address;
	}

	public String getGoodTimeType() {
		return goodTimeType;
	}

	public void setGoodTimeType(String goodTimeType) {
		this.goodTimeType = goodTimeType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getLastDispathDate() {
		return lastDispathDate;
	}

	public void setLastDispathDate(Date lastDispathDate) {
		this.lastDispathDate = lastDispathDate;
	}

	public Integer getDispathCount() {
		return dispathCount;
	}

	public void setDispathCount(Integer dispathCount) {
		this.dispathCount = dispathCount;
	}

	public Double getReleaseDot() {
		return releaseDot;
	}

	public void setReleaseDot(Double releaseDot) {
		this.releaseDot = releaseDot;
	}

 

}
