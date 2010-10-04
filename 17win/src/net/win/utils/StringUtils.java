package net.win.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * 
	 * 
	 * @param str
	 * 
	 * @return
	 */
	public static String formatNumber(Number number) {
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(number);

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

	/**
	 * 数字转换中文汉字
	 */
	public static String getChineseNumber(int number) throws Exception {
		String str = "一";
		switch (number) {
		case 1:
			str = "一";
			break;
		case 2:
			str = "二";
			break;
		case 3:
			str = "三";
			break;
		case 4:
			str = "四";
			break;
		case 5:
			str = "五";
			break;
		case 6:
			str = "六";
			break;
		case 7:
			str = "七";
			break;
		case 8:
			str = "八";
			break;
		case 9:
			str = "九";
			break;
		case 10:
			str = "十";
			break;
		default:
			str = "一";
			break;
		}
		return str;
	}

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return true表示不为空,false表示空
	 */
	public static boolean isEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 计算千分比
	 */
	public static String permillage(double p1, double p2) {
		String str;
		double p3 = p1 / p2;
		if (p1 == 0.0) {
			return "0.00";
		}
		if (p2 == 0.0 && p1 != 0.0) {
			return "-";
		}
		DecimalFormat df1 = new DecimalFormat("0.00");
		str = df1.format(p3 * 1000);
		return str;
	}

	/**
	 * 计算百分比
	 */
	public static String percent(double p1, double p2) {
		String str;
		if (p1 == 0.0) {
			return "0.00";
		}
		double p3 = p1 / p2;
		DecimalFormat df1 = new DecimalFormat("##.00");
		if (p3 * 100 < 1) {
			df1 = new DecimalFormat("0.00");
		}
		str = df1.format(p3 * 100);
		return str;
	}

	/**
	 * 计算平均值
	 */
	public static String average(double p1, double p2) {
		String str;
		if (p1 == 0.0) {
			return "0.00";
		}
		double p3 = p1 / p2;
		DecimalFormat df1 = new DecimalFormat("##.00");
		if (p3 < 1) {
			df1 = new DecimalFormat("0.00");
		}
		str = df1.format(p3);
		return str;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(processPwd("123456"));
	}
}
