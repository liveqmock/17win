package net.win.dao;

import java.util.List;

import net.win.BaseDAO;
import net.win.entity.TaskLinkManEntity;
import net.win.utils.StringUtils;

import org.springframework.stereotype.Repository;

@Repository("taskLinkManDAO")
public final class TaskLinkManDAO extends BaseDAO<TaskLinkManEntity> {
	public TaskLinkManEntity findLinkMan(String username, Long userID)
			throws Exception {
		return uniqueResult(
				"from TaskLinkManEntity tlm where  tlm.user.id=:userID and tlm.username=:username",
				new String[]{"userID", "username"}, new Object[]{userID,
						username});
	}

	public List<String> queryLinkMan(String username, Long userID)
			throws Exception {
		if (StringUtils.isBlank(username)) {
			return list(
					"select tlm.username from TaskLinkManEntity tlm where  tlm.user.id=:userID order by lastUseTime",
					new String[]{"userID"}, new Object[]{userID});
		} else {
			return list(
					"select tlm.username from TaskLinkManEntity tlm where  tlm.user.id=:userID and tlm.username  like :username  order by lastUseTime",
					new String[]{"userID", "username"}, new Object[]{userID,
							"%" + username + "%"});
		}
	}
}
