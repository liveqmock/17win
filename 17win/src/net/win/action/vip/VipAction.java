package net.win.action.vip;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.vip.VipService;
import net.win.vo.VipVO;

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
@Namespace("/vipManager")
@Results( { @Result(name = "insertVip", location = "/user/vip.jsp"),
		@Result(name = "insertRenewalVip", location = "/user/vip.jsp"),
		@Result(name = "input", location = "/user/vip.jsp"), })
public class VipAction extends BaseAction {
	@Resource
	private VipService vipService;
	private VipVO vipVO;

	public VipVO getVipVO() {
		return vipVO;
	}

	public void setVipVO(VipVO vipVO) {
		this.vipVO = vipVO;
	}
	

	@Action("/vip")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}
	public String initVip() throws Exception {
		return INPUT;
	}

	/**
	 * 购买VIP
	 * 
	 */
	public String buyVip() throws Exception {
		return vipService.insertVip(vipVO);
	}

	/**
	 * 续费VIP
	 * 
	 */
	public String renewalVip() throws Exception {
		return vipService.insertRenewalVip(vipVO);
	}
}
