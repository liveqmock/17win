package net.win.vo;

import net.win.BaseVO;

public class BuyerVO extends BaseVO {
	private String name;
	// 类型(1淘宝,2怕拍,3有啊)
	private String type;
	// 积分
	private Integer score = 0;

	private Boolean enable;

	private String creditURL;

	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCreditURL() {
		return creditURL;
	}

	public void setCreditURL(String creditURL) {
		this.creditURL = creditURL;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
