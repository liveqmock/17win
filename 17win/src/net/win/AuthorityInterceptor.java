package net.win;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.win.utils.Constant;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	private static final Set<String> EXINCLUDE_PATH = new HashSet<String>();
	static {
		EXINCLUDE_PATH.add("userManager/base!initLogin.php");
		EXINCLUDE_PATH.add("userManager/base!initRegister.php");
		EXINCLUDE_PATH.add("userManager/base!initRegister.php");
		EXINCLUDE_PATH.add("userManager/base!register.php");
		EXINCLUDE_PATH.add("userManager/base!initFindPassword.php");
		EXINCLUDE_PATH.add("userManager/base!findPassword.php");
		EXINCLUDE_PATH.add("menuManager/menu!toIndex.php");
		EXINCLUDE_PATH.add("userManager/base!login.php");
		EXINCLUDE_PATH.add("ajaxManager/ajax!findPassword.php");
		EXINCLUDE_PATH.add("ajaxManager/ajax!userExists.php");
		EXINCLUDE_PATH.add("ajaxManager/ajax!phoneExists.php");
		EXINCLUDE_PATH.add("ajaxManager/ajax!emailExists.php");
		EXINCLUDE_PATH.add("adminManager/admin!login.php");
		EXINCLUDE_PATH.add("adminNewsManager/adminNews!showHelp.php");
		EXINCLUDE_PATH.add("adminNewsManager/adminNews!listNews.php");
		EXINCLUDE_PATH.add("adminNewsManager/adminNews!detailNews.php");
		EXINCLUDE_PATH.add("userManager/base!getLoginUser.php");
		

	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if (validate(invocation)) {
			return invocation.invoke();
		} else {
			return "noRightsError";
		}

	}

	/**
	 * 验证
	 * 
	 * @param invocation
	 */
	private Boolean validate(ActionInvocation invocation) {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserLoginInfo userLoginInfo = (UserLoginInfo) request.getSession()
				.getAttribute(Constant.USER_LOGIN_INFO);
		for (String url : EXINCLUDE_PATH) {
			if (request.getRequestURI().toLowerCase().endsWith(
					url.toLowerCase())) {
				return true;
			}
		}
		if (userLoginInfo != null) {
			return true;
		}
		// if (EXINCLUDE_PATH.contains(request.getRequestURL())) {
		// return true;
		// }
		return false;
	}
}
