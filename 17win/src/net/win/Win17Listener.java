package net.win;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.beanutils.ConvertUtils;

public class Win17Listener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			ConvertUtils.register(new DateConverter(), java.sql.Date.class);
			Thread.currentThread().getContextClassLoader().loadClass(
					WinConfig.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
