package net.win.service.quartz;

import javax.annotation.Resource;

import net.win.dao.CreditTaskDAO;
import net.win.utils.LoggerUtils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class TaskQuartz {
	@Resource
	private CreditTaskDAO creditTaskDAO;

	/**
	 * 改变加时状态
	 */
	public void tastStatus() {
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
		Query query;
		Session session = null;
		try {
			session = creditTaskDAO.obtainSession();
			session.beginTransaction();
			String sql = "update"
					+ " Tb_CreditTask "
					+ "   set"
					+ "     RECEIVE_DATE_=null,"
					+ "   RECEIVE_IP_=null,"
					+ "    BUYER_ID_=null,"
					+ "       STATUS_='1',"
					+ "  REMAIN_TIME_=null,"
					+ "  RECEIVE_PERSON_=null "
					+ "   where"
					+ "     ("
					+ "       STATUS_='2' "
					+ "      or STATUS_='-2'"
					+ "    ) "
					+ "  and datediff(mi,RECEIVE_DATE_,getdate())>=REMAIN_TIME_";
			query = session.createSQLQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			LoggerUtils.error("20分钟任务定时错误！", e);
		}

	}
}
