package net.win.service.quartz;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.win.dao.BuyerDAO;
import net.win.dao.CreditTaskDAO;
import net.win.dao.LogisticsDAO;
import net.win.dao.UserDAO;
import net.win.entity.BuyerEntity;
import net.win.entity.LogisticsEntity;
import net.win.utils.Constant;
import net.win.utils.HttpB2CUtils;
import net.win.utils.LoggerUtils;

import org.hibernate.Query;
import org.hibernate.Session;

@SuppressWarnings( { "unchecked", "unused" })
public class TaskQuartzService {
	@Resource
	private CreditTaskDAO creditTaskDAO;
	@Resource
	private LogisticsDAO logisticsDAO;
	@Resource
	private BuyerDAO buyerDAO;
	@Resource
	private UserDAO userDAO;

	/**
	 * 分钟任务 改变加时状态
	 */
	public void quartzMinuteTask() {
		Query query;
		Session session = null;
		try {
			session = creditTaskDAO.obtainSession();

			// / 修改定时任务
			session.beginTransaction();
			String sql2 = "update"
					+ " Tb_CreditTask "
					+ "   set"
					+ "       STATUS_='1' "
					+ "   where"
					+ "       STATUS_='0'  and  (UNIX_TIMESTAMP(sysdate())-   UNIX_TIMESTAMP(TIMEING_TIME_)>=0)";
			query = session.createSQLQuery(sql2);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			LoggerUtils.error("分钟任务!", e);
		}
	}

	/**
	 * 每晚24点任务
	 */
	public void quartzClock24OfDayTask() {
		Query query;
		Session session = null;
		try {
			// 买家信誉认证
			List<BuyerEntity> buyers = buyerDAO
					.list("from BuyerEntity where enable=true and  type in (1,2) ");
			Integer value = 0;
			for (BuyerEntity buyerEntity : buyers) {
				value = HttpB2CUtils.obtainCreditValue(buyerEntity
						.getCreditURL(), buyerEntity.getType());
				buyerEntity.setScore(value);
				if (value > Constant.getCreditValueLimit()) {
					buyerEntity.setEnable(false);
				}
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			LoggerUtils.error(" 每晚24点任务!", e);
		}
	}

	/**
	 * 每星期任务
	 */
	public void quartzWeekTask() {
		Query query;
		Session session = null;
		try {
			session = creditTaskDAO.obtainSession();
			// / 删除完成的任务
			session.beginTransaction();
			String sql1 = "delete from " + " Tb_CreditTask " + "   where"
					+ "       STATUS_='6'   ";
			query = session.createSQLQuery(sql1);
			query.executeUpdate();
			// 删除资产记录
			Calendar calendarCapital = Calendar.getInstance();
			calendarCapital.add(Calendar.DAY_OF_YEAR, -7);
			String sql2 = "delete from   TB_CapitalLog  where LogTime_<=:logDate  ";
			query = session.createSQLQuery(sql2);
			query.setDate("logDate", calendarCapital.getTime());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			LoggerUtils.error(" 每星期错误!", e);
		}
	}

	/**
	 * 每月
	 */
	public void quartzMonthTask() {
		Query query;
		Session session = null;
		try {
			session = logisticsDAO.obtainSession();
			// / 快递
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, -7);
			List<LogisticsEntity> result = logisticsDAO
					.list(
							"from LogisticsEntity where logDate<=:logDate and status='1'",
							"logDate", calendar.getTime());
			for (LogisticsEntity logisticsEntity : result) {
				logisticsDAO.delete(logisticsEntity);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			LoggerUtils.error(" 每月错误！", e);
		}
	}

	/**
	 * 季度任务
	 */
	public void quartzQuarterTask() {
	}

	/**
	 * 每半年任务
	 */
	public void quartzHalfYearTask() {
	}
}
