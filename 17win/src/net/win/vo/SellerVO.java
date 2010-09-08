package net.win.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.win.BaseVO;
import net.win.entity.AreaEntity;
import net.win.entity.BuyerEntity;
import net.win.entity.CityEntity;
import net.win.entity.ProvinceEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;

public class SellerVO extends BaseVO {
	// 名字
	private String name;
	// 类型(1淘宝,2怕拍,3有啊)
	private String type;
	// 店铺地址
	private String shopURL;

	// 省 ID
	private Long provinceID;
	// 市 ID
	private Long cityID;
	// 县 ID
	private Long areaID;
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
	public Long getProvinceID() {
		return provinceID;
	}
	public void setProvinceID(Long provinceID) {
		this.provinceID = provinceID;
	}
	public Long getCityID() {
		return cityID;
	}
	public void setCityID(Long cityID) {
		this.cityID = cityID;
	}
	public Long getAreaID() {
		return areaID;
	}
	public void setAreaID(Long areaID) {
		this.areaID = areaID;
	}
}
