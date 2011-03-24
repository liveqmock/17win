package net.win.service.taskLinkMan;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.TaskLinkManDAO;
import net.win.dao.UserDAO;
import net.win.entity.LogisticsEntity;
import net.win.entity.TaskLinkManEntity;
import net.win.entity.UserEntity;
import net.win.utils.StringUtils;
import net.win.vo.LogisticsVO;
import net.win.vo.TaskLinkManVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unused", "unchecked"})
@Service("taskLinkManService")
public class TaskLinkManService extends BaseService {
	@Resource
	private UserDAO userDAO;
	@Resource
	private TaskLinkManDAO taskLinkManDAO;

	public String initAddLinkTaskMan(TaskLinkManVO taskLinkManVO)
			throws Exception {
		putIndexShowType("linkMan");
		return "initAddLinkTaskMan";
	}

	public String insertLinkTaskMan(TaskLinkManVO taskLinkManVO)
			throws Exception {
		putJumpSelfPage("taskLinkManManager/taskLinkMan!initAddLinkTaskMan.php");
		TaskLinkManEntity taskLinkManEntity = new TaskLinkManEntity();
		BeanUtils.copyProperties(taskLinkManEntity, taskLinkManVO);
		if (getLoginUser().getUsername().equalsIgnoreCase(
				taskLinkManEntity.getUsername())) {
			putAlertMsg("添加失败，不能添加自己为联系人！");
			return JUMP;
		}
		UserEntity assignUser = userDAO.findUserByName(taskLinkManEntity
				.getUsername());
		if (assignUser == null || "1".equals(assignUser.getType())) {
			putAlertMsg("添加失败，该用户不存在(可能被系统删除)！");
			return JUMP;
		}
		if (taskLinkManDAO.findLinkMan(taskLinkManEntity.getUsername(),
				getLoginUser().getId()) != null) {
			putAlertMsg("添加失败，该用户已经被您添加过！");
			return JUMP;
		}

		taskLinkManEntity.setUser(userDAO.get(getLoginUser().getId()));
		taskLinkManDAO.save(taskLinkManEntity);
		putAlertMsg("添加成功！");

		return JUMP;

	}
	/**
	 * 查询联系人
	 * 
	 * @param taskLinkManVO
	 * @return
	 * @throws Exception
	 */
	public String queryLinkTaskMan(TaskLinkManVO taskLinkManVO)
			throws Exception {
		List<TaskLinkManVO> result = queryTaskLinkManData(taskLinkManVO);
		putByRequest("result", result);
		return "queryLinkTaskMan";
	}

	private List<TaskLinkManVO> queryTaskLinkManData(TaskLinkManVO taskLinkManVO)
			throws Exception, IllegalAccessException, InvocationTargetException {
		StringBuffer resultHQL = new StringBuffer(
				"select  _t  from TaskLinkManEntity _t inner join _t.user as _u  where _u.id=:userId  ");
		StringBuffer countHQL = new StringBuffer(
				"select  count(*)  from TaskLinkManEntity _t inner join _t.user as _u where _u.id=:userId  ");
		List<String> paramNames = new ArrayList<String>();
		paramNames.add("userId");
		List<Object> paramValues = new ArrayList<Object>();
		paramValues.add(getLoginUser().getId());
		// 时间
		if (taskLinkManVO.getStartDate() != null
				&& taskLinkManVO.getEndDate() != null) {
			resultHQL
					.append(" and (_t.lastUseTime>=:startDate and _t.lastUseTime<=:endDate) ");
			countHQL
					.append(" and (_t.lastUseTime>=:startDate and _t.lastUseTime<=:endDate) ");
			paramNames.add("startDate");
			paramNames.add("endDate");
			paramValues.add(taskLinkManVO.getStartDate());
			paramValues.add(taskLinkManVO.getEndDate());
		} else if (taskLinkManVO.getStartDate() != null) {
			resultHQL.append(" and _t.lastUseTime>=:startDate  ");
			countHQL.append(" and  _t.lastUseTime>=:startDate   ");
			paramNames.add("startDate");
			paramValues.add(taskLinkManVO.getStartDate());
		} else if (taskLinkManVO.getEndDate() != null) {
			resultHQL.append(" and   _t.lastUseTime<=:endDate  ");
			countHQL.append(" and   _t.lastUseTime<=:endDate  ");
			paramNames.add("endDate");
			paramValues.add(taskLinkManVO.getEndDate());
		}
		// 单号
		if (!StringUtils.isBlank(taskLinkManVO.getUsername())) {
			resultHQL.append(" and _t.username = :username");
			countHQL.append(" and _t.username = :username");
			paramNames.add("username");
			paramValues.add(taskLinkManVO.getUsername());
		}
		resultHQL.append(" order by _t.lastUseTime  asc ");
		Long count = (Long) taskLinkManDAO.uniqueResultObject(countHQL
				.toString(), paramNames.toArray(paramNames
				.toArray(new String[paramNames.size()])), paramValues
				.toArray(new Object[paramValues.size()]));
		List<TaskLinkManVO> result = new ArrayList<TaskLinkManVO>();
		if (count > 0) {
			taskLinkManVO.setDataCount(count.intValue());
			List<TaskLinkManEntity> resultTemp = taskLinkManDAO.pageQuery(
					resultHQL.toString(), paramNames.toArray(paramNames
							.toArray(new String[paramNames.size()])),
					paramValues.toArray(new Object[paramValues.size()]),
					taskLinkManVO.getStart(), taskLinkManVO.getLimit());

			TaskLinkManVO taskLinkManVOTemp = null;
			for (TaskLinkManEntity taskLinkManEntityTemp : resultTemp) {
				taskLinkManVOTemp = new TaskLinkManVO();
				BeanUtils.copyProperties(taskLinkManVOTemp,
						taskLinkManEntityTemp);
				result.add(taskLinkManVOTemp);
			}
		}
		return result;
	}

	/**
	 * 删除联系人
	 * 
	 * @param taskLinkManVO
	 * @return
	 * @throws Exception
	 */
	public String deleteLinkTaskMan(TaskLinkManVO taskLinkManVO)
			throws Exception {
		TaskLinkManEntity taskLinkManEntity = taskLinkManDAO.get(taskLinkManVO
				.getId());
		if (taskLinkManEntity == null) {
			putAlertMsg("删除失败，记录已经不存在!");
			return "deleteLinkTaskMan";
		}
		taskLinkManDAO.delete(taskLinkManEntity);
		List<TaskLinkManVO> result = queryTaskLinkManData(taskLinkManVO);
		putByRequest("result", result);
		putAlertMsg("删除成功!");
		return "deleteLinkTaskMan";
	}
}
