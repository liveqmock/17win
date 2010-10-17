package net.win.service.admin.news;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.NewsDAO;
import net.win.dao.NewsTypeDAO;
import net.win.entity.NewsEntity;
import net.win.entity.NewsTypeEntity;
import net.win.utils.StringUtils;
import net.win.vo.AdminPayVO;
import net.win.vo.NewsTypeVO;
import net.win.vo.NewsVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("adminNewsService")
public class AdminNewsService extends BaseService {
	@Resource
	private NewsDAO newsDAO;

	@Resource
	private NewsTypeDAO newsTypeDAO;

	/**
	 * 初始化新闻
	 * 
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String queryNews(NewsVO newsVO) throws Exception {
		StringBuffer resultHQL = new StringBuffer(
				"select  _news   from NewsEntity as _news where 1=1");
		StringBuffer countHQL = new StringBuffer(
				"select  _news   from NewsEntity as _news where 1=1");
		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();
		// 用户名
		if (!StringUtils.isBlank(newsVO.getTitle())) {
			resultHQL.append(" and _news.title like :title ");
			countHQL.append(" and _news.title like :title ");
			paramNames.add("title");
			paramValues.add("%" + newsVO.getTitle() + "%");
		}
		// 时间
		if (newsVO.getStartDate() != null && newsVO.getEndDate() != null) {

			resultHQL
					.append(" and (_news.date>=:startDate and _news.date<=:endDate) ");
			countHQL
					.append(" and (_news.date>=:startDate and _news.date<=:endDate) ");

			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(newsVO.getStartDate());
			paramValues.add(newsVO.getEndDate());
		} else if (newsVO.getStartDate() != null) {
			resultHQL.append(" and _news.date>=:startDate  ");
			countHQL.append(" and  _news.date>=:startDate   ");

			paramNames.add("startDate");
			paramValues.add(newsVO.getStartDate());
		} else if (newsVO.getEndDate() != null) {
			resultHQL.append(" and   _news.date<=:endDate ");
			countHQL.append(" and   _news.date<=:endDate  ");
			paramNames.add("endDate");
			paramValues.add(newsVO.getEndDate());
		}

		Long count = (Long) newsDAO.uniqueResultObject(countHQL.toString(),
				paramNames.toArray(paramNames.toArray(new String[paramNames
						.size()])), paramValues.toArray(new Object[paramValues
						.size()]));

		List<NewsVO> result = new ArrayList<NewsVO>();
		if (count > 0) {
			newsVO.setDataCount(count.intValue());
			List<NewsEntity> resultTemp = newsDAO.pageQuery(resultHQL
					.toString(), paramNames.toArray(paramNames
					.toArray(new String[paramNames.size()])), paramValues
					.toArray(new Object[paramValues.size()]),
					newsVO.getStart(), newsVO.getLimit());
			NewsVO newsVOTemp = null;
			for (NewsEntity obj : resultTemp) {
				newsVOTemp = new NewsVO();
				BeanUtils.copyProperties(newsVOTemp, obj);
				result.add(newsVOTemp);
			}
		}
		putByRequest("result", result);
		return "queryNews";
	}

	/**
	 * 新增新闻
	 * 
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String addNews(NewsVO newsVO) throws Exception {
		NewsEntity newsEntity = new NewsEntity();
		BeanUtils.copyProperties(newsEntity, newsVO);
		newsVO.setTitle("");
		newsDAO.save(newsEntity);
		queryNews(newsVO);
		return "addNews";
	}

	/**
	 * 修改新闻
	 * 
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String updateNews(NewsVO newsVO) throws Exception {
		NewsEntity newsEntity = new NewsEntity();
		BeanUtils.copyProperties(newsEntity, newsVO);
		newsVO.setTitle("");
		newsDAO.update(newsEntity);
		queryNews(newsVO);
		return "updateNews";
	}

	/**
	 * 删除新闻
	 * 
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String deleteNews(NewsVO newsVO) throws Exception {
		newsDAO.deleteById(newsVO.getId());
		queryNews(newsVO);
		return "deleteNews";
	}

	// ///----------------------------------------------------------------------
	/**
	 * 初始化类别
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	public String initNewsType(NewsTypeVO newsTypeVO) throws Exception {
		List<NewsTypeEntity> newses = newsTypeDAO.listAll();
		putByRequest("result", newses);
		return "initNewsType";
	}

	/**
	 * 新增
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	public String insertyNewsType(NewsTypeVO newsTypeVO) throws Exception {
		NewsTypeEntity newsTypeEntity = new NewsTypeEntity();
		BeanUtils.copyProperties(newsTypeEntity, newsTypeVO);
		newsTypeDAO.save(newsTypeEntity);
		initNewsType(newsTypeVO);
		putAlertMsg("添加成功！");
		return "addNewsType";
	}

	/**
	 * 修改
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	public String updateNewsType(NewsTypeVO newsTypeVO) throws Exception {
		NewsTypeEntity newsTypeEntity = newsTypeDAO.get(newsTypeVO.getId());
		newsTypeEntity.setName(newsTypeVO.getName());
		initNewsType(newsTypeVO);
		putAlertMsg("修改成功！");
		return "updateNewsType";
	}

	/**
	 * 删除
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	public String deleteNewsType(NewsTypeVO newsTypeVO) throws Exception {
		newsTypeDAO.deleteById(newsTypeVO.getId());
		initNewsType(newsTypeVO);
		putAlertMsg("删除成功！");
		return "deleteNewsType";
	}
}