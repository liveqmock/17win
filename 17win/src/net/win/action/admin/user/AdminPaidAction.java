package net.win.action.admin.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.user.AdminPaidService;
import net.win.vo.AdminPayVO;

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
		@Result(name = "queryPay", location = "/admin/paid/paidIndex.jsp"),
		@Result(name = "updateUserMoney", location = "/admin/paid/paidIndex.jsp"), })
@Namespace("/adminPaidManager")
public class AdminPaidAction extends BaseAction {
	@Resource
	private AdminPaidService adminPaidService;

	private AdminPayVO adminPayVO = new AdminPayVO();

	public AdminPayVO getAdminPayVO() {
		return adminPayVO;
	}

	public void setAdminPayVO(AdminPayVO adminPayVO) {
		this.adminPayVO = adminPayVO;
	}

	@Action("/adminPaid")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 删除
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String deleteMoney() throws Exception {
		return adminPaidService.deleteMoney(adminPayVO);
	}

	/**
	 * 充值
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String addMoney() throws Exception {
		return adminPaidService.updateUserMoney(adminPayVO);
	}

	/**
	 * 查询用户
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String queryPay() throws Exception {
		return adminPaidService.queryPay(adminPayVO);
	}
}
