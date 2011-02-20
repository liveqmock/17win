package net.win.action.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.task.CreditTaskService;
import net.win.vo.CreditTaskVO;

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
@Results({
		@Result(name = "input", location = "/credit/task.jsp"),
		@Result(name = "initTask", location = "/credit/task.jsp"),
		@Result(name = "initReleaseTask", location = "/credit/releaseTask.jsp"),
		@Result(name = "operationValidate", location = "/system/operationValidate.jsp"),
		@Result(name = "insertReleaseTaskFail", type = "chain", location = "/taskManager/task!initReleaseTask.php"),
		//		@Result(name = "insertReleaseTaskSuccess", type = "chain", location = "/taskManager/task!initReleaseTask.php"),
		@Result(name = "initReleaseTaskFail", type = "chain", location = "/taskManager/task!initTask.php"),
		@Result(name = "initReleasedTast", location = "/credit/jyReleaseTask.jsp"),
		@Result(name = "initReceivedTast", location = "/credit/jyReceiveTask.jsp")
/**
 * 买家操作
 */

})
@Namespace("/taskManager")
public class CreditTaskAction extends BaseAction {
	@Resource
	private CreditTaskService creditTaskService;

	private CreditTaskVO creditTaskVO = new CreditTaskVO();

	private List<String> linkMans = new ArrayList<String>();

	@Action("/task")
	public String execute() throws Exception {
		return INPUT;
	}

	/** ************************** 买家操作 ******************************* */
	/**
	 * 好评
	 */
	public String buyerEvaluate() throws Exception {
		return creditTaskService.updateBuyerEvaluate(creditTaskVO);
	}

	/**
	 * 撤销付款
	 */
	public String rollbackPay() throws Exception {
		return creditTaskService.updateRollbackPay(creditTaskVO);
	}

	/**
	 * 已经付款
	 */
	public String payMoney() throws Exception {
		return creditTaskService.updatePayTask(creditTaskVO);
	}

	/**
	 * 退出任务
	 */
	public String quitTask() throws Exception {
		return creditTaskService.updateQuitTask(creditTaskVO);
	}

	/**
	 * 接手操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String receiveTask() throws Exception {
		return creditTaskService.updateReceiveTask(creditTaskVO);
	}

	/** ********* 卖家操作 **************** */
	/**
	 * 审核买家
	 */
	public String audiReceiver() throws Exception {
		return creditTaskService.updateAudiReceiver(creditTaskVO);
	}

	/**
	 * 清理买家
	 */
	public String clearReceiver() throws Exception {
		return creditTaskService.updateClearReceiver(creditTaskVO);
	}

	/**
	 * 卖家好评
	 */
	public String sellerEvaluate() throws Exception {
		return creditTaskService.updateSellerEvaluate(creditTaskVO);
	}

	/**
	 * 卖家发货
	 */
	public String dispatch() throws Exception {
		return creditTaskService.updateDispatch(creditTaskVO);
	}

	/**
	 * 加时
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addTime() throws Exception {
		return creditTaskService.updateAddTime(creditTaskVO);
	}

	/**
	 * 任务排前
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toFirstTask() throws Exception {
		return creditTaskService.updateToFirstTask(creditTaskVO);
	}

	/**
	 * 取消任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelTask() throws Exception {
		return creditTaskService.updateCancelTask(creditTaskVO);
	}

	/**
	 * 初始化已接任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initReceivedTast() throws Exception {
		return creditTaskService.initReceivedTast(creditTaskVO);
	}

	/**
	 * 初始化已发任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initReleasedTast() throws Exception {
		return creditTaskService.initReleasedTast(creditTaskVO);
	}

	/**
	 * 初始化发布任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initReleaseTask() throws Exception {
		return creditTaskService.initReleaseTask(creditTaskVO);
	}

	/**
	 * 发布任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String releaseTask() throws Exception {
		return creditTaskService.insertReleaseTask(creditTaskVO);
	}

	/**
	 * 加载联系人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String obtainLinkMan() throws Exception {
		linkMans = creditTaskService.getLinkMans(creditTaskVO);
		return JSON;
	}

	/**
	 * 发送手机短信
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendMsg() throws Exception {
		return creditTaskService.updateSendMsg(creditTaskVO);
	}

	/**
	 * 初始化任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initTask() throws Exception {
		return creditTaskService.initTask(creditTaskVO);
	}

	public CreditTaskVO getCreditTaskVO() {
		return creditTaskVO;
	}

	public void setCreditTaskVO(CreditTaskVO creditTaskVO) {
		this.creditTaskVO = creditTaskVO;
	}

	public List<String> getLinkMans() {
		return linkMans;
	}

}
