import java.text.SimpleDateFormat;
import java.util.Calendar;

import freemarker.template.SimpleDate;
import net.win.utils.StringUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(dateFormat.parse("2009-10-4"));
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(dateFormat.parse("2010-10-4"));
	}
}
