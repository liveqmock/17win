package net.win.service.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.UserLoginInfo;
import net.win.dao.AreaDAO;
import net.win.dao.CityDAO;
import net.win.dao.ProvinceDAO;
import net.win.dao.UserDAO;
import net.win.entity.ProvinceEntity;
import net.win.entity.UserEntity;
import net.win.utils.Constant;
import net.win.utils.MailUtils;
import net.win.utils.StringUtils;
import net.win.utils.UserLevelUtils;
import net.win.vo.UserVO;

import org.hibernate.Hibernate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import sun.misc.BASE64Decoder;

import com.sun.org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("unused")
@Service("userService")
public class UserService extends BaseService {
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
	 * 初始化找回密码
	 * 
	 * @param userVO
	 * @return
	 */
	public String updatefindPassword(UserVO userVO) throws Exception {
		UserEntity userEntity = userDAO.uniqueResult(
				"from UserEntity where username=:username", "username", userVO
						.getUserEntity().getUsername());
		if (!"3".equals(userEntity.getStatus())) {
			return "error";
		} else {
			// 如果状态是修改密码。那么就把状态换成上一次状态
			if ("3".equals(userEntity.getStatus())) {
				userEntity.setStatus(userEntity.getLastStatus());
			}
			userEntity.setStatus(userEntity.getLastStatus());
			userEntity.setLoginPassword(StringUtils.processPwd(userVO
					.getUserEntity().getLoginPassword()));
			putAlertMsg("密码修改成功！");
			return "findPasswordSuccess";
		}
	}

	/**
	 * 初始化找回密码
	 * 
	 * @param userVO
	 * @return
	 */
	public String initFindPassword(UserVO userVO) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		String username = new String(decoder.decodeBuffer(getByParam("u")));
		String time = new String(decoder.decodeBuffer(getByParam("t")));
		String status = (String) userDAO.uniqueResultObject(
				"select status from UserEntity where username=:username",
				"username", username);
		if (!"3".equals(status)) {
			return "error";
		} else {
			Long oldTime = Long.parseLong(time);
			Long newTime = System.currentTimeMillis();
			if ((newTime - oldTime) / 1000 / 60 <= 30) {
				userVO.getUserEntity().setUsername(username);
				return "initFindPassword";
			} else {
				return "error";
			}
		}
	}

	/**
	 * 手机激活
	 * 
	 * @return
	 * @throws Exception
	 */
	public String activateAccount(UserVO userVO) throws Exception {
		List<ProvinceEntity> provinces = provinceDAO.listAll();
		Hibernate.initialize(provinces);
		userVO.setProvinces(provinces);
		return "initRegister";
	}

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login(UserVO userVO) throws Exception {
		UserEntity userEntity = userDAO
				.uniqueResult(
						"from UserEntity  as _u where _u.username=:username and _u.loginPassword=:loginPassword",
						new String[] { "username", "loginPassword" },
						new Object[] {
								userVO.getUserEntity().getUsername(),
								StringUtils.processPwd(userVO.getUserEntity()
										.getLoginPassword()) });
		if (userEntity == null) {
			putAlertMsg("用户名或密码错误");
			userVO.setVerificationCode(null);
			return "inputLogin";
		} else {
			UserLoginInfo userLoginInfo = new UserLoginInfo();
			BeanUtils.copyProperties(userLoginInfo, userEntity);
			userLoginInfo.setLevel(UserLevelUtils.getLevel(userEntity
					.getUpgradeScore()));
			userLoginInfo.setLevelImg(UserLevelUtils.getLevelImg(userEntity
					.getUpgradeScore()));
			putLoginUser(userLoginInfo);
			return "loginSuccess";
		}
	}

	/**
	 * 初始化注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initRegister(UserVO userVO) throws Exception {
		List<ProvinceEntity> provinces = provinceDAO.listAll();
		Hibernate.initialize(provinces);
		userVO.setProvinces(provinces);
		return "initRegister";
	}

	/**
	 * 注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insertUser(UserVO userVO) throws Exception {
		String vcode = (String) getBySession(Constant.VERIFY_CODE);
		if (vcode == null || !vcode.equals(userVO.getVerificationCode())) {
			putAlertMsg("验证码不正确！");
			return INPUT;
		}
		UserEntity userEntity = userVO.getUserEntity();
		// 改变密码
		userEntity.setLoginPassword(StringUtils.processPwd(userEntity
				.getLoginPassword()));
		userEntity.setOpertationCode(StringUtils.processPwd(userEntity
				.getOpertationCode()));

		// 设置关系
		if (nullID(userEntity.getProvince())) {
			userEntity.setProvince(null);
		}
		if (nullID(userEntity.getCity())) {
			userEntity.setCity(null);
		}
		if (nullID(userEntity.getArea())) {
			userEntity.setArea(null);
		}
		// if (userEntity.getTaobaoUser().isNull()) {
		// userEntity.setTaobaoUser(null);
		// }
		// if (userEntity.getPaipaiUser().isNull()) {
		// userEntity.setPaipaiUser(null);
		// }
		// if (userEntity.getYouaUser().isNull()) {
		// userEntity.setYouaUser(null);
		// }
		// 推广人
		if (StringUtils.isBlank(userEntity.getReferee().getUsername())) {
			userEntity.setReferee(null);
		}
		userDAO.save(userEntity);
		MailUtils.sendRegisterMail(mailSender, freeMarkerCfj, userEntity
				.getUsername(), userEntity.getEmail());
		putDIV("注册成功,马上激活吧！");
		return "registerSuccess";
	}
}
