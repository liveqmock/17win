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
import net.win.vo.CreditTaskRepositoryVO;
import net.win.vo.CreditTaskVO;
import net.win.vo.SellerVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service("creditTaskRepositoryService")
public class CreditTaskRepositoryService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private CreditTaskRepositoryDAO creditTaskRepositoryDAO;
	@Resource
	private CreditTaskDAO creditTaskDAO;
	@Resource
	private SellerDAO sellerDAO;

	/**
	 * 发布任务
	 * 
	 * @param creditTaskRepositoryVO
	 * @return
	 * @throws Exception
	 */
	public String insertRepository(CreditTaskRepositoryVO creditTaskRepositoryVO)
			throws Exception {
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskReId = Long.parseLong(getByParam("taskReId"));
			CreditTaskRepositoryEntity creditTaskRepositoryEntity = creditTaskRepositoryDAO
					.get(taskReId);
			UserEntity user = creditTaskRepositoryEntity.getUser();

			SellerEntity seller = sellerDAO.get(creditTaskRepositoryEntity
					.getSellerID());

			if (!user.getId().equals(getLoginUser().getId())) {
				WinUtils.throwIllegalityException(getLoginUser().getUsername()
						+ " 试图越过【任务仓库的发布任务】操作.");
			}
			// 验证 金钱
			if (creditTaskRepositoryEntity.getMoney()
					+ creditTaskRepositoryEntity.getAddtionMoney() > user
					.getMoney()) {
				putJumpPage("taskRepositoryManager/taskRepository!queryRepositories.php?platformType="
						+ platformType);
				putAlertMsg("您当前的余额不够发布此任务！");
				return JUMP;
			}
			if (creditTaskRepositoryEntity.getReleaseDot()
					+ creditTaskRepositoryEntity.getAddtionReleaseDot() > user
					.getReleaseDot()) {
				putJumpPage("taskRepositoryManager/taskRepository!queryRepositories.php?platformType="
						+ platformType);
				putAlertMsg("您当前的发布点不够发布此任务！");
				return JUMP;
			}

			// 发布任务
			CreditTaskEntity creditTaskEntity = new CreditTaskEntity();
			creditTaskEntity.setReleaseDate(new Date());
			creditTaskEntity.setReleaseDot(creditTaskRepositoryEntity
					.getReleaseDot());
			creditTaskEntity.setMoney(creditTaskRepositoryEntity.getMoney());
			creditTaskEntity.setGoodTimeType(creditTaskRepositoryEntity
					.getGoodTimeType());

			creditTaskEntity
					.setProtect(creditTaskRepositoryEntity.getProtect());
			// 状态 到时间
			if (!creditTaskEntity.getGoodTimeType().equals("5")) {
				creditTaskEntity.setIntervalHour(StrategyUtils
						.getIntervalHourByGoodType(creditTaskEntity
								.getGoodTimeType()));
			} else {
				creditTaskEntity.setIntervalHour(creditTaskRepositoryEntity
						.getIntervalHour());
			}
			// 有地址
			if (creditTaskRepositoryEntity.getAddress()) {
				creditTaskEntity.setAddress(seller.getAddress() + " "
						+ StrategyUtils.makeAddress());
			} else {
				creditTaskEntity.setAddress("无");
			}
			creditTaskEntity.setTestID(TaskMananger.getInstance()
					.generateTaskID());
			creditTaskEntity.setStatus("1");
			creditTaskEntity.setUpdatePrice(creditTaskRepositoryEntity
					.getUpdatePrice());
			creditTaskEntity
					.setItemUrl(creditTaskRepositoryEntity.getItemUrl());
			creditTaskEntity.setType(creditTaskRepositoryEntity.getType());
			creditTaskEntity.setSeller(seller);
			creditTaskEntity.setReleasePerson(user);
			creditTaskEntity.setGrade(creditTaskRepositoryEntity.getGrade());
			creditTaskEntity.setDesc(creditTaskRepositoryEntity.getDesc());
			creditTaskEntity.setWaybill(StrategyUtils.makeWaybill());
			creditTaskDAO.save(creditTaskEntity);
			// 改变人
			user.setMoney(ArithUtils.sub(user.getMoney(),
					creditTaskRepositoryEntity.getMoney()));
			user.setReleaseDot(ArithUtils.sub(user.getReleaseDot(),
					creditTaskRepositoryEntity.getReleaseDot()));

			// 仓库信息
			creditTaskRepositoryEntity
					.setDispathCount(creditTaskRepositoryEntity
							.getDispathCount() + 1);
			creditTaskRepositoryEntity.setLastDispathDate(new Date());
			updateUserLoginInfo(user);
			putJumpPage("taskRepositoryManager/taskRepository!queryRepositories.php?platformType="
					+ platformType);
			putAlertMsg("发布成功！");
			return JUMP;
		}
	}

	/**
	 * 删除
	 * 
	 * @param creditTaskRepositoryVO
	 * @return
	 * @throws Exception
	 */
	public String deleteRepository(CreditTaskRepositoryVO creditTaskRepositoryVO)
			throws Exception {
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskReId = Long.parseLong(getByParam("taskReId"));
			int count = creditTaskRepositoryDAO
					.deleteByHql(
							"delete from  CreditTaskRepositoryEntity as _taskRe  where _taskRe.id=:taskReId and _taskRe.user.id=:userId",
							new String[] { "taskReId", "userId" },
							new Object[] { taskReId, getLoginUser().getId() });
			if (count > 0) {
				putAlertMsg("删除成功！");
			} else {
				putAlertMsg("已经被删除,不要重复提交！");
			}
			putJumpPage("taskRepositoryManager/taskRepository!queryRepositories.php?platformType="
					+ platformType);
			return JUMP;
		}
	}

	/**
	 * 获取到任务信息
	 * 
	 * @param creditTaskRepositoryVO
	 * @return
	 * @throws Exception
	 */
	public String obtainTaskInfo(CreditTaskRepositoryVO creditTaskRepositoryVO)
			throws Exception {
		CreditTaskRepositoryEntity creditTaskRepositoryEntity = creditTaskRepositoryDAO
				.get(creditTaskRepositoryVO.getId());
		BeanUtils.copyProperties(creditTaskRepositoryVO,
				creditTaskRepositoryEntity);
		return JSON;
	}

	/**
	 * 查询
	 * 
	 * @param creditTaskRepositoryVO
	 * @return
	 * @throws Exception
	 */
	public String queryRepositories(
			CreditTaskRepositoryVO creditTaskRepositoryVO) throws Exception {
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			putTaskShowType("6");
			String hqlCount = "select count(*) from CreditTaskRepositoryEntity as _taskRe ,SellerEntity as _seller"
					+ " where _seller.id=_taskRe.sellerID and  _taskRe.type=:platformType and _taskRe.user.id=:userId";
			Long count = (Long) creditTaskRepositoryDAO.uniqueResultObject(
					hqlCount, new String[] { "platformType", "userId" },
					new Object[] { platformType, getLoginUser().getId() });
			if (count > 0) {
				String hql = "select _taskRe ,_seller.name from CreditTaskRepositoryEntity as _taskRe ,SellerEntity as _seller"
						+ " where _seller.id=_taskRe.sellerID and  _taskRe.type=:platformType and _taskRe.user.id=:userId";
				List<Object[]> resultTemp = creditTaskRepositoryDAO.list(hql,
						new String[] { "platformType", "userId" },
						new Object[] { platformType, getLoginUser().getId() });
				List<CreditTaskRepositoryVO> result = new ArrayList<CreditTaskRepositoryVO>(
						resultTemp.size());
				for (Object[] objects : resultTemp) {
					CreditTaskRepositoryEntity creditTaskRepositoryEntity = (CreditTaskRepositoryEntity) objects[0];
					CreditTaskRepositoryVO cTemp = new CreditTaskRepositoryVO();
					BeanUtils.copyProperties(cTemp, creditTaskRepositoryEntity);
					cTemp.setSellerName((String) objects[1]);
					result.add(cTemp);
				}
				creditTaskRepositoryVO.setDataCount(count.intValue());
				putByRequest("result", result);
			}
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			return "queryRepositories";
		}
	}
}
