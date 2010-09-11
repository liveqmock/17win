package net.win.service.task;

import java.util.ArrayList;
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
import net.win.entity.CreditTaskEntity;
import net.win.entity.CreditTaskRepositoryEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;
import net.win.utils.ArithUtils;
import net.win.utils.StrategyUtils;
import net.win.utils.StringUtils;
import net.win.utils.WinUtils;
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
		String ip = getRequset().getRemoteAddr();
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
		// 放IP
		creditTaskEntity.setReceiveIP(ip);
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
			creditTaskRepository.setName(creditTaskVO.getRespositoryName());
			creditTaskRepository.setSellerID(sellerID);
			creditTaskRepositoryDAO.save(creditTaskRepository);
		}
		// 是否是定时任务
		if (creditTaskEntity.getTimeingTime() == null) {
			creditTaskEntity.setStatus("4");
		} else {
			taskMananger.addTimingTask(creditTaskEntity.getId());
			creditTaskEntity.setStatus("0");
		}
		// 保存
		creditTaskEntity.setStartDate(new Date());
		creditTaskEntity.setReleasePerson(userEntity);
		creditTaskEntity.setType(platFormType);
		creditTaskDAO.save(creditTaskEntity);
		// 完成对金钱进行修改,登陆名的也需要
		updateUserLoginInfo(userEntity);
		return "insertReleaseTaskSuccess";
	}

	/**
	 * 生成desc
	 */
	private void createDesc(CreditTaskVO creditTaskVO,
			CreditTaskEntity creditTaskEntity, TaskMananger taskMananger,
			Long sellerID) throws Exception {
		// 生成描述(包含地址)
		StringBuffer address = new StringBuffer();
		address.append("地址：");
		StringBuffer desc = new StringBuffer();
		desc.append("描述：");
		if (creditTaskVO.getAddress()) {
			List<Object[]> addresses = (List<Object[]>) sellerDAO
					.uniqueResult(
							"select _p.name,_c.name from SellerEntity as _s left join _s.province _p left join _s.city as _c where _s.id=:id",
							"id", sellerID);
			if (addresses.size() > 0) {
				for (Object str : addresses) {
					address.append(str + " ");
				}
				address.append(taskMananger.randomObtainAddress(userDAO));
			}
		}
		desc.append(creditTaskVO.getDesc());

		creditTaskEntity
				.setDesc(address.toString() + "<br/>" + desc.toString());

	}

	/**
	 * 初始化发布任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String initReleaseTask(CreditTaskVO creditTaskVO) throws Exception {
		// 没有操作码验证就验证
		HttpServletRequest request = getRequset();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			UserEntity userEntity = getLoginUserEntity(userDAO);
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
			String platformType = getPlatformType();
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			putByRequest("sellers", resultSellers);
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
		putPlatformByRequest(WinUtils.changeType2Platform(platformType));
		putPlatformTypeByRequest(platformType);
		return "initTask";
	}
}
