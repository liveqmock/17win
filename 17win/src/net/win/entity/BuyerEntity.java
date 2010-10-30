package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tb_Buyer")
/**
 * 买家信息
 */
// 名字
public class BuyerEntity extends BaseEntity {

	@Column(name = "NAME_", length = 20, nullable = false)
	private String name;
	// 类型(1淘宝,2怕拍,3有啊)
	@Column(name = "TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;

	@Column(name = "ENABLE_", nullable = false)
	private Boolean enable;

	// 所属人
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	private UserEntity user;

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
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
}
