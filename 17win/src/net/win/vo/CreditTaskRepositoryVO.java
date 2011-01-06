package net.win.vo;

import java.util.Date;

import net.win.BaseVO;

public class CreditTaskRepositoryVO extends BaseVO {

	// 名字
	private String name;
	// 类型 ( 1:淘宝，2：拍拍，3有啊)
	private String type;
	// 价格
	private Double money;
	// 任务保护
	private Boolean protect;
	// 商品地址
	private String itemUrl;
	// 是否修改价格
	private Boolean updatePrice;
	// 指定某人
	private String assignUser;
	// 动态评分(x:默认好评，x:全部5分 ...)
	private String grade;
	// 好评时间类型(1 马上 2:24小时 3:48小时 ，4：72小时，5 ：自定义)
	private String goodTimeType;
	// 间隔几个小时(x*24[勾选]或则X[自定义] 0为马上收货) 收货时间
	private Integer intervalHour;
	// 发布人的卖家号
	private Long sellerID;

	// 发布点
	private Double releaseDot;

	// 上次发布时间
	private Date lastDispathDate;
	// 发布次数
	private Integer dispathCount = 0;

	// 是否地址
	private Boolean address;
	// 描述
	private String desc;

	// 现实
	// 发布人的卖家号名字
	private String sellerName;

	// 附加金额、
	private Integer addtionMoney = 0;
	// 附加发布点
	private Integer addtionReleaseDot = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Double getReleaseDot() {
		return releaseDot;
	}

	public void setReleaseDot(Double releaseDot) {
		this.releaseDot = releaseDot;
	}

	public Integer getAddtionMoney() {
		return addtionMoney;
	}

	public void setAddtionMoney(Integer addtionMoney) {
		this.addtionMoney = addtionMoney;
	}

	public Integer getAddtionReleaseDot() {
		return addtionReleaseDot;
	}

	public void setAddtionReleaseDot(Integer addtionReleaseDot) {
		this.addtionReleaseDot = addtionReleaseDot;
	}

	public String getAssignUser() {
		return assignUser;
	}

	public void setAssignUser(String assignUser) {
		this.assignUser = assignUser;
	}

}
