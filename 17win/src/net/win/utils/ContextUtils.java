package net.win.utils;

import java.io.File;

import org.apache.struts2.ServletActionContext;

public final class ContextUtils {
	private ContextUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 得到跟目录
	 * 
	 * @return
	 */
	public static String getRootPath() {
		String s = getClassesPath();
		return s.split("WEB-INF")[0];
	}

	/**
	 * 得到classes目录
	 * 
	 * @return
	 */
	public static String getClassesPath() {
		String s = Thread.currentThread().getContextClassLoader().getResource(
				"").toString();
		return s.split("/", 2)[1];
	}
}
