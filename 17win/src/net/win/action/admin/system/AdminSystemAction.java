package net.win.action.admin.system;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.system.AdminSystemService;

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
@Namespace("/adminSystemManager")
@Results( { @Result(name = "input", location = "/admin/system/staticPage.jsp")

})
public class AdminSystemAction extends BaseAction {
	@Resource
	private AdminSystemService adminSystemService;

	@Override
	@Action("/adminSystem")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 静态页面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initStaticPage() throws Exception {
		// TODO Auto-generated method stub
		return "staticPage";
	}

	/**
	 * 新闻静态页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String staticNewsPage() throws Exception {
		return adminSystemService.staticNewsPage();
	}

	/**
	 * 新闻静态页面 2
	 * 
	 * @return
	 * @throws Exception
	 */
	public String staticNews2Page() throws Exception {
		return adminSystemService.staticNews2Page();
	}

	/**
	 * 首页静态页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String staticIndexPage() throws Exception {
		return adminSystemService.staticIndexPage();
	}

	/**
	 * 首页静态页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String staticShuakeIndexPage() throws Exception {
		return adminSystemService.staticShuakeIndexPage();
	}

}
