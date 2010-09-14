package net.win.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final Integer PRE_MONTH = -1;
	public static final Integer NOW_MONTH = 0;
	public static final Integer NEXT_MONTH = 1;

	private DateUtils() {

	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parseDate(Date date, String format) {
		if (date == null || format == null)
			return "";
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	 * 得到某月的第一天
	 * 
	 * @param monthFlag
	 * @return
	 */
	public static Date getMonthMinDay(Integer monthFlag) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthFlag);
		calendar.set(calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH)); // 最小的一天
		return calendar.getTime();
	}
	/**
	 * 得到某月的最后一天
	 * 
	 * @param monthFlag
	 * @return
	 */
	public static Date getMonthMaxDay(Integer monthFlag) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthFlag);
		calendar.set(calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH)); // 最大的一天
		return calendar.getTime();
	}
}
