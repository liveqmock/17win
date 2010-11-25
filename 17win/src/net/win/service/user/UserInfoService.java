package net.win.service.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.win.BaseService;
import net.win.TaskMananger;
import net.win.UserLoginInfo;
import net.win.dao.BuyerDAO;
import net.win.dao.SellerDAO;
import net.win.dao.UserDAO;
import net.win.entity.BuyerEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;
import net.win.utils.ArithUtils;
import net.win.utils.Constant;
import net.win.utils.HttpB2CUtils;
import net.win.utils.MailUtils;
import net.win.utils.StrategyUtils;
import net.win.utils.StringUtils;
import net.win.utils.TotalUtils;
import net.win.utils.WinUtils;
import net.win.vo.BuyerVO;
import net.win.vo.SellerVO;
import net.win.vo.UserVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import sun.misc.BASE64Encoder;

@SuppressWarnings( { "unchecked" })
@Service("userInfoService")
public class UserInfoService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private SellerDAO sellerDAO;
	@Resource
	private BuyerDAO buyerDAO;
	@Resource
	private JavaMailSender mailSender;
	@Resource
	private FreeMarkerConfigurer freeMarkerCfj;

	/**
	 * 初始化找回密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initFindPassword() throws Exception {
		return "initFindPassword";
	}

	/**
	 * 查找密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateFindPassword(UserVO userVO) throws Exception {
		UserEntity userEntity = userDAO
				.uniqueResult(
						" from UserEntity as _u where _u.username=:username  or _u.telephone =:telephone ",
						new String[] { "username", "telephone" }, new Object[] {
								userVO.getUsername(), userVO.getUsername() });
		if (userEntity == null) {
			putAlertMsg("用户名或手机不存在！");
			return "updateFindPassword";
		}
		BASE64Encoder encoder = new BASE64Encoder();
		String username64 = encoder.encode(userEntity.getUsername().getBytes());
		String nowTime65 = encoder.encode((System.currentTimeMillis() + "")
				.getBytes());

		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		String content = basePath + "userManager/base!initFindPassword.php?u="
				+ username64 + "&t=" + nowTime65;

		userEntity.setStatusAndLastStatus("3");

		MailUtils.sendPasswordMail(mailSender, freeMarkerCfj, userEntity
				.getUsername(), userEntity.getEmail(), content);
		putAlertMsg("邮件已经发送到您的邮箱里面，请根据邮件找回密码！");
		return "updateFindPassword";
	}

	/**
	 * 推广
	 * 
	 * @return
	 * @throws Exception
	 */
	public String referee() throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		// 推广 奖金
		Integer refereeMoney = userEntity.getRefereeMoney();
		// 推广的会员数
		Long userCount = (Long) userDAO
				.uniqueResultObject(
						"select count(*) from UserEntity as _user where _user.referee.id=:userId ",
						new String[] { "userId" }, new Object[] { userEntity
								.getId() });
		// 推广的VIP会员数
		Long vipCount = (Long) userDAO
				.uniqueResultObject(
						"select count(*) from UserEntity as _user where _user.referee.id=:userId and _user.vipEnable=:vipEnable",
						new String[] { "userId", "vipEnable" }, new Object[] {
								userEntity.getId(), true });

		putByRequest("userCount", userCount);
		putByRequest("refereeMoney", refereeMoney);
		putByRequest("vipCount", vipCount);

		return "referee";
	}

	/**
	 * 更新卖号
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateSeller(UserVO userVO) throws Exception {
		SellerEntity sellerEntity = sellerDAO.get(Long
				.parseLong(getByParam("upadteSeller")));
		String sheng = getByParam("sheng").trim();
		String shi = getByParam("shi").trim();
		String qu = getByParam("qu").trim();
		String youbian = getByParam("youbian").trim();
		String result = sheng + " " + shi + " " + qu + " " + youbian;
		if (result.length() > 50) {
			putAlertMsg("地址和邮编长度加起来不能大于50！");
			return "updateSeller";
		}
		if (!StringUtils.isBlank(result)) {
			sellerEntity.setAddress(result);
		}
		putAlertMsg("修改成功！");
		return "updateSeller";
	}

	public String initExchange(UserVO userVO) throws Exception {
		putIndexShowType("7");
		return "initExchange";
	}

	/**
	 * 增加买家或卖家
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String insertSellerAndBuyer(UserVO userVO) throws Exception {
		String type = getByParam("type");
		String platformTypeParam = getByParam("platformTypeParam");

		UserEntity userEntity = getLoginUserEntity(userDAO);
		if ("1".equals(type)) {
			SellerEntity sellerEntity = userVO.getSeller();
			if (StringUtils.isBlank(sellerEntity.getName())) {
				putAlertMsg("名字不能为空！");
				putJumpPage("userInfoManager/info!initSellerAndBuyer.php");
				return JUMP;
			}
			// 判断当前卖号是否被注册过
			Boolean sellerExists = (0 == (Long) sellerDAO
					.uniqueResultObject(
							"select count(*) from  SellerEntity as _seller where _seller.name=:sellerName and _seller.type=:platformType",
							new String[] { "sellerName", "platformType" },
							new Object[] { sellerEntity.getName(),
									platformTypeParam }));
			if (!sellerExists) {
				putAlertMsg(sellerEntity.getName() + "已被使用！");
				putJumpPage("userInfoManager/info!initSellerAndBuyer.php");
				return JUMP;
			}

			// 判断当前的级别是否可以用友多个卖号
			Long count = (Long) sellerDAO
					.uniqueResultObject(
							"select count(*) from SellerEntity as _seller "
									+ "where _seller.user.id=:userId and type=:platformType",
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(),
									platformTypeParam });
			Integer sellerCountFlag = StrategyUtils.getSellerCount(
					getLoginUser().getVipType(), getLoginUser().getVipEnable(),
					userEntity.getVip());
			if (sellerCountFlag != -1) {
				if (count >= sellerCountFlag) {
					String platform = WinUtils
							.changeType2Platform(platformTypeParam);
					putAlertMsg("您当前在" + platform + "平台最多只能拥有"
							+ sellerCountFlag + "个账号");
					putJumpPage("userInfoManager/info!initSellerAndBuyer.php");
					return JUMP;
				}
			}

			String sheng = getByParam("sheng").trim();
			String shi = getByParam("shi").trim();
			String qu = getByParam("qu").trim();
			String youbian = getByParam("youbian").trim();
			String result = sheng + " " + shi + " " + qu + " " + youbian;
			if (result.length() > 50) {
				putAlertMsg("地址和邮编长度加起来不能大于50！");
				return "insertSellerAndBuyer";
			}
			if (!StringUtils.isBlank(result)) {
				sellerEntity.setAddress(result);
			}
			sellerEntity.setType(platformTypeParam);
			sellerEntity.setUser(userEntity);
			sellerDAO.save(sellerEntity);
		} else {
			BuyerEntity buyerEntity = userVO.getBuyer();
			if (StringUtils.isBlank(buyerEntity.getName())) {
				putAlertMsg("名字不能为空！");
				return "insertSellerAndBuyer";
			}
			Boolean sellerExists = (0 == (Long) buyerDAO
					.uniqueResultObject(
							"select count(*) from  BuyerEntity as _buyer where _buyer.name=:buyerName and _buyer.type=:platformType",
							new String[] { "buyerName", "platformType" },
							new Object[] { buyerEntity.getName(),
									platformTypeParam }));
			if (!sellerExists) {
				putAlertMsg(buyerEntity.getName() + "已被使用！");
				return "insertSellerAndBuyer";
			}
			Integer score = HttpB2CUtils.obtainCreditValue(buyerEntity
					.getName(), buyerEntity.getCreditURL(), platformTypeParam);
			if (score == -1) {
				putAlertMsg("您输入的地址有问题或地址和买号不同，如果有疑问请联系客户！");
				return "insertSellerAndBuyer";
			}
			if (score > 250) {
				putAlertMsg("您的买号级别过高，请换一个号！");
				return "insertSellerAndBuyer";
			}
			buyerEntity.setScore(score);
			buyerEntity.setType(platformTypeParam);
			buyerEntity.setUser(userEntity);
			buyerEntity.setEnable(true);
			buyerDAO.save(buyerEntity);
		}
		initSellerAndBuyer(userVO);
		putAlertMsg("添加成功！");
		return "insertSellerAndBuyer";
	}

	/**
	 * 初始化买家或卖家
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String initSellerAndBuyer(UserVO userVO) throws Exception {
		List<SellerEntity> sellers = sellerDAO.list(
				"from SellerEntity _s where _s.user.id=:userID", "userID",
				getLoginUser().getId());
		List<BuyerEntity> buyers = buyerDAO.list(
				"from BuyerEntity _b where _b.user.id=:userID", "userID",
				getLoginUser().getId());
		Map<String, List<SellerVO>> sellerResult = new TreeMap<String, List<SellerVO>>();
		sellerResult.put("1", new ArrayList<SellerVO>());
		sellerResult.put("2", new ArrayList<SellerVO>());
		sellerResult.put("3", new ArrayList<SellerVO>());

		Map<String, List<BuyerVO>> buyerResult = new TreeMap<String, List<BuyerVO>>();
		buyerResult.put("1", new ArrayList<BuyerVO>());
		buyerResult.put("2", new ArrayList<BuyerVO>());
		buyerResult.put("3", new ArrayList<BuyerVO>());
		// 解析
		if (sellers.size() > 0) {
			SellerVO sellerVO;
			for (SellerEntity sellerEntity : sellers) {
				sellerVO = new SellerVO();
				List<SellerVO> list = sellerResult.get(sellerEntity.getType());
				BeanUtils.copyProperties(sellerVO, sellerEntity);
				list.add(sellerVO);
			}
		}

		if (buyers.size() > 0) {
			BuyerVO buyerVO;
			for (BuyerEntity buyerEntity : buyers) {
				buyerVO = new BuyerVO();
				List<BuyerVO> list = buyerResult.get(buyerEntity.getType());
				BeanUtils.copyProperties(buyerVO, buyerEntity);
				list.add(buyerVO);
			}
		}

		if (getByParam("noSellerDirect") != null) {
			putAlertMsg("您还没有绑定卖号，请绑定！");
		}
		putByRequest("sellers", sellerResult);

		putByRequest("buyers", buyerResult);

		return "initSellerAndBuyer";
	}

	/**
	 * 发送激活号码
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateActiave(UserVO userVO) throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		String activeCode = getByParam("activeCode");
		String activeCodeOld = (String) getBySession(Constant.USER_ACTIVE_CODE_INFO);
		if (activeCode == null || !activeCodeOld.equals(activeCode)) {
			userEntity.setStatusAndLastStatus("1");
			putByRequest("activeCode", "3");
			putAlertMsg("激活码错误！");
			return "updateActiave";
		} else {
			userEntity.setStatusAndLastStatus("1");
			putAlertMsg("激活成功！快去体验吧！");
			putByRequest("activeCode", "1");
			updateUserLoginInfo(userEntity);
			return "updateActiave";
		}
	}

	/**
	 * 发送激活号码
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String sendActiave(UserVO userVO) throws Exception {
		putBySession(Constant.USER_ACTIVE_CODE_INFO, "123456");
		putAlertMsg("激活码已成功的发送到您手机上，请核对后天输入！");
		putByRequest("activeCode", "3");
		return "sendActiave";
	}

	/**
	 * 初始化激活码
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String initActiave(UserVO userVO) throws Exception {
		if (!getLoginUser().getStatus().equals("0")) {
			putByRequest("activeCode", "1");
		} else {
			putByRequest("activeCode", "2");
		}
		return "initActiave";
	}

	/**
	 * 购买发布点
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateBuyDot(UserVO userVO) throws Exception {
		String flag = getByParam("flag");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		String operaCode = userVO.getOperationCode();
		Double releaseDot = userVO.getReleaseDot();
		// 判断操作码
		if (!userEntity.getOpertationCode().equals(
				StringUtils.processPwd(operaCode))) {
			putAlertMsg("操作码不正确！");
			return "updateBuyDot";
		}
		if ("1".equals(flag)) {
			// 购买单个发布点
			if (userEntity.getMoney() >= Constant.getFabudianPrice()
					* releaseDot) {
				userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(),
						0.5 * releaseDot));
				userEntity.setReleaseDot(ArithUtils.add(userEntity
						.getReleaseDot(), releaseDot));
				logMoneyCapital(userDAO, 0 - (0.5 * releaseDot), "购买发布点",
						userEntity);
				logDotCapital(userDAO, releaseDot, "购买发布点", userEntity);
				if (userEntity.getReferee() != null) {
					userEntity.getReferee().setMoney(
							userEntity.getReferee().getMoney()
									+ (0.5 * releaseDot * 0.1));
				}

			} else {
				putDIV("您的钱不够支付购买" + releaseDot + "个发布点的费用，<a>点击此处充值！</a>");
			}
		} else if ("2".equals(flag)) {
			// 购买皇冠卡
			Double money = Constant.getHuangguanPrice();
			if (userEntity.getMoney() >= money) {
				userEntity.setMoney(ArithUtils
						.sub(userEntity.getMoney(), money));
				userEntity.setReleaseDot(ArithUtils.add(userEntity
						.getReleaseDot(), Constant.getHuangguanNumber()));
				logMoneyCapital(userDAO, 0 - money, "购买皇冠卡", userEntity);
				logDotCapital(userDAO, Constant.getHuangguanNumber()
						.doubleValue(), "购买皇冠卡", userEntity);
				if (userEntity.getReferee() != null) {
					userEntity.getReferee().setMoney(
							userEntity.getReferee().getMoney() + (money * 0.1));
				}
			} else {
				putDIV("您的钱不够支付购买皇冠卡，<a>点击此处充值！</a>");
			}

		} else if ("3".equals(flag)) {
			// 购买双钻卡
			Double money = Constant.getShuangzuanPrice();
			if (userEntity.getMoney() >= money) {
				userEntity.setMoney(ArithUtils
						.sub(userEntity.getMoney(), money));
				userEntity.setReleaseDot(ArithUtils.add(userEntity
						.getReleaseDot(), Constant.getShuangzuanNumber()));
				logMoneyCapital(userDAO, 0 - money, "购买双钻卡", userEntity);
				logDotCapital(userDAO, Constant.getShuangzuanNumber()
						.doubleValue(), "购买双钻卡", userEntity);
				if (userEntity.getReferee() != null) {
					userEntity.getReferee().setMoney(
							userEntity.getReferee().getMoney() + (money * 0.1));
				}
			} else {
				putDIV("您的钱不够支付购买双钻卡，<a>点击此处充值！</a>");
			}

		} else if ("4".equals(flag)) {
			// 购买一钻卡
			Double money = Constant.getZuanshiPrice();
			if (userEntity.getMoney() >= money) {
				userEntity.setMoney(ArithUtils
						.sub(userEntity.getMoney(), money));
				userEntity.setReleaseDot(ArithUtils.add(userEntity
						.getReleaseDot(), Constant.getZuanshiNumber()));
				logMoneyCapital(userDAO, 0 - money, "购买一钻卡", userEntity);
				logDotCapital(userDAO, Constant.getZuanshiNumber()
						.doubleValue(), "购买一钻卡", userEntity);
				if (userEntity.getReferee() != null) {
					userEntity.getReferee().setMoney(
							userEntity.getReferee().getMoney() + (money * 0.1));
				}
			} else {
				putDIV("您的钱不够支付购买一钻卡，<a>点击此处充值！</a>");
			}

		}
		if (getByRequest("div") == null) {
			updateUserLoginInfo(userEntity);
			putDIV("恭喜您，充值成功！<a>点击此处</a>跳转到淘宝互刷区！");
			putByRequest("executeFlag", "success");
		}
		return "updateBuyDot";
	}

	/**
	 * 发布点兑换
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateExchange(UserVO userVO) throws Exception {
		putIndexShowType("5");
		String flag = getByParam("flag");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		String operaCode = userVO.getOperationCode();
		// 判断操作码
		if (!userEntity.getOpertationCode().equals(
				StringUtils.processPwd(operaCode))) {
			putAlertMsg("操作码不正确！");
			return "updateExchange";
		}
		if ("1".equals(flag)) {
			// 兑换金额
			Double releaseDot = userVO.getReleaseDot();
			if (releaseDot < 10 || releaseDot > userEntity.getReleaseDot()) {
				WinUtils.throwIllegalityException("违法的操作，试图越过兑换发布点验证");
			}
			userEntity.setReleaseDot(ArithUtils.sub(userEntity.getReleaseDot(),
					releaseDot));
			userEntity.setMoney(ArithUtils.add(userEntity.getMoney(),
					ArithUtils.mul(releaseDot, 0.5)));

			logDotCapital(userDAO, 0 - releaseDot, "发布点兑换金额", userEntity);
			logMoneyCapital(userDAO, ArithUtils.mul(releaseDot, 0.5),
					"发布点兑换金额", userEntity);
		} else if ("2".equals(flag)) {
			// 赠送
			Double releaseDot = userVO.getReleaseDot();
			String username = userVO.getUsername();
			UserEntity touser = userDAO.uniqueResult(
					"from  UserEntity where username=:username", "username",
					username);
			if (releaseDot > userEntity.getReleaseDot()) {
				WinUtils.throwIllegalityException("违法的操作，试图越过兑换发布点验证");
			}
			if (username.equals(userEntity.getUsername())) {
				WinUtils.throwIllegalityException("违法的操作，试图越过兑换发布点验证");
			}
			if (touser == null) {
				putAlertMsg(username + "用户不存在");
				return "updateExchange";
			}
			userEntity.setReleaseDot(ArithUtils.sub(touser.getReleaseDot(),
					releaseDot));
			touser.setReleaseDot(ArithUtils.add(touser.getMoney(), releaseDot));

			logDotCapital(userDAO, 0 - releaseDot, "赠送发布点给"
					+ touser.getUsername(), userEntity);
			logDotCapital(userDAO, releaseDot, userEntity.getUsername()
					+ "赠送发布点给你的发布点", touser);
		} else if ("3".equals(flag)) {
			// 积分兑换
			Double releaseDot = userVO.getReleaseDot();
			if (releaseDot > userEntity.getReleaseDot()) {
				WinUtils.throwIllegalityException("违法的操作，试图越过兑换发布点验证");
			}
			if (releaseDot * 200 > userEntity.getConvertScore()) {
				putAlertMsg("兑换失败！您的积分不够" + releaseDot * 200);
			}
			userEntity.setReleaseDot(ArithUtils.add(userEntity.getReleaseDot(),
					releaseDot));
			Integer tempScore = ((Double) (releaseDot * 200)).intValue();
			userEntity
					.setConvertScore(userEntity.getConvertScore() - tempScore);

			logDotCapital(userDAO, releaseDot, "积分兑换发布点", userEntity);
		}
		updateUserLoginInfo(userEntity);
		putAlertMsg("操作成功！");
		putJumpPage("userInfoManager/info!initExchange.php");
		return JUMP;
	}

	/**
	 * 我的推广
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String myRefee(UserVO userVO) throws Exception {

		UserEntity newUserEntity = userDAO.get(getLoginUser().getId());

		UserEntity referee = newUserEntity.getReferee();
		if (referee == null) {
			putByRequest("referee", "无");
		} else {
			putByRequest("referee", referee.getUsername());
		}

		List<UserEntity> myReferees = newUserEntity.getMyReferees();
		List<UserInfoVO> result1 = new ArrayList<UserInfoVO>();
		for (UserEntity userEntity : myReferees) {
			UserInfoVO userInfoVO = new UserInfoVO(userEntity.getUsername(),
					userEntity.getQq(), userEntity.getReleaseTaskCount(),
					userEntity.getReceiveTaskCount(), userEntity
							.getRegisterTime(), userEntity.getConvertScore(),
					userEntity.getReleaseDot());
			result1.add(userInfoVO);
		}
		// 设置我的推广
		putByRequest("myRefees", result1);
		return "myRefee";
	}

	/**
	 * 修改密码
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updatePassword(UserVO userVO) throws Exception {
		UserLoginInfo userLoginInfo = getLoginUser();
		UserEntity userEntity = userDAO.get(userLoginInfo.getId());
		UserEntity newUserEntity = userVO.getUserEntity();
		if (!StringUtils.isBlank(newUserEntity.getLoginPassword())
				&& !StringUtils.isBlank(newUserEntity.getOpertationCode())
				&& newUserEntity.getLoginPassword().equals(
						newUserEntity.getOpertationCode())) {
			putAlertMsg("密码和操作码不能相同！");
		} else if (userEntity.getOpertationCode().equals(
				StringUtils.processPwd(userVO.getOperationCode()))) {
			if (!StringUtils.isBlank(newUserEntity.getLoginPassword())) {
				userEntity.setLoginPassword(StringUtils
						.processPwd(newUserEntity.getLoginPassword()));
			}
			if (!StringUtils.isBlank(newUserEntity.getOpertationCode())) {
				userEntity.setOpertationCode(StringUtils
						.processPwd(newUserEntity.getOpertationCode()));
			}
			putAlertMsg("修改成功！");
		} else {
			putAlertMsg("操作码不正确,请重新输入！");
		}
		userVO.setUserEntity(userEntity);
		return "initUpdatePassword";
	}

	/**
	 * 初始化跟新信息
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateInfo(UserVO userVO) throws Exception {
		UserLoginInfo userLoginInfo = getLoginUser();
		UserEntity userEntity = userDAO.get(userLoginInfo.getId());
		UserEntity oldUserEntity = userVO.getUserEntity();

		userEntity.setWw(oldUserEntity.getWw());

		userVO.setUserEntity(userEntity);
		putAlertMsg("更新成功!");
		return "updateInfo";

	}

	/**
	 * 初始化跟新信息
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String initUpdateInfo(UserVO userVO) throws Exception {
		UserLoginInfo userLoginInfo = getLoginUser();
		UserEntity userEntity = userDAO.get(userLoginInfo.getId());
		userVO.setUserEntity(userEntity);
		return "initUpdateInfo";
	}

	/**
	 * 初始化信息
	 */
	public String initUserInfo(UserVO userVO) throws Exception {
		UserLoginInfo userLoginInfo = getLoginUser();
		// 接手
		List<Object[]> tmpResult1 = (List<Object[]>) userDAO
				.list(
						" select   _rct.type,_rct.status , count(_rct.id) from  UserEntity   as _u    inner join  _u.receiveCreditTasks as _rct where   _u.id=:id group by  _rct.type, _rct.status  order by _rct.type,_rct.status",
						"id", userLoginInfo.getId());
		// 发布
		List<Object[]> tmpResult2 = (List<Object[]>) userDAO
				.list(
						"select    _rct.type,_rct.status , count(_rct.id) from  UserEntity   as _u    inner join  _u.releaseCreditTasks as _rct where   _u.id=:id group by   _rct.type, _rct.status  order by _rct.type,_rct.status",
						"id", userLoginInfo.getId());
		/**
		 * 买家
		 */
		String[][] result1 = new String[4][6];
		/**
		 * 卖家
		 */
		String[][] result2 = new String[4][9];
		for (String[] r : result1) {
			Arrays.fill(r, "0");
		}
		{
			// 设置result1的初始值
			result1[0][0] = "淘宝";
			result1[1][0] = "拍拍";
			result1[2][0] = "有啊";
			result1[3][0] = "合计";
		}
		for (String[] r : result2) {
			Arrays.fill(r, "0");
		}
		{
			// 设置result2的初始值
			result2[0][0] = "淘宝";
			result2[1][0] = "拍拍";
			result2[2][0] = "有啊";
			result2[3][0] = "合计";
		}
		if (tmpResult1.size() > 0) {
			for (int i = 0; i < tmpResult1.size(); i++) {
				Object[] objs = tmpResult1.get(i);
				for (int j = 0; j < objs.length; j++) {

					if ("1".equals(objs[0])) {
						// 淘宝的
						if (TaskMananger.STEP_TWO_STATUS.equals(objs[1])) {
							result1[0][1] = objs[2] + "";// 等我付款
						} else if (TaskMananger.STEP_THREE_STATUS
								.equals(objs[1])) {
							result1[0][2] = objs[2] + "";// 等待卖家发货
						} else if (TaskMananger.STEP_FOUR_STATUS
								.equals(objs[1])) {
							result1[0][3] = objs[2] + "";// 等待我好评
						} else if (TaskMananger.STEP_FIVE_STATUS
								.equals(objs[1])) {
							result1[0][4] = objs[2] + "";// 等待卖家好评
						}
					} else if ("2".equals(objs[0])) {
						// 拍拍
						if (TaskMananger.STEP_TWO_STATUS.equals(objs[1])) {
							result1[1][1] = objs[2] + "";// 等我付款
						} else if (TaskMananger.STEP_THREE_STATUS
								.equals(objs[1])) {
							result1[1][2] = objs[2] + "";// 等待卖家发货
						} else if (TaskMananger.STEP_FOUR_STATUS
								.equals(objs[1])) {
							result1[1][3] = objs[2] + "";// 等待我好评
						} else if (TaskMananger.STEP_FIVE_STATUS
								.equals(objs[1])) {
							result1[1][4] = objs[2] + "";// 等待卖家好评
						}
					} else if ("3".equals(objs[0])) {
						// 拍拍
						if (TaskMananger.STEP_TWO_STATUS.equals(objs[1])) {
							result1[2][1] = objs[2] + "";// 等我付款
						} else if (TaskMananger.STEP_THREE_STATUS
								.equals(objs[1])) {
							result1[2][2] = objs[2] + "";// 等待卖家发货
						} else if (TaskMananger.STEP_FOUR_STATUS
								.equals(objs[1])) {
							result1[2][3] = objs[2] + "";// 等待我好评
						} else if (TaskMananger.STEP_FIVE_STATUS
								.equals(objs[1])) {
							result1[2][4] = objs[2] + "";// 等待卖家好评
						}
					}
				}
			}
		}
		/**
		 * 卖家
		 */
		if (tmpResult2.size() > 0) {
			for (int i = 0; i < tmpResult2.size(); i++) {
				Object[] objs = tmpResult2.get(i);
				for (int j = 0; j < objs.length; j++) {

					if ("1".equals(objs[0])) {
						// 淘宝的
						if (TaskMananger.STEP_ONE_STATUS.equals(objs[1])) {
							result2[0][1] = objs[2] + "";// 等待接收
						} else if (TaskMananger.AUDIT_STATUS.equals(objs[1])) {
							result2[0][2] = objs[2] + "";// 等待审核
						} else if (TaskMananger.STEP_TWO_STATUS.equals(objs[1])) {
							result2[0][3] = objs[2] + ""; // 等待买家付款
						} else if (TaskMananger.STEP_THREE_STATUS
								.equals(objs[1])) {
							result2[0][4] = objs[2] + "";// 等待我发货
						} else if (TaskMananger.STEP_FOUR_STATUS
								.equals(objs[1])) {
							result2[0][5] = objs[2] + "";// 等待买家确认好评
						} else if (TaskMananger.STEP_FIVE_STATUS
								.equals(objs[1])) {
							result2[0][6] = objs[2] + "";// 等待我好评
						} else if (TaskMananger.TIMING_STATUS.equals(objs[1])) {
							result2[0][7] = objs[2] + "";// 定时任务
						}
					} else if ("2".equals(objs[0])) {
						// 拍拍
						if (TaskMananger.STEP_ONE_STATUS.equals(objs[1])) {
							result2[1][1] = objs[2] + "";// 等待接收
						} else if (TaskMananger.AUDIT_STATUS.equals(objs[1])) {
							result2[1][2] = objs[2] + "";// 等待审核
						} else if (TaskMananger.STEP_TWO_STATUS.equals(objs[1])) {
							result2[1][3] = objs[2] + ""; // 等待买家付款
						} else if (TaskMananger.STEP_THREE_STATUS
								.equals(objs[1])) {
							result2[1][4] = objs[2] + "";// 等待我发货
						} else if (TaskMananger.STEP_FOUR_STATUS
								.equals(objs[1])) {
							result2[1][5] = objs[2] + "";// 等待买家确认好评
						} else if (TaskMananger.STEP_FIVE_STATUS
								.equals(objs[1])) {
							result2[1][6] = objs[2] + "";// 等待我好评
						} else if (TaskMananger.TIMING_STATUS.equals(objs[1])) {
							result2[1][7] = objs[2] + "";// 定时任务
						}
					} else if ("3".equals(objs[0])) {
						// 有啊
						if (TaskMananger.STEP_ONE_STATUS.equals(objs[1])) {
							result2[2][1] = objs[2] + "";// 等待接收
						} else if (TaskMananger.AUDIT_STATUS.equals(objs[1])) {
							result2[2][2] = objs[2] + "";// 等待审核
						} else if (TaskMananger.STEP_TWO_STATUS.equals(objs[1])) {
							result2[2][3] = objs[2] + ""; // 等待买家付款
						} else if (TaskMananger.STEP_THREE_STATUS
								.equals(objs[1])) {
							result2[2][4] = objs[2] + "";// 等待我发货
						} else if (TaskMananger.STEP_FOUR_STATUS
								.equals(objs[1])) {
							result2[2][5] = objs[2] + "";// 等待买家确认好评
						} else if (TaskMananger.STEP_FIVE_STATUS
								.equals(objs[1])) {
							result2[2][6] = objs[2] + "";// 等待我好评
						} else if (TaskMananger.TIMING_STATUS.equals(objs[1])) {
							result2[2][7] = objs[2] + "";// 定时任务
						}
					}
				}
			}
		}
		// 合并数据
		TotalUtils.totalAllByInt(result1);
		TotalUtils.totalAllByInt(result2);

		// 短信数
		Long smsCount = (Long) userDAO
				.uniqueResultObject(
						"select count(*) from SmsEntity  _sms where _sms.fromUser.id=:userid and _sms.read=:read",
						new String[] { "userid", "read" }, new Object[] {
								userLoginInfo.getId(), false });
		putByRequest("smsCount", smsCount);
		putByRequest("sellTasks", result1);
		putByRequest("buyTasks", result2);
		return INPUT;
	}
}
