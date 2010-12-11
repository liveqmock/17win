package net.win.action.admin.system;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.system.AdminPropertyService;
import net.win.vo.PropertyVO;

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
@Namespace("/adminPropertyManager")
@Results( {
		@Result(name = "input", location = "/admin/property/property.jsp"),
		@Result(name = "queryProperty", location = "/admin/property/property.jsp"),
		@Result(name = "updateProperty", location = "/admin/property/updateProperty.jsp"),
		@Result(name = "initUpdateProperty", location = "/admin/property/updateProperty.jsp")

})
public class AdminPropertyAction extends BaseAction {
	@Resource
	private AdminPropertyService adminPropertyService;

	private PropertyVO propertyVO = new PropertyVO();

	public PropertyVO getPropertyVO() {
		return propertyVO;
	}

	public void setPropertyVO(PropertyVO propertyVO) {
		this.propertyVO = propertyVO;
	}

	@Override
	@Action("/adminProperty")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryProperty() throws Exception {
		return adminPropertyService.queryProperty(propertyVO);
	}

	/**
	 * 更新
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateProperty() throws Exception {
		return adminPropertyService.updateProperty(propertyVO);
	}

	/**
	 * 初始化更新
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initUpdateProperty() throws Exception {
		return adminPropertyService.initUpdateProperty(propertyVO);
	}

	/**
	 * 刷新Constant
	 * 
	 * @return
	 * @throws Exception
	 */
	public String refreshConstant() throws Exception {
		return adminPropertyService.refreshConstant(propertyVO);
	}
}
