package net.win;

import java.util.HashSet;
import java.util.Set;

/**
 * 任务管理器
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
