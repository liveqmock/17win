package net.win.action.task;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.task.CreditTaskService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ParentPackage("17win-default")
@Results({})
@Namespace("/taskManager")
public class TaskAction extends BaseAction {
	@Resource
	private CreditTaskService creditTaskService;

	@Action("/task")
	public String execute() throws Exception {
		return super.execute();
	}
}
