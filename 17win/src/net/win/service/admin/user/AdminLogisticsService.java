package net.win.service.admin.user;

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
import net.win.utils.DateUtils;
import net.win.utils.LoggerUtils;
import net.win.utils.StringUtils;
import net.win.vo.LogisticsVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unused", "unchecked" })
@Service("logisticsService")
public class AdminLogisticsService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private LogisticsDAO logisticsDAO;
	@Resource
	private AdminLogisticsService logisticsService;

	/**
	 * 撤销
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateRedoLogistics(LogisticsVO logisticsVO) throws Exception {
		final double LOGISTICS_DOT = 0.2;
		putJumpPage("adminLogisticsManager/adminLogistics!queryLogisticsLog.php");
		String logisticsID = getByParam("logisticsID");
		if (StringUtils.isBlank(logisticsID)) {
			putAlertMsg("请选择物流数据");
			return JUMP;
		}
		Long logisticsIDLong = Long.parseLong(logisticsID);
		LogisticsEntity logisticsEntity = logisticsDAO.get(logisticsIDLong);
		UserEntity releaseUser = logisticsEntity.getReleaseUser();
		List<UserEntity> reveieveUsers = logisticsEntity.getReceieveUsers();
		if (reveieveUsers.size() > 0) {
			for (UserEntity userEntity : reveieveUsers) {
				userEntity.setReleaseDot(ArithUtils.add(userEntity
						.getReleaseDot(), LOGISTICS_DOT));
				logDotCapital(userDAO, LOGISTICS_DOT, "因为"
						+ releaseUser.getUsername() + "发送的物流信息是虚假的，所以退还发布点",
						userEntity);
			}
			// 如果有多余的发布点扣除，就扣发布点
			if (releaseUser.getReleaseDot() > logisticsEntity
					.getReleaseDotCount()) {
				releaseUser
						.setReleaseDot(ArithUtils.sub(releaseUser
								.getReleaseDot(), logisticsEntity
								.getReleaseDotCount()));
				logDotCapital(userDAO,
						0 - logisticsEntity.getReleaseDotCount(), "您发的快递单号为"
								+ logisticsEntity.getWaybill()
								+ "为虚假信息扣除您根据此单号获得的所有发布点", releaseUser);
				logisticsDAO.delete(logisticsEntity);
			} else {
				releaseUser.setStatus("2");
				releaseUser.setStatusDesc("您发送的快递单号为:"
						+ logisticsEntity.getWaybill()
						+ "不真实，被人举报。由于您的发布点不够，使您的账号被冻结，请联系客户！");
				logisticsEntity.setStatus("2");
				putAlertMsg("撤销失败，因为发布点不够！");
				return JUMP;
			}
		} else {
			logisticsDAO.delete(logisticsEntity);
		}
		return JUMP;
	}

	/**
	 * 物流信息
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String queryLogisticsLog(LogisticsVO logisticsVO) throws Exception {
		StringBuffer resultHQL = new StringBuffer(
				"select  _l ,_u.username from LogisticsEntity _l inner join _l.releaseUser as _u  where 1=1  ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)  from LogisticsEntity _l inner join _l.releaseUser as _u  where 1=1  ");
		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();
		// 使用次数
		if (logisticsVO.getStartUseCount() != null
				&& logisticsVO.getEndUseCount() != null) {
			resultHQL
					.append(" and (_l.useCount>=:startUseCount and _l.useCount<=:endUseCount) ");
			countHQL
					.append(" and (_l.useCount>=:startUseCount and _l.useCount<=:endUseCount) ");
			paramNames.add("startUseCount");
			paramNames.add("endUseCount");
			paramValues.add(logisticsVO.getStartUseCount());
			paramValues.add(logisticsVO.getEndUseCount());
		} else if (logisticsVO.getStartUseCount() != null) {
			resultHQL.append(" and _l.useCount>=:startUseCount  ");
			countHQL.append(" and  _l.useCount>=:startUseCount   ");
			paramNames.add("startUseCount");
			paramValues.add(logisticsVO.getStartUseCount());
		} else if (logisticsVO.getEndUseCount() != null) {
			resultHQL.append(" and   _l.useCount<=:endUseCount  ");
			countHQL.append(" and   _l.useCount<=:endUseCount  ");
			paramNames.add("endUseCount");
			paramValues.add(logisticsVO.getEndUseCount());
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
}
