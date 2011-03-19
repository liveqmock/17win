package net.win.vo;

import net.win.BaseVO;
import net.win.entity.BuyerEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;

public class UserVO extends BaseVO {

	/**
	 * 接手数据
	 */
	// 用户信息
	private UserEntity userEntity = new UserEntity();
	// 验证码
	private String verificationCode;
	//用户类型
	private String type;
	// 操作码
	private String operationCode;
	// 赠送数量
	private Double number;
	// 用户名
	private String username;

	//电话
	private String telphone;
	private SellerEntity seller = new SellerEntity();

	private BuyerEntity buyer = new BuyerEntity();

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
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

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
