package net.win.service.system;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.UserDAO;
import net.win.entity.UserEntity;
import net.win.utils.StringUtils;
import net.win.vo.CommonVO;

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
		String preURL = getByParam("preURL");
		UserEntity userEntity = getLoginUserEntity(userDAO);
		if (userEntity.getOpertationCode().equals(
				StringUtils.processPwd(getByParam("opertationCode")))) {
			getLoginUser().setOperationCodeStatus(true);
		} else {
			putAlertMsg("操作码不正确！");
			putByRequest("preURL", preURL);
			return "activateOperattionCode";
		}
		getResponse().sendRedirect(preURL);
		return null;
	}
}
