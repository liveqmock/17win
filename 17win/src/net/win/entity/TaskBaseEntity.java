package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TaskBaseEntity extends BaseEntity {
	// 任务ID 给用户看 格式yyymmddhhmmssis
	@Column(name = "TESTID_", columnDefinition = "CHAR(16)", nullable = false, unique = true)
	private String testID;
	// 接收人IP
	@Column(name = "RECEIVE_IP_", length = 19, nullable = false)
	private String receiveIP;
	// 发布点
	@Column(name = "RELEASE_DOT_", nullable = false)
	private Float releaseDot;
	// 接手时间
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;
	// 发布人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "RELEASE_PERSON_")
	private UserEntity releasePerson;
	// 接收人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "RECEIVE_PERSON_")
	private UserEntity receivePerson;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public UserEntity getReleasePerson() {
		return releasePerson;
	}

	public void setReleasePerson(UserEntity releasePerson) {
		this.releasePerson = releasePerson;
	}

	public UserEntity getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(UserEntity receivePerson) {
		this.receivePerson = receivePerson;
	}

}