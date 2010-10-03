package net.win.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 策略工具。。用于生成 金额，发布点的策略工具 ,升级
 * 
 * @author xgj
 * 
 */
public final class StrategyUtils {
	private StrategyUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 评价类型，一天，两天，三天
	 * 
	 * @param goodType
	 * @return
	 * @throws Exception
	 */
	public static Integer getIntervalHourByGoodType(String goodType)
			throws Exception {
		if ("1".equals(goodType)) {
			return 0;
		}
		if ("2".equals(goodType)) {
			return 24;
		}
		if ("3".equals(goodType)) {
			return 48;
		}
		if ("4".equals(goodType)) {
			return 72;
		}
		WinUtils.throwIllegalityException("试图越过发布点的时间计算操作");
		return 0;
	}

	/**
	 * // 圆通 1 // 韵达 2 // 申通 3 // EMS 4 // 顺风 5 // 中通6
	 * 
	 * @param src
	 * @param dest
	 * @throws Exception
	 */
	public static String makeWaybill() throws Exception {
		Random random = new Random();
		int size = WayBill.waybillList.size();
		int index = random.nextInt(size);
		WayBill wayBill = WayBill.waybillList.get(index);
		String type = wayBill.type;
		if (type.equals("1")) {
			return "圆通 " + wayBill.code;
		}
		if (type.equals("2")) {
			return "韵达 " + wayBill.code;
		}
		if (type.equals("3")) {
			return "申通" + wayBill.code;
		}
		if (type.equals("4")) {
			return "EMS " + wayBill.code;
		}
		if (type.equals("5")) {
			return "顺风 " + wayBill.code;
		}
		if (type.equals("6")) {
			return "中通 " + wayBill.code;
		}
		return "无";
	}

	private static class WayBill {
		private WayBill() {

		}

		private WayBill(String type, String code) {
			this.type = type;
			this.code = code;
		}

		/**
		 * // 圆通 1 // 韵达 2 // 申通 3 // EMS 4 // 顺风 5 // 中通6
		 */
		private String type;
		private String code;
		private static List<WayBill> waybillList = new LinkedList<WayBill>();
		static {
			/**
			 * 圆通
			 */
			waybillList.add(new WayBill("1", "6001756116"));
			waybillList.add(new WayBill("1", "6001756114"));
			waybillList.add(new WayBill("1", "6001756112"));
			waybillList.add(new WayBill("1", "6001756111"));
			waybillList.add(new WayBill("1", "6001756110"));
			waybillList.add(new WayBill("1", "6001756131"));
			waybillList.add(new WayBill("1", "6001756109"));
			waybillList.add(new WayBill("1", "6001756124"));
			waybillList.add(new WayBill("1", "6001756126"));
			waybillList.add(new WayBill("1", "6001756107"));
			waybillList.add(new WayBill("1", "6001756130"));
			waybillList.add(new WayBill("1", "6001756130"));
			waybillList.add(new WayBill("1", "6001756108"));
			waybillList.add(new WayBill("1", "6001756125"));
			waybillList.add(new WayBill("1", "6001756087"));
			// /
			waybillList.add(new WayBill("1", "2271000681"));
			waybillList.add(new WayBill("1", "2271000565"));
			waybillList.add(new WayBill("1", "2271000361"));
			waybillList.add(new WayBill("1", "2271000861"));
			waybillList.add(new WayBill("1", "2271000750"));
			//

			waybillList.add(new WayBill("1", "2312507869"));
			waybillList.add(new WayBill("1", "2312507867"));
			waybillList.add(new WayBill("1", "2312507866"));
			waybillList.add(new WayBill("1", "2312507908"));
			waybillList.add(new WayBill("1", "2312507909"));
			waybillList.add(new WayBill("1", "2312507864"));
			waybillList.add(new WayBill("1", "2312507865"));
			waybillList.add(new WayBill("1", "2312507907"));
			/**
			 * 韵达
			 */
			waybillList.add(new WayBill("2", "1200291981000"));
			waybillList.add(new WayBill("2", "1200291980999"));
			waybillList.add(new WayBill("2", "1200291980997"));
			waybillList.add(new WayBill("2", "1200291980994"));
			waybillList.add(new WayBill("2", "1200291980993"));
			waybillList.add(new WayBill("2", "1200291980995"));
			waybillList.add(new WayBill("2", "1200291980991"));
			waybillList.add(new WayBill("2", "1200291980996"));
			waybillList.add(new WayBill("2", "1200291980990"));
			waybillList.add(new WayBill("2", "1200291980987"));
			waybillList.add(new WayBill("2", "1200291980985"));
			waybillList.add(new WayBill("2", "1200291980989"));
			waybillList.add(new WayBill("2", "1200291980822"));
			waybillList.add(new WayBill("2", "1200291980827"));
			waybillList.add(new WayBill("2", "1200291980821"));
			waybillList.add(new WayBill("2", "1200291980823"));
			waybillList.add(new WayBill("2", "1200291980820"));
			waybillList.add(new WayBill("2", "1200291980827"));
			waybillList.add(new WayBill("2", "1200291980826"));
			waybillList.add(new WayBill("2", "1200291980825"));
			waybillList.add(new WayBill("2", "1200291980824"));
			waybillList.add(new WayBill("2", "1200291980831"));
			waybillList.add(new WayBill("2", "1200286209648"));

			//
			waybillList.add(new WayBill("2", "1200286209655"));
			waybillList.add(new WayBill("2", "1200286209654"));
			waybillList.add(new WayBill("2", "1200286209653"));
			waybillList.add(new WayBill("2", "1200286209652"));
			waybillList.add(new WayBill("2", "1200286209662"));
			waybillList.add(new WayBill("2", "1200286209684"));
			waybillList.add(new WayBill("2", "1200286209602"));
			waybillList.add(new WayBill("2", "1200286209603"));
			waybillList.add(new WayBill("2", "1200286209499"));
			waybillList.add(new WayBill("2", "1200286209498"));

			/**
			 * 申通
			 */
			waybillList.add(new WayBill("3", "368686240513"));
			waybillList.add(new WayBill("3", "368686460486"));
			waybillList.add(new WayBill("3", "368686460487"));
			waybillList.add(new WayBill("3", "368686460488"));
			waybillList.add(new WayBill("3", "368686240503"));
			waybillList.add(new WayBill("3", "368686240520"));
			waybillList.add(new WayBill("3", "368686240506"));
			waybillList.add(new WayBill("3", "368686240504"));
			waybillList.add(new WayBill("3", "368686240505"));
			waybillList.add(new WayBill("3", "368686240505"));
			waybillList.add(new WayBill("3", "368686240507"));
			waybillList.add(new WayBill("3", "368686240508"));
			waybillList.add(new WayBill("3", "368686240509"));
			waybillList.add(new WayBill("3", "368686240510"));
			waybillList.add(new WayBill("3", "368686240511"));
			waybillList.add(new WayBill("3", "368686240512"));
			waybillList.add(new WayBill("3", "368686240515"));
			waybillList.add(new WayBill("3", "368686240517"));
			waybillList.add(new WayBill("3", "368686240516"));
			waybillList.add(new WayBill("3", "368686240518"));
			waybillList.add(new WayBill("3", "368686240519"));
			waybillList.add(new WayBill("3", "368687508156"));

			/**
			 * EMS
			 */
			waybillList.add(new WayBill("4", "EF672566936CS"));
			waybillList.add(new WayBill("4", "EF672566922CS"));
			waybillList.add(new WayBill("4", "EF672566953CS"));
			waybillList.add(new WayBill("4", "EF672566940CS"));
			/**
			 * 顺风
			 */
			waybillList.add(new WayBill("5", "027111023362"));
			waybillList.add(new WayBill("5", "027111023362"));
			waybillList.add(new WayBill("5", "027111022994"));
			waybillList.add(new WayBill("5", "027111023344"));
			waybillList.add(new WayBill("5", "027111022994"));
			waybillList.add(new WayBill("5", "101599163357"));
			waybillList.add(new WayBill("5", "101599163215"));
			waybillList.add(new WayBill("5", "101599163218"));

			// 中通
			waybillList.add(new WayBill("6", "618326479802"));
			waybillList.add(new WayBill("6", "618326479806"));
			waybillList.add(new WayBill("6", "618326479807"));
			waybillList.add(new WayBill("6", "618326479808"));
			waybillList.add(new WayBill("6", "618326479809"));
			//
			waybillList.add(new WayBill("6", "680086870947"));
			waybillList.add(new WayBill("6", "680086870957"));
			waybillList.add(new WayBill("6", "680086870998"));
			//
			waybillList.add(new WayBill("6", "680086870952"));
			waybillList.add(new WayBill("6", "680086870969"));
			waybillList.add(new WayBill("6", "680086870976"));
			waybillList.add(new WayBill("6", "680086870975"));
			waybillList.add(new WayBill("6", "680086870972"));
			waybillList.add(new WayBill("6", "680086870950"));
			waybillList.add(new WayBill("6", "680086870975"));
			waybillList.add(new WayBill("6", "680086870963"));
			waybillList.add(new WayBill("6", "680086870965"));
			waybillList.add(new WayBill("6", "680086870970"));
			//
			waybillList.add(new WayBill("6", "680086870974"));
			waybillList.add(new WayBill("6", "680086870968"));
			waybillList.add(new WayBill("6", "680086870966"));
			waybillList.add(new WayBill("6", "680086870962"));
			waybillList.add(new WayBill("6", "680086870959"));
			waybillList.add(new WayBill("6", "680086870958"));
			waybillList.add(new WayBill("6", "680086870961"));
			waybillList.add(new WayBill("6", "680086870964"));
			waybillList.add(new WayBill("6", "680086870982"));
			waybillList.add(new WayBill("6", "680086870986"));
			waybillList.add(new WayBill("6", "680086870944"));
			waybillList.add(new WayBill("6", "680086870946"));
			waybillList.add(new WayBill("6", "680086870945"));
			waybillList.add(new WayBill("6", "680086870949"));
			waybillList.add(new WayBill("6", "680086870851"));
			waybillList.add(new WayBill("6", "680086870852"));
			waybillList.add(new WayBill("6", "680086870943"));
			waybillList.add(new WayBill("6", "680086870855"));
			waybillList.add(new WayBill("6", "680086870854"));
			waybillList.add(new WayBill("6", "680086870861"));
		}
	}

	/**
	 * 得到信誉dot发布点
	 * 
	 * @param src
	 * @param dest
	 * @throws Exception
	 */
	public static Double generateCreditRDot(Double money, Integer intervalHour)
			throws Exception {
		double resultDot = 0;
		// 基本发布点
		double metaDot = 0;
		if (money >= 1 && money <= 40) {
			metaDot = 1;
		} else if (money > 40 && money <= 100) {
			metaDot = 2;
		} else if (money > 100 && money <= 200) {
			metaDot = 3;
		} else if (money > 200 && money <= 500) {
			metaDot = 4;
		} else if (money > 500) {
			metaDot = 5;
		}
		// 时间
		if (intervalHour == 0) {
			return metaDot;
		} else if (intervalHour > 0 && intervalHour < 24) {
			return metaDot;
		} else if (intervalHour >= 24 && intervalHour < 48) {
			return metaDot * 2;
		} else if (intervalHour >= 48 && intervalHour < 72) {
			return metaDot * 2 + 1;
		} else if (intervalHour >= 72) {
			return metaDot * 2 + 2;
		}
		WinUtils.throwIllegalityException("试图越过刷信誉发布点计算操作");
		return resultDot;
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
