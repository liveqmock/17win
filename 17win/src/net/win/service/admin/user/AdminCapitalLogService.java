package net.win.service.admin.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.UserLoginInfo;
import net.win.dao.CapitalLogDAO;
import net.win.dao.UserDAO;
import net.win.dao.WithDrawalsDAO;
import net.win.entity.CapitalLogEntity;
import net.win.entity.UserEntity;
import net.win.entity.WithdrawalsEntity;
import net.win.utils.StringUtils;
import net.win.utils.WinUtils;
import net.win.vo.CapitalLogVO;
import net.win.vo.WithdrawalsVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SuppressWarnings( { "unused", "unchecked" })
@Service("adminCapitalLogService")
public class AdminCapitalLogService extends BaseService {
	@Resource
	private CapitalLogDAO capitalLogDAO;

	/**
	 * 提现记录
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String queryLogs(CapitalLogVO capitalLogVO) throws Exception {
		UserLoginInfo userLoginInfo = getLoginUser();
		StringBuffer resultHQL = new StringBuffer(
				"select  _c ,_u.username from CapitalLogEntity _c inner join _c.user as _u  where  1=1  ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)  from CapitalLogEntity _c inner join _c.user as _u  where 1=1  ");
		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();

		if (!StringUtils.isBlank(capitalLogVO.getUsername())) {
			resultHQL.append(" and _u.username like :username ");
			countHQL.append(" and _u.username like :username ");
			paramNames.add("username");
			paramValues.add("%"+capitalLogVO.getUsername()+"%");
		}
		// 类型
		if (!StringUtils.isBlank(capitalLogVO.getType())) {
			resultHQL.append(" and _c.type=:type ");
			countHQL.append(" and _c.type=:type ");
			paramNames.add("type");
			paramValues.add(capitalLogVO.getType());
		}
		// /钱
		if (capitalLogVO.getStartValue() != null
				&& capitalLogVO.getEndValue() != null) {
			resultHQL
					.append(" and (_c.value>=:startValue and _c.value<=:endValue) ");
			countHQL
					.append(" and (_c.value>=:startValue and _c.value<=:endValue) ");
			paramNames.add("startValue");
			paramNames.add("endValue");
			paramValues.add(capitalLogVO.getStartValue());
			paramValues.add(capitalLogVO.getEndValue());
		} else if (capitalLogVO.getStartValue() != null) {
			resultHQL.append(" and _c.value>=:startValue  ");
			countHQL.append(" and  _c.value>=:startValue   ");
			paramNames.add("startValue");
			paramValues.add(capitalLogVO.getStartValue());
		} else if (capitalLogVO.getEndValue() != null) {
			resultHQL.append(" and   _c.value<=:endValue  ");
			countHQL.append(" and   _c.value<=:endValue  ");
			paramNames.add("endValue");
			paramValues.add(capitalLogVO.getEndValue());
		}
		// 时间
		if (capitalLogVO.getStartDate() != null
				&& capitalLogVO.getEndDate() != null) {
			resultHQL
					.append(" and (_c.logTime>=:startDate and _c.logTime<=:endDate) ");
			countHQL
					.append(" and (_c.logTime>=:startDate and _c.logTime<=:endDate) ");
			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(capitalLogVO.getStartDate());
			paramValues.add(capitalLogVO.getEndDate());
		} else if (capitalLogVO.getStartDate() != null) {
			resultHQL.append(" and _c.logTime>=:startDate  ");
			countHQL.append(" and  _c.logTime>=:startDate   ");
			paramNames.add("startDate");
			paramValues.add(capitalLogVO.getStartDate());
		} else if (capitalLogVO.getEndDate() != null) {
			resultHQL.append(" and   _c.logTime<=:endDate  ");
			countHQL.append(" and   _c.logTime<=:endDate  ");
			paramNames.add("endDate");
			paramValues.add(capitalLogVO.getEndDate());
		}
		resultHQL.append(" order by  _c.logTime");
		Long count = (Long) capitalLogDAO.uniqueResultObject(countHQL
				.toString(), paramNames.toArray(paramNames
				.toArray(new String[paramNames.size()])), paramValues
				.toArray(new Object[paramValues.size()]));
		List<CapitalLogVO> result = new ArrayList<CapitalLogVO>();
		if (count > 0) {
			capitalLogVO.setDataCount(count.intValue());
			List<Object[]> resultTemp = capitalLogDAO.pageQuery(
					resultHQL.toString(), paramNames.toArray(paramNames
							.toArray(new String[paramNames.size()])),
					paramValues.toArray(new Object[paramValues.size()]),
					capitalLogVO.getStart(), capitalLogVO.getLimit());

			CapitalLogVO capitalLogVOTEMP = null;
			for (Object[] objs : resultTemp) {
				capitalLogVOTEMP = new CapitalLogVO();
				BeanUtils
						.copyProperties(capitalLogVOTEMP, objs[0]);
				capitalLogVOTEMP.setUsername(objs[1].toString());
				result.add(capitalLogVOTEMP);
			}
		}
		putByRequest("result", result);
		return "queryLogs";
	}
}
