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

	// 信誉值上限
	private static Double taobaoCreditValueLimit;
	// 信誉值上限
	private static Double paipaiCreditValueLimit;

	// 登录时获得的积分，非会员
	private static Double loginScore;

	// 通过你的宣传链接注册的会员积分每上升1000 ，你的收益=100积分
	private static Double score1000Refree;
	// 支付界面
	private static String toPayPage;
	// 最大注册数
	public static Double maxRegisterCount;

	/**
	 * 系统流程控制
	 * 
	 * @return
	 */
	public static Boolean stopTask = true;
	public static Boolean stopAll = true;

	public static Double getLoginScore() {
		return loginScore;
	}

	public static Double getScore1000Refree() {
		return score1000Refree;
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
			saxReader.setValidation(false);
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

				// 登录时的积分
				if ("loginScore".equalsIgnoreCase(name)) {
					loginScore = numberValue;
				}

				// 通过你的宣传链接注册的会员积分每上升1000 ，你的收益=N积分
				else if ("score1000Refree".equalsIgnoreCase(name)) {
					score1000Refree = numberValue;
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
				//淘宝信誉值
				else if ("taobaoCreditValueLimit".equalsIgnoreCase(name)) {
					taobaoCreditValueLimit = numberValue;
				}
				//拍拍信誉值
				else if ("paipaiCreditValueLimit".equalsIgnoreCase(name)) {
					paipaiCreditValueLimit = numberValue;
				}
				// 停止所有(不包括HTML)
				else if ("stopAll".equalsIgnoreCase(name)) {
					stopAll = Boolean.parseBoolean(stringValue);
				}
				// 停止所有和任务相关的操作
				else if ("stopTask".equalsIgnoreCase(name)) {
					stopTask = Boolean.parseBoolean(stringValue);
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

	public static Boolean getStopTask() {
		return stopTask;
	}

	public static Boolean getStopAll() {
		return stopAll;
	}

	public static Double getTaobaoCreditValueLimit() {
		return taobaoCreditValueLimit;
	}

	public static Double getPaipaiCreditValueLimit() {
		return paipaiCreditValueLimit;
	}

}
