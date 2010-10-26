package net.win.service.shuake;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.UserDAO;

import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("shuakeService")
public class ShuakeService extends BaseService {
	@Resource
	private UserDAO userDAO;

	/**
	 * 初始化
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String initShuakeIndex() throws Exception {
		putIndexShowType("11");
		return "initShuakeIndex";
	}

}
