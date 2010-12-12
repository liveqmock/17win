package net.win;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.win.utils.Constant;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class TokenInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6896151123159541897L;
	/**
	 * 不包含的用户登录路径
	 */
	private static final Set<String> INCLUDE_TOKEN_VALIDATION_URL = new HashSet<String>();
	static {
		// vip
		INCLUDE_TOKEN_VALIDATION_URL.add("vipManager/vip!buyVip.php");
		INCLUDE_TOKEN_VALIDATION_URL.add("vipManager/vip!renewalVip.php");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Boolean flag = hasToken(invocation);
		if (flag) {
			return invocation.invoke();
		} else {
			return "tokenError";
		}
	}

	private Boolean hasToken(ActionInvocation invocation) throws Exception {
		boolean flag = true;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String tokenValue = request.getParameter(Constant.WIN17_TOKEN);
		for (String url : INCLUDE_TOKEN_VALIDATION_URL) {
			if (request.getRequestURI().toLowerCase().endsWith(
					url.toLowerCase())) {
				if (tokenValue == null) {
					flag = false;
				} else {
					flag = tokenValue.equals(session
							.getAttribute(Constant.WIN17_TOKEN));
					if (flag) {
						session.removeAttribute(Constant.WIN17_TOKEN);
					}
				}
				break;
			}
		}
		return flag;
	}

}
