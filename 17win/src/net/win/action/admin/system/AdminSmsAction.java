package net.win.action.admin.system;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.system.AdminSmsService;
import net.win.service.sms.SmsService;
import net.win.vo.SmsVO;

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
@Namespace("/adminSmsManager")
@Results( { @Result(name = "initSendSms", location = "/sms/sms.jsp"),
		@Result(name = "insertSms", location = "/sms/sms.jsp"),
		@Result(name = "input", location = "/sms/sms.jsp"),
		@Result(name = "querySms", location = "/sms/mySms.jsp"),
		@Result(name = "deleteSms", location = "/sms/mySms.jsp"),
		@Result(name = "sendTelphone", location = "/msg/msg.jsp"),
		@Result(name = "initSendTelphone", location = "/msg/msg.jsp"),

})
public class AdminSmsAction extends BaseAction {
	@Resource
	private AdminSmsService adminSmsService;

	private SmsVO smsVO = new SmsVO();

	public SmsVO getSmsVO() {
		return smsVO;
	}

	public void setSmsVO(SmsVO smsVO) {
		this.smsVO = smsVO;
	}

	@Action("/adminSms")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addSms() throws Exception {
		return adminSmsService.insertSms(smsVO);
	}

}
