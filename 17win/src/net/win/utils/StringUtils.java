package net.win.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 字符串工具类
 * 
 * @author xgj
 * 
 * May 13, 2010
 */
public final class StringUtils {
	private StringUtils() {
	}

	/**
	 * 判断字符串是否为null或则是否为""字符串
	 * 
	 * @param str
	 *            要验证的字符处
	 * @return
	 */
	public static Boolean isBlank(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 加密
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String processPwd(String str) {
		try {
			if (isBlank(str)) {
				return "";
			}
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));

			return newstr;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 替换空格
	 * 
	 * @param s
	 * @return
	 */
	public static String replaceBlank(String s) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(s);
		return m.replaceAll("");
	}

	/**
	 * 返回数组处理后的以逗号分隔数组元素的字符串
	 * 
	 * @param strs
	 * @return
	 */

	public static String getStringByComma(String... strs) {
		if (null != strs && strs.length > 0) {
			List<String> list = Arrays.asList(strs);
			String s = list.toString();
			s = s.substring(1, s.length() - 1);
			return replaceBlank(s);
		} else
			return null;
	}
	public static void main(String[] args) throws Exception{
	 System.out.println(processPwd("123456"));
	}
}
