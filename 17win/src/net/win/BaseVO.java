package net.win;

public class BaseVO {
	// 分页信息
	private Integer nowpage = 1;
	private Integer eachPage = 20;
	private Integer pageCount = 0;
	private Integer dataCount = 0;

	public Integer getStart() {
		return (nowpage - 1) * eachPage;
	}

	public Integer getLimit() {
		return eachPage;
	}

	public Integer getNowpage() {
		return nowpage;
	}

	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}

	public Integer getEachPage() {
		return eachPage;
	}

	public void setEachPage(Integer eachPage) {
		this.eachPage = eachPage;
	}

	public Integer getPageCount() {
		pageCount = (dataCount + eachPage) / eachPage;
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
}
