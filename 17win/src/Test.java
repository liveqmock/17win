import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.win.utils.StringUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "<li tagid=\"HOME_PAGE\"><a href=\"http://253004809.paipai.com/?PTAG=10.1.98\" title=\"店铺首页\">店铺首页</a></li>"
				.replaceAll("\"", "'");
		String temp = StringUtils.replaceBlank(str);

		String regex = "<litagid='HOME_PAGE'><ahref='http://(\\d+)\\.paipai\\.com/";

		System.out.println(temp);
		System.out.println(regex);

		Pattern pattern = Pattern
				.compile(regex);
		Matcher matcher;
			 
			matcher = pattern.matcher(temp);
			while (matcher.find()) {
				System.out.println(matcher.group(1));
			}
	}
}
