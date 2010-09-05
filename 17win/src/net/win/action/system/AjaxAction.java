package net.win.action.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.win.BaseAction;
import net.win.dao.AreaDAO;
import net.win.dao.CityDAO;
import net.win.dao.ProvinceDAO;
import net.win.dao.UserDAO;
import net.win.entity.AreaEntity;
import net.win.entity.CityEntity;
import net.win.entity.ProvinceEntity;
import net.win.entity.UserEntity;
import net.win.service.system.AjaxService;
import net.win.utils.MailUtils;
import net.win.utils.HttpB2CUtils;
import net.win.utils.StringUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpServerConnection;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import sun.misc.BASE64Encoder;

import com.sun.org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings( { "serial", "unused", "unchecked" })
@Controller
@ParentPackage("17win-default")
@Scope("prototype")
@Namespace("/ajaxManager")
public class AjaxAction extends BaseAction {
	@Resource
	private AjaxService ajaxService;

	@Resource
	private ProvinceDAO provinceDAO;
	@Resource
	private CityDAO cityDAO;
	@Resource
	private AreaDAO areaDAO;
	@Resource
	private UserDAO userDAO;

	/**
	 * 接受的数据
	 */
	// 类型
	private String type;
	// ID,
	private Long id;
	// URL
	private String url;
	//
	private String username;
	//
	private String telephone;
	//
	private String email;
	/**
	 * 返回的数据
	 * 
	 * @return
	 */
	private String seller;
	private List<Address> cityList = new ArrayList<Address>();
	private List<Address> areaList = new ArrayList<Address>();
	private Boolean bool;

	public Boolean getBool() {
		return bool;
	}

	@Action("/ajax")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	/**
	 * 店铺账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String obtainSellerByShop() throws Exception {
		String type = HttpB2CUtils.obtainShopType(url);
		if ("0".equals(type)) {
			seller = HttpB2CUtils.obtainSeller(url, "1");
			if (StringUtils.isBlank(seller)) {
				seller = HttpB2CUtils.obtainSeller(url, "2");
			}
			if (StringUtils.isBlank(seller)) {
				seller = HttpB2CUtils.obtainSeller(url, "3");
			}
		} else {
			seller = HttpB2CUtils.obtainSeller(url, type);
		}
		return JSON;

	}

	/**
	 * 查找密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findPassword() throws Exception {
		bool = ajaxService.updateFindPassword(username, telephone);
		return JSON;
	}

	/**
	 * 省市县联动
	 * 
	 * @return
	 * @throws Exception
	 */
	public String address() throws Exception {
		Address address;
		if ("1".equals(type)) {
			ProvinceEntity provinceEntity = provinceDAO.load(id);
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
			// }
		} else {
			CityEntity cityEntity = cityDAO.load(id);
			List<AreaEntity> areas = cityEntity.getAreas();
			for (AreaEntity areaEntity : areas) {
				address = new Address();
				BeanUtils.copyProperties(address, areaEntity);
				areaList.add(address);
			}
		}
		return JSON;
	}

	/**
	 * 验证用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String userExists() throws Exception {
		Long id = (Long) userDAO
				.uniqueResultObject(
						"select id from UserEntity  as _u where  _u.username=:username",
						"username", username);
		bool = id == null;
		return JSON;
	}

	/**
	 * 验证手机
	 * 
	 * @return
	 * @throws Exception
	 */
	public String phoneExists() throws Exception {
		Long id = (Long) userDAO
				.uniqueResultObject(
						"select id from UserEntity  as _u where  _u.telephone=:telephone",
						"telephone", telephone);
		bool = id == null;
		return JSON;
	}

	/**
	 * 验证email
	 * 
	 * @return
	 * @throws Exception
	 */
	public String emailExists() throws Exception {
		Long id = (Long) userDAO.uniqueResultObject(
				"select id from UserEntity  as _u where  _u.email=:email",
				"email", email);
		bool = id == null;
		return JSON;
	}

	/**
	 * 店铺账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String seller() throws Exception {
		seller = HttpB2CUtils.obtainSeller(url, type);
		return JSON;

	}

	public List<Address> getCityList() {
		return cityList;
	}

	public List<Address> getAreaList() {
		return areaList;
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

	public void setType(String type) {
		this.type = type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeller() {
		return seller;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
