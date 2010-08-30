package net.win.action.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.user.UserService;
import net.win.vo.UserVO;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ParentPackage("17win-default")
@Results( {
		@Result(name = "input", location = "/system/register.jsp"),
		@Result(name = "inputLogin", location = "/user/login.jsp"),
		@Result(name = "loginSuccess", type = "redirect", location = "/user/index.jsp"),
		@Result(name = "initRegister", location = "/system/register.jsp"),
		@Result(name = "registerSuccess", location = "/user/login.jsp") })
@Namespace("/userManager")
public class UserAction extends BaseAction {
	@Resource
	private UserService userService;

	private UserVO userVO = new UserVO();

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

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		return userService.login(userVO);
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
}
