package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 管理员
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "Tb_property")
public class PropertyEntity extends BaseEntity {
	/**
	 * 基本信息
	 */
	@Column(name = "NAME_", length = 255, unique = true, nullable = false)
	private String name;
	@Column(name = "NUMBERVALUE_")
	private Double numberValue;
	@Column(name = "STRINGVALUE_", length = 255)
	private String stringValue;
	@Column(name = "DESC_", length = 255)
	private String desc;

	public Double getNumberValue() {
		return numberValue;
	}

	public void setNumberValue(Double numberValue) {
		this.numberValue = numberValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 
}
