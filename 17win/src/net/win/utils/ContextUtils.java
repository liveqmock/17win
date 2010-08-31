package net.win.utils;

import java.io.File;

import org.apache.struts2.ServletActionContext;

public final class ContextUtils {
	private ContextUtils() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * �õ���Ŀ¼
	 * 
	 * @return
	 */
	public static String getRootPath() {
		String fileName = ServletActionContext.getRequest().getSession()
				.getServletContext().getRealPath("/");
		return fileName;
	}
	/**
	 * �õ�classesĿ¼
	 * 
	 * @return
	 */
	public static String getClassesPath() {
		String fileName = ServletActionContext.getRequest().getSession()
				.getServletContext().getRealPath("/")
				+ "WEB-INF" + File.separator + "classes";
		return fileName;
	}

}
