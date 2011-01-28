package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Tb_TaskLinkMan")
/**
 * 任务常用联系人
 */
public class TaskLinkManEntity extends BaseEntity {
	// 用户名
	@Column(name = "USERNAME_", length = 12, unique = true, nullable = false)
	private String username;
	// 最后一次使用日期
	@Column(name = "LastUseTime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUseTime;
	// 所属人
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "USER_ID_")
	private UserEntity user;
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getLastUseTime() {
		return lastUseTime;
	}
	public void setLastUseTime(Date lastUseTime) {
		this.lastUseTime = lastUseTime;
	}
}
