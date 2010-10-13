package net.win.service.admin.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.UserLoginInfo;
import net.win.dao.PayDAO;
import net.win.dao.SmsDAO;
import net.win.dao.UserDAO;
import net.win.entity.PayEntity;
import net.win.entity.SmsEntity;
import net.win.entity.UserEntity;
import net.win.service.sms.SmsService;
import net.win.utils.ArithUtils;
import net.win.utils.StringUtils;
import net.win.utils.WinUtils;
import net.win.vo.AdminPayVO;
import net.win.vo.AdminUserVO;
import net.win.vo.PayVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("adminPaidService")
public class AdminPaidService extends BaseService {
	@Resource
	private UserDAO userDAO;

	@Resource
	private SmsDAO smsDAO;

	@Resource
	private PayDAO payDAO;

	/**
	 * 删除
	 * 
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String deleteMoney(AdminPayVO adminPayVO) throws Exception {
		payDAO.deleteById(Long.parseLong(getByParam("payId")));
		putAlertMsg("删除成功！");
		queryPay(adminPayVO);
		return "updateUserMoney";
	}

	/**
	 * 充值
	 * 
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String updateUserMoney(AdminPayVO adminPayVO) throws Exception {
		UserEntity adminEntity = getLoginUserEntity(userDAO);
		PayEntity payEntity = payDAO.get(Long.parseLong(getByParam("payId")));
		UserEntity userEntity = payEntity.getUser();
		if (!payEntity.getStatus().equals("1")) {
			WinUtils.throwIllegalityException("充值漏洞");
		}
		userEntity.setMoney(ArithUtils.add(userEntity.getMoney(), payEntity
				.getMoney()));
		payEntity.setStatus("2");

		// 站内信
		SmsEntity smsEntity = new SmsEntity();
		smsEntity.setType("1");
		smsEntity.setFromUser(adminEntity);
		smsEntity.setTitle("账号充值");
		smsEntity.setContent("恭喜您，账号充值成功！");
		smsEntity.setSendDate(new Date());
		smsEntity.setToUser(userEntity);
		smsEntity.setRead(false);
		smsDAO.save(smsEntity);
		queryPay(adminPayVO);
		putAlertMsg("充值成功！");
		return "updateUserMoney";
	}

	/**
	 * 
	 * @param payVO
	 * @return
	 * @throws Exception
	 */
	public String queryPay(AdminPayVO adminPayVO) throws Exception {
		StringBuffer resultHQL = new StringBuffer(
				"select _pay ,_user.username from PayEntity _pay inner join _pay.user as _user  where 1=1 ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*) from PayEntity _pay inner join _pay.user as _user where 1=1 ");
		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();
		// 用户名
		if (!StringUtils.isBlank(adminPayVO.getUsername())) {
			resultHQL.append(" and _user.username like :username ");
			countHQL.append(" and _user.username like :username ");
			paramNames.add("username");
			paramValues.add("%" + adminPayVO.getUsername() + "%");
		}
		// 时间
		if (adminPayVO.getStartDate() != null
				&& adminPayVO.getEndDate() != null) {

			resultHQL
					.append(" and (_pay.payDate>=:startDate and _pay.payDate<=:endDate) ");
			countHQL
					.append(" and (_pay.payDate>=:startDate and _pay.payDate<=:endDate) ");

			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(adminPayVO.getStartDate());
			paramValues.add(adminPayVO.getEndDate());
		} else if (adminPayVO.getStartDate() != null) {
			resultHQL.append(" and _pay.payDate>=:startDate  ");
			countHQL.append(" and  _pay.payDate>=:startDate   ");

			paramNames.add("startDate");
			paramValues.add(adminPayVO.getStartDate());
		} else if (adminPayVO.getEndDate() != null) {

			resultHQL.append(" and   _w.payDate<=:endDate ");
			countHQL.append(" and   _w.payDate<=:endDate  ");

			paramNames.add("endDate");
			paramValues.add(adminPayVO.getEndDate());
		}

		Long count = (Long) payDAO.uniqueResultObject(countHQL.toString(),
				paramNames.toArray(paramNames.toArray(new String[paramNames
						.size()])), paramValues.toArray(new Object[paramValues
						.size()]));

		List<AdminPayVO> result = new ArrayList<AdminPayVO>();
		if (count > 0) {
			adminPayVO.setDataCount(count.intValue());
			List<Object[]> resultTemp = payDAO.pageQuery(resultHQL.toString(),
					paramNames.toArray(paramNames.toArray(new String[paramNames
							.size()])), paramValues
							.toArray(new Object[paramValues.size()]),
					adminPayVO.getStart(), adminPayVO.getLimit());
			AdminPayVO payVOTemp = null;
			for (Object[] objs : resultTemp) {
				payVOTemp = new AdminPayVO();
				BeanUtils.copyProperties(payVOTemp, objs[0]);
				payVOTemp.setUsername(objs[1].toString());
				result.add(payVOTemp);
			}
		}
		putByRequest("result", result);
		return "queryPay";
	}
}
