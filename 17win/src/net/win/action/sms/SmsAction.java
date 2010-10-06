package net.win.action.sms;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.sms.SmsService;
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
@Namespace("/smsManager")
@Results( { @Result(name = "insertVip", location = "/sms/sms.jsp"),
		@Result(name = "insertRenewalVip", location = "/user/vip.jsp"),
		@Result(name = "input", location = "/user/vip.jsp"), })
public class SmsAction extends BaseAction {
	@Resource
	private SmsService smsService;
 

	@Action("/sms")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}
	public String initVip() throws Exception {
		return INPUT;
	}
}
