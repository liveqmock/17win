package net.win.action.admin.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.user.AdminCapitalLogService;
import net.win.service.capitalLog.CapitalLogService;
import net.win.vo.CapitalLogVO;

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
@Results( { @Result(name = "input", location = "/admin/capitalLog/index.jsp"),
		@Result(name = "queryLogs", location = "/admin/capitalLog/index.jsp") })
@Namespace("/adminCapitalLogManager")
public class AdminCapitalLogAction extends BaseAction {
	@Resource
	private AdminCapitalLogService adminCapitalLogService;
	private CapitalLogVO capitalLogVO = new CapitalLogVO();

	public CapitalLogVO getCapitalLogVO() {
		return capitalLogVO;
	}

	public void setCapitalLogVO(CapitalLogVO capitalLogVO) {
		this.capitalLogVO = capitalLogVO;
	}

	@Action("/adminCapitalLog")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	/**
	 * 提现记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryLogs() throws Exception {
		return adminCapitalLogService.queryLogs(capitalLogVO);
	}
}
