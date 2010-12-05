package net.win.utils;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public final class Constant {
	private Constant() {
	}

	static {
		initJDBC();
	}
	/**
	 * 数据库信息
	 */
	private static String jdbcUrl;
	private static String jdbcUsername;
	private static String jdbcPassword;

	// 登陆用户的信息
	public static final String USER_LOGIN_INFO = "userLogin";

	// 登陆用户的信息
	public static final String USER_ACTIVE_CODE_INFO = "userActiveCode";
	//
	public static final String RTP_I18N_LANGUAGE = "language";
	// 验证码
	public static final String VERIFY_CODE = "verifyCode";
	// EMAIL的发送地址
	public static final String FROM_EMAIL = "30756500@qq.com";

	// 一些参数配置 (数据库)
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
	private static Double huangguan_N;
	private static Double shuangzuan_N;
	private static Double zuanshi_N;
	/**
	 * vip
	 */
	private static Double vipPrice;
	private static Double vipYearRebate;

	// 接任务所扣的发布点折扣率
	private static Double receieveTaskDotRate;
	// 物流发布点个数
	private static Double logisticsDotCount;
	//信誉值上限
	private static Double creditValueLimit;
	//发布点兑换成金额
	private static Double releaseDotChangeMoney;
	//积分兑换发布点
	private static Double scoreChangeReleaseDot;
	//登录时获得的积分，非会员
	private static Double loginScore;
	//通过你的宣传链接注册的会员购买VIP，获得10个发布点
	private static Double refreeByVipReleaseDot;
	
	//当购买发布点的时候，推广人所得金额为，他推荐人买的发布点的金额的0.01
	private static Double buyReleaseDotRebateToRefree;

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
	public static Double getHuangguanNumber() {
		return huangguan_N;
	}

	public static Double getShuangzuanNumber() {
		return shuangzuan_N;
	}

	public static Double getZuanshiNumber() {
		return zuanshi_N;
	}

	public static Double getYearVipPrice() {
		return vipYearRebate;
	}

	public static Double getVipPrice() {
		return vipPrice;
	}

	public static Double getLogisticsDotCount() {
		return logisticsDotCount;
	}

	/**
	 * 获取jdbc connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
	}

	/**
	 * close jdbc
	 * 
	 * @param rs
	 * @param st
	 * @param conn
	 */
	private static void closeJDBC(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 初始化JDBC信息
	 */
	private static void initJDBC() {
		URL url = Thread.currentThread().getContextClassLoader().getResource(
				"hibernate.cfg.xml");
		SAXReader saxReader = new SAXReader(); // 使用SAXReader方式读取XML文件
		// 加载数据库XML配置文件，得到Document对象
		Document document = null;
		try {
			document = saxReader.read(url.getFile());
			Element root = document.getRootElement(); // 获得根节点
			// 得到class节点
			Element classElement = (Element) root
					.selectSingleNode("/hibernate-configuration/session-factory/property[@name='hibernate.connection.driver_class']");
			Element urlElement = (Element) root
					.selectSingleNode("/hibernate-configuration/session-factory/property[@name='hibernate.connection.url']");
			Element usernamElement = (Element) root
					.selectSingleNode("/hibernate-configuration/session-factory/property[@name='hibernate.connection.username']");
			Element passwordElement = (Element) root
					.selectSingleNode("/hibernate-configuration/session-factory/property[@name='hibernate.connection.password']");
			Class.forName(classElement.getTextTrim());
			jdbcUrl = urlElement.getTextTrim();
			jdbcUsername = usernamElement.getTextTrim();
			jdbcPassword = passwordElement.getTextTrim();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化
	 */
	public static void initMetatData() {
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement("select * from tb_property ");
			rs = st.executeQuery();
			String name = null;
			Double numberValue = null;
			String stringValue = null;
			while (rs.next()) {
				name = rs.getString("name_");
				numberValue = rs.getDouble("numberValue_");
				stringValue = rs.getString("stringValue_");
				// 发布点价格
				if ("fabudianPrice".equalsIgnoreCase(name)) {
					fabudian_P = numberValue;
				}
				// 皇冠价格
				else if ("huangguanPrice".equalsIgnoreCase(name)) {
					huangguan_P = numberValue;
				}
				// 双钻价格
				else if ("shuangzuanPrice".equalsIgnoreCase(name)) {
					shuangzuan_P = numberValue;
				}
				// 钻石价格
				else if ("zuanshikaPrice".equalsIgnoreCase(name)) {
					zuanshi_P = numberValue;
				}
				// 皇冠数量
				else if ("huangguanCount".equalsIgnoreCase(name)) {
					huangguan_N = numberValue;
				}
				// 双钻数量
				else if ("shuangzuanCount".equalsIgnoreCase(name)) {
					shuangzuan_N = numberValue;
				}
				// 钻石数量
				else if ("zuanshiCount".equalsIgnoreCase(name)) {
					zuanshi_N = numberValue;
				}
				// vip 一个月价格
				else if ("vipPrice".equalsIgnoreCase(name)) {
					vipPrice = numberValue;
				}
				// vip 一年的则扣率
				else if ("vipYearRebate".equalsIgnoreCase(name)) {
					vipYearRebate = numberValue;
				}
				// 接任务所得的发布点
				else if ("receieveTaskDotRate".equalsIgnoreCase(name)) {
					receieveTaskDotRate = numberValue;
				}
				//物流发布点个数
				else if ("logisticsDotCount".equalsIgnoreCase(name)) {
					logisticsDotCount = numberValue;
				}
				//信誉值上限
				else if ("creditValueLimit".equalsIgnoreCase(name)) {
					creditValueLimit = numberValue;
				}
				//当购买发布点的时候，推广人所得金额为，他推荐人买的发布点的金额的0.01
				else if ("buyReleaseDotRebateToRefree".equalsIgnoreCase(name)) {
					buyReleaseDotRebateToRefree = numberValue;
				}
				//发布点兑换成金额
				else if ("releaseDotChangeMoney".equalsIgnoreCase(name)) {
					releaseDotChangeMoney = numberValue;
				}
				//积分兑换成发布点
				else if ("releaseDotChangeMoney".equalsIgnoreCase(name)) {
					scoreChangeReleaseDot = numberValue;
				}
				//积分兑换成发布点
				else if ("loginScore".equalsIgnoreCase(name)) {
					loginScore = numberValue;
				}
				//通过你的宣传链接注册的会员购买VIP，获得10个发布点
				else if ("refreeByVipReleaseDot".equalsIgnoreCase(name)) {
					refreeByVipReleaseDot = numberValue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeJDBC(rs, st, conn);
		}
	}

	public static Double getReceieveTaskDotRate() {
		return receieveTaskDotRate;
	}

	public static Double getCreditValueLimit() {
		return creditValueLimit;
	}

	public static Double getBuyReleaseDotRebateToRefree() {
		return buyReleaseDotRebateToRefree;
	}

	public static Double getReleaseDotChangeMoney() {
		return releaseDotChangeMoney;
	}

	public static Double getScoreChangeReleaseDot() {
		return scoreChangeReleaseDot;
	}

	public static Double getLoginScore() {
		return loginScore;
	}

	public static Double getRefreeByVipReleaseDot() {
		return refreeByVipReleaseDot;
	}

}
