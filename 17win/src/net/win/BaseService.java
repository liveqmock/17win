package net.win;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.win.dao.UserDAO;
import net.win.entity.BaseEntity;
import net.win.entity.CapitalLogEntity;
import net.win.entity.UserEntity;
import net.win.utils.Constant;
import net.win.utils.StrategyUtils;
import net.win.utils.StringUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

public class BaseService {
	protected static final String SUCCESS = "success";
	protected static final String INPUT = "input";
	protected static final String INIT = "init";
	protected static final String JSON = "json";
	protected static final String PAGE = "page";
	protected static final String FILEUPLOAD = "fileUpload";
	protected static final String APPLETVIEW = "appletview";
	protected static final String APPLET = "applet";
	protected static final String OUTEXCEL = "outexcel";
	protected static final String DOWNLOAD = "download";
	protected static final String JUMP = "jump";

	// 前台显示数据
	private static final String MSG = "msg";
	private static final String DIV = "div";

	/**
	 * 获取request
	 */
	protected HttpServletRequest getRequset() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取平台类型
	 * 
	 * @return
	 */
	protected String getPlatformType() {
		String platformType = getByParam("platformType");
		if (StringUtils.isBlank(platformType)) {
			platformType = (String) getByRequest("platformType");
		}
		return platformType;
	}

	/**
	 * 获取验证码
	 * 
	 * @return
	 */
	protected String getVerifyCode() {
		return (String) getBySession(Constant.VERIFY_CODE);
	}

	/**
	 * 存放平台类型
	 * 
	 * @return
	 */
	protected void putPlatformTypeByRequest(String platformType) {
		putByRequest("platformType", platformType);
	}

	/**
	 * 获取平台名字
	 * 
	 * @return
	 */
	protected String getPlatform() {
		return getByParam("platform");
	}

	/**
	 * 存放平台类型
	 * 
	 * @return
	 */
	protected void putPlatformByRequest(String platform) {
		putByRequest("platform", platform);
	}

	/**
	 * 存放现实类型
	 * 
	 * @return
	 */
	protected void putTaskShowType(String showTaskType) {
		putByRequest("showTaskType", showTaskType);
	}

	/**
	 * 存放现实类型
	 * 
	 * @return
	 */
	protected void putIndexShowType(String showIndexType) {
		putByRequest("showIndexType", showIndexType);
	}

	/**
	 * 获取response
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获取session
	 */
	protected HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 更新用户信息
	 * 
	 * @param userEntity
	 * @throws Exception
	 */
	protected void updateUserLoginInfo(UserEntity userEntity) throws Exception {
		UserLoginInfo userLoginInfo = getLoginUser();
		// ///
		BeanUtils.copyProperties(userLoginInfo, userEntity);
		userLoginInfo.setLevel(StrategyUtils.getLevel(userEntity
				.getUpgradeScore()));
		userLoginInfo.setLevelImg(StrategyUtils.getLevelImg(userEntity
				.getUpgradeScore()));
	}
	/**
	 * 把数据存放在request里面
	 * 
	 * @param name
	 * @param value
	 */
	protected void putByRequest(String key, Object value) {
		ServletActionContext.getRequest().setAttribute(key, value);
	}

	/**
	 * 把数据存放在session里面
	 * 
	 * @param name
	 * @param value
	 */
	protected void putBySession(String key, Object value) {
		ServletActionContext.getRequest().getSession().setAttribute(key, value);
	}

	/**
	 * 存放用户
	 * 
	 * @param name
	 * @param value
	 */
	protected void putLoginUser(UserLoginInfo userLoginInfo) {
		ServletActionContext.getRequest().getSession().setAttribute(
				Constant.USER_LOGIN_INFO, userLoginInfo);
	}

	/**
	 * 
	 * 
	 * @param name
	 * @param value
	 */
	protected UserLoginInfo getLoginUser() {
		UserLoginInfo userLoginInfo = (UserLoginInfo) getBySession(Constant.USER_LOGIN_INFO);
		if (userLoginInfo == null) {
			userLoginInfo = new UserLoginInfo();
			putLoginUser(userLoginInfo);
		}
		return userLoginInfo;
	}

	/**
	 * 获取Login
	 * 
	 * @return
	 */
	protected UserEntity getLoginUserEntity(UserDAO userDAO) {
		return userDAO.get(getLoginUser().getId());
	}

	/**
	 * 前台Alert提示数据，放在request里面
	 * 
	 * @param message
	 * @throws Exception
	 */
	protected void putAlertMsg(String message) throws Exception {
		message = "<script>alert('" + message + "');</script>";
		putByRequest(MSG, message);
	}

	/**
	 * 前台Alert提示数据，放在request里面
	 * 
	 * @param message
	 * @throws Exception
	 */
	protected void putJumpPage(String page) throws Exception {
		putByRequest(JUMP, page);
	}

	/**
	 * outteer 是否是其他网站
	 * 
	 * @param message
	 * @throws Exception
	 */
	protected void putJumpOutterPage(String page) throws Exception {
		putByRequest("outter", true);
		putByRequest(JUMP, page);
	}

	/**
	 * 显示DIV
	 * 
	 * @param message
	 * @throws Exception
	 */
	protected void putDIV(String message) throws Exception {
		putByRequest(DIV, message);
	}

	/**
	 * 从request取出数据
	 * 
	 * @param name
	 * @param value
	 */
	protected Object getByRequest(String key) {
		return ServletActionContext.getRequest().getAttribute(key);
	}

	/**
	 * 从param取出数据
	 * 
	 * @param name
	 * @param value
	 */
	protected String getByParam(String key) {
		return ServletActionContext.getRequest().getParameter(key);
	}

	/**
	 * 从session取出数据
	 * 
	 * @param name
	 * @param value
	 */
	protected Object getBySession(String key) {
		return ServletActionContext.getRequest().getSession().getAttribute(key);
	}

	// 保存金额记录
	protected void logMoneyCapital(BaseDAO baseDAO, Double value, String desc,
			UserEntity userEntity) throws Exception {
		CapitalLogEntity capitalLogEntity = new CapitalLogEntity();
		capitalLogEntity.setType("1");
		capitalLogEntity.setValue(value);
		capitalLogEntity.setRemainValue(userEntity.getMoney());
		capitalLogEntity.setDesc(desc);
		capitalLogEntity.setUser(userEntity);
		capitalLogEntity.setLogTime(new Date());
		baseDAO.save(capitalLogEntity);
	}

	// 保存积分
	protected void logScoreCapital(BaseDAO baseDAO, Double value, String desc,
			UserEntity userEntity) throws Exception {
		CapitalLogEntity capitalLogEntity = new CapitalLogEntity();
		capitalLogEntity.setType("3");
		capitalLogEntity.setValue(value);
		capitalLogEntity.setRemainValue(userEntity.getConvertScore()
				.doubleValue());
		capitalLogEntity.setDesc(desc);
		capitalLogEntity.setUser(userEntity);
		capitalLogEntity.setLogTime(new Date());
		baseDAO.save(capitalLogEntity);
	}

	/**
	 * 存放UUID
	 * 
	 * @param base
	 * @return
	 */
	protected void putTokenBySession() {
		putBySession(Constant.WIN17_TOKEN, StringUtils.createGUID());
	}

	/**
	 * 存放UUID
	 * 
	 * @param base
	 * @return
	 */
	protected Boolean isTokenPass() {
		if (getByParam(Constant.WIN17_TOKEN) == null) {
			return false;
		}
		return getByParam(Constant.WIN17_TOKEN).equals(
				getBySession(Constant.WIN17_TOKEN));
	}

	// 保存发布点记录
	protected void logDotCapital(BaseDAO baseDAO, Double value, String desc,
			UserEntity userEntity) throws Exception {
		CapitalLogEntity capitalLogEntity = new CapitalLogEntity();
		capitalLogEntity.setType("2");
		capitalLogEntity.setValue(value);
		capitalLogEntity.setRemainValue(userEntity.getReleaseDot());
		capitalLogEntity.setDesc(desc);
		capitalLogEntity.setUser(userEntity);
		capitalLogEntity.setLogTime(new Date());
		baseDAO.save(capitalLogEntity);
	}

	protected Boolean nullID(BaseEntity base) {
		return base.getId() == null;
	}

}
