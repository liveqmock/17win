package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 权限
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_RIGHTS")
public class RightsEntity extends BaseEntity {
	// 名字
	@Column(name = "NAME_", length = 20, nullable = false)
	private String name;
	// 功能标识
	@Column(name = "IDENTITY_", length = 20, nullable = false)
	private String identity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}

}
