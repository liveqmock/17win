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

	private String fromUserName;

	private Boolean read;
	
	private String  queryTypeFlag;
	
	private Long[] fjSmsIDs;
	
	private Long[] sjSmsIDs;
	// 参数
	private Date startDate;
	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

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

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getQueryTypeFlag() {
		return queryTypeFlag;
	}

	public void setQueryTypeFlag(String queryTypeFlag) {
		this.queryTypeFlag = queryTypeFlag;
	}

	public Long[] getFjSmsIDs() {
		return fjSmsIDs;
	}

	public void setFjSmsIDs(Long[] fjSmsIDs) {
		this.fjSmsIDs = fjSmsIDs;
	}

	public Long[] getSjSmsIDs() {
		return sjSmsIDs;
	}

	public void setSjSmsIDs(Long[] sjSmsIDs) {
		this.sjSmsIDs = sjSmsIDs;
	}

	 
}
