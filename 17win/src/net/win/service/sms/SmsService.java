package net.win.service.sms;

import java.util.Calendar;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.SmsDAO;
import net.win.dao.UserDAO;
import net.win.dao.VipDAO;
import net.win.entity.UserEntity;
import net.win.entity.VipEntity;
import net.win.utils.ArithUtils;
import net.win.utils.Constant;
import net.win.utils.StringUtils;
import net.win.vo.VipVO;

import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("smsService")
public class SmsService extends BaseService {
	@Resource
	private SmsDAO userDAO;

}
