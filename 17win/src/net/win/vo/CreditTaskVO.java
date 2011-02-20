package net.win.vo;

import java.util.Date;

import net.win.BaseVO;

public class CreditTaskVO extends BaseVO {
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
	// 是否修改价格
	private Boolean updatePrice;
	// 发布时间
	private Date releaseDate;
	// 动态评分(x:默认好评，x:全部5分 ...)
	private String grade;
	// 任务类型(1 虚拟任务 2，实体任务 3，套餐任务)
	private String taskType;
	// 间隔几个小时(x*24[勾选]或则X[自定义])
	private Integer intervalHour;
	// 指定某人
	private String assignUser;
	// 剩余时间
	private Long remainTime;
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
	private Double releaseDot;
	// 好评时间类型
	private String goodTimeType;
	// 描述
	private String comment;

	// 附加金额、
	private Double addtionMoney;
	// 附加发布点
	private Double addtionReleaseDot;

	// 任务保护
	private Boolean protect;
	/**
	 * 和entity无关的数据
	 */
	private Boolean repository;
	private String address;
	private Long sellerID;
	private Long buyerID;
	private Date receiveDate;
	private String respositoryName;
	private String sellname;
	private String buyername;
	private String jsUsername;
	private Integer jsUpgradeScore;
	private String jsVipType;
	private String jsQQ;
	private String jsWW;
	private String jsTelphone;
	private String fbShopURL;
	private String fbQQ;
	private String fbWW;
	private String fbUsername;
	private String fbTelphone;
	private Integer fbUpgradeScore;
	private String fbVipType;

	private Date fbStartDate;
	private Date fbEndDate;
	private Date jsStartDate;
	private Date jsEndDate;
	
	//刷新 时间
	private Integer refreshSec;
	
	//任务大厅的查询标记
	private String moneyFlag;
	public String getMoneyFlag() {
		return moneyFlag;
	}

	public void setMoneyFlag(String moneyFlag) {
		this.moneyFlag = moneyFlag;
	}

	public Integer getRefreshSec() {
		return refreshSec;
	}

	public void setRefreshSec(Integer refreshSec) {
		this.refreshSec = refreshSec;
	}

	public String getJsQQ() {
		return jsQQ;
	}

	public void setJsQQ(String jsQQ) {
		this.jsQQ = jsQQ;
	}

	public String getJsUsername() {
		return jsUsername;
	}

	public void setJsUsername(String jsUsername) {
		this.jsUsername = jsUsername;
	}

	public String getSellname() {
		return sellname;
	}

	public void setSellname(String sellname) {
		this.sellname = sellname;
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

	public Long getSellerID() {
		return sellerID;
	}

	public void setSellerID(Long sellerID) {
		this.sellerID = sellerID;
	}

	public Long getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(Long remainTime) {
		this.remainTime = remainTime;
	}

	public Boolean getRepository() {
		return repository;
	}

	public void setRepository(Boolean repository) {
		this.repository = repository;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
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

	public Long getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(Long buyerID) {
		this.buyerID = buyerID;
	}

	public String getAssignUser() {
		return assignUser;
	}

	public void setAssignUser(String assignUser) {
		this.assignUser = assignUser;
	}

	public Double getReleaseDot() {
		return releaseDot;
	}

	public void setReleaseDot(Double releaseDot) {
		this.releaseDot = releaseDot;
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

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFbShopURL() {
		return fbShopURL;
	}

	public void setFbShopURL(String fbShopURL) {
		this.fbShopURL = fbShopURL;
	}

	public String getFbQQ() {
		return fbQQ;
	}

	public void setFbQQ(String fbQQ) {
		this.fbQQ = fbQQ;
	}

	public String getFbUsername() {
		return fbUsername;
	}

	public void setFbUsername(String fbUsername) {
		this.fbUsername = fbUsername;
	}

	public String getJsWW() {
		return jsWW;
	}

	public void setJsWW(String jsWW) {
		this.jsWW = jsWW;
	}

	public String getFbWW() {
		return fbWW;
	}

	public void setFbWW(String fbWW) {
		this.fbWW = fbWW;
	}

	public String getJsTelphone() {
		return jsTelphone;
	}

	public void setJsTelphone(String jsTelphone) {
		this.jsTelphone = jsTelphone;
	}

	public String getFbTelphone() {
		return fbTelphone;
	}

	public void setFbTelphone(String fbTelphone) {
		this.fbTelphone = fbTelphone;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Integer getJsUpgradeScore() {
		return jsUpgradeScore;
	}

	public void setJsUpgradeScore(Integer jsUpgradeScore) {
		this.jsUpgradeScore = jsUpgradeScore;
	}

	public Integer getFbUpgradeScore() {
		return fbUpgradeScore;
	}

	public void setFbUpgradeScore(Integer fbUpgradeScore) {
		this.fbUpgradeScore = fbUpgradeScore;
	}

	public String getJsVipType() {
		return jsVipType;
	}

	public void setJsVipType(String jsVipType) {
		this.jsVipType = jsVipType;
	}

	public String getFbVipType() {
		return fbVipType;
	}

	public void setFbVipType(String fbVipType) {
		this.fbVipType = fbVipType;
	}

	public Date getFbStartDate() {
		return fbStartDate;
	}

	public void setFbStartDate(Date fbStartDate) {
		this.fbStartDate = fbStartDate;
	}

	public Date getFbEndDate() {
		return fbEndDate;
	}

	public void setFbEndDate(Date fbEndDate) {
		this.fbEndDate = fbEndDate;
	}

	public Date getJsStartDate() {
		return jsStartDate;
	}

	public void setJsStartDate(Date jsStartDate) {
		this.jsStartDate = jsStartDate;
	}

	public Date getJsEndDate() {
		return jsEndDate;
	}

	public void setJsEndDate(Date jsEndDate) {
		this.jsEndDate = jsEndDate;
	}

}
