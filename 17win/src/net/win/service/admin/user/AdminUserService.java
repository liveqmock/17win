package net.win.service.admin.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.PayDAO;
import net.win.dao.SmsDAO;
import net.win.dao.UserDAO;
import net.win.entity.PayEntity;
import net.win.entity.SmsEntity;
import net.win.entity.UserEntity;
import net.win.utils.ArithUtils;
import net.win.utils.MailUtils;
import net.win.utils.StringUtils;
import net.win.vo.AdminUserVO;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SuppressWarnings( { "unchecked" })
@Service("adminUserService")
public class AdminUserService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private SmsDAO smsDAO;
	@Resource
	private PayDAO payDAO;
	@Resource
	private JavaMailSender mailSender;
	@Resource
	private FreeMarkerConfigurer freeMarkerCfj;

	/**
	 * 改变用户状态
	 * 
	 * @param adminUserVO
	 * @return
	 * @throws Exception
	 */
	public String updateStatus(AdminUserVO adminUserVO) throws Exception {
		Long id = Long.parseLong(getByParam("userId"));
		String status = getByParam("status");
		String statusDesc = getByParam("statusDesc");
		UserEntity userEntity = userDAO.get(id);
		userEntity.setStatus(status);
		userEntity.setStatusDesc(statusDesc);
		queryUser(adminUserVO);
		putAlertMsg("修改成功！");
		putJumpSelfPage("admin/adminUserManager/adminUser!queryUser.php");
		return JUMP;
	}

	/**
	 * 删除人员
	 * 
	 * @param adminUserVO
	 * @return
	 * @throws Exception
	 */
	public String deleteUser(AdminUserVO adminUserVO) throws Exception {
		String[] userIDs = getByParams("userIDs");
		if (userIDs != null && userIDs.length > 0) {
			for (String userID : userIDs) {
				userDAO.deleteById(Long.parseLong(userID));
			}
			putAlertMsg("删除成功！");
		} else {
			putAlertMsg("请选择用户！");
		}
		putJumpSelfPage("admin/adminUserManager/adminUser!queryUser.php");
		return JUMP;
	}

	/**
	 * 发送邮件
	 * 
	 * @param adminUserVO
	 * @return
	 * @throws Exception
	 */
	public String sendMail(AdminUserVO adminUserVO) throws Exception {
		String userIDs = getByParam("userIdses");
		String content = getByParam("mailContent");
		String subject = getByParam("mailSubjct");
		if (userIDs != null) {
			for (String userID : userIDs.split(",")) {
				UserEntity userEntity = userDAO.get(Long.parseLong(userID));
				MailUtils.sendCommonMail(mailSender, freeMarkerCfj, subject,
						content, userEntity.getEmail());
			}
			putAlertMsg("发送成功！");
		} else {
			putAlertMsg("请选择用户！");
		}
		putJumpSelfPage("admin/adminUserManager/adminUser!queryUser.php");
		return JUMP;
	}

	/**
	 * 发送邮件
	 * 
	 * @param adminUserVO
	 * @return
	 * @throws Exception
	 */
	public String insertSendSms(AdminUserVO adminUserVO) throws Exception {
		String userIDs = getByParam("userSmsIdses");
		String smsTitle = getByParam("smsTitle");
		String smsContent = getByParam("smsContent");
		UserEntity fromUser = getLoginUserEntity(userDAO);
		if (userIDs != null) {
			SmsEntity smsEntity = null;
			for (String userID : userIDs.split(",")) {
				smsEntity = new SmsEntity();
				UserEntity userEntity = userDAO.get(Long.parseLong(userID));
				smsEntity.setFromUser(fromUser);
				smsEntity.setRead(false);
				smsEntity.setContent(smsContent);
				smsEntity.setTitle(smsTitle);
				smsEntity.setToUser(userEntity);
				smsEntity.setType("1");
				smsEntity.setSendDate(new Date());
				smsDAO.save(smsEntity);
			}
			putAlertMsg("发送成功！");
		} else {
			putAlertMsg("请选择用户！");
		}
		putJumpSelfPage("admin/adminUserManager/adminUser!queryUser.php");
		return JUMP;
	}

	/**
	 * 充值金额
	 * 
	 * @param adminUserVO
	 * @return
	 * @throws Exception
	 */
	public String updateUserMoney(AdminUserVO adminUserVO) throws Exception {

		Double money = Double.parseDouble(getByParam("money"));
		String desc = getByParam("moneyDesc");
		Long id = Long.parseLong(getByParam("userId"));
		UserEntity userEntity = userDAO.get(id);
		userEntity.setMoney(ArithUtils.add(userEntity.getMoney(), money));
		queryUser(adminUserVO);
		logMoneyCapital(userDAO, money, desc, userEntity);

		PayEntity payEntity = new PayEntity();
		payEntity.setMoney(money);
		payEntity.setPayDate(new Date());
		payEntity.setStatus("2");
		payDAO.save(payEntity);
		payEntity.setUser(userEntity);
		putAlertMsg("充值金额成功！");
		putJumpSelfPage("admin/adminUserManager/adminUser!queryUser.php");
		return JUMP;
	}

	/**
	 * 充值发布点
	 * 
	 * @param adminUserVO
	 * @return
	 * @throws Exception
	 */
	public String updateUserReleaseDot(AdminUserVO adminUserVO)
			throws Exception {
		Double releaseDot = Double.parseDouble(getByParam("releaseDot"));
		String desc = getByParam("releaseDotDesc");
		Long id = Long.parseLong(getByParam("userId"));
		UserEntity userEntity = userDAO.get(id);
		userEntity.setReleaseDot(ArithUtils.add(userEntity.getReleaseDot(),
				releaseDot));
		queryUser(adminUserVO);
		logDotCapital(userDAO, releaseDot, desc, userEntity);
		putAlertMsg("充值发布点成功！");
		putJumpSelfPage("admin/adminUserManager/adminUser!queryUser.php");
		return JUMP;
	}

	/**
	 * 查询所有用户
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String queryUser(AdminUserVO adminUserVO) throws Exception {
		StringBuffer resultHQL = new StringBuffer(
				"select  _user.username,_user.releaseDot,_user.money,_user.registerTime,_user.email,_user.telephone," // 5
						+ "_user.spreadScore,_user.spreadScore,_user.releaseTaskCount,_user.receiveTaskCount,_user.vipEnable ,"// 10
						+ " _user.status,_user.id,_user.statusDesc" // 13
						+ " from UserEntity   as _user  where 1=1 ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)  from UserEntity  as _user  where 1=1  ");
		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();
		// 用户名
		if (!StringUtils.isBlank(adminUserVO.getUsername())) {
			resultHQL.append(" and _user.username like :username ");
			countHQL.append(" and _user.username like :username ");
			paramNames.add("username");
			paramValues.add("%" + adminUserVO.getUsername() + "%");
		}
		// 发布点
		if (adminUserVO.getStartReleaseDot() != null
				&& adminUserVO.getEndReleaseDot() != null) {
			resultHQL
					.append(" and (_user.releaseDot>=:startReleaseDot and _user.releaseDot<=:endReleaseDot) ");
			countHQL
					.append(" and (_user.releaseDot>=:startReleaseDot and _user.releaseDot<=:endReleaseDot) ");
			paramNames.add("startReleaseDot");
			paramNames.add("endReleaseDot");
			paramValues.add(adminUserVO.getStartReleaseDot());
			paramValues.add(adminUserVO.getEndReleaseDot());
		} else if (adminUserVO.getStartReleaseDot() != null) {
			resultHQL.append(" and _user.releaseDot>=:startReleaseDot  ");
			countHQL.append(" and _user.releaseDot>=:startReleaseDot   ");
			paramNames.add("startReleaseDot");
			paramValues.add(adminUserVO.getStartReleaseDot());
		} else if (adminUserVO.getEndReleaseDot() != null) {
			resultHQL.append(" and   _user.releaseDot<=:endReleaseDot  ");
			countHQL.append(" and   _user.releaseDot<=:endReleaseDot  ");
			paramNames.add("endReleaseDot");
			paramValues.add(adminUserVO.getEndReleaseDot());
		}
		// /钱
		if (adminUserVO.getStartMoney() != null
				&& adminUserVO.getEndMoney() != null) {
			resultHQL
					.append(" and (_user.money>=:startMoney and _user.money<=:endMoney) ");
			countHQL
					.append(" and (_user.money>=:startMoney and _user.money<=:endMoney) ");
			paramNames.add("startMoney");
			paramNames.add("endMoney");
			paramValues.add(adminUserVO.getStartMoney());
			paramValues.add(adminUserVO.getEndMoney());
		} else if (adminUserVO.getStartMoney() != null) {
			resultHQL.append(" and _user.money>=:startMoney  ");
			countHQL.append(" and  _user.money>=:startMoney   ");
			paramNames.add("startMoney");
			paramValues.add(adminUserVO.getStartMoney());
		} else if (adminUserVO.getEndMoney() != null) {
			resultHQL.append(" and   _user.money<=:endMoney  ");
			countHQL.append(" and   _user.money<=:endMoney  ");
			paramNames.add("endMoney");
			paramValues.add(adminUserVO.getEndMoney());
		}
		// 注册时间
		if (adminUserVO.getRegeditStartDate() != null
				&& adminUserVO.getRegeditEndDate() != null) {
			resultHQL
					.append(" and (_user.registerTime>=:startRegisterTime and _user.registerTime<=:endRegisterTime) ");
			countHQL
					.append(" and (_user.registerTime>=:startRegisterTime and _user.registerTime<=:endRegisterTime) ");
			paramNames.add("startRegisterTime");
			paramNames.add("endRegisterTime");
			paramValues.add(adminUserVO.getRegeditStartDate());
			paramValues.add(adminUserVO.getRegeditEndDate());
		} else if (adminUserVO.getRegeditStartDate() != null) {
			resultHQL.append(" and _user.registerTime>=:startRegisterTime  ");
			countHQL.append(" and  _user.registerTime>=:startRegisterTime   ");
			paramNames.add("startRegisterTime");
			paramValues.add(adminUserVO.getRegeditStartDate());
		} else if (adminUserVO.getRegeditEndDate() != null) {
			resultHQL.append(" and   _user.registerTime<=:endRegisterTime  ");
			countHQL.append(" and   _user.registerTime<=:endRegisterTime  ");
			paramNames.add("endRegisterTime");
			paramValues.add(adminUserVO.getRegeditEndDate());
		}
		// 电子邮箱
		if (!StringUtils.isBlank(adminUserVO.getEmail())) {
			resultHQL.append(" and _user.email like :email ");
			countHQL.append(" and _user.email like :email ");
			paramNames.add("email");
			paramValues.add("%" + adminUserVO.getEmail() + "%");
		}
		// 电话
		if (!StringUtils.isBlank(adminUserVO.getTelphone())) {
			resultHQL.append(" and _user.telephone like :telephone ");
			countHQL.append(" and _user.telephone like :telephone ");
			paramNames.add("telephone");
			paramValues.add("%" + adminUserVO.getTelphone() + "%");
		}
		// 推广积分
		if (adminUserVO.getStartSpreadScore() != null
				&& adminUserVO.getEndSpreadScore() != null) {
			resultHQL
					.append(" and (_user.spreadScore>=:startSpreandScore and _user.spreadScore<=:endSpreandScore) ");
			countHQL
					.append(" and (_user.spreadScore>=:startSpreandScore and _user.spreadScore<=:endSpreandScore) ");
			paramNames.add("startSpreandScore");
			paramNames.add("endSpreandScore");
			paramValues.add(adminUserVO.getStartSpreadScore());
			paramValues.add(adminUserVO.getEndSpreadScore());
		} else if (adminUserVO.getStartSpreadScore() != null) {
			resultHQL.append(" and _user.spreadScore>=:startSpreandScore  ");
			countHQL.append(" and  _user.spreadScore>=:startSpreandScore   ");
			paramNames.add("startSpreandScore");
			paramValues.add(adminUserVO.getStartSpreadScore());
		} else if (adminUserVO.getEndSpreadScore() != null) {
			resultHQL.append(" and   _user.spreadScore<=:endSpreandScore  ");
			countHQL.append(" and   _user.spreadScore<=:endSpreandScore  ");
			paramNames.add("endSpreandScore");
			paramValues.add(adminUserVO.getEndSpreadScore());
		}
		// 发任务数
		if (adminUserVO.getStartReleaseTaskCount() != null
				&& adminUserVO.getEndReleaseTaskCount() != null) {
			resultHQL
					.append(" and (_user.releaseTaskCount>=:startReleaseTaskCount and _user.releaseTaskCount<=:endReleaseTaskCount) ");
			countHQL
					.append(" and (_user.releaseTaskCount>=:startReleaseTaskCount and _user.releaseTaskCount<=:endReleaseTaskCount) ");
			paramNames.add("startReleaseTaskCount");
			paramNames.add("endReleaseTaskCount");
			paramValues.add(adminUserVO.getStartReleaseTaskCount());
			paramValues.add(adminUserVO.getEndReleaseTaskCount());
		} else if (adminUserVO.getStartReleaseTaskCount() != null) {
			resultHQL
					.append(" and _user.releaseTaskCount>=:startReleaseTaskCount  ");
			countHQL
					.append(" and  _user.releaseTaskCount>=:startReleaseTaskCount   ");
			paramNames.add("startReleaseTaskCount");
			paramValues.add(adminUserVO.getStartReleaseTaskCount());
		} else if (adminUserVO.getEndReleaseTaskCount() != null) {
			resultHQL
					.append(" and   _user.releaseTaskCount<=:endReleaseTaskCount  ");
			countHQL
					.append(" and   _user.releaseTaskCount<=:endReleaseTaskCount  ");
			paramNames.add("endReleaseTaskCount");
			paramValues.add(adminUserVO.getEndReleaseTaskCount());
		}
		// 接任务数
		if (adminUserVO.getStartReceieveTaskCount() != null
				&& adminUserVO.getEndReceieveTaskCount() != null) {
			resultHQL
					.append(" and (_user.receiveTaskCount>=:startReceiveTaskCount and _user.receiveTaskCount<=:endReceiveTaskCount) ");
			countHQL
					.append(" and (_user.receiveTaskCount>=:startReceiveTaskCount and _user.receiveTaskCount<=:endReceiveTaskCount) ");
			paramNames.add("startReceiveTaskCount");
			paramNames.add("endReceiveTaskCount");
			paramValues.add(adminUserVO.getStartReceieveTaskCount());
			paramValues.add(adminUserVO.getEndReceieveTaskCount());
		} else if (adminUserVO.getStartReceieveTaskCount() != null) {
			resultHQL
					.append(" and _user.receiveTaskCount>=:startReceiveTaskCount  ");
			countHQL
					.append(" and  _user.receiveTaskCount>=:startReceiveTaskCount   ");
			paramNames.add("startReceiveTaskCount");
			paramValues.add(adminUserVO.getStartReceieveTaskCount());
		} else if (adminUserVO.getEndReceieveTaskCount() != null) {
			resultHQL
					.append(" and   _user.receiveTaskCount<=:endReceiveTaskCount  ");
			countHQL
					.append(" and   _user.receiveTaskCount<=:endReceiveTaskCount  ");
			paramNames.add("endReceiveTaskCount");
			paramValues.add(adminUserVO.getEndReceieveTaskCount());
		}
		// 是否VIP
		if (!StringUtils.isBlank(adminUserVO.getVipEnable())) {
			resultHQL.append(" and _user.vipEnable=:vipEnable ");
			countHQL.append(" and _user.vipEnable=:vipEnable ");
			paramNames.add("vipEnable");
			paramValues.add(new Boolean(adminUserVO.getVipEnable()));
		}
		// 状态
		if (!StringUtils.isBlank(adminUserVO.getStatus())) {
			resultHQL.append(" and _user.status=:status ");
			countHQL.append(" and _user.status=:status ");
			paramNames.add("status");
			paramValues.add(adminUserVO.getStatus());
		}
		// // 成长值
		// if (adminUserVO.getStartVipGrowValue() != null
		// && adminUserVO.getEndVipGrowValue() != null) {
		// resultHQL
		// .append(" and (_user.vipGrowValue>=:startVipGrowValue and
		// _user.operationDate<=:endVipGrowValue) ");
		// countHQL
		// .append(" and (_user.vipGrowValue>=:startVipGrowValue and
		// _user.operationDate<=:endVipGrowValue) ");
		// paramNames.add("startVipGrowValue");
		// paramNames.add("endVipGrowValue");
		// paramValues.add(adminUserVO.getStartVipGrowValue());
		// paramValues.add(adminUserVO.getEndVipGrowValue());
		// } else if (adminUserVO.getStartVipGrowValue() != null) {
		// resultHQL.append(" and _user.vipGrowValue>=:startVipGrowValue ");
		// countHQL.append(" and _user.vipGrowValue>=:startVipGrowValue ");
		// paramNames.add("startVipGrowValue");
		// paramValues.add(adminUserVO.getStartVipGrowValue());
		// } else if (adminUserVO.getEndVipGrowValue() != null) {
		// resultHQL.append(" and _user.vipGrowValue<=:endVipGrowValue ");
		// countHQL.append(" and _user.vipGrowValue<=:endVipGrowValue ");
		// paramNames.add("endVipGrowValue");
		// paramValues.add(adminUserVO.getEndVipGrowValue());
		// }
		Long count = (Long) userDAO.uniqueResultObject(countHQL.toString(),
				paramNames.toArray(paramNames.toArray(new String[paramNames
						.size()])), paramValues.toArray(new Object[paramValues
						.size()]));
		List<Object[]> result = new ArrayList<Object[]>();
		if (count > 0) {
			adminUserVO.setDataCount(count.intValue());
			result = userDAO.pageQuery(resultHQL.toString(),
					paramNames.toArray(paramNames.toArray(new String[paramNames
							.size()])), paramValues
							.toArray(new Object[paramValues.size()]),
					adminUserVO.getStart(), adminUserVO.getLimit());
		}
		putByRequest("result", result);
		return "queryUser";
	}
}
