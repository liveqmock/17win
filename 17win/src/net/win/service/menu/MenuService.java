package net.win.service.menu;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.NewsDAO;
import net.win.entity.NewsEntity;
import net.win.vo.NewsVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("menuService")
public class MenuService extends BaseService {
	@Resource
	private NewsDAO newsDAO;

	/**
	 * 添加
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String toIndex() throws Exception {
		ServletActionContext.getRequest().setAttribute("showIndexType", "1");
		List<NewsEntity> newses1 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='新手入门'  order by _news.sort asc,_news.date desc",
						0, 12);

		List<NewsEntity> newses2 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='卖家必读'  order by _news.sort asc,_news.date desc",
						0, 5);

		List<NewsEntity> newses3 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='买家必读'  order by _news.sort asc,_news.date desc",
						0, 5);

		List<NewsEntity> newses4 = newsDAO
				.pageQuery(
						"from NewsEntity as _news where _news.type.name='网站公告'  order by _news.sort asc,_news.date desc",
						0, 3);

		List<NewsVO> xsResult = new ArrayList<NewsVO>();
		List<NewsVO> sellerResult = new ArrayList<NewsVO>();
		List<NewsVO> buyerResult = new ArrayList<NewsVO>();
		List<NewsVO> ggResult = new ArrayList<NewsVO>();
		NewsVO newsVOTemp = null;
		for (NewsEntity newsEntity : newses1) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			xsResult.add(newsVOTemp);
		}

		for (NewsEntity newsEntity : newses2) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			sellerResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses3) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			buyerResult.add(newsVOTemp);
		}
		for (NewsEntity newsEntity : newses4) {
			newsVOTemp = new NewsVO();
			BeanUtils.copyProperties(newsVOTemp, newsEntity);
			ggResult.add(newsVOTemp);
		}

		putByRequest("xsResult", xsResult);
		putByRequest("sellerResult", sellerResult);
		putByRequest("buyerResult", buyerResult);
		putByRequest("ggResult", ggResult);
		return "toIndex";
	}

	public String to404Index() throws Exception {
		return "to404Index";

	}
}
