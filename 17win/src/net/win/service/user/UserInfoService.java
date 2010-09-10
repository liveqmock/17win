package net.win.service.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.UserLoginInfo;
import net.win.dao.AreaDAO;
import net.win.dao.BuyerDAO;
import net.win.dao.CityDAO;
import net.win.dao.ProvinceDAO;
import net.win.dao.SellerDAO;
import net.win.dao.UserDAO;
import net.win.dao.WithDrawalsDAO;
import net.win.entity.AreaEntity;
import net.win.entity.BuyerEntity;
import net.win.entity.CityEntity;
import net.win.entity.ProvinceEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;
import net.win.utils.ArithUtils;
import net.win.utils.StringUtils;
import net.win.utils.TotalUtils;
import net.win.vo.BuyerVO;
import net.win.vo.SellerVO;
import net.win.vo.UserVO;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.sun.org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings({"unchecked"})
@Service("userInfoService")
public class UserInfoService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private WithDrawalsDAO withDrawalsDAO;
	@Resource
	private SellerDAO sellerDAO;
	@Resource
	private BuyerDAO buyerDAO;
	@Resource
	private ProvinceDAO provinceDAO;
	@Resource
	private CityDAO cityDAO;
	@Resource
	private AreaDAO areaDAO;
	@Resource
	private JavaMailSender mailSender;
	@Resource
	private FreeMarkerConfigurer freeMarkerCfj;

	/**
	 * 买家或卖家
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateSellerAndBuyer(UserVO userVO) throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		List<SellerEntity> sellers = userVO.getSellers();
		List<BuyerEntity> buyers = userVO.getBuyers();

		userDAO.deleteBySQL("delete TB_SELLER where USER_ID_=:userId",
				new String[]{"userId"}, new Object[]{userEntity.getId()});
		userDAO.deleteBySQL("delete TB_BUYER where USER_ID_=:userId",
				new String[]{"userId"}, new Object[]{userEntity.getId()});

		for (SellerEntity sellerEntity : sellers) {
			if (nullID(sellerEntity.getProvince())) {
				sellerEntity.setProvince(null);
			}
			if (nullID(sellerEntity.getCity())) {
				sellerEntity.setCity(null);
			}
			if (nullID(sellerEntity.getArea())) {
				sellerEntity.setArea(null);
			}
		}
		userEntity.setSellers(sellers);
		userEntity.setBuyers(buyers);
		putAlertMsg("添加成功！");
		return "updateSellerAndBuyer";
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
		// 省
		List<ProvinceEntity> provinces = provinceDAO
				.list("from ProvinceEntity");
		
		putByRequest("provinces", provinces);
		// 解析
		if (sellers.size() > 0) {
			SellerVO sellerVO;
			for (SellerEntity sellerEntity : sellers) {
				sellerVO = new SellerVO();
				List<SellerVO> list = sellerResult.get(sellerEntity.getType());
				BeanUtils.copyProperties(sellerVO, sellerEntity);
				sellerVO.setProvinces(provinces);
				if (sellerEntity.getProvince() != null) {
					// 省
					List<CityEntity> citys = cityDAO.list(
							"from CityEntity  where province.id=:id", "id",
							sellerEntity.getProvince().getId());
					sellerVO.setCitys(citys);
					sellerVO.setProvinceID(sellerEntity.getProvince().getId());
				}
				if (sellerEntity.getCity() != null) {
					// 市
					sellerVO.setCityID(sellerEntity.getCity().getId());
					List<AreaEntity> areas = cityDAO.list(
							"from AreaEntity where  city.id=:id", "id",
							sellerEntity.getCity().getId());
					sellerVO.setAreas(areas);
				}
				if (sellerEntity.getArea() != null) {
					// 县
					sellerVO.setAreaID(sellerEntity.getArea().getId());
				}
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

		putByRequest("sellers", sellerResult);
		
		putByRequest("buyers", buyerResult);

		return "initSellerAndBuyer";
	}
	/**
	 * 激活账号
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateActiave(UserVO userVO) throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		userEntity.setStatus("1");
		putAlertMsg("激活成功！快去体验吧！");
		return "updateActiave";
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
			if (userEntity.getMoney() >= 0.5 * releaseDot) {
				userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(),
						0.5 * releaseDot));
				userEntity.setReleaseDot(ArithUtils.add(userEntity
						.getReleaseDot(), releaseDot));
			} else {
				putDIV("您的钱不够支付购买" + releaseDot + "个发布点的费用，<a>点击此处充值！</a>");
			}
		} else if ("2".equals(flag)) {
			// 购买皇冠卡
			Double money = 5000d;
			if (userEntity.getMoney() >= money) {
				userEntity.setMoney(ArithUtils
						.sub(userEntity.getMoney(), money));
				userEntity.setReleaseDot(ArithUtils.add(userEntity
						.getReleaseDot(), 100001));
			} else {
				putDIV("您的钱不够支付购买皇冠卡，<a>点击此处充值！</a>");
			}

		} else if ("3".equals(flag)) {
			// 购买双钻卡
			Double money = 275D;
			if (userEntity.getMoney() >= money) {
				userEntity.setMoney(ArithUtils
						.sub(userEntity.getMoney(), money));
				userEntity.setReleaseDot(ArithUtils.add(userEntity
						.getReleaseDot(), 550));
			} else {
				putDIV("您的钱不够支付购买双钻卡，<a>点击此处充值！</a>");
			}

		} else if ("4".equals(flag)) {
			// 购买皇一钻卡
			Double money = 135D;
			if (userEntity.getMoney() >= money) {
				userEntity.setMoney(ArithUtils
						.sub(userEntity.getMoney(), money));
				userEntity.setReleaseDot(ArithUtils.add(userEntity
						.getReleaseDot(), 270));
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
			// 兑换发布点
			Double releaseDot = userVO.getReleaseDot();
			if (releaseDot < 10 || releaseDot > userEntity.getReleaseDot()) {
				throwIllegalityException(userEntity.getUsername()
						+ ":违法的操作，试图越过兑换发布点验证");
			}
			userEntity.setReleaseDot(ArithUtils.sub(userEntity.getReleaseDot(),
					releaseDot));
			userEntity.setMoney(ArithUtils.add(userEntity.getMoney(),
					ArithUtils.mul(releaseDot, 0.5)));
		} else if ("2".equals(flag)) {
			// 赠送
			Double releaseDot = userVO.getReleaseDot();
			String username = userVO.getUsername();
			UserEntity touser = userDAO.uniqueResult(
					"fromo UserEntity where username=:username", "username",
					username);
			if (releaseDot > userEntity.getReleaseDot()) {
				throwIllegalityException(userEntity.getUsername()
						+ ":违法的操作，试图越过兑换发布点验证");
			}
			if (username.equals(userEntity.getUsername())) {
				throwIllegalityException(userEntity.getUsername()
						+ ":违法的操作，试图越过兑换发布点验证");
			}
			if (touser == null) {
				putAlertMsg(username + "用户不存在");
				return "updateExchange";
			}
			userEntity.setReleaseDot(ArithUtils.sub(touser.getReleaseDot(),
					releaseDot));
			touser.setReleaseDot(ArithUtils.add(touser.getMoney(), releaseDot));
		} else if ("3".equals(flag)) {
			// 赠送
			Double releaseDot = userVO.getReleaseDot();
			if (releaseDot > userEntity.getReleaseDot()) {
				throwIllegalityException(userEntity.getUsername()
						+ ":违法的操作，试图越过兑换发布点验证");
			}
			if (releaseDot * 200 > userEntity.getConvertScore()) {
				putAlertMsg("您的积分不够" + releaseDot * 200 + ",最多只能兑换"
						+ userEntity.getConvertScore() / 200 + "个发布点");
			}
			userEntity.setReleaseDot(ArithUtils.add(userEntity.getReleaseDot(),
					releaseDot));
			Integer tempScore = ((Double) (releaseDot * 200)).intValue();
			userEntity
					.setConvertScore(userEntity.getConvertScore() - tempScore);
		}
		updateUserLoginInfo(userEntity);
		putAlertMsg("操作成功！");
		return "updateExchange";
	}

	/**
	 * 修改密码
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String myRefee(UserVO userVO) throws Exception {

		UserEntity newUserEntity = userDAO.get(getLoginUser().getId());

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
		// 设置推广排行
		List<UserEntity> referees = userDAO
				.pageQuery(
						" from UserEntity _u  order by _u.spreadCount , _u.spreadScore",
						0, 15);
		List<UserInfoVO> result2 = new ArrayList<UserInfoVO>();
		for (UserEntity userEntity : referees) {
			UserInfoVO userInfoVO = new UserInfoVO(userEntity.getUsername(),
					userEntity.getSpreadCount(), userEntity.getSpreadScore());
			result2.add(userInfoVO);
		}
		// 推广排行
		putByRequest("referees", result2);
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

		// 比较以前的操作码
		if (userEntity.getOpertationCode().equals(
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
		List<Object[]> tmpResult1 = (List<Object[]>) userDAO
				.list(
						" select   _rct.type,_rct.status , count(_rct.id) from  UserEntity   as _u    inner join  _u.receiveCreditTasks as _rct where   _u.id=:id group by  _rct.type, _rct.status  order by _rct.type,_rct.status",
						"id", userLoginInfo.getId());
		List<Object[]> tmpResult2 = (List<Object[]>) userDAO
				.list(
						"select    _rct.type,_rct.status , count(_rct.id) from  UserEntity   as _u    inner join  _u.releaseCreditTasks as _rct where   _u.id=:id group by   _rct.type, _rct.status  order by _rct.type,_rct.status",
						"id", userLoginInfo.getId());
		/**
		 * 买家
		 */
		String[][] result1 = new String[4][5];
		String[][] result2 = new String[4][7];
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
						if ("1".equals(objs[1])) {
							result1[0][0] = objs[2] + "";
						} else if ("2".equals(objs[1])) {
							result1[0][1] = objs[2] + "";
						} else if ("3".equals(objs[1])) {
							result1[0][2] = objs[2] + "";
						}
					} else if ("2".equals(objs[0])) {
						// 拍拍
						if ("1".equals(objs[1])) {
							result1[1][0] = objs[2] + "";
						} else if ("2".equals(objs[1])) {
							result1[1][1] = objs[2] + "";
						} else if ("3".equals(objs[1])) {
							result1[1][2] = objs[2] + "";
						}

					} else if ("3".equals(objs[0])) {
						// 有啊
						if ("1".equals(objs[1])) {
							result1[2][0] = objs[2] + "";
						} else if ("2".equals(objs[1])) {
							result1[2][1] = objs[2] + "";
						} else if ("3".equals(objs[1])) {
							result1[2][2] = objs[2] + "";
						}
					}
				}
			}
		}
		/**
		 * 卖家
		 */
		if (tmpResult2.size() > 0) {
			for (int i = 0; i < tmpResult1.size(); i++) {
				Object[] objs = tmpResult1.get(i);
				for (int j = 0; j < objs.length; j++) {

					if ("1".equals(objs[0])) {
						// 淘宝的
						if ("1".equals(objs[1])) {
							result1[0][0] = objs[2] + "";
						} else if ("2".equals(objs[1])) {
							result1[0][1] = objs[2] + "";
						} else if ("3".equals(objs[1])) {
							result1[0][2] = objs[2] + "";
						} else if ("4".equals(objs[1])) {
							result1[0][3] = objs[2] + "";
						} else if ("5".equals(objs[1])) {
							result1[0][4] = objs[2] + "";
						}
					} else if ("2".equals(objs[0])) {
						// 拍拍
						if ("1".equals(objs[1])) {
							result1[1][0] = objs[2] + "";
						} else if ("2".equals(objs[1])) {
							result1[1][1] = objs[2] + "";
						} else if ("3".equals(objs[1])) {
							result1[1][2] = objs[2] + "";
						} else if ("4".equals(objs[1])) {
							result1[1][3] = objs[2] + "";
						} else if ("5".equals(objs[1])) {
							result1[1][4] = objs[2] + "";
						}

					} else if ("3".equals(objs[0])) {
						// 有啊
						if ("1".equals(objs[1])) {
							result1[2][0] = objs[2] + "";
						} else if ("2".equals(objs[1])) {
							result1[2][1] = objs[2] + "";
						} else if ("3".equals(objs[1])) {
							result1[2][2] = objs[2] + "";
						} else if ("4".equals(objs[1])) {
							result1[2][3] = objs[2] + "";
						} else if ("5".equals(objs[1])) {
							result1[2][4] = objs[2] + "";
						}
					}
				}
			}
		}
		// 合并数据
		TotalUtils.totalAllByInt(result1);
		TotalUtils.totalAllByInt(result2);
		putByRequest("sellTasks", result1);
		putByRequest("buyTasks", result2);
		return INPUT;
	}
}
