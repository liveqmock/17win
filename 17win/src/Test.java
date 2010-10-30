import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.DOMReader;
import org.dom4j.xpath.DefaultXPath;
import org.jaxen.SimpleNamespaceContext;
import org.xml.sax.SAXException;

public class Test {
	public static void main(String[] argv) throws Exception {
		String url = "http://rate.taobao.com/rate.htm?user_id=20455417";
		Document document = getDoc(url);// 获取document
		String gz = "//xmlns:A[@id='J_BuyerRate']";// xpath匹配
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
