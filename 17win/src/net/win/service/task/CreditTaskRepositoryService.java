package net.win.service.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.win.BaseService;
import net.win.TaskMananger;
import net.win.UserLoginInfo;
import net.win.dao.BuyerDAO;
import net.win.dao.CreditTaskDAO;
import net.win.dao.CreditTaskRepositoryDAO;
import net.win.dao.SellerDAO;
import net.win.dao.UserDAO;
import net.win.entity.CreditTaskEntity;
import net.win.entity.CreditTaskRepositoryEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;
import net.win.utils.ArithUtils;
import net.win.utils.StrategyUtils;
import net.win.utils.StringUtils;
import net.win.utils.WinUtils;
import net.win.vo.CreditTaskRepositoryVO;
import net.win.vo.CreditTaskVO;
import net.win.vo.SellerVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service("creditTaskRepositoryService")
public class CreditTaskRepositoryService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private CreditTaskRepositoryDAO creditTaskRepositoryDAO;

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
	 * 获取到任务信息
	 * 
	 * @param creditTaskRepositoryVO
	 * @return
	 * @throws Exception
	 */
	public String queryRepositories(
			CreditTaskRepositoryVO creditTaskRepositoryVO) throws Exception {
		String platformType = getPlatformType();
		String hqlCount = "select count(*) from CreditTaskRepositoryEntity as _taskRe ,SellerEntity as _seller"
				+ " where _seller.id=_taskRe.sellerID and  _taskRe.type=:platformType and _taskRe.user=:userId";
		Long count = (Long) creditTaskRepositoryDAO.uniqueResultObject(
				hqlCount, new String[] { "platformType", "userId" },
				new Object[] { platformType, getLoginUser().getId() });
		if (count > 0) {
			String hql = "select _taskRe ,_seller.name from CreditTaskRepositoryEntity as _taskRe ,SellerEntity as _seller"
					+ " where _seller.id=_taskRe.sellerID and  _taskRe.type=:platformType and _taskRe.user=:userId";
			List<Object[]> resultTemp = creditTaskRepositoryDAO.list(hql,
					new String[] { "platformType", "userId" }, new Object[] {
							platformType, getLoginUser().getId() });
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
