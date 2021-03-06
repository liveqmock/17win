package net.win.service.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.TaskMananger;
import net.win.dao.CreditTaskDAO;
import net.win.dao.UserDAO;
import net.win.entity.CreditTaskEntity;
import net.win.entity.UserEntity;
import net.win.stragegy.ScoreStrategy;
import net.win.utils.ArithUtils;
import net.win.utils.StringUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("adminTaskService")
public class AdminTaskService extends BaseService {
	@Resource
	private CreditTaskDAO creditTaskDAO;
	@Resource
	private UserDAO userDAO;

	/**
	 * 改变用户状态
	 * 
	 * @param adminUserVO
	 * @return
	 * @throws Exception
	 */
	public String initCreditTask(AdminCreditTaskVO adminCreditTaskVO)
			throws Exception {
		StringBuffer resultHQL = new StringBuffer(
				"select _task,_releaseUser.username,_receiveUser.username from  CreditTaskEntity as  _task "
						+ "left join _task.releasePerson as _releaseUser "
						+ "left join _task.receivePerson as _receiveUser where 1=1 ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)   from  CreditTaskEntity as  _task  "
						+ " left join _task.releasePerson as _releaseUser "
						+ " left join _task.receivePerson as _receiveUser where 1=1 ");
		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();
		// 任务ID
		if (!StringUtils.isBlank(adminCreditTaskVO.getTestID())) {
			resultHQL.append(" and _task.testID = :testID ");
			countHQL.append(" and _task.testID = :testID ");
			paramNames.add("testID");
			paramValues.add(adminCreditTaskVO.getTestID().trim());
		}
		// 状态
		if (!StringUtils.isBlank(adminCreditTaskVO.getStatus())) {
			resultHQL.append(" and _task.status = :status ");
			countHQL.append(" and _task.status = :status ");
			paramNames.add("status");
			paramValues.add(adminCreditTaskVO.getStatus());
		}
		// 类型
		if (!StringUtils.isBlank(adminCreditTaskVO.getType())) {
			resultHQL.append(" and _task.type = :type ");
			countHQL.append(" and _task.type = :type ");
			paramNames.add("type");
			paramValues.add(adminCreditTaskVO.getType());
		}
		Long count = (Long) creditTaskDAO.uniqueResultObject(countHQL
				.toString(), paramNames.toArray(paramNames
				.toArray(new String[paramNames.size()])), paramValues
				.toArray(new Object[paramValues.size()]));
		List<AdminCreditTaskVO> result = new ArrayList<AdminCreditTaskVO>();
		if (count > 0) {
			resultHQL.append(" order by _task.releaseDate desc");
			adminCreditTaskVO.setDataCount(count.intValue());
			List<Object[]> resultTemp = creditTaskDAO.pageQuery(resultHQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), adminCreditTaskVO
					.getStart(), adminCreditTaskVO.getLimit());
			AdminCreditTaskVO adminCreditTaskVO2 = null;
			for (Object[] objects : resultTemp) {
				adminCreditTaskVO2 = new AdminCreditTaskVO();
				BeanUtils.copyProperties(adminCreditTaskVO2, objects[0]);
				adminCreditTaskVO2.setReleaseUser((String) objects[1]);
				adminCreditTaskVO2.setReceiveUser((String) objects[2]);
				result.add(adminCreditTaskVO2);
			}
		}
		putByRequest("result", result);
		return "initCreditTask";
	}

	/**
	 * 撤销支付
	 * 
	 * @param creditTaskVO
	 * @return
	 */
	public String updateRollbackPay(AdminCreditTaskVO adminCreditTaskVO)
			throws Exception {
		putJumpSelfPage("adminTaskManager/adminTask!initCreditTask.php");
		CreditTaskEntity creditTask = creditTaskDAO.get(adminCreditTaskVO
				.getId());
		if (!creditTask.getStatus().equals(TaskMananger.STEP_THREE_STATUS)) {
			putAlertMsg("状态已经改变！");
			return JUMP;
		}
		/**
		 * 任务
		 */
		Date currOperDate = creditTask.getReceiveDate();
		Long minuties = ((System.currentTimeMillis() - currOperDate.getTime()) / 1000 / 60);
		/**
		 * 真正的逻辑 修改时间
		 */
		creditTask.setRemainTime(minuties + 20);
		creditTask.setStatus(TaskMananger.STEP_TWO_STATUS);
		putAlertMsg("撤销成功！");
		return JUMP;
	}

	/**
	 * 卖家发货
	 */
	public String updateDispatch(AdminCreditTaskVO adminCreditTaskVO)
			throws Exception {
		putJumpSelfPage("adminTaskManager/adminTask!initCreditTask.php");
		// 没有操作码验证就验证
		CreditTaskEntity creditTask = creditTaskDAO.get(adminCreditTaskVO
				.getId());
		if (!creditTask.getStatus().equals(TaskMananger.STEP_THREE_STATUS)) {
			putAlertMsg("状态已改变！");
			return JUMP;
		}
		/**
		 * 验证
		 */
		//		String goodType = creditTask.getGoodTimeType();
		//		if (!"1".equals(goodType)) {
		//			creditTask.setRemainTime(creditTask.getIntervalHour());
		//			creditTask.setDispatchDate(new Date());
		//		}
		creditTask.setStatus(TaskMananger.STEP_FOUR_STATUS);
		putAlertMsg("修改成功！");
		return JUMP;
	}

	/** ************ */
	/**
	 * 买家付款
	 * 
	 * @param creditTaskVO
	 * @return
	 */
	public String updatePayTask(AdminCreditTaskVO adminCreditTaskVO)
			throws Exception {
		putJumpSelfPage("adminTaskManager/adminTask!initCreditTask.php");
		CreditTaskEntity creditTask = creditTaskDAO.get(adminCreditTaskVO
				.getId());
		if (!creditTask.getStatus().equals(TaskMananger.STEP_FOUR_STATUS)) {
			putAlertMsg("状态已改变！");
			return JUMP;
		}
		/**
		 * 任务
		 */
		creditTask.setRemainTime(0L);
		creditTask.setStatus(TaskMananger.STEP_THREE_STATUS);
		putAlertMsg("修改成功！");
		return JUMP;
	}

	/**
	 * 买家好评
	 */
	public String updateBuyerEvaluate(AdminCreditTaskVO adminCreditTaskVO)
			throws Exception {
		putJumpSelfPage("adminTaskManager/adminTask!initCreditTask.php");
		CreditTaskEntity creditTask = creditTaskDAO.get(adminCreditTaskVO
				.getId());
		if (!creditTask.getStatus().equals(TaskMananger.STEP_FOUR_STATUS)) {
			putAlertMsg("状态已改变！");
			return JUMP;
		}
		/**
		 * 验证
		 */
		creditTask.setStatus(TaskMananger.STEP_FIVE_STATUS);

		return JUMP;
	}

	/** *** */
	/**
	 * 卖家好评 任务完成
	 */
	public String updateSellerEvaluate(AdminCreditTaskVO adminCreditTaskVO)
			throws Exception {
		putJumpSelfPage("adminTaskManager/adminTask!initCreditTask.php");
		// 没有操作码验证就验证
		CreditTaskEntity creditTask = creditTaskDAO.get(adminCreditTaskVO
				.getId());
		UserEntity receiveUser = creditTask.getReceivePerson();
		UserEntity releaseUser = creditTask.getReleasePerson();
		if (!creditTask.getStatus().equals(TaskMananger.STEP_FIVE_STATUS)) {
			putAlertMsg("状态已改变！");
			return JUMP;
		}
		/**
		 * 人
		 */
		/**
		 * 修改积分发送人
		 */
		Integer releaseScore = 5;
		releaseUser.setUpgradeScore(releaseUser.getUpgradeScore()
				+ releaseScore);
		logScoreCapital(userDAO, releaseScore + 0.0, "您发起的"
				+ creditTask.getTestID() + "任务完成，获得积分", releaseUser);
		/**
		 * 修改积分和钱 接手人 和 接手号
		 */

		Integer receieveScore = 5;
		receiveUser.setMoney(ArithUtils.add(receiveUser.getMoney(), creditTask
				.getMoney()
				+ creditTask.getAddtionMoney()));
		receiveUser.setUpgradeScore(receiveUser.getUpgradeScore()
				+ receieveScore);
		logScoreCapital(userDAO, receieveScore + 0.0, "您接手的"
				+ creditTask.getTestID() + "任务完成，获得积分", receiveUser);
		/**
		 * 计算发布和任务数
		 */
		releaseUser.setReleaseTaskCount(releaseUser.getReleaseTaskCount() + 1);
		receiveUser.setReceiveTaskCount(receiveUser.getReceiveTaskCount() + 1);

		creditTask.setStatus(TaskMananger.STEP_SIX_STATUS);
		putAlertMsg("好评成功，任务完成！");

		// 通过你的宣传链接注册的会员积分每上升1000
		// 你的收益=100积分
		ScoreStrategy.updateRefreeScoreByScore(userDAO, receiveUser);
		// 记录 信息

		logMoneyCapital(userDAO, creditTask.getMoney()
				+ creditTask.getAddtionMoney(), "接手任务获取金额", receiveUser);

		// 更新信息
		updateUserLoginInfo(releaseUser);
		return JUMP;
	}
}
