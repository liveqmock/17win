package net.win.vo;

import java.util.ArrayList;
import java.util.List;

import net.win.BaseVO;
import net.win.entity.AreaEntity;
import net.win.entity.BuyerEntity;
import net.win.entity.CityEntity;
import net.win.entity.ProvinceEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;

public class UserVO extends BaseVO {

	/**
	 * 接受数据
	 */
	// 用户信息
	private UserEntity userEntity = new UserEntity();
	// 验证码
	private String verificationCode;
	// 操作码
	private String operationCode;
	// 发布点
	private Double releaseDot;
	// 赠送数量
	private Double number;
	// 用户名
	private String username;
	/**
	 * 显示数据
	 */
	private List<ProvinceEntity> provinces = new ArrayList<ProvinceEntity>();
	private List<CityEntity> cities = new ArrayList<CityEntity>();
	private List<AreaEntity> areas = new ArrayList<AreaEntity>();

	private SellerEntity seller =new  SellerEntity();

	private BuyerEntity buyer =new  BuyerEntity();

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public List<ProvinceEntity> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<ProvinceEntity> provinces) {
		this.provinces = provinces;
	}

	public List<CityEntity> getCities() {
		return cities;
	}

	public void setCities(List<CityEntity> cities) {
		this.cities = cities;
	}

	public List<AreaEntity> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaEntity> areas) {
		this.areas = areas;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public Double getReleaseDot() {
		return releaseDot;
	}

	public void setReleaseDot(Double releaseDot) {
		this.releaseDot = releaseDot;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SellerEntity getSeller() {
		return seller;
	}

	public void setSeller(SellerEntity seller) {
		this.seller = seller;
	}

	public BuyerEntity getBuyer() {
		return buyer;
	}

	public void setBuyer(BuyerEntity buyer) {
		this.buyer = buyer;
	}

}
