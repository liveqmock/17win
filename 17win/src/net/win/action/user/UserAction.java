package net.win.action.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.UserLoginInfo;
import net.win.service.user.UserService;
import net.win.utils.Constant;
import net.win.utils.WinUtils;
import net.win.vo.UserVO;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ParentPackage("17win-default")
@Namespace("/userManager")
@Results({
		@Result(name = "input", location = "/user/register.jsp"),
		@Result(name = "inputLogin", location = "/user/login.jsp"),
		@Result(name = "loginSuccess", type = "redirect", location = "/userInfoManager/info!init.php"),
		@Result(name = "initRegister", location = "/user/register.jsp"),
		@Result(name = "registerSuccess", location = "/user/login.jsp"),
		@Result(name = "initFindPassword", location = "/user/findPassword.jsp"),
		@Result(name = "findPasswordSuccess", location = "/user/login.jsp"),
		@Result(name = "initLogin", location = "/user/login.jsp"),
		@Result(name = "loginOut", type = "redirect", location = "/index.html")})
public class UserAction extends BaseAction {
	@Resource
	private UserService userService;

	private UserLoginInfo loginInfo;

	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public UserLoginInfo getLoginInfo() {
		return loginInfo;
	}

	/**
	 * 推广用的，推广人
	 */
	private String spreadUsername;
	private UserVO userVO = new UserVO();

	@JSON(serialize = false)
	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	@Action("/base")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	public String getLoginUser() throws Exception {
		loginInfo = (UserLoginInfo) ServletActionContext.getRequest()
				.getSession().getAttribute(Constant.USER_LOGIN_INFO);
		ip = WinUtils.getIPAddress(ServletActionContext.getRequest());

		return JSON;
	}
	/**
	 * 登录
	 * 
	 */
	public String loginOut() throws Exception {
		return userService.loginOut(userVO);
	}

	/**
	 * 找回密码
	 * 
	 */
	public String findPassword() throws Exception {
		return userService.updatefindPassword(userVO);
	}

	/**
	 * 初始化找回密码
	 * 
	 */
	public String initFindPassword() throws Exception {
		return userService.initFindPassword(userVO);
	}

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		return userService.updateLogin(userVO);
	}
	/**
	 * admin登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String adminLogin() throws Exception {
		return userService.updateAdminLogin(userVO);
	}
	/**
	 * admin登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateAdminPassword() throws Exception {
		return userService.updateAdminPassword(userVO);
	}

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initLogin() throws Exception {
		return userService.initLogin(userVO);
	}

	/**
	 * 注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception {
		return userService.insertUser(userVO);
	}

	/**
	 * 注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initRegister() throws Exception {
		return userService.initRegister(userVO);
	}

	@JSON(serialize = false)
	public String getSpreadUsername() {
		return spreadUsername;
	}

	public void setSpreadUsername(String spreadUsername) {
		this.spreadUsername = spreadUsername;
	}

	public void setLoginInfo(UserLoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
}
