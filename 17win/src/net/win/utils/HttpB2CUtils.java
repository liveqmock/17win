package net.win.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.DOMReader;
import org.dom4j.xpath.DefaultXPath;
import org.jaxen.SimpleNamespaceContext;
import org.xml.sax.SAXException;

public final class HttpB2CUtils {
	private static HttpClient taobaoHttpClient = createHttpClient("taobao");
	private static HttpClient paipaiHttpClient = createHttpClient("paipai");
	private static HttpClient youaHttpClient = createHttpClient("youa");

	// 店铺地址验证
	private static final String TAOBAO_SHOP_REGEX = "^http:[/\\\\]{2}\\w+\\-*\\w+\\.taobao\\.com[/\\\\]?";
	private static final String PAIPAI_SHOP_REGEX = "^http:[/\\\\]{2}\\w+\\-*\\w+\\.paipai\\.com[/\\\\]?";
	private static final String YOUA_SHOP_REGEX = "^http:[/\\\\]{2}youa.baidu\\.com[/\\\\]?";

	// 商品地址验证
	private static final String TAOBAO_ITEM_REGEX = "^http:[/\\\\]{2}item\\.taobao\\.com[/\\\\]item.htm";
	private static final String PAIPAI_ITEM_REGEX = "^http:[/\\\\]{2}auction1\\.paipai\\.com[/\\\\]search";
	private static final String YOUA_ITEM_REGEX = "^http:[/\\\\]{2}youa.baidu\\.com[/\\\\]item";

	// 账号获取
	private static final String TAOBAO_USER_REGEX = "data\\-nick=\"(.+)\" data-tnick=";
	private static final String PAIPAI_USER_REGEX = "<litagid='HOME_PAGE'><ahref='http://(\\d+)\\.paipai\\.com/";
	private static final String YOUA_USER_REGEX = "uname=\"([[\u0391-\uFFE5]\\w_]+)\"";

	// 信誉地址验证
	private static final String TAOBAO_CREDIT_URL = "^http:[/\\\\]{2}rate\\.taobao\\.com[/\\\\]rate.htm\\?user_id=\\d+";
	private static final String PAIPAI_CREDIT_URL = "^http:[/\\\\]{2}shop\\d+\\.paipai\\.com[/\\\\]cgi\\-bin[/\\\\]credit_info\\?uin=\\d+&";

	private HttpB2CUtils() {

	}

	/**
	 * 创建httppClient
	 * 
	 * @param flag
	 * @return
	 */
	private static HttpClient createHttpClient(String flag) {
		HttpParams params = new BasicHttpParams();
		// Increase max total connection to 200
		ConnManagerParams.setMaxTotalConnections(params, 200);
		// Increase default max connection per route to 20
		ConnPerRouteBean connPerRoute = new ConnPerRouteBean(20);
		// Increase max connections for localhost:80 to 50
		HttpHost host = null;
		if ("taobao".equals(flag)) {
			host = new HttpHost("www.taobao.com", 80);
		}
		if ("paipai".equals(flag)) {
			host = new HttpHost("www.paipai.com", 80);
		}
		if ("youa".equals(flag)) {
			host = new HttpHost("www.youa.com", 80);
		}
		connPerRoute.setMaxForRoute(new HttpRoute(host), 50);
		ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory
				.getSocketFactory(), 443));
		ClientConnectionManager cm = new ThreadSafeClientConnManager(params,
				schemeRegistry);
		return new DefaultHttpClient(cm, params);
	}

	/**
	 * 获取商品类型
	 * 
	 * @return 0 非法地址 1 淘宝 2 拍拍 3 有啊
	 * @throws Exception
	 */
	public static String obtainItemType(String url) throws Exception {
		if (Pattern.compile(TAOBAO_ITEM_REGEX).matcher(url).find()) {
			return "1";
		}
		if (Pattern.compile(PAIPAI_ITEM_REGEX).matcher(url).find()) {
			return "2";
		}
		if (Pattern.compile(YOUA_ITEM_REGEX).matcher(url).find()) {
			return "3";
		}
		return "0";
	}

	/**
	 * 获取店铺类型
	 * 
	 * @return 0 非法地址 1 淘宝 2 拍拍 3 有啊
	 * @throws Exception
	 */
	public static String obtainShopType(String url) throws Exception {
		if (Pattern.compile(TAOBAO_SHOP_REGEX).matcher(url).find()) {
			return "1";
		}
		if (Pattern.compile(PAIPAI_SHOP_REGEX).matcher(url).find()) {
			return "2";
		}
		if (Pattern.compile(YOUA_SHOP_REGEX).matcher(url).find()) {
			return "3";
		}
		return "0";
	}

	/**
	 * 获取账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String obtainSellerByShop(String url, String type)
			throws Exception {
		String seller = "";
		// 判断输入的url地址的类型和type是否相等
		if (!obtainShopType(url).equals(type)) {
			return "";
		}
		HttpEntity entity = getContext(url, type);
		BufferedReader br = new BufferedReader(new InputStreamReader(entity
				.getContent(), "GBK"));
		String line;
		// 淘宝
		if ("1".equals(type)) {
			Pattern pattern = Pattern.compile(TAOBAO_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = URLDecoder.decode(matcher.group(1), "UTF-8");
					break OUTTER;
				}
			}
		}
		// 拍拍
		else if ("2".equals(type)) {
			Pattern pattern = Pattern.compile(PAIPAI_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				line = StringUtils.replaceBlank(line.replaceAll("\"", "'"));
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = URLDecoder.decode(matcher.group(1), "UTF-8");
					break OUTTER;
				}
			}
		}
		// 有啊
		else if ("3".equals(type)) {
			Pattern pattern = Pattern.compile(YOUA_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = URLDecoder.decode(matcher.group(1), "UTF-8");
					break OUTTER;
				}
			}
		}
		if (entity != null) {
			entity.consumeContent();
		}
		return seller;
	}

	/**
	 * 获取账号商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String obtainSellerByItem(String url, String type)
			throws Exception {
		String seller = "";
		// 判断输入的url地址的类型和type是否相等
		if (!obtainItemType(url).equals(type)) {
			return "";
		}
		HttpEntity entity = getContext(url, type);
		BufferedReader br = new BufferedReader(new InputStreamReader(entity
				.getContent(), "GBK"));
		String line;
		// 淘宝
		if ("1".equals(type)) {
			Pattern pattern = Pattern.compile(TAOBAO_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = URLDecoder.decode(matcher.group(1), "UTF-8");
					break OUTTER;
				}
			}
		}
		// 拍拍
		else if ("2".equals(type)) {
			Pattern pattern = Pattern.compile(PAIPAI_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				line = StringUtils.replaceBlank(line.replaceAll("\"", "'"));
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = URLDecoder.decode(matcher.group(1), "UTF-8");
					break OUTTER;
				}
			}
		}
		// 有啊
		else if ("3".equals(type)) {
			Pattern pattern = Pattern.compile(YOUA_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = URLDecoder.decode(matcher.group(1), "UTF-8");
					break OUTTER;
				}
			}
		}
		if (entity != null) {
			entity.consumeContent();
		}
		return seller;
	}

	/**
	 * 获取买家信誉值 -1表示错误 0表示有啊
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Integer obtainCreditValue(String username, String url,
			String type) throws Exception {
		Map nameSpaces = new HashMap();
		nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
		// 淘宝
		if ("1".equals(type)) {
			if (!url.matches(TAOBAO_CREDIT_URL)) {
				return -1;
			} else {
				List<Node> nodes = getMutliNodeByDom4j(
						url,
						"//xmlns:A[starts-with(@href,'http://store.taobao.com/shop/')]|//xmlns:A[@id='J_BuyerRate']|a[@id='J_BuyerRate']",
						nameSpaces);
				if (nodes.size() != 2) {
					return -1;
				} else {
					if (!username.equals(nodes.get(0).getText().trim())) {
						return -1;
					} else {
						return Integer.parseInt(nodes.get(1).getText().trim());
					}
				}
			}
		}
		// 拍拍
		else if ("2".equals(type)) {
			if (!url.matches(PAIPAI_CREDIT_URL)) {
				return -1;
			} else {
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(url);
				httpget
						.setHeader(new BasicHeader(
								"Cookie",
								"	PPRD_S=PVS.USER-PVSE.1; pvid=3194253992; flv=10.1 r53; pgv=pgvReferrer=&ssid=s9049088248; visitkey=3625640881013513"));

				HttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						entity.getContent(), "GBK"));
				String buyer = null;
				String line;
				Pattern pattern = Pattern
						.compile("<li id='userNickname'><span class='name'>买家昵称：</span><strong>(.+)<span class='imstatic'></span></strong></li>");

				Pattern pattern2 = Pattern
						.compile("<a href='http:[/\\\\]{2}shop\\d+\\.paipai\\.com[/\\\\]cgi\\-bin[/\\\\]credit_info\\?uin=\\d+' target='_blank'>(\\d+)</a>");
				Matcher matcher;
				Matcher matcher2;
				OUTTER: while ((line = br.readLine()) != null) {
					line = line.replaceAll("\"", "'");
					matcher = pattern.matcher(line);
					matcher2 = pattern2.matcher(line);
					while (matcher.find()) {
						buyer = matcher.group(1);
						if (!buyer.equals(username)) {
							return -1;
						} else {
							break OUTTER;
						}
					}
					while (matcher2.find()) {
						return Integer.parseInt(matcher.group(1));
					}
				}
				br.close();
				httpget.abort();
				httpclient.getConnectionManager().shutdown();
			}
		}
		// 有啊
		else if ("3".equals(type)) {
			return 0;
		}
		return -1;
	}

	/**
	 * 获取内容
	 * 
	 * @param url
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private static HttpEntity getContext(String url, String type)
			throws IOException, ClientProtocolException {
		HttpClient httpClient = null;
		if ("1".equals(type)) {
			httpClient = taobaoHttpClient;
		}
		if ("2".equals(type)) {
			httpClient = paipaiHttpClient;
		}
		if ("3".equals(type)) {
			httpClient = youaHttpClient;
		}
		HttpPost httRequest = new HttpPost(url);
		HttpResponse response = httpClient.execute(httRequest);
		HttpEntity entity = response.getEntity();
		return entity;
	}

	/**
	 * 获取多节点
	 * 
	 * @param url
	 * @param xpathStr
	 * @param nameSpace
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static List<Node> getMutliNodeByDom4j(String url, String xpathStr,
			Map nameSpace) {
		Document document = getDoc(url);// 获取document
		XPath xpath = new DefaultXPath(xpathStr);
		xpath.setNamespaceContext(new SimpleNamespaceContext(nameSpace));
		List<Node> nodes = xpath.selectNodes(document);
		return nodes;
	}

	/**
	 * 获取单节点
	 * 
	 * @param url
	 * @param xpathStr
	 * @param nameSpace
	 * @return
	 */
	private static Node getOneNodeByDom4j(String url, String xpathStr,
			Map nameSpace) {
		Document document = getDoc(url);// 获取document
		Map nameSpaces = new HashMap();
		XPath xpath = new DefaultXPath(xpathStr);
		xpath.setNamespaceContext(new SimpleNamespaceContext(nameSpaces));
		Node node = xpath.selectSingleNode(document);
		return node;
	}

	public static Document getDoc(String url) {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(url);
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		org.w3c.dom.Document doc = parser.getDocument();

		// SAXReader reader = new SAXReader();
		DOMReader domReader = new DOMReader();
		Document document = domReader.read(doc);
		return document;
	}
}
