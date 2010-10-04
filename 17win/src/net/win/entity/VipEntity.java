package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	// 卖号个数
	@Column(name = "Seller_Count_", nullable = false)
	private String sellerCount;

	// 积分400 800 1000 每月送积分
	@Column(name = "Score_", nullable = false)
	private Integer score;

	// VIP
	@ManyToOne(targetEntity = UserBidVipEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "Vip_id_")
	private UserBidVipEntity userBidVip;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSellerCount() {
		return sellerCount;
	}

	public void setSellerCount(String sellerCount) {
		this.sellerCount = sellerCount;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public UserBidVipEntity getUserBidVip() {
		return userBidVip;
	}

	public void setUserBidVip(UserBidVipEntity userBidVip) {
		this.userBidVip = userBidVip;
	}

}
