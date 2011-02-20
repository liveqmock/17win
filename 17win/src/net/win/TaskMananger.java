package net.win;

import java.util.Date;

import net.win.utils.DateUtils;

/**
 * 任务管理器 线程安全的
 * 
 * @author xgj
 * 
 */
public final class TaskMananger {
	/**
	 * 因为申述被暂停(
	 */ 
	public final static String ALLEGE_STATUS = "-1";
	/**
	 * 审核
	 */
	public final static String AUDIT_STATUS = "-2";
	/**
	 * 定时任务
	 */
	public final static String TIMING_STATUS = "0";
	/**
	 * 等待接手
	 */
	public final static String STEP_ONE_STATUS = "1";
	/**
	 * 买家接手，卖家等待买家付款
	 */
	public final static String STEP_TWO_STATUS = "2";
	/**
	 * 买家付款了。等待卖家确认发货
	 */
	public final static String STEP_THREE_STATUS = "3";
	/**
	 * 卖家发货了。等待买家确认好评
	 */
	public final static String STEP_FOUR_STATUS = "4";
	/**
	 * 买家已经确认好评。等待卖家确认好评
	 */
	public final static String STEP_FIVE_STATUS = "5";
	/**
	 * 任务类型  虚拟， 实物 套餐
	 */
	public final static String TASK_TYPE_VIRTUAL="1";
	public final static String TASK_TYPE_ENTITY="2";
	public final static String TASK_TYPE_MIX="3";
	/**
	 * 完成
	 */
	public final static String STEP_SIX_STATUS = "6";
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
		return DateUtils.format(new Date(), "yyyyMMddHHmmssS");
	}

}
