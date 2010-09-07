package net.win.action.task;

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
@Results( {
		@Result(name = "input", location = "/credit/task.jsp"),
		@Result(name = "initTask", location = "/credit/task.jsp"),
		@Result(name = "initReleaseTask", location = "/credit/releaseTask.jsp"),
		@Result(name = "operationValidate", location = "/credit/operationValidate.jsp"),
		@Result(name = "updateUserLoginOperattionCode", type = "redirect", location = "/taskManager/task!initReleaseTask.php") })
@Namespace("/taskManager")
public class TaskAction extends BaseAction {
	@Resource
	private CreditTaskService creditTaskService;

	private CreditTaskVO creditTaskVO = new CreditTaskVO();

	@Action("/task")
	public String execute() throws Exception {
		return INPUT;
	}

	/**
	 * 更新操作码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateUserLoginOperattionCode() throws Exception {
		return creditTaskService.updateUserLoginOperattionCode(creditTaskVO);
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


	/**
	 * 初始化发布任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initReleaseTask() throws Exception {
		return creditTaskService.initReleaseTask(creditTaskVO);
	}

	public CreditTaskVO getCreditTaskVO() {
		return creditTaskVO;
	}

	public void setCreditTaskVO(CreditTaskVO creditTaskVO) {
		this.creditTaskVO = creditTaskVO;
	}

}
