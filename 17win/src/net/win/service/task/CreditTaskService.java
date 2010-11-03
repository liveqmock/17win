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
import net.win.WinContext;
import net.win.dao.BuyerDAO;
import net.win.dao.CreditTaskDAO;
import net.win.dao.CreditTaskRepositoryDAO;
import net.win.dao.SellerDAO;
import net.win.dao.UserDAO;
import net.win.dao.VipDAO;
import net.win.entity.BuyerEntity;
import net.win.entity.CreditTaskEntity;
import net.win.entity.CreditTaskRepositoryEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;
import net.win.entity.VipBidUserEntity;
import net.win.entity.VipEntity;
import net.win.stragegy.ScoreStrategy;
import net.win.utils.ArithUtils;
import net.win.utils.Constant;
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
@SuppressWarnings("unused")
@Service("creditTaskService")
public class CreditTaskService extends BaseService {
	@Resource
	private UserDAO userDAO;
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
				putJumpPage("taskManager/task!initReceivedTast.php?platformType="
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
			if (!creditTask.getGoodTimeType().equals("1")) {
				Long tempTime = System.currentTimeMillis()
						- creditTask.getDispatchDate().getTime();
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过【买家好评】的时间操作！任务ID是：" + taskId);
			}
			/**
			 * 验证
			 */
			creditTask.setStatus(TaskMananger.STEP_FIVE_STATUS);
			putAlertMsg("好评成功，通知卖家好评吧！");
			putJumpPage("taskManager/task!initReceivedTast.php?platformType="
					+ platformType);

			return JUMP;
		}
	}

	/**
	 * 撤销支付
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
				putJumpPage("taskManager/task!initReceivedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			if (creditTask.getStatus().equals(TaskMananger.STEP_FOUR_STATUS)) {
				putJumpPage("taskManager/task!initReceivedTast.php?platformType="
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
		putJumpPage("taskManager/task!initReceivedTast.php?platformType="
				+ platformType);
		return JUMP;
	}

	/**
	 * 已经支付
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
				putJumpPage("taskManager/task!initReceivedTast.php?platformType="
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
		putJumpPage("taskManager/task!initReceivedTast.php?platformType="
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
				putJumpPage("taskManager/task!initReceivedTast.php?platformType="
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
		putJumpPage("taskManager/task!initReceivedTast.php?platformType="
				+ platformType);
		putAlertMsg("已经退出任务！");
		return JUMP;
	}

	/*
	 * 接受任务
	 * 
	 * @param userVO @return
	 */
	public String updateReceiveTask(CreditTaskVO creditTaskVO) throws Exception {
		String platformType = getPlatformType();

		UserLoginInfo loginInfo = getLoginUser();

		Long taskId = Long.parseLong(getByParam("taskId"));
		CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
		Long buyeId = Long.parseLong(getByParam("buyerId"));
		UserEntity userEntity = userDAO.get(loginInfo.getId());
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
			putJumpPage("userInfoManager/info!initActiave.php");
			return JUMP;
		}
		if (creditTask.getStatus().equals(TaskMananger.STEP_TWO_STATUS)) {
			if (creditTask.getReceivePerson().getId().equals(
					getLoginUser().getId())) {
				putAlertMsg("您已经接受了此任务，不要重复提交！");
			} else {
				putAlertMsg("任务已经被人别抢先接受！！");
			}
			putJumpPage("taskManager/task!initReceivedTast.php?platformType="
					+ platformType);
			return JUMP;
		}
		// 验证状态
		if (!platformType.equals(creditTask.getType())
				|| !TaskMananger.STEP_ONE_STATUS.equals(creditTask.getStatus())
				|| buyeId == null) {
			WinUtils.throwIllegalityException(loginInfo.getUsername()
					+ "试图越过【接受任务】操作！ 任务ID是:" + taskId);
		}

		/**
		 * 验证IP
		 */
		String ip = getIpAddr();
		String hqlOne = "select count(*) from CreditTaskEntity as _task inner join buyer _buyer where _buyer.id=:bid "
				+ "and _task.receiveIP=:receiveIP  "
				+ "and year(_task.receiveDate)=:year and month(_task.receiveDate)=:month having count(_task.itemUrl)>1";
		String hqlSix = "select count(*) from CreditTaskEntity as _task inner join buyer _buyer inner join seller as _seller "
				+ "where _buyer.id=:bid "
				+ "and _task.receiveIP=:receiveIP and year(_task.receiveDate)=:year and month(_task.receiveDate)=:month having count(_seller.shopURL)>6";

		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer month = Calendar.getInstance().get(Calendar.MONTH);
		Long count = (Long) creditTaskDAO.uniqueResultObject(hqlOne,
				new String[] { "bid", "receiveIP", "year", "month" },
				new Object[] { buyeId, ip, year, month });
		if (count == 0) {
			count = (Long) creditTaskDAO.uniqueResultObject(hqlSix,
					new String[] { "bid", "receiveIP", "year", "month" },
					new Object[] { buyeId, ip, year, month });
		}
		if (count > 0) {
			putJumpPage("taskManager/task!initReceivedTast.php?platformType="
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
		BuyerEntity buyerEntity = new BuyerEntity();
		buyerEntity.setId(buyeId);
		creditTask.setBuyer(buyerEntity);
		creditTask.setRemainTime(20);
		creditTask.setReceiveDate(new Date());
		// 保护就到审核状态
		if (creditTask.getProtect()) {
			creditTask.setStatus(TaskMananger.AUDIT_STATUS);
		} else {
			creditTask.setStatus(TaskMananger.STEP_TWO_STATUS);
		}

		putJumpPage("taskManager/task!initReceivedTast.php?platformType="
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
			UserEntity releaEntity = creditTask.getReleasePerson();
			if (creditTask.getStatus().equals(TaskMananger.STEP_SIX_STATUS)) {
				putAlertMsg("已经好评，不要重复提交！");
				putJumpPage("taskManager/task!initReleasedTast.php?platformType="
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
			VipEntity releaseUserVip = releaEntity.getVip();
			VipBidUserEntity releaseVipBidUser = releaEntity
					.getVipBidUserEntity();
			Integer releaseScore = StrategyUtils.getReleaseUserTaskScore(
					releaseUserVip, releaEntity.getVipEnable());
			releaEntity.setUpgradeScore(releaEntity.getUpgradeScore()
					+ releaseScore);
			releaEntity.setConvertScore(releaEntity.getConvertScore()
					+ releaseScore);
			// 有会员
			if (releaEntity.getVipEnable() && releaseUserVip != null) {
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
					creditTask.getMoney()));
			// 有会员
			if (receiveUser.getVipEnable() && receiveUserVip != null) {
				receiveVipBidUser.setGrowValue(receiveVipBidUser.getGrowValue()
						+ receiveUserVip.getReceieveGrowValue());
			}
			/**
			 * 发布点
			 */
			Double releaseDot = creditTask.getReleaseDot()
					* StrategyUtils.getTaskOverDotRate(releaEntity,
							receiveUserVip, receiveUser.getVipEnable());
			receiveUser.setReleaseDot(ArithUtils.add(receiveUser
					.getReleaseDot(), releaseDot));
			/**
			 * 计算积分
			 */
			receiveUser.setUpgradeScore(receiveUser.getUpgradeScore()
					+ receieveScore);
			receiveUser.setConvertScore(receiveUser.getConvertScore()
					+ receieveScore);
			UserLoginInfo userLoginInfo = WinContext.getInstance()
					.getUserLoginInfo(receiveUser.getUsername());
			/**
			 * 计算发布和任务数
			 */
			releaEntity
					.setReleaseTaskCount(releaEntity.getReleaseTaskCount() + 1);
			receiveUser
					.setReceiveTaskCount(releaEntity.getReceiveTaskCount() + 1);
			/**
			 * 计算会员成长值 和升级
			 */
			if (releaseUserVip != null && releaEntity.getVipEnable()) {
				releaseVipBidUser.setGrowValue(releaseVipBidUser.getGrowValue()
						+ StrategyUtils.getReleaseGrowValue(releaseUserVip));
				String vipType = StrategyUtils.getVipType(releaseVipBidUser
						.getGrowValue());
				if (!releaseUserVip.getType().equals(vipType)) {
					releaEntity.setVip(vipDAO.getVIPByType(vipType));
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
			creditTask.setStatus(TaskMananger.STEP_SIX_STATUS);
			putAlertMsg("好评成功，任务完成！");
			putJumpPage("taskManager/task!initReleasedTast.php?platformType="
					+ platformType);

			/**
			 * 计算推广
			 */
			// 积累接受100个任务
			ScoreStrategy.updateRefreeMoneyByTask(receiveUser);
			// 通过你的宣传链接注册的会员积分每上升1000
			// 你的收益=100积分
			ScoreStrategy.updateRefreeScoreByScore(receiveUser);
			ScoreStrategy.updateRefreeScoreByScore(releaEntity);

			// 更新信息
			updateUserLoginInfo(releaEntity);
			updateUserLoginInfo(receiveUser, userLoginInfo);
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
				putJumpPage("taskManager/task!initReleasedTast.php?platformType="
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
				String goodType = creditTask.getGoodTimeType();
				if (!"1".equals(goodType)) {
					creditTask.setRemainTime(creditTask.getIntervalHour());
					creditTask.setDispatchDate(new Date());
				}
				creditTask.setStatus(TaskMananger.STEP_FOUR_STATUS);
				putAlertMsg("发货成功！");
			}
			putJumpPage("taskManager/task!initReleasedTast.php?platformType="
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
				putJumpPage("taskManager/task!initReleasedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			if (creditTask.getStatus().equals(TaskMananger.STEP_ONE_STATUS)) {
				putJumpPage("taskManager/task!initReleasedTast.php?platformType="
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
			putJumpPage("taskManager/task!initReleasedTast.php?platformType="
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
				putJumpPage("taskManager/task!initReleasedTast.php?platformType="
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
			putJumpPage("taskManager/task!initReleasedTast.php?platformType="
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
				putJumpPage("taskManager/task!initReleasedTast.php?platformType="
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
			putJumpPage("taskManager/task!initReleasedTast.php?platformType="
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
				putJumpPage("taskManager/task!initReleasedTast.php?platformType="
						+ platformType);
				return JUMP;
			}
			if (creditTask.getStatus().equals(TaskMananger.STEP_TWO_STATUS)
					|| creditTask.getStatus().equals(TaskMananger.AUDIT_STATUS)) {
				putAlertMsg("取消失败，任务已被接受！");
				putJumpPage("taskManager/task!initReleasedTast.php?platformType="
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
			creditTaskVO.setAddress(!"无".equals(creditTask.getAddress()));
			/**
			 * 删除任务
			 */
			creditTaskDAO.delete(creditTask);
			/**
			 * 修改用户金钱和发布点
			 */
			userEntity.setMoney(ArithUtils.add(userEntity.getMoney(),
					creditTask.getMoney()));
			userEntity.setReleaseDot(ArithUtils.add(userEntity.getReleaseDot(),
					creditTask.getReleaseDot()));
			putByRequest("cancelTask", "cancelTask");
			putByRequest("cancelTask", "cancelTask");
			putAlertMsg("取消成功，金额已返回！");
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
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(), platformType });
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
											true), new String[] { "userId",
									"platformType" }, new Object[] {
									getLoginUser().getId(), platformType });
			List<Object[]> result = creditTaskDAO
					.pageQuery(
							"select _task.testID , _task.releaseDate ,_fbuser.username,_fbuser.qq,_task.money,_task.updatePrice ,_task.releaseDot "// 6
									+ ", _task.itemUrl , _seller.name,_seller.shopURL,_buyer.name,_jsuser.upgradeScore,_task.status" // 12
									+ ", _task.remainTime,_task.goodTimeType ,_task.intervalHour,_task.desc,_task.address ,_task.grade,_task.id ,_task.dispatchDate,_fbuser.ww,_task.waybill" // index=22
									+ " from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer  where     _jsuser.id=:userId and   _task.type=:platformType "
									+ orderAndWhereReceivedTaskStr(queryType,
											false), new String[] { "userId",
									"platformType" }, new Object[] {
									getLoginUser().getId(), platformType },
							creditTaskVO.getStart(), creditTaskVO.getLimit());
			Long currentDate = System.currentTimeMillis();
			for (Object[] objs : result) {
				if (TaskMananger.STEP_FOUR_STATUS.equals(objs[12])) {
					if (!objs[14].equals("1")) {
						Long nowCurrTime = (currentDate - ((Date) objs[20])
								.getTime());
						Integer remainTime = (Integer) objs[13] * 60 * 60 * 1000;
						if (nowCurrTime >= remainTime) {
							objs[13] = 0 + "";
						} else {
							double temp = ((double) (remainTime - nowCurrTime)) / 60 / 60 / 1000;
							if (temp > 1) {
								objs[13] = (remainTime - nowCurrTime) / 60 / 60
										/ 1000 + "";
							} else {
								objs[13] = StringUtils.formatNumber(temp);
							}
						}
					}
				}
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
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(), platformType });
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
											true), new String[] { "userId",
									"platformType" }, new Object[] {
									getLoginUser().getId(), platformType });
			List<Object[]> result = creditTaskDAO
					.pageQuery(
							"select _task.testID , _task.releaseDate ,_task.money,_task.updatePrice ,_task.releaseDot, _task.itemUrl , _seller.name,_task.status "// 7
									+ ", _jsuser.username,_buyer.name,_jsuser.qq,_jsuser.upgradeScore" // 11
									+ ", _task.remainTime,_task.goodTimeType ,_task.intervalHour,_task.desc,_task.address ,_task.grade,_task.id,_seller.shopURL ,_task.dispatchDate,_jsuser.ww,_task.waybill,_task.timeingTime" // index=23
									+ " from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer  where     _fbuser.id=:userId and   _task.type=:platformType "
									+ orderAndWhereReleasedTaskStr(queryType,
											false), new String[] { "userId",
									"platformType" }, new Object[] {
									getLoginUser().getId(), platformType },
							creditTaskVO.getStart(), creditTaskVO.getLimit());
			Long currentDate = System.currentTimeMillis();
			// 设置时间
			for (Object[] objs : result) {
				if (TaskMananger.STEP_FOUR_STATUS.equals(objs[7])) {
					if (!objs[13].equals("1")) {
						Long nowCurrTime = (currentDate - ((Date) objs[20])
								.getTime());
						Integer remainTime = (Integer) objs[12] * 60 * 60 * 1000;
						if (nowCurrTime >= remainTime) {
							objs[12] = 0 + "";
						} else {
							double temp = ((double) (remainTime - nowCurrTime)) / 60 / 60 / 1000;
							if (temp > 1) {
								objs[12] = (remainTime - nowCurrTime) / 60 / 60
										/ 1000 + "";
							} else {
								objs[12] = StringUtils.formatNumber(temp);
							}
						}
					}
				}
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
		BeanUtils.copyProperties(creditTask, creditTaskVO);

		// 把天转换成时间
		if (!creditTaskVO.getGoodTimeType().equals("5")) {
			creditTaskVO.setIntervalHour(StrategyUtils
					.getIntervalHourByGoodType(creditTaskVO.getGoodTimeType()));
		}
		// 算发布点
		double dot = StrategyUtils.generateCreditRDot(creditTaskVO.getMoney(),
				creditTaskVO.getIntervalHour());
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
			return "insertReleaseTaskFail";
		}
		if (StringUtils.isBlank(platFormType)) {
			WinUtils.throwIllegalityException("视图越过发布任务的任务类型验证！");
			return "insertReleaseTaskFail";
		}
		if (creditTask.getMoney() > userEntity.getMoney()) {
			creditTaskVO.setMoney(null);
			putAlertMsg("您当前的余额为不够发布这个任务，点此处进行充值！");
			return "insertReleaseTaskFail";
		}

		if (creditTaskVO.getMoney() < 1) {
			putAlertMsg("不能发布小于1元的任务！");
			return "insertReleaseTaskFail";
		}
		if (dot > userEntity.getReleaseDot()) {
			putAlertMsg("您当前的发布点不够" + dot + "！");
			return "insertReleaseTaskFail";
		}
		// 设置发布人，发布账号
		// Long buyerID = creditTaskVO.getBuyerID();
		Long sellerID = creditTaskVO.getSellerID();
		if (sellerID == null) {
			putAlertMsg("掌柜名和商品地址不对应！");
			return "insertReleaseTaskFail";
		}
		SellerEntity seller = new SellerEntity();
		seller.setId(sellerID);
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
				return "insertReleaseTaskFail";
			}
			creditTask.setStatus(TaskMananger.TIMING_STATUS);
		}
		creditTask.setSeller(seller);
		creditTask.setReleasePerson(userEntity);
		// 生成testID
		creditTask.setTestID(taskMananger.generateTaskID());
		// 生成地址
		creditTask.setAddress(createAddress(creditTaskVO, taskMananger,
				sellerID));
		creditTask.setReleaseDot(dot);
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
			creditTaskRepository.setReleaseDot(dot);
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
				.getMoney()));
		userEntity.setReleaseDot(ArithUtils
				.sub(userEntity.getReleaseDot(), dot));
		// 完成对金钱进行修改,登陆名的也需要
		updateUserLoginInfo(userEntity);
		putDIV("");
		return "insertReleaseTaskSuccess";
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
				putJumpPage("userInfoManager/info!initActiave.php");
				return JUMP;
			}
			List<SellerEntity> sellers = userEntity.getSellers();
			List<SellerVO> resultSellers = new ArrayList<SellerVO>(sellers
					.size());
			if (sellers.size() > 0) {
				for (SellerEntity sellerEntity : sellers) {
					SellerVO sellerVO = new SellerVO();
					BeanUtils.copyProperties(sellerVO, sellerEntity);
					resultSellers.add(sellerVO);
				}
			} else {
				putAlertMsg("您还没有绑定卖号，请先添加！");
				return "noSellerPage";
			}
			List<CreditTaskRepositoryEntity> creditTaskResitorys = creditTaskRepositoryDAO
					.list(
							"from CreditTaskRepositoryEntity _cr where _cr.user.id=:userId and _cr.type=:type",
							new String[] { "userId", "type" }, new Object[] {
									userEntity.getId(), platformType });
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
			return "initReleaseTask";
		}
	}

	/**
	 * 初始化任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String initTask(CreditTaskVO creditTaskVO) throws Exception {
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
								+ " from CreditTaskEntity as _task inner join _task.releasePerson as _user where (_task.status!='0' or _task.status!='-1')  and   _task.type=:platformType"
								+ orderAndWhereInitTaskStr(queryType, true),
						"platformType", platformType);
		List<Object[]> result = creditTaskDAO
				.pageQuery(
						"select _task.testID , _task.releaseDate ,_user.username,_user.upgradeScore,_task.money,_task.updatePrice, "
								+ "_task.goodTimeType,_task.releaseDot,_task.status ,_task.intervalHour,_task.desc,_task.address,_task.id,_task.grade ,_vip.type" // index=14
								+ " from CreditTaskEntity as _task inner join _task.releasePerson as _user  left join _user.vip as _vip  where (_task.status!='0' or _task.status!='-1')  and   _task.type=:platformType "
								+ orderAndWhereInitTaskStr(queryType, false),
						"platformType", platformType, creditTaskVO.getStart(),
						creditTaskVO.getLimit());
		List<BuyerEntity> buyers = userDAO
				.list(
						" from BuyerEntity  as _b where _b.user.id=:userId  and  _b.type=:type and _b.enable=:enable",
						new String[] { "userId", "type", "enable" },
						new Object[] { getLoginUser().getId(), platformType,
								true });
		List<BuyerVO> resultBuyers = new ArrayList<BuyerVO>(buyers.size());
		if (buyers.size() == 0) {
			putJumpPage("userInfoManager/info!initSellerAndBuyer.php");
			putAlertMsg("您的" + WinUtils.changeType2Platform(platformType)
					+ "平台还没有绑定买号！");
			return JUMP;
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
		return "initTask";
	}

	/**
	 * 生成地址
	 */
	private String createAddress(CreditTaskVO creditTaskVO,
			TaskMananger taskMananger, Long sellerID) throws Exception {
		// 生成描述(包含地址)
		StringBuffer address = new StringBuffer();
		if (creditTaskVO.getAddress()) {
			SellerEntity sellerEntity = sellerDAO.get(sellerID);
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
