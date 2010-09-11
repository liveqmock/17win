package net.win.action.task;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.task.CreditTaskRepositoryService;
import net.win.service.task.CreditTaskService;
import net.win.vo.CreditTaskRepositoryVO;
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
@Results( {
		@Result(name = "input", location = "/credit/task.jsp"),
		@Result(name = "initTask", location = "/credit/task.jsp"),
		@Result(name = "initReleaseTask", location = "/credit/releaseTask.jsp"),
		@Result(name = "operationValidate", location = "/system/operationValidate.jsp"),
		@Result(name = "noSellerPage", type = "redirect", location = "/userInfoManager/info!initSellerAndBuyer.php?noSellerDirect=noSellerDirect"),
		@Result(name = "insertReleaseTaskFail", type = "chain", location = "/taskManager/task!initReleaseTask.php"),
		@Result(name = "insertReleaseTaskSuccess", type = "redirect", location = "/taskManager/task!initTask.php?platformType=1") })
@Namespace("/taskRepositoryManager")
public class CreditTaskRepositiryAction extends BaseAction {
	@Resource
	private CreditTaskRepositoryService creditTaskRepositoryService;

	private CreditTaskRepositoryVO creditTaskRepositoryVO = new CreditTaskRepositoryVO();

	@Action("/taskRepository")
	public String execute() throws Exception {
		return INPUT;
	}

	/**
	 * 获取任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String obtainTaskInfo() throws Exception {
		return creditTaskRepositoryService
				.obtainTaskInfo(creditTaskRepositoryVO);
	}

	public CreditTaskRepositoryVO getCreditTaskRepositoryVO() {
		return creditTaskRepositoryVO;
	}

	public void setCreditTaskRepositoryVO(
			CreditTaskRepositoryVO creditTaskRepositoryVO) {
		this.creditTaskRepositoryVO = creditTaskRepositoryVO;
	}
}
