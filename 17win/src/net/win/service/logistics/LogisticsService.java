package net.win.service.logistics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.LogisticsDAO;
import net.win.dao.UserDAO;
import net.win.entity.LogisticsEntity;
import net.win.entity.UserEntity;
import net.win.utils.ArithUtils;
import net.win.utils.Constant;
import net.win.utils.DateUtils;
import net.win.utils.StringUtils;
import net.win.vo.LogisticsVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unused", "unchecked" })
@Service("logisticsService")
public class LogisticsService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private LogisticsDAO logisticsDAO;
	@Resource
	private LogisticsService logisticsService;

	public String initLogistics(LogisticsVO logisticsVO) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DAY_OF_YEAR, -1);
		String minDate = DateUtils.format(calendar.getTime(),
				DateUtils.DATE_FORMAT);
		calendar.add(calendar.DAY_OF_YEAR, 2);
		String maxDate1 = DateUtils.format(calendar.getTime(),
				DateUtils.DATE_FORMAT);
		calendar.add(calendar.DAY_OF_YEAR, -1);
		calendar.add(calendar.DAY_OF_YEAR, 15);
		String maxDate2 = DateUtils.format(calendar.getTime(),
				DateUtils.DATE_FORMAT);
		putByRequest("minDate", minDate);
		putByRequest("maxDate1", maxDate1);
		putByRequest("maxDate2", maxDate2);
		putTokenBySession();

		return "initLogistics";
	}

	/**
	 * 添加物流
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String insertLogistics(LogisticsVO logisticsVO) throws Exception {
		putJumpSelfPage("logisticsManager/logistics!initLogistics.php");
		LogisticsEntity logisticsEntity = logisticsVO.getLogistics();
		Long count = (Long) logisticsDAO.uniqueResultObject(
				"select count(*) from LogisticsEntity where waybill=:waybill ",
				new String[] { "waybill" }, new Object[] { logisticsEntity
						.getWaybill() });
		if (count > 0) {
			putAlertMsg("订单号已经存在！");
			return JUMP;
		}
		UserEntity userEntity = getLoginUserEntity(userDAO);
		logisticsEntity.setReleaseUser(userEntity);
		logisticsDAO.save(logisticsEntity);
		putAlertMsg("添加成功！");
		return JUMP;
	}

	/**
	 * 删除
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String deleteLogistics(LogisticsVO logisticsVO) throws Exception {
		putJumpSelfPage("logisticsManager/logistics!logisticsLog.php");
		String logisticsID = getByParam("logisticsID");
		if (StringUtils.isBlank(logisticsID)) {
			putAlertMsg("请选择物流数据");
			return JUMP;
		}
		Long logisticsIDLong = Long.parseLong(logisticsID);
		LogisticsEntity logisticsEntity = logisticsDAO.get(logisticsIDLong);
		if (!logisticsEntity.getReleaseUser().getId().equals(
				getLoginUser().getId())) {
			putAlertMsg("请选择物流数据");
			return JUMP;
		}
		if (logisticsEntity.getUseCount() > 0) {
			putAlertMsg("该物流已经被人使用不能删除！");
			return JUMP;
		} else {
			logisticsDAO.delete(logisticsEntity);
			putAlertMsg("删除成功!");
			return JUMP;
		}
	}

	/**
	 * 使用
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateUseLogistics(LogisticsVO logisticsVO) throws Exception {
		putJumpOutterPage("logisticsManager/logistics!queryLogisticsLog.php");
		String logisticsID = getByParam("logisticsID");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		if (StringUtils.isBlank(logisticsID)) {
			putAlertMsg("请选择物流数据");
			return JUMP;
		}
		Long logisticsIDLong = Long.parseLong(logisticsID);
		Long count = (Long) logisticsDAO
				.uniqueResultObject(
						"select count(*) from LogisticsEntity as _l inner join _l.receieveUsers as _ru where _ru.id=:userID and _l.id=:logistisID",
						new String[] { "userID", "logistisID" }, new Object[] {
								getLoginUser().getId(), logisticsIDLong });
		if (count > 0) {
			putAlertMsg("您已经使用过该物流信息，去您的【个人中心】→【我使用的物流】可以查找到，本次操作不会扣除您的发布点！");
			return JUMP;
		} else {
			LogisticsEntity logisticsEntity = logisticsDAO.get(logisticsIDLong);
			if (userEntity.getStatus().equals("0")) {
				putAlertMsg("您的账号还没激活！");
				return JUMP;
			}
			if (userEntity.getMoney() < logisticsEntity.getMoney()) {
				putAlertMsg("您当前的金额不够" + logisticsEntity.getMoney() + "，不能使用！");
				return JUMP;
			}

			if (userEntity.getUsername().equalsIgnoreCase(
					logisticsEntity.getReleaseUser().getUsername())) {
				putAlertMsg("不能使用您自己的！");
				return JUMP;
			}
			logisticsEntity.addReceieveUser(userEntity);
			logisticsEntity.setUseCount(logisticsEntity.getUseCount() + 1);
			userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(),
					logisticsEntity.getMoney()));
			logisticsEntity.getReleaseUser().setMoney(
					ArithUtils.add(logisticsEntity.getReleaseUser()
							.getMoney(), logisticsEntity.getMoney()));

			updateUserLoginInfo(userEntity);
			logMoneyCapital(userDAO, 0 - logisticsEntity.getMoney(), "获取物流（快递单号"
					+ logisticsEntity.getWaybill() + "）扣除金额", userEntity);
			logMoneyCapital(userDAO, logisticsEntity.getMoney(), logisticsEntity.getWaybill()
					+ "物流被别人使用得到金额",
					logisticsEntity.getReleaseUser());
			putJumpOutterPage("logisticsManager/logistics!useLogisticsLog.php");
			putAlertMsg("使用成功！");
			return JUMP;
		}
	}

	/**
	 * 物流信息
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String queryLogisticsLog(LogisticsVO logisticsVO) throws Exception {
		putIndexShowType("12");
		StringBuffer resultHQL = new StringBuffer(
				"select  _l ,_u.username from LogisticsEntity _l inner join _l.releaseUser as _u  where 1=1  and  _l.status='1'  ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)  from LogisticsEntity _l inner join _l.releaseUser as _u  where 1=1  and  _l.status='1' ");
		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();

		// MONEY
		if (logisticsVO.getStartMoney() != null
				&& logisticsVO.getEndMoney() != null) {
			resultHQL
					.append(" and (_l.money>=:startMoney and _l.money<=:endMoney) ");
			countHQL
					.append(" and (_l.money>=:startMoney and _l.money<=:endMoney) ");
			paramNames.add("startMoney");
			paramNames.add("endMoney");
			paramValues.add(logisticsVO.getStartMoney());
			paramValues.add(logisticsVO.getEndMoney());
		} else if (logisticsVO.getStartMoney() != null) {
			resultHQL.append(" and _l.money>=:startMoney  ");
			countHQL.append(" and  _l.money>=:startMoney   ");
			paramNames.add("startMoney");
			paramValues.add(logisticsVO.getStartMoney());
		} else if (logisticsVO.getEndMoney() != null) {
			resultHQL.append(" and   _l.money<=:endMoney  ");
			countHQL.append(" and   _l.money<=:endMoney  ");
			paramNames.add("endMoney");
			paramValues.add(logisticsVO.getEndMoney());
		}
		// 时间
		if (logisticsVO.getStartDate() != null
				&& logisticsVO.getEndDate() != null) {
			resultHQL
					.append(" and (_l.sendDate>=:startDate and _l.sendDate<=:endDate) ");
			countHQL
					.append(" and (_l.sendDate>=:startDate and _l.sendDate<=:endDate) ");
			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(logisticsVO.getStartDate());
			paramValues.add(logisticsVO.getEndDate());
		} else if (logisticsVO.getStartDate() != null) {
			resultHQL.append(" and _l.sendDate>=:startDate  ");
			countHQL.append(" and  _l.sendDate>=:startDate   ");
			paramNames.add("startDate");
			paramValues.add(logisticsVO.getStartDate());
		} else if (logisticsVO.getEndDate() != null) {
			resultHQL.append(" and   _l.sendDate<=:endDate  ");
			countHQL.append(" and   _l.sendDate<=:endDate  ");
			paramNames.add("endDate");
			paramValues.add(logisticsVO.getEndDate());
		}
		// 发送者
		if (!StringUtils.isBlank(logisticsVO.getReleaseUsername())) {
			resultHQL.append(" and _u.username like :username");
			countHQL.append(" and _u.username like :username");
			paramNames.add("username");
			paramValues.add("%" + logisticsVO.getReleaseUsername() + "%");
		}
		// 单号
		if (!StringUtils.isBlank(logisticsVO.getWaybill())) {
			resultHQL.append(" and _l.waybill like :waybill");
			countHQL.append(" and _l.waybill like :waybill");
			paramNames.add("waybill");
			paramValues.add("%" + logisticsVO.getWaybill() + "%");
		}
		// 发货信息
		if (!StringUtils.isBlank(logisticsVO.getReleaseInfo())) {
			resultHQL.append(" and _l.releaseInfo like :releaseInfo");
			countHQL.append(" and _l.releaseInfo like :releaseInfo");
			paramNames.add("releaseInfo");
			paramValues.add("%" + logisticsVO.getReleaseInfo() + "%");
		}
		// 收货信息
		if (!StringUtils.isBlank(logisticsVO.getReceieveInfo())) {
			resultHQL.append(" and _l.receieveInfo like :receieveInfo");
			countHQL.append(" and _l.receieveInfo like :receieveInfo");
			paramNames.add("receieveInfo");
			paramValues.add("%" + logisticsVO.getReceieveInfo() + "%");
		}
		resultHQL.append(" order by _l.sendDate  desc ");
		Long count = (Long) logisticsDAO.uniqueResultObject(
				countHQL.toString(), paramNames.toArray(paramNames
						.toArray(new String[paramNames.size()])), paramValues
						.toArray(new Object[paramValues.size()]));
		List<LogisticsVO> result = new ArrayList<LogisticsVO>();
		if (count > 0) {
			logisticsVO.setDataCount(count.intValue());
			List<Object[]> resultTemp = logisticsDAO.pageQuery(resultHQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), logisticsVO
					.getStart(), logisticsVO.getLimit());

			LogisticsVO logisticsVOTEMP = null;
			for (Object[] objs : resultTemp) {
				logisticsVOTEMP = new LogisticsVO();
				BeanUtils.copyProperties(logisticsVOTEMP, objs[0]);
				logisticsVOTEMP.setReleaseUsername(objs[1].toString());
				result.add(logisticsVOTEMP);
			}
		}
		putByRequest("result", result);
		return "queryLogisticsLog";
	}

	/**
	 * 使用的物流记录
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String useLogisticsLog(LogisticsVO logisticsVO) throws Exception {
		StringBuffer resultHQL = new StringBuffer(
				"select  _l  from LogisticsEntity _l inner join _l.receieveUsers as _u  where _u.id=:userId  ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)  from LogisticsEntity _l inner join _l.receieveUsers as _u  where _u.id=:userId  ");
		List<String> paramNames = new ArrayList<String>();
		paramNames.add("userId");
		List<Object> paramValues = new ArrayList<Object>();
		paramValues.add(getLoginUser().getId());
		// 时间
		if (logisticsVO.getStartDate() != null
				&& logisticsVO.getEndDate() != null) {
			resultHQL
					.append(" and (_l.sendDate>=:startDate and _l.sendDate<=:endDate) ");
			countHQL
					.append(" and (_l.sendDate>=:startDate and _l.sendDate<=:endDate) ");
			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(logisticsVO.getStartDate());
			paramValues.add(logisticsVO.getEndDate());
		} else if (logisticsVO.getStartDate() != null) {
			resultHQL.append(" and _l.sendDate>=:startDate  ");
			countHQL.append(" and  _l.sendDate>=:startDate   ");
			paramNames.add("startDate");
			paramValues.add(logisticsVO.getStartDate());
		} else if (logisticsVO.getEndDate() != null) {
			resultHQL.append(" and   _l.sendDate<=:endDate  ");
			countHQL.append(" and   _l.sendDate<=:endDate  ");
			paramNames.add("endDate");
			paramValues.add(logisticsVO.getEndDate());
		}
		// 单号
		if (!StringUtils.isBlank(logisticsVO.getWaybill())) {
			resultHQL.append(" and _l.waybill like :waybill");
			countHQL.append(" and _l.waybill like :waybill");
			paramNames.add("waybill");
			paramValues.add("%" + logisticsVO.getWaybill() + "%");
		}
		resultHQL.append(" order by _l.sendDate  desc ");
		Long count = (Long) logisticsDAO.uniqueResultObject(
				countHQL.toString(), paramNames.toArray(paramNames
						.toArray(new String[paramNames.size()])), paramValues
						.toArray(new Object[paramValues.size()]));
		List<LogisticsVO> result = new ArrayList<LogisticsVO>();
		if (count > 0) {
			logisticsVO.setDataCount(count.intValue());
			List<LogisticsEntity> resultTemp = logisticsDAO.pageQuery(resultHQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), logisticsVO
					.getStart(), logisticsVO.getLimit());

			LogisticsVO logisticsVOTEMP = null;
			for (LogisticsEntity logisticsEntityTemmp : resultTemp) {
				logisticsVOTEMP = new LogisticsVO();
				BeanUtils.copyProperties(logisticsVOTEMP, logisticsEntityTemmp);
				result.add(logisticsVOTEMP);
			}
		}
		putByRequest("result", result);
		return "useLogisticsLog";
	}

	/**
	 * 提交的物流记录
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String logisticsLog(LogisticsVO logisticsVO) throws Exception {
		StringBuffer resultHQL = new StringBuffer(
				"select  _l  from LogisticsEntity _l inner join _l.releaseUser as _u  where _u.id=:userId  ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)  from LogisticsEntity _l inner join _l.releaseUser as _u  where _u.id=:userId  ");
		List<String> paramNames = new ArrayList<String>();
		paramNames.add("userId");
		List<Object> paramValues = new ArrayList<Object>();
		paramValues.add(getLoginUser().getId());
		// 时间
		if (logisticsVO.getStartDate() != null
				&& logisticsVO.getEndDate() != null) {
			resultHQL
					.append(" and (_l.sendDate>=:startDate and _l.sendDate<=:endDate) ");
			countHQL
					.append(" and (_l.sendDate>=:startDate and _l.sendDate<=:endDate) ");
			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(logisticsVO.getStartDate());
			paramValues.add(logisticsVO.getEndDate());
		} else if (logisticsVO.getStartDate() != null) {
			resultHQL.append(" and _l.sendDate>=:startDate  ");
			countHQL.append(" and  _l.sendDate>=:startDate   ");
			paramNames.add("startDate");
			paramValues.add(logisticsVO.getStartDate());
		} else if (logisticsVO.getEndDate() != null) {
			resultHQL.append(" and   _l.sendDate<=:endDate  ");
			countHQL.append(" and   _l.sendDate<=:endDate  ");
			paramNames.add("endDate");
			paramValues.add(logisticsVO.getEndDate());
		}
		// 单号
		if (!StringUtils.isBlank(logisticsVO.getWaybill())) {
			resultHQL.append(" and _l.waybill like :waybill");
			countHQL.append(" and _l.waybill like :waybill");
			paramNames.add("waybill");
			paramValues.add("%" + logisticsVO.getWaybill() + "%");
		}
		resultHQL.append(" order by _l.sendDate  desc ");
		Long count = (Long) logisticsDAO.uniqueResultObject(
				countHQL.toString(), paramNames.toArray(paramNames
						.toArray(new String[paramNames.size()])), paramValues
						.toArray(new Object[paramValues.size()]));
		List<LogisticsVO> result = new ArrayList<LogisticsVO>();
		if (count > 0) {
			logisticsVO.setDataCount(count.intValue());
			List<LogisticsEntity> resultTemp = logisticsDAO.pageQuery(resultHQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), logisticsVO
					.getStart(), logisticsVO.getLimit());

			LogisticsVO logisticsVOTEMP = null;
			for (LogisticsEntity logisticsEntityTemmp : resultTemp) {
				logisticsVOTEMP = new LogisticsVO();
				BeanUtils.copyProperties(logisticsVOTEMP, logisticsEntityTemmp);
				if (logisticsEntityTemmp.getUseCount() > 0) {
					logisticsVOTEMP.setDeleteFlag(false);
				} else {
					logisticsVOTEMP.setDeleteFlag(true);
				}
				result.add(logisticsVOTEMP);
			}
		}
		putByRequest("result", result);
		return "logisticsLog";
	}
}
