package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 淘宝用户信息
 * @author xgj
 *
 */
@Entity
@Table(name = "TB_TAOBAOUSER")
public class TaobaoUserEntity extends BaseEntity {
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

}
