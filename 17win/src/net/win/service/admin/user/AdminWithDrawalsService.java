package net.win.service.admin.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.UserDAO;
import net.win.dao.WithDrawalsDAO;
import net.win.entity.UserEntity;
import net.win.entity.WithdrawalsEntity;
import net.win.utils.ArithUtils;
import net.win.utils.StringUtils;
import net.win.utils.WinUtils;
import net.win.vo.WithdrawalsVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SuppressWarnings( { "unused", "unchecked" })
@Service("adminWithDrawalsService")
public class AdminWithDrawalsService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private WithDrawalsDAO withDrawalsDAO;

	/**
	 * 提现记录
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String queryLog(WithdrawalsVO withdrawalsVO) throws Exception {
		StringBuffer resultHQL = new StringBuffer(
				"select  _w,_u.username  from WithdrawalsEntity _w inner join _w.user as _u  where 1=1 ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)  from WithdrawalsEntity _w inner join _w.user as _u  where 1=1 ");
		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();
		// 用户名
		if (!StringUtils.isBlank(withdrawalsVO.getUsername())) {
			resultHQL.append(" and _u.username=:username ");
			countHQL.append(" and _u.username=:username ");
			paramNames.add("username");
			paramValues.add(withdrawalsVO.getUsername());
		}
		// 类型
		if (!StringUtils.isBlank(withdrawalsVO.getType())) {
			resultHQL.append(" and _w.type=:type ");
			countHQL.append(" and _w.type=:type ");
			paramNames.add("type");
			paramValues.add(withdrawalsVO.getType());
		}
		// 店铺类型
		if (!StringUtils.isBlank(withdrawalsVO.getShopType())) {
			resultHQL.append(" and _w.shopType=:shopType ");
			countHQL.append(" and _w.shopType=:shopType ");
			paramNames.add("shopType");
			paramValues.add(withdrawalsVO.getShopType());
		}
		// /钱
		if (withdrawalsVO.getStartMoney() != null
				&& withdrawalsVO.getEndMoney() != null) {
			resultHQL
					.append(" and (_w.money>=:startMoney and _w.money<=:endMoney) ");
			countHQL
					.append(" and (_w.money>=:startMoney and _w.money<=:endMoney) ");
			paramNames.add("startMoney");
			paramNames.add("endMoney");
			paramValues.add(withdrawalsVO.getStartMoney());
			paramValues.add(withdrawalsVO.getEndMoney());
		} else if (withdrawalsVO.getStartMoney() != null) {
			resultHQL.append(" and _w.money>=:startMoney  ");
			countHQL.append(" and  _w.money>=:startMoney   ");
			paramNames.add("startMoney");
			paramValues.add(withdrawalsVO.getStartMoney());
		} else if (withdrawalsVO.getEndMoney() != null) {
			resultHQL.append(" and   _w.money<=:endMoney  ");
			countHQL.append(" and   _w.money<=:endMoney  ");
			paramNames.add("endMoney");
			paramValues.add(withdrawalsVO.getEndMoney());
		}
		// 时间
		if (withdrawalsVO.getStartDate() != null
				&& withdrawalsVO.getEndDate() != null) {
			resultHQL
					.append(" and (_w.operationDate>=:startDate and _w.operationDate<=:endDate) ");
			countHQL
					.append(" and (_w.operationDate>=:startDate and _w.operationDate<=:endDate) ");
			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(withdrawalsVO.getStartDate());
			paramValues.add(withdrawalsVO.getEndDate());
		} else if (withdrawalsVO.getStartDate() != null) {
			resultHQL.append(" and _w.operationDate>=:startDate  ");
			countHQL.append(" and  _w.operationDate>=:startDate   ");
			paramNames.add("startDate");
			paramValues.add(withdrawalsVO.getStartDate());
		} else if (withdrawalsVO.getEndDate() != null) {
			resultHQL.append(" and   _w.operationDate<=:endDate  ");
			countHQL.append(" and   _w.operationDate<=:endDate  ");
			paramNames.add("endDate");
			paramValues.add(withdrawalsVO.getEndDate());
		}
		// 状态
		if (!StringUtils.isBlank(withdrawalsVO.getStatus())) {
			resultHQL.append(" and _w.status=:status ");
			countHQL.append(" and _w.status=:status ");
			paramNames.add("status");
			paramValues.add(withdrawalsVO.getStatus());
		}
		resultHQL.append(" order by _w.operationDate");
		Long count = (Long) withDrawalsDAO.uniqueResultObject(countHQL
				.toString(), paramNames.toArray(paramNames
				.toArray(new String[paramNames.size()])), paramValues
				.toArray(new Object[paramValues.size()]));
		List<WithdrawalsVO> result = new ArrayList<WithdrawalsVO>();
		if (count > 0) {
			withdrawalsVO.setDataCount(count.intValue());
			List<Object[]> resultTemp = withDrawalsDAO.pageQuery(resultHQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]), withdrawalsVO
					.getStart(), withdrawalsVO.getLimit());

			WithdrawalsVO withdrawalsVOTEMP = null;
			for (Object[] objs : resultTemp) {
				withdrawalsVOTEMP = new WithdrawalsVO();
				BeanUtils.copyProperties(withdrawalsVOTEMP, objs[0]);
				withdrawalsVOTEMP.setUsername(objs[1].toString());
				result.add(withdrawalsVOTEMP);
			}
		}
		putByRequest("result", result);
		return "updateLog";
	}

	/**
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateLog(WithdrawalsVO withdrawalsVO) throws Exception {
		String flag = getByParam("flag");
		WithdrawalsEntity withdrawalsEntity = withDrawalsDAO.get(withdrawalsVO
				.getId());
		UserEntity userEntity = withdrawalsEntity.getUser();
		// 完成
		if ("1".equals(withdrawalsEntity.getStatus())
				&& "over".equalsIgnoreCase(flag)) {
			if (userEntity.getMoney() > withdrawalsEntity.getMoney()) {
				withdrawalsEntity.setStatus("3");
				withdrawalsEntity.setStatusDesc("提款成功!");
			}
		} else if ("1".equals(withdrawalsEntity.getStatus())
				&& "reject".equalsIgnoreCase(flag)) {
			withdrawalsEntity.setStatusDesc(withdrawalsVO.getStatusDesc());
			withdrawalsEntity.setStatus("2");
			userEntity.setMoney(ArithUtils.add(withdrawalsEntity.getMoney(),
					withdrawalsEntity.getMoney()));
			logMoneyCapital(withDrawalsDAO, withdrawalsEntity.getMoney(),
					"提款被驳回,原因:" + withdrawalsVO.getStatusDesc(), userEntity);
		}
		putJumpPage("adminWithdrawalsManager/adminWithdrawals!queryLog.php");
		putAlertMsg("操作成功");
		return JUMP;
	}
}
