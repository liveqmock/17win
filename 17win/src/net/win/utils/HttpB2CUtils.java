package net.win.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public final class HttpB2CUtils {
	private static HttpClient taobaoHttpClient = createHttpClient("taobao");
	private static HttpClient paipaiHttpClient = createHttpClient("paipai");
	private static HttpClient youaHttpClient = createHttpClient("youa");

	// 地址验证
	private static final String TAOBAO_SHOP_REGEX = "^http:[/\\\\]{2}\\w+\\-*\\w+\\.taobao\\.com[/\\\\]?";
	private static final String PAIPAI_SHOP_REGEX = "^http:[/\\\\]{2}\\w+\\-*\\w+\\.paipai\\.com[/\\\\]?";
	private static final String YOUA_SHOP_REGEX = "^http:[/\\\\]{2}youa.baidu\\.com[/\\\\]?";

	// 商品地址验证
	private static final String TAOBAO_ITEM_REGEX = "^http:[/\\\\]{2}item\\.taobao\\.com[/\\\\]item.htm";
	private static final String PAIPAI_ITEM_REGEX = "^http:[/\\\\]{2}auction1\\.paipai\\.com[/\\\\]search";
	private static final String YOUA_ITEM_REGEX = "^http:[/\\\\]{2}youa.baidu\\.com[/\\\\]item";

	// 账号获取
	private static final String TAOBAO_USER_REGEX = "data\\-nick=\"([[\u0391-\uFFE5]\\w_]+)\"";
	private static final String PAIPAI_USER_REGEX = "<litagid='HOME_PAGE'><ahref='http://(\\d+)\\.paipai\\.com/";
	private static final String YOUA_USER_REGEX = "uname=\"([[\u0391-\uFFE5]\\w_]+)\"";

	private HttpB2CUtils() {

	}

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
				.getContent(), "UTF-8"));
		String line;
		// TB
		if ("1".equals(type)) {
			Pattern pattern = Pattern.compile(TAOBAO_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				line = URLDecoder.decode(line, "UTF-8");
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = matcher.group(1);
					break OUTTER;
				}
			}
		}
		// PP
		else if ("2".equals(type)) {
			Pattern pattern = Pattern.compile(PAIPAI_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				line = URLDecoder.decode(line, "UTF-8");
				line = StringUtils.replaceBlank(line.replaceAll("\"", "'"));
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = matcher.group(1);
					break OUTTER;
				}
			}
		}
		// YA
		else if ("3".equals(type)) {
			Pattern pattern = Pattern.compile(YOUA_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				line = URLDecoder.decode(line, "UTF-8");
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = matcher.group(1);
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
				.getContent(), "UTF-8"));
		String line;
		// TB
		if ("1".equals(type)) {
			Pattern pattern = Pattern.compile(TAOBAO_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				line = URLDecoder.decode(line, "UTF-8");
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = matcher.group(1);
					break OUTTER;
				}
			}
		}
		// PP
		else if ("2".equals(type)) {
			Pattern pattern = Pattern.compile(PAIPAI_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				line = URLDecoder.decode(line, "UTF-8");
				line = StringUtils.replaceBlank(line.replaceAll("\"", "'"));
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = matcher.group(1);
					break OUTTER;
				}
			}
		}
		// YA
		else if ("3".equals(type)) {
			Pattern pattern = Pattern.compile(YOUA_USER_REGEX);
			Matcher matcher;
			OUTTER: while ((line = br.readLine()) != null) {
				line = URLDecoder.decode(line, "UTF-8");
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					seller = matcher.group(1);
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
}
