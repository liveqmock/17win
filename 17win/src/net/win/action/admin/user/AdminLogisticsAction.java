package net.win.action.admin.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.user.AdminLogisticsService;
import net.win.service.logistics.LogisticsService;
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
		@Result(name = "input", location = "/logistics/logistics.jsp"),
		@Result(name = "initLogistics", location = "/logistics/logistics.jsp"),
		@Result(name = "logistics", location = "/logistics/logistics.jsp"),
		@Result(name = "logisticsLog", location = "/logistics/logisticsLog.jsp"),
		@Result(name = "useLogisticsLog", location = "/logistics/useLogisticsLog.jsp"),
		@Result(name = "queryLogisticsLog", location = "/logistics/titleLogistics.jsp") })
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
		return adminLogisticsService.queryLogisticsLog(logisticsVO);
	}


}
