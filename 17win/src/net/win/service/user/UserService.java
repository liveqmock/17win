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
import net.win.vo.UserVO;

import org.hibernate.Hibernate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

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
	 * 查找密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findPassword(UserVO userVO) throws Exception {
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
			putLoginUser( userLoginInfo);
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
		// 可以转换的积分
		userEntity.setConvertScore(0);
		// 升级的积分
		userEntity.setUpgradeScore(0);
		// 注册时间
		userEntity.setRegisterTime(new Date());
		// 发布点
		userEntity.setReleaseDot(2.0);
		// 当前级别是0（没级别）
		userEntity.setLevel(0);
		// 状态，没激活
		userEntity.setStatus("0");
		// 钱
		userEntity.setMoney(0.0);

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
		if (nullID(userEntity.getTaobaoUser())) {
			userEntity.setTaobaoUser(null);
		}
		if (nullID(userEntity.getPaipaiUser())) {
			userEntity.setPaipaiUser(null);
		}
		if (nullID(userEntity.getYouaUser())) {
			userEntity.setYouaUser(null);
		}
		if (nullID(userEntity.getReferee())) {
			userEntity.setReferee(null);
		}
		userDAO.save(userEntity);
		MailUtils.sendRegisterMail(mailSender,freeMarkerCfj, userEntity.getUsername(),
				userEntity.getEmail());
		putDIV("注册成功,马上激活吧！");
		return "registerSuccess";
	}
}
