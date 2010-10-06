package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 站内信
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_Sms")
public class SmsEntity extends BaseEntity {
	// 类别， 1 系统 2 用户
	@Column(name = "Type_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;
	// 所属人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "From_User_ID_")
	private UserEntity fromUser;
	// 所属人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "To_User_ID_")
	private UserEntity toUser;
	// 标题
	@Column(name = "Title_", length = 50, nullable = false)
	private String title;
	@Column(name = "Content_", length = 200, nullable = false)
	private String content;
	// 发送时间
	@Column(name = "Send_Date_", nullable = false)
	private Date sendDate;

	public UserEntity getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserEntity fromUser) {
		this.fromUser = fromUser;
	}

	public UserEntity getToUser() {
		return toUser;
	}

	public void setToUser(UserEntity toUser) {
		this.toUser = toUser;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
}
