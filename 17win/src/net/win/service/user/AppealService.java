package net.win.service.user;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.AppealDAO;
import net.win.dao.UserDAO;
import net.win.entity.AppealEntity;
import net.win.entity.UserEntity;
import net.win.utils.WinUtils;
import net.win.vo.AppealVO;
import net.win.vo.UpLoadFileVO;

import org.springframework.stereotype.Service;

@SuppressWarnings({"unused", "unchecked"})
@Service("appealService")
public class AppealService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private AppealDAO appealDAO;
	/**
	 * 添加申诉
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public String insertAppeal(AppealVO appealVO) throws Exception {
		UpLoadFileVO upLoadFileVO = appealVO.getUpLoadFileVO();
		String fileName = System.currentTimeMillis() + upLoadFileVO.getSuffix();
		AppealEntity appealEntity = new AppealEntity();
		appealEntity.setTitle(appealVO.getTitle());
		appealEntity.setTaskID(appealVO.getTaskID());
		appealEntity.setType(appealVO.getType());
		appealEntity.setDesc(appealVO.getDesc());
		appealEntity.setAppealDate(new Date());
		appealEntity.setStatus("1");
		appealEntity.setImage(fileName);

		Long id = (Long) userDAO.uniqueResultObject(
				"select id from  UserEntity where username=:username",
				"username", appealVO.getAppealedUser());
		if (id == null) {
			putAlertMsg("该用户不存在！");
			return "insertAppeal";
		}
		
		
		UserEntity appealedUser = new UserEntity();
		appealedUser.setId(id);
		appealEntity.setAppealUser(getLoginUserEntity(userDAO));
		appealEntity.setAppealedUser(appealedUser);
		appealDAO.save(appealEntity);
		// 文件上传
		String destPath = upLoadFileVO.getSavePath() + fileName;
		WinUtils.copy(upLoadFileVO.getFile(), new File(destPath));
		putAlertMsg("申述提交成功！我们为尽快的为您解决！");
		return "insertAppeal";
	}
}
