package net.win.service.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.CreditTaskDAO;
import net.win.dao.UserDAO;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;
import net.win.utils.StringUtils;
import net.win.vo.CreditTaskVO;
import net.win.vo.SellerVO;

import org.apache.commons.beanutils.BeanUtils;
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
	 * 初始化发布任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String initReleaseTask(CreditTaskVO creditTaskVO) throws Exception {
		// 没有操作码验证就验证

		if (!getLoginUser().getOperationCodeStatus()) {
			return "operationValidate";
		} else {
			UserEntity userEntity = getLoginUserEntity(userDAO);
			List<SellerEntity> sellers = userEntity.getSellers();
			List<SellerVO> resultSellers = new ArrayList<SellerVO>(sellers
					.size());
			if (sellers.size() > 0) {
				creditTaskVO.setSellerID(sellers.get(0).getId());
				for (SellerEntity sellerEntity : sellers) {
					SellerVO sellerVO = new SellerVO();
					BeanUtils.copyProperties(sellerVO, sellerEntity);
					resultSellers.add(sellerVO);
				}
			}
			putByRequest("sellers", resultSellers);
			return "initReleaseTask";
		}
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

}
