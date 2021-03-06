package net.win.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import net.win.UserLoginInfo;
import net.win.exception.IllegalityException;
import net.win.exception.NoPageException;

import org.apache.struts2.ServletActionContext;

public final class WinUtils {
	private static final int BUFFER_SIZE = 1024;

	private WinUtils() {

	}

	/**
	 * 抛出异常
	 * 
	 * @param name
	 * @param value
	 */
	public static void throwIllegalityException(String msg)
			throws IllegalityException {
		UserLoginInfo userLoginInfo = (UserLoginInfo) ServletActionContext
				.getRequest().getSession().getAttribute(
						Constant.USER_LOGIN_INFO);
		throw new IllegalityException(userLoginInfo + ":" + msg);
	}

	/**
	 * 平台名字转换成type
	 * 
	 * @param platform
	 * @return
	 */
	public static String changePlatform2Type(String platform)
			throws NoPageException {
		try {
			if (platform.equals("淘宝")) {
				return "1";
			}
			if (platform.equals("拍拍")) {
				return "2";
			}
			if (platform.equals("有啊")) {
				return "3";
			}
		} catch (Exception e) {
			throw new NoPageException(e);
		}
		return "1";
	}
	/**
	 * 获取IP
	 * @param request
	 * @return
	 */
	public static String getIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.indexOf(",") != -1) {
			ip = ip.split(",", 2)[0];
		}
		return ip;
	}

	/**
	 * type转换成平台名字
	 * 
	 * @param platform
	 * @return
	 */
	public static String changeType2Platform(String platformType)
			throws NoPageException {
		try {
			if (platformType.equals("1")) {
				return "淘宝";
			}
			if (platformType.equals("2")) {
				return "拍拍";
			}
			if (platformType.equals("3")) {
				return "有啊";
			}
		} catch (Exception e) {
			throw new NoPageException(e);
		}
		return "淘宝";
	}

	/**
	 * 文件拷贝
	 * 
	 * @param src
	 * @param dest
	 * @throws Exception
	 */
	public static void copy(File src, File dest) throws Exception {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			os = new BufferedOutputStream(new FileOutputStream(dest),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			while (is.read(buffer) > 0) {
				os.write(buffer);
			}
		} catch (FileNotFoundException e) {
			LoggerUtils.error(e);
		} catch (IOException e) {
			LoggerUtils.error(e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
