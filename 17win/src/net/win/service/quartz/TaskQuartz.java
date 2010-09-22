package net.win.service.quartz;

import java.util.List;

import javax.annotation.Resource;

import net.win.dao.CreditTaskDAO;
import net.win.entity.CreditTaskEntity;
import net.win.entity.UserEntity;
import net.win.utils.ArithUtils;

public class TaskQuartz {
	@Resource
	private CreditTaskDAO creditTaskDAO;

	/**
	 * 改变加时状态
	 */
	public void tastStatus() {
		List<CreditTaskEntity> tasks = creditTaskDAO
				.list("from CreditTaskEntity as _task where (_task.status='2' or _task.status='-2') and"
						+ " (minute(current_time())-minute(_task.receiveDate))>_task.remainTime");
		if (tasks.size() > 0) {
			for (CreditTaskEntity creditTaskEntity : tasks) {
				UserEntity receiveUser = creditTaskEntity.getReceivePerson();
				receiveUser.setMoney(ArithUtils.add(receiveUser.getMoney(),
						creditTaskEntity.getMoney()));
				receiveUser.setReleaseDot(ArithUtils.add(receiveUser
						.getReleaseDot(), receiveUser.getReleaseDot()));
				creditTaskEntity.setReceivePerson(null);
				creditTaskEntity.setStatus("1");
				creditTaskEntity.setBuyer(null);
				creditTaskEntity.setReceiveIP(null);
				creditTaskEntity.setReceiveDate(null);
				creditTaskEntity.setRemainTime(null);
			}
		}
	}
}
