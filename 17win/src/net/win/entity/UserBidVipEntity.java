package net.win.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 用户VIP 关系表
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "Tb_UserBidVip")
public class UserBidVipEntity extends BaseEntity {
	// 用户
	@OneToMany(targetEntity = UserEntity.class, mappedBy = "userBidVip", fetch = FetchType.LAZY)
	private List<UserEntity> user;

	// 用户
	@OneToMany(targetEntity = VipEntity.class, mappedBy = "userBidVip", fetch = FetchType.LAZY)
	private List<VipEntity> vip;

	// 发任务数
	@Column(name = "Release_Count_", nullable = false)
	private Integer releaseCount = 0;
	// 接任务数
	@Column(name = "Receieve_Count_", nullable = false)
	private Integer receieveCount = 0;

	public List<UserEntity> getUser() {
		return user;
	}

	public void setUser(List<UserEntity> user) {
		this.user = user;
	}

	public List<VipEntity> getVip() {
		return vip;
	}

	public void setVip(List<VipEntity> vip) {
		this.vip = vip;
	}

	public Integer getReleaseCount() {
		return releaseCount;
	}

	public void setReleaseCount(Integer releaseCount) {
		this.releaseCount = releaseCount;
	}

	public Integer getReceieveCount() {
		return receieveCount;
	}

	public void setReceieveCount(Integer receieveCount) {
		this.receieveCount = receieveCount;
	}
}
