package net.win.dao;

import java.util.List;

import net.win.BaseDAO;
import net.win.entity.TaskLinkManEntity;

import org.springframework.stereotype.Repository;

@Repository("taskLinkManDAO")
public final class TaskLinkManDAO extends BaseDAO<TaskLinkManEntity> {
	public TaskLinkManEntity findLinkMan(String username, Long userID)
			throws Exception {
		return uniqueResult(
				"from TaskLinkManEntity tlm where  tlm.user.id=:userID and tlm.username=:username",
				new String[] { "userID", "username" }, new Object[] { userID,
						username });
	}

	public List<String> queryLinkMan(Long userID) throws Exception {
		return list(
				"select tlm.username from TaskLinkManEntity tlm where  tlm.user.id=:userID order by lastUseTime",
				new String[] { "userID" }, new Object[] { userID });

	}
}
