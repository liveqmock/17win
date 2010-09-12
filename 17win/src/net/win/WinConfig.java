package net.win;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import net.win.utils.LoggerUtils;

public final class WinConfig {
	private static Properties properties = initProperty();
	private static WinConfig winConfig = new WinConfig();

	private WinConfig() {
	}

	public static WinConfig getInstance() {
		return winConfig;
	}

	/**
	 * 是否IP验证
	 * 
	 * @return
	 */
	public boolean isIPValidate() {
		String value = (String) properties.get("IPValidate");
		return "true".equals(value);
	}

	private static Properties initProperty() {
		URL url = Thread.currentThread().getContextClassLoader().getResource(
				"winConf.properties");
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = url.openStream();
			properties.load(is);
		} catch (Exception e) {
			LoggerUtils.error("初始化读取配置文件错误！" + e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					LoggerUtils.error("初始化读取配置文件错误！" + e);
				}
			}
		}
		return properties;
	}
}
