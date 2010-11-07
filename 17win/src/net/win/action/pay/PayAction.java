package net.win.action.pay;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.pay.PayService;
import net.win.vo.PayVO;

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
@Namespace("/payManager")
@Results( { @Result(name = "initPay", location = "/paid/paid.jsp"),
		@Result(name = "insertPay", location = "/paid/paid.jsp"),
		@Result(name = "queryPay", location = "/paid/myPaid.jsp"),
		@Result(name = "initPay", location = "/paid/paid.jsp")
})
public class PayAction extends BaseAction {
	@Resource
	private PayService payService;

	private PayVO payVO = new PayVO();

	public PayVO getPayVO() {
		return payVO;
	}

	public void setPayVO(PayVO payVO) {
		this.payVO = payVO;
	}

	@Action("/pay")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	/**
	 * 初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initPay() throws Exception {
//		
		return payService.initPay(payVO);
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addPaid() throws Exception {
		return payService.insertPay(payVO);
	}

	/**
	 * 查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryPay() throws Exception {
		return payService.queryPay(payVO);
	}

}
