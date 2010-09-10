package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Tb_TaskAddress")
public class TaskAddressEntity extends BaseEntity {
	// 名字
	@Column(name = "ADDRESS_", nullable = false)
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
