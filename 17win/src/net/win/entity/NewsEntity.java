package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tb_News")
public class NewsEntity extends BaseEntity {
	// 标题
	@Column(name = "Title_", length = 50, nullable = false)
	private String title;
	// 内容
	@Column(name = "Content_", columnDefinition = "text", nullable = false)
	private String content;

	// 是否置顶
	@Column(name = "TOP_", nullable = false)
	private Boolean top;
	// URL
	@Column(name = "Url_", unique = true)
	private String url;

	// 修改时间
	@Column(name = "date_", nullable = false)
	private Date date;

	// 修改时间
	@Column(name = "page_date_", nullable = true)
	private Date pageDate;

	// 充值人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = NewsTypeEntity.class)
	@JoinColumn(name = "Type_ID_")
	private NewsTypeEntity type;

	public NewsTypeEntity getType() {
		return type;
	}

	public void setType(NewsTypeEntity type) {
		this.type = type;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getPageDate() {
		return pageDate;
	}

	public void setPageDate(Date pageDate) {
		this.pageDate = pageDate;
	}

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}

}
