package net.win;

import net.win.exception.IllegalityException;
import net.win.exception.NoPageException;
import net.win.exception.NoRightsException;
import net.win.exception.SystemErrorException;
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
		} catch (NoRightsException e1) {
			LoggerUtils.error(e1);
			return "noRightsError";
		} catch (NoPageException e1) {
			LoggerUtils.error(e1);
			return "noPageError";
		} catch (SystemErrorException e1) {
			LoggerUtils.error(e1);
			return "systemError";
		} catch (IllegalityException e1) {
			LoggerUtils.fatal(e1);
			return "illegalityError";
		} catch (Exception e) {
			LoggerUtils.error(e);
			return "commonError";
		}

	}

}
