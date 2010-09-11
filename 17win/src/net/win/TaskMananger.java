package net.win;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.win.utils.DateUtils;

/**
 * 任务管理器 线程安全的
 * 
 * @author xgj
 * 
 */
public final class TaskMananger {
	private static TaskMananger taskMananger = new TaskMananger();

	private TaskMananger() {

	}

	public static TaskMananger getInstance() {
		return taskMananger;
	}

	/**
	 * 生成taskID
	 * 
	 * @param src
	 * @param dest
	 * @throws Exception
	 */
	public synchronized String generateTaskID() throws Exception {
		return DateUtils.parseDate(new Date(), "yyyyMMddHHmmssS");
	}

	/**
	 * 返回随机地址
	 * 
	 * @param addressDAO
	 * @return
	 * @throws Exception
	 */
	public String randomObtainAddress(BaseDAO baseDAO) throws Exception {
		Long addrssCount = (Long) baseDAO
				.uniqueResultObject("select count(*) from  TaskAddressEntity");
		int topIndex = (int) (Math.random() * addrssCount + 1);
		return (String) baseDAO.pageQuery(
				"select name from  TaskAddressEntity", topIndex, 1).get(0);
	}


	private Set<Long> timingTasks = new HashSet<Long>();

	/**
	 * 添加定时任务
	 * 
	 * @param id
	 */
	public void addTimingTask(Long id) {
		timingTasks.add(id);
	}

	/**
	 * 定时任务的大小
	 * 
	 * @param id
	 * @return
	 */
	public Integer timingTaskSize(Long id) {
		return timingTasks.size();
	}

	/**
	 * 移出定时任务
	 * 
	 * @param id
	 */
	public void removeTimingTask(Long id) {
		timingTasks.remove(id);
	}

	/**
	 * 清除所有定时任务
	 * 
	 * @param id
	 */
	public void clearTimingTask(Long id) {
		timingTasks.clear();
	}
}
