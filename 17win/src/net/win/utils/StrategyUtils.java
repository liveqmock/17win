package net.win.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import net.win.entity.UserEntity;
import net.win.entity.VipEntity;

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
	 * 计算卖号个数
	 * 
	 * @param goodType
	 * @return
	 * @throws Exception
	 */
	public static Integer getSellerCount(String type, Boolean vipEnable,
			VipEntity vipEntity) {
		if (!vipEnable) {
			return 2;
		} else {
			return vipEntity.getSellerCount();
		}
	}

	/**
	 * 会员升级
	 * 
	 * @param goodType
	 * @return
	 * @throws Exception
	 */
	public static String getVipType(Integer growValue) throws Exception {
		// 如果vip失效或则没有vip
		if (growValue == null) {
			throw new IllegalArgumentException("会员升级错误！");
		}
		if (0 <= growValue && growValue < 1800) {
			return "1";
		}
		if (1800 <= growValue && growValue < 5400) {
			return "2";
		}
		if (5400 <= growValue) {
			return "3";
		}
		return "1";
	}

	/**
	 * 计算发任务会员成长值
	 * 
	 * @param goodType
	 * @return
	 * @throws Exception
	 */
	public static Integer getReleaseGrowValue(VipEntity vipEntity)
			throws Exception {
		// 如果vip失效或则没有vip
		if (vipEntity == null) {
			return 0;
		}
		if ("1".equals(vipEntity.getType())) {
			return 1;
		}
		if ("2".equals(vipEntity.getType())) {
			return 2;
		}
		if ("3".equals(vipEntity.getType())) {
			return 3;
		}
		return 0;
	}

	/**
	 * 计算接任务会员成长值
	 * 
	 * @param goodType
	 * @return
	 * @throws Exception
	 */
	public static Integer getReceieveGrowValue(VipEntity vipEntity)
			throws Exception {
		// 如果vip失效或则没有vip
		if (vipEntity == null) {
			return 0;
		}
		if ("1".equals(vipEntity.getType())) {
			return 2;
		}
		if ("2".equals(vipEntity.getType())) {
			return 3;
		}
		if ("3".equals(vipEntity.getType())) {
			return 4;
		}
		return 0;
	}

	/**
	 * 获取任务结束发布点比例
	 * 
	 * @param goodType
	 * @return
	 * @throws Exception
	 */
	public static Double getTaskOverDotRate(UserEntity userEntity,
			VipEntity vipEntity, Boolean vipEnable) throws Exception {
		// 如果vip失效或则没有vip
		if (!vipEnable || vipEntity == null) {
			if (getLevel(userEntity.getUpgradeScore()).equals(1)) {
				return 1D;
			} else {
				return Constant.getReceieveTaskDotRate();
			}
		} else {
			return 1D;
		}
	}

	/**
	 * 获取任务发送者的的积分
	 * 
	 * @param goodType
	 * @return
	 * @throws Exception
	 */
	public static Integer getReleaseUserTaskScore(VipEntity vipEntity,
			Boolean vipEnable) throws Exception {
		// 如果vip失效或则没有vip
		if (!vipEnable || vipEntity == null) {
			return 1;
		}
		return vipEntity.getReleaseScore();
	}

	/**
	 * 获取任务接受者的的积分
	 * 
	 * @param goodType
	 * @return
	 * @throws Exception
	 */
	public static Integer getReceieveUserTaskScore(VipEntity vipEntity,
			Boolean vipEnable) throws Exception {
		// 如果vip失效或则没有vip
		if (!vipEnable || vipEntity == null) {
			return 2;
		}
		return vipEntity.getReceieveScore();
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

	/**
	 * 生成地址
	 * 
	 * @param src
	 * @param dest
	 * @throws Exception
	 */
	public static String makeAddress() throws Exception {
		Random random = new Random();
		int addressSize = Address.addressList.size();
		int nameSize = Address.nameList.size();
		int idSize = Address.idList.size();
		int addressIndex = random.nextInt(addressSize);
		int nameIndex = random.nextInt(nameSize);
		int idIndex = random.nextInt(idSize);
		StringBuffer result = new StringBuffer();
		result.append(Address.addressList.get(addressIndex) + " ");
		result.append("姓名："+Address.nameList.get(nameIndex) + " ");
		result.append("电话: "+Address.makePhone() + " ");
		result.append("身份证: "+Address.idList.get(idIndex));
		return result.toString();

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

	/**
	 * 获取VIP的图片
	 * 
	 * @param score
	 * @return
	 */
	public static String getVipImg(String type, Boolean enable) {
		return "vip" + type + "_" + (enable ? "cai" : "yin") + ".gif";
	}

	/**
	 * 地址
	 * 
	 * @author Administrator
	 * 
	 */
	private static class Address {
		private Address() {
		}

		private static String makePhone() {
			String[] prefix = new String[] { "130", "156", "135", "136", "139",
					"138", "150", "151", "158" };
			Random random = new Random();
			StringBuffer result = new StringBuffer(prefix[random
					.nextInt(prefix.length)]);
			for (int i = 0; i < 7; i++) {
				result.append(random.nextInt(10));
			}
			return result.toString();
		}

		private static List<String> addressList = new LinkedList<String>();
		private static List<String> idList = new LinkedList<String>();
		private static List<String> nameList = new LinkedList<String>();
		static {
			/**
			 * 地址
			 */
			addressList.add("廊下镇勇敢勇勤4组1018号");
			addressList.add("区高基街福庆坊6号303 ");
			addressList.add("镇三屯村上涌路22 ");
			addressList.add("供电有限责任公司营业室");
			addressList.add("旺吴路169号莱福特公司");
			addressList.add("礼乐新丰三福里");
			addressList.add("礼乐街道办事处新丰三福里150号");
			addressList.add("三干渠水利公司");
			addressList.add("小关街北口鼎好电脑城清华同方专卖店");
			addressList.add("重文路19号");

			addressList.add("第四中学高三（19）班");
			addressList.add("工业路西段华冠面粉有限公司");
			addressList.add("雄楚大街229号春林庭苑A区2001室");
			addressList.add("人民医院B超室");
			addressList.add("钢铁大街89号包钢集团设计研究院312房间");
			addressList.add("街洞煤矿138号");
			addressList.add("县府后街中段路南骄阳广告");
			addressList.add("宁海里小区5号403");
			addressList.add("东沟商业街101号");
			addressList.add("华西村炮营农场");

			addressList.add("万丰路83号");
			addressList.add("医学院附属妇产科医院药库");
			addressList.add("公园路49号");
			addressList.add("福田区上沙村一坊32栋203室");
			addressList.add("复兴路79号604楼2单元2号");
			addressList.add("齐云南村5号楼4单元301室");
			addressList.add("张家湾镇里二泗村1号保罗生物园413室国际市场部");
			addressList.add("附海工业园/德晶汽配");
			addressList.add("文山学海路");
			addressList.add("东方红路富达小区底商苏宁电器南50米富豪电脑");

			addressList.add("电子科技大学08届4班");
			addressList.add("振宁路387号");
			addressList.add("湖滨北路28号建业大厦A1903室");
			addressList.add("泰州职业技术学院09电子2班");
			addressList.add("明丰小城C-7");
			addressList.add("黄河路184号黑龙江农垦管理干部学院09物流");
			addressList.add("龙门镇王山村1组34号");
			addressList.add("武夷花园9幢18单元205室");
			addressList.add("温泉开发区温泉村6组");
			addressList.add("张家湾镇9号");

			addressList.add("横店中心小学一年二班");
			addressList.add("环境资源职业技术学院08届4班");
			addressList.add("新一中高二（14）班");
			addressList.add("金鸡路20号");
			addressList.add("风味小吃一条街银河双语安置楼2号楼602");
			addressList.add("谭家岭西路158号新皇潮酒店");
			addressList.add("保健街43-20门阜新市津桥艺术学校");
			addressList.add("壮锦大道碧园南城故事29栋502号");
			addressList.add("环远洋国际D座403 ");
			addressList.add("三香新村17幢中单元203 ");

			/**
			 * 名字
			 */

			nameList.add("姜金耀");
			nameList.add("姜彦恩");
			nameList.add("姜宗智");
			nameList.add("姜炳鑫");
			nameList.add("姜锘松");

			nameList.add("董春琳");
			nameList.add("董小民");
			nameList.add("董嘉星");
			nameList.add("董林珧");
			nameList.add("董奕升");

			nameList.add("朱长瑞");
			nameList.add("朱轩嘉");
			nameList.add("朱吉备");
			nameList.add("朱镇源");
			nameList.add("朱小宇");

			nameList.add("赵羽钱");
			nameList.add("赵琳乔");
			nameList.add("赵佳英");
			nameList.add("赵艺鑫");
			nameList.add("赵艳淇");

			nameList.add("胡心渊");
			nameList.add("胡仪思");
			nameList.add("胡荣卿");
			nameList.add("胡传宇");
			nameList.add("胡淑玉");

			nameList.add("杜涛");
			nameList.add("杜亮");
			nameList.add("杜博");
			nameList.add("杜楠");
			nameList.add("杜岭");

			nameList.add("孙敏媛");
			nameList.add("孙玥丽");
			nameList.add("孙呈媛");
			nameList.add("孙秋卉");
			nameList.add("孙岚茜");

			nameList.add("王庆");
			nameList.add("王宇");
			nameList.add("王光");
			nameList.add("王晶");
			nameList.add("王晗");

			nameList.add("韩琳翔");
			nameList.add("韩河泽");
			nameList.add("韩翎萌");
			nameList.add("韩小皓");
			nameList.add("韩长桂");

			nameList.add("钱鹏明");
			nameList.add("钱泷伟");
			nameList.add("钱家付");
			nameList.add("钱东强");
			nameList.add("钱忆辰");
			/**
			 * 身份证
			 */
			idList.add("330101198303017526");
			idList.add("330101198303016726");
			idList.add("210101198303011602");
			idList.add("210101198303015910");
			idList.add("210101198303014037");
			idList.add("44030119800312625X");
			idList.add("440301198003122320");
			idList.add("150101198110018561");
			idList.add("150101198110018262");
			idList.add("23010119870601393X");

			idList.add("23010119870601625X");
			idList.add("420101198706011155");
			idList.add("420101198706012916");
			idList.add("310101198706014502");
			idList.add("310101198706012451");
			idList.add("350101198706019538");
			idList.add("350101198706015780");
			idList.add("440101198706011204");
			idList.add("440101198706015475");
			idList.add("450101198706019929");

			idList.add("450101199006018567");
			idList.add("450101199006012800");
			idList.add("460101199006017160");
			idList.add("460101199006012730");
			idList.add("51010119890601888X");
			idList.add("510101198906012859");
			idList.add("520101198906011807");
			idList.add("520101198906016886");
			idList.add("520301198906012835");
			idList.add("520301198906015243");

			idList.add("530101198802019806");
			idList.add("530101198802019689");
			idList.add("610101198806017234");
			idList.add("610101198806017971");
			idList.add("350501198806011447");
			idList.add("350501198806017822");
			idList.add("350701198806013638");
			idList.add("230401198806019104");
			idList.add("210501198806012561");
			idList.add("210501198806017303");

			idList.add("21070119930601155X");
			idList.add("210701199306019906");
			idList.add("120101199306019821");
			idList.add("120101199306018589");
			idList.add("310101199306011809");
			idList.add("310101199306017506");
			idList.add("310101199207013550");
			idList.add("310101199207014625");
			idList.add("320501199207018590");
			idList.add("32050119920701299X");

		}
	}

	/**
	 * 快递单号
	 * 
	 * @author Administrator
	 * 
	 */
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

}
