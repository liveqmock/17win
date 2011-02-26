package net.win.action.taskLinkMan;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.taskLinkMan.TaskLinkManService;
import net.win.vo.TaskLinkManVO;

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
		@Result(name = "input", location = "/taskLinkMan/addTaskLinkMan.jsp"),
		@Result(name = "initAddLinkTaskMan", location = "/taskLinkMan/addTaskLinkMan.jsp"),
		@Result(name = "queryLinkTaskMan", location = "/taskLinkMan/taskLinkManLog.jsp"),
		@Result(name = "deleteLinkTaskMan", location = "/taskLinkMan/taskLinkManLog.jsp")

})
@Namespace("/taskLinkManManager")
public class TaskLinkManAction extends BaseAction {
	@Resource
	private TaskLinkManService taskLinkManService;
	private TaskLinkManVO taskLinkManVO = new TaskLinkManVO();

	public TaskLinkManVO getTaskLinkManVO() {
		return taskLinkManVO;
	}

	public void setTaskLinkManVO(TaskLinkManVO taskLinkManVO) {
		this.taskLinkManVO = taskLinkManVO;
	}

	@Action("/taskLinkMan")
	public String execute() throws Exception {
		return INPUT;
	}

	/**
	 * 初始化添加联系人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initAddLinkTaskMan() throws Exception {
		return taskLinkManService.initAddLinkTaskMan(taskLinkManVO);
	}

	/**
	 * 添加联系人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insertLinkTaskMan() throws Exception {
		return taskLinkManService.insertLinkTaskMan(taskLinkManVO);
	}

	//
	/**
	 * 查找联系人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryLinkTaskMan() throws Exception {
		return taskLinkManService.queryLinkTaskMan(taskLinkManVO);
	}

	/**
	 * 删除联系人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteLinkTaskMan() throws Exception {
		return taskLinkManService.deleteLinkTaskMan(taskLinkManVO);
	}

}
