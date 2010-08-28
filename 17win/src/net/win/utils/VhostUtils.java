package net.win.utils;

import java.io.File;

import org.apache.struts2.ServletActionContext;

public class VhostUtils {
	/**
	 * 得到根目录
	 * 
	 * @return
	 */
	public static String getRootPath() {
		String fileName = ServletActionContext.getRequest().getSession()
				.getServletContext().getRealPath("/");
		return fileName;
	}
	/**
	 * 得到classes目录
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
