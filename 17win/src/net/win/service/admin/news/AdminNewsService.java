package net.win.service.admin.news;

import java.util.ArrayList;
import java.util.Date;
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
				"select  _news, _type.name  from NewsEntity as _news inner join _news.type as _type where 1=1");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)   from NewsEntity as _news  inner join _news.type as _type where 1=1");
		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();
		// 用户名
		if (!StringUtils.isBlank(newsVO.getTitle())) {
			resultHQL.append(" and _news.title like :title ");
			countHQL.append(" and _news.title like :title ");
			paramNames.add("title");
			paramValues.add("%" + newsVO.getTitle() + "%");
		}
		// 类型
		if (newsVO.getTypeId() != null) {
			resultHQL.append(" and _type.id = :typeId ");
			countHQL.append(" and _type.id = :typeId ");
			paramNames.add("typeId");
			paramValues.add(newsVO.getTypeId());
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
			List<Object[]> resultTemp = newsDAO.pageQuery(resultHQL.toString(),
					paramNames.toArray(paramNames.toArray(new String[paramNames
							.size()])), paramValues
							.toArray(new Object[paramValues.size()]), newsVO
							.getStart(), newsVO.getLimit());
			NewsVO newsVOTemp = null;
			for (Object[] obj : resultTemp) {
				newsVOTemp = new NewsVO();
				BeanUtils.copyProperties(newsVOTemp, obj[0]);
				newsVOTemp.setTypeName(String.valueOf(obj[1]));
				result.add(newsVOTemp);
			}
		}

		List<NewsTypeEntity> newsTypes = newsTypeDAO.listAll();
		List<NewsTypeVO> resultType = new ArrayList<NewsTypeVO>(newsTypes
				.size());
		NewsTypeVO typeVO = null;
		for (NewsTypeEntity newsTypeEntity : newsTypes) {
			typeVO = new NewsTypeVO();
			BeanUtils.copyProperties(typeVO, newsTypeEntity);
			resultType.add(typeVO);
		}
		putByRequest("newsTpyes", resultType);
		putByRequest("result", result);
		return "queryNews";
	}

	/**
	 * 初始化新增新闻
	 * 
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String initAddNews(NewsVO newsVO) throws Exception {
		List<NewsTypeEntity> newsTypes = newsTypeDAO.listAll();
		List<NewsTypeVO> result = new ArrayList<NewsTypeVO>(newsTypes.size());
		NewsTypeVO typeVO = null;
		for (NewsTypeEntity newsTypeEntity : newsTypes) {
			typeVO = new NewsTypeVO();
			BeanUtils.copyProperties(typeVO, newsTypeEntity);
			result.add(typeVO);
		}
		putByRequest("newsTpyes", result);
		return "initAddNews";
	}

	/**
	 * 浏览
	 * 
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String browserNews(NewsVO newsVO) throws Exception {
		NewsEntity newsEntity = newsDAO.get(newsVO.getId());
		BeanUtils.copyProperties(newsVO, newsEntity);
		return "browserNews";
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
		NewsTypeEntity newsTypeEntity = new NewsTypeEntity();
		newsTypeEntity.setId(newsVO.getTypeId());
		newsEntity.setType(newsTypeEntity);
		newsEntity.setDate(new Date());
		newsDAO.save(newsEntity);
		putAlertMsg("增加成功！");
		putJumpPage("adminNewsManager/adminNews!queryNews.php");
		return JUMP;
	}

	/**
	 * 初始化修改新闻
	 * 
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String initUpdateNews(NewsVO newsVO) throws Exception {
		List<NewsTypeEntity> newsTypes = newsTypeDAO.listAll();
		List<NewsTypeVO> result = new ArrayList<NewsTypeVO>(newsTypes.size());
		NewsTypeVO typeVO = null;
		for (NewsTypeEntity newsTypeEntity : newsTypes) {
			typeVO = new NewsTypeVO();
			BeanUtils.copyProperties(typeVO, newsTypeEntity);
			result.add(typeVO);
		}
		putByRequest("newsTpyes", result);
		NewsEntity newsEntity = newsDAO.get(newsVO.getId());
		BeanUtils.copyProperties(newsVO, newsEntity);
		putByRequest("newsVO", newsVO);
		return "initUpdateNews";
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

		NewsTypeEntity newsTypeEntity = new NewsTypeEntity();
		newsTypeEntity.setId(newsVO.getTypeId());
		newsEntity.setType(newsTypeEntity);
		newsEntity.setDate(new Date());
		putJumpPage("adminNewsManager/adminNews!queryNews.php");
		putAlertMsg("修改成功！");
		return JUMP;
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
		putAlertMsg("删除成功！");
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
		List<NewsTypeVO> result = new ArrayList<NewsTypeVO>(newses.size());
		NewsTypeVO typeVO = null;
		for (NewsTypeEntity newsTypeEntity : newses) {
			typeVO = new NewsTypeVO();
			BeanUtils.copyProperties(typeVO, newsTypeEntity);
			result.add(typeVO);
		}
		putByRequest("result", result);
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