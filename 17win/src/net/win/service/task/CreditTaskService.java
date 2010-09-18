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
import net.win.dao.UserDAO;
import net.win.entity.BuyerEntity;
import net.win.entity.CreditTaskEntity;
import net.win.entity.CreditTaskRepositoryEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;
import net.win.exception.NoPageException;
import net.win.utils.ArithUtils;
import net.win.utils.StrategyUtils;
import net.win.utils.StringUtils;
import net.win.utils.WinUtils;
import net.win.vo.BuyerVO;
import net.win.vo.CreditTaskRepositoryVO;
import net.win.vo.CreditTaskVO;
import net.win.vo.SellerVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.sun.management.jmx.JMProperties;

import sun.security.krb5.internal.PAEncTSEnc;

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

	/** ***************************买家操作********************************* */
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
		if (StringUtils.isBlank(platformType)) {
			WinUtils.throwIllegalityException(getLoginUser().getUsername()
					+ "试图越过取消重填操作！ ");
		}
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			CreditTaskEntity creditTaskEntity = creditTaskDAO.get(taskId);
			/**
			 * 验证
			 */
			if (!TaskMananger.STEP_TWO_STATUS.equals(creditTaskEntity
					.getStatus())) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过退出任务操作！ ");
			}
			/**
			 * 任务
			 */
			creditTaskEntity.setStatus(TaskMananger.STEP_THREE_STATUS);
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
		if (StringUtils.isBlank(platformType)) {
			WinUtils.throwIllegalityException(getLoginUser().getUsername()
					+ "试图越过取消重填操作！ ");
		}
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			CreditTaskEntity creditTaskEntity = creditTaskDAO.get(taskId);
			UserEntity receiveUser = creditTaskEntity.getReceivePerson();
			if (receiveUser.getId() != loginInfo.getId()) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过退出任务操作！ ");
			}
			/**
			 * 验证 如果不是付款，也不是 审核，那么就不能退出任务了。
			 */
			if (!TaskMananger.STEP_TWO_STATUS.equals(creditTaskEntity
					.getStatus())
					&& !TaskMananger.AUDIT_STATUS.equals(creditTaskEntity
							.getStatus())) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ "试图越过退出任务操作！ ");
			}
			/**
			 * 人
			 */
			receiveUser.setMoney(ArithUtils.add(receiveUser.getMoney(),
					creditTaskEntity.getMoney()));
			receiveUser.setReleaseDot(ArithUtils.add(receiveUser
					.getReleaseDot(), creditTaskEntity.getReleaseDot()));
			/**
			 * 任务
			 */
			creditTaskEntity.setReceivePerson(null);
			creditTaskEntity.setReceiveIP(null);
			creditTaskEntity.setBuyer(null);
			creditTaskEntity.setRemainTime(null);
			creditTaskEntity.setReceiveDate(null);
			creditTaskEntity.setStatus(TaskMananger.STEP_ONE_STATUS);
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
		// 验证状态
		if (!TaskMananger.STEP_ONE_STATUS.equals(creditTask.getStatus())) {
			WinUtils.throwIllegalityException(loginInfo.getUsername()
					+ "试图越过接受任务操作！");
		}
		/**
		 * 判断
		 */
		// 金钱
		if (userEntity.getMoney() < creditTask.getMoney()) {
			putAlertMsg("您当前的金额不够" + creditTask.getMoney() + "元,不能接此任务！");
			putJumpPage("taskManager/task!initTask.php?platformType="
					+ platformType);
			return JUMP;
		}
		// 发布点
		if (userEntity.getReleaseDot() < creditTask.getReleaseDot()) {
			putJumpPage("taskManager/task!initTask.php?platformType="
					+ platformType);
			putAlertMsg("您当前的发布点不够" + creditTask.getMoney() + ",不能接此任务！");
			return JUMP;
		}
		if (buyeId == null) {
			WinUtils.throwIllegalityException(loginInfo.getUsername()
					+ "试图越过接受任务操作！");
		}
		/**
		 * 改变用户属性
		 */
		// 金钱
		userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(), creditTask
				.getMoney()));
		// 发布点
		userEntity.setReleaseDot(ArithUtils.sub(userEntity.getReleaseDot(),
				creditTask.getReleaseDot()));
		/**
		 * 改变任务属性
		 */
		// 接收人
		creditTask.setReceivePerson(userEntity);
		// Ip
		creditTask.setReceiveIP(getIpAddr());
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

		updateUserLoginInfo(userEntity);
		putJumpPage("taskManager/task!initReceivedTast.php?platformType="
				+ platformType);
		putAlertMsg("恭喜您，你已经抢到了此任务！");
		return JUMP;
	}

	/***************************************************************************
	 * 卖家操作
	 */
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
			// 如果发布人不是当前的登陆人就报错
			if (!creditTask.getReleasePerson().getId().equals(
					userEntity.getId())) {
				WinUtils.throwIllegalityException(userEntity.getUsername()
						+ "试图越过取消重填操作！任务ID是：" + creditTask.getTestID());
			}
			/**
			 * 验证
			 */
			if (TaskMananger.STEP_THREE_STATUS.equals(creditTask.getStatus())) {
				putAlertMsg("买家已经付款！");
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
			if (!creditTask.getReleasePerson().getId().equals(
					userEntity.getId())) {
				WinUtils.throwIllegalityException(userEntity.getUsername()
						+ "试图越过取消重填操作！任务ID是：" + creditTask.getTestID());
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
		if (StringUtils.isBlank(platformType)) {
			WinUtils.throwIllegalityException(getLoginUser().getUsername()
					+ "试图越过取消重填操作！ ");
		}
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskId = Long.parseLong(getByParam("taskId"));
			UserEntity userEntity = userDAO.get(getLoginUser().getId());
			CreditTaskEntity creditTask = creditTaskDAO.get(taskId);
			// 如果发布人不是当前的登陆人就报错
			if (creditTask == null
					|| !creditTask.getReleasePerson().getId().equals(
							userEntity.getId())) {
				WinUtils.throwIllegalityException(userEntity.getUsername()
						+ "试图越过取消重填操作！ ");
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
			 * 把状态为审核(-2) ，买家付款(2)的取出，设置它们的剩余时间 ，查看它们的剩余时间
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
				// if (minuties > creditTaskEntity.getRemainTime()) {
				// // 如果大于当前的剩余时间 就移出任务到发布任务区。
				// UserEntity receiveUser = creditTaskEntity
				// .getReceivePerson();
				// /**
				// * 人
				// */
				// receiveUser.setMoney(ArithUtils.add(receiveUser.getMoney(),
				// creditTaskEntity.getMoney()));
				// receiveUser
				// .setReleaseDot(ArithUtils.add(receiveUser
				// .getReleaseDot(), creditTaskEntity
				// .getReleaseDot()));
				// /**
				// * 任务
				// */
				// creditTaskEntity.setReceivePerson(null);
				// creditTaskEntity.setReceiveIP(null);
				// creditTaskEntity.setBuyer(null);
				// creditTaskEntity.setRemainTime(null);
				// creditTaskEntity.setNowOpertationDate(null);
				// creditTaskEntity.setStatus(TaskMananger.STEP_ONE_STATUS);
				// } else {
				creditTaskEntity.setRemainTime(creditTaskEntity.getRemainTime()
						- minuties);
			}
			creditTaskDAO.flushSession();
			// creditTaskDAO.clearSession();
			// 分页查询
			Long count = (Long) creditTaskDAO
					.uniqueResultObject(
							"select count(*) from CreditTaskEntity as _task inner join _task.receivePerson as _user   where     _user.id=:userId and   _task.type=:platformType ",
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(), platformType });
			List<Object[]> result = creditTaskDAO
					.pageQuery(
							"select _task.testID , _task.releaseDate ,_fbuser.username,_fbuser.qq,_task.money,_task.updatePrice ,_task.releaseDot "// 6
									+ ", _task.itemUrl , _seller.name,_seller.shopURL,_buyer.name,_jsuser.upgradeScore,_task.status" // 12
									+ ", _task.remainTime,_task.goodTimeType ,_task.intervalHour,_task.desc,_task.address ,_task.grade,_task.id " // index=19
									+ " from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer  where     _jsuser.id=:userId and   _task.type=:platformType order by _task.releaseDate desc",
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(), platformType },
							creditTaskVO.getStart(), creditTaskVO.getLimit());
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
			 * 把状态为审核(-2) ，买家付款(2)的取出，设置它们的剩余时间 ，查看它们的剩余时间
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
				// if (minuties > creditTaskEntity.getRemainTime()) {
				// // 如果大于当前的剩余时间 就移出任务到发布任务区。
				// UserEntity receiveUser = creditTaskEntity
				// .getReceivePerson();
				// /**
				// * 人
				// */
				// receiveUser.setMoney(ArithUtils.add(receiveUser.getMoney(),
				// creditTaskEntity.getMoney()));
				// receiveUser
				// .setReleaseDot(ArithUtils.add(receiveUser
				// .getReleaseDot(), creditTaskEntity
				// .getReleaseDot()));
				// /**
				// * 任务
				// */
				// creditTaskEntity.setReceivePerson(null);
				// creditTaskEntity.setReceiveIP(null);
				// creditTaskEntity.setBuyer(null);
				// creditTaskEntity.setRemainTime(null);
				// creditTaskEntity.setNowOpertationDate(null);
				// creditTaskEntity.setStatus(TaskMananger.STEP_ONE_STATUS);
				// } else {
				creditTaskEntity.setRemainTime(creditTaskEntity.getRemainTime()
						- minuties);
			}
			creditTaskDAO.flushSession();
			// creditTaskDAO.clearSession();
			// 分页查询
			Long count = (Long) creditTaskDAO
					.uniqueResultObject(
							"select count(*) from CreditTaskEntity as _task inner join _task.releasePerson as _user   where     _user.id=:userId and   _task.type=:platformType ",
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(), platformType });
			List<Object[]> result = creditTaskDAO
					.pageQuery(
							"select _task.testID , _task.releaseDate ,_task.money,_task.updatePrice ,_task.releaseDot, _task.itemUrl , _seller.name,_task.status "// 7
									+ ", _jsuser.username,_buyer.name,_jsuser.qq,_jsuser.upgradeScore" // 11
									+ ", _task.remainTime,_task.goodTimeType ,_task.intervalHour,_task.desc,_task.address ,_task.grade,_task.id,_seller.shopURL " // index=19
									+ " from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer  where     _fbuser.id=:userId and   _task.type=:platformType order by _task.releaseDate desc",
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(), platformType },
							creditTaskVO.getStart(), creditTaskVO.getLimit());
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
		CreditTaskEntity creditTaskEntity = new CreditTaskEntity();
		TaskMananger taskMananger = TaskMananger.getInstance();

		String platFormType = getPlatformType();
		BeanUtils.copyProperties(creditTaskEntity, creditTaskVO);

		// 把天转换成时间
		if (!creditTaskVO.getGoodTimeType().equals("5")) {
			creditTaskVO.setIntervalHour(StrategyUtils
					.getIntervalHourByGoodType(creditTaskVO.getGoodTimeType()));
		}
		// 算发布点
		double dot = StrategyUtils.generateCreditRDot(creditTaskVO.getMoney(),
				creditTaskVO.getIntervalHour());
		// 验证
		if (StringUtils.isBlank(platFormType)) {
			WinUtils.throwIllegalityException("视图越过发布任务的任务类型验证！");
			return "insertReleaseTaskFail";
		}
		if (creditTaskEntity.getMoney() > userEntity.getMoney()) {
			creditTaskVO.setMoney(null);
			putAlertMsg("您当前的余额为不够发布这个任务，点此处进行充值！");
			return "insertReleaseTaskFail";
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
		SellerEntity seller = new SellerEntity();
		seller.setId(sellerID);
		/**
		 * 保存任务
		 */
		// 是否是定时任务
		if (creditTaskEntity.getTimeingTime() == null) {
			creditTaskEntity.setStatus(TaskMananger.STEP_ONE_STATUS);
		} else {
			taskMananger.addTimingTask(creditTaskEntity.getId());
			creditTaskEntity.setStatus(TaskMananger.TIMING_STATUS);
		}
		creditTaskEntity.setSeller(seller);
		creditTaskEntity.setReleasePerson(userEntity);
		// 生成testID
		creditTaskEntity.setTestID(taskMananger.generateTaskID());
		// 生成地址
		creditTaskEntity.setAddress(createAddress(creditTaskVO, taskMananger,
				sellerID));
		creditTaskEntity.setReleaseDot((float) dot);
		creditTaskEntity.setReleaseDate(new Date());
		creditTaskEntity.setReleasePerson(userEntity);
		creditTaskEntity.setType(platFormType);
		creditTaskDAO.save(creditTaskEntity);
		/**
		 * 保存任务仓库
		 */
		// 任务仓库
		if (creditTaskVO.getRepository()) {
			CreditTaskRepositoryEntity creditTaskRepository = new CreditTaskRepositoryEntity();
			BeanUtils.copyProperties(creditTaskRepository, creditTaskVO);
			creditTaskRepository.setUser(userEntity);
			creditTaskRepository.setType(platFormType);
			creditTaskRepository
					.setGoodTimeType(creditTaskVO.getGoodTimeType());
			if (StringUtils.isBlank(creditTaskVO.getRespositoryName())) {
				creditTaskRepository.setName(creditTaskEntity.getTestID());
			} else {
				creditTaskRepository.setName(creditTaskVO.getRespositoryName());

			}
			creditTaskRepositoryDAO.save(creditTaskRepository);
		}
		/**
		 * 改变用户金钱和发布点
		 */
		userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(),
				creditTaskEntity.getMoney()));
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
				return "initReleaseTaskFail";
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
								+ " from CreditTaskEntity as _task inner join _task.releasePerson as _user where (_task.status!='0' or _task.status!='-1')  and   _task.type=:platformType",
						"platformType", platformType);
		List<Object[]> result = creditTaskDAO
				.pageQuery(
						"select _task.testID , _task.releaseDate ,_user.username,_user.upgradeScore,_task.money,_task.updatePrice, "
								+ "_task.goodTimeType,_task.releaseDot,_task.status ,_task.intervalHour,_task.desc,_task.address,_task.id,_task.grade" // index=13
								+ " from CreditTaskEntity as _task inner join _task.releasePerson as _user where (_task.status!='0' or _task.status!='-1')  and   _task.type=:platformType order by   _task.releaseDate desc",
						"platformType", platformType, creditTaskVO.getStart(),
						creditTaskVO.getLimit());
		List<BuyerEntity> buyers = userDAO
				.list(
						" from BuyerEntity  as _b where _b.user.id=:userId  and  _b.type=:type",
						new String[] { "userId", "type" }, new Object[] {
								getLoginUser().getId(), platformType });
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
		creditTaskVO.setDataCount(count.intValue());
		putByRequest("result", result);
		putByRequest("resultBuyers", resultBuyers);
		putPlatformByRequest(WinUtils.changeType2Platform(platformType));
		putPlatformTypeByRequest(platformType);
		return "initTask";
	}

	/**
	 * 生成desc
	 */
	private String createAddress(CreditTaskVO creditTaskVO,
			TaskMananger taskMananger, Long sellerID) throws Exception {
		// 生成描述(包含地址)
		StringBuffer address = new StringBuffer();
		if (creditTaskVO.getAddress()) {
			Object[] addresses = (Object[]) sellerDAO
					.uniqueResultObject(
							"select _p.name,_c.name from SellerEntity as _s inner join _s.province _p inner join _s.city as _c where _s.id=:id",
							"id", sellerID);
			if (addresses != null) {
				for (Object str : addresses) {
					address.append(str + " ");
				}
				address.append(taskMananger.randomObtainAddress(userDAO));
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
