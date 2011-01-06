package net.win.service.admin.service;

import java.util.Date;

import net.win.BaseVO;

public class AdminCreditTaskVO extends BaseVO {
	// 类型 ( 1:淘宝，2：拍拍，3有啊)
	private String type;
	// 价格
	private Double money;
	// 状态
	// (-1因为申述被暂停(但是要判断12小时，不能像2000w一样不能判断) ,0 定时任务有用
	// ,[1:等待我付款,2:等待卖家发货,3:等待卖家发货](买家),[4:等待接手,5:等待审核人,6:等待我发货,7:等待买家确认8:等待我核查好评](卖家))
	private String status;
	// 商品地址
	private String itemUrl;

	private Date releaseDate;
	// 是否修改价格
	private Boolean updatePrice;
	// 动态评分(x:默认好评，x:全部5分 ...)
	private String grade;
	// 间隔几个小时(x*24[勾选]或则X[自定义])
	private Integer intervalHour;
	// 剩余时间
	private Integer remainTime;
	// 运货单号
	private String waybill;
	// 定时任务时间(不能小于开始时间)
	private Date timeingTime;
	// 接手时间
	private Date startDate;
	// 任务ID 给用户看 格式yyymmddhhmmssis
	private String testID;
	// 接收人IP
	private String receiveIP;
	// 发布点
	private Float releaseDot;
	// 好评时间类型
	private String goodTimeType;
	// 描述
	private String desc;

	// 附加金额、
	private Integer addtionMoney;
	// 附加发布点
	private Integer addtionReleaseDot;

	// 任务保护
	private Boolean protect;

	/**
	 * 和entity无关的数据
	 */
	private String releaseUser;
	private String receiveUser;

	private Boolean repository;
	private Boolean address;
	private Long sellerID;
	private Long buyerID;
	// 仓库名
	private String respositoryName;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTestID() {
		return testID;
	}

	public void setTestID(String testID) {
		this.testID = testID;
	}

	public String getReceiveIP() {
		return receiveIP;
	}

	public void setReceiveIP(String receiveIP) {
		this.receiveIP = receiveIP;
	}

	public Float getReleaseDot() {
		return releaseDot;
	}

	public void setReleaseDot(Float releaseDot) {
		this.releaseDot = releaseDot;
	}

	public Long getSellerID() {
		return sellerID;
	}

	public void setSellerID(Long sellerID) {
		this.sellerID = sellerID;
	}

	public Integer getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(Integer remainTime) {
		this.remainTime = remainTime;
	}

	public Boolean getRepository() {
		return repository;
	}

	public void setRepository(Boolean repository) {
		this.repository = repository;
	}

	public Boolean getAddress() {
		return address;
	}

	public void setAddress(Boolean address) {
		this.address = address;
	}

	public Boolean getProtect() {
		return protect;
	}

	public void setProtect(Boolean protect) {
		this.protect = protect;
	}

	public String getRespositoryName() {
		return respositoryName;
	}

	public void setRespositoryName(String respositoryName) {
		this.respositoryName = respositoryName;
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

	public Long getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(Long buyerID) {
		this.buyerID = buyerID;
	}

	public Integer getAddtionMoney() {
		if (addtionMoney == null)
			return 0;
		return addtionMoney;
	}

	public void setAddtionMoney(Integer addtionMoney) {
		this.addtionMoney = addtionMoney;
	}

	public Integer getAddtionReleaseDot() {
		if (addtionReleaseDot == null)
			return 0;
		return addtionReleaseDot;
	}

	public void setAddtionReleaseDot(Integer addtionReleaseDot) {
		this.addtionReleaseDot = addtionReleaseDot;
	}

	public String getReleaseUser() {
		return releaseUser;
	}

	public void setReleaseUser(String releaseUser) {
		this.releaseUser = releaseUser;
	}

	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}
