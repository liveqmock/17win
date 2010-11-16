package net.win.action.menu;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.menu.MenuService;

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
@Namespace("/menuManager")
@Results( { @Result(name = "toIndex", location = "/index.jsp") })
public class MenuAction extends BaseAction {

	@Resource
	private MenuService menuService;

	@Action("/menu")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toIndex() throws Exception {
		return menuService.toIndex();
	}

}
