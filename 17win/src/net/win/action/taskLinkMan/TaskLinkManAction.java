package net.win.action.taskLinkMan;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.logistics.LogisticsService;
import net.win.service.taskLinkMan.TaskLinkManService;
import net.win.vo.LogisticsVO;
import net.win.vo.TaskLinkManVo;

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
		@Result(name = "input", location = "/logistics/logistics.jsp"),
		@Result(name = "initLogistics", location = "/logistics/logistics.jsp"),
		@Result(name = "addLinkTaskMan", location = "/logistics/logistics.jsp"),
		@Result(name = "logisticsLog", location = "/logistics/logisticsLog.jsp"),
		@Result(name = "useLogisticsLog", location = "/logistics/useLogisticsLog.jsp"),
		@Result(name = "queryLogisticsLog", location = "/logistics/titleLogistics.jsp") })
@Namespace("/taskLinkManManager")
public class TaskLinkManAction extends BaseAction {
	@Resource
	private TaskLinkManService taskLinkManService;
	private TaskLinkManVo taskLinkManVo = new TaskLinkManVo();

 

	public TaskLinkManVo getTaskLinkManVo() {
		return taskLinkManVo;
	}

	public void setTaskLinkManVo(TaskLinkManVo taskLinkManVo) {
		this.taskLinkManVo = taskLinkManVo;
	}

	@Action("/logistics")
	public String execute() throws Exception {
		return INPUT;
	}

	/**
	 * 物流信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryLogisticsLog() throws Exception {
		return logisticsService.queryLogisticsLog(logisticsVO);
	}

	/**
	 * 添加联系人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insertLogistics() throws Exception {
		return logisticsService.insertLogistics(logisticsVO);
	}

	public String useLogistics() throws Exception {
		return logisticsService.updateUseLogistics(logisticsVO);
	}

	/**
	 * 删除物流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteLogistics() throws Exception {
		return logisticsService.deleteLogistics(logisticsVO);
	}

	/**
	 * 查找提交物流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logisticsLog() throws Exception {
		return logisticsService.logisticsLog(logisticsVO);
	}

	/**
	 * 查找使用物流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String useLogisticsLog() throws Exception {
		return logisticsService.useLogisticsLog(logisticsVO);
	}

	/**
	 * 初始化物流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initLogistics() throws Exception {
		return logisticsService.initLogistics(logisticsVO);
	}

}
