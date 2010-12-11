package net.win.service.admin.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.SmsDAO;
import net.win.dao.UserDAO;
import net.win.entity.SmsEntity;
import net.win.entity.UserEntity;
import net.win.utils.LoggerUtils;
import net.win.utils.MsgUtils;
import net.win.utils.StringUtils;
import net.win.vo.SmsVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("adminSmsService")
public class AdminSmsService extends BaseService {
	@Resource
	private SmsDAO smsDAO;
	@Resource
	private UserDAO userDAO;

	/**
	 * 初始化
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String initSendSms(SmsVO smsVO) throws Exception {
		String toUsername = new String(getByParam("toUser"));
		if (toUsername != null) {
			toUsername = new String(toUsername.getBytes("ISO-8859-1"), "UTF-8");
		}
		smsVO.setToUserName(toUsername);
		return "initSendSms";
	}

	/**
	 * 插入
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String insertSms(SmsVO smsVO) throws Exception {
		SmsEntity smsEntity = new SmsEntity();
		UserEntity toUser = userDAO.findUserByName(smsVO.getToUserName());
		if (smsVO.getToUserName().equals(getLoginUser().getUsername())) {
			putAlertMsg("不能给自己发送短信！");
			return "insertSms";
		}
		if (toUser == null) {
			putAlertMsg("没有该接收人！");
			return "insertSms";
		}
		BeanUtils.copyProperties(smsEntity, smsVO);
		smsEntity.setSendDate(new Date());
		smsEntity.setFromUser(getLoginUserEntity(userDAO));
		smsEntity.setToUser(toUser);
		smsEntity.setType("2");
		smsEntity.setRead(false);
		smsDAO.save(smsEntity);
		putAlertMsg("发送成功！");
		smsVO.setToUserName("");
		return "insertSms";
	}
}
