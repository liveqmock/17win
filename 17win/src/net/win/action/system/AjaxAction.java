package net.win.action.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.dao.UserDAO;
import net.win.service.system.AjaxService;
import net.win.utils.HttpB2CUtils;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings( { "serial", "unused", "unchecked" })
@Controller
@ParentPackage("17win-default")
@Scope("prototype")
@Namespace("/ajaxManager")
public class AjaxAction extends BaseAction {
	@Resource
	private AjaxService ajaxService;

 
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
		// 自动获取
		if ("0".equals(type)) {
			String shopType = HttpB2CUtils.obtainShopType(url);
			if (!"0".equals(shopType)) {
				seller = HttpB2CUtils.obtainSellerByShop(url, shopType);
			} else {
				seller = "";
			}
		} else {
			seller = HttpB2CUtils.obtainSellerByShop(url, type);
		}
		return JSON;

	}

	/**
	 * 商品账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String obtainSellerByItem() throws Exception {
		// 自动获取
		if ("0".equals(type)) {
			String shopType = HttpB2CUtils.obtainShopType(url);
			if (!"0".equals(shopType)) {
				seller = HttpB2CUtils.obtainSellerByItem(url, shopType);
			} else {
				seller = "";
			}
		} else {
			seller = HttpB2CUtils.obtainSellerByItem(url, type);
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

	public String getType() {
		return type;
	}

}
