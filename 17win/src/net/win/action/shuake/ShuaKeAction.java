package net.win.action.shuake;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.shuake.ShuakeService;

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
@Results( { @Result(name = "initShuakeIndex", location = "/shuake/index.jsp") })
@Namespace("/shuakeManager")
public class ShuaKeAction extends BaseAction {
	@Resource
	private ShuakeService shuakeService;

	@Override
	@Action("/shuake")
	public String execute() throws Exception {
		return super.execute();
	}

	public String initShuakeIndex() throws Exception {
		return shuakeService.initShuakeIndex();
	}
}
