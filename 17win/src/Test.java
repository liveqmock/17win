import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.win.utils.StringUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pattern pattern = Pattern
				.compile("^http:[/\\\\]{2}\\w+\\-*\\w+\\.taobao\\.com[/\\\\]?");
		Matcher matcher = pattern.matcher("http://sz-ds.taobao.com/item");
		
		System.out.println(matcher.find());
		System.out
				.println("http://sz-ds.taobao.com/item"
						.matches("^http:[/\\\\]{2}\\w+\\-*\\w+\\.taobao\\.com[/\\\\]?"));
	}
}
