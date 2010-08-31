package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 拍拍用户信息
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_PAIPAIUSER")
public class PaipaiUserEntity extends BaseEntity {
	// 卖家号
	// 用户名
	@Column(name = "SELLER_", length = 20, unique = true, nullable = false)
	private String seller;
	// 购买号
	@Column(name = "BUYER_", length = 20, unique = true, nullable = false)
	private String buyer;
	// 店铺地址
	@Column(name = "SHOPURL_", length = 50, unique = true, nullable = false)
	private String shopURL;
	// 用户
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	private UserEntity user;
	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getShopURL() {
		return shopURL;
	}

	public void setShopURL(String shopURL) {
		this.shopURL = shopURL;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
