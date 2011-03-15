package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tb_Seller")
/**
 * 卖家信息
 */
public class SellerEntity extends BaseEntity {
	// 名字
	@Column(name = "NAME_", length = 20, nullable = false)
	private String name;
	// 类型(1淘宝,2怕拍,3有啊)
	@Column(name = "TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;
	// 店铺地址
	@Column(name = "SHOPURL_", length = 255, nullable = false)
	private String shopURL;
	// 发货地址
	@Column(name = "Address_", length = 53)
	private String address;
	// 所属人
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	private UserEntity user;
	//是否消保
	private Boolean isEnsure = false;
	//信誉积分
	private Integer sellerScore = 0;

	public Integer getSellerScore() {
		return sellerScore;
	}
	public void setSellerScore(Integer sellerScore) {
		this.sellerScore = sellerScore;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShopURL() {
		return shopURL;
	}

	public void setShopURL(String shopURL) {
		this.shopURL = shopURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getIsEnsure() {
		return isEnsure;
	}
	public void setIsEnsure(Boolean isEnsure) {
		this.isEnsure = isEnsure;
	}

}
