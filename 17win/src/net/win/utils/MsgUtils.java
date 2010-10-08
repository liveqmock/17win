package net.win.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public final class MsgUtils {
	private MsgUtils() {

	}

	/**
	 * 发送短信
	 * 
	 * @throws Exception
	 */
	public static void sendMsg(String telphone, String msg) throws Exception {
		// http://service.winic.org:8009/sys_port/gateway/?id=userid&pwd=password&to=接收短信手机号码&content=短信内容
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(
				"http://service.winic.org:8009/sys_port/gateway/");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("id", "xgj1988"));
		nvps.add(new BasicNameValuePair("pwd", "8868829xgj"));
		nvps.add(new BasicNameValuePair("to", telphone));
		nvps.add(new BasicNameValuePair("content", msg));

		httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

		HttpResponse response = httpclient.execute(httpost);
		HttpEntity entity = response.getEntity();

		System.out.println("Login form get: " + response.getStatusLine());
		if (entity != null) {
			entity.consumeContent();
		}
		httpclient.getConnectionManager().shutdown();
	}
}
