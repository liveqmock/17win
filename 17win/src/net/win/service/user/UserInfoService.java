package net.win.service.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import net.win.utils.Constant;
import net.win.utils.HttpB2CUtils;
import net.win.utils.MailUtils;
import net.win.utils.MsgUtils;
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

		try {
			MailUtils.sendPasswordMail(mailSender, freeMarkerCfj, userEntity
					.getUsername(), userEntity.getEmail(), content);
		} catch (Exception e) {
			putJumpSelfPage("userInfoManager/info!findPassword.php");
			putAlertMsg("发送邮件失败，请确认您的邮件支持STMP,POP3。如有问题联系客户！");
			return JUMP;
		}

		userEntity.setStatusAndLastStatus("3");
		userEntity.setStatusDesc("找回密码中！");
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
		// 推广的会员数
		Long userCount = (Long) userDAO
				.uniqueResultObject(
						"select count(*) from UserEntity as _user where _user.referee.id=:userId ",
						new String[] { "userId" }, new Object[] { userEntity
								.getId() });
		putByRequest("userCount", userCount);
		// 通过你的宣传链接注册的会员积分每上升1000 ，你的收益=100积分
		putByRequest("score1000Refree", Constant.getScore1000Refree());
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


	/**
	 * 增加买家或卖家
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String insertSellerAndBuyer(UserVO userVO) throws Exception {
		putJumpSelfPage("userInfoManager/info!initSellerAndBuyer.php");
		String type = getByParam("type");
		String platformTypeParam = getByParam("platformTypeParam");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		//卖号
		if ("1".equals(type)) {
			SellerEntity sellerEntity = userVO.getSeller();
			if (StringUtils.isBlank(sellerEntity.getName())) {
				putAlertMsg("名字不能为空！");
				return JUMP;
			}
			// 判断当前卖号是否被注册过
			Boolean sellerExists = (0 == (Long) sellerDAO
					.uniqueResultObject(
							"select count(*) from  SellerEntity as _seller where _seller.name=:sellerName and _seller.type=:platformType and _seller.user.id=:userID",
							new String[] { "sellerName", "platformType",
									"userID" }, new Object[] {
									sellerEntity.getName(), platformTypeParam,
									userEntity.getId() }));
			if (!sellerExists) {
				putAlertMsg(sellerEntity.getName() + "已被使用！");
				return JUMP;
			}

			Boolean buyerExists = (0 == (Long) buyerDAO
					.uniqueResultObject(
							"select count(*) from  BuyerEntity as _buyer where _buyer.name=:buyerName and _buyer.type=:platformType and _buyer.user.id=:userID",
							new String[] { "buyerName", "platformType",
									"userID" }, new Object[] {
									sellerEntity.getName(), platformTypeParam,
									userEntity.getId() }));
			if (!buyerExists) {
				putAlertMsg("不能绑定和买号相同的卖号！");
				return JUMP;
			}
			// 判断当前的级别是否可以拥有更多的卖号
			Long count = (Long) sellerDAO
					.uniqueResultObject(
							"select count(*) from SellerEntity as _seller "
									+ "where _seller.user.id=:userId and type=:platformType ",
							new String[] { "userId", "platformType" },
							new Object[] { getLoginUser().getId(),
									platformTypeParam });
			Integer sellerCountFlag = StrategyUtils.getSellerCount();
			if (sellerCountFlag != -1) {
				if (count >= sellerCountFlag) {
					String platform = WinUtils
							.changeType2Platform(platformTypeParam);
					putAlertMsg("您当前在" + platform + "平台最多只能拥有"
							+ sellerCountFlag + "个账号");
					return JUMP;
				}
			}
			sellerEntity = HttpB2CUtils.getSellerInfo(sellerEntity,
					platformTypeParam);
			//判断是否没找到信息
			if (sellerEntity == null) {
				putAlertMsg("您输入的帐号错误，出现这样的原因可能是：\\n1:用户不存在（注意大小写,拍拍必须为QQ号码！）。\\n2:您是商城卖家（本平台只为小卖家开放，不为商城卖家开发！）。\\n3:如还有问题请联系客户。");
				return JUMP;
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
				return JUMP;
			}
			Boolean sellerExists = (0 == (Long) buyerDAO
					.uniqueResultObject(
							"select count(*) from  SellerEntity as _seller where _seller.name=:sellerName and _seller.type=:platformType and _seller.user.id=:userID",
							new String[] { "sellerName", "platformType",
									"userID" }, new Object[] {
									buyerEntity.getName(), platformTypeParam,
									userEntity.getId() }));

			if (!sellerExists) {
				putAlertMsg("不能绑定和卖号相同的买号！");
				return JUMP;
			}
			Boolean buyerExists = (0 == (Long) buyerDAO
					.uniqueResultObject(
							"select count(*) from  BuyerEntity as _buyer where _buyer.name=:buyerName and _buyer.type=:platformType and _buyer.user.id=:userID",
							new String[] { "buyerName", "platformType",
									"userID" }, new Object[] {
									buyerEntity.getName(), platformTypeParam,
									userEntity.getId() }));
			if (!buyerExists) {
				putAlertMsg(buyerEntity.getName() + "已被使用！");
				return JUMP;
			}
			//添加买号
			buyerEntity = HttpB2CUtils.getBuyerInfo(buyerEntity,
					platformTypeParam, true);

			if (buyerEntity == null) {
				putAlertMsg("添加失败，可能由一下原因造成：\\n\\n1:用户不存在（注意大小写，拍拍必须为QQ号码！）。\\n2:信誉值过高（淘宝最高为250，拍拍为100）。\\n3:添加的号码已是掌柜。\\n4:如还有问题请联系客户。");
				return JUMP;
			}
			buyerEntity.setType(platformTypeParam);
			buyerEntity.setUser(userEntity);
			buyerEntity.setEnable(true);
			buyerDAO.save(buyerEntity);
		}
		putAlertMsg("添加成功！");
		return JUMP;
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

		Map<String, List<BuyerVO>> buyerResult = new TreeMap<String, List<BuyerVO>>();
		buyerResult.put("1", new ArrayList<BuyerVO>());
		buyerResult.put("2", new ArrayList<BuyerVO>());
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
	 * 激活
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
			userEntity.setStatusDesc("已激活");
			updateUserLoginInfo(userEntity);
			putAlertMsg("激活成功！快去体验吧！");
			putJumpSelfPage("userInfoManager/info!initActiave.php");
			return JUMP;
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
		if (getLoginUser().getSendMsgTOValiate()) {
			putAlertMsg("已经发送，不要重复发送，或则重新登录激活！");
			putJumpSelfPage("userInfoManager/info!initActiave.php");
			return JUMP;
		}
		getLoginUser().setSendMsgTOValiate(true);
		UserEntity user = getLoginUserEntity(userDAO);
		Random random = new Random();
		String value = random.nextInt(1000000) + "";
		putBySession(Constant.USER_ACTIVE_CODE_INFO, value);
		if (!user.getStatus().equals("0")) {
			putAlertMsg("已经激活！");
			putJumpSelfPage("userInfoManager/info!initActiave.php");
			return JUMP;
		}
		MsgUtils.sendMsg(user.getTelephone(), user.getUsername()
				+ "您好，这里是来至www.17win.net的信息(一起赢刷钻网)，您的激活码是：" + value + "");
		putAlertMsg("激活码已成功的发送到您手机上，请查看后输入！");
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
		Boolean senfFlag = getLoginUser().getSendMsgTOValiate();
		if (getLoginUser().getStatus().equals("0") && senfFlag) {
			putByRequest("activeCode", "3");
			return "initActiave";
		}
		if (!getLoginUser().getStatus().equals("0")) {
			putByRequest("activeCode", "1");
		} else {
			putByRequest("activeCode", "2");
		}
		return "initActiave";
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
							.getRegisterTime());
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
	 * 刷新用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String refreshUser(UserVO userVO) throws Exception {
		UserLoginInfo userLoginInfo = getLoginUser();
		UserEntity userEntity = userDAO.get(userLoginInfo.getId());
		updateUserLoginInfo(userEntity);
		return "refreshUser";
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
		String[][] result1 = new String[3][7];
		/**
		 * 卖家
		 */
		String[][] result2 = new String[3][10];
		for (String[] r : result1) {
			Arrays.fill(r, "0");
		}
		{
			// 设置result1的初始值
			result1[0][0] = "淘宝";
			result1[1][0] = "拍拍";
			//			result1[2][0] = "有啊";
			result1[2][0] = "合计";
		}
		for (String[] r : result2) {
			Arrays.fill(r, "0");
		}
		{
			// 设置result2的初始值
			result2[0][0] = "淘宝";
			result2[1][0] = "拍拍";
			//			result2[2][0] = "有啊";
			result2[2][0] = "合计";
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
						} else if (TaskMananger.STEP_SIX_STATUS.equals(objs[1])) {
							result1[0][5] = objs[2] + "";// 完成的
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
						} else if (TaskMananger.STEP_SIX_STATUS.equals(objs[1])) {
							result1[1][5] = objs[2] + "";// 完成的
						}
					}
					//					else if ("3".equals(objs[0])) {
					//						// 拍拍
					//						if (TaskMananger.STEP_TWO_STATUS.equals(objs[1])) {
					//							result1[2][1] = objs[2] + "";// 等我付款
					//						} else if (TaskMananger.STEP_THREE_STATUS
					//								.equals(objs[1])) {
					//							result1[2][2] = objs[2] + "";// 等待卖家发货
					//						} else if (TaskMananger.STEP_FOUR_STATUS
					//								.equals(objs[1])) {
					//							result1[2][3] = objs[2] + "";// 等待我好评
					//						} else if (TaskMananger.STEP_FIVE_STATUS
					//								.equals(objs[1])) {
					//							result1[2][4] = objs[2] + "";// 等待卖家好评
					//						} else if (TaskMananger.STEP_SIX_STATUS.equals(objs[1])) {
					//							result1[2][5] = objs[2] + "";// 完成的
					//						}
					//					}
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
						if (TaskMananger.TIMING_STATUS.equals(objs[1])) {
							result2[0][1] = objs[2] + "";// 定时任务
						} else if (TaskMananger.STEP_ONE_STATUS.equals(objs[1])) {
							result2[0][2] = objs[2] + "";// 等待接手
						} else if (TaskMananger.AUDIT_STATUS.equals(objs[1])) {
							result2[0][3] = objs[2] + "";// 等待审核
						} else if (TaskMananger.STEP_TWO_STATUS.equals(objs[1])) {
							result2[0][4] = objs[2] + ""; // 等待买家付款
						} else if (TaskMananger.STEP_THREE_STATUS
								.equals(objs[1])) {
							result2[0][5] = objs[2] + "";// 等待我发货
						} else if (TaskMananger.STEP_FOUR_STATUS
								.equals(objs[1])) {
							result2[0][6] = objs[2] + "";// 等待买家确认好评
						} else if (TaskMananger.STEP_FIVE_STATUS
								.equals(objs[1])) {
							result2[0][7] = objs[2] + "";// 等待我好评
						} else if (TaskMananger.STEP_SIX_STATUS.equals(objs[1])) {
							result2[0][8] = objs[2] + "";// 完成的
						}
					} else if ("2".equals(objs[0])) {
						// 拍拍
						if (TaskMananger.TIMING_STATUS.equals(objs[1])) {
							result2[1][1] = objs[2] + "";// 定时任务
						} else if (TaskMananger.STEP_ONE_STATUS.equals(objs[1])) {
							result2[1][2] = objs[2] + "";// 等待接手
						} else if (TaskMananger.AUDIT_STATUS.equals(objs[1])) {
							result2[1][3] = objs[2] + "";// 等待审核
						} else if (TaskMananger.STEP_TWO_STATUS.equals(objs[1])) {
							result2[1][4] = objs[2] + ""; // 等待买家付款
						} else if (TaskMananger.STEP_THREE_STATUS
								.equals(objs[1])) {
							result2[1][5] = objs[2] + "";// 等待我发货
						} else if (TaskMananger.STEP_FOUR_STATUS
								.equals(objs[1])) {
							result2[1][6] = objs[2] + "";// 等待买家确认好评
						} else if (TaskMananger.STEP_FIVE_STATUS
								.equals(objs[1])) {
							result2[1][7] = objs[2] + "";// 等待我好评
						} else if (TaskMananger.STEP_SIX_STATUS.equals(objs[1])) {
							result2[1][8] = objs[2] + "";// 完成的
						}
					}

					//					else if ("3".equals(objs[0])) {
					//						// 有啊
					//						if (TaskMananger.TIMING_STATUS.equals(objs[1])) {
					//							result2[2][1] = objs[2] + "";// 定时任务
					//						} else if (TaskMananger.STEP_ONE_STATUS.equals(objs[1])) {
					//							result2[2][2] = objs[2] + "";// 等待接手
					//						} else if (TaskMananger.AUDIT_STATUS.equals(objs[1])) {
					//							result2[2][3] = objs[2] + "";// 等待审核
					//						} else if (TaskMananger.STEP_TWO_STATUS.equals(objs[1])) {
					//							result2[2][4] = objs[2] + ""; // 等待买家付款
					//						} else if (TaskMananger.STEP_THREE_STATUS
					//								.equals(objs[1])) {
					//							result2[2][5] = objs[2] + "";// 等待我发货
					//						} else if (TaskMananger.STEP_FOUR_STATUS
					//								.equals(objs[1])) {
					//							result2[2][6] = objs[2] + "";// 等待买家确认好评
					//						} else if (TaskMananger.STEP_FIVE_STATUS
					//								.equals(objs[1])) {
					//							result2[2][7] = objs[2] + "";// 等待我好评
					//						} else if (TaskMananger.STEP_SIX_STATUS.equals(objs[1])) {
					//							result2[1][8] = objs[2] + "";// 完成的
					//						}
					//					}
				}
			}
		}
		// 合并数据
		TotalUtils.totalAllByInt(result1);
		TotalUtils.totalAllByInt(result2);

		// 短信数
		Long smsCount = (Long) userDAO
				.uniqueResultObject(
						"select count(*) from SmsEntity  _sms where _sms.toUser.id=:userid and _sms.read=:read",
						new String[] { "userid", "read" }, new Object[] {
								userLoginInfo.getId(), false });
		putByRequest("smsCount", smsCount);
		putByRequest("sellTasks", result1);
		putByRequest("buyTasks", result2);
		return INPUT;
	}
}
