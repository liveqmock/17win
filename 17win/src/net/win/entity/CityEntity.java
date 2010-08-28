package net.win.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

@Entity
@Table(name = "TB_CITY")
public class CityEntity extends BaseEntity {
	// 名字
	@Column(name = "NAME_", length = 20, nullable = false)
	private String name;
	// 省
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVINCE_ID_", nullable = false)
	private ProvinceEntity province;

	// 县区
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	private Set<PrefectureEntity> prefectures = new HashSet<PrefectureEntity>();

	public ProvinceEntity getProvince() {
		return province;
	}

	public void setProvince(ProvinceEntity province) {
		this.province = province;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PrefectureEntity> getPrefectures() {
		return prefectures;
	}

	public void setPrefectures(Set<PrefectureEntity> prefectures) {
		this.prefectures = prefectures;
	}
}
