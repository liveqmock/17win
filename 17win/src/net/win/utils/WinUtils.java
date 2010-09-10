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
import java.util.Date;

public final class WinUtils {
	private static final int BUFFER_SIZE = 1024;

	private WinUtils() {

	}

	/**
	 * taskId
	 * 
	 * @param src
	 * @param dest
	 * @throws Exception
	 */
	public static String generateTaskID() throws Exception {
		return DateUtils.parseDate(new Date(), "yyyyMMddHHmmssS");
	}

	/**
	 * 平台名字转换成type
	 * 
	 * @param platform
	 * @return
	 */
	public static String changePlatform2Type(String platform) {
		if (platform.equals("淘宝")) {
			return "1";
		}
		if (platform.equals("拍拍")) {
			return "2";
		}
		if (platform.equals("有啊")) {
			return "3";
		}
		return "1";
	}

	/**
	 * type转换成平台名字
	 * 
	 * @param platform
	 * @return
	 */
	public static String changeType2Platform(String platformType) {
		if (platformType.equals("1")) {
			return "淘宝";
		}
		if (platformType.equals("2")) {
			return "拍拍";
		}
		if (platformType.equals("3")) {
			return "有啊";
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
