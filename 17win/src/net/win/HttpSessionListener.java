package net.win;

import javax.servlet.http.HttpSessionEvent;

import net.win.utils.Constant;

public class HttpSessionListener implements
		javax.servlet.http.HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		WinContext.getInstance().addOnlineCount();
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		String userName = ((UserLoginInfo) event.getSession().getAttribute(
				Constant.USER_LOGIN_INFO)).getUsername();
		WinContext.getInstance().removeUserLoginInfo(userName);
	}

}
