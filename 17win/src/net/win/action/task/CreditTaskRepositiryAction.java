package net.win.action.task;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.task.CreditTaskRepositoryService;
import net.win.vo.CreditTaskRepositoryVO;

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
@Results( { @Result(name = "queryRepositories", location = "/credit/teskRepository.jsp") })
@Namespace("/taskRepositoryManager")
public class CreditTaskRepositiryAction extends BaseAction {
	@Resource
	private CreditTaskRepositoryService creditTaskRepositoryService;

	private CreditTaskRepositoryVO creditTaskRepositoryVO = new CreditTaskRepositoryVO();

	@Action("/taskRepository")
	public String execute() throws Exception {
		return INPUT;
	}

	/**
	 * 发布任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String releaseRepository() throws Exception {
		return creditTaskRepositoryService
				.innsertRepository(creditTaskRepositoryVO);
	}
	
	/**
	 * 发布任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteRepository() throws Exception {
		return creditTaskRepositoryService
				.deleteRepository(creditTaskRepositoryVO);
	}

	/**
	 * 查询任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryRepositories() throws Exception {
		return creditTaskRepositoryService
				.queryRepositories(creditTaskRepositoryVO);
	}

	/**
	 * 获取任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String obtainTaskInfo() throws Exception {
		return creditTaskRepositoryService
				.obtainTaskInfo(creditTaskRepositoryVO);
	}

	public CreditTaskRepositoryVO getCreditTaskRepositoryVO() {
		return creditTaskRepositoryVO;
	}

	public void setCreditTaskRepositoryVO(
			CreditTaskRepositoryVO creditTaskRepositoryVO) {
		this.creditTaskRepositoryVO = creditTaskRepositoryVO;
	}
}
