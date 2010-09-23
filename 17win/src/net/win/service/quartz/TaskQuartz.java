package net.win.service.quartz;

import javax.annotation.Resource;

import net.win.dao.CreditTaskDAO;

import org.hibernate.Query;
import org.hibernate.Session;

public class TaskQuartz {
	@Resource
	private CreditTaskDAO creditTaskDAO;

	/**
	 * 改变加时状态
	 */
	public void tastStatus() throws Exception {
		// List<CreditTaskEntity> tasks = creditTaskDAO
		// .list("from CreditTaskEntity as _task where (_task.status='2' or
		// _task.status='-2') and"
		// + "
		// (minute(current_time())-minute(_task.receiveDate))>_task.remainTime");
		// if (tasks.size() > 0) {
		// for (CreditTaskEntity creditTaskEntity : tasks) {
		// UserEntity receiveUser = creditTaskEntity.getReceivePerson();
		// receiveUser.setMoney(ArithUtils.add(receiveUser.getMoney(),
		// creditTaskEntity.getMoney()));
		// receiveUser.setReleaseDot(ArithUtils.add(receiveUser
		// .getReleaseDot(), receiveUser.getReleaseDot()));
		// creditTaskEntity.setReceivePerson(null);
		// creditTaskEntity.setStatus("1");
		// creditTaskEntity.setBuyer(null);
		// creditTaskEntity.setReceiveIP(null);
		// creditTaskEntity.setReceiveDate(null);
		// creditTaskEntity.setRemainTime(null);
		// }
		// }
		Session session = creditTaskDAO.obtainSession();
		String hql = "update CreditTaskEntity as _task  set _task.receiveDate=null ,_task.receiveIP=null ,_task.buyer=null,_task.status='1',"
				+ "_task.remainTime=null,_task.receivePerson=null where  (_task.status='2' or _task.status='-2') and "
				+ "(minute(current_time())-minute(_task.receiveDate))>_task.remainTime";
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}
}
