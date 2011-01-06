package net.win.action.admin.task;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.service.AdminCreditTaskVO;
import net.win.service.admin.service.AdminTaskService;

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
@Namespace("/adminTaskManager")
@Results( { @Result(name = "initCreditTask", location = "/admin/creditTask/index.jsp")

})
public class AdminTaskAction extends BaseAction {
	@Resource
	private AdminTaskService adminTaskService;

	private AdminCreditTaskVO adminCreditTaskVO = new AdminCreditTaskVO();

	public AdminCreditTaskVO getAdminCreditTaskVO() {
		return adminCreditTaskVO;
	}

	public void setAdminCreditTaskVO(AdminCreditTaskVO adminCreditTaskVO) {
		this.adminCreditTaskVO = adminCreditTaskVO;
	}

	@Action("/adminTask")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initCreditTask () throws Exception {
		return adminTaskService.initCreditTask(adminCreditTaskVO);
	}

	
	/**
	 * 撤销支付
	 * 
	 * @param creditTaskVO
	 * @return
	 */
	public String updateRollbackPay()
			throws Exception {
		return adminTaskService.updateRollbackPay(adminCreditTaskVO);
	}

	/**
	 * 卖家发货
	 */
	public String updateDispatch()
			throws Exception {
		return adminTaskService.updateDispatch(adminCreditTaskVO);
	}

	/** ************ */
	/**
	 * 买家付款
	 * 
	 * @param creditTaskVO
	 * @return
	 */
	public String updatePayTask()
			throws Exception {
		return adminTaskService.updatePayTask(adminCreditTaskVO);
	}

	/**
	 * 买家好评
	 */
	public String updateBuyerEvaluate()
			throws Exception {
		return adminTaskService.updateBuyerEvaluate(adminCreditTaskVO);
	}

	/** *** */
	/**
	 * 卖家好评 任务完成
	 */
	public String updateSellerEvaluate()
			throws Exception {
		return adminTaskService.updateSellerEvaluate(adminCreditTaskVO);
	}

}
