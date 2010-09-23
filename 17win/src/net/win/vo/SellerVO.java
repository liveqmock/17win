package net.win.vo;

import net.win.BaseVO;

public class SellerVO extends BaseVO {
	// 名字
	private String name;
	// 类型(1淘宝,2怕拍,3有啊)
	private String type;
	// 店铺地址
	private String shopURL;

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
