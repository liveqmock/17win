package net.win;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.win.utils.Constant;

import org.apache.commons.beanutils.ConvertUtils;

public class Win17Listener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			ConvertUtils.register(new DateConverter(), java.sql.Date.class);
			ConvertUtils
					.register(new DoubleConvert(), java.lang.Double.class);
			Constant.initMetatData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
