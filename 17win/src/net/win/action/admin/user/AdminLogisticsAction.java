package net.win.action.admin.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.user.AdminLogisticsService;
import net.win.vo.LogisticsVO;

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
		@Result(name = "input", location = "/admin//logistics/index.jsp"),
		@Result(name = "queryLogisticsLog", location = "/admin//logistics/index.jsp") })
@Namespace("/adminLogisticsManager")
public class AdminLogisticsAction extends BaseAction {
	@Resource
	private AdminLogisticsService adminLogisticsService;
	private LogisticsVO logisticsVO = new LogisticsVO();

	public LogisticsVO getLogisticsVO() {
		return logisticsVO;
	}

	public void setLogisticsVO(LogisticsVO logisticsVO) {
		this.logisticsVO = logisticsVO;
	}

	@Action("/adminLogistics")
	public String execute() throws Exception {
		return INPUT;
	}

	/**
	 * 撤销
	 * 
	 * @return
	 * @throws Exception
	 */
	public String redoLogistics() throws Exception {
		return adminLogisticsService.updateRedoLogistics(logisticsVO);
	}

	/**
	 * 物流信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryLogisticsLog() throws Exception {
		return adminLogisticsService.queryLogisticsLog(logisticsVO);
	}

}
