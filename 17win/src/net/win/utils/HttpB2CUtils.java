package net.win.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.win.entity.BuyerEntity;
import net.win.entity.SellerEntity;

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
import org.xml.sax.InputSource;

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
	 * 获取卖家信息
	 * 
	 * @param url
	 * @return
	 */
	public static SellerEntity getSellerInfo(SellerEntity sellerEntity,
			String platformType) throws Exception {
		Map nameSpaces = new HashMap();
		nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
		if ("1".equals(platformType)) {
			/**
			 * 淘宝
			 */
			//店铺地址
			sellerEntity
					.setShopURL("http://store.taobao.com/shop/view_shop.htm?asker=wangwang&shop_nick="
							+ URLEncoder.encode(sellerEntity.getName(), "GBK"));
			//验证是否有这个店铺
			Element node = (Element) getOneNodeByDom4j(sellerEntity
					.getShopURL(), "//xmlns:DIV[@class='error-notice']",
					nameSpaces);
			if (node != null) {
				return null;
			}
			//验证是否是旺铺卖家
			node = (Element) getOneNodeByDom4j(sellerEntity.getShopURL(),
					"//xmlns:HTML", nameSpaces);
			//没找到店铺
			if (node != null && node.getName().equalsIgnoreCase("div")) {
				return null;
			}
			List<Element> imgAndEnsureNodes = null;
			//是否旺铺
			if (node == null) {
				sellerEntity.setWinport(true);
				imgAndEnsureNodes = (List) getMutliNodeByDom4j(sellerEntity
						.getShopURL(),
						"//IMG[@class='rank'] | //A[@class='shop-ensure']",
						null);
			} else {
				sellerEntity.setWinport(false);
				imgAndEnsureNodes = (List) getMutliNodeByDom4j(
						sellerEntity.getShopURL(),
						"//xmlns:IMG[@class='rank'] | //xmlns:A[@class='shop-ensure']",
						nameSpaces);
			}
			//消保和信誉图片和信誉地址
			if (imgAndEnsureNodes.size() == 2) {
				sellerEntity.setImg(imgAndEnsureNodes.get(0).attributeValue(
						"src"));
				sellerEntity.setEnsure(true);
				sellerEntity.setCreditURL(imgAndEnsureNodes.get(0).getParent()
						.attributeValue("href"));
			} else if (imgAndEnsureNodes.size() == 1) {
				sellerEntity.setImg(imgAndEnsureNodes.get(0).attributeValue(
						"src"));
				sellerEntity.setEnsure(false);
				sellerEntity.setCreditURL(imgAndEnsureNodes.get(0).getParent()
						.attributeValue("href"));
			} else {
				return null;
			}
			//获取信誉值
			if (sellerEntity.getCreditURL() != null) {
				node = (Element) getOneNodeByDom4j(sellerEntity.getCreditURL(),
						"//xmlns:A[@id='J_SellerRate']", nameSpaces);
				if (node != null) {
					sellerEntity.setScore(Integer.parseInt(node.getTextTrim()));
				}
			}
		} else if ("2".equals(platformType)) {
			/**
			 * 拍拍
			 */
			//店铺地址
			sellerEntity.setShopURL("http://shop.paipai.com/"
					+ sellerEntity.getName());
			//验证是否有这个店铺 
			Element node = (Element) getOneNodeByDom4j(sellerEntity
					.getShopURL(),
					"//xmlns:DIV[@class='pfhlkd_path']/xmlns:A[1]", nameSpaces);
			if (node == null || "QQ商城".equalsIgnoreCase(node.getTextTrim())) {
				return null;
			}
			//消保
			sellerEntity.setWinport(true);
			//旺铺
			sellerEntity.setEnsure(true);
			//图片 和 信誉 
			List<Element> nodes = (List) getMutliNodeByDom4j(sellerEntity
					.getShopURL(),
					"//xmlns:UL[@class='ulist_rate']/xmlns:LI/xmlns:A",
					nameSpaces);
			if (nodes.size() == 2) {
				//信誉值
				sellerEntity.setScore(Integer.parseInt(nodes.get(0)
						.getTextTrim()));
				//信誉地址
				sellerEntity.setCreditURL(nodes.get(0).attributeValue("href"));
				//信誉图片
				sellerEntity.setImg(nodes.get(1).element("IMG").attributeValue(
						"src"));
			} else {
				return null;
			}
		} else {
			return null;
		}
		return sellerEntity;
	}

	/**
	 * 获取买家信息
	 * 
	 * @param url
	 * @return
	 */
	public static BuyerEntity getBuyerInfo(BuyerEntity buyerEntity,
			String platformType, Boolean isAdd) throws Exception {
		Map nameSpaces = new HashMap();
		nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
		if ("1".equals(platformType)) {
			/**
			 * 淘宝
			 */
			Element element = getOneNodeByDom4j(
					"http://member1.taobao.com/member/user_profile.jhtml?userID="
							+ URLEncoder.encode(buyerEntity.getName(), "GBK"),
					"//xmlns:UL[@class='TabBarLevel1']/xmlns:LI[2]/xmlns:A",
					nameSpaces);
			if (element == null) {
				return null;
			}
			//信誉地址和图片
			buyerEntity.setCreditURL(element.attributeValue("href"));
			List<Element> elements = getMutliNodeByDom4j(buyerEntity
					.getCreditURL(), "//xmlns:H4[@class='buyer']/xmlns:A",
					nameSpaces);
			if (elements == null || elements.size() == 0) {
				return null;
			}
			//只有信誉值，没图片
			if (elements.size() == 1) {
				buyerEntity.setImg(null);
				Element creditEle = elements.get(0);
				buyerEntity.setScore(Integer.parseInt(creditEle.getTextTrim()));
			} else {
				Element creditEle = elements.get(0);
				buyerEntity.setImg(elements.get(1).element("IMG")
						.attributeValue("src"));
				if (Integer.parseInt(creditEle.getTextTrim()) > Constant
						.getTaobaoCreditValueLimit()
						&& isAdd) {
					return null;
				} else {
					buyerEntity.setScore(Integer.parseInt(creditEle
							.getTextTrim()));
					//如果不是添加，就判断信誉是否超出 
					if (!isAdd
							&& buyerEntity.getScore() > Constant
									.getTaobaoCreditValueLimit()) {
						buyerEntity.setEnable(false);
					}
				}
			}
		} else if ("2".equals(platformType)) {
			/**
			 * 拍拍
			 */
			//信誉地址
			buyerEntity
					.setCreditURL("http://shop1.paipai.com/cgi-bin/credit_info?uin="
							+ buyerEntity.getName());
			//信誉值
			// http://shop1.paipai.com/cgi-bin/credit_info?uin=30756500&
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(buyerEntity.getCreditURL());
			httpget
					.setHeader(new BasicHeader(
							"Cookie",
							"	PPRD_S=PVS.USER-PVSE.1; pvid=3194253992; flv=10.1 r53; pgv=pgvReferrer=&ssid=s9049088248; visitkey=3625640881013513"));
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			Element node = getOneNodeByDom4j(entity.getContent(),
					"//xmlns:SPAN[@tag='userGrade']", nameSpaces);
			if (node == null
					|| (Integer.parseInt(node.attributeValue("score")) > Constant
							.getPaipaiCreditValueLimit() && isAdd)) {
				return null;
			} else {
				buyerEntity.setScore(Integer.parseInt(node
						.attributeValue("score")));
				//如果不是添加，就判断信誉是否超出 
				if (!isAdd
						&& buyerEntity.getScore() > Constant
								.getPaipaiCreditValueLimit()) {
					buyerEntity.setEnable(false);
				}
			}
			//设置图片
			//http://www.shangyi.org/news/kaidianzhishi/2011/0312/335.html 参考
			if (buyerEntity.getScore() == 0) {
				buyerEntity.setImg(null);
			} else if (buyerEntity.getScore() >= 1
					&& buyerEntity.getScore() <= 4) {
				buyerEntity.setImg("images/credit/paipai/xin_1.gif");
			} else if (buyerEntity.getScore() >= 5
					&& buyerEntity.getScore() <= 10) {
				buyerEntity.setImg("images/credit/paipai/xin_2.gif");
			} else if (buyerEntity.getScore() >= 11
					&& buyerEntity.getScore() <= 20) {
				buyerEntity.setImg("images/credit/paipai/xin_3.gif");
			} else if (buyerEntity.getScore() >= 21
					&& buyerEntity.getScore() <= 40) {
				buyerEntity.setImg("images/credit/paipai/xin_4.gif");
			} else if (buyerEntity.getScore() >= 41
					&& buyerEntity.getScore() <= 100) {
				buyerEntity.setImg("images/credit/paipai/xin_5.gif");
			} else {
				buyerEntity.setImg("images/credit/paipai/zuan_1.gif");
			}
		} else {
			return null;
		}
		return buyerEntity;
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
	private static List<Element> getMutliNodeByDom4j(String url,
			String xpathStr, Map nameSpace) {
		if (nameSpace == null) {
			nameSpace = new HashMap();
		}
		Document document = getDoc(url);// 获取document
		XPath xpath = new DefaultXPath(xpathStr);
		xpath.setNamespaceContext(new SimpleNamespaceContext(nameSpace));
		List<Element> nodes = xpath.selectNodes(document);
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
	private static Element getOneNodeByDom4j(String url, String xpathStr,
			Map nameSpace) {
		if (nameSpace == null) {
			nameSpace = new HashMap();
		}
		Document document = getDoc(url);// 获取document
		XPath xpath = new DefaultXPath(xpathStr);
		xpath.setNamespaceContext(new SimpleNamespaceContext(nameSpace));
		return (Element) xpath.selectSingleNode(document);
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
	private static List<Node> getMutliNodeByDom4j(InputStream inputStream,
			String xpathStr, Map nameSpace) {
		if (nameSpace == null) {
			nameSpace = new HashMap();
		}
		Document document = getDoc(inputStream);// 获取document
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
	private static Element getOneNodeByDom4j(InputStream inputStream,
			String xpathStr, Map nameSpace) {
		if (nameSpace == null) {
			nameSpace = new HashMap();
		}
		Document document = getDoc(inputStream);// 获取document
		XPath xpath = new DefaultXPath(xpathStr);
		xpath.setNamespaceContext(new SimpleNamespaceContext(nameSpace));
		return (Element) xpath.selectSingleNode(document);
	}

	private static Document getDoc(String url) {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(url);
		} catch (Exception e) {
			LoggerUtils.error(e);
		}
		org.w3c.dom.Document doc = parser.getDocument();
		DOMReader domReader = new DOMReader();
		Document document = domReader.read(doc);
		return document;
	}

	private static Document getDoc(InputStream inputStream) {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(new InputSource(inputStream));
		} catch (Exception e) {
			LoggerUtils.error(e);
		}
		org.w3c.dom.Document doc = parser.getDocument();
		DOMReader domReader = new DOMReader();
		Document document = domReader.read(doc);
		return document;
	}
}
