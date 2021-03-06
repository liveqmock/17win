package net.win.service.user;

import java.util.Date;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.UserDAO;
import net.win.entity.UserEntity;
import net.win.stragegy.ScoreStrategy;
import net.win.utils.Constant;
import net.win.utils.DateUtils;
import net.win.utils.MailUtils;
import net.win.utils.StringUtils;
import net.win.vo.UserVO;

import org.apache.struts2.ServletActionContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import sun.misc.BASE64Decoder;

@SuppressWarnings( { "unused", "deprecation" })
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
				userEntity.setStatusDesc("找回密码完成,修改成成，变回以前的状态！");
			}
			userEntity.setLoginPassword(StringUtils.processPwd(userVO
					.getUserEntity().getLoginPassword()));
			userEntity.setOpertationCode(StringUtils.processPwd(userVO
					.getUserEntity().getOpertationCode()));
			putAlertMsg("操作密码修改成功！");
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
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateLogin(UserVO userVO) throws Exception {
		final int LOGIN_SCORE = Constant.getLoginScore().intValue();
		if (userVO.getVerificationCode() == null
				|| !userVO.getVerificationCode().equals(
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
			// 今天第一次登录
			Date nowDate = new Date();
			String oldDateStr = DateUtils.format(userEntity.getLastLoginTime(),
					"yyyy-MM-dd");
			String newDateStr = DateUtils.format(nowDate, "yyyy-MM-dd");

			if (!oldDateStr.equals(newDateStr)) {
				userEntity.setUpgradeScore(userEntity.getUpgradeScore()
						+ LOGIN_SCORE);
				logScoreCapital(userDAO, 0.0 + LOGIN_SCORE, "每天登录一次",
						userEntity);
			}
			userEntity.setLastLoginTime(nowDate);
			// 通过你的宣传链接注册的会员积分每上升1000
			// 你的收益=N积分
			ScoreStrategy.updateRefreeScoreByScore(userDAO, userEntity);
			updateUserLoginInfo(userEntity);
			putJumpSelfPage("/userInfoManager/info!init.php");
			return JUMP;
		}
	}

	/**
	 * 登陆ADMIN
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateAdminLogin(UserVO userVO) throws Exception {
		if (userVO.getVerificationCode() == null
				|| !userVO.getVerificationCode().equals(
						getBySession(Constant.VERIFY_CODE))) {
			putAlertMsg("验证码不正确！");
			putJumpSelfPage("/admin");
			return JUMP;
		}
		UserEntity userEntity = userDAO
				.uniqueResult(
						"from UserEntity  as _u where _u.username=:username and _u.loginPassword=:loginPassword  and _u.type=:type",
						new String[] { "username", "loginPassword", "type" },
						new Object[] {
								userVO.getUserEntity().getUsername(),
								StringUtils.processPwd(userVO.getUserEntity()
										.getLoginPassword()), "1" });
		if (userEntity == null) {
			putAlertMsg("用户名或密码错误");
			putJumpSelfPage("/admin");
			return JUMP;
		} else {
			userEntity.setLastLoginTime(new Date());
			updateUserLoginInfo(userEntity);
			MailUtils.sendCommonMail(mailSender, freeMarkerCfj,
					"admin登录17win后台", DateUtils.format(new Date(),
							DateUtils.DATE_TIME_FORMAT)
							+ "登录后台系统", Constant.getXgjEmail());
			putJumpSelfPage("/admin/main.html");
			return JUMP;
		}
	}

	/**
	 * 登陆ADMIN
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateAdminPassword(UserVO userVO) throws Exception {
		String password = getByParam("password");
		String verificationCode = getByParam("verificationCode");
		putJumpSelfPage("/admin/adminInfo/index.jsp");
		if (verificationCode == null
				|| !verificationCode.equals(getBySession(Constant.VERIFY_CODE))) {
			putAlertMsg("验证码不正确！");
			return JUMP;
		} else {
			UserEntity userEntity = getLoginUserEntity(userDAO);
			userEntity.setLoginPassword(StringUtils.processPwd(password));
			putAlertMsg("修改密码成功！");
			return JUMP;
		}
	}

	/**
	 * 初始化注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initRegister(UserVO userVO) throws Exception {
		Long maxRegisterCount = (Long) userDAO
				.uniqueResultObject("select count(*) from UserEntity  ");
		if (maxRegisterCount.doubleValue() - 1 >= Constant
				.getMaxRegisterCount()) {
			putJumpSelfPage("index.html");
			putAlertMsg("允许注册的用户数已经最大了,如有问题联系客服！");
			return JUMP;
		}
		String username = getByParam("spreadUsername");
		if (username != null) {
			username = new String(username.getBytes("ISO-8859-1"), "GBK");
			putByRequest("username", username);
		}
		putTokenBySession();
		return "initRegister";
	}

	/**
	 * 初始化登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initLogin(UserVO userVO) throws Exception {
		putTokenBySession();
		return "initLogin";
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
		putByRequest("username", userEntity.getReferee().getUsername());
		if (vcode == null || !vcode.equals(userVO.getVerificationCode())) {
			putAlertMsg("验证码不正确！");
			return INPUT;
		}
		if (userEntity.getLoginPassword()
				.equals(userEntity.getOpertationCode())) {
			putAlertMsg("密码和操作码不能相同！");
			return INPUT;
		}
		if (userDAO.findUserByName(userEntity.getUsername().toLowerCase()) != null) {
			putAlertMsg("用户已经存在！");
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
		} else {
			userEntity.setReferee(userDAO.findUserByName(userEntity
					.getReferee().getUsername()));
		}
		try {
			MailUtils.sendCommonMail(mailSender, freeMarkerCfj, "注册成功",
					userEntity.getUsername() + "：欢迎您注册www.17win.com(一起赢)平台。",
					userEntity.getEmail());
		} catch (RuntimeException e) {
			putAlertMsg("您的邮箱有异常，请于管理员联系！");
			return INPUT;
		}

		MailUtils.sendCommonMail(mailSender, freeMarkerCfj, "有人注册", userEntity
				.getUsername()
				+ "在"
				+ DateUtils.format(new Date(), DateUtils.DATE_TIME_FORMAT)
				+ "注册", Constant.getXgjEmail());
		//
		userEntity.setLastLoginTime(new Date());
		userEntity.setUpgradeScore(userEntity.getUpgradeScore()
				+ Constant.getLoginScore().intValue());
		userDAO.save(userEntity);
		updateUserLoginInfo(userEntity);
		putAlertMsg("注册成功，现在请激活您的帐号！");
		putJumpSelfPage("userInfoManager/info!initActiave.php");
		return JUMP;
	}
}
