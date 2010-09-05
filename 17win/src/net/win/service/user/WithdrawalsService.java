package net.win.service.user;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.UserDAO;
import net.win.dao.WithDrawalsDAO;
import net.win.entity.UserEntity;
import net.win.entity.WithdrawalsEntity;
import net.win.utils.StringUtils;
import net.win.vo.WithdrawalsVO;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.sun.org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings( { "unused", "unchecked" })
@Service("withdrawalsService")
public class WithdrawalsService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private WithDrawalsDAO withDrawalsDAO;
	@Resource
	private JavaMailSender mailSender;
	@Resource
	private FreeMarkerConfigurer freeMarkerCfj;

	/**
	 * 提现
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String insertWithdrawals(WithdrawalsVO withdrawalsVO)
			throws Exception {
		UserEntity userEntity = getLoginUserEntity(userDAO);
		WithdrawalsEntity withdrawalsEntity = new WithdrawalsEntity();
		BeanUtils.copyProperties(withdrawalsEntity, withdrawalsVO);
		if (!userEntity.equals(StringUtils.processPwd(withdrawalsVO
				.getOperationCode()))) {
			putAlertMsg("操作码不正确！");
			return "insertWithdrawals";
		} else {
			if (withdrawalsEntity.getMoney() < 100) {
				throwIllegalityException(userEntity.getUsername()
						+ ":视图越过提现大于100的操作！");
			}
			if (withdrawalsEntity.getMoney() > userEntity.getMoney()) {
				putAlertMsg("提现错误！您的17win余额不够" + withdrawalsEntity.getMoney());
			}
			withdrawalsEntity.setUser(userEntity);
			withdrawalsEntity.setStatus("0");
			withdrawalsEntity.setStatusDesc("进入提现流程");
			withDrawalsDAO.save(withdrawalsEntity);

			putAlertMsg("操作成功，您的操作已经进入提现流程，我们会马上完成您的提现然后邮件通知您！");
		}
		return "insertWithdrawals";
	}

}
