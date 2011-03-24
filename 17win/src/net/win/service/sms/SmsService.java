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

@SuppressWarnings({"unchecked"})
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
		putIndexShowType("sms");
		String toUser = getByParam("toUser");
		if (toUser != null) {
			String toUsername = new String(getByParam("toUser"));
			toUsername = new String(toUsername.getBytes("ISO-8859-1"), "UTF-8");
			smsVO.setToUserName(toUsername);
		}
		return "initSendSms";
	}

	/**
	 * 删除
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String deleteSjxSms(SmsVO smsVO) throws Exception {
		Long[] sjxIds = smsVO.getSjSmsIDs();
		for (Long id : sjxIds) {
			smsDAO.deleteByHql("delete from SmsEntity where id=:smsID",
					new String[]{"smsID"}, new Object[]{id});
		}
		querySJXSms(smsVO);
		putAlertMsg("删除成功！");
		return "deleteSms";
	}

	/**
	 * 删除
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String deleteFjxSms(SmsVO smsVO) throws Exception {
		Long[] fjxIds = smsVO.getFjSmsIDs();
		for (Long id : fjxIds) {
			smsDAO.deleteByHql("delete from SmsEntity where id=:smsID",
					new String[]{"smsID"}, new Object[]{id});
		}
		queryFJXSms(smsVO);
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
		putTokenBySession();
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
		putJumpSelfPage("smsManager/sms!initSendSms.php");
		SmsEntity smsEntity = new SmsEntity();
		UserEntity toUser = userDAO.findUserByName(smsVO.getToUserName());
		if (smsVO.getToUserName().equals(getLoginUser().getUsername())) {
			putAlertMsg("不能给自己发送短信！");
			return JUMP;
		}
		if (toUser == null) {
			putAlertMsg("没有该用户！");
			return JUMP;
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
		return JUMP;
	}

	/**
	 * 发送手机短信
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String updateSendTelphone() throws Exception {

		putJumpSelfPage("smsManager/sms!initSendTelphone.php");
		try {
			String telphone = getByParam("telehpne");
			String content = getByParam("content");
			String sendType = getByParam("sendType");
			String opertaionCode = getByParam("opertaionCode");
			UserEntity userEntity = getLoginUserEntity(userDAO);
			if (!userEntity.getOpertationCode().equals(
					StringUtils.processPwd(opertaionCode))) {
				putAlertMsg("操作码不正确！");
				return JUMP;
			}
			if (sendType.equals("1")) {
				UserEntity toUser = userDAO.findUserByName(telphone);
				if (toUser == null) {
					putAlertMsg("用户不存在！");
					return JUMP;
				} else {
					telphone = toUser.getTelephone();
				}
			}
			MsgUtils.sendMsg(telphone, content);
			putAlertMsg("发送成功");
		} catch (Exception e) {
			LoggerUtils.error(e);
			putAlertMsg("发送失败，请联系管理员！");
		}
		return JUMP;
	}

	/**
	 * 查询收件箱短信
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String querySJXSms(SmsVO smsVO) throws Exception {
		smsVO.setQueryTypeFlag("sjx");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		StringBuffer resultFromHQL = new StringBuffer(
				"select  _sms ,_toUser.username,_fromUser.username from SmsEntity _sms inner join _sms.fromUser as _fromUser  inner join _sms.toUser as _toUser where _toUser.id=:userId  ");
		StringBuffer countFromHQL = new StringBuffer(
				"select  count(*)  from SmsEntity _sms inner join _sms.fromUser as _fromUser inner join _sms.toUser as _toUser  where _toUser.id=:userId  ");
		List<String> paramNames = new ArrayList<String>();
		paramNames.add("userId");
		List<Object> paramValues = new ArrayList<Object>();
		paramValues.add(userEntity.getId());
		// 类型
		if (!StringUtils.isBlank(smsVO.getType())) {
			resultFromHQL.append(" and _sms.type=:type ");
			countFromHQL.append(" and _sms.type=:type ");

			paramNames.add("type");
			paramValues.add(smsVO.getType());
		}

		// 时间
		if (smsVO.getStartDate() != null && smsVO.getEndDate() != null) {

			resultFromHQL
					.append(" and (_sms.sendDate>=:startDate and _sms.sendDate<=:endDate) ");
			countFromHQL
					.append(" and (_sms.sendDate>=:startDate and _sms.sendDate<=:endDate) ");

			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(smsVO.getStartDate());
			paramValues.add(smsVO.getEndDate());
		} else if (smsVO.getStartDate() != null) {

			resultFromHQL.append(" and _sms.sendDate>=:startDate  ");
			countFromHQL.append(" and  _sms.sendDate>=:startDate   ");

			paramNames.add("startDate");
			paramValues.add(smsVO.getStartDate());
		} else if (smsVO.getEndDate() != null) {

			resultFromHQL.append(" and   _sms.sendDate<=:endDate ");
			countFromHQL.append(" and   _sms.sendDate<=:endDate  ");

			paramNames.add("endDate");
			paramValues.add(smsVO.getEndDate());
		}

		Long count = (Long) smsDAO.uniqueResultObject(countFromHQL.toString(),
				paramNames.toArray(paramNames.toArray(new String[paramNames
						.size()])), paramValues.toArray(new Object[paramValues
						.size()]));
		List<SmsVO> result = new ArrayList<SmsVO>();
		if (count > 0) {
			smsVO.setDataCount(count.intValue());
			List<Object[]> resultFromTemp = smsDAO.pageQuery(resultFromHQL
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
		}
		putByRequest("result", result);
		return "querySms";
	}

	/**
	 * 查询收件箱短信
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String queryFJXSms(SmsVO smsVO) throws Exception {
		smsVO.setQueryTypeFlag("fjx");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		StringBuffer resultToHQL = new StringBuffer(
				"select _sms ,_toUser.username,_fromUser.username from SmsEntity _sms inner join _sms.toUser as _toUser inner join _sms.fromUser as _fromUser where _fromUser.id=:userId ");
		StringBuffer countToHQL = new StringBuffer(
				"select count(*) from SmsEntity _sms inner join _sms.toUser as _toUser inner join _sms.fromUser as _fromUser where _fromUser.id=:userId ");

		List<String> paramNames = new ArrayList<String>();
		paramNames.add("userId");
		List<Object> paramValues = new ArrayList<Object>();
		paramValues.add(userEntity.getId());
		// 类型
		if (!StringUtils.isBlank(smsVO.getType())) {
			resultToHQL.append(" and _sms.type=:type ");
			countToHQL.append(" and _sms.type=:type ");

			paramNames.add("type");
			paramValues.add(smsVO.getType());
		}

		// 时间
		if (smsVO.getStartDate() != null && smsVO.getEndDate() != null) {
			resultToHQL
					.append(" and (_sms.sendDate>=:startDate and _sms.sendDate<=:endDate) ");
			countToHQL
					.append(" and (_sms.sendDate>=:startDate and  _sms.sendDate<=:endDate) ");
			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(smsVO.getStartDate());
			paramValues.add(smsVO.getEndDate());
		} else if (smsVO.getStartDate() != null) {

			resultToHQL.append(" and _sms.sendDate>=:startDate ");
			countToHQL.append(" and _sms.sendDate>=:startDate ");

			paramNames.add("startDate");
			paramValues.add(smsVO.getStartDate());
		} else if (smsVO.getEndDate() != null) {
			resultToHQL.append(" and _sms.sendDate<=:endDate ");
			countToHQL.append(" and _sms.sendDate<=:endDate ");

			paramNames.add("endDate");
			paramValues.add(smsVO.getEndDate());
		}

		Long count = (Long) smsDAO.uniqueResultObject(countToHQL.toString(),
				paramNames.toArray(paramNames.toArray(new String[paramNames
						.size()])), paramValues.toArray(new Object[paramValues
						.size()]));
		List<SmsVO> result = new ArrayList<SmsVO>();
		if (count > 0) {
			smsVO.setDataCount(count.intValue());
			List<Object[]> resultToTemp = smsDAO.pageQuery(resultToHQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), smsVO.getStart(),
					smsVO.getLimit());

			SmsVO smsVOTemp = null;
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
