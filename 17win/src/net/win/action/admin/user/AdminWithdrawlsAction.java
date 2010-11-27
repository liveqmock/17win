package net.win.action.admin.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.user.AdminWithDrawalsService;
import net.win.vo.WithdrawalsVO;

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
		@Result(name = "queryLog", location = "/admin/withdrawals/withdrawalsIndex.jsp"),
		@Result(name = "updateLog", location = "/admin/withdrawals/withdrawalsIndex.jsp") })
@Namespace("/adminWithdrawalsManager")
public class AdminWithdrawlsAction extends BaseAction {
	@Resource
	private AdminWithDrawalsService adminWithDrawalsService;
	private WithdrawalsVO withdrawalsVO = new WithdrawalsVO();

	public WithdrawalsVO getWithdrawalsVO() {
		return withdrawalsVO;
	}

	public void setWithdrawalsVO(WithdrawalsVO withdrawalsVO) {
		this.withdrawalsVO = withdrawalsVO;
	}

	@Action("/adminWithdrawals")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	/**
	 * 提现记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryLog() throws Exception {
		return adminWithDrawalsService.queryLog(withdrawalsVO);
	}

	/**
	 * 提现
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateLog() throws Exception {
		return adminWithDrawalsService.updateLog(withdrawalsVO);
	}

}
