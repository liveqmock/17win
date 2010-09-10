package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 县 区
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "Tb_Area")
public class AreaEntity extends BaseEntity {
	// 名字
	@Column(name = "NAME_", length = 20, nullable = false)
	private String name;
	// 城市
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID_", nullable = false)
	private CityEntity city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}
}
