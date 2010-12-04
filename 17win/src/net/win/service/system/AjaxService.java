package net.win.service.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.win.BaseService;
import net.win.dao.UserDAO;
import net.win.entity.UserEntity;
import net.win.utils.MailUtils;

import org.apache.struts2.ServletActionContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import sun.misc.BASE64Encoder;

@SuppressWarnings("unused")
@Service("ajaxService")
public class AjaxService extends BaseService {
	@Resource
	private UserDAO userDAO;

	@Resource
	private JavaMailSender mailSender;
	@Resource
	private FreeMarkerConfigurer freeMarkerCfj;

	/**
	 * 查找密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public Boolean updateFindPassword(String username, String telephone)
			throws Exception {
		UserEntity userEntity = userDAO
				.uniqueResult(
						" from UserEntity as _u where _u.username=:username  or _u.telephone =:telephone ",
						new String[] { "username", "telephone" }, new Object[] {
								username, telephone });
		if (userEntity == null) {
			return false;
		}
		BASE64Encoder encoder = new BASE64Encoder();
		String username64 = encoder.encode(userEntity.getUsername().getBytes());
		String nowTime65 = encoder.encode((System.currentTimeMillis() + "")
				.getBytes());

		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		String content = basePath + "userManager/base!initFindPassword.php?u="
				+ username64 + "&t=" + nowTime65;

		userEntity.setStatusAndLastStatus("3");
		userEntity.setStatusDesc("找回密码中！");
		MailUtils.sendPasswordMail(mailSender, freeMarkerCfj, userEntity
				.getUsername(), userEntity.getEmail(), content);
		return true;
	}
}
