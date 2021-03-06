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
import net.win.utils.Constant;
import net.win.utils.DateUtils;
import net.win.utils.LoggerUtils;
import net.win.utils.StringUtils;
import net.win.vo.LogisticsVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unused", "unchecked" })
@Service("adminLogisticsService")
public class AdminLogisticsService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private LogisticsDAO logisticsDAO;

	/**
	 * 撤销
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateRedoLogistics(LogisticsVO logisticsVO) throws Exception {

		putJumpSelfPage("adminLogisticsManager/adminLogistics!queryLogisticsLog.php");
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
			// 如果有多余金额，就扣金额 + 10(处罚)
			if (releaseUser.getMoney() > (logisticsEntity.getUseCount()
					* logisticsEntity.getMoney() + 10)) {
				releaseUser.setMoney(ArithUtils.sub(releaseUser.getMoney(),
						logisticsEntity.getUseCount()
								* logisticsEntity.getMoney() + 10));
				logMoneyCapital(userDAO, 0 - (logisticsEntity.getUseCount()
						* logisticsEntity.getMoney() + 10), "您发的快递单号为"
						+ logisticsEntity.getWaybill()
						+ "为虚假信息扣除您根据此单号获得的所有获得的金额,并处罚10元", releaseUser);
				logisticsDAO
						.deleteBySQL(
								"delete from tb_logistics_bid_user where Logistics_ID_=:logisticsID",
								new String[] { "logisticsID" },
								new Object[] { logisticsEntity.getId() });
				logisticsDAO.deleteBySQL("delete from tb_logicstics",
						new String[] { "logisticsID" },
						new Object[] { logisticsEntity.getId() });
				for (UserEntity userEntity : reveieveUsers) {
					userEntity.setMoney(ArithUtils.add(userEntity.getMoney(),
							logisticsEntity.getMoney()));
					logMoneyCapital(userDAO, logisticsEntity.getMoney(),
							"经查实，因为" + releaseUser.getUsername()
									+ "发送的物流信息是虚假的，所以退金额", userEntity);
				}
				logisticsDAO.delete(logisticsEntity);
			} else {
				releaseUser.setStatus("2");
				releaseUser.setStatusDesc("您发送的快递单号为:"
						+ logisticsEntity.getWaybill()
						+ "不真实，被人举报。由于您的发布点不够，使您的账号被冻结，请联系客户！");
				logisticsEntity.setStatus("2");
				putAlertMsg("撤销失败，因为发布点不够被冻结！");
				return JUMP;
			}
		} else {
			logisticsDAO
					.deleteBySQL(
							"delete from tb_logistics_bid_user where Logistics_ID_=:logisticsID",
							new String[] { "logisticsID" },
							new Object[] { logisticsEntity.getId() });
			logisticsDAO.deleteBySQL(
					"delete from tb_logicstics where id_=:logisticsID",
					new String[] { "logisticsID" },
					new Object[] { logisticsEntity.getId() });
			putAlertMsg("撤销成功,物流信息被删除！");
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
