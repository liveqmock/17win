package net.win.vo;

import java.util.ArrayList;
import java.util.List;

import net.win.BaseVO;
import net.win.entity.AreaEntity;
import net.win.entity.CityEntity;
import net.win.entity.ProvinceEntity;
import net.win.entity.UserEntity;

public class UserVO extends BaseVO {
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
	private List<ProvinceEntity> provinces = new ArrayList<ProvinceEntity>();
	private List<CityEntity> cities = new ArrayList<CityEntity>();
	private List<AreaEntity> areas = new ArrayList<AreaEntity>();
	

	/*
	 * 买家操作
	 */
	// 等我付款
	private Integer paiedMeCount=0;
	// 等卖家发货
	private Integer dispatch=0;
	// 等我好评
	private Integer evaluatedCount=0;
	/*
	 * 卖家操作
	 */
	// 等待接手
	private Integer acceptCount=0;
	// 等等待审核
	private Integer verifyCount=0;
	// 等待我发货
	private Integer dispatchedCount=0;
	// 等待买家确认
	private Integer affirmCount=0;
	// 等待我核查好评
	private Integer checkEcaluateCount=0;
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

	public Integer getPaiedMeCount() {
		return paiedMeCount;
	}

	public void setPaiedMeCount(Integer paiedMeCount) {
		this.paiedMeCount = paiedMeCount;
	}

	public Integer getDispatch() {
		return dispatch;
	}

	public void setDispatch(Integer dispatch) {
		this.dispatch = dispatch;
	}

	public Integer getEvaluatedCount() {
		return evaluatedCount;
	}

	public void setEvaluatedCount(Integer evaluatedCount) {
		this.evaluatedCount = evaluatedCount;
	}

	public Integer getAcceptCount() {
		return acceptCount;
	}

	public void setAcceptCount(Integer acceptCount) {
		this.acceptCount = acceptCount;
	}

	public Integer getVerifyCount() {
		return verifyCount;
	}

	public void setVerifyCount(Integer verifyCount) {
		this.verifyCount = verifyCount;
	}

	public Integer getDispatchedCount() {
		return dispatchedCount;
	}

	public void setDispatchedCount(Integer dispatchedCount) {
		this.dispatchedCount = dispatchedCount;
	}

	public Integer getAffirmCount() {
		return affirmCount;
	}

	public void setAffirmCount(Integer affirmCount) {
		this.affirmCount = affirmCount;
	}

	public Integer getCheckEcaluateCount() {
		return checkEcaluateCount;
	}

	public void setCheckEcaluateCount(Integer checkEcaluateCount) {
		this.checkEcaluateCount = checkEcaluateCount;
	}

}
