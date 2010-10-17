package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Tb_News")
public class NewsEntity extends BaseEntity {
	// 标题
	@Column(name = "type_", length = 50, nullable = false)
	private String title;
	// 内容
	@Column(name = "name_", length = 20, nullable = false)
	private String content;
	// URL
	@Column(name = "url_")
	private String url;

	// 发布时间
	@Column(name = "date_", columnDefinition = "text", nullable = false)
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

}
