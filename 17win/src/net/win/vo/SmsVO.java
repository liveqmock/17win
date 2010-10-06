package net.win.vo;

import java.util.Date;

import net.win.BaseVO;

public class SmsVO extends BaseVO {
	// 类别， 1 系统 2 用户
	private String type;
	// 标题
	private String title;
	//
	private String content;

	private Date sendDate;

	private String toUserName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
}
