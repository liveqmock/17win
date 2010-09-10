package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	// 动态评分(x:默认好评，x:全部5分 ...)
	@Column(name = "GRADE_", columnDefinition = "CHAR(1)", nullable = false)
	private String grade;
	// 间隔几个小时(x*24[勾选]或则X[自定义] 0为马上收货)
	@Column(name = "INTERVAL_HOUR_", nullable = false)
	private Integer intervalHour;

	// 发布人的卖家号
	@Column(name = "SELLER_ID_", nullable = false)
	private Long sellerID;

	// 发布点
	@Column(name = "RELEASE_DOT_", nullable = false)
	private Float releaseDot;

	// 描述
	@Column(name = "DESC_")
	private String desc;

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

	public Float getReleaseDot() {
		return releaseDot;
	}

	public void setReleaseDot(Float releaseDot) {
		this.releaseDot = releaseDot;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
