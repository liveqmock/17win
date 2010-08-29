package net.win.action.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.dao.AreaDAO;
import net.win.dao.CityDAO;
import net.win.dao.ProvinceDAO;
import net.win.entity.AreaEntity;
import net.win.entity.CityEntity;
import net.win.entity.ProvinceEntity;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sun.org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings( { "serial", "unused", "unchecked" })
@Controller
@ParentPackage("17win-default")
@Scope("prototype")
@Namespace("/ajaxManager")
public class AjaxAction extends BaseAction {
	@Resource
	private ProvinceDAO provinceDAO;
	@Resource
	private CityDAO cityDAO;
	@Resource
	private AreaDAO areaDAO;

	/**
	 * 接受的数据
	 */
	// 省市联动的标识 1省查市，2市查县
	private String addressType;
	// 省市联动的ID
	private Long addressID;

	/**
	 * 返回的数据
	 * 
	 * @return
	 */
	private List<Address> cityList = new ArrayList<Address>();
	private List<Address> areaList = new ArrayList<Address>();

	@Action("/ajax")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Action("/address")
	public String address() throws Exception {
		Address address;
		if ("1".equals(addressType)) {
			ProvinceEntity provinceEntity = provinceDAO.load(addressID);
			List<CityEntity> cities = provinceEntity.getCities();
			for (CityEntity cityEntity : cities) {
				address = new Address();
				BeanUtils.copyProperties(address, cityEntity);
				cityList.add(address);
			}
			// List<AreaEntity> areas = cities.get(0).getAreas();
			// for (AreaEntity areaEntity : areas) {
			// address = new Address();
			// BeanUtils.copyProperties(address, areaEntity);
			// areaList.add(address);
			//			}
		} else {
			CityEntity cityEntity = cityDAO.load(addressID);
			List<AreaEntity> areas = cityEntity.getAreas();
			for (AreaEntity areaEntity : areas) {
				address = new Address();
				BeanUtils.copyProperties(address, areaEntity);
				areaList.add(address);
			}
		}
		return JSON;
	}

	public List<Address> getCityList() {
		return cityList;
	}

	public List<Address> getAreaList() {
		return areaList;
	}

	public void setAddressID(Long addressID) {
		this.addressID = addressID;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 * 省市县联动返回数据
	 * 
	 * @author xgj
	 * 
	 */
	public class Address {
		private Long id;
		private String name;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
