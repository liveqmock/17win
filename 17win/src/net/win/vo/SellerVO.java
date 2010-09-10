package net.win.vo;

import java.util.List;

import net.win.BaseVO;
import net.win.entity.AreaEntity;
import net.win.entity.CityEntity;
import net.win.entity.ProvinceEntity;

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

	List<ProvinceEntity> provinces;

	List<CityEntity> citys;

	List<AreaEntity> areas;

	public List<ProvinceEntity> getProvinces() {
		return provinces;
	}
	public void setProvinces(List<ProvinceEntity> provinces) {
		this.provinces = provinces;
	}
	public List<CityEntity> getCitys() {
		return citys;
	}
	public void setCitys(List<CityEntity> citys) {
		this.citys = citys;
	}
	public List<AreaEntity> getAreas() {
		return areas;
	}
	public void setAreas(List<AreaEntity> areas) {
		this.areas = areas;
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
