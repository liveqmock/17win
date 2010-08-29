package net.win.vo;

import java.util.List;

import net.win.BaseVO;
import net.win.entity.AreaEntity;
import net.win.entity.CityEntity;
import net.win.entity.ProvinceEntity;
import net.win.entity.UserEntity;

public class CommonVO extends BaseVO {
	/**
	 * 接受数据
	 */
	// 用户信息
	private UserEntity userEntity = new UserEntity();
	// 验证码
	private String verificationCode;
	/**
	 * 显示数据
	 */
	private List<ProvinceEntity> provinces;
	private List<CityEntity> cities;
	private List<AreaEntity> areas;

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

}
