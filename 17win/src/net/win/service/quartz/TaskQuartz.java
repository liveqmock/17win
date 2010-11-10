package net.win.service.quartz;

import java.util.List;

import javax.annotation.Resource;

import net.win.dao.BuyerDAO;
import net.win.dao.CreditTaskDAO;
import net.win.entity.BuyerEntity;
import net.win.utils.HttpB2CUtils;
import net.win.utils.LoggerUtils;

import org.hibernate.Query;
import org.hibernate.Session;
@SuppressWarnings({"unchecked","unused"})
public class TaskQuartz {
	@Resource
	private CreditTaskDAO creditTaskDAO;
	@Resource
	private BuyerDAO buyerDAO;

	/**
	 * 分钟任务 改变加时状态
	 */
	public void minuteTask() {
		Query query;
		Session session = null;
		try {
			session = creditTaskDAO.obtainSession();
			session.beginTransaction();
			// 修改发布任务 审核 或者等待买家付款
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
					+ "  and (SEC_TO_TIME(UNIX_TIMESTAMP(now())- UNIX_TIMESTAMP(RECEIVE_DATE_))>=REMAIN_TIME_)";
			query = session.createSQLQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();

			// / 修改定时任务
			session.beginTransaction();
			String sql2 = "update"
					+ " Tb_CreditTask "
					+ "   set"
					+ "       STATUS_='1' "
					+ "   where"
					+ "       STATUS_='0'  and  (UNIX_TIMESTAMP(now())-   UNIX_TIMESTAMP(TIMEING_TIME_)>=0)";
			query = session.createSQLQuery(sql2);
			query.executeUpdate();
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			LoggerUtils.error("定时任务错误!", e);
		}
	}

	/**
	 * 每晚24点任务
	 */
	public void clock24OfDayTask() {
		Query query;
		Session session = null;
		try {
			List<BuyerEntity> buyers = buyerDAO
					.list("from BuyerEntity where enable=false and  type in (1,2) ");
			Integer value = 0;
			for (BuyerEntity buyerEntity : buyers) {
				value = HttpB2CUtils.obtainCreditValue(buyerEntity.getName(),
						buyerEntity.getCreditURL(), buyerEntity.getType());
				buyerEntity.setScore(value);
				if (value > 250) {
					buyerEntity.setEnable(false);
				}
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			LoggerUtils.error("定时任务错误!", e);
		}
	}
}
