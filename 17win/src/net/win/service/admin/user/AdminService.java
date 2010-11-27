package net.win.service.admin.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.AdminDAO;
import net.win.dao.UserDAO;
import net.win.entity.AdminEntity;
import net.win.entity.UserEntity;
import net.win.entity.VipEntity;
import net.win.utils.Constant;
import net.win.utils.MailUtils;
import net.win.utils.StringUtils;
import net.win.vo.UserVO;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import sun.misc.BASE64Decoder;

@SuppressWarnings("unused")
@Service("adminService")
public class AdminService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private AdminDAO adminDAO;

	/**
	 * 注销
	 * 
	 * @param userVO
	 * @return
	 */
	public String loginOut() throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return "loginOut";
	}

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateLogin() throws Exception {
		String code = getByParam("code");
		String password = getByParam("password");
		String username = getByParam("username");
		if (code == null || !code.equals(getBySession(Constant.VERIFY_CODE))) {
			putAlertMsg("验证码不正确！");
			return "inputLogin";
		}
		AdminEntity adminEntity = adminDAO
				.uniqueResult(
						"from AdminEntity  as _u where _u.username=:username and _u.password=:password",
						new String[] { "username", "password" }, new Object[] {
								username, StringUtils.processPwd(password) });
		if (adminEntity == null) {
			putAlertMsg("用户名或密码错误");
			return "inputLogin";
		} else {
			updateUserLoginInfo(adminEntity.getUser());
			getLoginUser().setAdminID(adminEntity.getId());
			return "loginSuccess";
		}
	}

	/**
	 * 修改密码
	 */
	public String updatePassword() throws Exception {
		String code = getByParam("code");
		if (code == null || !code.equals(getBySession(Constant.VERIFY_CODE))) {
			putAlertMsg("验证码不正确！");
			return "updatePassword";
		}
		AdminEntity adminEntity = adminDAO.get(getLoginUser().getAdminID());
		String password = getByParam("password");
		if (StringUtils.isBlank(password)) {
			putAlertMsg("密码不能为空!");
			return "updatePassword";
		}
		adminEntity.setPassword(StringUtils.processPwd(password));
		putAlertMsg("修改成功!");
		return "updatePassword";
	}
}
