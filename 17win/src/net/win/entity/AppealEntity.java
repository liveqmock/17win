package net.win.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 申诉
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_APPEAL")
public class AppealEntity {
	// 标题
	@Column(name = "TITLE_", length = 25, nullable = false)
	private String title;

	// 申诉人
	private UserEntity appleUser;

	// 被申诉人
	private UserEntity appealedUser;

	// 申述类型(1 信誉 2 流量 3 收藏)
	private String type;

	// 任务ID
	private String taskID;

	// 描述
	private String desc;

	// 申诉时间
	private Date appealDate;

	// 官方意见
	private String officIdea;
	
	
	//状态
	private String status;

	// 辩解
	private Set<JustifyEntity> justifies = new HashSet<JustifyEntity>();


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserEntity getAppleUser() {
		return appleUser;
	}

	public void setAppleUser(UserEntity appleUser) {
		this.appleUser = appleUser;
	}

	public UserEntity getAppealedUser() {
		return appealedUser;
	}

	public void setAppealedUser(UserEntity appealedUser) {
		this.appealedUser = appealedUser;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getAppealDate() {
		return appealDate;
	}

	public void setAppealDate(Date appealDate) {
		this.appealDate = appealDate;
	}

	public String getOfficIdea() {
		return officIdea;
	}

	public void setOfficIdea(String officIdea) {
		this.officIdea = officIdea;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<JustifyEntity> getJustifies() {
		return justifies;
	}

	public void setJustifies(Set<JustifyEntity> justifies) {
		this.justifies = justifies;
	}
}
