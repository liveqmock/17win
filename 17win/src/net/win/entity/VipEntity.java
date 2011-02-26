package net.win.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	// 类型 1 vip1 2 vip2 3vip3
	@Column(name = "Type_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;

	// // 积分400 800 1000 每月送积分
	// @Column(name = "presentDot", nullable = false)
	// private Double presentDot;

	// 人员
	@OneToMany(targetEntity = UserEntity.class, mappedBy = "vip", fetch = FetchType.LAZY)
	private List<UserEntity> users;

	// 卖号上线数 -1 没有限制
	@Column(name = "Seller_Count_", nullable = false)
	private Integer sellerCount = 0;

 

	// 手机短信数量
	@Column(name = "Phone_Msg_", nullable = false)
	private Integer phoneMsg = 0;

	// 发任务数 发任务所得的成长值
	@Column(name = "Release_GrowValue_", nullable = false)
	private Integer releaseGrowValue = 0;
	// 接任务数 接手任务所得的成长值
	@Column(name = "Receieve_GrowValue_", nullable = false)
	private Integer receieveGrowValue = 0;

	// 发任务积分数
	@Column(name = "Release_Score_", nullable = false)
	private Integer releaseScore = 0;
	// 接任务积分数
	@Column(name = "Receieve_Score_", nullable = false)
	private Integer receieveScore = 0;

	// 登录送积分
	@Column(name = "Login_Score", nullable = false)
	private Integer loginScore = 0;
	// 登录送成长值
	@Column(name = "Login_GrowValue", nullable = false)
	private Integer loginGrowValue = 0;

	public Integer getReleaseGrowValue() {
		return releaseGrowValue;
	}

	public void setReleaseGrowValue(Integer releaseGrowValue) {
		this.releaseGrowValue = releaseGrowValue;
	}

	public Integer getReceieveGrowValue() {
		return receieveGrowValue;
	}

	public void setReceieveGrowValue(Integer receieveGrowValue) {
		this.receieveGrowValue = receieveGrowValue;
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

	public Integer getLoginScore() {
		return loginScore;
	}

	public void setLoginScore(Integer loginScore) {
		this.loginScore = loginScore;
	}

	public Integer getLoginGrowValue() {
		return loginGrowValue;
	}

	public void setLoginGrowValue(Integer loginGrowValue) {
		this.loginGrowValue = loginGrowValue;
	}

}
