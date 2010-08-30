package net.win.action.system;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.system.CommonService;
import net.win.vo.CommonVO;

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
@Results( { @Result(name = "initRegister", location = "/system/register.jsp"),
		@Result(name = "success", location = "/user/index.jsp") })
@Namespace("/systemManager")
public class CommonAction extends BaseAction {

}
