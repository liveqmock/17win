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
import net.win.entity.UserEntity;
import net.win.utils.Constant;
import net.win.utils.StringUtils;
import net.win.vo.CommonVO;
import net.win.vo.CreditTaskVO;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service("commonService")
public class CommonService extends BaseService {
	/**
	 * 更新操作码
	 * 
	 * @param userVO
	 * @return
	 */
	@Resource
	private UserDAO userDAO;

	public String activateOperattionCode(CommonVO commonVO) throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		if (userEntity.getOpertationCode().equals(
				StringUtils.processPwd(getByParam("opertationCode")))) {
			getLoginUser().setOperationCodeStatus(true);
		} else {
			putAlertMsg("操作码不正确！");
			return "activateOperattionCode";
		}
		return "";
	}

}
