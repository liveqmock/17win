package net.win.service.admin.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.NewsDAO;
import net.win.entity.NewsEntity;
import net.win.utils.ContextUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("adminSystemService")
public class AdminSystemService extends BaseService {
	@Resource
	private NewsDAO newsDAO;

	/**
	 * 新闻静态页面
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	public String staticNewsPage() throws Exception {

		List<NewsEntity> newses = newsDAO.listAll();
		HttpClient httpclient = new DefaultHttpClient();
		String path = getRequset().getContextPath();
		String basePath = getRequset().getScheme() + "://"
				+ getRequset().getServerName() + ":"
				+ getRequset().getServerPort() + path + "/";
		for (NewsEntity newsEntity : newses) {
			HttpGet httpget = null;
			BufferedWriter writer = null;
			BufferedReader reader = null;
			try {
				httpget = new HttpGet(
						basePath
								+ "adminNewsManager/adminNews!detailNews.php?newsVO.id="
								+ newsEntity.getId());
				HttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					writer = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(new File(ContextUtils
									.getRootPath()
									+ File.separator
									+ "help"
									+ File.separator
									+ newsEntity.getUrl())), "UTF-8"));
					reader = new BufferedReader(new InputStreamReader(entity
							.getContent(), "UTF-8"));
					String line = null;
					while ((line = reader.readLine()) != null) {
						writer.write(line);
						writer.newLine();
					}
				}
			} catch (RuntimeException e) {
				throw new RuntimeException(e);
			} finally {
				if (writer != null) {
					writer.newLine();
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
			}
			httpget.abort();
		}
		httpclient.getConnectionManager().shutdown();
		putAlertMsg("生成成功！");
		return INPUT;
	}

	/**
	 * 首页静态页面2
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	public String staticNews2Page() throws Exception {
		String path = getRequset().getContextPath();
		String basePath = getRequset().getScheme() + "://"
				+ getRequset().getServerName() + ":"
				+ getRequset().getServerPort() + path + "/";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = null;
		BufferedWriter writer = null;
		BufferedReader reader = null;

		try {
			httpget = new HttpGet(basePath
					+ "adminNewsManager/adminNews!showHelp.php");
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(new File(ContextUtils
								.getRootPath()
								+ File.separator
								+ "help"
								+ File.separator
								+ "index.html")), "UTF-8"));
				reader = new BufferedReader(new InputStreamReader(entity
						.getContent(), "UTF-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					writer.write(line);
					writer.newLine();
				}
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		} finally {
			if (writer != null) {
				writer.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
		httpget.abort();
		httpclient.getConnectionManager().shutdown();
		putAlertMsg("生成成功！");
		return INPUT;
	}

	/**
	 * 首页静态页面
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	public String staticIndexPage() throws Exception {
		String path = getRequset().getContextPath();
		String basePath = getRequset().getScheme() + "://"
				+ getRequset().getServerName() + ":"
				+ getRequset().getServerPort() + path + "/";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = null;
		BufferedWriter writer = null;
		BufferedReader reader = null;

		try {
			httpget = new HttpGet(basePath + "menuManager/menu!toIndex.php");
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(new File(ContextUtils
								.getRootPath()
								+ File.separator + "index.html")), "UTF-8"));
				reader = new BufferedReader(new InputStreamReader(entity
						.getContent(), "UTF-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					writer.write(line);
					writer.newLine();
				}
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		} finally {
			if (writer != null) {
				writer.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
		httpget.abort();
		httpclient.getConnectionManager().shutdown();
		putAlertMsg("生成成功！");
		return INPUT;
	}

	/**
	 * 首页刷客排行静态页面
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	public String staticShuakeIndexPage() throws Exception {
		String path = getRequset().getContextPath();
		String basePath = getRequset().getScheme() + "://"
				+ getRequset().getServerName() + ":"
				+ getRequset().getServerPort() + path + "/";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = null;
		BufferedWriter writer = null;
		BufferedReader reader = null;

		try {
			httpget = new HttpGet(basePath + "menuManager/menu!toIndex.php");
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(new File(ContextUtils
								.getRootPath()
								+ File.separator + "shuake" + "index.html")),
						"UTF-8"));
				reader = new BufferedReader(new InputStreamReader(entity
						.getContent(), "UTF-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					writer.write(line);
					writer.newLine();
				}
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		} finally {
			if (writer != null) {
				writer.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
		httpget.abort();
		httpclient.getConnectionManager().shutdown();
		putAlertMsg("生成成功！");
		return INPUT;
	}
}