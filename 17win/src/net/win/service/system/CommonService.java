package net.win.service.system;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.CityDAO;
import net.win.dao.PrefectureDAO;
import net.win.dao.ProvinceDAO;
import net.win.dao.UserDAO;

import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service("userService")
public class CommonService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private ProvinceDAO provinceDAO;
	@Resource
	private CityDAO cityDAO;
	@Resource
	private PrefectureDAO prefectureDAO;
	
	public String register( ) throws Exception {
		return SUCCESS;
	}
}
