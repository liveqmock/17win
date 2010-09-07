package net.win.service.task;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.CreditTaskDAO;
import net.win.dao.UserDAO;
import net.win.entity.UserEntity;
import net.win.utils.StringUtils;
import net.win.vo.CreditTaskVO;

import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service("creditTaskService")
public class CreditTaskService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private CreditTaskDAO creditTaskDAO;

	/**
	 * 更新操作码
	 * 
	 * @param userVO
	 * @return
	 */
	public String updateUserLoginOperattionCode(CreditTaskVO creditTaskVO)
			throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		if (userEntity.getOpertationCode().equals(
				StringUtils.processPwd(getByParam("opertationCode")))) {
			getLoginUser().setOperationCodeStatus(true);
		} else {
			putAlertMsg("操作码不正确！");
			return "operationValidate";
		}
		return "updateUserLoginOperattionCode";
	}

	/**
	 * 初始化任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String initTask(CreditTaskVO creditTaskVO) throws Exception {
		return "initTask";
	}

	/**
	 * 初始化发布任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String initReleaseTask(CreditTaskVO creditTaskVO) throws Exception {
		if (!getLoginUser().getOperationCodeStatus()) {
			return "operationValidate";
		}
		return "initReleaseTask";
	}

}
