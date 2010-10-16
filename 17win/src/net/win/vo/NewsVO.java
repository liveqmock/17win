package net.win.vo;

import java.util.Date;

import net.win.BaseVO;

public class NewsVO extends BaseVO {
	private NewsVO prevNews;
	private NewsVO nextNews;
	// 标题
	private String title;
	// 内容
	private String content;
	// URL
	private String url;
	// 发布时间
	private Date date;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public NewsVO getPrevNews() {
		return prevNews;
	}

	public void setPrevNews(NewsVO prevNews) {
		this.prevNews = prevNews;
	}

	public NewsVO getNextNews() {
		return nextNews;
	}

	public void setNextNews(NewsVO nextNews) {
		this.nextNews = nextNews;
	}
}
