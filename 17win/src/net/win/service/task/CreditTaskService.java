package net.win.service.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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

	/**
	 * 接收任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String updateReceiveTask(CreditTaskVO creditTaskVO) throws Exception {
		CreditTaskEntity taskEntity = creditTaskDAO.get(creditTaskVO.getId());
		taskEntity.setRemainTime(20);
		String ip = getRequset().getRemoteAddr();
		if (taskEntity.getStatus().equals(TaskMananger.STEP_SIX_STATUS)) {
			WinUtils.throwIllegalityException("视图进入修改状态！");
		}
		// 等待接手
		if (taskEntity.getStatus().equals(TaskMananger.STEP_ONE_STATUS)) {
			if (taskEntity.getProtect()) {
				// 保护，就设置为 保护状态
				taskEntity.setStatus(TaskMananger.AUDIT_STATUS);
			} else {
				taskEntity.setStatus(TaskMananger.STEP_TWO_STATUS);
			}
		}
		BuyerEntity buyerEntity = new BuyerEntity();
		buyerEntity.setId(creditTaskVO.getId());
		taskEntity.setBuyer(buyerEntity);
		taskEntity.setReceiveIP(ip);
		return "updateTask";
	}

	/**
	 * 初始化已接任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String initReceivedTast(CreditTaskVO creditTaskVO) throws Exception {
		CreditTaskEntity taskEntity = creditTaskDAO.get(creditTaskVO.getId());
		// 没有操作码验证就验证
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long count = (Long) creditTaskDAO
					.uniqueResultObject(
							"select count(*) from CreditTaskEntity as _task inner join _task.releasePerson as _user  inert join _task.seller as _seller where     _user.id=:userId and   _task.type=:platformType ",
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(), platformType });
			List<Object[]> result = creditTaskDAO
					.pageQuery(
							"select _task.testID , _task.releaseDate ,_task.money,_task.updatePrice ,_task.releaseDot, _seller.shopURL , _seller.name,_task.status "// 7
									+ " _jsuser.username,_buyer.name,_jsuser.qq,_jsuser.upgradeScore" // 11
									+ " _tasl.remainTime,_task.goodTimeType ,_task.intervalHour,_task.desc,_task.address" // index=17
									+ " from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inert join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer  where     _fbuser.id=:userId and   _task.type=:platformType ",
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(), platformType },
							creditTaskVO.getStart(), creditTaskVO.getLimit());
			creditTaskVO.setDataCount(count.intValue());
			return "updateTask";
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
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long count = (Long) creditTaskDAO
					.uniqueResultObject(
							"select count(*) from CreditTaskEntity as _task inner join _task.releasePerson as _user   where     _user.id=:userId and   _task.type=:platformType ",
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(), platformType });
			List<Object[]> result = creditTaskDAO
					.pageQuery(
							"select _task.testID , _task.releaseDate ,_task.money,_task.updatePrice ,_task.releaseDot, _task.itemUrl , _seller.name,_task.status "// 7
									+ ", _jsuser.username,_buyer.name,_jsuser.qq,_jsuser.upgradeScore" // 11
									+ ", _task.remainTime,_task.goodTimeType ,_task.intervalHour,_task.desc,_task.address ,_task.grade " // index=17
									+ " from CreditTaskEntity as _task inner join _task.releasePerson as _fbuser  inner join _task.seller as _seller left join _task.receivePerson as _jsuser left join _task.buyer as _buyer  where     _fbuser.id=:userId and   _task.type=:platformType ",
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
		// 改变余额
		userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(),
				creditTaskEntity.getMoney()));
		// 设置发布人，发布账号
		// Long buyerID = creditTaskVO.getBuyerID();
		Long sellerID = creditTaskVO.getSellerID();
		SellerEntity seller = new SellerEntity();
		seller.setId(sellerID);
		creditTaskEntity.setSeller(seller);
		creditTaskEntity.setReleasePerson(userEntity);

		// 生成testID
		creditTaskEntity.setTestID(taskMananger.generateTaskID());
		// 生成描述
		createDesc(creditTaskVO, creditTaskEntity, taskMananger, sellerID);
		userEntity.setReleaseDot(ArithUtils
				.sub(userEntity.getReleaseDot(), dot));
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
		// 是否是定时任务
		if (creditTaskEntity.getTimeingTime() == null) {
			creditTaskEntity.setStatus(TaskMananger.STEP_ONE_STATUS);
		} else {
			taskMananger.addTimingTask(creditTaskEntity.getId());
			creditTaskEntity.setStatus(TaskMananger.TIMING_STATUS);
		}
		// 保存
		creditTaskEntity.setRemainTime(20);
		creditTaskEntity.setReleaseDate(new Date());
		creditTaskEntity.setReleasePerson(userEntity);
		creditTaskEntity.setType(platFormType);
		creditTaskDAO.save(creditTaskEntity);
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
		if (platformType == null) {
			platformType = getPlatformType();
		}
		if (platformType == null) {
			throw new NoPageException("初始化任务，没有platformType");
		}
		Long count = (Long) creditTaskDAO
				.uniqueResultObject(
						"select count(*)"
								+ " from CreditTaskEntity as _task inner join _task.releasePerson as _user where (_task.status!='0' or _task.status!='-1')  and   _task.type=:platformType",
						"platformType", platformType);
		List<Object[]> result = creditTaskDAO
				.pageQuery(
						"select _task.testID , _task.releaseDate ,_user.username,_user.upgradeScore,_task.money,_task.updatePrice, "
								+ "_task.goodTimeType,_task.releaseDot,_task.status ,_task.intervalHour,_task.desc,_task.address" // index=11
								+ " from CreditTaskEntity as _task inner join _task.releasePerson as _user where (_task.status!='0' or _task.status!='-1')  and   _task.type=:platformType",
						"platformType", platformType, creditTaskVO.getStart(),
						creditTaskVO.getLimit());
		List<BuyerEntity> buyers = userDAO.list(
				" from BuyerEntity  as _b where _b.user.id=:userId", "userId",
				getLoginUser().getId());
		List<BuyerVO> resultBuyers = new ArrayList<BuyerVO>(buyers.size());
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
	private void createDesc(CreditTaskVO creditTaskVO,
			CreditTaskEntity creditTaskEntity, TaskMananger taskMananger,
			Long sellerID) throws Exception {
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
			creditTaskEntity.setAddress(address.toString());
		}
	}
}
