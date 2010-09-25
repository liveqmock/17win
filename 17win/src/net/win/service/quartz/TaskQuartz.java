package net.win.service.quartz;

import javax.annotation.Resource;

import net.win.dao.CreditTaskDAO;
import net.win.utils.LoggerUtils;

import org.hibernate.Query;
import org.hibernate.Session;

public class TaskQuartz {
	@Resource
	private CreditTaskDAO creditTaskDAO;

	/**
	 * 改变加时状态
	 */
	public void tastStatus() {
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
			// String sqlHour = "update"
			// + " Tb_CreditTask "
			// + " set"
			// + " REMAIN_TIME_=0"
			// + " where STATUS_='4' and _task.goodTimeType!='1' and
			// (datediff(mi,DISPATCH_DATE_,getdate())> )";
		} catch (Exception e) {
			session.getTransaction().rollback();
			LoggerUtils.error("20分钟任务定时错误！", e);
		}

	}

}
