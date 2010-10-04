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
			// 修改发布任务
			// String sql = "update"
			// + " Tb_CreditTask "
			// + " set"
			// + " RECEIVE_DATE_=null,"
			// + " RECEIVE_IP_=null,"
			// + " BUYER_ID_=null,"
			// + " STATUS_='1',"
			// + " REMAIN_TIME_=null,"
			// + " RECEIVE_PERSON_=null "
			// + " where"
			// + " ("
			// + " STATUS_='2' "
			// + " or STATUS_='-2'"
			// + " ) "
			// + " and datediff(mi,RECEIVE_DATE_,getdate())>=REMAIN_TIME_";
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
					+ "  and SEC_TO_TIME(UNIX_TIMESTAMP(now())-   UNIX_TIMESTAMP(RECEIVE_DATE_))>=REMAIN_TIME_";

			query = session.createSQLQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();

			// / 定时任务
			session.beginTransaction();
			String sql2 = "update"
					+ " Tb_CreditTask "
					+ "   set"
					+ "       STATUS_='1',"
					+ "   where"
					+ "       STATUS_='0'  and  UNIX_TIMESTAMP(now())-   UNIX_TIMESTAMP(TIMEING_TIME_)>=0";
			query = session.createSQLQuery(sql2);
			query.executeUpdate();
			session.getTransaction().commit();

			// / vip失效
			session.beginTransaction();
			String sql3 = "update"
					+ " UserEntity "
					+ "   set"
					+ "       vipEnable=false,"
					+ "   where"
					+ "  vipEndDate is not null and      second(current_time())>second(vipEndDate)";
			query = session.createQuery(sql3);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			LoggerUtils.error("定时任务错误!", e);
		}

	}

}
