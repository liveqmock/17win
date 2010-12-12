package net.win.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public final class MsgUtils {
	private MsgUtils() {

	}

	// 000 成送成功！
	// -01 当前账号余额不足！
	// -02 当前用户ID错误！
	// -03 当前密码错误！
	// -04 参数不够或参数内容的类型错误！
	// -05 手机号码格式不对！（目前还未实现）
	// -06 短信内容编码不对！（目前还未实现）
	// -07 短信内容含有敏感字符！（目前还未实现）
	// null 无接收数据
	// -09 系统维护中.. （目前还未实现）
	// -10 手机号码数量超长！（100个/次 超100个请自行做循环）（目前还未实现）
	// -11 短信内容超长！（70个字符）目前已取消，如果内容超长，会自动分成多条发送
	// -12 其它错误！
	/**
	 * 发送短信
	 * 
	 * @throws Exception
	 */
	public static void sendMsg(String telphone, String msg) throws Exception {
		// http://service.winic.org:8009/sys_port/gateway/?id=userid&pwd=password&to=接收短信手机号码&content=短信内容
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(
				"http://service.winic.org:8009/sys_port/gateway/?id=xgj1988&pwd=8868829xgj&to="
						+ telphone + "&content=" + msg);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("id", "xgj1988"));
		nvps.add(new BasicNameValuePair("pwd", "8868829xgj"));
		nvps.add(new BasicNameValuePair("to", telphone));
		nvps.add(new BasicNameValuePair("content", msg));

		HttpResponse response = httpclient.execute(httpget);

		BufferedReader br = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		String result = br.readLine();
		if (result != null && !"000".equals(br.readLine())) {
			throw new RuntimeException("发送手机错误 代码列表查看MsgUtils类，错误，错误代码:" + br.readLine());
		}
		br.close();
		httpclient.getConnectionManager().shutdown();
	}
}
