package net.win.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体交易，真实的信息描述
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_RECEIVEREALINFO")
public class ReceiveRealInfoEntity {
	// 地址
	private String address;
	// 邮编
	private String zip;
	// 电话
	private String telephone;
	// 接收人的名字
	private String receiver;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

}
