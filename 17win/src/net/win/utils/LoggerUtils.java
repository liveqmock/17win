package net.win.utils;

import org.apache.log4j.Logger;

/**
 * 日志工具类
 * 
 * @author xgj
 * 
 * May 26, 2010
 */
public final class LoggerUtils {
	/**
	 * 获取InfoLogger
	 * 
	 * @return
	 */
	protected static Logger getInfoLogger() {
		return Logger.getLogger("17winInfoLogger");
	}

	/**
	 * 获取WarnLogger
	 * 
	 * @return
	 */
	public static Logger getWarnLogger() {
		return Logger.getLogger("17winWarnLogger");
	}

	/**
	 * 获取ErrorLogger
	 * 
	 * @return
	 */
	public static Logger getErrorLogger() {
		return Logger.getLogger("17winErrorLogger");
	}

	/**
	 * 获取DebugLogger
	 * 
	 * @return
	 */
	public static Logger getDebugLogger() {
		return Logger.getLogger("17winDebugLogger");
	}

	/**
	 * 获取FatalLogger
	 * 
	 * @return
	 */
	public static Logger getFatalLogger() {
		return Logger.getLogger("17winFatalLogger");
	}

	public static void error(String message) {
		getErrorLogger().error(message);
	}

	public static void error(Throwable throwable) {
		getErrorLogger().error("", throwable);
	}

	public static void error(String message, Throwable throwable) {
		getErrorLogger().error(message, throwable);
	}

	public static void debug(Throwable throwable) {
		getDebugLogger().debug("", throwable);
	}

	public static void debug(String message) {
		getDebugLogger().debug(message);
	}

	public static void debug(String message, Throwable throwable) {
		getDebugLogger().debug(message, throwable);
	}

	public static void info(Throwable throwable) {
		getInfoLogger().info("", throwable);
	}

	public static void info(String message) {
		getInfoLogger().info(message);
	}

	public static void info(String message, Throwable throwable) {
		getInfoLogger().info(message, throwable);
	}

	public static void fatal(String message) {
		getFatalLogger().fatal(message);
	}

	public static void fatal(Throwable throwable) {
		getFatalLogger().fatal("", throwable);
	}

	public static void fatal(String message, Throwable throwable) {
		getFatalLogger().fatal(message, throwable);
	}

	public static void warn(String message) {
		getWarnLogger().warn(message);
	}

	public static void warn(Throwable throwable) {
		getFatalLogger().warn(null, throwable);
	}

	public static void warn(String message, Throwable throwable) {
		getFatalLogger().warn(message, throwable);
	}

}
