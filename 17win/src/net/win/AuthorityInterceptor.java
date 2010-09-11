package net.win;

import javax.servlet.http.HttpServletRequest;

import net.win.exception.NoRightsException;
import net.win.utils.Constant;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserLoginInfo userLoginInfo = (UserLoginInfo) request.getSession()
				.getAttribute(Constant.USER_LOGIN_INFO);

		if (userLoginInfo == null) {
			return "noRightsError";
		}
		return invocation.invoke();
	}

	// 明天权限列表
}
