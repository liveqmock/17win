package net.win.action.admin.news;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.admin.news.AdminNewsService;
import net.win.service.admin.user.AdminService;
import net.win.vo.NewsTypeVO;
import net.win.vo.NewsVO;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
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
		@Result(name = "addNewsType", location = "/admin/news/newsType.jsp") })
public class AdminNewsAction extends BaseAction {
	@Resource
	private AdminNewsService adminNewsService;

	private NewsVO newsVO = new NewsVO();
	private NewsTypeVO newsTypeVO = new NewsTypeVO();

	public NewsVO getNewsVO() {
		return newsVO;
	}

	public void setNewsVO(NewsVO newsVO) {
		this.newsVO = newsVO;
	}

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
		return adminNewsService.addNewsType(newsTypeVO);
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

}
