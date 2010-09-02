package net.win.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 角色 ,VIP
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_ROLE")
public class VipEntity extends BaseEntity {
	// 名字
	@Column(name = "NAME_", length = 20, nullable = false)
	private String name;
	// 权限
	private Set<RightsEntity> rightses = new HashSet<RightsEntity>(0);
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<RightsEntity> getRightses() {
		return rightses;
	}
	public void setRightses(Set<RightsEntity> rightses) {
		this.rightses = rightses;
	}
}
