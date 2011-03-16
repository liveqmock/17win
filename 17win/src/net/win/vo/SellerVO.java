package net.win.vo;

import javax.persistence.Column;

import net.win.BaseVO;

public class SellerVO extends BaseVO {
	// 名字
	private String name;
	// 类型(1淘宝,2怕拍,3有啊)
	private String type;
	// 店铺地址
	private String shopURL;

	private String address;

	//是否消保
	private Boolean ensure = false;
	//图片地址
	private String img = "";
	//是否旺铺
	private Boolean winport = false;
	//信誉值
	private Integer score = 0;
	//信誉地址
	private String creditURL;

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

	public Boolean getEnsure() {
		return ensure;
	}

	public void setEnsure(Boolean ensure) {
		this.ensure = ensure;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Boolean getWinport() {
		return winport;
	}

	public void setWinport(Boolean winport) {
		this.winport = winport;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getCreditURL() {
		return creditURL;
	}

	public void setCreditURL(String creditURL) {
		this.creditURL = creditURL;
	}
}
