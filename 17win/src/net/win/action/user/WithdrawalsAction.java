package net.win.action.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.user.WithdrawalsService;
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
@Namespace("/withdrawalsManager")
@Results( {
		@Result(name = "initWithdrawals", location = "/user/withdrawals.jsp"),
		@Result(name = "loginSuccess", location = "/user/withdrawals.jsp") })
public class WithdrawalsAction extends BaseAction {
	@Resource
	private WithdrawalsService withdrawalsService;
	private WithdrawalsVO withdrawalsVO = new WithdrawalsVO();

	public WithdrawalsVO getWithdrawalsVO() {
		return withdrawalsVO;
	}

	public void setWithdrawalsVO(WithdrawalsVO withdrawalsVO) {
		this.withdrawalsVO = withdrawalsVO;
	}

	@Action("/base")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	/**
	 * 提现
	 * 
	 * @return
	 * @throws Exception
	 */
	public String withdrawals() throws Exception {
		return withdrawalsService.insertWithdrawals(withdrawalsVO);
	}

	/**
	 * 初始化提现
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initWithdrawals() throws Exception {
		return "initWithdrawals";
	}

}
