package net.win.utils;

public final class UserLevelUtils {
	private UserLevelUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取级别
	 * 
	 * @param score
	 * @return
	 */
	// 0分-100分 1红心
	// 101分-400分 2红心
	// 401分-900分 3红心
	// 901分-1500分 1钻
	// 1501分-2500分 2钻
	// 2501分-8000分 3钻
	// 8001分-10000分 1皇冠
	// 10001分-20000分 2皇冠
	// 20001分-50000分 3皇冠
	//
	//
	// 50001分-100000分 1金冠
	//
	// 100001分-180000分 2金冠
	//
	// 180001分-300000 分 3金冠
	public static Integer getLevel(Integer score) {
		if (score >= 0 && score < 100) {
			return 1;
		}
		if (score >= 100 && score < 400) {
			return 2;
		}
		if (score >= 400 && score < 900) {
			return 3;
		}
		if (score >= 900 && score < 1500) {
			return 4;
		}
		if (score >= 1500 && score < 2500) {
			return 5;
		}
		if (score >= 2500 && score < 8000) {
			return 6;
		}
		if (score >= 8000 && score < 10000) {
			return 7;
		}
		if (score >= 10000 && score < 20000) {
			return 8;
		}
		if (score >= 20000 && score < 50000) {
			return 9;
		}
		if (score >= 50000 && score < 100000) {
			return 10;
		}
		if (score >= 100000 && score < 180000) {
			return 11;
		}
		if (score >= 180000 && score < 300000) {
			return 12;
		}
		return 0;

	}

	/**
	 * 根据级别获取到图片名字
	 * 
	 * @param score
	 * @return
	 */
	public static String getLevelImg(Integer score) {
		if (score >= 0 && score < 100) {
			return "xin_1.gif";
		}
		if (score >= 100 && score < 400) {
			return "xin_2.gif";
		}
		if (score >= 400 && score < 900) {
			return "xin_3.gif";
		}
		if (score >= 900 && score < 1500) {
			return "zuan_1.gif";
		}
		if (score >= 1500 && score < 2500) {
			return "zuan_2.gif";
		}
		if (score >= 2500 && score < 8000) {
			return "zuan_13.gif";
		}
		if (score >= 8000 && score < 10000) {
			return "guan_1.gif";
		}
		if (score >= 10000 && score < 20000) {
			return "guan_2.gif";
		}
		if (score >= 20000 && score < 50000) {
			return "guan_3.gif";
		}
		if (score >= 50000 && score < 100000) {
			return "hg_1.gif";
		}
		if (score >= 100000 && score < 180000) {
			return "hg_2.gif";
		}
		if (score >= 180000 && score < 300000) {
			return "hg_3.gif";
		}
		return "xin_1.gif";

	}
}
