package net.win.stragegy;

import java.util.Date;

import net.win.BaseDAO;
import net.win.entity.BuyerEntity;
import net.win.utils.DateUtils;

public class ReceiveStrategy {
	/**
	 * 验证接手人是否 一个买号一个月接同一个店铺的只能是6次 ,一个商品一次。
	 * 
	 * @param buyerEntity
	 * @param itemURL
	 * @param baseDAO
	 * @return
	 * @throws Exception
	 */
	public Boolean validateCount(BuyerEntity buyerEntity, String itemURL,
			BaseDAO baseDAO) throws Exception {
		Date startDate = DateUtils.getMonthMinDay(DateUtils.NOW_MONTH);
		Date endDate = new Date();

		Boolean flag = true;
		String hql1 = "select count(*) from CreditTaskEntity as _task"
				+ "  where _task.buyer.id=:buyerId and "
				+ "(_task.releaseDate>=:startDate and _task.releaseDate<=:endDate)"
				+ " group by _task.itemUrl having count(*)>=1";
		Long count = (Long) baseDAO.uniqueResultObject(hql1, new String[]{
				"buyerId", "startDate", "endDate"}, new Object[]{
				buyerEntity.getId(), startDate, endDate});
		if (count == 0) {
			String hql2 = "select count(*) from CreditTaskEntity as _task"
					+ "  join _task.seller as _s where _task.buyer.id=:buyerId and "
					+ "(_task.releaseDate>=:startDate and _task.releaseDate<=:endDate)"
					+ " group by _s.shopURL having count(*)>=6";
			count = (Long) baseDAO.uniqueResultObject(hql2, new String[]{
					"buyerId", "startDate", "endDate"}, new Object[]{
					buyerEntity.getId(), startDate, endDate});
			if (count == 0) {
				flag = true;
			} else {
				flag = false;
			}
		} else {
			flag = false;
		}
		return flag;
	}
}
