package net.win.service.vip;

import java.util.Calendar;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.UserDAO;
import net.win.dao.VipDAO;
import net.win.entity.UserEntity;
import net.win.entity.VipEntity;
import net.win.utils.ArithUtils;
import net.win.utils.Constant;
import net.win.utils.StringUtils;
import net.win.vo.VipVO;

import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("vipService")
public class VipService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private VipDAO vipDAO;

	/**
	 * 初始化
	 * 
	 * @param vipVO
	 * @return
	 * @throws Exception
	 */
	public String initVip(VipVO vipVO) throws Exception {
		return INPUT;
	}

	/**
	 * 添加VIP
	 * 
	 * @param vipVO
	 * @return
	 * @throws Exception
	 */
	public String insertVip(VipVO vipVO) throws Exception {
		String operationCode = getByParam("operationCode");
		Integer monthCount = Integer.parseInt(getByParam("monthC"));
		UserEntity userEntity = userDAO.get(getLoginUser().getId());
		if (!userEntity.getOpertationCode().equals(
				StringUtils.processPwd(operationCode))) {
			putAlertMsg("操作码不正确！");
			return "insertVip";
		}

		Double money = 0D;
		if (monthCount.equals(12)) {
			money = monthCount * Constant.getYearVipPrice();
		} else {
			money = monthCount * Constant.getVipPrice();
		}

		if (userEntity.getMoney() < money) {
			putAlertMsg("您当前的余额不足" + money + "！");
			return "insertVip";
		}

		VipEntity vipEntity = vipDAO.getVIP1();
		userEntity.setVip(vipEntity);
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.MONTH, monthCount);
		userEntity.setVipEndDate(calendar.getTime());
		userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(), money));
		userEntity.setVipGrowValue(0);
		getLoginUser().setVipType("1");
		updateUserLoginInfo(userEntity);
		putAlertMsg("恭喜您加入VIP，快去体验吧！");
		return "insertVip";
	}

	/**
	 * 续费
	 * 
	 * @param vipVO
	 * @return
	 * @throws Exception
	 */
	public String insertRenewalVip(VipVO vipVO) throws Exception {
		String operationCode = getByParam("operationCode");
		Integer monthCount = Integer.parseInt(getByParam("monthC"));
		UserEntity userEntity = userDAO.get(getLoginUser().getId());
		if (!userEntity.getOpertationCode().equals(
				StringUtils.processPwd(operationCode))) {
			putAlertMsg("操作码不正确！");
			return "insertRenewalVip";
		}

		Double money = 0D;
		if (monthCount.equals(12)) {
			money = monthCount * Constant.getYearVipPrice();
		} else {
			money = monthCount * Constant.getVipPrice();
		}

		if (userEntity.getMoney() < money) {
			putAlertMsg("您当前的余额不足" + money + "！");
			return "insertRenewalVip";
		}
		Calendar calendar = Calendar.getInstance();
		// VIP已经失效
		if (!userEntity.getVipEnable()) {
			calendar.add(calendar.MONTH, monthCount);
			userEntity.setVipEndDate(calendar.getTime());
			userEntity.setVipEnable(true);
		} else {
			calendar.setTime(userEntity.getVipEndDate());
			calendar.add(calendar.MONTH, monthCount);
			userEntity.setVipEndDate(calendar.getTime());
		}
		userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(), money));
		updateUserLoginInfo(userEntity);
		putAlertMsg("恭喜您续费成功！");
		return "insertRenewalVip";

	}
}
