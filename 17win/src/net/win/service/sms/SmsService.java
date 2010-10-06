package net.win.service.sms;

import java.util.Date;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.SmsDAO;
import net.win.dao.UserDAO;
import net.win.entity.SmsEntity;
import net.win.entity.UserEntity;
import net.win.vo.SmsVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("smsService")
public class SmsService extends BaseService {
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
		String toUsername = getByParam("toUser");
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
		BeanUtils.copyProperties(smsEntity, smsVO);
		smsEntity.setSendDate(new Date());
		smsEntity.setFromUser(getLoginUserEntity(userDAO));
		smsEntity.setToUser(toUser);
		return "insertSms";
	}
}