package net.win.service.vip;

import java.util.Calendar;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.UserDAO;
import net.win.dao.VipBidUserDAO;
import net.win.dao.VipDAO;
import net.win.entity.UserEntity;
import net.win.entity.VipBidUserEntity;
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
	@Resource
	private VipBidUserDAO vipBidUserDAO;

	/**
	 * 初始化
	 * 
	 * @param vipVO
	 * @return
	 * @throws Exception
	 */
	public String initVip(VipVO vipVO) throws Exception {
		putIndexShowType("9");
		putByRequest("vipPrice", Constant.getVipPrice());
		putByRequest("vipYearRebate", Constant.getVipYearRebate()*10);
		putByRequest("receieveTaskDotRate", Constant.getReceieveTaskDotRate());
		 
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
			putJumpPage("vipManager/vip!initVip.php");
			return JUMP;
		}

		Double money = 0D;
		if (monthCount > 12) {
			money = monthCount * Constant.getVipYearRebate();
		} else {
			money = monthCount * Constant.getVipPrice();
		}

		if (userEntity.getMoney() < money) {
			putAlertMsg("您当前的余额不足" + money + "！");
			putJumpPage("vipManager/vip!initVip.php");
			return JUMP;
		}

		VipEntity vipEntity = vipDAO.getVIP1();
		userEntity.setVip(vipEntity);
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.MONTH, monthCount);
		userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(), money));
		userEntity.setVipEnable(true);
		getLoginUser().setVipType("1");

		VipBidUserEntity vipBidUserEntity = new VipBidUserEntity();
		vipBidUserEntity.setEndDate(calendar.getTime());
		vipBidUserEntity.setGrowValue(0);
		vipBidUserEntity.setRemainMsgCount(vipEntity.getPhoneMsg());
		vipBidUserEntity.setUser(userEntity);
		vipBidUserDAO.save(vipBidUserEntity);

		userEntity.setVipBidUserEntity(vipBidUserEntity);

		// 通过你的宣传链接注册的会员购买VIP
		// 你的收益=10个发布点
		if (userEntity.getReferee() != null) {
			userEntity.getReferee().setMoney(
					userEntity.getReferee().getMoney()
							+ Constant.getRefreeByVipMoney());
			logMoneyCapital(userDAO, Constant.getRefreeByVipMoney(), "你推荐的名为："
					+ userEntity.getUsername() + "购买VIP。", userEntity
					.getReferee());
		}
		updateUserLoginInfo(userEntity);
		getLoginUser().setVipEndDate(vipBidUserEntity.getEndDate());
		getLoginUser().setVipGrowValue(vipBidUserEntity.getGrowValue());
		logMoneyCapital(userDAO, 0 - money, "购买VIP", userEntity);
		putJumpPage("vipManager/vip!initVip.php");
		return JUMP;
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
		VipBidUserEntity vipBidUserEntity = userEntity.getVipBidUserEntity();
		if (!userEntity.getOpertationCode().equals(
				StringUtils.processPwd(operationCode))) {
			putAlertMsg("操作码不正确！");
			putJumpPage("vipManager/vip!initVip.php");
			return JUMP;
		}

		Double money = 0D;
		if (monthCount>12) {
			money = monthCount * Constant.getVipYearRebate();
		} else {
			money = monthCount * Constant.getVipPrice();
		}

		if (userEntity.getMoney() < money) {
			putAlertMsg("您当前的余额不足" + money + "！");
			putJumpPage("vipManager/vip!initVip.php");
			return JUMP;
		}
		Calendar calendar = Calendar.getInstance();
		// VIP已经失效
		if (!userEntity.getVipEnable()) {
			calendar.add(calendar.MONTH, monthCount);
			vipBidUserEntity.setEndDate(calendar.getTime());
			userEntity.setVipEnable(true);
		} else {
			calendar.setTime(vipBidUserEntity.getEndDate());
			calendar.add(calendar.MONTH, monthCount);
			vipBidUserEntity.setEndDate(calendar.getTime());
		}
		userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(), money));
		logMoneyCapital(userDAO, 0-money, "续费"+monthCount+"个月的VIP", userEntity);
		updateUserLoginInfo(userEntity);
		getLoginUser().setVipEndDate(vipBidUserEntity.getEndDate());
		getLoginUser().setVipGrowValue(vipBidUserEntity.getGrowValue());
		putAlertMsg("恭喜您续费成功！");
		putJumpPage("vipManager/vip!initVip.php");
		return JUMP;
	}
}
