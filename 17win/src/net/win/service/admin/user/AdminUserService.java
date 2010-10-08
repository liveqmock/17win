package net.win.service.admin.user;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.BuyerDAO;
import net.win.dao.SellerDAO;
import net.win.dao.UserDAO;
import net.win.vo.UserVO;

import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("adminUserService")
public class AdminUserService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private SellerDAO sellerDAO;
	@Resource
	private BuyerDAO buyerDAO;

	/**
	 * 查询所有用户
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String queryUser(UserVO userVO) throws Exception {
		return "queryUser";
	}
}
