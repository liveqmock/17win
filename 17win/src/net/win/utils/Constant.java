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

	public static final String WIN17_TOKEN = "win17_token";

	// 登陆用户的信息
	public static final String USER_ACTIVE_CODE_INFO = "userActiveCode";
	//
	public static final String RTP_I18N_LANGUAGE = "language";
	// 验证码
	public static final String VERIFY_CODE = "verifyCode";

	// 一些参数配置 (数据库)
	/**
	 * 邮箱
	 */
	private static String winEmail;
	private static String xgjEmail;
	private static Integer winEmailPort;
	private static String winEmailHost;
	private static String winEmailPassword;
	private static String winEmailUsername;
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
	// 信誉值上限
	private static Double creditValueLimit;
	// 发布点兑换成金额
	private static Double releaseDotChangeMoney;
	// 积分兑换发布点
	private static Double scoreChangeReleaseDot;
	// 登录时获得的积分，非会员
	private static Double loginScore;
	// 通过你的宣传链接注册的会员购买VIP，你获得的金额
	private static Double refreeByVipMoney;

	// 当购买发布点的时候，推广人所得金额为，他推荐人买的发布点的金额的0.01
	private static Double buyReleaseDotRebateToRefree;

	// 积累接受100个任务 推广人获取10元钱
	private static Double task100RefreeMoney;
	// 通过你的宣传链接注册的会员积分每上升1000 ，你的收益=100积分
	private static Double score1000Refree;

	// 初始化用户金额
	private static Double initUserMoney;
	// 初始化用户发布点
	private static Double initUserReleaseDot;
	// 支付界面
	private static String toPayPage;
	// 最大注册数
	public static Double maxRegisterCount;

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

	public static Double getVipYearRebate() {
		return vipYearRebate;
	}

	public static Double getVipPrice() {
		return vipPrice;
	}

	public static Double getLogisticsDotCount() {
		return logisticsDotCount;
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

	public static Double getRefreeByVipMoney() {
		return refreeByVipMoney;
	}

	public static Double getTask100RefreeMoney() {
		return task100RefreeMoney;
	}

	public static Double getScore1000Refree() {
		return score1000Refree;
	}

	public static Double getInitUserMoney() {
		return initUserMoney;
	}

	public static Double getInitUserReleaseDot() {
		return initUserReleaseDot;
	}

	public static String getWinEmail() {
		return winEmail;
	}

	public static String getXgjEmail() {
		return xgjEmail;
	}

	public static Integer getWinEmailPort() {
		return winEmailPort;
	}

	public static String getWinEmailHost() {
		return winEmailHost;
	}

	public static String getWinEmailPassword() {
		return winEmailPassword;
	}

	public static String getWinEmailUsername() {
		return winEmailUsername;
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
				// 物流发布点个数
				else if ("logisticsDotCount".equalsIgnoreCase(name)) {
					logisticsDotCount = numberValue;
				}
				// 信誉值上限
				else if ("creditValueLimit".equalsIgnoreCase(name)) {
					creditValueLimit = numberValue;
				}
				// 当购买发布点的时候，推广人所得金额为，他推荐人买的发布点的金额的0.01
				else if ("buyReleaseDotRebateToRefree".equalsIgnoreCase(name)) {
					buyReleaseDotRebateToRefree = numberValue;
				}
				// 发布点兑换成金额
				else if ("releaseDotChangeMoney".equalsIgnoreCase(name)) {
					releaseDotChangeMoney = numberValue;
				}
				// 积分兑换成发布点
				else if ("scoreChangeReleaseDot".equalsIgnoreCase(name)) {
					scoreChangeReleaseDot = numberValue;
				}
				// 积分兑换成发布点
				else if ("loginScore".equalsIgnoreCase(name)) {
					loginScore = numberValue;
				}
				// 通过你的宣传链接注册的会员购买VIP，获得n元
				else if ("refreeByVipMoney".equalsIgnoreCase(name)) {
					refreeByVipMoney = numberValue;
				}
				// 积累接受100个任务 推广人获取N元钱
				else if ("task100RefreeMoney".equalsIgnoreCase(name)) {
					task100RefreeMoney = numberValue;
				}
				// 通过你的宣传链接注册的会员积分每上升1000 ，你的收益=N积分
				else if ("score1000Refree".equalsIgnoreCase(name)) {
					score1000Refree = numberValue;
				}
				//
				else if ("initUserMoney".equalsIgnoreCase(name)) {
					initUserMoney = numberValue;
				}
				//
				else if ("initUserReleaseDot".equalsIgnoreCase(name)) {
					initUserReleaseDot = numberValue;
				}
				// 谢国俊监控邮箱
				else if ("xgjEmail".equalsIgnoreCase(name)) {
					xgjEmail = stringValue;
				}
				// 系统管理员邮箱
				else if ("winEmail".equalsIgnoreCase(name)) {
					winEmail = stringValue;
				}
				// 系统管理员邮箱端口
				else if ("winEmailPort".equalsIgnoreCase(name)) {
					winEmailPort = numberValue.intValue();
				}
				// 系统管理员邮箱主机
				else if ("winEmailHost".equalsIgnoreCase(name)) {
					winEmailHost = stringValue;
				}
				// 系统管理员邮箱密码
				else if ("winEmailPassword".equalsIgnoreCase(name)) {
					winEmailPassword = stringValue;
				}
				// 系统管理员邮箱用户名
				else if ("winEmailUsername".equalsIgnoreCase(name)) {
					winEmailUsername = stringValue;
				}
				// 充值页面
				else if ("toPayPage".equalsIgnoreCase(name)) {
					toPayPage = stringValue;
				}
				// 最大注册数
				else if ("maxRegisterCount".equalsIgnoreCase(name)) {
					maxRegisterCount = numberValue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeJDBC(rs, st, conn);
		}
	}

	public static String getToPayPage() {
		return toPayPage;
	}

	public static Double getMaxRegisterCount() {
		return maxRegisterCount;
	}

}
