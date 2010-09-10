package net.win.service.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.win.BaseService;
import net.win.TaskMananger;
import net.win.UserLoginInfo;
import net.win.dao.BuyerDAO;
import net.win.dao.CreditTaskDAO;
import net.win.dao.SellerDAO;
import net.win.dao.UserDAO;
import net.win.entity.CreditTaskEntity;
import net.win.entity.SellerEntity;
import net.win.entity.UserEntity;
import net.win.utils.ArithUtils;
import net.win.utils.LoggerUtils;
import net.win.utils.StringUtils;
import net.win.utils.WinUtils;
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
	private SellerDAO sellerDAO;
	@Resource
	private BuyerDAO buyerDAO;
	@Resource
	private CreditTaskDAO creditTaskDAO;

	/**
	 * 发布任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String insertReleaseTask(CreditTaskVO creditTaskVO) throws Exception {
		// 基本数据
		String platFormType = getPlatformType();
		UserEntity userEntity = getLoginUserEntity(userDAO);
		UserLoginInfo userLoginInfo = getLoginUser();
		CreditTaskEntity creditTaskEntity = new CreditTaskEntity();
		TaskMananger taskMananger = TaskMananger.getInstance();
		// /逻辑
		BeanUtils.copyProperties(creditTaskEntity, creditTaskVO);
		String ip = getRequset().getRemoteAddr();
		// 放IP
		creditTaskEntity.setReceiveIP(ip);
		// 设置发布人，发布账号
		// Long buyerID = creditTaskVO.getBuyerID();
		Long sellerID = creditTaskVO.getSellerID();
		SellerEntity seller = new SellerEntity();
		seller.setId(sellerID);
		creditTaskEntity.setSeller(seller);
		creditTaskEntity.setReleasePerson(userEntity);
		// 验证
		if (creditTaskEntity.getMoney() > userEntity.getMoney()) {
			putDIV("您当前的余额为不够发布这个任务，点此处进行充值！");
			LoggerUtils.fatal(userEntity.getUsername() + ":试图越过金钱验证");
			return "insertReleaseTaskFail";
		} else {
			userEntity.setMoney(ArithUtils.sub(userEntity.getMoney(),
					creditTaskEntity.getMoney()));
		}
		// 生成testID
		creditTaskEntity.setTestID(taskMananger.generateTaskID());
		// 生成描述(包含地址)
		if (creditTaskVO.getAddress()) {
			List<Object[]> addresses = (List<Object[]>) sellerDAO
					.uniqueResult(
							"select _p.name,_c.name from SellerEntity as _s left join _s.province _p left join _s.city as _c where _s.id=:id",
							"id", sellerID);
			if (addresses.size() > 0) {
				creditTaskEntity.setDesc(taskMananger.htmlAddreeStr(addresses
						.get(0), userDAO));
			}
		}
		// 任务仓库
		if (creditTaskVO.getRepository()) {
		}
		// 保存
		creditTaskDAO.save(creditTaskEntity);
		// 完成对金钱进行修改,登陆名的也需要
		updateUserLoginInfo(userEntity);

		if (creditTaskVO.getProtect()) {

		}
		// 是否是定时任务
		if (creditTaskEntity.getStatus().equals("0")) {
			taskMananger.addTimingTask(creditTaskEntity.getId());
		}
		return "insertReleaseTaskSuccess";
	}

	/**
	 * 初始化发布任务
	 * 
	 * @param userVO
	 * @return
	 */
	public String initReleaseTask(CreditTaskVO creditTaskVO) throws Exception {
		// 没有操作码验证就验证
		HttpServletRequest request = getRequset();
		if (!getLoginUser().getOperationCodeStatus()) {
			putByRequest("preURL", getRequset().getRequestURL() + "?"
					+ getRequset().getQueryString());
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
		String platformType = getPlatformType();
		putPlatformByRequest(WinUtils.changeType2Platform(platformType));
		putPlatformTypeByRequest(platformType);
		return "initTask";
	}
}
