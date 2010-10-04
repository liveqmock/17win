package net.win.service.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.UserDAO;
import net.win.entity.UserEntity;
import net.win.entity.VipEntity;
import net.win.utils.Constant;
import net.win.utils.MailUtils;
import net.win.utils.StringUtils;
import net.win.vo.UserVO;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import sun.misc.BASE64Decoder;

@SuppressWarnings("unused")
@Service("userService")
public class UserService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private JavaMailSender mailSender;
	@Resource
	private FreeMarkerConfigurer freeMarkerCfj;

	/**
	 * 注销
	 * 
	 * @param userVO
	 * @return
	 */
	public String loginOut(UserVO userVO) throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return "loginOut";
	}

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
			if (userEntity.getOpertationCode().equals(
					StringUtils.processPwd(userVO.getUserEntity()
							.getLoginPassword()))) {
				getResponse().sendRedirect(
						"base!initFindPassword.php" + getByParam("preURL")
								+ "&error=sameCode");
				return null;
			}
			// 如果状态是修改密码。那么就把状态换成上一次状态
			if ("3".equals(userEntity.getStatus())) {
				userEntity.setStatusAndLastStatus(userEntity.getLastStatus());
			}
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
				if (getByParam("error") != null)
					putAlertMsg("密码和操作码不能相同！");
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
		return "initRegister";
	}

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateLogin(UserVO userVO) throws Exception {
		if (!userVO.getVerificationCode().equals(
				getBySession(Constant.VERIFY_CODE))) {
			putAlertMsg("验证码不正确！");
			userVO.setVerificationCode(null);
			return "inputLogin";
		}
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
			userEntity.setLastLoginTime(new Date());
			// 设置VIP
			VipEntity vip = userEntity.getVip();
			if (vip != null) {
				getLoginUser().setVipType(userEntity.getVip().getType());
			}
			updateUserLoginInfo(userEntity);
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
		UserEntity userEntity = userVO.getUserEntity();
		if (vcode == null || !vcode.equals(userVO.getVerificationCode())) {
			putAlertMsg("验证码不正确！");
			return INPUT;
		}
		if (userEntity.getLoginPassword()
				.equals(userEntity.getOpertationCode())) {
			putAlertMsg("密码和操作码不能相同！");
			return INPUT;
		}
		// 改变密码
		userEntity.setLoginPassword(StringUtils.processPwd(userEntity
				.getLoginPassword()));
		userEntity.setOpertationCode(StringUtils.processPwd(userEntity
				.getOpertationCode()));

		// 推广人
		if (StringUtils.isBlank(userEntity.getReferee().getUsername())) {
			userEntity.setReferee(null);
		}
		userDAO.save(userEntity);
		try {
			MailUtils.sendRegisterMail(mailSender, freeMarkerCfj, userEntity
					.getUsername(), userEntity.getEmail());
		} catch (RuntimeException e) {
			putAlertMsg("注册失败，您的邮箱不正确！");
			return INPUT;
		}
		putDIV("注册成功,马上激活吧！");
		return "registerSuccess";
	}
}
