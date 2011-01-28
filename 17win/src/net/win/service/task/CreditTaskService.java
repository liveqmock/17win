package net.win.service.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.win.BaseService;
import net.win.TaskMananger;
import net.win.UserLoginInfo;
import net.win.dao.BuyerDAO;
import net.win.dao.CreditTaskDAO;
import net.win.dao.CreditTaskRepositoryDAO;
import net.win.dao.SellerDAO;
import net.win.dao.TaskLinkManDAO;
import net.win.dao.UserDAO;
import net.win.dao.VipDAO;
import net.win.entity.BuyerEntity;
import net.win.entity.CreditTaskEntity;
import net.win.entity.CreditTaskRepositoryEntity;
import net.win.entity.SellerEntity;
import net.win.entity.TaskLinkManEntity;
import net.win.entity.UserEntity;
import net.win.entity.VipBidUserEntity;
import net.win.entity.VipEntity;
import net.win.stragegy.ScoreStrategy;
import net.win.utils.ArithUtils;
import net.win.utils.Constant;
import net.win.utils.HttpB2CUtils;
import net.win.utils.MsgUtils;
import net.win.utils.StrategyUtils;
import net.win.utils.StringUtils;
import net.win.utils.WinUtils;
import net.win.vo.BuyerVO;
import net.win.vo.CreditTaskRepositoryVO;
import net.win.vo.CreditTaskVO;
import net.win.vo.SellerVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 
 * @author xgj
 * 
 */
@SuppressWarnings({"unused", "unchecked"})
@Service("creditTaskService")
public class CreditTaskService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private TaskLinkManDAO taskLinkManDAO;
	@Resource
	private SellerDAO sellerDAO;
	@Resource
	private BuyerDAO buyerDAO;
	@Resource
	private CreditTaskDAO creditTaskDAO;
	@Resource
	private CreditTaskRepositoryDAO creditTaskRepositoryDAO;

	@Resource
	private VipDAO vipDAO;

	/** ***************************买家操作********************************* */
	/**
	 * 买家好评
	 */
	public String updateBuyerEvaluate(CreditTaskVO creditTaskVO)
			throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = Long.parseLong(getByParam("taskId"));
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			// 如果发布人不是当前的登陆人就报错
			if (creditTask.getStatus().equals(TaskMananger.STEP_FIVE_STATUS)) {
				putAlertMsg("已经好评，不要重复提交！");
				putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			if (!platformType.equals(creditTask.getType())
					|| !creditTask.getReceivePerson().getId().equals(
							getLoginUser().getId())
					|| !creditTask.getStatus().equals(
							TaskMananger.STEP_FOUR_STATUS)) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过【买家好评】操作！任务ID是：" + taskId);
			}
			/**
			 * 验证
			 */
			creditTask.setStatus(TaskMananger.STEP_FIVE_STATUS);
			putAlertMsg("好评成功，通知卖家好评吧！");
			putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
					+ platformType);
			return JUMP;
		}
	}

	/**
	 * 取消支付
	 * 
	 * @param creditTaskVO
	 * @return
	 */
	public String updateRollbackPay(CreditTaskVO creditTaskVO) throws Exception {
		String platformType = getPlatformType();
		UserLoginInfo loginInfo = getLoginUser();
		Long taskId = Long.parseLong(getByParam("taskId"));
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			if (creditTask.getStatus().equals(TaskMananger.STEP_TWO_STATUS)) {
				putAlertMsg("已经撤销，不要重复提交！");
				putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			if (creditTask.getStatus().equals(TaskMananger.STEP_FOUR_STATUS)) {
				putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
						+ platformType);
				putAlertMsg("撤销失败，卖家已发货！");
				return JUMP;
			}
			// 如果发布人不是当前的登陆人就报错
			if (!platformType.equals(creditTask.getType())
					|| !creditTask.getReceivePerson().getId().equals(
							loginInfo.getId())
					|| !creditTask.getStatus().equals(
							TaskMananger.STEP_THREE_STATUS)) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过【撤销支付】操作！任务ID是：" + taskId);
			}
			/**
			 * 任务
			 */
			Date currOperDate = creditTask.getReceiveDate();
			Integer minuties = ((Long) ((System.currentTimeMillis() - currOperDate
					.getTime()) / 1000 / 60)).intValue();
			/**
			 * 真正的逻辑 修改时间
			 */
			creditTask.setRemainTime(minuties + 20);
			creditTask.setStatus(TaskMananger.STEP_TWO_STATUS);
			putAlertMsg("撤销成功！");
		}
		putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
				+ platformType);
		return JUMP;
	}

	/**
	 * 付款操作
	 * 
	 * @param creditTaskVO
	 * @return
	 */
	public String updatePayTask(CreditTaskVO creditTaskVO) throws Exception {
		String platformType = getPlatformType();
		UserLoginInfo loginInfo = getLoginUser();
		Long taskId = Long.parseLong(getByParam("taskId"));
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			if (creditTask.getStatus().equals(TaskMananger.STEP_THREE_STATUS)) {
				putAlertMsg("已经支付，不要重复提交！");
				putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			// 如果发布人不是当前的登陆人就报错
			if (!platformType.equals(creditTask.getType())
					|| !creditTask.getReceivePerson().getId().equals(
							loginInfo.getId())
					|| !creditTask.getStatus().equals(
							TaskMananger.STEP_TWO_STATUS)) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过【已经支付】操作！任务ID是：" + taskId);
			}
			/**
			 * 任务
			 */
			creditTask.setRemainTime(0);
			creditTask.setStatus(TaskMananger.STEP_THREE_STATUS);
		}
		putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
				+ platformType);
		putAlertMsg("支付成功！");
		return JUMP;
	}

	/**
	 * 退出任务
	 * 
	 * @param creditTaskVO
	 * @return
	 */
	public String updateQuitTask(CreditTaskVO creditTaskVO) throws Exception {
		String platformType = getPlatformType();
		UserLoginInfo loginInfo = getLoginUser();
		Long taskId = Long.parseLong(getByParam("taskId"));
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			UserEntity receiveUser = creditTask.getReceivePerson();
			if (creditTask.getStatus().equals(TaskMananger.STEP_ONE_STATUS)) {
				putAlertMsg("已经退出，不要重复提交！");
				putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			if ((!platformType.equals(creditTask.getType()))
					|| (!receiveUser.getId().equals(loginInfo.getId()))
					|| (!platformType.equals(creditTask.getType()))) {
				WinUtils.throwIllegalityException(loginInfo.getUsername()
						+ "试图越过【退出任务】操作！ 任务ID是:" + taskId);
			}
			/**
			 * 验证 如果不是付款，也不是 审核，那么就不能退出任务了。
			 */
			if (!TaskMananger.STEP_TWO_STATUS.equals(creditTask.getStatus())
					&& !TaskMananger.AUDIT_STATUS
							.equals(creditTask.getStatus())) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过【退出任务】操作！ 任务ID是:" + taskId);
			}
			/**
			 * 任务
			 */
			creditTask.setReceivePerson(null);
			creditTask.setReceiveIP(null);
			creditTask.setBuyer(null);
			creditTask.setRemainTime(null);
			creditTask.setReceiveDate(null);
			creditTask.setStatus(TaskMananger.STEP_ONE_STATUS);
			updateUserLoginInfo(receiveUser);
		}
		putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
				+ platformType);
		putAlertMsg("已经退出任务！");
		return JUMP;
	}

	/*
	 * 接手任务
	 * 
	 * @param userVO @return
	 */
	public String updateReceiveTask(CreditTaskVO creditTaskVO) throws Exception {
		String platformType = getPlatformType();

		UserLoginInfo loginInfo = getLoginUser();

		Long taskId = Long.parseLong(getByParam("taskId"));
		CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
		BuyerEntity buyerEntitiy = buyerDAO.get(Long
				.parseLong(getByParam("buyerId")));
		UserEntity userEntity = userDAO.get(loginInfo.getId());
		if (creditTask == null) {
			putAlertMsg("任务已经不存在！");
			putJumpSelfPage("userInfoManager/info!initActiave.php");
			return JUMP;
		}
		if (!StringUtils.isBlank(creditTask.getAssignUser())
				&& !creditTask.getAssignUser().equals(userEntity.getUsername())) {
			putAlertMsg("任务已经不存在！");
			putJumpSelfPage("这是特殊任务，您不是指定的人！");
			return JUMP;
		}
		if (!userEntity.getStatus().equals("1")) {
			switch (Integer.parseInt(userEntity.getStatus())) {
				case 0 :
					putAlertMsg("您当前的【状态】为【未激活状态】，请到个人中心激活！");
					break;
				case 2 :
					putAlertMsg("您当前的【状态】为【冻结状态】，不能发布任务！");
					break;
				case 3 :
					putAlertMsg("您当前的【状态】为【找密码状态】，可能有人试图盗取您的秘密，请联系管理员，不能发布任务！");
					break;
				default :
					putAlertMsg("您当前的【状态】不是【正常状态】，不能发布任务！");
					break;
			}
			putJumpSelfPage("userInfoManager/info!initActiave.php");
			return JUMP;
		}
		if (creditTask.getStatus().equals(TaskMananger.STEP_TWO_STATUS)) {
			if (creditTask.getReceivePerson().getId().equals(
					getLoginUser().getId())) {
				putAlertMsg("您已经接受了此任务，不要重复提交！");
			} else {
				putAlertMsg("任务已经被人别抢先接受！！");
			}
			putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
					+ platformType);
			return JUMP;
		}
		// 验证状态
		if (!platformType.equals(creditTask.getType())
				|| !TaskMananger.STEP_ONE_STATUS.equals(creditTask.getStatus())
				|| buyerEntitiy == null) {
			WinUtils.throwIllegalityException(loginInfo.getUsername()
					+ "试图越过【接受任务】操作！ 任务ID是:" + taskId);
		}

		/**
		 * 验证卖号和买号是否同一个人
		 */
		if (buyerEntitiy.getName().equalsIgnoreCase(
				creditTask.getSeller().getName())) {
			putAlertMsg("买号和卖号不能相同！");
			putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
					+ platformType);
			return JUMP;
		}
		/**
		 * 验证IP
		 */
		String ip = getIpAddr();
		// 一月接一个IP,同一商品一次
		String hqlOne = "select count(*) from UserEntity  as  _buyer  inner join _buyer.receiveCreditTasks as _task where _buyer.id=:bid "
				+ " and _task.receiveIP=:receiveIP  "
				+ " and year(_task.receiveDate)=:year and month(_task.receiveDate)=:month  and  _task.itemUrl=:itemUrl";
		// 一个月同一IP 接同一店铺 6次
		String hqlSix = "select count(*)   from UserEntity  as  _buyer  inner join _buyer.receiveCreditTasks as _task inner join _task.releasePerson.sellers as _seller "
				+ "where _buyer.id=:bid "
				+ "and _task.receiveIP=:receiveIP and year(_task.receiveDate)=:year and month(_task.receiveDate)=:month and  _seller.shopURL=:shopUrl";

		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer month = Calendar.getInstance().get(Calendar.MONTH);
		Boolean refuseFlag = (Long) creditTaskDAO.uniqueResultObject(hqlOne,
				new String[]{"bid", "receiveIP", "year", "month", "itemUrl"},
				new Object[]{buyerEntitiy.getId(), ip, year, month,
						creditTask.getItemUrl()}) == 1;
		if (!refuseFlag) {
			refuseFlag = (Long) creditTaskDAO
					.uniqueResultObject(hqlSix, new String[]{"bid",
							"receiveIP", "year", "month", "shopUrl"},
							new Object[]{buyerEntitiy.getId(), ip, year, month,
									creditTask.getSeller().getShopURL()}) == 6;
		}
		if (refuseFlag) {
			putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
					+ platformType);
			putAlertMsg("为了您和他人的安全，同一商品买号在一个月内只能接手一次，同一店铺的商品买号最多只能接受6次！");
			return JUMP;
		}
		/**
		 * 改变任务属性
		 */
		// 接收人
		creditTask.setReceivePerson(userEntity);
		// Ip
		creditTask.setReceiveIP(ip);
		creditTask.setBuyer(buyerEntitiy);
		creditTask.setRemainTime(20);
		creditTask.setReceiveDate(new Date());
		// 保护就到审核状态
		if (creditTask.getProtect()) {
			creditTask.setStatus(TaskMananger.AUDIT_STATUS);
		} else {
			creditTask.setStatus(TaskMananger.STEP_TWO_STATUS);
		}

		putJumpSelfPage("taskManager/task!initReceivedTast.php?platformType="
				+ platformType);
		putAlertMsg("恭喜您，你已经抢到了此任务！");
		return JUMP;
	}

	/***************************************************************************
	 * 卖家操作
	 */
	/**
	 * 卖家好评 任务完成
	 */
	public String updateSellerEvaluate(CreditTaskVO creditTaskVO)
			throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = Long.parseLong(getByParam("taskId"));
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			UserEntity receiveUser = creditTask.getReceivePerson();
			UserEntity releaseUser = creditTask.getReleasePerson();
			if (creditTask.getStatus().equals(TaskMananger.STEP_SIX_STATUS)) {
				putAlertMsg("已经好评，不要重复提交！");
				putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			// 如果发布人不是当前的登陆人就报错
			if (!platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							getLoginUser().getId())
					|| !creditTask.getStatus().equals(
							TaskMananger.STEP_FIVE_STATUS)) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过【卖家好评】操作！ 任务ID是:" + taskId);
			}
			/**
			 * 人
			 */
			/**
			 * 修改积分发送人
			 */
			VipEntity releaseUserVip = releaseUser.getVip();
			VipBidUserEntity releaseVipBidUser = releaseUser
					.getVipBidUserEntity();
			Integer releaseScore = StrategyUtils.getReleaseUserTaskScore(
					releaseUserVip, releaseUser.getVipEnable());
			releaseUser.setUpgradeScore(releaseUser.getUpgradeScore()
					+ releaseScore);
			releaseUser.setConvertScore(releaseUser.getConvertScore()
					+ releaseScore);
			logScoreCapital(userDAO, releaseScore + 0.0, "您发起的"
					+ creditTask.getTestID() + "任务完成，获得积分", releaseUser);

			// 是会员
			if (releaseUser.getVipEnable() && releaseUserVip != null) {
				releaseVipBidUser.setGrowValue(releaseVipBidUser.getGrowValue()
						+ releaseUserVip.getReleaseGrowValue());
			}

			/**
			 * 修改积分和钱 接收人 和 接受号
			 */
			VipEntity receiveUserVip = receiveUser.getVip();
			VipBidUserEntity receiveVipBidUser = receiveUser
					.getVipBidUserEntity();
			Integer receieveScore = StrategyUtils.getReleaseUserTaskScore(
					receiveUserVip, receiveUser.getVipEnable());
			receiveUser.setMoney(ArithUtils.add(receiveUser.getMoney(),
					creditTask.getMoney() + creditTask.getAddtionMoney()));
			// 有会员
			if (receiveUser.getVipEnable() && receiveUserVip != null) {
				receiveVipBidUser.setGrowValue(receiveVipBidUser.getGrowValue()
						+ receiveUserVip.getReceieveGrowValue());
			}
			/**
			 * 发布点
			 */
			Double releaseDot = creditTask.getReleaseDot()
					* StrategyUtils.getTaskOverDotRate(receiveUser,
							receiveUserVip, receiveUser.getVipEnable());
			receiveUser.setReleaseDot(ArithUtils.add(receiveUser
					.getReleaseDot(), releaseDot
					+ creditTask.getAddtionReleaseDot()));
			/**
			 * 计算积分
			 */
			receiveUser.setUpgradeScore(receiveUser.getUpgradeScore()
					+ receieveScore);
			receiveUser.setConvertScore(receiveUser.getConvertScore()
					+ receieveScore);
			logScoreCapital(userDAO, receieveScore + 0.0, "您接手的"
					+ creditTask.getTestID() + "任务完成获得积分!", receiveUser);
			/**
			 * 计算发布和任务数
			 */
			releaseUser
					.setReleaseTaskCount(releaseUser.getReleaseTaskCount() + 1);
			receiveUser
					.setReceiveTaskCount(receiveUser.getReceiveTaskCount() + 1);
			/**
			 * 计算会员成长值 和升级
			 */
			if (releaseUserVip != null && releaseUser.getVipEnable()) {
				releaseVipBidUser.setGrowValue(releaseVipBidUser.getGrowValue()
						+ StrategyUtils.getReleaseGrowValue(releaseUserVip));
				String vipType = StrategyUtils.getVipType(releaseVipBidUser
						.getGrowValue());
				if (!releaseUserVip.getType().equals(vipType)) {
					releaseUser.setVip(vipDAO.getVIPByType(vipType));
				}
			}
			if (receiveUserVip != null && receiveUser.getVipEnable()) {
				receiveVipBidUser.setGrowValue(receiveVipBidUser.getGrowValue()
						+ StrategyUtils.getReleaseGrowValue(receiveUserVip));
				String vipType = StrategyUtils.getVipType(receiveVipBidUser
						.getGrowValue());
				if (!receiveUserVip.getType().equals(vipType)) {
					receiveUser.setVip(vipDAO.getVIPByType(vipType));
				}
			}
			/**
			 * 会员升级
			 */

			/**
			 * 计算推广
			 */
			// 积累接受100个任务
			if (receiveUser.getReceiveTaskCount() % 100 == 0) {
				UserEntity refereeUser = receiveUser.getReferee();
				if (refereeUser != null) {
					refereeUser.setMoney(Constant.getTask100RefreeMoney()
							+ refereeUser.getMoney());
					logMoneyCapital(userDAO, Constant.getTask100RefreeMoney(),
							"你推广的用户接受了100个任务你获得"
									+ Constant.getTask100RefreeMoney() + "元！",
							refereeUser);
				}
			}
			// 通过你的宣传链接注册的会员积分每上升1000
			// 你的收益=100积分
			ScoreStrategy.updateRefreeScoreByScore(userDAO, receiveUser);
			// 记录 信息

			logMoneyCapital(userDAO, creditTask.getMoney()
					+ creditTask.getAddtionMoney(), "接受任务获取金额", receiveUser);

			logDotCapital(userDAO, releaseDot
					+ creditTask.getAddtionReleaseDot(), "接受任务获取发布点",
					receiveUser);
			// 接手人买好信誉+1
			BuyerEntity buyer = creditTask.getBuyer();
			buyer.setScore(buyer.getScore() + 1);
			if (buyer.getScore() >= Constant.getCreditValueLimit()) {
				buyer.setEnable(false);
			}
			// 更新信息
			updateUserLoginInfo(releaseUser);
			creditTask.setStatus(TaskMananger.STEP_SIX_STATUS);
			putAlertMsg("好评成功，任务完成！");
			putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
					+ platformType);
			return JUMP;
		}
	}

	/**
	 * 卖家发货
	 */
	public String updateDispatch(CreditTaskVO creditTaskVO) throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = Long.parseLong(getByParam("taskId"));
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			if (creditTask.getStatus().equals(TaskMananger.STEP_FOUR_STATUS)) {
				putAlertMsg("已经发货，不要重复提交！");
				putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			/**
			 * 验证
			 */
			if (TaskMananger.STEP_TWO_STATUS.equals(creditTask.getStatus())) {
				putAlertMsg("发货失败，买家已经撤销支付！");
			} else {
				// 如果发布人不是当前的登陆人就报错
				if (!platformType.equals(creditTask.getType())
						|| !creditTask.getReleasePerson().getId().equals(
								getLoginUser().getId())
						|| !creditTask.getStatus().equals(
								TaskMananger.STEP_THREE_STATUS)) {
					WinUtils.throwIllegalityException(getLoginUser()
							.getUsername()
							+ "试图越过【卖家发货】操作！ 任务ID是:" + taskId);
				}
				// String goodType = creditTask.getGoodTimeType();
				// if (!"1".equals(goodType)) {
				// creditTask.setRemainTime(creditTask.getIntervalHour());
				// creditTask.setDispatchDate(new Date());
				// }
				creditTask.setStatus(TaskMananger.STEP_FOUR_STATUS);
				putAlertMsg("发货成功！");
			}
			putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
					+ platformType);
			return JUMP;
		}
	}

	/**
	 * 清理买家
	 */
	public String updateClearReceiver(CreditTaskVO creditTaskVO)
			throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		UserEntity receiveUser = userDAO.get(getLoginUser().getId());
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = Long.parseLong(getByParam("taskId"));
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			if (creditTask.getStatus().equals(TaskMananger.STEP_ONE_STATUS)) {
				putAlertMsg("已经清理，不要重复提交！");
				putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			if (creditTask.getStatus().equals(TaskMananger.STEP_ONE_STATUS)) {
				putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
						+ platformType);
				putAlertMsg("清理成功！");
				return JUMP;
			}
			// 如果发布人不是当前的登陆人就报错
			if (!creditTask.getReleasePerson().getId().equals(
					getLoginUser().getId())
					|| !platformType.equals(creditTask.getType())) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过【清理买家】操作！ 任务ID是:" + taskId);
			}
			/**
			 * 修改
			 */
			/**
			 * 任务
			 */
			creditTask.setReceivePerson(null);
			creditTask.setReceiveIP(null);
			creditTask.setBuyer(null);
			creditTask.setRemainTime(null);
			creditTask.setReceiveDate(null);
			creditTask.setStatus(TaskMananger.STEP_ONE_STATUS);
			putAlertMsg("清理买家成功！");
			putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
					+ platformType);
			return JUMP;
		}
	}

	/**
	 * 审核接手人
	 */
	public String updateAudiReceiver(CreditTaskVO creditTaskVO)
			throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = Long.parseLong(getByParam("taskId"));
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			if (creditTask.getStatus().equals(TaskMananger.STEP_TWO_STATUS)) {
				putAlertMsg("已经审核，不要重复提交！");
				putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			// 如果发布人不是当前的登陆人就报错
			if (!platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							getLoginUser().getId())
					|| !creditTask.getStatus()
							.equals(TaskMananger.AUDIT_STATUS)) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过【审核接收人】操作！ 任务ID是:" + taskId);
			}
			/**
			 * 验证
			 */
			creditTask.setStatus(TaskMananger.STEP_TWO_STATUS);
			putAlertMsg("审核完成，联系买家付款吧！");
			putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
					+ platformType);
			return JUMP;
		}
	}

	/**
	 * 加时
	 */
	public String updateAddTime(CreditTaskVO creditTaskVO) throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = Long.parseLong(getByParam("taskId"));
			UserEntity userEntity = userDAO.get(getLoginUser().getId());
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			if (creditTask.getStatus().equals(TaskMananger.STEP_ONE_STATUS)) {
				putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
						+ platformType);
				putAlertMsg("加时失败，买家已经退出！");
				return JUMP;
			}
			// 如果发布人不是当前的登陆人就报错
			if (!platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							userEntity.getId())) {
				WinUtils.throwIllegalityException(userEntity.getUsername()
						+ "试图越过【卖家加时】操作！ 任务ID是:" + taskId);
			}
			/**
			 * 验证
			 */
			if (TaskMananger.STEP_THREE_STATUS.equals(creditTask.getStatus())) {
				putAlertMsg("加时失败,买家已经付款！");
			} else {

				Date currOperDate = creditTask.getReceiveDate();
				Integer minuties = ((Long) ((System.currentTimeMillis() - currOperDate
						.getTime()) / 1000 / 60)).intValue();
				/**
				 * 真正的逻辑 修改时间
				 */
				creditTask.setRemainTime(minuties + 20);
				putAlertMsg("加时成功！");
			}
			putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
					+ platformType);

			return JUMP;
		}
	}

	/**
	 * 刷新排前
	 * 
	 * @param userVO
	 * @return
	 */
	public String updateToFirstTask(CreditTaskVO creditTaskVO) throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = Long.parseLong(getByParam("taskId"));
			UserEntity userEntity = userDAO.get(getLoginUser().getId());
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			// 如果发布人不是当前的登陆人就报错
			if (!platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							userEntity.getId())) {
				WinUtils.throwIllegalityException(userEntity.getUsername()
						+ "试图越过【刷新排前】操作！ 任务ID是:" + taskId);
			}

			/**
			 * 真正的逻辑 修改时间
			 */
			creditTask.setReleaseDate(new Date());
			putAlertMsg("已经排前！");
			return "updateToFirstTask";
		}
	}

	/**
	 * 取消重填
	 * 
	 * @param userVO
	 * @return
	 */
	public String updateCancelTask(CreditTaskVO creditTaskVO) throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = Long.parseLong(getByParam("taskId"));
			UserEntity userEntity = userDAO.get(getLoginUser().getId());
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			if (creditTask == null) {
				putAlertMsg("已经取消了任务，不要重复提交！");
				putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			if (creditTask.getStatus().equals(TaskMananger.STEP_TWO_STATUS)
					|| creditTask.getStatus().equals(TaskMananger.AUDIT_STATUS)) {
				putAlertMsg("取消失败，任务已被接受！");
				putJumpSelfPage("taskManager/task!initReleasedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			// 如果发布人不是当前的登陆人就报错
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							userEntity.getId())) {
				WinUtils.throwIllegalityException(userEntity.getUsername()
						+ "试图越过【取消重填】操作！  任务ID是:" + taskId);
			}
			/**
			 * 拷贝数据
			 */
			BeanUtils.copyProperties(creditTaskVO, creditTask);
			// 掌柜
			creditTaskVO.setSellerID(creditTask.getSeller().getId());
			// 地址
			creditTaskVO.setAddress(creditTask.getAddress());
			/**
			 * 删除任务
			 */
			creditTaskDAO.delete(creditTask);
			/**
			 * 修改用户金钱和发布点
			 */
			userEntity.setMoney(ArithUtils.add(userEntity.getMoney(),
					creditTask.getMoney())
					+ creditTask.getAddtionMoney());
			userEntity.setReleaseDot(ArithUtils.add(userEntity.getReleaseDot(),
					creditTask.getReleaseDot())
					+ creditTask.getAddtionReleaseDot());
			putByRequest("cancelTask", "cancelTask");
			putByRequest("cancelTask", "cancelTask");
			putAlertMsg("取消成功，金额已返回！");

			logMoneyCapital(userDAO, creditTask.getMoney()
					+ creditTask.getAddtionMoney(), "取消重填  任务 返回金额", userEntity);
			logDotCapital(userDAO, creditTask.getReleaseDot()
					+ creditTask.getAddtionReleaseDot(), "取消重填  任务 返回发布点",
					userEntity);
			updateUserLoginInfo(userEntity);
			return "cancelTask";
		}
	}

	/** ***************************** */

	/**
	 * 初始化已接任务 ******
	 * 
	 * @param userVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String initReceivedTast(CreditTaskVO creditTaskVO) throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		updateUserLoginInfo(userEntity);
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if ("1".equals(platformType)) {
			putIndexShowType("2");
		} else if ("2".equals(platformType)) {
			putIndexShowType("3");
		} else if ("3".equals(platformType)) {
			putIndexShowType("4");
		}
		putTaskShowType("3");
		String queryType = getByParam("queryType");
		String page = getByParam("page");
		String autoRefresh = getByParam("autoRefresh");
		if (queryType != null) {
			putByRequest("queryType", queryType);
		} else {
			putByRequest("queryType", "1");
		}
		if (autoRefresh != null) {
			putByRequest("autoRefresh", autoRefresh);
		}
		if (page != null) {
			creditTaskVO.setNowPage(Integer.parseInt(page));
		}
		if (StringUtils.isBlank(platformType)) {
			WinUtils.throwIllegalityException(getLoginUser().getUsername()
					+ "试图越过取消重填操作！ ");
		}
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			/**
			 * 更新加时的剩余时间
			 */
			List<CreditTaskEntity> tasks = creditTaskDAO
					.list(
							"select  _task from CreditTaskEntity as _task   inner join _task.receivePerson as _user where _user.id=:userId and _task.type=:platformType and (_task.status='2' or _task.status='-2' ) ",
							new String[]{"userId", "platformType"},
							new Object[]{getLoginUser().getId(), platformType});
			for (CreditTaskEntity creditTaskEntity : tasks) {
				Date currOperDate = creditTaskEntity.getReceiveDate();
				Integer minuties = ((Long) ((System.currentTimeMillis() - currOperDate
						.getTime()) / 1000 / 60)).intValue();
				creditTaskEntity.setRemainTime(creditTaskEntity.getRemainTime()
						- minuties);
			}
			creditTaskDAO.flushSession();
			// 分页查询
			Long count = (Long) creditTaskDAO
					.uniqueResultObject(
							"select count(*) from CreditTaskEntity as _task inner join _task.receivePerson as _user   where     _user.id=:userId and   _task.type=:platformType "
									+ orderAndWhereReceivedTaskStr(queryType,
											true), new String[]{"userId",
									"platformType"}, new Object[]{
									getLoginUser().getId(), platformType});
			List<Object[]> resultTemp = creditTaskDAO
					.pageQuery(
							"select _task.testID , _task.releaseDate ,_fbuser.username,_fbuser.qq,_task.money,_task.updatePrice ,_task.releaseDot "// 6
									+ ", _task.itemUrl , _seller.name,_seller.shopURL,_buyer.name,_jsuser.upgradeScore,_task.status" // 12
									+ ", _task.remainTime,_task.taskType ,_task.intervalHour,_task.comment,_task.address ,_task.grade,_task.id ," // 19
									+ "_fbuser.ww,_task.waybill,_task.addtionMoney,_task.addtionReleaseDot,_fbuser.upgradeScore," // 24
									+ "_task.assignUser,_fbuser.telephone,_jsuser.username,_task.receiveDate" // index=28
									+ " from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer  where     _jsuser.id=:userId and   _task.type=:platformType "
									+ orderAndWhereReceivedTaskStr(queryType,
											false), new String[]{"userId",
									"platformType"}, new Object[]{
									getLoginUser().getId(), platformType},
							creditTaskVO.getStart(), creditTaskVO.getLimit());
			// 设置
			List<CreditTaskVO> result = new ArrayList<CreditTaskVO>(resultTemp
					.size());
			CreditTaskVO creditTaskVO2 = null;
			for (Object[] objs : resultTemp) {
				creditTaskVO2 = new CreditTaskVO();
				creditTaskVO2.setTestID((String) objs[0]);
				creditTaskVO2.setReleaseDate((Date) objs[1]);
				creditTaskVO2.setFbUsername((String) objs[2]);
				creditTaskVO2.setFbQQ((String) objs[3]);
				creditTaskVO2.setMoney((Double) objs[4]);
				creditTaskVO2.setUpdatePrice((Boolean) objs[5]);
				creditTaskVO2.setReleaseDot((Double) objs[6]);
				creditTaskVO2.setItemUrl((String) objs[7]);
				creditTaskVO2.setSellname((String) objs[8]);
				creditTaskVO2.setFbShopURL((String) objs[9]);
				creditTaskVO2.setBuyername((String) objs[10]);
				creditTaskVO2.setJsUpgradeScore((Integer) objs[11]);
				creditTaskVO2.setStatus((String) objs[12]);
				creditTaskVO2.setRemainTime((Integer) objs[13]);
				creditTaskVO2.setTaskType((String) objs[14]);
				creditTaskVO2.setIntervalHour((Integer) objs[15]);
				creditTaskVO2.setComment((String) objs[16]);
				creditTaskVO2.setAddress((String) objs[17]);
				creditTaskVO2.setGrade((String) objs[18]);
				creditTaskVO2.setId((Long) objs[19]);
				creditTaskVO2.setFbQQ((String) objs[20]);
				creditTaskVO2.setWaybill((String) objs[21]);
				creditTaskVO2.setAddtionMoney((Double) objs[22]);
				creditTaskVO2.setAddtionReleaseDot((Double) objs[23]);
				creditTaskVO2.setFbUpgradeScore((Integer) objs[24]);
				creditTaskVO2.setAssignUser((String) objs[25]);
				creditTaskVO2.setFbTelphone((String) objs[26]);
				creditTaskVO2.setJsUsername((String) objs[27]);
				creditTaskVO2.setReceiveDate((Date) objs[28]);
				result.add(creditTaskVO2);
			}
			creditTaskVO.setDataCount(count.intValue());
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			putByRequest("result", result);
			return "initReceivedTast";
		}
	}

	/**
	 * 初始化已发任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String initReleasedTast(CreditTaskVO creditTaskVO) throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		updateUserLoginInfo(userEntity);

		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if ("1".equals(platformType)) {
			putIndexShowType("2");
		} else if ("2".equals(platformType)) {
			putIndexShowType("3");
		} else if ("3".equals(platformType)) {
			putIndexShowType("4");
		}
		putTaskShowType("4");
		String queryType = getByParam("queryType");
		String page = getByParam("page");
		String autoRefresh = getByParam("autoRefresh");
		if (queryType != null) {
			putByRequest("queryType", queryType);
		} else {
			putByRequest("queryType", "1");
		}
		if (autoRefresh != null) {
			putByRequest("autoRefresh", autoRefresh);
		}
		if (page != null) {
			creditTaskVO.setNowPage(Integer.parseInt(page));
		}
		if (StringUtils.isBlank(platformType)) {
			WinUtils.throwIllegalityException(getLoginUser().getUsername()
					+ "试图越过取消重填操作！ ");
		}
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			/**
			 * 更新加时的剩余时间
			 */
			List<CreditTaskEntity> tasks = creditTaskDAO
					.list(
							"select  _task  from CreditTaskEntity as _task   inner join _task.releasePerson as _user where _user.id=:userId and _task.type=:platformType and (_task.status='2' or _task.status='-2' ) ",
							new String[]{"userId", "platformType"},
							new Object[]{getLoginUser().getId(), platformType});
			for (CreditTaskEntity creditTaskEntity : tasks) {
				Date currOperDate = creditTaskEntity.getReceiveDate();
				Integer minuties = ((Long) ((System.currentTimeMillis() - currOperDate
						.getTime()) / 1000 / 60)).intValue();
				creditTaskEntity.setRemainTime(creditTaskEntity.getRemainTime()
						- minuties);
			}
			creditTaskDAO.flushSession();
			// 分页查询
			Long count = (Long) creditTaskDAO
					.uniqueResultObject(
							"select count(*) from CreditTaskEntity as _task inner join _task.releasePerson as _user   where     _user.id=:userId and   _task.type=:platformType "
									+ orderAndWhereReleasedTaskStr(queryType,
											true), new String[]{"userId",
									"platformType"}, new Object[]{
									getLoginUser().getId(), platformType});
			List<Object[]> resultTemp = creditTaskDAO
					.pageQuery(
							"select _task.testID , _task.releaseDate ,_task.money,_task.updatePrice ,_task.releaseDot, _task.itemUrl , _seller.name,_task.status "// 7
									+ ", _jsuser.username,_buyer.name,_jsuser.qq" // 10
									+ ", _task.remainTime,_task.taskType ,_task.intervalHour,_task.comment,_task.address ,_task.grade," // 16
									+ "_task.id,_seller.shopURL ,_jsuser.ww,_task.waybill,_task.timeingTime,_task.addtionMoney," // 22
									+ "_task.addtionReleaseDot ,_task.assignUser,_jsuser.telephone,_fbuser.username,_task.receiveDate" // index=27
									+ " from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer  where     _fbuser.id=:userId and   _task.type=:platformType "
									+ orderAndWhereReleasedTaskStr(queryType,
											false), new String[]{"userId",
									"platformType"}, new Object[]{
									getLoginUser().getId(), platformType},
							creditTaskVO.getStart(), creditTaskVO.getLimit());
			Long currentDate = System.currentTimeMillis();
			// 设置时间
			List<CreditTaskVO> result = new ArrayList<CreditTaskVO>(resultTemp
					.size());
			CreditTaskVO creditTaskVO2 = null;
			for (Object[] objs : resultTemp) {
				creditTaskVO2 = new CreditTaskVO();
				creditTaskVO2.setTestID((String) objs[0]);
				creditTaskVO2.setReleaseDate((Date) objs[1]);
				creditTaskVO2.setMoney((Double) objs[2]);
				creditTaskVO2.setUpdatePrice((Boolean) objs[3]);
				creditTaskVO2.setReleaseDot((Double) objs[4]);
				creditTaskVO2.setItemUrl((String) objs[5]);
				creditTaskVO2.setSellname((String) objs[6]);
				creditTaskVO2.setStatus((String) objs[7]);
				creditTaskVO2.setJsUsername((String) objs[8]);
				creditTaskVO2.setBuyername((String) objs[9]);
				creditTaskVO2.setJsQQ((String) objs[10]);
				creditTaskVO2.setRemainTime((Integer) objs[11]);
				creditTaskVO2.setTaskType((String) objs[12]);
				creditTaskVO2.setIntervalHour((Integer) objs[13]);
				creditTaskVO2.setComment((String) objs[14]);
				creditTaskVO2.setAddress((String) objs[15]);
				creditTaskVO2.setGrade((String) objs[16]);
				creditTaskVO2.setId((Long) objs[17]);
				creditTaskVO2.setFbShopURL((String) objs[18]);
				creditTaskVO2.setJsWW((String) objs[19]);
				creditTaskVO2.setWaybill((String) objs[20]);
				creditTaskVO2.setTimeingTime((Date) objs[21]);
				creditTaskVO2.setAddtionMoney((Double) objs[22]);
				creditTaskVO2.setAddtionReleaseDot((Double) objs[23]);
				creditTaskVO2.setAssignUser((String) objs[24]);
				creditTaskVO2.setJsTelphone((String) objs[25]);
				creditTaskVO2.setFbUsername((String) objs[26]);
				creditTaskVO2.setReceiveDate((Date) objs[27]);
				result.add(creditTaskVO2);
			}
			creditTaskVO.setDataCount(count.intValue());
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			putByRequest("result", result);
			return "initReleasedTast";
		}
	}

	/**
	 * 发布任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String insertReleaseTask(CreditTaskVO creditTaskVO) throws Exception {
		// 基本数据
		UserEntity userEntity = getLoginUserEntity(userDAO);
		UserLoginInfo userLoginInfo = getLoginUser();
		CreditTaskEntity creditTask = new CreditTaskEntity();
		TaskMananger taskMananger = TaskMananger.getInstance();

		String platFormType = getPlatformType();

		putJumpOutterPage("taskManager/task!initReleaseTask.php?platformType="
				+ platFormType);

		BeanUtils.copyProperties(creditTask, creditTaskVO);

		// 把天转换成时间
		Integer hour = 0;
		if (creditTaskVO.getGrade().indexOf("一天") != -1) {
			hour = 24;
		} else if (creditTaskVO.getGrade().indexOf("二天") != -1) {
			hour = 24 * 2;
		} else if (creditTaskVO.getGrade().indexOf("三 天") != -1) {
			hour = 24 * 3;
		} else if (creditTaskVO.getIntervalHour() != null) {
			hour = creditTaskVO.getIntervalHour();
		}
		creditTaskVO.setIntervalHour(hour);
		// 算发布点
		double creditTaskDot = 0D;
		if (StringUtils.isBlank(creditTaskVO.getAssignUser())) {
			creditTaskDot = StrategyUtils.generateCreditRDot(creditTaskVO
					.getMoney(), creditTaskVO.getIntervalHour());
		}
		// 验证
		if (!userEntity.getStatus().equals("1")) {
			switch (Integer.parseInt(userEntity.getStatus())) {
				case 0 :
					putAlertMsg("您当前的【状态】为【未激活状态】，请到个人中心激活！");
					break;
				case 2 :
					putAlertMsg("您当前的【状态】为【冻结状态】，不能发布任务！");
					break;
				case 3 :
					putAlertMsg("您当前的【状态】为【找密码状态】，可能有人试图盗取您的秘密，请联系管理员，不能发布任务！");
					break;
				default :
					putAlertMsg("您当前的【状态】不是【正常状态】，不能发布任务！");
					break;
			}
			return JUMP;
		}
		if (StringUtils.isBlank(platFormType)) {
			WinUtils.throwIllegalityException(userEntity.getUsername()
					+ "视图越过发布任务的任务类型验证！");
			return JUMP;
		}
		if (creditTask.getAddtionMoney() == null) {
			creditTask.setAddtionMoney(0D);
		}
		if (creditTask.getAddtionReleaseDot() == null) {
			creditTask.setAddtionReleaseDot(0D);
		}
		if (creditTask.getMoney() + creditTask.getAddtionMoney() > userEntity
				.getMoney()) {
			creditTaskVO.setMoney(null);
			putAlertMsg("您当前的余额为不够发布这个任务！");
			return JUMP;
		}

		if (creditTaskVO.getMoney() < 1) {
			putAlertMsg("不能发布小于1元的任务！");
			return JUMP;
		}
		if (creditTaskDot + creditTask.getAddtionReleaseDot() > userEntity
				.getReleaseDot()) {
			putAlertMsg("您当前的发布点不够" + creditTaskDot
					+ creditTask.getAddtionReleaseDot() + "，不能发布此任务！");
			return JUMP;
		}
		// 验证掌柜地址
		SellerEntity seller = sellerDAO.get(creditTaskVO.getSellerID());
		if (seller == null) {
			putAlertMsg("掌柜名和商品地址不对应！");
			return JUMP;
		} else {
			String[] itemUrls = getByParams("itemUrls");
			if (itemUrls.length > 0) {
				StringBuffer itemUrl = new StringBuffer();
				for (String url : itemUrls) {
					if (!HttpB2CUtils.obtainSellerByShop(url, platFormType)
							.equals(seller.getName())) {
						putAlertMsg("您输入的商品地址和选择的掌柜不一致，请核实后再输入！");
						return JUMP;
					} else {
						itemUrl.append(url + ",");
					}
				}
				itemUrl.delete(itemUrl.length() - 1, itemUrl.length());
				creditTask.setItemUrl(itemUrl.toString());
			} else {
				putAlertMsg("请输入商品地址！");
				return JUMP;
			}
		}
		/**
		 * 是否指定人
		 */
		if (!StringUtils.isBlank(creditTaskVO.getAssignUser())) {
			creditTaskVO.setAssignUser(creditTaskVO.getAssignUser().trim());
			UserEntity assignUser = userDAO.findUserByName(creditTaskVO
					.getAssignUser());
			if (assignUser == null) {
				putAlertMsg("你指定的【" + creditTaskVO.getAssignUser() + "】用户不存在！");
				return JUMP;
			}
			String adddLinkName = getByParam("addLinkName");
			if ("true".equals(adddLinkName)) {
				TaskLinkManEntity linkMan = taskLinkManDAO.findLinkMan(
						creditTaskVO.getAssignUser(), userEntity.getId());
				if (linkMan == null) {
					linkMan = new TaskLinkManEntity();
					linkMan.setUsername(creditTaskVO.getAssignUser());
					linkMan.setUser(userEntity);
					linkMan.setLastUseTime(new Date());
					taskLinkManDAO.save(linkMan);
				} else {
					linkMan.setLastUseTime(new Date());
				}
			}
			creditTask.setReleaseDot(0D);
		}
		/**
		 * 接手人自己想
		 */
		String commentByJS =getByParam("commentByJS");
		if("true".equalsIgnoreCase(commentByJS)){
			creditTask.setComment("-1");
		}
		/**
		 * 保存任务
		 */
		// 是否是定时任务
		if (creditTask.getTimeingTime() == null) {
			creditTask.setStatus(TaskMananger.STEP_ONE_STATUS);
		} else {
			// 验证定时时间是否 小于 当前的系统时间
			if (creditTask.getTimeingTime().getTime() < System
					.currentTimeMillis()) {
				putAlertMsg("定时任务时间必须大于当前的时间！");
				return JUMP;
			}
			creditTask.setStatus(TaskMananger.TIMING_STATUS);
		}
		creditTask.setSeller(seller);
		creditTask.setReleasePerson(userEntity);
		// 生成testID
		creditTask.setTestID(taskMananger.generateTaskID());
		// 生成地址
		creditTask
				.setAddress(createAddress(creditTaskVO, taskMananger, seller));
		creditTask.setReleaseDot(creditTaskDot);
		creditTask.setReleaseDate(new Date());
		creditTask.setReleasePerson(userEntity);
		creditTask.setType(platFormType);
		creditTask.setIntervalHour(creditTaskVO.getIntervalHour());
		creditTask.setWaybill(StrategyUtils.makeWaybill());
		creditTaskDAO.save(creditTask);
		/**
		 * 保存任务仓库
		 */
		// 任务仓库
		if (creditTaskVO.getRepository()) {
			CreditTaskRepositoryEntity creditTaskRepository = new CreditTaskRepositoryEntity();
			BeanUtils.copyProperties(creditTaskRepository, creditTaskVO);
			creditTaskRepository.setUser(userEntity);
			creditTaskRepository.setType(platFormType);
			creditTaskRepository.setReleaseDot(creditTaskDot);
			creditTaskRepository
					.setGoodTimeType(creditTaskVO.getGoodTimeType());
			if (StringUtils.isBlank(creditTaskVO.getRespositoryName())) {
				creditTaskRepository.setName(creditTask.getTestID());
			} else {
				creditTaskRepository.setName(creditTaskVO.getRespositoryName());

			}
			creditTaskRepositoryDAO.save(creditTaskRepository);
		}
		/**
		 * 改变用户金钱和发布点
		 */
		userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(), creditTask
				.getMoney()
				+ creditTask.getAddtionMoney()));
		userEntity.setReleaseDot(ArithUtils.sub(userEntity.getReleaseDot(),
				creditTaskDot + creditTask.getAddtionReleaseDot()));
		// 完成对金钱进行修改,登陆名的也需要
		updateUserLoginInfo(userEntity);

		logMoneyCapital(userDAO, 0 - (creditTask.getMoney() + creditTask
				.getAddtionMoney()), "发布任务", userEntity);
		logDotCapital(userDAO, 0 - (creditTaskDot + creditTask
				.getAddtionReleaseDot()), "发布任务", userEntity);

		putAlertMsg("发布任务成功!");
		return JUMP;
	}
	/**
	 * 初始化发布任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String initReleaseTask(CreditTaskVO creditTaskVO) throws Exception {

		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if ("1".equals(platformType)) {
			putIndexShowType("2");
		} else if ("2".equals(platformType)) {
			putIndexShowType("3");
		} else if ("3".equals(platformType)) {
			putIndexShowType("4");
		}
		putTaskShowType("2");
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			UserEntity userEntity = getLoginUserEntity(userDAO);
			if (!userEntity.getStatus().equals("1")) {
				switch (Integer.parseInt(userEntity.getStatus())) {
					case 0 :
						putAlertMsg("您当前的【状态】为【未激活状态】，请到个人中心激活！");
						break;
					case 2 :
						putAlertMsg("您当前的【状态】为【冻结状态】，不能发布任务！");
						break;
					case 3 :
						putAlertMsg("您当前的【状态】为【找密码状态】，可能有人试图盗取您的秘密，请联系管理员，不能发布任务！");
						break;
					default :
						putAlertMsg("您当前的【状态】不是【正常状态】，不能发布任务！");
						break;
				}
				putJumpSelfPage("userInfoManager/info!initActiave.php");
				return JUMP;
			}
			List<SellerEntity> sellers = sellerDAO
					.list(
							"select _s   from SellerEntity  as _s where _s.type=:type and _s.user.id=:userID",
							new String[]{"type", "userID"}, new Object[]{
									platformType, userEntity.getId()});
			List<SellerVO> resultSellers = new ArrayList<SellerVO>(sellers
					.size());
			if (sellers.size() > 0) {
				for (SellerEntity sellerEntity : sellers) {
					SellerVO sellerVO = new SellerVO();
					BeanUtils.copyProperties(sellerVO, sellerEntity);
					resultSellers.add(sellerVO);
				}
			} else {
				putAlertMsg("您还没有为【"
						+ WinUtils.changeType2Platform(platformType)
						+ "】平台绑定卖号，请先添加！");
				putJumpSelfPage("userInfoManager/info!initSellerAndBuyer.php");
				return JUMP;
			}
			List<CreditTaskRepositoryEntity> creditTaskResitorys = creditTaskRepositoryDAO
					.list(
							"from CreditTaskRepositoryEntity _cr where _cr.user.id=:userId and _cr.type=:type",
							new String[]{"userId", "type"}, new Object[]{
									userEntity.getId(), platformType});
			List<CreditTaskRepositoryVO> resultTaskReps = new ArrayList<CreditTaskRepositoryVO>(
					creditTaskResitorys.size());
			CreditTaskRepositoryVO creditTaskRepositoryVO = null;
			for (CreditTaskRepositoryEntity creditTaskRepositoryEntity : creditTaskResitorys) {
				creditTaskRepositoryVO = new CreditTaskRepositoryVO();
				BeanUtils.copyProperties(creditTaskRepositoryVO,
						creditTaskRepositoryEntity);
				resultTaskReps.add(creditTaskRepositoryVO);
			}

			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			putByRequest("sellers", resultSellers);
			putByRequest("resultTaskReps", resultTaskReps);
			putTokenBySession();
			return "initReleaseTask";
		}
	}
	/**
	 * 查找联系人
	 * 
	 * @param creditTaskVO
	 * @return
	 * @throws Exception
	 */
	public List<String> getLinkMans(CreditTaskVO creditTaskVO) throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		/**
		 * 是否指定人
		 */
		List<String> tlms = taskLinkManDAO.queryLinkMan(creditTaskVO
				.getAssignUser(), userEntity.getId());
		return tlms;
	}
	/**
	 * 初始化任务
	 * 
	 * @param userVO
	 * @return
	 */

	public String initTask(CreditTaskVO creditTaskVO) throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		updateUserLoginInfo(userEntity);
		String platformType = getPlatformType();
		if ("1".equals(platformType)) {
			putIndexShowType("2");
		} else if ("2".equals(platformType)) {
			putIndexShowType("3");
		} else if ("3".equals(platformType)) {
			putIndexShowType("4");
		}
		putTaskShowType("1");
		String queryType = getByParam("queryType");
		String page = getByParam("page");
		String autoRefresh = getByParam("autoRefresh");
		if (queryType != null) {
			putByRequest("queryType", queryType);
		} else {
			putByRequest("queryType", "1");
		}
		if (autoRefresh != null) {
			putByRequest("autoRefresh", autoRefresh);
		}
		if (page != null) {
			creditTaskVO.setNowPage(Integer.parseInt(page));
		}
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		}
		if (platformType == null) {
			WinUtils.throwIllegalityException(getLoginUser().getUsername()
					+ "试图越过初始化任务!");
		}
		Long count = (Long) creditTaskDAO
				.uniqueResultObject(
						"select count(*)"
								+ " from CreditTaskEntity as _task inner join _task.releasePerson as _user where  _task.status not  in ('0','-1')  and   _task.type=:platformType"
								+ orderAndWhereInitTaskStr(queryType, true),
						"platformType", platformType);
		List<Object[]> resultTemp = creditTaskDAO
				.pageQuery(
						"select _task.testID , _task.releaseDate ,_user.username,_user.upgradeScore,_task.money,_task.updatePrice, " // 5
								+ "_task.taskType,_task.releaseDot,_task.status ,_task.intervalHour,_task.id,_task.grade " // 11
								+ ",_vip.type,_task.addtionMoney,_task.addtionReleaseDot,_task.assignUser" // index=15
								+ " from CreditTaskEntity as _task inner join _task.releasePerson as _user  left join _user.vip as _vip  where _task.status not  in ('0','-1')   and   _task.type=:platformType "
								+ orderAndWhereInitTaskStr(queryType, false),
						"platformType", platformType, creditTaskVO.getStart(),
						creditTaskVO.getLimit());
		List<BuyerEntity> buyers = userDAO
				.list(
						" from BuyerEntity  as _b where _b.user.id=:userId  and  _b.type=:type and _b.enable=:enable",
						new String[]{"userId", "type", "enable"}, new Object[]{
								getLoginUser().getId(), platformType, true});
		List<BuyerVO> resultBuyers = new ArrayList<BuyerVO>(buyers.size());
		// 设置
		List<CreditTaskVO> result = new ArrayList<CreditTaskVO>(resultTemp
				.size());
		CreditTaskVO creditTaskVO2 = null;
		for (Object[] objs : resultTemp) {
			creditTaskVO2 = new CreditTaskVO();
			creditTaskVO2.setTestID((String) objs[0]);
			creditTaskVO2.setReleaseDate((Date) objs[1]);
			creditTaskVO2.setFbUsername((String) objs[2]);
			creditTaskVO2.setFbUpgradeScore((Integer) objs[3]);
			creditTaskVO2.setMoney((Double) objs[4]);
			creditTaskVO2.setUpdatePrice((Boolean) objs[5]);
			creditTaskVO2.setTaskType((String) objs[6]);
			creditTaskVO2.setReleaseDot((Double) objs[7]);
			creditTaskVO2.setStatus((String) objs[8]);
			creditTaskVO2.setIntervalHour((Integer) objs[9]);
			creditTaskVO2.setId((Long) objs[10]);
			creditTaskVO2.setGrade((String) objs[11]);
			creditTaskVO2.setFbVipType((String) objs[12]);
			creditTaskVO2.setAddtionMoney((Double) objs[13]);
			creditTaskVO2.setAddtionReleaseDot((Double) objs[14]);
			creditTaskVO2.setAssignUser((String) objs[15]);
			result.add(creditTaskVO2);
		}
		for (BuyerEntity buyerEntity : buyers) {
			BuyerVO buyerVO = new BuyerVO();
			BeanUtils.copyProperties(buyerVO, buyerEntity);
			resultBuyers.add(buyerVO);
		}
		// ///
		creditTaskVO.setDataCount(count.intValue());
		putByRequest("result", result);
		putByRequest("resultBuyers", resultBuyers);
		putPlatformByRequest(WinUtils.changeType2Platform(platformType));
		putPlatformTypeByRequest(platformType);
		if (buyers.size() == 0) {
			putByRequest("noBuyser", "noBuyser");
			return "initTask";
		}
		return "initTask";
	}

	/**
	 * 发送短信
	 * 
	 * @param creditTaskVO
	 * @return
	 */
	public String updateSendMsg(CreditTaskVO creditTaskVO) throws Exception {
		putJumpOutterPage("", true, true);
		String telphone = getByParam("telphone");
		String content = getByParam("content");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		Double money = 0.1 * ((content.length() + 69) / 70);
		if (userEntity.getMoney() < money) {
			putAlertMsg("您当前的余额不足！");
		} else {
			MsgUtils.sendMsg(telphone, content);
			userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(), money));
			logMoneyCapital(userDAO, 0 - money, "发送长度为" + content.length()
					+ "的短信花费" + money + "元", userEntity);
			updateUserLoginInfo(userEntity);
			putAlertMsg("发送成功！");
		}
		return JUMP;
	}

	/**
	 * 生成地址
	 */
	private String createAddress(CreditTaskVO creditTaskVO,
			TaskMananger taskMananger, SellerEntity sellerEntity)
			throws Exception {
		// 生成描述(包含地址)
		StringBuffer address = new StringBuffer();
		if (!StringUtils.isBlank(creditTaskVO.getAddress())) {
			String addresses = sellerEntity.getAddress();
			if (addresses != null) {
				address.append(addresses + " " + StrategyUtils.makeAddress());
			}
		}
		return StringUtils.isBlank(address.toString()) ? "无" : address
				.toString();
	}

	private String getIpAddr() {
		HttpServletRequest request = getRequset();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.indexOf(",") != -1) {
			ip = ip.split(",", 2)[0];
		}
		return ip;
	}

	/**
	 * 发任务查询
	 * 
	 * @param type
	 * @return
	 */
	private String orderAndWhereReleasedTaskStr(String type, Boolean countFlag) {
		if (countFlag) {
			// 默认时间排列
			if ("1".equals(type)) {
				return "";
			}
			// 等待支付
			if ("2".equals(type)) {
				return " and _task.status='2' ";
			}
			// 等待发货
			if ("3".equals(type)) {
				return " and _task.status='3'";
			}
			// 等待好评
			if ("4".equals(type)) {
				return " and _task.status='4' ";
			}
			// 等待完成
			if ("5".equals(type)) {
				return " and _task.status='5' ";
			}
			// 已完成
			if ("6".equals(type)) {
				return " and _task.status='6' ";
			} // 等待接手
			if ("7".equals(type)) {
				return " and _task.status='1' ";
			}
			// 等待审核
			if ("8".equals(type)) {
				return " and _task.status='-2' ";
			}
			if ("9".equals(type)) {
				return " and _task.status='0' ";
			}
			return "";
		} else {
			// 默认时间排列
			if ("1".equals(type)) {
				return " order by   _task.releaseDate desc";
			}
			// 等待支付
			if ("2".equals(type)) {
				return " and _task.status='2' order by   _task.releaseDate desc";
			}
			// 等待发货
			if ("3".equals(type)) {
				return " and _task.status='3' order by   _task.releaseDate desc";
			}
			// 等待好评
			if ("4".equals(type)) {
				return " and _task.status='4' order by   _task.releaseDate desc";
			}
			// 等待完成
			if ("5".equals(type)) {
				return " and _task.status='5' order by   _task.releaseDate desc";
			}
			// 已完成
			if ("6".equals(type)) {
				return " and _task.status='6' order by   _task.releaseDate desc";
			}
			// 等待接手
			if ("7".equals(type)) {
				return " and _task.status='1'  order by   _task.releaseDate desc";
			}
			// 等待审核
			if ("8".equals(type)) {
				return " and _task.status='-2'  order by   _task.releaseDate desc ";
			}
			// 定时
			if ("9".equals(type)) {
				return " and _task.status='0'  order by   _task.releaseDate desc ";
			}
			return " order by   _task.releaseDate desc";
		}

	}

	/**
	 * 已接任务查询
	 * 
	 * @param type
	 * @return
	 */
	private String orderAndWhereReceivedTaskStr(String type, Boolean countFlag) {
		if (countFlag) {
			// 默认时间排列
			if ("1".equals(type)) {
				return "";
			}
			// 已接任务
			if ("2".equals(type)) {
				return " and _task.status='2' ";
			}
			// 已支付
			if ("3".equals(type)) {
				return " and _task.status='3' ";
			}
			// 已发货
			if ("4".equals(type)) {
				return " and _task.status='4' ";
			}
			// 已评价
			if ("5".equals(type)) {
				return " and _task.status='5' ";
			}
			// 已完成
			if ("6".equals(type)) {
				return " and _task.status='6' ";
			}
			return "";
		} else {
			// 默认时间排列
			if ("1".equals(type)) {
				return " order by   _task.releaseDate desc";
			}
			// 已接任务
			if ("2".equals(type)) {
				return " and _task.status='2' order by   _task.releaseDate desc";
			}
			// 已支付
			if ("3".equals(type)) {
				return " and _task.status='3' order by   _task.releaseDate desc";
			}
			// 已发货
			if ("4".equals(type)) {
				return " and _task.status='4' order by   _task.releaseDate desc";
			}
			// 已评价
			if ("5".equals(type)) {
				return " and _task.status='5' order by   _task.releaseDate desc";
			}
			// 已完成
			if ("6".equals(type)) {
				return " and _task.status='6' order by   _task.releaseDate desc";
			}
			return " order by   _task.releaseDate desc";
		}
	}

	/**
	 * 
	 * 
	 * @param type
	 * @return
	 */
	private String orderAndWhereInitTaskStr(String type, Boolean countFlag) {
		if (countFlag) {
			// 默认时间排列
			if ("1".equals(type)) {
				return "";
			}
			// 价低排列
			if ("2".equals(type)) {
				return "";
			}
			// 价高排列
			if ("3".equals(type)) {
				return "";
			}
			// 1-40
			if ("4".equals(type)) {
				return " and  (_task.money>0 and _task.money<=40) ";
			}
			// 40-100
			if ("5".equals(type)) {
				return " and  (_task.money>40 and _task.money<=100) ";
			}
			// 100-200
			if ("6".equals(type)) {
				return " and  (_task.money>100 and _task.money<=200)";
			}
			// 200-500
			if ("7".equals(type)) {
				return " and  (_task.money>200 and _task.money<=500) ";
			}
			// 500 以上
			if ("8".equals(type)) {
				return " and  _task.money>500 ";
			}
			return "";
		} else {
			// 默认时间排列
			if ("1".equals(type)) {
				return " order by   _vip.type desc , _task.releaseDate desc ";
			}
			// 价低排列
			if ("2".equals(type)) {
				return " order by   _vip.type desc ,   _task.money asc";
			}
			// 价高排列
			if ("3".equals(type)) {
				return " order by   _vip.type desc ,    _task.money desc";
			}
			// 1-40
			if ("4".equals(type)) {
				return " and  (_task.money>0 and _task.money<=40) order by   _vip.type desc ,   _task.money asc";
			}
			// 40-100
			if ("5".equals(type)) {
				return " and  (_task.money>40 and _task.money<=100) order by   _vip.type desc ,   _task.money asc";
			}
			// 100-200
			if ("6".equals(type)) {
				return " and  (_task.money>100 and _task.money<=200) order by   _vip.type desc ,   _task.money asc";
			}
			// 200-500
			if ("7".equals(type)) {
				return " and  (_task.money>200 and _task.money<=500) order by   _vip.type desc ,   _task.money asc";
			}
			// 500 以上
			if ("8".equals(type)) {
				return " and  _task.money>500 order by   _vip.type desc ,   _task.money asc";
			}
			return " order by   _vip.type desc ,   _task.releaseDate desc";
		}

	}
}
