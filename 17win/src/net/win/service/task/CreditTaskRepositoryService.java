package net.win.service.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.CreditTaskDAO;
import net.win.dao.CreditTaskRepositoryDAO;
import net.win.dao.SellerDAO;
import net.win.dao.UserDAO;
import net.win.entity.CreditTaskRepositoryEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;
import net.win.utils.WinUtils;
import net.win.vo.CreditTaskRepositoryVO;
import net.win.vo.CreditTaskVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service("creditTaskRepositoryService")
public class CreditTaskRepositoryService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private CreditTaskRepositoryDAO creditTaskRepositoryDAO;
	@Resource
	private CreditTaskDAO creditTaskDAO;
	@Resource
	private SellerDAO sellerDAO;

	/**
	 * 删除
	 * 
	 * @param creditTaskRepositoryVO
	 * @return
	 * @throws Exception
	 */
	public String deleteRepository(CreditTaskRepositoryVO creditTaskRepositoryVO)
			throws Exception {
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			Long taskReId = Long.parseLong(getByParam("taskReId"));
			int count = creditTaskRepositoryDAO
					.deleteByHql(
							"delete from  CreditTaskRepositoryEntity as _taskRe  where _taskRe.id=:taskReId and _taskRe.user.id=:userId",
							new String[] { "taskReId", "userId" },
							new Object[] { taskReId, getLoginUser().getId() });
			if (count > 0) {
				putAlertMsg("删除成功！");
			} else {
				putAlertMsg("已经被删除,不要重复提交！");
			}
			putJumpSelfPage("taskRepositoryManager/taskRepository!queryRepositories.php?platformType="
					+ platformType);
			return JUMP;
		}
	}

	/**
	 * 获取到任务信息
	 * 
	 * @param creditTaskRepositoryVO
	 * @return
	 * @throws Exception
	 */
	public String obtainTaskInfo(CreditTaskRepositoryVO creditTaskRepositoryVO)
			throws Exception {
		CreditTaskRepositoryEntity creditTaskRepositoryEntity = creditTaskRepositoryDAO
				.get(creditTaskRepositoryVO.getId());
		BeanUtils.copyProperties(creditTaskRepositoryVO,
				creditTaskRepositoryEntity);
		return JSON;
	}

	/**
	 * 查询
	 * 
	 * @param creditTaskRepositoryVO
	 * @return
	 * @throws Exception
	 */
	public String queryRepositories(
			CreditTaskRepositoryVO creditTaskRepositoryVO) throws Exception {
		String platformType = getPlatformType();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
			return "operationValidate";
		} else {
			putTaskShowType("6");
			String hqlCount = "select count(*) from CreditTaskRepositoryEntity as _taskRe "
					+ " where  _taskRe.type=:platformType and _taskRe.user.id=:userId";
			Long count = (Long) creditTaskRepositoryDAO.uniqueResultObject(
					hqlCount, new String[] { "platformType", "userId" },
					new Object[] { platformType, getLoginUser().getId() });
			if (count > 0) {
				String hql = "select _taskRe ,_taskRe.seller.name from CreditTaskRepositoryEntity as _taskRe "
						+ " where    _taskRe.type=:platformType and _taskRe.user.id=:userId order by _taskRe.id desc";
				List<Object[]> resultTemp = creditTaskRepositoryDAO.list(hql,
						new String[] { "platformType", "userId" },
						new Object[] { platformType, getLoginUser().getId() });
				List<CreditTaskRepositoryVO> result = new ArrayList<CreditTaskRepositoryVO>(
						resultTemp.size());
				for (Object[] objects : resultTemp) {
					CreditTaskRepositoryEntity creditTaskRepositoryEntity = (CreditTaskRepositoryEntity) objects[0];
					CreditTaskRepositoryVO cTemp = new CreditTaskRepositoryVO();
					BeanUtils.copyProperties(cTemp, creditTaskRepositoryEntity);
					cTemp.setSellerName((String) objects[1]);
					result.add(cTemp);
				}
				creditTaskRepositoryVO.setDataCount(count.intValue());
				putByRequest("result", result);
			}
			putPlatformByRequest(WinUtils.changeType2Platform(platformType));
			putPlatformTypeByRequest(platformType);
			return "queryRepositories";
		}
	}
}
