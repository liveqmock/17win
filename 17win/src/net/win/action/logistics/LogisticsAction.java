package net.win.action.logistics;

import javax.annotation.Resource;

import net.win.BaseAction;
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
		@Result(name = "useLogisticsLog", location = "/logistics/useLogisticsLog.jsp") })
@Namespace("/logisticsManager")
public class LogisticsAction extends BaseAction {
	@Resource
	private LogisticsService logisticsService;
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
	 * 填写物流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insertLogistics() throws Exception {
		return logisticsService.insertLogistics(logisticsVO);
	}

	/**
	 * 删除物流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteLogistics() throws Exception {
		return logisticsService.deleteLogistics(logisticsVO);
	}

	/**
	 * 查找提交物流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logisticsLog() throws Exception {
		return logisticsService.logisticsLog(logisticsVO);
	}

	/**
	 * 查找使用物流
	 * 
	 * @return
	 * @throws Exception
	 */
	public String useLogisticsLog() throws Exception {
		return logisticsService.useLogisticsLog(logisticsVO);
	}

	/**
	 * 初始化提现
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initLogistics() throws Exception {
		return "initLogistics";
	}

}
