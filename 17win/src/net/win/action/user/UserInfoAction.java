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
@Results( { @Result(name = "input", location = "/user/index.jsp"),
		@Result(name = "initUpdateInfo", location = "/user/updateInfo.jsp"),
		@Result(name = "updateInfo", location = "/user/updateInfo.jsp"),
		@Result(name = "initUpdatePassword", location = "/user/updatePW.jsp"),
		@Result(name = "updatePassword", location = "/user/updatePW.jsp")
})
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
		return userInfoService.updatePassword(userVO);
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

}
