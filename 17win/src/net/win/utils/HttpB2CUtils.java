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

import net.win.entity.SellerEntity;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.DOMReader;
import org.dom4j.xpath.DefaultXPath;
import org.jaxen.SimpleNamespaceContext;
import org.xml.sax.SAXException;

@SuppressWarnings("unchecked")
public final class HttpB2CUtils {
	private static HttpClient taobaoHttpClient = createHttpClient("taobao");
	private static HttpClient paipaiHttpClient = createHttpClient("paipai");
	private static HttpClient youaHttpClient = createHttpClient("youa");

	// 店铺地址验证
	private static final String TAOBAO_SHOP_REGEX = "^http:[/\\\\]{2}\\w+\\-*\\w+\\.taobao\\.com[/\\\\]?";
	private static final String PAIPAI_SHOP_REGEX = "^http:[/\\\\]{2}\\w+\\-*\\w+\\.paipai\\.com[/\\\\]?";
	private static final String YOUA_SHOP_REGEX = "^http:[/\\\\]{2}youa.baidu\\.com[/\\\\]?";

	// 商品地址验证
	private static final String TAOBAO_ITEM_REGEX = "((^http:[/\\\\]{2}item\\.taobao\\.com[/\\\\]item.htm)|(^http:[/\\\\]{2}item\\.taobao\\.com[/\\\\]auction[/\\\\]item))";
	private static final String PAIPAI_ITEM_REGEX = "^http:[/\\\\]{2}auction\\d\\.paipai\\.com[/\\\\]";
	private static final String YOUA_ITEM_REGEX = "^http:[/\\\\]{2}youa.baidu\\.com[/\\\\]item";

	// 账号获取
	private static final String TAOBAO_USER_REGEX = "data\\-nick=\"(.+)\" data-tnick=";

	// private static final String PAIPAI_USER_REGEX =
	// "<litagid='HOME_PAGE'><ahref='http://(\\d+)\\.paipai\\.com/";
	// private static final String YOUA_USER_REGEX =
	// "uname=\"([[\u0391-\uFFE5]\\w_]+)\"";

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
			host = new HttpHost("youa.baidu.com", 80);
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
		String line;
		// 淘宝
		if ("1".equals(type)) {
			HttpEntity entity = getContext(url, type);
			BufferedReader br = new BufferedReader(new InputStreamReader(entity
					.getContent(), "GBK"));
			Pattern pattern = Pattern.compile(TAOBAO_USER_REGEX);
			Matcher matcher;
			OUTTER : while ((line = br.readLine()) != null) {
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = URLDecoder.decode(matcher.group(1), "UTF-8");
					break OUTTER;
				}
			}
			if (entity != null) {
				entity.consumeContent();
			}
		}
		// 拍拍
		else if ("2".equals(type)) {
			Map nameSpaces = new HashMap();
			nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
			Element element = (Element) getOneNodeByDom4j(url,
					"//xmlns:DIV[@class='fr_sidebar']/xmlns:DIV[1]", nameSpaces);
			if (element != null) {
				seller = element.attributeValue("shopid");
			}
		}
		// 有啊
		else if ("3".equals(type)) {
			Node node = getOneNodeByDom4j(url, "//UL/LI[@class='hi-me']/A[1]",
					null);
			if (node != null) {
				seller = node.getText().trim();
			}
		}
		if (seller == null) {
			seller = "";
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
		// 淘宝
		if ("1".equals(type)) {
			HttpEntity entity = getContext(url, type);
			BufferedReader br = new BufferedReader(new InputStreamReader(entity
					.getContent(), "GBK"));
			String line;
			Pattern pattern = Pattern.compile(TAOBAO_USER_REGEX);
			Matcher matcher;
			OUTTER : while ((line = br.readLine()) != null) {
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = URLDecoder.decode(matcher.group(1), "UTF-8");
					break OUTTER;
				}
			}
			if (br != null) {
				br.close();
			}
			if (entity != null) {
				entity.consumeContent();
			}
		}
		// 拍拍
		else if ("2".equals(type)) {
			Map nameSpaces = new HashMap();
			nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
			Node node = getOneNodeByDom4j(
					url,
					"//xmlns:UL[@class='ulist_basic']/xmlns:LI[1]/xmlns:SPAN[@class='uc nickname']",
					nameSpaces);
			if (node != null) {
				seller = node.getText().trim().replaceAll("[\\(\\)]", "");
			}
		}
		// 有啊
		else if ("3".equals(type)) {
			Node node = getOneNodeByDom4j(url, "//LI[@class='uname']/A[1]",
					null);
			if (node != null) {
				seller = node.getText().trim();
			}
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
	public static Integer obtainCreditValue(String url, String type)
			throws Exception {
		Map nameSpaces = new HashMap();
		nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
		// 淘宝
		if ("1".equals(type)) {
			Node node = getOneNodeByDom4j(url,
					"//xmlns:LI[@class='credit']/xmlns:A[1]", nameSpaces);
			if (node == null) {
				return -1;
			} else {
				return Integer.parseInt(node.getText().trim());
			}
		}
		// 拍拍
		else if ("2".equals(type)) {
			Integer score = -1;
			// http://shop1.paipai.com/cgi-bin/credit_info?uin=30756500&
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			httpget
					.setHeader(new BasicHeader(
							"Cookie",
							"	PPRD_S=PVS.USER-PVSE.1; pvid=3194253992; flv=10.1 r53; pgv=pgvReferrer=&ssid=s9049088248; visitkey=3625640881013513"));

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity
					.getContent(), "GBK"));
			String buyer = null;
			String line;

			// <li id="userNickname"><span
			// class="name">买家昵称：</span><strong>Dreamway<span
			// class="imstatic"></span></strong></li>
			Pattern buyerPattern = Pattern
					.compile("<li id='userNickname'><span class='name'>买家昵称：</span><strong>(.+)<span class='imstatic'></span></strong></li>");

			// <span tagvar="buyer" score="20" tag="userGrade" ></span>
			Pattern scorePattern = Pattern
					.compile("<span tagvar='buyer'  score='(\\d+)' tag='userGrade' >");
			Matcher matcher;
			Matcher matcher2;
			OUTTER : while ((line = br.readLine()) != null) {
				line = line.replaceAll("\"", "'");
				matcher2 = scorePattern.matcher(line);
				while (matcher2.find()) {
					score = Integer.parseInt(matcher2.group(1));
					break OUTTER;
				}
			}
			if (br != null) {
				br.close();
			}
			httpget.abort();
			httpclient.getConnectionManager().shutdown();
			return score;
		}
		// 有啊
		else if ("3".equals(type)) {
			return 0;
		}
		return -1;
	}

	/**
	 * 获取淘宝的信誉地址
	 * 
	 * @param url
	 * @return
	 */
	public static String getTaobaoCreditURL(String url) {
		Map nameSpaces = new HashMap();
		nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
		Element node = (Element) getOneNodeByDom4j(url,
				"//xmlns:UL[@class='TabBarLevel1']/xmlns:LI[2]/xmlns:A",
				nameSpaces);
		if (node == null) {
			return null;
		} else {
			return node.attributeValue("href");
		}
	}

	/**
	 * 获取卖家是否皇冠，是否消保
	 * 
	 * @param url
	 * @return
	 */
	public static SellerEntity getSellerInfo(SellerEntity sellerEntity,
			String platformType) {
		if ("1".equals(platformType)) {
			//获取信誉 和消保
			String sellerURL1 = "http://member1.taobao.com/member/user_profile.jhtml?userID="
					+ sellerEntity.getName();
			List<Element> nodes = (List) getMutliNodeByDom4j(
					sellerURL1,
					"//DIV[@class='infocard']/DL[1]/DD[1]/UL[1]/LI[3]/A[1] | //A[@class='shop-ensure']",
					null);
			if (nodes.size() == 2) {
				//旺铺
				sellerEntity.setSellerScore(Integer.parseInt(nodes.get(1)
						.getTextTrim()));
				sellerEntity.setIsEnsure(true);
				//获取店铺地址
				HttpClient httpClient = taobaoHttpClient;
				HttpGet httpGet = new HttpGet(
						"http://member1.taobao.com/member/user_profile.jhtml?userID="
								+ sellerEntity.getName());
				HttpResponse response;
				try {
					response = httpClient.execute(httpGet);
					Header header = response.getFirstHeader("Location");
					if (header != null) {
						String url = header.getValue();
						String shopURL = url.split("/shop")[0];
						sellerEntity.setShopURL(shopURL);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (nodes.size() == 1) {
				//非旺铺获取信誉
				sellerEntity.setSellerScore(Integer.parseInt(nodes.get(0)
						.getTextTrim()));
				sellerEntity.setIsEnsure(false);
			}
		} else if ("2".equals(platformType)) {
			sellerEntity.setShopURL("http://shop.paipai.com/"
					+ sellerEntity.getName());
			//获取信誉 和 消保
			Map nameSpaces = new HashMap();
			nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
			Element node = (Element) getOneNodeByDom4j(
					sellerEntity.getShopURL(),
					"//xmlns:DIV[@class='pfhlkd_uinfo']/xmlns:UL[@class='ulist_rate']/xmlns:LI[1]/xmlns:A[1]",
					nameSpaces);
			if (node != null) {
				sellerEntity.setSellerScore(Integer
						.parseInt(node.getTextTrim()));
				sellerEntity.setIsEnsure(true);
			}
		}
		return sellerEntity;
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
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpGet);
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
		if (nameSpace == null) {
			nameSpace = new HashMap();
		}
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
		if (nameSpace == null) {
			nameSpace = new HashMap();
		}
		Document document = getDoc(url);// 获取document
		XPath xpath = new DefaultXPath(xpathStr);
		xpath.setNamespaceContext(new SimpleNamespaceContext(nameSpace));
		Node node = xpath.selectSingleNode(document);
		return node;
	}

	public static Document getDoc(String url) {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(url);
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		org.w3c.dom.Document doc = parser.getDocument();
		DOMReader domReader = new DOMReader();
		Document document = domReader.read(doc);
		return document;
	}
}
