package net.win.service.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.UserLoginInfo;
import net.win.dao.AreaDAO;
import net.win.dao.CityDAO;
import net.win.dao.CreditTaskDAO;
import net.win.dao.ProvinceDAO;
import net.win.dao.UserDAO;
import net.win.entity.ProvinceEntity;
import net.win.entity.UserEntity;
import net.win.utils.Constant;
import net.win.utils.MailUtils;
import net.win.utils.StringUtils;
import net.win.utils.UserLevelUtils;
import net.win.vo.UserVO;

import org.hibernate.Hibernate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import sun.misc.BASE64Decoder;

import com.sun.org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("unused")
@Service("creditTaskService")
public class CreditTaskService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private CreditTaskDAO creditTaskDAO;
	/**
	 * 
	 * @param userVO
	 * @return
	 */
	public String updatefindPassword(UserVO userVO) throws Exception {
		return " ";
	}
}
