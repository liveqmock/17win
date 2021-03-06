package net.win;

public class BaseVO {
	// 分页信息
	private Integer nowPage = 1;
	private Integer eachPage = 10;
	private Integer pageCount = 0;
	private Integer dataCount = 0;
	private Long id;

	private Boolean checkFlag = true;

	// ajax调用
	private String executeFlag = "";
	private String message = "";

	public Boolean getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStart() {
		return (nowPage - 1) * eachPage;
	}

	public Integer getLimit() {
		return eachPage;
	}

	public Integer getNowPage() {
		return nowPage;
	}

	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}

	public Integer getEachPage() {
		return eachPage;
	}

	public void setEachPage(Integer eachPage) {
		this.eachPage = eachPage;
	}

	public Integer getPageCount() {
		if (dataCount == 0) {
			return 1;
		}
		pageCount = (dataCount + eachPage - 1) / eachPage;
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getDataCount() {
		return dataCount;
	}

	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}

	public String getExecuteFlag() {
		return executeFlag;
	}

	public void setExecuteFlag(String executeFlag) {
		this.executeFlag = executeFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
