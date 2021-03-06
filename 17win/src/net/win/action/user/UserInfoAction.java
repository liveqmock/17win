package net.win.action.user;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.user.UserInfoService;
import net.win.vo.UserVO;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ParentPackage("17win-default")
@Results( {
		@Result(name = "input", location = "/user/index.jsp"),
		@Result(name = "initUpdateInfo", location = "/user/updateInfo.jsp"),
		@Result(name = "updateInfo", location = "/user/updateInfo.jsp"),
		@Result(name = "initUpdatePassword", location = "/user/updatePW.jsp"),
		@Result(name = "updatePassword", location = "/user/updatePW.jsp"),
		@Result(name = "referee", location = "/spread/index.jsp"),
		@Result(name = "refereeCode", location = "/spread/code.jsp"),
		@Result(name = "myRefee", location = "/user/mySpread.jsp"),
		@Result(name = "initBuyDot", location = "/user/buyDot.jsp"),
		@Result(name = "updateBuyDot", location = "/user/buyDot.jsp"),
		@Result(name = "initActiave", location = "/user/activate.jsp"),
		@Result(name = "sendActiave", location = "/user/activate.jsp"),
		@Result(name = "updateActiave", location = "/user/activate.jsp"),
		@Result(name = "initSellerAndBuyer", location = "/user/sellerBuyerInfo.jsp"),
		@Result(name = "updateSeller", type = "chain", location = "/userInfoManager/info!initSellerAndBuyer.php"),
		@Result(name = "insertSellerAndBuyer", type = "chain", location = "/userInfoManager/info!initSellerAndBuyer.php"),
		@Result(name = "initFindPassword", location = "/system/initFindPassword.jsp"),
		@Result(name = "updateFindPassword", location = "/system/initFindPassword.jsp"),
		@Result(name = "refreshUser", type = "redirect", location = "/userInfoManager/info!init.php") })
@Namespace("/userInfoManager")
public class UserInfoAction extends BaseAction {
	@Resource
	private UserInfoService userInfoService;

	private UserVO userVO = new UserVO();

	public UserVO getUserVO() {
		return userVO;
	}

	@Action("/info")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 初始化找回密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initFindPassword() throws Exception {
		return userInfoService.initFindPassword();
	}

	/**
	 * 找回密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findPassword() throws Exception {
		return userInfoService.updateFindPassword(userVO);
	}

	/**
	 * 修改买家或卖家
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String updateSeller() throws Exception {
		return userInfoService.updateSeller(userVO);
	}

	/**
	 * 增加买家或卖家
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String sellerAndBuyer() throws Exception {
		return userInfoService.insertSellerAndBuyer(userVO);
	}

	/**
	 * 初始化买家或卖家
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String initSellerAndBuyer() throws Exception {
		return userInfoService.initSellerAndBuyer(userVO);
	}

	/**
	 * 激活账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateActiave() throws Exception {
		return userInfoService.updateActiave(userVO);
	}

	/**
	 * 发送激活账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendActiave() throws Exception {
		return userInfoService.sendActiave(userVO);
	}

	/**
	 * 初始化激活账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initActiave() throws Exception {
		return userInfoService.initActiave(userVO);
	}

 

	/**
	 * 我的推广
	 * 
	 * @return
	 * @throws Exception
	 */
	public String myRefee() throws Exception {
		return userInfoService.myRefee(userVO);
	}

	/**
	 * 推广码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String refereeCode() throws Exception {
		return "refereeCode";
	}

	/**
	 * 推广
	 * 
	 * @return
	 * @throws Exception
	 */
	public String referee() throws Exception {
		return userInfoService.referee();
	}

	/**
	 * 更新信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatePassword() throws Exception {
		return userInfoService.updatePassword(userVO);
	}

	/**
	 * 初始化更新信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initUpdatePassword() throws Exception {
		return "initUpdatePassword";
	}

	/**
	 * 更新信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateInfo() throws Exception {
		return userInfoService.updateInfo(userVO);
	}

	/**
	 * 初始化更新信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initUpdateInfo() throws Exception {
		return userInfoService.initUpdateInfo(userVO);
	}

	/**
	 * 初始化信息
	 */
	public String init() throws Exception {
		return userInfoService.initUserInfo(userVO);
	}

	/**
	 * 刷新用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String refreshUser() throws Exception {
		return userInfoService.refreshUser(userVO);
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

}
