package net.win.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	// 申述类型(1 信誉 2 流量 3 收藏)
	@Column(name = "TYPE_", columnDefinition = "char(1)", nullable = false)
	private String type;

	// 任务ID
	@Column(name = "TASKID_", columnDefinition = "char(16)", nullable = false)
	private String taskID;

	// 描述
	@Column(name = "DESC_", length = 500, nullable = false)
	private String desc;

	// 申诉时间
	@Column(name = "APPEAL_DATE_", nullable = false)
	private Date appealDate;

	// 状态(1 申请中 2 受理中 3 无效 4成功)
	@Column(name = "STATUS_", columnDefinition = "char(1)", nullable = false)
	private String status;
	// 官方意见
	@Column(name = "OFFIC_IDEA_")
	private String officIdea;

	// 图片位置
	@Column(name = "IMAGE_", columnDefinition = "char(13)")
	private String image;

	// 申诉人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "APPEAL_USER_ID_")
	private UserEntity appealUser;

	// 被申诉人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "APPEALED_USER_ID_")
	private UserEntity appealedUser;

	// 辩解 暂时不做
	// private Set<JustifyEntity> justifies = new HashSet<JustifyEntity>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserEntity getAppealUser() {
		return appealUser;
	}

	public void setAppealUser(UserEntity appealUser) {
		this.appealUser = appealUser;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
