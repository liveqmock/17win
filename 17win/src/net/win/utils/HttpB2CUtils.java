package net.win.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public final class HttpB2CUtils {
	private HttpB2CUtils() {

	}

	// 地址验证
	private static final String TAOBAO_REGEX = "http:[/\\\\]{2}\\w+\\-*\\w+\\.taobao\\.com[/\\\\]";
	private static final String PAIPAI_REGEX = "http:[/\\\\]{2}\\w+\\-*\\w+\\.paipai\\.com[/\\\\]";
	private static final String YOUA_REGEX = "http:[/\\\\]{2}youa.baidu\\.com[/\\\\]";

	// 账号获取
	private static final String TAOBAO_USER_REGEX = "data\\-nick=\"([[\u0391-\uFFE5]\\w_]+)\"";
	private static final String PAIPAI_USER_REGEX = "<litagid='HOME_PAGE'><ahref='http://(\\d+)\\.paipai\\.com/";
	private static final String YOUA_USER_REGEX = "uname=\"([[\u0391-\uFFE5]\\w_]+)\"";

	/**
	 * 获取店铺类型
	 * 
	 * @return 0 非法地址 1 淘宝 2 拍拍 3 有啊
	 * @throws Exception
	 */
	public static String obtainShopType(String url) throws Exception {
		if (url.matches(TAOBAO_REGEX)) {
			return "1";
		}
		if (url.matches(PAIPAI_REGEX)) {
			return "2";
		}
		if (url.matches(YOUA_REGEX)) {
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
	public static String obtainSeller(String url, String type) throws Exception {
		String seller = "";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httRequest = new HttpPost(url);
		HttpResponse response = httpClient.execute(httRequest);
		HttpEntity entity = response.getEntity();
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
		httRequest.abort();
		httpClient.getConnectionManager().shutdown();
		return seller;

	}
}
