package net.win.service.user;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.UserLoginInfo;
import net.win.dao.AreaDAO;
import net.win.dao.CityDAO;
import net.win.dao.ProvinceDAO;
import net.win.dao.UserDAO;
import net.win.entity.UserEntity;
import net.win.utils.StringUtils;
import net.win.vo.UserVO;

import org.hibernate.Hibernate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.utility.StringUtil;

@SuppressWarnings( { "unused", "unchecked" })
@Service("userInfoService")
public class UserInfoService extends BaseService {
	@Resource
	private UserDAO userDAO;
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
		if (userEntity.getOpertationCode().equals(StringUtils
				.processPwd(userVO.getOperationCode()))) {
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
	 * 初始化修改密码
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String initUpdatePassword(UserVO userVO) throws Exception {
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

		// 设置关系
		if (!nullID(oldUserEntity.getProvince())) {
			userEntity.setProvince(oldUserEntity.getProvince());
		}
		if (!nullID(oldUserEntity.getCity())) {
			userEntity.setCity(oldUserEntity.getCity());
		}
		if (!nullID(oldUserEntity.getArea())) {
			userEntity.setArea(oldUserEntity.getArea());
		}
//		if (!oldUserEntity.getTaobaoUser().isNull()) {
//			userEntity.setTaobaoUser(oldUserEntity.getTaobaoUser());
//		}
//		if (!oldUserEntity.getPaipaiUser().isNull()) {
//			userEntity.setPaipaiUser(oldUserEntity.getPaipaiUser());
//		}
//		if (!oldUserEntity.getYouaUser().isNull()) {
//			userEntity.setYouaUser(oldUserEntity.getYouaUser());
//		}
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
		Hibernate.initialize(userEntity.getProvince());
		Hibernate.initialize(userEntity.getCity());
		Hibernate.initialize(userEntity.getArea());
		userVO.setUserEntity(userEntity);
		return "initUpdateInfo";
	}

	/**
	 * 初始化信息
	 */
	public String initUserInfo(UserVO userVO) throws Exception {
		UserLoginInfo userLoginInfo = getLoginUser();
		UserEntity userEntity = userDAO.get(userLoginInfo.getId());
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
		String[][] result1 = new String[3][4];
		String[][] result2 = new String[3][6];
		for (String[] r : result1) {
			Arrays.fill(r, "0");
		}
		{
			// 设置result1的初始值
			result1[0][0] = "淘宝";
			result1[1][0] = "拍拍";
			result1[2][0] = "有啊";
		}
		for (String[] r : result2) {
			Arrays.fill(r, "0");
		}
		{
			// 设置result2的初始值
			result2[0][0] = "淘宝";
			result2[1][0] = "拍拍";
			result2[2][0] = "有啊";
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
		putByRequest("sellTasks", result1);
		putByRequest("buyTasks", result2);
		return INPUT;
	}
}
