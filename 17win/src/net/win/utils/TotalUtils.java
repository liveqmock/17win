package net.win.utils;

/**
 * 球总和工具类
 * 
 * @author xgj
 * 
 */
public final class TotalUtils {
	private TotalUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 计算每行的整型总和
	 * 
	 * @param strs
	 */
	public static void totalRowByInt(String[][] strs) {
		if (strs == null || strs.length == 0) {
			return;
		}
		// 行
		int indexR = strs.length;
		// 列
		int indexC = strs[0].length;

		for (int i = 0; i < indexR; i++) {
			Integer count = 0;
			// j=1 第一行是标题，不在计算之内
			for (int j = 1; j < indexC; j++) {
				count += Integer.parseInt(strs[i][j]);
			}
			strs[i][indexC - 1] = count + "";
		}
	}

	/**
	 * 计算每列的整型总和
	 * 
	 * @param strs
	 */
	public static void totalColumnByInt(String[][] strs) {
		if (strs == null || strs.length == 0) {
			return;
		}
		// 行
		int indexR = strs.length;
		// 列
		int indexC = strs[0].length;

		// i=1 第一行是标题，不在计算之内
		for (int i = 1; i < indexC; i++) {
			Integer count = 0;
			for (int j = 0; j < indexR; j++) {
				count += Integer.parseInt(strs[j][i]);
			}
			strs[indexR - 1][i] = count + "";
		}
	}

	/**
	 * 计算每列的整型总和
	 * 
	 * @param strs
	 */
	public static void totalAllByInt(String[][] strs) {
		totalRowByInt(strs);
		totalColumnByInt(strs);

	}
}
