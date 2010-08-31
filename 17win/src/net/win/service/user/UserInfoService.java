package net.win.service.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.UserLoginInfo;
import net.win.dao.AreaDAO;
import net.win.dao.CityDAO;
import net.win.dao.ProvinceDAO;
import net.win.dao.UserDAO;
import net.win.entity.UserEntity;
import net.win.vo.UserVO;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SuppressWarnings( { "unused", "unchecked" })
@Service("userInfoService")
public class UserInfoService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private ProvinceDAO provinceDAO;
	@Resource
	private CityDAO cityDAO;
	@Resource
	private AreaDAO areaDAO;
	@Resource
	private JavaMailSender mailSender;
	@Resource
	private FreeMarkerConfigurer freeMarkerCfj;

	/**
	 * 初始化信息
	 */
	public String initUserInfo(UserVO userVO) throws Exception {
		UserLoginInfo userLoginInfo = getLoginUser();
		UserEntity userEntity = userDAO.get(userLoginInfo.getId());
		List<Object[]> result1 = (List<Object[]>) userDAO
				.list(
						" select   new map(_rct.status , count(_rct.id)) from  UserEntity   as _u    inner join  _u.receiveCreditTasks as _rct where   _u.id=:id group by  _rct.status  order by _rct.status",
						"id", userLoginInfo.getId());
		List<Object[]> result2 = (List<Object[]>) userDAO
				.list(
						"select    new map(_rct.status , count(_rct.id)) from  UserEntity   as _u    inner join  _u.releaseCreditTasks as _rct where   _u.id=:id group by  _rct.status  order by _rct.status",
						"id", userLoginInfo.getId());
		/**
		 * 买家
		 */
		Integer paiedMeCount = 0;
		Integer dispatch = 0;
		Integer evaluatedCount = 0;
		if (result1.size() == 3) {
			for (Object[] obj : result1) {
				if ("1".equals(obj[0])) {
					paiedMeCount = ((Long) obj[1]).intValue();
				} else if ("2".equals(obj[0])) {
					dispatch = ((Long) obj[1]).intValue();
				} else if ("2".equals(obj[0])) {
					evaluatedCount = ((Long) obj[1]).intValue();
				}
			}
		}
		/**
		 * 卖家
		 */
		// 等待接手
		Integer acceptCount;
		// 等等待审核
		Integer verifyCount;
		// 等待我发货
		Integer dispatchedCount;
		// 等待买家确认
		Integer affirmCount;
		// 等待我核查好评
		Integer checkEcaluateCount;
		if (result2.size() == 5) {
			for (Object[] obj : result1) {
				if ("4".equals(obj[0])) {
					acceptCount = ((Long) obj[1]).intValue();
				} else if ("5".equals(obj[0])) {
					verifyCount = ((Long) obj[1]).intValue();
				} else if ("6".equals(obj[0])) {
					dispatchedCount = ((Long) obj[1]).intValue();
				} else if ("7".equals(obj[0])) {
					affirmCount = ((Long) obj[1]).intValue();
				} else if ("8".equals(obj[0])) {
					checkEcaluateCount = ((Long) obj[1]).intValue();
				}
			}
		}

		userVO.setPaiedMeCount(paiedMeCount);
		userVO.setDispatch(dispatch);
		userVO.setEvaluatedCount(evaluatedCount);
		return INPUT;
	}
}
