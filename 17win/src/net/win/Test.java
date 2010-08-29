package net.win;

import java.net.URLDecoder;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		String s = "%E9%91%AB%E8%9B%8B%E6%95%B0%E7%A0%81%E5%AE%B6%E7%94%B5";
		System.out.println(URLDecoder.decode(s, "UTF-8").matches("\\w*"));
	}

}
