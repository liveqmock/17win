package net.win.action.admin.news;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.news.AdminNewsService;
import net.win.vo.NewsTypeVO;
import net.win.vo.NewsVO;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ParentPackage("17win-default")
@Namespace("/adminNewsManager")
@Results( {
		@Result(name = "initNewsType", location = "/admin/news/newsType.jsp"),
		@Result(name = "updateNewsType", location = "/admin/news/newsType.jsp"),
		@Result(name = "deleteNewsType", location = "/admin/news/newsType.jsp"),
		@Result(name = "addNewsType", location = "/admin/news/newsType.jsp"),

		@Result(name = "queryNews", location = "/admin/news/newsIndex.jsp"),
		@Result(name = "initAddNews", location = "/admin/news/addNews.jsp"),

		@Result(name = "addNews", type = "chain", location = "adminNewsManager/adminNews!queryNews.php"),
		@Result(name = "updateNews", type = "chain", location = "adminNewsManager/adminNews!queryNews.php"),

		@Result(name = "initUpdateNews", location = "/admin/news/updateNews.jsp"),
		@Result(name = "deleteNews", location = "/admin/news/newsIndex.jsp"),

		@Result(name = "browserNews", location = "/admin/news/browserNews.jsp"),

		@Result(name = "showHelp", location = "/help/index.jsp"),

		@Result(name = "listNews", location = "/help/list.jsp"),

		@Result(name = "detailNews", location = "/help/detail.jsp") })
public class AdminNewsAction extends BaseAction {

	private Integer sort;
	@Resource
	private AdminNewsService adminNewsService;

	private NewsVO newsVO = new NewsVO();
	private NewsTypeVO newsTypeVO = new NewsTypeVO();

	@JSON(serialize=false)
	public NewsVO getNewsVO() {
		return newsVO;
	}

	public void setNewsVO(NewsVO newsVO) {
		this.newsVO = newsVO;
	}
	@JSON(serialize=false)
	public NewsTypeVO getNewsTypeVO() {
		return newsTypeVO;
	}

	public void setNewsTypeVO(NewsTypeVO newsTypeVO) {
		this.newsTypeVO = newsTypeVO;
	}

	@Override
	@Action("/adminNews")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ajaxObtainSort() throws Exception {
		sort = adminNewsService.ajaxObtainSort(newsVO);
		return JSON;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String detailNews() throws Exception {
		return adminNewsService.detailNews(newsVO);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listNews() throws Exception {
		return adminNewsService.listNews(newsVO);
	}

	/**
	 * 新闻显示
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showHelp() throws Exception {
		return adminNewsService.showHelp(newsVO);
	}

	/**
	 * 初始化新闻
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryNews() throws Exception {
		return adminNewsService.queryNews(newsVO);
	}

	/**
	 * 初始化新增
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initAddNews() throws Exception {
		return adminNewsService.initAddNews(newsVO);
	}

	/**
	 * 新增
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addNews() throws Exception {
		return adminNewsService.addNews(newsVO);
	}

	/**
	 * 初始化 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initUpdateNews() throws Exception {
		return adminNewsService.initUpdateNews(newsVO);
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateNews() throws Exception {
		return adminNewsService.updateNews(newsVO);
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteNews() throws Exception {
		return adminNewsService.deleteNews(newsVO);
	}

	/**
	 * 浏览
	 * 
	 * @return
	 * @throws Exception
	 */
	public String browserNews() throws Exception {
		return adminNewsService.browserNews(newsVO);
	}

	// /-------------------------------------------------------------------------
	/**
	 * 初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initNewsType() throws Exception {
		return adminNewsService.initNewsType(newsTypeVO);
	}

	/**
	 * 新增
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addNewsType() throws Exception {
		return adminNewsService.insertyNewsType(newsTypeVO);
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateNewsType() throws Exception {
		return adminNewsService.updateNewsType(newsTypeVO);
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteNewsType() throws Exception {
		return adminNewsService.deleteNewsType(newsTypeVO);
	}

	public Integer getSort() {
		return sort;
	}

}
