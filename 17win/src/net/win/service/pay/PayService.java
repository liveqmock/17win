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

@SuppressWarnings({"unchecked"})
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
		putIndexShowType("pay");
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
		String customMoney = getByParam("customMoney");

		UserEntity userEntity = getLoginUserEntity(userDAO);
		if (!userEntity.getOpertationCode().equals(
				StringUtils.processPwd(opertationCode))) {
			putAlertMsg("操作码不正确！");
			putJumpSelfPage("payManager/pay!initPay.php");
			return JUMP;
		}
		if (!getVerifyCode().equals(verificationCode)) {
			putAlertMsg("验证码不正确！");
			putJumpSelfPage("payManager/pay!initPay.php");
			return JUMP;
		}
		PayEntity payEntity = new PayEntity();
		BeanUtils.copyProperties(payEntity, payVO);
		payEntity.setStatus("1");
		payEntity.setPayDate(new Date());
		payEntity.setUser(userEntity);
		if (!StringUtils.isBlank(customMoney)) {
			payEntity.setMoney(Double.parseDouble(customMoney));
		}
		payDAO.save(payEntity);
		String toTaobao = null;
		switch (payVO.getMoney().intValue()) {
			case 1 :
				toTaobao = "http://item.taobao.com/auction/item_detail.htm?item_num_id=9027703346";
				break;
			case 10 :
				toTaobao = "http://item.taobao.com/item.htm?id=9027758246";
				break;
			case 50 :
				toTaobao = "http://item.taobao.com/auction/item_detail.htm?item_num_id=9020022672";
				break;
			case 100 :
				toTaobao = "http://item.taobao.com/auction/item_detail.htm?item_num_id=9027716018";
				break;
			default :
				toTaobao = "http://shop62179252.taobao.com/?order=&queryType=cat&browseType=grid&searchWord=im%C9%E7%C7%F8&price1=&price2=";
		}
		MailUtils.sendCommonMail(mailSender, freeMarkerCfj, userEntity
				.getUsername()
				+ "用户申请充值", userEntity.getUsername() + "在"
				+ DateUtils.format(new Date(), DateUtils.DATE_TIME_FORMAT)
				+ "用淘宝号:【" + payVO.getBuyername() + "】提交充值", Constant
				.getXgjEmail());
		putJumpOutterPage(toTaobao, false, true);
		putAlertMsg("充值提交成功，现在到淘宝进行支付！");
		return JUMP;
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
