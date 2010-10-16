package net.win.service.admin.news;

import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.NewsDAO;
import net.win.dao.NewsTypeDAO;
import net.win.entity.NewsTypeEntity;
import net.win.vo.NewsTypeVO;

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
	 * 初始化类别
	 * 
	 * @param adminPayVO
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
	 * @param adminPayVO
	 * @return
	 * @throws Exception
	 */
	public String addNewsType(NewsTypeVO newsTypeVO) throws Exception {
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
	 * @param adminPayVO
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
	 * @param adminPayVO
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