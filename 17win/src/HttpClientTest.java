import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.win.utils.LoggerUtils;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.DOMReader;
import org.dom4j.xpath.DefaultXPath;
import org.jaxen.SimpleNamespaceContext;
import org.xml.sax.InputSource;

@SuppressWarnings( { "unchecked", "unused" })
public class HttpClientTest {
	public static void main(String[] args) throws Exception {
		Map nameSpaces = new HashMap();
		nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://login.taobao.com/");
		HttpResponse response = httpclient.execute(httpPost);
		Map<String, String> cookies = new HashMap<String, String>();
		String token = null;
		Header[] headers = response.getHeaders("Set-Cookie");
		for (Header header : headers) {
			for (HeaderElement he : header.getElements()) {
				if (he.getName().equalsIgnoreCase("_tb_token_")) {
					token = he.getValue();
				}
				cookies.put(he.getName(), he.getValue());
			}
		}
		if (response.getEntity() != null) {
			response.getEntity().consumeContent();
		}
		httpPost = new HttpPost("http://login.taobao.com/member/login.jhtml");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("_tb_token_", token));
		nvps.add(new BasicNameValuePair("action", "Authenticator"));
		nvps.add(new BasicNameValuePair("callback", ""));
		nvps.add(new BasicNameValuePair("css_style", ""));
		nvps.add(new BasicNameValuePair("CtrlVersion", "1,0,0,7"));
		nvps.add(new BasicNameValuePair("event_submit_do_login", "anything"));
		nvps.add(new BasicNameValuePair("fc", "2"));
		nvps.add(new BasicNameValuePair("from", "tb"));
		nvps.add(new BasicNameValuePair("from_encoding", ""));
		nvps.add(new BasicNameValuePair("guf", ""));
		nvps.add(new BasicNameValuePair("gvfdcname", "10"));
		nvps.add(new BasicNameValuePair("isIgnore", ""));
		nvps.add(new BasicNameValuePair("llnick", ""));
		nvps.add(new BasicNameValuePair("loginType", "3"));
		nvps.add(new BasicNameValuePair("longLogin", "-1"));
		nvps.add(new BasicNameValuePair("minipara", ""));
		nvps.add(new BasicNameValuePair("minititle", ""));
		nvps.add(new BasicNameValuePair("need_sign", ""));
		nvps.add(new BasicNameValuePair("need_user_id", ""));
		nvps.add(new BasicNameValuePair("not_duplite_str", ""));
		nvps.add(new BasicNameValuePair("popid", ""));
		nvps.add(new BasicNameValuePair("poy", ""));
		nvps.add(new BasicNameValuePair("pstrong", "1"));
		nvps.add(new BasicNameValuePair("sign", ""));
		nvps.add(new BasicNameValuePair("style", "default"));
		nvps.add(new BasicNameValuePair("support", "000001"));
		//		XOR_1_000000000000000000000000000000_63584452310E790579010679
		nvps
				.add(new BasicNameValuePair("tid",
						"XOR_1_000000000000000000000000000000_63584452310E790579010679"));
		nvps.add(new BasicNameValuePair("TPL_password", "8868829xgj"));
		nvps.add(new BasicNameValuePair("TPL_redirect_url", ""));
		String username = URLEncoder.encode("随便_到处逛逛", "gb2312");
		username = "随便_到处逛逛";
		nvps.add(new BasicNameValuePair("TPL_username", username));
		CookieStore cookieStore = new BasicCookieStore();
		for (Entry<String, String> entry : cookies.entrySet()) {
			cookieStore.addCookie(new BasicClientCookie(entry.getKey(), entry
					.getValue()));
		}

		httpclient.setCookieStore(cookieStore);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "gbk"));

		response = httpclient.execute(httpPost);
		System.out.println("状态------"
				+ response.getStatusLine().getStatusCode());
		if (302 == response.getStatusLine().getStatusCode()) {
			headers = response.getHeaders("Set-Cookie");
			cookieStore = httpclient.getCookieStore();
			for (Header header : headers) {
				for (HeaderElement he : header.getElements()) {
					cookieStore.addCookie(new BasicClientCookie(he.getName(),
							he.getValue()));
				}

			}
			System.out.println("cookie-----------------size:"
					+ cookieStore.getCookies().size());
			for (Cookie cookie : cookieStore.getCookies()) {
				System.out.println(cookie.getName() + "-----------"
						+ cookie.getValue());
			}

			response.getEntity().consumeContent();
			nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("_tb_token_", token));
			nvps.add(new BasicNameValuePair("action", "CollectionAction"));
			nvps.add(new BasicNameValuePair("event_submit_do_save_add",
					"anything"));
			nvps.add(new BasicNameValuePair("id", "9443495292"));
			nvps.add(new BasicNameValuePair("isTmall", ""));
			nvps.add(new BasicNameValuePair("tags", ""));
			nvps.add(new BasicNameValuePair("type", "1"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			response = httpclient.execute(new HttpPost(
					"http://favorite.taobao.com/popup/success.htm"));
			System.out.println("状态------"
					+ response.getStatusLine().getStatusCode());

			InputStream is = response.getEntity().getContent();
			writeFile("successCollage.html", is);
		} else {
			System.out.println(getDoc(response.getEntity().getContent())
					.asXML());
		}

		httpclient.getConnectionManager().shutdown();
		System.out.println("oever------");
	}

	/**
	 * 输出文件
	 * @param fileName
	 * @param is
	 * @throws Exception
	 */
	private static void writeFile(String fileName, InputStream is)
			throws Exception {
		File file = new File("d:\\" + fileName);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), "gbk"));
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "gbk"));
		String line = null;
		while ((line = br.readLine()) != null) {
			bw.write(line);
		}
		bw.close();
		br.close();

	}

	/**
	 * 获取多节点
	 * 
	 * @param url
	 * @param xpathStr
	 * @param nameSpace
	 * @return
	 */

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
