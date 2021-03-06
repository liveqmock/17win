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
@Results( { @Result(name = "initSendSms", location = "/sms/sms.jsp"),
		@Result(name = "insertSms", location = "/sms/sms.jsp"),
		@Result(name = "input", location = "/sms/sms.jsp"),
		@Result(name = "querySms", location = "/sms/mySms.jsp"),
		@Result(name = "deleteSms", location = "/sms/mySms.jsp"),
		@Result(name = "sendTelphone", location = "/msg/msg.jsp"),
		@Result(name = "initSendTelphone", location = "/msg/msg.jsp"),

})
public class SmsAction extends BaseAction {
	@Resource
	private SmsService smsService;

	private SmsVO smsVO = new SmsVO();

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

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addSms() throws Exception {
		return smsService.insertSms(smsVO);
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initSendTelphone() throws Exception {
		return smsService.initSendTelphone();
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendTelphone() throws Exception {
		return smsService.updateSendTelphone();
	}

	/**
	 * 查询收件箱
	 * 
	 * @return
	 * @throws Exception
	 */
	public String querySJXSms() throws Exception {
		return smsService.querySJXSms(smsVO);
	}
	/**
	 * 查询发件箱
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryFJXSms() throws Exception {
		return smsService.queryFJXSms(smsVO);
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteSjxSms() throws Exception {
		return smsService.deleteSjxSms(smsVO);
	}
	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteFjxSms() throws Exception {
		return smsService.deleteFjxSms(smsVO);
	}
	/**
	 * 修改已读标记
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateSms() throws Exception {
		return smsService.updateSms(smsVO);
	}
}
