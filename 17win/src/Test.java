import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
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
import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.DOMReader;
import org.dom4j.xpath.DefaultXPath;
import org.jaxen.SimpleNamespaceContext;
import org.xml.sax.SAXException;
@SuppressWarnings("unused")
public class Test {
	private static HttpClient taobaoHttpClient = createHttpClient("taobao");

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

	public static void main(String[] argv) throws Exception {

		HttpClient httpClient = taobaoHttpClient;

		HttpPost httRequest = new HttpPost("http://www.paipai.com");
		HttpResponse response = httpClient.execute(httRequest);
		httRequest = new HttpPost(
				"http://shop1.paipai.com/cgi-bin/credit_info?uin=30756500&");
		response = httpClient.execute(httRequest);
		HttpEntity entity = response.getEntity();

		String url = "http://shop1.paipai.com/cgi-bin/credit_info?uin=30756500&";
		Document document = getDoc(url);// 获取document
		// String gz = "//xmlns:A[@id='J_BuyerRate']|a[@id='J_BuyerRate']";//
		// xpath匹配

		String gz = "//xmlns:A[starts-with(@href,'http://store.taobao.com/shop/')]";// xpath匹配
		//

		Node node = getAttr(document, gz);// 获取属性
		System.out.println(node.getText().trim());

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

	public static Node getAttr(Document document, String gz) {
		Map nameSpaces = new HashMap();
		XPath xpath = new DefaultXPath(gz);
		nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
		xpath.setNamespaceContext(new SimpleNamespaceContext(nameSpaces));
		Node node = xpath.selectSingleNode(document);
		return node;
	}

}
