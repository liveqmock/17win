package net.win.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 角色 ,VIP
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "Tb_Vip")
public class VipEntity extends BaseEntity {
	// 类型
	@Column(name = "Type_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;

	// // 积分400 800 1000 每月送积分
	// @Column(name = "presentDot", nullable = false)
	// private Double presentDot;

	// 人员
	@OneToMany(targetEntity = UserEntity.class, mappedBy = "vip", fetch = FetchType.LAZY)
	private List<UserEntity> users;

	// 卖号上线数
	@Column(name = "Seller_Count_", nullable = false)
	private Integer sellerCount = 0;

	// 会员成长值
	@Column(name = "Grow_Value_", nullable = false)
	private Integer growValue = 0;

	// 手机短信
	@Column(name = "Phone_Msg_", nullable = false)
	private Integer phoneMsg = 0;

	// 发任务数
	@Column(name = "Release_Count_", nullable = false)
	private Integer releaseCount = 0;
	// 接任务数
	@Column(name = "Receieve_Count_", nullable = false)
	private Integer receieveCount = 0;

	// 发任务积分数
	@Column(name = "Release_Score_", nullable = false)
	private Integer releaseScore = 0;
	// 接任务几分熟
	@Column(name = "Receieve_Score_", nullable = false)
	private Integer receieveScore = 0;

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

	public Integer getReleaseScore() {
		return releaseScore;
	}

	public void setReleaseScore(Integer releaseScore) {
		this.releaseScore = releaseScore;
	}

	public Integer getReceieveScore() {
		return receieveScore;
	}

	public void setReceieveScore(Integer receieveScore) {
		this.receieveScore = receieveScore;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSellerCount() {
		return sellerCount;
	}

	// public Integer getScore() {
	// return score;
	// }
	//
	// public void setScore(Integer score) {
	// this.score = score;
	//	}

	public Integer getGrowValue() {
		return growValue;
	}

	public void setGrowValue(Integer growValue) {
		this.growValue = growValue;
	}

	public Integer getPhoneMsg() {
		return phoneMsg;
	}

	public void setPhoneMsg(Integer phoneMsg) {
		this.phoneMsg = phoneMsg;
	}

	public void setSellerCount(Integer sellerCount) {
		this.sellerCount = sellerCount;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

}
