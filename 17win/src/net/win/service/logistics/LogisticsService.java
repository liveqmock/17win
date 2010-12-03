package net.win.service.logistics;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.LogisticsDAO;
import net.win.dao.UserDAO;
import net.win.entity.LogisticsEntity;
import net.win.entity.UserEntity;
import net.win.utils.ArithUtils;
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

	/**
	 * 添加物流
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String insertLogistics(LogisticsVO logisticsVO) throws Exception {
		putJumpPage("logisticsManager/logistics!initLogistics.php");
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
		putJumpPage("logisticsManager/logistics!logisticsLog.php");
		String logisticsID = getByParam("logisticsID");
		Long logisticsIDLong = Long.parseLong(logisticsID);
		if (logisticsID == null) {
			putAlertMsg("请选择物流数据");
			return "error";
		}
		LogisticsEntity logisticsEntity = logisticsDAO.get(logisticsIDLong);
		if (!logisticsEntity.getReleaseUser().getId().equals(getLoginUser().getId())) {
			putAlertMsg("请选择物流数据");
			return JUMP;
		}
		if (logisticsEntity.getUseCount()> 0) {
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
	public String updateUseLogistics() throws Exception {
		String logisticsID = getByParam("logisticsID");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		Long logisticsIDLong = Long.parseLong(logisticsID);
		if (logisticsID == null) {
			putAlertMsg("请选择物流数据");
			return "error";
		}
		Long count = (Long) logisticsDAO
				.uniqueResultObject(
						"select count(*) from LogisticsEntity as _l inner join _l.receieveUsers as _ru where _ru.id=:userID and _l.id=:logistisID",
						new String[] { "userID", "logistisID" }, new Object[] {
								getLoginUser().getId(), logisticsIDLong });
		if (count > 0) {
			return "true";
		} else {
			if (userEntity.getReleaseDot() < 0.1) {
				return "false";
			} else {
				LogisticsEntity logisticsEntity = logisticsDAO
						.get(logisticsIDLong);
				logisticsEntity.addReceieveUser(userEntity);
				logisticsEntity.setUseCount(logisticsEntity.getUseCount() + 1);
				logisticsEntity.setReleaseDotCount(logisticsEntity
						.getReleaseDotCount() + 0.1);
				userEntity.setReleaseDot(ArithUtils.sub(userEntity
						.getReleaseDot(), 0.1));
				logisticsEntity.getReleaseUser().setReleaseDot(
						ArithUtils.add(logisticsEntity.getReleaseUser()
								.getReleaseDot(), 0.1));
				logDotCapital(userDAO, 0 - 0.1, "获取物流（快递单号"
						+ logisticsEntity.getWaybill() + ")扣发布点", userEntity);
				logDotCapital(userDAO, 0.1, logisticsEntity.getWaybill()
						+ "物流被别人使用得到发布点", logisticsEntity.getReleaseUser());
				return "true";
			}
		}
	}

	/**
	 * 物流记录
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
			resultHQL.append(" and (_l.waybill like :waybill");
			countHQL.append(" and (_l.waybill like :waybill");
			paramNames.add("waybill");
			paramValues.add(logisticsVO.getWaybill());
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
