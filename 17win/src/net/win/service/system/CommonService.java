package net.win.service.system;

import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.AreaDAO;
import net.win.dao.CityDAO;
import net.win.dao.ProvinceDAO;
import net.win.dao.UserDAO;
import net.win.entity.AreaEntity;
import net.win.entity.CityEntity;
import net.win.entity.ProvinceEntity;
import net.win.vo.CommonVO;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service("commonService")
public class CommonService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private ProvinceDAO provinceDAO;
	@Resource
	private CityDAO cityDAO;
	@Resource
	private AreaDAO areaDAO;

	/**
	 * 初始化注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initRegister(CommonVO commonVO) throws Exception {
		List<ProvinceEntity> provinces = provinceDAO.listAll();
		Hibernate.initialize(provinces);
		commonVO.setProvinces(provinces);
		return "initRegister";
	}

	/**
	 * 注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String register(CommonVO commonVO) throws Exception {

		return SUCCESS;
	}

}
