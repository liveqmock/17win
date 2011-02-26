package net.win.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.win.BaseVO;

@Entity
@Table(name = "Tb_TaskLinkMan")
/**
 * 任务常用联系人
 */
public class TaskLinkManVo extends BaseVO {
	// 用户名
	private String username;
	// 最后一次使用日期

	private Date lastUseTime;
	// 使用次数

	private Integer useCount;

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

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

}
