package net.win.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * VIP信息
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "Tb_VipBidUser")
public class VipBidUserEntity extends BaseEntity {
	// 剩余短信数
	@Column(name = "Remain_Msg_Count_", nullable = false)
	private Integer remainMsgCount = 0;
	// VIP结束时间
	@Column(name = "End_Date_", nullable = false)
	private Date endDate;

	// 当前的Vip成长值
	@Column(name = "Grow_Value_", nullable = false)
	private Integer growValue = 0;

	// VIP信息
	@OneToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	private UserEntity user;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getRemainMsgCount() {
		return remainMsgCount;
	}

	public void setRemainMsgCount(Integer remainMsgCount) {
		this.remainMsgCount = remainMsgCount;
	}

	public Integer getGrowValue() {
		return growValue;
	}

	public void setGrowValue(Integer growValue) {
		this.growValue = growValue;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
