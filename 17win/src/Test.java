import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.win.utils.StringUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
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

	public static void fn() throws Exception {
		int chByte = 0;
		URL url = null;
		HttpURLConnection httpConn = null;
		InputStream in = null;
		FileOutputStream out = null;

		try {
			url = new URL(
					"http://shop1.paipai.com/cgi-bin/credit_info?uin=30756500&");
			httpConn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("GET");

			httpConn
					.setRequestProperty(
							"Cookie",
							"	PPRD_S=PVS.USER-PVSE.1; pvid=3194253992; flv=10.1 r53; pgv=pgvReferrer=&ssid=s9049088248; visitkey=3625640881013513");

			// logger.info(httpConn.getResponseMessage());
			in = httpConn.getInputStream();
			out = new FileOutputStream(new File("d:\\1.html"));

			chByte = in.read();
			while (chByte != -1) {
				out.write(chByte);
				chByte = in.read();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				httpConn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void main(String[] argv) throws Exception {
		String str = "<span tagvar='buyer'  score='20' tag='userGrade' >";
		Pattern scorePattern = Pattern
				.compile("<span tagvar='buyer'  score='(\\d+)' tag='userGrade' >");

		Matcher matcher = scorePattern.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group(1));
		}
		System.out.println("over");

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
