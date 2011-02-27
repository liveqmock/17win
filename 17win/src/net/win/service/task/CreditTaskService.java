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
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 * 
 * @author xgj
 * 
 */
@SuppressWarnings( { "unused", "unchecked" })
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
		putIndexShowType(platformType);
		putTaskShowType("3");
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = creditTaskVO.getId();
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReceivePerson().getId().equals(
							getLoginUser().getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReceivedTast";
			}
			// 如果发布人不是当前的登陆人就报错
			if (!creditTask.getStatus().equals(TaskMananger.STEP_FOUR_STATUS)) {
				putAlertMsg("好评失败，任务状态已经改变！");
				List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
						platformType);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				putByRequest("result", result);
				return "initReceivedTast";
			}
			if (creditTask.getRemainTime() > 0) {
				putAlertMsg("好评失败，时间还没到！");
				List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
						platformType);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				putByRequest("result", result);
				return "initReceivedTast";
			}
			/**
			 * 验证
			 */
			creditTask.setStatus(TaskMananger.STEP_FIVE_STATUS);
			putAlertMsg("好评成功，通知卖家好评吧！");
			List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
					platformType);
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			putByRequest("result", result);
			return "initReceivedTast";
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
		putIndexShowType(platformType);
		putTaskShowType("3");
		UserLoginInfo loginInfo = getLoginUser();
		Long taskId = creditTaskVO.getId();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReceivePerson().getId().equals(
							getLoginUser().getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReceivedTast";
			}
			if (!creditTask.getStatus().equals(TaskMananger.STEP_THREE_STATUS)) {
				putAlertMsg("撤销失败，任务状态已经改变！");
				List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
						platformType);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				putByRequest("result", result);
				return "initReceivedTast";
			}
			/**
			 * 任务
			 */
			Date currOperDate = creditTask.getReceiveDate();
			Long minuties = ((System.currentTimeMillis() - currOperDate
					.getTime()) / 1000 / 60);
			/**
			 * 真正的逻辑 修改时间
			 */
			creditTask.setRemainTime(minuties + 20);
			creditTask.setStatus(TaskMananger.STEP_TWO_STATUS);
			putAlertMsg("撤销成功！");
		}
		List<CreditTaskVO> result = queryReceiveData(creditTaskVO, platformType);
		putPlatformByRequest(WinUtils.changeType2Platform(platformType));
		putPlatformTypeByRequest(platformType);
		putByRequest("result", result);
		return "initReceivedTast";
	}

	/**
	 * 付款操作
	 * 
	 * @param creditTaskVO
	 * @return
	 */
	public String updatePayTask(CreditTaskVO creditTaskVO) throws Exception {
		String platformType = getPlatformType();
		putIndexShowType(platformType);
		putTaskShowType("3");
		UserLoginInfo loginInfo = getLoginUser();
		Long taskId = creditTaskVO.getId();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReceivePerson().getId().equals(
							getLoginUser().getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReceivedTast";
			}
			if (!creditTask.getStatus().equals(TaskMananger.STEP_TWO_STATUS)) {
				putAlertMsg("支付失败，任务状态已经改变！");
				List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
						platformType);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				putByRequest("result", result);
				return "initReceivedTast";
			}
			creditTask.setRemainTime(0L);
			/**
			 * 任务
			 */
			creditTask.setStatus(TaskMananger.STEP_THREE_STATUS);
		}
		putAlertMsg("支付成功！");
		List<CreditTaskVO> result = queryReceiveData(creditTaskVO, platformType);
		putPlatformByRequest(WinUtils.changeType2Platform(platformType));
		putPlatformTypeByRequest(platformType);
		putByRequest("result", result);
		return "initReceivedTast";
	}

	/**
	 * 退出任务
	 * 
	 * @param creditTaskVO
	 * @return
	 */
	public String updateQuitTask(CreditTaskVO creditTaskVO) throws Exception {
		String platformType = getPlatformType();
		putIndexShowType(platformType);
		putTaskShowType("3");
		UserLoginInfo loginInfo = getLoginUser();
		Long taskId = creditTaskVO.getId();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReceivePerson().getId().equals(
							getLoginUser().getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReceivedTast";
			}
			if (!creditTask.getStatus().equals(TaskMananger.STEP_TWO_STATUS)
					&& !creditTask.getStatus()
							.equals(TaskMananger.AUDIT_STATUS)) {
				putAlertMsg("退出失败，任务状态已经改变！");
				List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
						platformType);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				putByRequest("result", result);
				return "initReceivedTast";
			}
			UserEntity receiveUser = creditTask.getReceivePerson();
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
		putAlertMsg("已经退出任务！");
		List<CreditTaskVO> result = queryReceiveData(creditTaskVO, platformType);
		putPlatformByRequest(WinUtils.changeType2Platform(platformType));
		putPlatformTypeByRequest(platformType);
		putByRequest("result", result);
		return "initReceivedTast";
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
		/**
		 * 验证
		 */
		// 如果发布人不是当前的登陆人就报错
		if (creditTask == null || !platformType.equals(creditTask.getType())) {
			putAlertMsg("任务不存在！");
			putJumpSelfPage("taskManager/task!initTask.php?platformType="
					+ platformType);
			return JUMP;
		}
		if (!creditTask.getStatus().equals(TaskMananger.STEP_ONE_STATUS)) {
			putAlertMsg("接手任务失败，任务状态已经改变！");
			putJumpSelfPage("taskManager/task!initTask.php?platformType="
					+ platformType);
			return JUMP;
		}
		if (!StringUtils.isBlank(creditTask.getAssignUser())
				&& !creditTask.getAssignUser().equals(userEntity.getUsername())) {
			putAlertMsg("这是特殊任务，您不是指定的人！");
			putJumpSelfPage("taskManager/task!initTask.php?platformType="
					+ platformType);
			return JUMP;
		}
		if (!userEntity.getStatus().equals("1")) {
			switch (Integer.parseInt(userEntity.getStatus())) {
			case 0:
				putAlertMsg("您当前的【状态】为【未激活状态】，请到个人中心激活！");
				break;
			case 2:
				putAlertMsg("您当前的【状态】为【冻结状态】，不能发布任务！");
				break;
			case 3:
				putAlertMsg("您当前的【状态】为【找密码状态】，可能有人试图盗取您的秘密，请联系管理员，不能发布任务！");
				break;
			default:
				putAlertMsg("您当前的【状态】不是【正常状态】，不能发布任务！");
				break;
			}
			putJumpSelfPage("userInfoManager/info!initActiave.php");
			return JUMP;
		}
		/**
		 * 验证卖号和买号是否同一个人
		 */
		if (buyerEntitiy.getName().equalsIgnoreCase(
				creditTask.getSeller().getName())) {
			putAlertMsg("买号和卖号不能相同！");
			putJumpSelfPage("taskManager/task!initTask.php?platformType="
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
		Boolean refuseFlag = (Long) creditTaskDAO
				.uniqueResultObject(hqlOne, new String[] { "bid", "receiveIP",
						"year", "month", "itemUrl" }, new Object[] {
						buyerEntitiy.getId(), ip, year, month,
						creditTask.getItemUrl() }) == 1;
		if (!refuseFlag) {
			refuseFlag = (Long) creditTaskDAO.uniqueResultObject(hqlSix,
					new String[] { "bid", "receiveIP", "year", "month",
							"shopUrl" }, new Object[] { buyerEntitiy.getId(),
							ip, year, month,
							creditTask.getSeller().getShopURL() }) == 6;
		}
		if (refuseFlag) {
			putAlertMsg("为了您和他人的安全，同一商品买号在一个月内只能接手一次，同一店铺的商品买号最多只能接手6次！");
			putJumpSelfPage("taskManager/task!initTask.php?platformType="
					+ platformType);
			return JUMP;
		}
		/**
		 * 改变任务属性
		 */
		// 接手人
		creditTask.setReceivePerson(userEntity);
		// Ip
		creditTask.setReceiveIP(ip);
		creditTask.setBuyer(buyerEntitiy);
		creditTask.setRemainTime(20L);
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
		putIndexShowType(platformType);
		putTaskShowType("4");
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = creditTaskVO.getId();
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			// 如果发布人不是当前的登陆人就报错
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							getLoginUser().getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			if (!creditTask.getStatus().equals(TaskMananger.STEP_FIVE_STATUS)) {
				putAlertMsg("已经好评，，任务状态已经改变！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			UserEntity receiveUser = creditTask.getReceivePerson();
			UserEntity releaseUser = creditTask.getReleasePerson();
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
			 * 修改积分和钱 接收人 和 接手号
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
			// 积累接手100个任务
			if (receiveUser.getReceiveTaskCount() % 100 == 0) {
				UserEntity refereeUser = receiveUser.getReferee();
				if (refereeUser != null) {
					refereeUser.setMoney(Constant.getTask100RefreeMoney()
							+ refereeUser.getMoney());
					logMoneyCapital(userDAO, Constant.getTask100RefreeMoney(),
							"你推广的用户接手了100个任务你获得"
									+ Constant.getTask100RefreeMoney() + "元！",
							refereeUser);
				}
			}
			// 通过你的宣传链接注册的会员积分每上升1000
			// 你的收益=100积分
			ScoreStrategy.updateRefreeScoreByScore(userDAO, receiveUser);
			// 记录 信息

			logMoneyCapital(userDAO, creditTask.getMoney()
					+ creditTask.getAddtionMoney(), "接手任务获取金额", receiveUser);

			logDotCapital(userDAO, releaseDot
					+ creditTask.getAddtionReleaseDot(), "接手任务获取发布点",
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
			List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
					platformType);
			putByRequest("result", result);
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			return "initReleasedTast";
		}
	}

	/**
	 * 卖家发货
	 */
	public String updateDispatch(CreditTaskVO creditTaskVO) throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		putIndexShowType(platformType);
		putTaskShowType("4");
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = creditTaskVO.getId();
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			// 如果发布人不是当前的登陆人就报错
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							getLoginUser().getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			if (!creditTask.getStatus().equals(TaskMananger.STEP_THREE_STATUS)) {
				putAlertMsg("发货失败，任务状态已经改变！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			if (creditTask.getIntervalHour() > 0) {
				creditTask.setRemainTime(1L);
			}
			creditTask.setStatus(TaskMananger.STEP_FOUR_STATUS);
			putAlertMsg("发货成功！");
			List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
					platformType);
			putByRequest("result", result);
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			return "initReleasedTast";
		}
	}

	/**
	 * 清理买家
	 */
	public String updateClearReceiver(CreditTaskVO creditTaskVO)
			throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		putIndexShowType(platformType);
		putTaskShowType("4");
		UserEntity receiveUser = userDAO.get(getLoginUser().getId());
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = creditTaskVO.getId();
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			// 如果发布人不是当前的登陆人就报错
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							getLoginUser().getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			if (!creditTask.getStatus().equals(TaskMananger.STEP_TWO_STATUS)
					&& !creditTask.getStatus()
							.equals(TaskMananger.AUDIT_STATUS)) {
				putAlertMsg("清理失败，任务状态已经改变！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
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
			putAlertMsg("清理买家成功！");
			List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
					platformType);
			putByRequest("result", result);
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			return "initReleasedTast";
		}
	}

	/**
	 * 审核接手人
	 */
	public String updateAudiReceiver(CreditTaskVO creditTaskVO)
			throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		putIndexShowType(platformType);
		putTaskShowType("4");
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = creditTaskVO.getId();
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			// 如果发布人不是当前的登陆人就报错
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							getLoginUser().getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			if (!creditTask.getStatus().equals(TaskMananger.AUDIT_STATUS)) {
				putAlertMsg("审核失败，任务状态已经改变！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			/**
			 * 验证
			 */
			creditTask.setStatus(TaskMananger.STEP_TWO_STATUS);
			putAlertMsg("审核完成，联系买家付款吧！");
			List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
					platformType);
			putByRequest("result", result);
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			return "initReleasedTast";
		}
	}

	/**
	 * 加时
	 */
	public String updateAddTime(CreditTaskVO creditTaskVO) throws Exception {
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		putIndexShowType(platformType);
		putTaskShowType("4");
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = creditTaskVO.getId();
			UserEntity userEntity = userDAO.get(getLoginUser().getId());
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			// 如果发布人不是当前的登陆人就报错
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							userEntity.getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			if (!TaskMananger.AUDIT_STATUS.equals(creditTask.getStatus())
					&& !TaskMananger.STEP_TWO_STATUS.equals(creditTask
							.getStatus())) {
				putAlertMsg("加时失败,任务状态已经改变！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			Date currOperDate = creditTask.getReceiveDate();
			/**
			 * 真正的逻辑 修改时间
			 */
			creditTask.setRemainTime(20L);
			putAlertMsg("加时成功！");
			List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
					platformType);
			putByRequest("result", result);
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			return "initReleasedTast";
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
		putIndexShowType(platformType);
		putTaskShowType("4");
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = creditTaskVO.getId();
			UserEntity userEntity = userDAO.get(getLoginUser().getId());
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			// 如果发布人不是当前的登陆人就报错
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							userEntity.getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			if (!TaskMananger.STEP_ONE_STATUS.equals(creditTask.getStatus())) {
				putAlertMsg("刷新失败,任务状态已经改变！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}

			/**
			 * 真正的逻辑 修改时间
			 */
			creditTask.setReleaseDate(new Date());
			putAlertMsg("已经排前！");
			List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
					platformType);
			putByRequest("result", result);
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			return "initReleasedTast";
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
		putIndexShowType(platformType);
		putTaskShowType("4");
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = creditTaskVO.getId();
			UserEntity userEntity = userDAO.get(getLoginUser().getId());
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			// 如果发布人不是当前的登陆人就报错
			if (creditTask == null
					|| !platformType.equals(creditTask.getType())
					|| !creditTask.getReleasePerson().getId().equals(
							userEntity.getId())) {
				putAlertMsg("任务不存在！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
			// 验证
			if (!TaskMananger.TIMING_STATUS.equals(creditTask.getStatus())
					&& !TaskMananger.STEP_ONE_STATUS.equals(creditTask
							.getStatus())) {
				putAlertMsg("取消失败,任务状态已经改变！");
				List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
						platformType);
				putByRequest("result", result);
				putPlatformByRequest(WinUtils.changeType2Platform(platformType));
				putPlatformTypeByRequest(platformType);
				return "initReleasedTast";
			}
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
			logMoneyCapital(userDAO, creditTask.getMoney()
					+ creditTask.getAddtionMoney(), "取消重填  任务 返回金额", userEntity);
			logDotCapital(userDAO, creditTask.getReleaseDot()
					+ creditTask.getAddtionReleaseDot(), "取消重填  任务 返回发布点",
					userEntity);
			updateUserLoginInfo(userEntity);
			putAlertMsg("取消成功，金额已返回！");
			List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
					platformType);
			putByRequest("result", result);
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			return "initReleasedTast";
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
	public String updateInitReceivedTast(CreditTaskVO creditTaskVO)
			throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		updateUserLoginInfo(userEntity);
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		putIndexShowType(platformType);
		putTaskShowType("3");
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
			 * 更新用户信息
			 */
			updateUserLoginInfo(userEntity);
			/**
			 * 更新加时的剩余时间
			 */
			Session session = creditTaskDAO.obtainSession();
			String updateRemainTimeSql1 = "update"
					+ " Tb_CreditTask as _task      "
					+ "   set "
					+ "     _task.REMAIN_TIME_=20-(UNIX_TIMESTAMP(sysdate())- UNIX_TIMESTAMP(_task.RECEIVE_DATE_))/60"
					+ "   where  (      _task.STATUS_='2'      or _task.STATUS_='-2'   ) "
					+ "   and _task.RECEIVE_PERSON_=:userId and  _task.TYPE_=:platformType  and  _task.REMAIN_TIME_>0";
			Query query = session.createSQLQuery(updateRemainTimeSql1);
			query.setLong("userId", getLoginUser().getId());
			query.setString("platformType", platformType);
			query.executeUpdate();
			// 更新多久好评时间
			String updateRemainTimeSql2 = "update"
					+ " Tb_CreditTask as _task      "
					+ "   set "
					+ "     _task.REMAIN_TIME_= _task.INTERVAL_HOUR_*60-(UNIX_TIMESTAMP(sysdate())- UNIX_TIMESTAMP(_task.RECEIVE_DATE_))/60"
					+ "   where      _task.STATUS_='4'     and _task.INTERVAL_HOUR_>0"
					+ "   and _task.RECEIVE_PERSON_=:userId and  _task.TYPE_=:platformType  and  _task.REMAIN_TIME_>0";
			query = session.createSQLQuery(updateRemainTimeSql2);
			query.setLong("userId", getLoginUser().getId());
			query.setString("platformType", platformType);
			query.executeUpdate();
			session.flush();
			List<CreditTaskVO> result = queryReceiveData(creditTaskVO,
					platformType);
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			putByRequest("result", result);
			return "initReceivedTast";
		}
	}

	/**
	 * 接手数据查询
	 * 
	 * @param creditTaskVO
	 * @param platformType
	 * @return
	 * @throws Exception
	 */
	private List<CreditTaskVO> queryReceiveData(CreditTaskVO creditTaskVO,
			String platformType) throws Exception {
		// 分页查询
		List<String> paramNames = new ArrayList<String>();
		List paramValues = new ArrayList();
		StringBuffer countSQL = new StringBuffer(
				"select count(*) 	 from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer "
						+ " where     _jsuser.id=:userId and   _task.type=:platformType ");
		StringBuffer resultSQL = new StringBuffer(
				"select _task.testID , _task.releaseDate ,_fbuser.username,_fbuser.qq,_task.money,_task.updatePrice ,_task.releaseDot "// 6
						+ ", _task.itemUrl , _seller.name,_seller.shopURL,_buyer.name,_jsuser.upgradeScore,_task.status" // 12
						+ ", _task.remainTime,_task.taskType ,_task.intervalHour,_task.comment,_task.address ,_task.grade,_task.id ," // 19
						+ "_fbuser.ww,_task.waybill,_task.addtionMoney,_task.addtionReleaseDot,_fbuser.upgradeScore," // 24
						+ "_task.assignUser,_fbuser.telephone,_jsuser.username,_task.receiveDate" // index=28
						+ " from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer "
						+ " where     _jsuser.id=:userId and   _task.type=:platformType ");
		paramNames.add("userId");
		paramNames.add("platformType");
		paramValues.add(getLoginUser().getId());
		paramValues.add(platformType);
		if (!StringUtils.isBlank(creditTaskVO.getTestID())) {
			countSQL.append(" and _task.testID=:testID ");
			resultSQL.append(" and _task.testID=:testID ");
			paramNames.add("testID");
			paramValues.add(creditTaskVO.getTestID());
		}
		if (!StringUtils.isBlank(creditTaskVO.getJsUsername())) {
			countSQL.append(" and _jsuser.username=:jsUsername ");
			resultSQL.append(" and _jsuser.username=:jsUsername ");
			paramNames.add("jsUsername");
			paramValues.add(creditTaskVO.getJsUsername());
		}
		if (!StringUtils.isBlank(creditTaskVO.getSellname())) {
			countSQL.append(" and _seller.name=:sellername ");
			resultSQL.append(" and _seller.name=:sellername ");
			paramNames.add("sellername");
			paramValues.add(creditTaskVO.getSellname());
		}
		// 发布 时间
		if (creditTaskVO.getFbStartDate() != null
				&& creditTaskVO.getFbEndDate() != null) {
			countSQL
					.append(" and (_task.releaseDate>=:fbStartDate and _task.releaseDate<=:fbEndDate) ");
			resultSQL
					.append(" and (_task.releaseDate>=:fbStartDate and _task.releaseDate<=:fbEndDate) ");
			paramNames.add("fbStartDate");
			paramNames.add("fbEndDate");
			paramValues.add(creditTaskVO.getFbStartDate());
			paramValues.add(creditTaskVO.getFbEndDate());
		} else if (creditTaskVO.getFbStartDate() != null) {
			resultSQL.append(" and _task.releaseDate>=:fbStartDate  ");
			countSQL.append(" and  _task.releaseDate>=:fbStartDate   ");
			paramNames.add("fbStartDate");
			paramValues.add(creditTaskVO.getFbStartDate());
		} else if (creditTaskVO.getFbEndDate() != null) {
			resultSQL.append(" and    _task.releaseDate<=:fbEndDate  ");
			countSQL.append(" and   _task.releaseDate<=:fbEndDate  ");
			paramNames.add("fbEndDate");
			paramValues.add(creditTaskVO.getFbEndDate());
		}
		// 接手 时间
		if (creditTaskVO.getJsStartDate() != null
				&& creditTaskVO.getJsEndDate() != null) {
			countSQL
					.append(" and (_task.receiveDate>=:jsStartDate and _task.receiveDate<=:jsEndDate) ");
			resultSQL
					.append(" and (_task.receiveDate>=:jsStartDate and _task.receiveDate<=:jsEndDate) ");
			paramNames.add("jsStartDate");
			paramNames.add("jsEndDate");
			paramValues.add(creditTaskVO.getJsStartDate());
			paramValues.add(creditTaskVO.getJsEndDate());
		} else if (creditTaskVO.getJsStartDate() != null) {
			resultSQL.append(" and _task.receiveDate>=:jsStartDate  ");
			countSQL.append(" and  _task.receiveDate>=:jsStartDate   ");
			paramNames.add("jsStartDate");
			paramValues.add(creditTaskVO.getJsStartDate());
		} else if (creditTaskVO.getJsEndDate() != null) {
			resultSQL.append(" and    _task.receiveDate<=:jsEndDate  ");
			countSQL.append(" and   _task.receiveDate<=:jsEndDate  ");
			paramNames.add("jsEndDate");
			paramValues.add(creditTaskVO.getJsEndDate());
		}
		if (!StringUtils.isBlank(creditTaskVO.getBuyername())) {
			countSQL.append(" and _buyer.name=:buyname ");
			resultSQL.append(" and _buyer.name=:buyname ");
			paramNames.add("buyname");
			paramValues.add(creditTaskVO.getBuyername());
		}
		if (!StringUtils.isBlank(creditTaskVO.getStatus())) {
			countSQL.append(" and _task.status=:status ");
			resultSQL.append(" and _task.status=:status ");
			paramNames.add("status");
			paramValues.add(creditTaskVO.getStatus());
		}
		if (!StringUtils.isBlank(creditTaskVO.getTaskType())) {
			countSQL.append(" and _task.taskType=:taskType ");
			resultSQL.append(" and _task.taskType=:taskType ");
			paramNames.add("taskType");
			paramValues.add(creditTaskVO.getTaskType());
		}
		resultSQL.append(" order by _task.status asc, _task.releaseDate desc ");
		Long count = (Long) creditTaskDAO.uniqueResultObject(countSQL
				.toString(), paramNames.toArray(paramNames
				.toArray(new String[paramNames.size()])), paramValues
				.toArray(new Object[paramValues.size()]));
		List<CreditTaskVO> result = new ArrayList<CreditTaskVO>(count
				.intValue());
		if (count > 0) {
			List<Object[]> resultTemp = creditTaskDAO.pageQuery(resultSQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), creditTaskVO
					.getStart(), creditTaskVO.getLimit());
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
				creditTaskVO2.setRemainTime((Long) objs[13]);
				creditTaskVO2.setTaskType((String) objs[14]);
				creditTaskVO2.setIntervalHour((Integer) objs[15]);
				creditTaskVO2.setComment((String) objs[16]);
				creditTaskVO2.setAddress((String) objs[17]);
				creditTaskVO2.setGrade((String) objs[18]);
				creditTaskVO2.setId((Long) objs[19]);
				creditTaskVO2.setFbWW((String) objs[20]);
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
		}
		creditTaskVO.setDataCount(count.intValue());
		return result;
	}

	/**
	 * 初始化已发任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String updateInitReleasedTast(CreditTaskVO creditTaskVO)
			throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		updateUserLoginInfo(userEntity);

		// 没有操作码验证就验证
		String platformType = getPlatformType();
		putIndexShowType(platformType);
		putTaskShowType("4");
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
			 * 更新用户信息
			 */
			updateUserLoginInfo(userEntity);
			/**
			 * 更新审核或者等待的剩余时间
			 */
			Session session = creditTaskDAO.obtainSession();
			String updateRemainTimeSql1 = "update"
					+ " Tb_CreditTask as _task      "
					+ "   set "
					+ "     _task.REMAIN_TIME_=20-(UNIX_TIMESTAMP(sysdate())- UNIX_TIMESTAMP(_task.RECEIVE_DATE_))/60"
					+ "   where  (      _task.STATUS_='2'      or _task.STATUS_='-2'   ) "
					+ "   and _task.RELEASE_PERSON_=:userId and  _task.TYPE_=:platformType  and  _task.REMAIN_TIME_>0";
			Query query = session.createSQLQuery(updateRemainTimeSql1);
			query.setLong("userId", getLoginUser().getId());
			query.setString("platformType", platformType);
			query.executeUpdate();
			// 更新多久好评时间
			String updateRemainTimeSql2 = "update"
					+ " Tb_CreditTask as _task      "
					+ "   set "
					+ "     _task.REMAIN_TIME_= _task.INTERVAL_HOUR_*60-(UNIX_TIMESTAMP(sysdate())- UNIX_TIMESTAMP(_task.RECEIVE_DATE_))/60"
					+ "   where      _task.STATUS_='4'     and _task.INTERVAL_HOUR_>0"
					+ "   and _task.RELEASE_PERSON_=:userId and  _task.TYPE_=:platformType  and  _task.REMAIN_TIME_>0";
			query = session.createSQLQuery(updateRemainTimeSql2);
			query.setLong("userId", getLoginUser().getId());
			query.setString("platformType", platformType);
			query.executeUpdate();
			session.flush();
			List<CreditTaskVO> result = queryReleaseData(creditTaskVO,
					platformType);
			putByRequest("result", result);
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			return "initReleasedTast";
		}
	}

	/**
	 * 发布数据调整
	 * 
	 * @param creditTaskVO
	 * @param platformType
	 * @return
	 * @throws Exception
	 */
	private List<CreditTaskVO> queryReleaseData(CreditTaskVO creditTaskVO,
			String platformType) throws Exception {
		// 分页查询
		List<String> paramNames = new ArrayList<String>();
		List paramValues = new ArrayList();
		StringBuffer countSQL = new StringBuffer(
				"select count(*)	  from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer "
						+ " where     _fbuser.id=:userId and   _task.type=:platformType ");
		StringBuffer resultSQL = new StringBuffer(
				"select _task.testID , _task.releaseDate ,_task.money,_task.updatePrice ,_task.releaseDot, _task.itemUrl , _seller.name,_task.status "// 7
						+ ", _jsuser.username,_buyer.name,_jsuser.qq" // 10
						+ ", _task.remainTime,_task.taskType ,_task.intervalHour,_task.comment,_task.address ,_task.grade," // 16
						+ "_task.id,_seller.shopURL ,_jsuser.ww,_task.waybill,_task.timeingTime,_task.addtionMoney," // 22
						+ "_task.addtionReleaseDot ,_task.assignUser,_jsuser.telephone,_fbuser.username,_task.receiveDate" // index=27
						+ " from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer "
						+ " where     _fbuser.id=:userId and   _task.type=:platformType ");
		paramNames.add("userId");
		paramNames.add("platformType");
		paramValues.add(getLoginUser().getId());
		paramValues.add(platformType);
		if (!StringUtils.isBlank(creditTaskVO.getTestID())) {
			countSQL.append(" and _task.testID=:testID ");
			resultSQL.append(" and _task.testID=:testID ");
			paramNames.add("testID");
			paramValues.add(creditTaskVO.getTestID());
		}
		if (!StringUtils.isBlank(creditTaskVO.getJsUsername())) {
			countSQL.append(" and _jsuser.username=:jsUsername ");
			resultSQL.append(" and _jsuser.username=:jsUsername ");
			paramNames.add("jsUsername");
			paramValues.add(creditTaskVO.getJsUsername());
		}
		if (!StringUtils.isBlank(creditTaskVO.getSellname())) {
			countSQL.append(" and _seller.name=:sellername ");
			resultSQL.append(" and _seller.name=:sellername ");
			paramNames.add("sellername");
			paramValues.add(creditTaskVO.getSellname());
		}
		// 发布 时间
		if (creditTaskVO.getFbStartDate() != null
				&& creditTaskVO.getFbEndDate() != null) {
			countSQL
					.append(" and (_task.releaseDate>=:fbStartDate and _task.releaseDate<=:fbEndDate) ");
			resultSQL
					.append(" and (_task.releaseDate>=:fbStartDate and _task.releaseDate<=:fbEndDate) ");
			paramNames.add("fbStartDate");
			paramNames.add("fbEndDate");
			paramValues.add(creditTaskVO.getFbStartDate());
			paramValues.add(creditTaskVO.getFbEndDate());
		} else if (creditTaskVO.getFbStartDate() != null) {
			resultSQL.append(" and _task.releaseDate>=:fbStartDate  ");
			countSQL.append(" and  _task.releaseDate>=:fbStartDate   ");
			paramNames.add("fbStartDate");
			paramValues.add(creditTaskVO.getFbStartDate());
		} else if (creditTaskVO.getFbEndDate() != null) {
			resultSQL.append(" and    _task.releaseDate<=:fbEndDate  ");
			countSQL.append(" and   _task.releaseDate<=:fbEndDate  ");
			paramNames.add("fbEndDate");
			paramValues.add(creditTaskVO.getFbEndDate());
		}
		// 接手 时间
		if (creditTaskVO.getJsStartDate() != null
				&& creditTaskVO.getJsEndDate() != null) {
			countSQL
					.append(" and (_task.receiveDate>=:jsStartDate and _task.receiveDate<=:jsEndDate) ");
			resultSQL
					.append(" and (_task.receiveDate>=:jsStartDate and _task.receiveDate<=:jsEndDate) ");
			paramNames.add("jsStartDate");
			paramNames.add("jsEndDate");
			paramValues.add(creditTaskVO.getJsStartDate());
			paramValues.add(creditTaskVO.getJsEndDate());
		} else if (creditTaskVO.getJsStartDate() != null) {
			resultSQL.append(" and _task.receiveDate>=:jsStartDate  ");
			countSQL.append(" and  _task.receiveDate>=:jsStartDate   ");
			paramNames.add("jsStartDate");
			paramValues.add(creditTaskVO.getJsStartDate());
		} else if (creditTaskVO.getJsEndDate() != null) {
			resultSQL.append(" and    _task.receiveDate<=:jsEndDate  ");
			countSQL.append(" and   _task.receiveDate<=:jsEndDate  ");
			paramNames.add("jsEndDate");
			paramValues.add(creditTaskVO.getJsEndDate());
		}
		if (!StringUtils.isBlank(creditTaskVO.getBuyername())) {
			countSQL.append(" and _buyer.name=:buyname ");
			resultSQL.append(" and _buyer.name=:buyname ");
			paramNames.add("buyname");
			paramValues.add(creditTaskVO.getBuyername());
		}
		if (!StringUtils.isBlank(creditTaskVO.getStatus())) {
			countSQL.append(" and _task.status=:status ");
			resultSQL.append("and _task.status=:status ");
			paramNames.add("status");
			paramValues.add(creditTaskVO.getStatus());
		}
		if (!StringUtils.isBlank(creditTaskVO.getTaskType())) {
			countSQL.append(" and _task.taskType=:taskType ");
			resultSQL.append("and _task.taskType=:taskType ");
			paramNames.add("taskType");
			paramValues.add(creditTaskVO.getTaskType());
		}
		resultSQL.append(" order by  _task.status asc,_task.releaseDate desc ");
		Long count = (Long) creditTaskDAO.uniqueResultObject(countSQL
				.toString(), paramNames.toArray(paramNames
				.toArray(new String[paramNames.size()])), paramValues
				.toArray(new Object[paramValues.size()]));
		List<CreditTaskVO> result = new ArrayList<CreditTaskVO>(count
				.intValue());
		if (count > 0) {
			List<Object[]> resultTemp = creditTaskDAO.pageQuery(resultSQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), creditTaskVO
					.getStart(), creditTaskVO.getLimit());
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
				creditTaskVO2.setRemainTime((Long) objs[11]);
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
		}
		creditTaskVO.setDataCount(count.intValue());
		return result;
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
		} else if (creditTaskVO.getGrade().indexOf("三天") != -1) {
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
			case 0:
				putAlertMsg("您当前的【状态】为【未激活状态】，请到个人中心激活！");
				break;
			case 2:
				putAlertMsg("您当前的【状态】为【冻结状态】，不能发布任务！");
				break;
			case 3:
				putAlertMsg("您当前的【状态】为【找密码状态】，可能有人试图盗取您的秘密，请联系管理员，不能发布任务！");
				break;
			default:
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
				}
				linkMan.setLastUseTime(new Date());
				linkMan.setUseCount(linkMan.getUseCount() + 1);
			}
			creditTask.setReleaseDot(0D);
		}
		/**
		 * 接手人自己想
		 */
		String commentByJS = getByParam("commentByJS");
		if ("true".equalsIgnoreCase(commentByJS)) {
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
			BeanUtils.copyProperties(creditTaskRepository, creditTask);
			creditTaskRepository.setUser(userEntity);
			creditTaskRepository.setType(platFormType);
			creditTaskRepository.setSeller(creditTask.getSeller());
			creditTaskRepository.setAddress(!StringUtils.isBlank(creditTaskVO
					.getAddress()));
			if (StringUtils.isBlank(creditTaskVO.getRespositoryName())) {
				creditTaskRepository.setName(creditTask.getTestID());
			} else {
				creditTaskRepository.setName(creditTaskVO.getRespositoryName());
			}
			creditTaskRepositoryDAO.save(creditTaskRepository);
		}
		// 查看任务仓库
		String taskRepId = getByParam("taskRepId");
		if (!StringUtils.isBlank(taskRepId)) {
			CreditTaskRepositoryEntity taskRep = creditTaskRepositoryDAO
					.get(Long.parseLong(taskRepId));
			taskRep.setLastDispathDate(new Date());
			taskRep.setDispathCount(taskRep.getDispathCount() + 1);
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
		putIndexShowType(platformType);
		putTaskShowType("2");
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			UserEntity userEntity = getLoginUserEntity(userDAO);
			if (!userEntity.getStatus().equals("1")) {
				switch (Integer.parseInt(userEntity.getStatus())) {
				case 0:
					putAlertMsg("您当前的【状态】为【未激活状态】，请到个人中心激活！");
					break;
				case 2:
					putAlertMsg("您当前的【状态】为【冻结状态】，不能发布任务！");
					break;
				case 3:
					putAlertMsg("您当前的【状态】为【找密码状态】，可能有人试图盗取您的秘密，请联系管理员，不能发布任务！");
					break;
				default:
					putAlertMsg("您当前的【状态】不是【正常状态】，不能发布任务！");
					break;
				}
				putJumpSelfPage("userInfoManager/info!initActiave.php");
				return JUMP;
			}
			List<SellerEntity> sellers = sellerDAO
					.list(
							"select _s   from SellerEntity  as _s where _s.type=:type and _s.user.id=:userID",
							new String[] { "type", "userID" }, new Object[] {
									platformType, userEntity.getId() });
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

			String taskRepId = getByParam("taskRepId");
			if (!StringUtils.isBlank(taskRepId)) {
				CreditTaskRepositoryEntity taskRep = creditTaskRepositoryDAO
						.get(Long.parseLong(taskRepId));
				if (taskRep.getUser().getId().equals(getLoginUser().getId())) {
					putByRequest("taskRep", taskRepId);
					BeanUtils.copyProperties(creditTaskVO, taskRep);
				}
			}
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			putByRequest("sellers", resultSellers);
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
		List<String> tlms = taskLinkManDAO.queryLinkMan(getLoginUser().getId());
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
		putIndexShowType(platformType);
		putTaskShowType("1");
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		}
		if (platformType == null) {
			WinUtils.throwIllegalityException(getLoginUser().getUsername()
					+ "试图越过初始化任务!");
		}

		// 分页查询
		List<String> paramNames = new ArrayList<String>();
		List paramValues = new ArrayList();
		StringBuffer countSQL = new StringBuffer(
				"select count(*)"
						+ " from CreditTaskEntity as _task inner join _task.releasePerson as _user where  _task.status not  in ('0','-1')  and   _task.type=:platformType");
		StringBuffer resultSQL = new StringBuffer(
				"select _task.testID , _task.releaseDate ,_user.username,_user.upgradeScore,_task.money,_task.updatePrice, " // 5
						+ "_task.taskType,_task.releaseDot,_task.status ,_task.intervalHour,_task.id,_task.grade " // 11
						+ ",_vip.type,_task.addtionMoney,_task.addtionReleaseDot,_task.assignUser" // index=15
						+ " from CreditTaskEntity as _task inner join _task.releasePerson as _user  left join _user.vip as _vip "
						+ " where _task.status not  in ('0','-1')   and   _task.type=:platformType ");
		paramNames.add("platformType");
		paramValues.add(platformType);
		if ("1".equals(creditTaskVO.getMoneyFlag())) {
			// 默认时间排列
			resultSQL
					.append("    order by  _task.status asc,_vip.type desc ,_task.releaseDate desc ");
		} else if ("2".equals(creditTaskVO.getMoneyFlag())) {
			// 价低排列
			resultSQL.append("   order _task.status asc,_vip.type desc , _task.money asc ");
		} else if ("3".equals(creditTaskVO.getMoneyFlag())) {
			// 价高排列
			resultSQL
					.append(" order by _task.status asc,_vip.type desc ,    _task.money desc ");
		} else if ("4".equals(creditTaskVO.getMoneyFlag())) {
			// 1-40
			countSQL.append(" and  (_task.money>0 and _task.money<=40)  ");
			resultSQL
					.append(" and (_task.money>40 and _task.money<=100) order by    _task.status asc,_vip.type desc ,_task.releaseDate desc,  _task.money asc ");
		} else if ("5".equals(creditTaskVO.getMoneyFlag())) {
			// 40-100
			resultSQL.append(" and (_task.money>40 and _task.money<=100)");
			resultSQL
					.append(" and (_task.money>40 and _task.money<=100) order by    _task.status asc,_vip.type desc ,_task.releaseDate desc,  _task.money asc ");
		} else if ("6".equals(creditTaskVO.getMoneyFlag())) {
			// 100-200
			countSQL.append(" and   (_task.money>100 and _task.money<=200) ");
			resultSQL
					.append(" and   (_task.money>100 and _task.money<=200) order by  _task.status asc,  _vip.type desc ,_task.releaseDate desc,  _task.money asc ");
		} else if ("7".equals(creditTaskVO.getMoneyFlag())) {
			countSQL.append(" and   (_task.money>200 and _task.money<=500) ");
			resultSQL
					.append(" and   (_task.money>200 and _task.money<=500) order by   _task.status asc, _vip.type desc ,_task.releaseDate desc,  _task.money asc ");
		} else if ("8".equals(creditTaskVO.getMoneyFlag())) {
			countSQL.append(" and     _task.money>500  ");
			resultSQL
					.append(" and     _task.money>500 order by  _task.status asc,  _vip.type desc ,_task.releaseDate desc,  _task.money asc ");
		} else {
			resultSQL
					.append("    order by  _task.status asc,_vip.type desc ,_task.releaseDate desc ");
		}
		Long count = (Long) creditTaskDAO.uniqueResultObject(countSQL
				.toString(), paramNames.toArray(paramNames
				.toArray(new String[paramNames.size()])), paramValues
				.toArray(new Object[paramValues.size()]));
		List<CreditTaskVO> result = new ArrayList<CreditTaskVO>(count
				.intValue());
		if (count > 0) {
			List<Object[]> resultTemp = creditTaskDAO.pageQuery(resultSQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), creditTaskVO
					.getStart(), creditTaskVO.getLimit());
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
		}
		List<BuyerEntity> buyers = userDAO
				.list(
						" from BuyerEntity  as _b where _b.user.id=:userId  and  _b.type=:type and _b.enable=:enable",
						new String[] { "userId", "type", "enable" },
						new Object[] { getLoginUser().getId(), platformType,
								true });
		List<BuyerVO> resultBuyers = new ArrayList<BuyerVO>(buyers.size());
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
		String result = "";
		if (userEntity.getMoney() < money) {
			creditTaskVO.setExecuteFlag("error");
			creditTaskVO.setMessage("发送失败，您当前的余额不足！");
		} else {
			MsgUtils.sendMsg(telphone, content);
			userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(), money));
			logMoneyCapital(userDAO, 0 - money, "发送长度为" + content.length()
					+ "的短信花费" + money + "元", userEntity);
			updateUserLoginInfo(userEntity);
			creditTaskVO.setExecuteFlag("success");
			creditTaskVO.setMessage("发送成功！");
		}
		return JSON;
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
}
