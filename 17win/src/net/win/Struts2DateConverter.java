package net.win;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

@SuppressWarnings("unchecked")
public class Struts2DateConverter extends StrutsTypeConverter {
	/** */
	/** 默认的日期转换格式 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** */
	/** 可接手的日期格式 */
	public static final DateFormat[] ACCEPT_DATE_FORMATS = {
			new SimpleDateFormat(DEFAULT_DATE_FORMAT),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS") };

	@SuppressWarnings("unchecked")
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values[0] == null || values[0].trim().equals("")) {
			return null;
		}
		/** */
		/** 尝试从默认值开始转换 */
		for (DateFormat format : ACCEPT_DATE_FORMATS) {
			try {
				return format.parse(values[0]);
			} catch (Exception e) {
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String convertToString(Map context, Object o) {
		if (o instanceof Date) {
			try {
				return ACCEPT_DATE_FORMATS[0].format((Date) o);
			} catch (RuntimeException e) {
				return "";
			}
		}
		return "";
	}

}
