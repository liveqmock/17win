package net.win.utils;

import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public final class Constant {
	private Constant() {
	}

	static {
		initMetatData();
	}
	// 登陆用户的信息
	public static final String USER_LOGIN_INFO = "userLogin";
	//
	public static final String RTP_I18N_LANGUAGE = "language";
	// 验证码
	public static final String VERIFY_CODE = "verifyCode";
	// EMAIL的发送地址
	public static final String FROM_EMAIL = "30756500@qq.com";

	// 一些参数配置

	/**
	 * 价格
	 */
	private static Double fabudian_P;
	private static Double huangguan_P;
	private static Double shuangzuan_P;
	private static Double zuanshi_P;
	/**
	 * 数量
	 */
	private static Integer huangguan_N;
	private static Integer shuangzuan_N;
	private static Integer yizuan_N;

	/**
	 * 任务相关
	 * 
	 * @return
	 */
	private static Integer taskOverReleaseScore;
	private static Integer taskOverReceiveScore;

	public static Double getFabudianPrice() {
		return fabudian_P;
	}

	public static Double getHuangguanPrice() {
		return huangguan_P;
	}

	public static Double getShuangzuanPrice() {
		return shuangzuan_P;
	}

	public static Double getZuanshiPrice() {
		return zuanshi_P;
	}

	// //
	public static Integer getHuangguanNumber() {
		return huangguan_N;
	}

	public static Integer getShuangzuanNumber() {
		return shuangzuan_N;
	}

	public static Integer getZuanshiNumber() {
		return yizuan_N;
	}

	public static Integer getTaskOverReceiveScore() {
		return taskOverReceiveScore;
	}

	public static Integer getTaskOverReleaseScore() {
		return taskOverReleaseScore;
	}

	/**
	 * 初始化
	 */
	private static void initMetatData() {
		URL url = Thread.currentThread().getContextClassLoader().getResource(
				"win-metadata.xml");
		SAXReader saxReader = new SAXReader(); // 使用SAXReader方式读取XML文件
		// 加载数据库XML配置文件，得到Document对象
		Document document = null;
		;
		try {
			document = saxReader.read(url.getFile());
		} catch (DocumentException e) {
			LoggerUtils.error(e);
		}
		Element root = document.getRootElement(); // 获得根节点
		// 得到money节点
		Element fabudian = (Element) root
				.selectSingleNode("/win/price/fabudian");
		fabudian_P = Double.parseDouble(fabudian.getText());
		Element huangguanka = (Element) root
				.selectSingleNode("/win/price/huangguanKa");
		huangguan_P = Double.parseDouble(huangguanka.getText());
		Element shuangzuanka = (Element) root
				.selectSingleNode("/win/price/shuangzuanka");
		shuangzuan_P = Double.parseDouble(shuangzuanka.getText());
		Element zuanshika = (Element) root
				.selectSingleNode("/win/price/zuanshika");
		zuanshi_P = Double.parseDouble(zuanshika.getText());

		// 得到数量
		Element huangguanka_N = (Element) root
				.selectSingleNode("/win/number/huangguanKa");
		huangguan_N = Integer.parseInt(huangguanka_N.getText());
		Element shuangzuanka_N = (Element) root
				.selectSingleNode("/win/number/shuangzuanka");
		shuangzuan_N = Integer.parseInt(shuangzuanka_N.getText());
		Element zuanshika_N = (Element) root
				.selectSingleNode("/win/number/zuanshika");
		yizuan_N = Integer.parseInt(zuanshika_N.getText());

		// 任务
		Element taskOverReleaseScoreEke = (Element) root
				.selectSingleNode("/win/other/taskOverReleaseScore");
		taskOverReleaseScore = Integer.parseInt(taskOverReleaseScoreEke
				.getText());
		Element taskOverReceiveScoreE = (Element) root
				.selectSingleNode("/win/other/taskOverReceiveScore");
		taskOverReceiveScore = Integer
				.parseInt(taskOverReceiveScoreE.getText());
	}

}
