package net.win.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class ProvinceEntity extends BaseEntity {
	// 名字
	@Column(name = "NAME_", length = 20, nullable = false)
	private String name;
	// 城市
	@OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
	private Set<CityEntity> cities = new HashSet<CityEntity>();
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CityEntity> getCities() {
		return cities;
	}

	public void setCities(Set<CityEntity> cities) {
		this.cities = cities;
	}

}
