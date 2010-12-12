package net.win.service.pay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.UserLoginInfo;
import net.win.dao.PayDAO;
import net.win.dao.UserDAO;
import net.win.entity.PayEntity;
import net.win.entity.UserEntity;
import net.win.utils.Constant;
import net.win.utils.DateUtils;
import net.win.utils.MailUtils;
import net.win.utils.StringUtils;
import net.win.vo.PayVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SuppressWarnings( { "unchecked" })
@Service("payService")
public class PayService extends BaseService {
	@Resource
	private PayDAO payDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private JavaMailSender mailSender;
	@Resource
	private FreeMarkerConfigurer freeMarkerCfj;

	/**
	 * 初始化
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String initPay(PayVO payVO) throws Exception {
		putIndexShowType("6");
		putTokenBySession();
		return "initPay";
	}

	/**
	 * 添加
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String insertPay(PayVO payVO) throws Exception {
		String opertationCode = getByParam("opertationCode");
		String verificationCode = getByParam("verificationCode");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		if (!userEntity.getOpertationCode().equals(
				StringUtils.processPwd(opertationCode))) {
			putAlertMsg("操作码不正确！");
		}
		if (!getVerifyCode().equals(verificationCode)) {
			putAlertMsg("验证码不正确！");
			return "insertPay";
		}
		PayEntity payEntity = new PayEntity();
		BeanUtils.copyProperties(payEntity, payVO);
		payEntity.setStatus("1");
		payEntity.setPayDate(new Date());
		payEntity.setUser(userEntity);
		payDAO.save(payEntity);
		putAlertMsg("充值提交成功，请到淘宝进行充值,如有问题，请联系客户！");
		putByRequest("toTaobao", "toTaobao");
		MailUtils.sendCommonMail(mailSender, freeMarkerCfj,"用户申请充值", userEntity
				.getUsername()
				+ "在"
				+ DateUtils.format(new Date(), DateUtils.DATE_TIME_FORMAT)
				+ "提交充值", Constant.getXgjEmail());
		return "insertPay";
	}

	/**
	 * 
	 * @param payVO
	 * @return
	 * @throws Exception
	 */
	public String queryPay(PayVO payVO) throws Exception {
		UserLoginInfo userLoginInfo = getLoginUser();
		StringBuffer resultHQL = new StringBuffer(
				"select _pay  from PayEntity _pay inner join _pay.user as _user where _user.id=:userId");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*) from PayEntity _pay inner join _pay.user as _user where _user.id=:userId");
		List<String> paramNames = new ArrayList<String>();
		paramNames.add("userId");
		List<Object> paramValues = new ArrayList<Object>();
		paramValues.add(userLoginInfo.getId());

		// 时间
		if (payVO.getStartDate() != null && payVO.getEndDate() != null) {

			resultHQL
					.append(" and (_pay.payDate>=:startDate and _pay.payDate<=:endDate) ");
			countHQL
					.append(" and (_pay.payDate>=:startDate and _pay.payDate<=:endDate) ");

			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(payVO.getStartDate());
			paramValues.add(payVO.getEndDate());
		} else if (payVO.getStartDate() != null) {
			resultHQL.append(" and _pay.payDate>=:startDate  ");
			countHQL.append(" and  _pay.payDate>=:startDate   ");
			paramNames.add("startDate");
			paramValues.add(payVO.getStartDate());
		} else if (payVO.getEndDate() != null) {

			resultHQL.append(" and   _w.payDate<=:endDate ");
			countHQL.append(" and   _w.payDate<=:endDate  ");
			paramNames.add("endDate");
			paramValues.add(payVO.getEndDate());
		}

		Long count = (Long) payDAO.uniqueResultObject(countHQL.toString(),
				paramNames.toArray(paramNames.toArray(new String[paramNames
						.size()])), paramValues.toArray(new Object[paramValues
						.size()]));

		List<PayVO> result = new ArrayList<PayVO>();
		if (count > 0) {
			payVO.setDataCount(count.intValue());
			List<PayEntity> resultTemp = payDAO.pageQuery(resultHQL.toString(),
					paramNames.toArray(paramNames.toArray(new String[paramNames
							.size()])), paramValues
							.toArray(new Object[paramValues.size()]), payVO
							.getStart(), payVO.getLimit());
			PayVO payVOTemp = null;
			for (PayEntity payEntity : resultTemp) {
				payVOTemp = new PayVO();
				BeanUtils.copyProperties(payVOTemp, payEntity);
				result.add(payVOTemp);
			}
		}
		putByRequest("result", result);
		return "queryPay";
	}
}
