package net.win.stragegy;

import java.util.Date;

import net.win.BaseDAO;
import net.win.entity.BuyerEntity;
import net.win.entity.UserEntity;
import net.win.utils.DateUtils;

/**
 * 积分策略
 * 
 * @author Administrator
 * 
 */
public final class ScoreStrategy {
	private ScoreStrategy() {

	}

	/**
	 * 
	 * 通过你的宣传链接注册的会员积分每上升1000 你的收益=100积分
	 * 
	 * @param buyerEntity
	 * @param itemURL
	 * @param baseDAO
	 * @return
	 * @throws Exception
	 */
	public static void updateRefreeScoreByScore(UserEntity userEntity)
			throws Exception {
		if (userEntity.getUpgradeScore() % 1000 == 0) {
			if (userEntity.getReferee() != null) {
				userEntity.getReferee().setUpgradeScore(
						userEntity.getReferee().getUpgradeScore() + 100);
				userEntity.getReferee().setConvertScore(
						userEntity.getReferee().getConvertScore() + 100);
				updateRefreeScoreByScore(userEntity.getReferee());
			}
		}
	}

	/**
	 * 积累接受100个任务 推广人获取10元钱
	 * 
	 * @param userEntity
	 * @throws Exception
	 */
	public static void updateRefreeMoneyByTask(UserEntity userEntity)
			throws Exception {
		if (userEntity.getReceiveTaskCount() % 100 == 0) {
			UserEntity refereeUser = userEntity.getReferee();
			if (refereeUser != null) {
				refereeUser.setMoney(10 + refereeUser.getMoney());
			}
		}
	}
}
