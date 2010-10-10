package net.win.action.admin.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.user.AdminUserService;
import net.win.vo.AdminUserVO;

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
@Results( { @Result(name = "queryUser", location = "/admin/user/userIndex.jsp") })
@Namespace("/adminUserManager")
public class AdminUserAction extends BaseAction {
	@Resource
	private AdminUserService adminUserService;

	private AdminUserVO adminUserVO = new AdminUserVO();

	public AdminUserVO getAdminUserVO() {
		return adminUserVO;
	}

	public void setAdminUserVO(AdminUserVO adminUserVO) {
		this.adminUserVO = adminUserVO;
	}

	@Action("/adminUser")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 查询用户
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String queryUser() throws Exception {
		return adminUserService.queryUser(adminUserVO);
	}

}
