package net.win;

import net.win.exception.IllegalityException;
import net.win.utils.LoggerUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			String result = invocation.invoke();
			return result;
		} catch (IllegalityException e1) {
			LoggerUtils.fatal(e1);
			return "error";
		} catch (Exception e) {
			LoggerUtils.error(e);
			return "error";
		}

	}

}
