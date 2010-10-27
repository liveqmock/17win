package net.win.service.admin.news;

import java.net.URLDecoder;
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
	 * 详细信息
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	public String detailNews(NewsVO newsVO) throws Exception {
		List<NewsEntity> newses1 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='网站公告'  order by _news.date",
						0, 5);
		List<NewsEntity> newses2 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='推荐文章'  order by _news.date",
						0, 10);
		List<NewsVO> wzResult = new ArrayList<NewsVO>();
		List<NewsVO> tjResult = new ArrayList<NewsVO>();
		NewsVO newsVOTemp = null;
		for (NewsEntity newsEntity : newses1) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			wzResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses2) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			tjResult.add(newsVOTemp);
		}
		putByRequest("wzResult", wzResult);
		putByRequest("tjResult", tjResult);

		List<NewsEntity> prevNews = newsDAO.pageQuery(
				"from NewsEntity  where  id <:newsID  order by  id desc ",
				new String[] { "newsID" }, new Object[] { newsVO.getId() }, 0,
				1);
		List<NewsEntity> afterNews = newsDAO.pageQuery(
				"from NewsEntity  where  id >:newsID  order by  id asc ",
				new String[] { "newsID" }, new Object[] { newsVO.getId() }, 0,
				1);
		if (prevNews.size() > 0) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, prevNews.get(0));
			putByRequest("prevNews", newsVOTemp);
		} else {
			putByRequest("prevNews", null);
		}

		if (afterNews.size() > 0) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, afterNews.get(0));
			putByRequest("afterNews", newsVOTemp);
		} else {
			putByRequest("afterNews", null);
		}

		NewsEntity newsEntity = newsDAO.get(newsVO.getId());
		newsVOTemp = new NewsVO();
		BeanUtils.copyProperties(newsVOTemp, newsEntity);
		newsVOTemp.setTypeName(newsEntity.getType().getName());
		putByRequest("result", newsVOTemp);
		return "detailNews";
	}

	/**
	 * 新闻列表
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	public String listNews(NewsVO newsVO) throws Exception {
		List<NewsEntity> newses1 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='网站公告'  order by _news.date",
						0, 5);
		List<NewsEntity> newses2 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='推荐文章'  order by _news.date",
						0, 10);
		List<NewsVO> wzResult = new ArrayList<NewsVO>();
		List<NewsVO> tjResult = new ArrayList<NewsVO>();
		NewsVO newsVOTemp = null;
		for (NewsEntity newsEntity : newses1) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			wzResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses2) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			tjResult.add(newsVOTemp);
		}

		newsVO.setTypeName(URLDecoder.decode(newsVO.getTypeName(), "UTF-8"));
		Long count = (Long) newsDAO
				.uniqueResultObject(
						"select count(*) from NewsEntity as _news where _news.type.name=:typeName  order by _news.date",
						new String[] { "typeName" }, new String[] { newsVO
								.getTypeName() });
		List<NewsVO> result = new ArrayList<NewsVO>();
		if (count > 0) {
			List<NewsEntity> resultTemp = newsDAO
					.pageQuery(
							"from NewsEntity as _news where _news.type.name=:typName  order by date",
							new String[] { "typName" }, new Object[] { newsVO
									.getTypeName() }, newsVO.getStart(), newsVO
									.getLimit());
			newsVO.setDataCount(count.intValue());
			for (NewsEntity newsEntity : resultTemp) {
				newsVOTemp = new NewsVO();
				BeanUtils.copyProperties(newsVOTemp, newsEntity);
				result.add(newsVOTemp);
			}
		}
		putByRequest("wzResult", wzResult);
		putByRequest("tjResult", tjResult);
		putByRequest("result", result);
		return "listNews";
	}

	/**
	 * 显示帮助
	 * 
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String showHelp(NewsVO newsVO) throws Exception {
		putIndexShowType("10");
		List<NewsEntity> newses1 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='网站公告'  order by _news.date",
						0, 5);
		List<NewsEntity> newses2 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='推荐文章'  order by _news.date",
						0, 10);
		List<NewsEntity> newses3 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='新手入门'  order by _news.date",
						0, 5);
		List<NewsEntity> newses4 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='卖家必读'  order by _news.date",
						0, 5);
		List<NewsEntity> newses5 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='店铺推广'  order by _news.date",
						0, 5);
		List<NewsEntity> newses6 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='网络营销'  order by _news.date",
						0, 5);
		List<NewsEntity> newses7 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='刷客必读'  order by _news.date",
						0, 5);
		List<NewsEntity> newses8 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='买家必读'  order by _news.date",
						0, 5);
		List<NewsEntity> newses9 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='赚钱窍门'  order by _news.date",
						0, 5);
		List<NewsEntity> newses10 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='服务项目'  order by _news.date",
						0, 5);
		List<NewsVO> wzResult = new ArrayList<NewsVO>();
		List<NewsVO> tjResult = new ArrayList<NewsVO>();
		List<NewsVO> xsResult = new ArrayList<NewsVO>();
		List<NewsVO> mjResult = new ArrayList<NewsVO>();
		List<NewsVO> dpResult = new ArrayList<NewsVO>();
		List<NewsVO> wlResult = new ArrayList<NewsVO>();
		List<NewsVO> skResult = new ArrayList<NewsVO>();
		List<NewsVO> majResult = new ArrayList<NewsVO>();
		List<NewsVO> zqResult = new ArrayList<NewsVO>();
		List<NewsVO> fwResult = new ArrayList<NewsVO>();
		NewsVO newsVOTemp = null;
		for (NewsEntity newsEntity : newses1) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			wzResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses2) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			tjResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses3) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			xsResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses4) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			mjResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses5) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			dpResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses6) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			wlResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses7) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			skResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses8) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			majResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses9) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			zqResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses10) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			fwResult.add(newsVOTemp);
		}
		putByRequest("wzResult", wzResult);
		putByRequest("tjResult", tjResult);
		putByRequest("xsResult", xsResult);
		putByRequest("mjResult", mjResult);
		putByRequest("dpResult", dpResult);
		putByRequest("wlResult", wlResult);
		putByRequest("skResult", skResult);
		putByRequest("majResult", majResult);
		putByRequest("zqResult", zqResult);
		putByRequest("fwResult", fwResult);
		return "showHelp";
	}

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
		Long count = (Long) newsDAO
				.uniqueResultObject(
						"select count(*) from NewsEntity as _news  where _news.type.id=:typID",
						new String[] { "typID" }, new Object[] { newsVO
								.getTypeId() }) + 1;
		NewsEntity newsEntity = new NewsEntity();
		BeanUtils.copyProperties(newsEntity, newsVO);
		NewsTypeEntity newsTypeEntity = new NewsTypeEntity();
		newsTypeEntity.setId(newsVO.getTypeId());
		newsEntity.setType(newsTypeEntity);
		newsEntity.setDate(new Date());
		newsEntity.setUrl("detail_" + newsVO.getTypeId() + "_" + count
				+ ".html");
		newsDAO.save(newsEntity);
		putAlertMsg("增加成功！");
		putJumpPage("adminNewsManager/adminNews!queryNews.php");
		newsDAO.flushSession();
		newsDAO.clearSession();
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
		Long count = (Long) newsDAO
				.uniqueResultObject(
						"select count(*) from NewsEntity as _news  where _news.type.id=:typID",
						new String[] { "typID" }, new Object[] { newsVO
								.getTypeId() }) + 1;
		NewsEntity newsEntity = newsDAO.get(newsVO.getId());
		BeanUtils.copyProperties(newsEntity, newsVO);

		NewsTypeEntity newsTypeEntity = new NewsTypeEntity();
		newsTypeEntity.setId(newsVO.getTypeId());
		newsEntity.setType(newsTypeEntity);
		newsEntity.setDate(new Date());
		newsEntity.setUrl("detail_" + newsVO.getTypeId() + "_" + count
				+ ".html");
		putJumpPage("adminNewsManager/adminNews!queryNews.php");
		putAlertMsg("修改成功！");
		newsDAO.flushSession();
		newsDAO.clearSession();
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