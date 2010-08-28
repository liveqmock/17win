package net.win.action.system;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.win.BaseAction;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ParentPackage("17win-default")
@Results( { @Result(name = "input", location = "/system/register.jsp"),
		@Result(name = "success", location = "/user/index.jsp") })
@Namespace("/systemManager")
public class CommonAction extends BaseAction {
	@Action("/common")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
}
