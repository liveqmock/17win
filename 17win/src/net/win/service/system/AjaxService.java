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
}
