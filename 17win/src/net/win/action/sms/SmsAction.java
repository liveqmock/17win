package net.win.action.sms;

import javax.annotation.Resource;

import net.win.BaseAction;
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
@Namespace("/smsManager")
@Results( { @Result(name = "insertVip", location = "/sms/sms.jsp"),
		@Result(name = "insertRenewalVip", location = "/user/vip.jsp"),
		@Result(name = "input", location = "/user/vip.jsp"), })
public class SmsAction extends BaseAction {
	@Resource
	private SmsService smsService;

	private SmsVO smsVO;

	public SmsVO getSmsVO() {
		return smsVO;
	}

	public void setSmsVO(SmsVO smsVO) {
		this.smsVO = smsVO;
	}

	@Action("/sms")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	/**
	 * 初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initSendSms() throws Exception {
		return smsService.initSendSms(smsVO);
	}

	public String addSms() throws Exception {
		return smsService.insertSms(smsVO);
	}
}
