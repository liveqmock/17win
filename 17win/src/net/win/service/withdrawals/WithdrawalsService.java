package net.win.service.withdrawals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.UserDAO;
import net.win.dao.WithDrawalsDAO;
import net.win.entity.UserEntity;
import net.win.entity.WithdrawalsEntity;
import net.win.utils.Constant;
import net.win.utils.DateUtils;
import net.win.utils.MailUtils;
import net.win.utils.StringUtils;
import net.win.utils.WinUtils;
import net.win.vo.WithdrawalsVO;
import net.win.utils.ArithUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SuppressWarnings( { "unused", "unchecked" })
@Service("withdrawalsService")
public class WithdrawalsService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private WithDrawalsDAO withDrawalsDAO;
	@Resource
	private JavaMailSender mailSender;
	@Resource
	private FreeMarkerConfigurer freeMarkerCfj;

	/**
	 * 提现记录
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String withdrawalsLog(WithdrawalsVO withdrawalsVO) throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		StringBuffer resultHQL = new StringBuffer(
				"select  _w  from WithdrawalsEntity _w inner join _w.user as _u  where _u.id=:userId  ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)  from WithdrawalsEntity _w inner join _w.user as _u  where _u.id=:userId  ");
		List<String> paramNames = new ArrayList<String>();
		paramNames.add("userId");
		List<Object> paramValues = new ArrayList<Object>();
		paramValues.add(userEntity.getId());
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

		resultHQL.append(" order by _w.operationDate  desc ");
		Long count = (Long) withDrawalsDAO.uniqueResultObject(countHQL
				.toString(), paramNames.toArray(paramNames
				.toArray(new String[paramNames.size()])), paramValues
				.toArray(new Object[paramValues.size()]));
		List<WithdrawalsVO> result = new ArrayList<WithdrawalsVO>();
		if (count > 0) {
			withdrawalsVO.setDataCount(count.intValue());
			List<WithdrawalsEntity> resultTemp = withDrawalsDAO.pageQuery(
					resultHQL.toString(), paramNames.toArray(paramNames
							.toArray(new String[paramNames.size()])),
					paramValues.toArray(new Object[paramValues.size()]),
					withdrawalsVO.getStart(), withdrawalsVO.getLimit());

			WithdrawalsVO withdrawalsVOTEMP = null;
			for (WithdrawalsEntity withdrawalsEntity : resultTemp) {
				withdrawalsVOTEMP = new WithdrawalsVO();
				BeanUtils.copyProperties(withdrawalsVOTEMP, withdrawalsEntity);
				result.add(withdrawalsVOTEMP);
			}
		}
		putByRequest("result", result);
		return "withdrawalsLog";
	}

	/**
	 * 初始化提现
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String initWithdrawals(WithdrawalsVO withdrawalsVO) throws Exception {
		putTokenBySession();
		return "initWithdrawals";
	}

	/**
	 * 提现
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String insertWithdrawals(WithdrawalsVO withdrawalsVO)
			throws Exception {
		putJumpPage("withdrawalsManager/withdrawals!initWithdrawals.php");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		WithdrawalsEntity withdrawalsEntity = new WithdrawalsEntity();
		BeanUtils.copyProperties(withdrawalsEntity, withdrawalsVO);
		if (!userEntity.getOpertationCode().equals(
				StringUtils.processPwd(withdrawalsVO.getOperationCode()))) {
			putAlertMsg("操作码不正确！");
		} else {
			if (withdrawalsEntity.getMoney() < 100) {
				WinUtils.throwIllegalityException("视图越过提现大于100的操作！");
			}
			if (withdrawalsEntity.getMoney() > userEntity.getMoney()) {
				putAlertMsg("提现错误！您的17win余额不够" + withdrawalsEntity.getMoney());
				return JUMP;
			}
			if (StringUtils.isBlank(withdrawalsEntity.getRealName())) {
				putAlertMsg("名字或账号不能为空!");
				return JUMP;
			}
			userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(),
					withdrawalsEntity.getMoney()));
			logMoneyCapital(withDrawalsDAO, 0 - withdrawalsEntity.getMoney(),
					"提现操作", userEntity);
			withdrawalsEntity.setUser(userEntity);
			withdrawalsEntity.setStatus("1");
			withdrawalsEntity.setStatusDesc("进入提现流程");
			withdrawalsEntity.setOperationDate(new Date());
			updateUserLoginInfo(userEntity);
			withDrawalsDAO.save(withdrawalsEntity);
			putAlertMsg("操作成功，您的操作已经进入提现流程，我们会马上完成您的提现然后邮件通知您！");

			MailUtils.sendCommonMail(mailSender, freeMarkerCfj,"用户申请提现", userEntity
					.getUsername()
					+ "在"
					+ DateUtils.format(new Date(), DateUtils.DATE_TIME_FORMAT)
					+ "申请提现", Constant.getXgjEmail());
		}
		return JUMP;
	}
}
