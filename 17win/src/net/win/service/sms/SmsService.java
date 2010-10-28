package net.win.service.sms;

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
	 * 删除
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String deleteSms(SmsVO smsVO) throws Exception {
		smsDAO.deleteByHql("delete from SmsEntity where id=:smsID",
				new String[] { "smsID" }, new Object[] { smsVO.getId() });
		querySms(smsVO);
		putAlertMsg("删除成功！");
		return "deleteSms";
	}

	/**
	 * 初始化手机短信
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initSendTelphone() throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		if (userEntity.getVipEnable()) {
			if (userEntity.getVipBidUserEntity().getRemainMsgCount() == 0) {
				putByRequest("noMsgCount", "noMsgCount");
			} else {
				putByRequest("remainMsgCount", userEntity.getVipBidUserEntity()
						.getRemainMsgCount());
			}
		} else {
			putByRequest("notVIP", "noVIP");
		}
		return "initSendTelphone";
	}

	/**
	 * 修改
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String updateSms(SmsVO smsVO) throws Exception {
		smsDAO.get(smsVO.getId()).setRead(true);
		return null;
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

	/**
	 * 发送手机短信
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String updateSendTelphone() throws Exception {
		try {
			String telphone = getByParam("telehpne");
			String content = getByParam("content");
			String sendType = getByParam("sendType");
			String opertaionCode = getByParam("opertaionCode");
			UserEntity userEntity = getLoginUserEntity(userDAO);
			if (!userEntity.getOpertationCode().equals(
					StringUtils.processPwd(opertaionCode))) {
				putAlertMsg("操作码不正确！");
				return "sendTelphone";
			}
			if (getLoginUser().getVipEnable()) {
				putAlertMsg("发送失败，您不是会员！");
				return "sendTelphone";
			}
			if (sendType.equals("1")) {
				UserEntity toUser = userDAO.findUserByName(telphone);
				if (toUser == null) {
					putAlertMsg("用户不存在！");
					return "sendTelphone";
				} else {
					telphone = toUser.getTelephone();
				}
			}
			if (userEntity.getVipBidUserEntity().getRemainMsgCount() == 0) {
				putAlertMsg("发送失败，您所剩的短信数量不够！");
				return "sendTelphone";
			} else {
				userEntity.getVipBidUserEntity()
						.setRemainMsgCount(
								userEntity.getVipBidUserEntity()
										.getRemainMsgCount() - 1);
			}
			MsgUtils.sendMsg(telphone, content);
			putAlertMsg("发送成功！您还剩余"
					+ userEntity.getVipBidUserEntity().getRemainMsgCount()
					+ "条短信");
		} catch (RuntimeException e) {
			LoggerUtils.error(e);
			putAlertMsg("发送失败，请联系管理员！");
		}
		return "sendTelphone";
	}

	/**
	 * 查询短信
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String querySms(SmsVO smsVO) throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		StringBuffer resultFromHQL = new StringBuffer(
				"select  _sms ,_toUser.username,_fromUser.username from SmsEntity _sms inner join _sms.fromUser as _fromUser  inner join _sms.toUser as _toUser where _fromUser.id=:userId  ");
		StringBuffer countFromHQL = new StringBuffer(
				"select  count(*)  from SmsEntity _sms inner join _sms.fromUser as _fromUser inner join _sms.toUser as _toUser  where _fromUser.id=:userId  ");
		StringBuffer resultToHQL = new StringBuffer(
				"select  _sms  ,_toUser.username,_fromUser.username  from SmsEntity _sms inner join _sms.toUser as _toUser  inner join _sms.fromUser as _fromUser where  _toUser.id=:userId  ");
		StringBuffer countToHQL = new StringBuffer(
				"select  count(*)  from SmsEntity _sms inner join _sms.toUser as _toUser  inner join _sms.fromUser as _fromUser  where _toUser.id=:userId  ");

		List<String> paramNames = new ArrayList<String>();
		paramNames.add("userId");
		List<Object> paramValues = new ArrayList<Object>();
		paramValues.add(userEntity.getId());
		// 类型
		if (!StringUtils.isBlank(smsVO.getType())) {
			resultFromHQL.append(" and _sms.type=:type ");
			countFromHQL.append(" and _sms.type=:type ");

			resultToHQL.append(" and _sms.type=:type ");
			countToHQL.append(" and _sms.type=:type ");

			paramNames.add("type");
			paramValues.add(smsVO.getType());
		}

		// 时间
		if (smsVO.getStartDate() != null && smsVO.getEndDate() != null) {

			resultFromHQL
					.append(" and (_sms.sendDate>=:startDate and _sms.sendDate<=:endDate) ");
			countFromHQL
					.append(" and (_sms.sendDate>=:startDate and _sms.sendDate<=:endDate) ");

			resultToHQL
					.append(" and (_sms.sendDate>=:startDate and _sms.sendDate<=:endDate) ");
			countToHQL
					.append(" and (_sms.sendDate>=:startDate and _sms.sendDate<=:endDate) ");
			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(smsVO.getStartDate());
			paramValues.add(smsVO.getEndDate());
		} else if (smsVO.getStartDate() != null) {

			resultFromHQL.append(" and _sms.sendDate>=:startDate  ");
			countFromHQL.append(" and  _sms.sendDate>=:startDate   ");

			resultToHQL.append(" and _sms.sendDate>=:startDate  ");
			countToHQL.append(" and  _sms.sendDate>=:startDate   ");

			paramNames.add("startDate");
			paramValues.add(smsVO.getStartDate());
		} else if (smsVO.getEndDate() != null) {

			resultFromHQL.append(" and   _w.sendDate<=:endDate ");
			countFromHQL.append(" and   _w.sendDate<=:endDate  ");

			resultToHQL.append(" and   _w.sendDate<=:endDate  ");
			countToHQL.append(" and   _w.sendDate<=:endDate   ");

			paramNames.add("startDate");
			paramValues.add(smsVO.getStartDate());

			paramNames.add("endDate");
			paramValues.add(smsVO.getEndDate());
		}

		Long countFrom = (Long) smsDAO.uniqueResultObject(countFromHQL
				.toString(), paramNames.toArray(paramNames
				.toArray(new String[paramNames.size()])), paramValues
				.toArray(new Object[paramValues.size()]));
		Long countTo = (Long) smsDAO.uniqueResultObject(countToHQL.toString(),
				paramNames.toArray(paramNames.toArray(new String[paramNames
						.size()])), paramValues.toArray(new Object[paramValues
						.size()]));
		Long count = countFrom + countTo;
		List<SmsVO> result = new ArrayList<SmsVO>();
		if (count > 0) {
			smsVO.setDataCount(count.intValue());
			List<Object[]> resultFromTemp = smsDAO.pageQuery(resultFromHQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), smsVO.getStart(),
					smsVO.getLimit());
			List<Object[]> resultToTemp = smsDAO.pageQuery(resultToHQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), smsVO.getStart(),
					smsVO.getLimit());

			SmsVO smsVOTemp = null;
			for (Object[] objs : resultFromTemp) {
				smsVOTemp = new SmsVO();
				BeanUtils.copyProperties(smsVOTemp, objs[0]);
				smsVOTemp.setToUserName(objs[1].toString());
				smsVOTemp.setFromUserName(objs[2].toString());
				result.add(smsVOTemp);
			}
			for (Object[] objs : resultToTemp) {
				smsVOTemp = new SmsVO();
				BeanUtils.copyProperties(smsVOTemp, objs[0]);
				smsVOTemp.setToUserName(objs[1].toString());
				smsVOTemp.setFromUserName(objs[2].toString());
				result.add(smsVOTemp);
			}
		}
		putByRequest("result", result);
		return "querySms";
	}
}
