package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_SELLER")
/**
 * 卖家信息
 */
public class SellerEntity extends BaseEntity {
	// 名字
	@Column(name = "SELLER_", length = 20, nullable = false)
	private String name;
	// 类型(1淘宝,2怕拍,3有啊)
	@Column(name = "TYPE_", columnDefinition = "CHAR(1)", nullable = false)
	private String type;
	// 店铺地址
	@Column(name = "SHOPURL_", length = 50, unique = true, nullable = false)
	private String shopURL;
	
	// 省
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RPOVINCE_ID_")
	private ProvinceEntity province;
	// 市
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID_")
	private CityEntity city;
	// 县
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID_")
	private AreaEntity area;
	public ProvinceEntity getProvince() {
		return province;
	}

	public void setProvince(ProvinceEntity province) {
		this.province = province;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public AreaEntity getArea() {
		return area;
	}

	public void setArea(AreaEntity area) {
		this.area = area;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShopURL() {
		return shopURL;
	}

	public void setShopURL(String shopURL) {
		this.shopURL = shopURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
