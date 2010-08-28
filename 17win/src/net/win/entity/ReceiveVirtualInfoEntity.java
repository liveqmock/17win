package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体交易，虚拟账号的信息描述
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_RECEIVEVIRTUALINFO")
public class ReceiveVirtualInfoEntity extends BaseEntity {

	// QQ号
	@Column(name = "QQ_", length = 11)
	private String qq;
	// 电话
	@Column(name = "TELEPHONE_", length = 11)
	private String telephone;
	// 游戏选取
	@Column(name = "ADDRESS_", length = 20)
	private String address;
	// 游戏账号
	@Column(name = "ADDRESS_", length = 10)
	private String account;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
