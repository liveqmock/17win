package net.win;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.win.utils.Constant;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	/**
	 * 不包含的用户登录路径
	 */
	private static final Set<String> EXINCLUDE_USER_LOGIN_PATH = new HashSet<String>();
	static {
		EXINCLUDE_USER_LOGIN_PATH.add("userManager/base!initLogin.php");
		EXINCLUDE_USER_LOGIN_PATH.add("userManager/base!initRegister.php");
		EXINCLUDE_USER_LOGIN_PATH.add("userManager/base!initRegister.php");
		EXINCLUDE_USER_LOGIN_PATH.add("userManager/base!register.php");
		EXINCLUDE_USER_LOGIN_PATH.add("userManager/base!initFindPassword.php");
		EXINCLUDE_USER_LOGIN_PATH.add("userManager/base!findPassword.php");
		EXINCLUDE_USER_LOGIN_PATH.add("menuManager/menu!toIndex.php");
		EXINCLUDE_USER_LOGIN_PATH.add("userManager/base!login.php");
		EXINCLUDE_USER_LOGIN_PATH.add("ajaxManager/ajax!findPassword.php");
		EXINCLUDE_USER_LOGIN_PATH.add("ajaxManager/ajax!userExists.php");
		EXINCLUDE_USER_LOGIN_PATH.add("ajaxManager/ajax!phoneExists.php");
		EXINCLUDE_USER_LOGIN_PATH.add("ajaxManager/ajax!emailExists.php");
		EXINCLUDE_USER_LOGIN_PATH.add("adminManager/admin!login.php");
		EXINCLUDE_USER_LOGIN_PATH
				.add("logisticsManager/logistics!queryLogisticsLog.php");

		EXINCLUDE_USER_LOGIN_PATH
				.add("adminNewsManager/adminNews!showHelp.php");
		EXINCLUDE_USER_LOGIN_PATH
				.add("adminNewsManager/adminNews!listNews.php");
		EXINCLUDE_USER_LOGIN_PATH
				.add("adminNewsManager/adminNews!detailNews.php");
		EXINCLUDE_USER_LOGIN_PATH.add("userManager/base!getLoginUser.php");
		EXINCLUDE_USER_LOGIN_PATH
				.add("shuakeManager/shuake!initShuakeIndex.php");
		EXINCLUDE_USER_LOGIN_PATH
				.add("userInfoManager/info!initFindPassword.php");
		EXINCLUDE_USER_LOGIN_PATH.add("userInfoManager/info!findPassword.php");
		EXINCLUDE_USER_LOGIN_PATH.add("userManager/base!adminLogin.php");
	}
	/**
	 * 不包含的激活路径
	 */
	private static final Set<String> EXINCLUDE_ACTIVATE_PATH = new HashSet<String>();
	static {
		EXINCLUDE_ACTIVATE_PATH.add("userInfoManager/info!initActiave.php");
		EXINCLUDE_ACTIVATE_PATH.add("taskManager/task!initTask.php");
		EXINCLUDE_ACTIVATE_PATH
				.add("commonManager/common!activateOperattionCode.php");
		EXINCLUDE_ACTIVATE_PATH.add("userInfoManager/info!actiave.php");
		EXINCLUDE_ACTIVATE_PATH.add("userInfoManager/info!referee.php");
		EXINCLUDE_ACTIVATE_PATH.add("userInfoManager/info!myRefee.php");

		EXINCLUDE_ACTIVATE_PATH.add("userInfoManager/info!updateActiave.php");
		EXINCLUDE_ACTIVATE_PATH.add("userInfoManager/info!sendActiave.php");
		EXINCLUDE_ACTIVATE_PATH.add("userInfoManager/info!initActiave.php");
		EXINCLUDE_ACTIVATE_PATH.add("usermanager/base!getloginuser.php");
		EXINCLUDE_ACTIVATE_PATH.add("userManager/base!loginOut.php");
		EXINCLUDE_ACTIVATE_PATH
				.add("userInfoManager/info!initUpdatePassword.php");
		EXINCLUDE_ACTIVATE_PATH.add("userInfoManager/info!init.php");
		EXINCLUDE_ACTIVATE_PATH.add("userInfoManager/info!initUpdateInfo.php");
		EXINCLUDE_ACTIVATE_PATH.add("userInfoManager/info!refreshUser.php");

	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if (validateUserLogin(invocation)) {
			if (validateUserActive(invocation)) {
				return invocation.invoke();
			} else {

				ServletActionContext.getRequest().setAttribute("jumpPageType",
						"selfPage");
				ServletActionContext.getRequest().setAttribute("msg",
						"<script>alert('" + "您的账号还没激活！" + "');</script>");
				ServletActionContext.getRequest().setAttribute("jump",
						"userInfoManager/info!initActiave.php");
				return "jump";
			}

		} else {
			ServletActionContext.getRequest().setAttribute("jumpPageType",
					"selfPage");
			ServletActionContext.getRequest().setAttribute("msg",
					"<script>alert('" + "没有登录,请登录！" + "');</script>");
			ServletActionContext.getRequest().setAttribute("jump",
					"userManager/base!initLogin.php");
			return "jump";
		}

	}

	/**
	 * 验证用户登录
	 * 
	 * @param invocation
	 */
	private Boolean validateUserLogin(ActionInvocation invocation) {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserLoginInfo userLoginInfo = (UserLoginInfo) request.getSession()
				.getAttribute(Constant.USER_LOGIN_INFO);
		for (String url : EXINCLUDE_USER_LOGIN_PATH) {
			if (request.getRequestURI().toLowerCase().endsWith(
					url.toLowerCase())) {
				return true;
			}
		}
		if (userLoginInfo != null) {
			return true;
		}
		return false;
	}

	/**
	 * 验证是否激活
	 * 
	 * @param invocation
	 */
	private Boolean validateUserActive(ActionInvocation invocation) {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserLoginInfo userLoginInfo = (UserLoginInfo) request.getSession()
				.getAttribute(Constant.USER_LOGIN_INFO);
		if (userLoginInfo == null) {
			return true;
		}
		for (String url : EXINCLUDE_ACTIVATE_PATH) {
			if (request.getRequestURI().toLowerCase().endsWith(
					url.toLowerCase())) {
				return true;
			}
		}
		if (!userLoginInfo.getStatus().equals("0")) {
			return true;
		}
		return false;
	}
}
