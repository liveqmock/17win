package net.win.action.admin.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.user.AdminService;

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
@Namespace("/adminManager")
@Results( { @Result(name = "input", location = "/admin/index.jsp"),
		@Result(name = "inputLogin", location = "/admin/index.jsp"),
		@Result(name = "loginOut", location = "/admin/index.jsp"),
		@Result(name = "loginSuccess",type="redirect", location = "/admin/main.html") })
public class AdminAction extends BaseAction {
	@Resource
	private AdminService adminService;

	@Override
	@Action("/admin")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 注销
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loginOut() throws Exception {
		return adminService.loginOut();
	}

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		return adminService.updateLogin();
	}
}
