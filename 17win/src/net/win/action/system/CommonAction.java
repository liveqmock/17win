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
@Results( { @Result(name = "activateOperattionCode", location = "/system/operationValidate.jsp")

})
@Namespace("/commonManager")
public class CommonAction extends BaseAction {

	@Resource
	private CommonService commonService;

	private CommonVO commonVO = new CommonVO();

	public CommonVO getCommonVO() {
		return commonVO;
	}

	public void setCommonVO(CommonVO commonVO) {
		this.commonVO = commonVO;
	}

	@Override
	@Action("/common")
	public String execute() throws Exception {
		return super.execute();
	}

	/**
	 * 更新操作码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String activateOperattionCode() throws Exception {
		return commonService.activateOperattionCode(commonVO);
	}

}
